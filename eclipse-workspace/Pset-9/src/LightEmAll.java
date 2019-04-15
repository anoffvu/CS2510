import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
  int radius;
  Random rand;
  int score;
  public static int CELL_SIZE = 40;

  // the gameplay constructor
  LightEmAll(int width, int height) {
    this(width, height, 2);
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
      this.radius = (this.calcDiameter() / 2) + 1;
    }
    else if (genType == 1) { // manual board generation
      this.rand = new Random();
      this.width = width;
      this.height = height;
      this.powerRow = 0;
      this.powerCol = 0;
      this.board = this.generateBoard();
      this.nodes = this.grabAllNodes();
      this.mst = new ArrayList<Edge>();
      this.score = 0;
      generateManualConnections();
      updateAllNeighbors();
      this.radius = (this.calcDiameter() / 2) + 1;
      updatePower(this.board);
    }
    else if (genType == 2) { // fractal board generation
      this.rand = new Random();
      this.width = width;
      this.height = height;
      this.powerRow = 0;
      this.powerCol = 0;
      this.board = this.generateBoard();
      this.nodes = this.grabAllNodes();
      this.mst = new ArrayList<Edge>();
      this.score = 0;
      generateFractalConnections(new Posn(0, 0), this.board);
      updateAllNeighbors();
      this.radius = (this.calcDiameter() / 2) + 1;
      updatePower(this.board);
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

  // generates a fractal board
  public void generateFractalConnections(Posn lastKnownPosition,
      ArrayList<ArrayList<GamePiece>> currentBoard) {
    ArrayList<ArrayList<ArrayList<GamePiece>>> splits = // eclipse keeps moving this back
        new ArrayList<ArrayList<ArrayList<GamePiece>>>();
    int splitType = determineSplitType(currentBoard);
    if (splitType == 0) { // no split needed, execute base cases
      int colCount = currentBoard.size();
      int rowCount = currentBoard.get(0).size();
      if (colCount == 2 && rowCount == 2) { // a 2 x 2
        buildU(currentBoard);
      }
      if (colCount == 2 && rowCount == 3) { // a 2 x 3
        buildU(currentBoard);
      }
      if (colCount == 3 && rowCount == 2) { // a 3 x 2
        buildU(currentBoard);
        currentBoard.get(1).get(0).bottom = true;
        currentBoard.get(1).get(1).top = true;
      }
      if (colCount == 3 && rowCount == 3) { // a 3 x 3
        buildU(currentBoard);
        currentBoard.get(1).get(0).bottom = true;
        currentBoard.get(1).get(1).top = true;
        currentBoard.get(1).get(1).bottom = true;
        currentBoard.get(1).get(2).top = true;
      }
    }
    else if (splitType == 1) { // horizontal and vertical splits needed
      buildU(currentBoard);
      // gets top left, top right, bottom left, bottom right splits
      splits = splitBoard(splitType, currentBoard);
      // partition the board
      generateFractalConnections(new Posn(0, 0), splits.get(0));
      generateFractalConnections(new Posn(1, 0), splits.get(1));
      generateFractalConnections(new Posn(0, 1), splits.get(2));
      generateFractalConnections(new Posn(0, 1), splits.get(3));
    }
    else if (splitType == 2) { // only top and bottom split needed
      splits = splitBoard(splitType, currentBoard); // gets top then bottom
      int bottomRow = splits.get(0).get(0).size() - 1;
      int rightCol = splits.get(0).size() - 1;
      if (lastKnownPosition.x == 0) { // add the left connection between top and bottom

        splits.get(0).get(0).get(bottomRow).bottom = true; // top, left, bottom
        splits.get(1).get(0).get(0).top = true; // bottom, left, top
      }
      if (lastKnownPosition.x == 1) { // add the right connection between top and bottom
        splits.get(0).get(rightCol).get(bottomRow).bottom = true; // top, right, bottom
        splits.get(1).get(rightCol).get(0).top = true; // bottom, right, top
      }
      generateFractalConnections(new Posn(0, 0), splits.get(0));
      generateFractalConnections(new Posn(0, 1), splits.get(1));
    }
    else if (splitType == 3) { // only left and right split needed
      buildU(currentBoard);
      splits = splitBoard(splitType, currentBoard); // gets left then right
      generateFractalConnections(new Posn(0, 0), splits.get(0));
      generateFractalConnections(new Posn(1, 0), splits.get(1));
    }
  }

  // splits the board in the desired manner
  public ArrayList<ArrayList<ArrayList<GamePiece>>> splitBoard(int splitType,
      ArrayList<ArrayList<GamePiece>> boardToSplit) {
    ArrayList<ArrayList<ArrayList<GamePiece>>> ret = // eclipse keeps moving this back
        new ArrayList<ArrayList<ArrayList<GamePiece>>>();
    // this funky math is so java can actually round up correctly
    int splitCol = boardToSplit.size() / 2 + ((boardToSplit.size() % 2 == 0) ? 0 : 1);
    int splitRow = boardToSplit.get(0).size() / 2 + ((boardToSplit.get(0).size() % 2 == 0) ? 0 : 1);
    if (splitType == 1) { // split into 4 quadrants
      ArrayList<ArrayList<GamePiece>> quad1 = new ArrayList<ArrayList<GamePiece>>();
      ArrayList<ArrayList<GamePiece>> quad2 = new ArrayList<ArrayList<GamePiece>>();
      ArrayList<ArrayList<GamePiece>> quad3 = new ArrayList<ArrayList<GamePiece>>();
      ArrayList<ArrayList<GamePiece>> quad4 = new ArrayList<ArrayList<GamePiece>>();
      for (int c = 0; c < splitCol; c++) {
        quad1.add(new ArrayList<GamePiece>(boardToSplit.get(c).subList(0, splitRow)));
        quad3.add(new ArrayList<GamePiece>(
            boardToSplit.get(c).subList(splitRow, boardToSplit.get(0).size())));
      }
      for (int c = splitCol; c < boardToSplit.size(); c++) {
        quad2.add(new ArrayList<GamePiece>(boardToSplit.get(c).subList(0, splitRow)));
        quad4.add(new ArrayList<GamePiece>(
            boardToSplit.get(c).subList(splitRow, boardToSplit.get(0).size())));
      }
      ret.add(quad1);
      ret.add(quad2);
      ret.add(quad3);
      ret.add(quad4);
    }
    if (splitType == 2) { // split top and bottom
      ArrayList<ArrayList<GamePiece>> top = new ArrayList<ArrayList<GamePiece>>();
      ArrayList<ArrayList<GamePiece>> bottom = new ArrayList<ArrayList<GamePiece>>();
      for (int c = 0; c < boardToSplit.size(); c++) {
        top.add(new ArrayList<GamePiece>(boardToSplit.get(c).subList(0, splitRow)));
        bottom.add(new ArrayList<GamePiece>(
            boardToSplit.get(c).subList(splitRow, boardToSplit.get(0).size())));
      }
      ret.add(top);
      ret.add(bottom);
    }
    if (splitType == 3) { // split left and right
      ArrayList<ArrayList<GamePiece>> left = new ArrayList<ArrayList<GamePiece>>(
          boardToSplit.subList(0, splitCol));
      ArrayList<ArrayList<GamePiece>> right = new ArrayList<ArrayList<GamePiece>>(
          boardToSplit.subList(splitCol, boardToSplit.size()));
      ret.add(left);
      ret.add(right);
    }
    return ret;
  }

  public int determineSplitType(ArrayList<ArrayList<GamePiece>> currentBoard) {
    int colCount = currentBoard.size();
    int rowCount = currentBoard.get(0).size();
    if (colCount < 4 && rowCount < 4) { // no split needed
      return 0;
    }
    else if (colCount >= 4 && rowCount >= 4) { // vertical and horizontal split needed
      return 1;
    }
    else if (colCount < 4 && rowCount >= 4) { // top and bottom split needed
      return 2;
    }
    else if (colCount >= 4 && rowCount < 4) { // left and right split needed
      return 3;
    }
    return -1; // error
  }

  // makes the u pattern on the passed in board
  public void buildU(ArrayList<ArrayList<GamePiece>> currentBoard) {
    for (int r = 0; r < currentBoard.get(0).size(); r++) { // only iterating by each rows here
      if ((r != (currentBoard.get(0).size() - 1)) && r != 0) { // not the bottom or top row
        currentBoard.get(0).get(r).top = true;
        currentBoard.get(0).get(r).bottom = true;
        currentBoard.get(currentBoard.size() - 1).get(r).top = true;
        currentBoard.get(currentBoard.size() - 1).get(r).bottom = true;
      }
      else if (r == 0) { // top row
        currentBoard.get(0).get(r).bottom = true;
        currentBoard.get(currentBoard.size() - 1).get(r).bottom = true;
      }
      else if (r == (currentBoard.get(0).size() - 1)) { // bottom row
        for (int c = 1; c < currentBoard.size() - 1; c++) { // all nodes between left & right ones
          currentBoard.get(c).get(r).left = true;
          currentBoard.get(c).get(r).right = true;
        }
        currentBoard.get(0).get(r).top = true;
        currentBoard.get(0).get(r).right = true;
        currentBoard.get(currentBoard.size() - 1).get(r).top = true;
        currentBoard.get(currentBoard.size() - 1).get(r).left = true;
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
    }
    else if (button.equals("RightButton")) { // rotate it counter clockwise
      clicked.rotatePiece(-1);
      this.score++; // updates the score when a valid move is executed
    }
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
            this.board.get(c).get(r).drawPiece(this.radius)
                .movePinhole((-.5 * LightEmAll.CELL_SIZE), (-.5 * LightEmAll.CELL_SIZE)),
            (c * LightEmAll.CELL_SIZE), (r * LightEmAll.CELL_SIZE));
      }
    }
    // combines the boards
    gameScene.placeImageXY(scoreBoard, boardWidth / 2, boardHeight + CELL_SIZE);
    // displays restart instructions
    gameScene.placeImageXY(new TextImage("Press space to restart.", 15, Color.BLACK),
        boardWidth / 5, boardHeight + CELL_SIZE);
    return gameScene;
  }

  // restarts the game
  public void restartGame() {
    LightEmAll newGame = new LightEmAll(this.width, this.height);
    this.board = newGame.board;
    this.nodes = newGame.nodes;
    this.mst = newGame.mst;
    this.width = newGame.width;
    this.height = newGame.height;
    this.powerRow = newGame.powerRow;
    this.powerCol = newGame.powerCol;
    this.radius = newGame.radius;
    this.rand = newGame.rand;
    this.score = 0;
  }

  // powers the board
  public void updatePower(ArrayList<ArrayList<GamePiece>> targetBoard) {
    // resets the power levels
    for (GamePiece g : this.nodes) {
      g.powerLevel = 0;
    }
    targetBoard.get(powerCol).get(powerRow).powerStation = true; // sets the station
    targetBoard.get(powerCol).get(powerRow).powerLevel = this.radius; // sets power level
    // passes power to all neighbors
    targetBoard.get(powerCol).get(powerRow).powerNeighbors(new ArrayList<GamePiece>());
  }

  // grabs the farthest reachable node from the given node
  public GamePiece getFarthestNode(GamePiece startNode) {
    HashMap<GamePiece, Integer> distMap = generateDistanceMap(startNode);
    GamePiece farthestNode = startNode;
    int max = 0;
    // iterates over every entry in the distance map
    for (Map.Entry<GamePiece, Integer> entry : distMap.entrySet()) {
      GamePiece key = entry.getKey();
      Integer value = entry.getValue();
      // if the distance is larger, it will make that the farthest node and update the max
      if (value > max) {
        max = value;
        farthestNode = key;
      }
    }
    return farthestNode;
  }

  // calculates the diameter of this game
  public int calcDiameter() {
    // grabs the farthest node from the powerStation
    GamePiece farthestFromPower = this.getFarthestNode(this.board.get(powerCol).get(powerRow));
    // grabs the farthest node from whatever node that returns ^
    GamePiece farthestSecond = this.getFarthestNode(farthestFromPower);
    // add 1 to count the start node
    return generateDistanceMap(farthestFromPower).get(farthestSecond) + 1;
  }

  // creates a distance map of all the GamePieces reachable from the passed in GamePiece
  public HashMap<GamePiece, Integer> generateDistanceMap(GamePiece startNode) {
    ArrayList<String> directions = new ArrayList<String>(
        Arrays.asList("left", "right", "top", "bottom"));
    ArrayDeque<GamePiece> queue = new ArrayDeque<GamePiece>();
    ArrayList<GamePiece> seen = new ArrayList<GamePiece>();
    HashMap<GamePiece, Integer> distMap = new HashMap<GamePiece, Integer>();
    queue.addFirst(startNode);
    distMap.put(startNode, 0);
    while (!queue.isEmpty()) {
      GamePiece next = queue.removeFirst();
      if (!seen.contains(next)) {
        seen.add(next);
        for (String dir : directions) { // for each direction that a GamePiece has
          // if it has a connection in that direction and the connection isnt already seen
          if (next.isConnectedTo(dir) && !seen.contains(next.neighbors.get(dir))) {
            queue.addFirst(next.neighbors.get(dir));
            distMap.put(next.neighbors.get(dir), distMap.get(next) + 1);
          }
        }
      }
    }
    return distMap;
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