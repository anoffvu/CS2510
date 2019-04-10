import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javalib.impworld.World;
import javalib.impworld.WorldScene;
import javalib.worldimages.OutlineMode;
import javalib.worldimages.OverlayImage;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldImage;

// game state and main game class
class LightEmAll extends World {

  // a list of columns of GamePieces,
  // i.e., represents the board in column-major order
  ArrayList<ArrayList<GamePiece>> board;
  // a list of all nodes
  ArrayList<GamePiece> nodes;
  // a list of edges of the minimum spanning tree
  ArrayList<Edge> mst;
  // the width and height of the board
  int width; // column count
  int height; // row count
  // the current location of the power station,
  // as well as its effective radius
  int powerRow;
  int powerCol;
  public static int radius = 3;
  Random rand;
  int score; // TODO display this later
  public static int CELL_SIZE = 40;

  // the gameplay constructor
  LightEmAll(int width, int height) {
    this.rand = new Random();
    this.width = width;
    this.height = height;
    this.powerRow = 0;
    this.powerCol = 0;
    this.board = this.generateBoard();
    this.nodes = this.grabAllNodes();
    updateAllNeighbors();
    this.mst = new ArrayList<Edge>();
    this.score = 0;
  }

  // constructor for generating different types of boards
  LightEmAll(int width, int height, int genType) {
    if (genType == -1) { // this will just make a blank board, no connections or neighbors
      this.rand = new Random();
      this.width = width;
      this.height = height;
      this.powerRow = 0;
      this.powerCol = 0;
      this.board = this.generateBoard();
      this.nodes = this.grabAllNodes();
      this.mst = new ArrayList<Edge>();
      this.score = 0;
    }
    else if (genType == 1) { // manual board generation
      this.rand = new Random();
      this.width = width;
      this.height = height;
      this.powerRow = 5;
      this.powerCol = 5;
      this.board = this.generateBoard();
      this.nodes = this.grabAllNodes();
      generateManualConnections();
      updateAllNeighbors();
      updatePower(this.board);
      this.mst = new ArrayList<Edge>();
      this.score = 0;
    }
    else if (genType == 2) { // fractal board generation
      this.rand = new Random();
      this.width = width;
      this.height = height;
      this.powerRow = 0;
      this.powerCol = 0;
      this.board = this.generateBoard();
      this.nodes = this.grabAllNodes();
      // TODO createFractalConnections();
      updateAllNeighbors();
      updatePower(this.board);
      this.mst = new ArrayList<Edge>();
      this.score = 0;
    }
  }

  // will grab all the boards cells, left to right, then top to bottom
  public ArrayList<GamePiece> grabAllNodes() {
    ArrayList<GamePiece> allNodes = new ArrayList<GamePiece>();
    for (int c = 0; c < this.width; c++) {
      for (int r = 0; r < this.height; r++) {
        allNodes.add(this.board.get(c).get(r));
      }
    }
    return allNodes;
  }

  // creates manual connections
  public void generateManualConnections() {
    int middleColIndex = (int) Math.floor(this.width / 2);
    for (int c = 0; c < this.width; c++) {
      for (int r = 0; r < this.height; r++) {
        if (c == 0) { // left column
          this.board.get(c).get(r).left = false;
          this.board.get(c).get(r).right = true;
          this.board.get(c).get(r).top = false;
          this.board.get(c).get(r).bottom = false;
        }
        else if (c == middleColIndex) { // middle column
          this.board.get(c).get(r).left = true;
          this.board.get(c).get(r).right = true;
          this.board.get(c).get(r).top = true;
          this.board.get(c).get(r).bottom = true;
        }
        else if ((c + 1) == this.width) { // right column
          this.board.get(c).get(r).left = true;
          this.board.get(c).get(r).right = false;
          this.board.get(c).get(r).top = false;
          this.board.get(c).get(r).bottom = false;
        }
        else { // all other columns
          this.board.get(c).get(r).left = true;
          this.board.get(c).get(r).right = true;
          this.board.get(c).get(r).top = false;
          this.board.get(c).get(r).bottom = false;
        }
      }
    }
  }

  // builds the board, does not create connections or powerStation
  public ArrayList<ArrayList<GamePiece>> generateBoard() {
    ArrayList<ArrayList<GamePiece>> genBoard = new ArrayList<ArrayList<GamePiece>>();
    for (int c = 0; c < this.width; c++) {
      genBoard.add(new ArrayList<GamePiece>());
      for (int r = 0; r < this.height; r++) {
        genBoard.get(c).add(new GamePiece(r, c, false, false, false, false));
      }
    }
    return genBoard;
  }

  // handles clicks
  public void onMouseClicked(Posn mouse, String button) {
    GamePiece clicked = locatePiece(mouse);
    if (button.equals("LeftButton")) { // rotate it clockwise
      clicked.rotatePiece(1);
      this.score++; // updates the score when a valid move is executed
      // will want to update connectivity in the future
    }
    else if (button.equals("RightButton")) { // rotate it counter clockwise
      clicked.rotatePiece(-1);
      this.score++; // updates the score when a valid move is executed
      // will want to update connectivity in the future
    }
    // TODO if we have time, just update this cells neighbors clicked.updateNeighbors();
    updateAllNeighbors();
    updatePower(this.board);
  }

  // adds all the neighbors to each cell of the game board
  public void updateAllNeighbors() {
    // resets all connections
    for (GamePiece g : nodes) {
      g.updateNeighbor("top", null);
      g.updateNeighbor("right", null);
      g.updateNeighbor("bottom", null);
      g.updateNeighbor("left", null);
    }
    for (int c = 0; c < this.width; c++) {
      // column value
      int left = c - 1;
      int right = c + 1;
      for (int r = 0; r < this.height; r++) {
        // row value
        int top = r - 1;
        int bottom = r + 1;
        if (top >= 0) {
          this.board.get(c).get(r).updateNeighbor("top", this.board.get(c).get(top));
        }
        if (bottom < this.height) {
          this.board.get(c).get(r).updateNeighbor("bottom", this.board.get(c).get(bottom));
        }
        if (left >= 0) {
          this.board.get(c).get(r).updateNeighbor("left", this.board.get(left).get(r));
        }
        if (right < this.width) {
          this.board.get(c).get(r).updateNeighbor("right", this.board.get(right).get(r));
        }
      }
    }
  }

  // finds the cell at the given posn
  public GamePiece locatePiece(Posn mouse) {
    int row = (int) Math.floor(mouse.y / LightEmAll.CELL_SIZE);
    int col = (int) Math.floor(mouse.x / LightEmAll.CELL_SIZE);
    return this.board.get(col).get(row);
  }

  // draws the scene
  public WorldScene makeScene() {
    int boardWidth = this.width * LightEmAll.CELL_SIZE;
    int boardHeight = this.height * LightEmAll.CELL_SIZE;
    WorldScene gameScene = new WorldScene(0, 0);
    // a scoreboard to be displayed at the bottom
    WorldImage scoreBoard = new OverlayImage(
        new TextImage(Integer.toString(this.score), LightEmAll.CELL_SIZE, Color.GREEN),
        new OverlayImage(
            new RectangleImage(3 * CELL_SIZE, (int) 1.2 * CELL_SIZE, OutlineMode.SOLID,
                Color.black),
            new RectangleImage(boardWidth, 2 * CELL_SIZE, OutlineMode.SOLID, Color.lightGray)));
    // begins drawing the game board
    for (int c = 0; c < this.width; c++) {
      for (int r = 0; r < this.height; r++) {
        gameScene.placeImageXY(
            this.board.get(c).get(r).drawPiece().movePinhole((-.5 * LightEmAll.CELL_SIZE),
                (-.5 * LightEmAll.CELL_SIZE)),
            (c * LightEmAll.CELL_SIZE), (r * LightEmAll.CELL_SIZE));
      }
    }
    // combines the boards
    gameScene.placeImageXY(scoreBoard, boardWidth / 2, boardHeight + CELL_SIZE);
    return gameScene;
  }

  // restarts the game
  public void restartGame() {
    LightEmAll newGame = new LightEmAll(this.width, this.height, 1); // TODO change this to default
    this.nodes = newGame.nodes;
    this.board = newGame.board;
    this.mst = newGame.mst;
    this.width = newGame.width;
    this.height = newGame.height;
    this.powerRow = newGame.powerRow;
    this.powerCol = newGame.powerCol;
    this.rand = newGame.rand;
  }

  // powers the board
  public void updatePower(ArrayList<ArrayList<GamePiece>> targetBoard) {
    // resets the power levels
    for (GamePiece g : this.nodes) {
      g.powerLevel = 0;
    }
    targetBoard.get(powerCol).get(powerRow).powerStation = true; // sets the station
    targetBoard.get(powerCol).get(powerRow).powerLevel = radius; // sets power level
    // passes power to all neighbors
    targetBoard.get(powerCol).get(powerRow).powerNeighbors(new ArrayList<GamePiece>());
  }

  // handles key events
  public void onKeyEvent(String pressedKey) {
    GamePiece powerStationPiece = this.board.get(powerCol).get(powerRow);
    // moves the powerStation
    if (pressedKey.equals("up") && this.powerRow > 0 && powerStationPiece.isConnectedTo("top")) {
      this.board.get(powerCol).get(powerRow).powerStation = false;
      this.powerRow -= 1;
    }
    if (pressedKey.equals("down") && this.powerRow < this.height - 1
        && powerStationPiece.isConnectedTo("bottom")) {
      this.board.get(powerCol).get(powerRow).powerStation = false;
      this.powerRow += 1;
    }
    if (pressedKey.equals("left") && this.powerCol > 0 && powerStationPiece.isConnectedTo("left")) {
      this.board.get(powerCol).get(powerRow).powerStation = false;
      this.powerCol -= 1;
    }
    if (pressedKey.equals("right") && this.powerCol < this.width - 1
        && powerStationPiece.isConnectedTo("right")) {
      this.board.get(powerCol).get(powerRow).powerStation = false;
      this.powerCol += 1;
    }
    if (pressedKey.equals(" ") && this.powerCol < this.width) { // restarts the game
      restartGame();
    }
    updatePower(this.board);
  }

}