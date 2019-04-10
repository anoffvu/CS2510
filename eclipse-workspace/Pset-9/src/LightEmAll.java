import java.util.ArrayList;
import java.util.Random;

import javalib.impworld.World;
import javalib.impworld.WorldScene;
import javalib.worldimages.Posn;

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

  LightEmAll(int width, int height) {
    this.rand = new Random();
    this.width = width;
    this.height = height;
    this.powerRow = 0;
    this.powerCol = 0;
    // this.radius = 10;
    this.nodes = this.generateAllNodes();
    this.board = this.placeNodes();
    updateAllNeighbors();
    this.mst = new ArrayList<Edge>();
    this.score = 0;
  }

  // constructor for generating different types of boards
  LightEmAll(int width, int height, int genType) {
    if (genType == 0) {
      this.rand = new Random();
      this.width = width;
      this.height = height;
      this.powerRow = 0;
      this.powerCol = 0;
      this.radius = 99;
      this.nodes = this.generateAllNodes();
      this.board = this.placeNodes();
      this.mst = new ArrayList<Edge>();
      this.score = 0;
    }
    else if (genType == 1) { // fractal board generation
      this.rand = new Random();
      this.width = width;
      this.height = height;
      this.powerRow = 0;
      this.powerCol = 0;
      this.radius = 99;
      // this.nodes = this.generateAllFractalNodes();
      this.board = this.placeNodes();
      this.mst = new ArrayList<Edge>();
      this.score = 0;
    }
    updateAllNeighbors();

  }

  // will generate all the nodes in one flat list
  public ArrayList<GamePiece> generateAllNodes() {
    int middleColIndex = (int) Math.floor(this.width / 2);
    ArrayList<GamePiece> allNodes = new ArrayList<GamePiece>();
    for (int c = 0; c < this.width; c++) {
      for (int r = 0; r < this.height; r++) {
        if (c == 0) { // left column
          allNodes.add(new GamePiece(c, r, false, true, false, false));
        }
        else if (c == middleColIndex) { // middle column
          allNodes.add(new GamePiece(middleColIndex, r, true, true, true, true));
        }
        else if ((c + 1) == this.width) { // right column
          allNodes.add(new GamePiece(c, r, true, false, false, false));
        }
        else { // all other columns
          allNodes.add(new GamePiece(c, r, true, true, false, false));
        }
      }
    }
    return allNodes;
  }

  // builds the board with the Nodes
  // is it better to place them all
  public ArrayList<ArrayList<GamePiece>> placeNodes() {
    int nodesIndex = 0;
    // puts the cells inside the grid
    ArrayList<ArrayList<GamePiece>> columns = new ArrayList<ArrayList<GamePiece>>();
    for (int c = 0; c < this.width; c++) {
      columns.add(new ArrayList<GamePiece>());
      for (int r = 0; r < this.height; r++) {
        columns.get(c).add(r, this.nodes.get(nodesIndex));
        nodesIndex++;
      }
    }
    // TODO test these
    powerBoard(columns);
    return columns;
  }

  /*
   * // places the powerStation in the middle of the nodes
   * public void placePowerStation(ArrayList<ArrayList<GamePiece>> targetBoard) {
   * 
   * int powerPlantIndex = (int) ((this.width * this.height) / 2);
   * // on even number boards it'll round down
   * int totalNodes = this.width * this.height;
   * for (int i = 0; i < totalNodes; i++) {
   * if (i == powerPlantIndex) {
   * allNodes.get(i).powerStation = true;
   * }
   * }
   * 
   * 
   * }
   */

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
    resetPower();
    powerBoard(this.board);
    updateAllNeighbors();
    System.out.println(this.score);
  }

  // resets the power so the board can update when clicked
  public void resetPower() {
    for (int c = 0; c < this.width; c++) {
      for (int r = 0; r < this.height; r++) {
        this.board.get(c).get(r).powerLevel = 0;
      }
    }
  }

  // adds all the neighbors to each cell of the game board
  public void updateAllNeighbors() {
    for (int c = 0; c < this.width; c++) {
      // column value
      int left = c - 1;
      int right = c + 1;
      for (int r = 0; r < this.height; r++) {
        // row value
        int top = r - 1;
        int bottom = r + 1;
        this.board.get(c).get(r).updateNeighbor("top", null);
        this.board.get(c).get(r).updateNeighbor("right", null);
        this.board.get(c).get(r).updateNeighbor("bottom", null);
        this.board.get(c).get(r).updateNeighbor("left", null);
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
    return board.get(col).get(row);
  }

  // draws the scene
  public WorldScene makeScene() {
    WorldScene scene = new WorldScene(this.width * LightEmAll.CELL_SIZE,
        this.height * LightEmAll.CELL_SIZE);
    for (int c = 0; c < this.width; c++) {
      for (int r = 0; r < this.height; r++) {
        scene.placeImageXY(
            this.board.get(c).get(r).drawPiece().movePinhole((-.5 * LightEmAll.CELL_SIZE),
                (-.5 * LightEmAll.CELL_SIZE)),
            (c * LightEmAll.CELL_SIZE), (r * LightEmAll.CELL_SIZE));
      }
    }
    return scene;
  }

  // restarts the game
  public void restartGame() {
    LightEmAll newGame = new LightEmAll(this.width, this.height);
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
  public void powerBoard(ArrayList<ArrayList<GamePiece>> targetBoard) {
    targetBoard.get(powerCol).get(powerRow).powerStation = true;
    targetBoard.get(powerCol).get(powerRow).powerLevel = radius;
    targetBoard.get(powerCol).get(powerRow).powerNeighbors(new ArrayList<GamePiece>());
  }

  // handles key events
  public void onKeyEvent(String pressedKey) {
    // moves the powerStation
    if (pressedKey.equals("up") && this.powerRow > 0
        && (this.board.get(powerCol).get(powerRow).neighbors.get("up") != null)) {
      this.powerRow -= 1;
    }
    if (pressedKey.equals("down") && this.powerRow < this.height - 1
        && (this.board.get(powerCol).get(powerRow).neighbors.get("down") != null)) {
      this.powerRow += 1;
    }
    if (pressedKey.equals("left") && this.powerCol > 0
        && (this.board.get(powerCol).get(powerRow).neighbors.get("left") != null)) {
      this.powerCol -= 1;
    }
    if (pressedKey.equals("right") && this.powerCol < this.width - 1
        && (this.board.get(powerCol).get(powerRow).neighbors.get("right") != null)) {
      this.powerCol += 1;
    }
    if (pressedKey.equals(" ") && this.powerCol < this.width) { // restarts the game
      restartGame();
    }
    resetPower();
    powerBoard(this.board);
  }

}