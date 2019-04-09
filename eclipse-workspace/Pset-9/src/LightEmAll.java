import java.util.ArrayList;
import java.util.Random;

import javalib.impworld.World;
import javalib.impworld.WorldScene;
import javalib.worldimages.Posn;

public class LightEmAll extends World {

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

  // maybe make a hashmap of neighbors // connectedness

  public static int CELL_SIZE = 40;

  LightEmAll(int width, int height) {
    this.rand = new Random();
    this.width = width;
    this.height = height;
    this.powerRow = 0;
    this.powerCol = 0;
    this.radius = 99;
    this.nodes = this.generateAllNodes();
    this.board = this.placeNodes();
    this.mst = new ArrayList<Edge>();
  }

  // will generate all the nodes in one flat list
  // first row will only have connections down, the middle row will have all
  // connections
  // the last row will only have connections up, all other rows have up and down
  // should we give these nodes a row and column count already?
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
    placePowerStation(allNodes);
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
    return columns;
  }

  // places the powerStation in the middle of the nodes
  public void placePowerStation(ArrayList<GamePiece> allNodes) {
    int powerPlantIndex = (int) ((this.width * this.height) / 2); // on even
                                                                  // number
                                                                  // boards
                                                                  // it'll
    // round down
    int totalNodes = this.width * this.height;
    for (int i = 0; i < totalNodes; i++) {
      if (i == powerPlantIndex) {
        allNodes.get(i).powerStation = true;
      }
    }

  }

  public void onMouseClicked(Posn mouse, String button) {
    GamePiece clicked = locatePiece(mouse);
    if (button.equals("LeftButton")) { // rotate it clockwise
      clicked.rotatePiece(1);
      // this.updateConnections(clicked);
    }
    else if (button.equals("RightButton")) { // rotate it counter clockwise
      clicked.rotatePiece(2);
      // this.updateConnections(clicked);
    }
  }

  // adds all the neighbors to each cell of the game board
  public void addNeighbors() {
    for (int c = 0; c < this.width; c++) {
      // column value
      int left = c - 1;
      int right = c + 1;
      for (int r = 0; r < this.height; r++) {
        // row value
        int top = r - 1;
        int bottom = r + 1;
        if (top >= 0) {
          this.board.get(c).get(r).addNeighbor("top", this.board.get(c).get(top));
        }
        if (bottom < this.height) {
          this.board.get(c).get(r).addNeighbor("bottom", this.board.get(c).get(bottom));
        }
        if (left >= 0) {
          this.board.get(c).get(r).addNeighbor("left", this.board.get(left).get(r));
        }
        if (right < this.width) {
          this.board.get(c).get(r).addNeighbor("right", this.board.get(right).get(r));
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
}