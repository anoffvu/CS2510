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

  public static int CELL_SIZE = 30;

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
  // first row will only have connections down, the middle row will have all connections
  // the last row will only have connections up, all other rows have up and down
  // should we give these nodes a row and column count already?
  public ArrayList<GamePiece> generateAllNodes() {
    int middleRowIndex = (int) this.height / 2;
    ArrayList<GamePiece> allNodes = new ArrayList<GamePiece>();
    for (int y = 0; y < this.height; y++) {
      for (int x = 0; x < this.width; x++) {
        if (y == 0) { // top row
          allNodes.add(new GamePiece(y, x, false, false, false, true));
        }
        else if (y == middleRowIndex) { // middle row
          allNodes.add(new GamePiece(middleRowIndex, x, true, true, true, true));
        }
        else if ((y + 1) == this.height) { // bottom row
          allNodes.add(new GamePiece(y, x, false, false, true, false));
        }
        else { // all other rows
          allNodes.add(new GamePiece(y, x, false, false, true, true));
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
    ArrayList<ArrayList<GamePiece>> rows = new ArrayList<ArrayList<GamePiece>>();
    for (int y = 0; y < this.height; y++) {
      rows.add(new ArrayList<GamePiece>());
      for (int x = 0; x < this.width; x++) {
        rows.get(y).add(x, this.nodes.get(nodesIndex));
        nodesIndex++;
      }
    }
    return rows;
  }

  // places the powerStation in the middle of the nodes
  public void placePowerStation(ArrayList<GamePiece> allNodes) {
    int powerPlantIndex = (int) ((this.width * this.height) / 2); // on even number boards it'll
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
      this.updateConnections(clicked);
    }
    else if (button.equals("RightButton")) { // rotate it counter clockwise
      clicked.rotatePiece(2);
      this.updateConnections(clicked);
    }
  }

  private void updateConnections(GamePiece clicked) {
    // TODO grab

  }

  // finds the cell at the given posn
  public GamePiece locatePiece(Posn mouse) {
    int colNum = (int) Math.floor(mouse.x / LightEmAll.CELL_SIZE);
    int rowNum = (int) Math.floor(mouse.y / LightEmAll.CELL_SIZE);
    return board.get(colNum).get(rowNum);
  }

  // draws the scene
  public WorldScene makeScene() {
    WorldScene scene = new WorldScene(this.width * LightEmAll.CELL_SIZE,
        this.height * LightEmAll.CELL_SIZE);
    for (int x = 0; x < this.width; x++) {
      for (int y = 0; y < this.height; y++) {
        scene.placeImageXY(
            this.board.get(x).get(y).drawCell().movePinhole((-.5 * LightEmAll.CELL_SIZE),
                (-.5 * LightEmAll.CELL_SIZE)),
            (x * LightEmAll.CELL_SIZE), (y * LightEmAll.CELL_SIZE));
      }
    }
    return scene;
  }
}
