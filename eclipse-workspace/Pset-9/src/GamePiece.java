import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import javalib.worldimages.OutlineMode;
import javalib.worldimages.OverlayImage;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.RotateImage;
import javalib.worldimages.WorldImage;

public class GamePiece {
  // in logical coordinates, with the origin
  // at the top-left corner of the screen
  int row;
  int col;
  // whether this GamePiece is connected to the
  // adjacent left, right, top, or bottom pieces
  boolean left;
  boolean right;
  boolean top;
  boolean bottom;
  // whether the power station is on this piece
  boolean powerStation;
  int powerLevel;
  HashMap<String, GamePiece> neighbors;

  GamePiece(int row, int col, ArrayList<GamePiece> neighbors, boolean left, boolean right,
      boolean top, boolean bottom, boolean powerStation, int powerLevel) {
    this.row = row;
    this.col = col;
    this.left = left;
    this.right = right;
    this.top = top;
    this.bottom = bottom;
    this.powerStation = powerStation;
    this.powerLevel = powerLevel;
    this.neighbors = new HashMap<String, GamePiece>();
    this.neighbors.put("left", null);
    this.neighbors.put("right", null);
    this.neighbors.put("top", null);
    this.neighbors.put("bottom", null);

  }

  GamePiece(int row, int col, boolean left, boolean right, boolean top, boolean bottom,
      boolean powerStation) {
    this(row, col, new ArrayList<GamePiece>(), left, right, top, bottom, powerStation, 0);

  }

  // convenience constructor for all inputs but powerStation
  GamePiece(int row, int col, boolean left, boolean right, boolean top, boolean bottom) {
    this(row, col, left, right, top, bottom, false);
  }

  // convenience constructors for non powerStations, up and down connections
  GamePiece(int row, int col) {
    this(row, col, false, false, true, true, false);
  }

  public WorldImage drawPiece() {
    WorldImage base = new RectangleImage(LightEmAll.CELL_SIZE, LightEmAll.CELL_SIZE,
        OutlineMode.SOLID, Color.darkGray);
    WorldImage connection = new RectangleImage((int) LightEmAll.CELL_SIZE / 6,
        (int) LightEmAll.CELL_SIZE / 2, OutlineMode.SOLID, Color.BLUE).movePinhole(0,
            (int) LightEmAll.CELL_SIZE / 4);

    // TODO draw the correct connections by rotating shit
    if (this.top) {
      base = new OverlayImage(connection, base);
    }
    base = new RotateImage(base, 90.0);
    if (this.left) {
      base = new OverlayImage(connection, base);
    }
    base = new RotateImage(base, 90.0);
    if (this.bottom) {
      base = new OverlayImage(connection, base);
    }
    base = new RotateImage(base, 90.0);
    if (this.right) {
      base = new OverlayImage(connection, base);
    }
    base = new RotateImage(base, 90.0);

    return base;
  }

  // TODO gradient color functionality
  public Color calcColor() {
    return Color.yellow;
  }

  // rotates the GamePiece
  public void rotatePiece(int dir) {
    boolean ogLeft = this.left;
    boolean ogRight = this.right;
    boolean ogTop = this.top;
    boolean ogBottom = this.bottom;
    if (dir == 1) { // rotate clockwise
      this.top = ogLeft;
      this.right = ogTop;
      this.bottom = ogRight;
      this.left = ogBottom;
    }
    else if (dir == 2) { // rotate counter
      this.top = ogRight;
      this.right = ogBottom;
      this.bottom = ogLeft;
      this.left = ogTop;
    }

    System.out.println(this.isConnected());
  }

  // determines if a cell is connected to other cells by checking the sides of
  // the neighbors cells
  boolean isConnected() {

    if (this.right && !(this.neighbors.get("right") != null && this.neighbors.get("right").left)) {
      return false;
    }

    if (this.bottom
        && !(this.neighbors.get("bottom") != null && this.neighbors.get("bottom").top)) {
      return false;
    }

    if (this.top && !(this.neighbors.get("top") != null && this.neighbors.get("top").bottom)) {
      return false;
    }

    if (this.left && !(this.neighbors.get("left") != null && this.neighbors.get("left").right)) {
      return false;
    }

    return true;

  }

  // adds this gamepiece to the neighbors
  void addNeighbor(String location, GamePiece neighbor) {
    this.neighbors.replace(location, neighbor);
  }

  // tests if the passed in piece is the same, mainly used for testing
  public Object samePiece(GamePiece that) {
    return this.row == that.row && this.col == that.col && this.left == that.left
        && this.right == that.right && this.top == that.top && this.bottom == that.bottom
        && this.powerStation == that.powerStation && this.powerLevel == that.powerLevel;
  }

}