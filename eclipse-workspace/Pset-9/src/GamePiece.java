import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import javalib.worldimages.OutlineMode;
import javalib.worldimages.OverlayImage;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.RotateImage;
import javalib.worldimages.StarImage;
import javalib.worldimages.WorldImage;

// a piece in the game
class GamePiece {
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

  // draws the GamePiece
  public WorldImage drawPiece() {
    WorldImage base = new RectangleImage(LightEmAll.CELL_SIZE, LightEmAll.CELL_SIZE,
        OutlineMode.SOLID, Color.darkGray);
    WorldImage connection = new RectangleImage((int) LightEmAll.CELL_SIZE / 6,
        (int) LightEmAll.CELL_SIZE / 2, OutlineMode.SOLID, calcColor()).movePinhole(0,
            (int) LightEmAll.CELL_SIZE / 4);
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
    if (this.powerStation) {
      base = new OverlayImage(
          new StarImage((LightEmAll.CELL_SIZE / 2.5), 8, 2, OutlineMode.SOLID, Color.ORANGE), base);
    }
    return base;
  }

  // gradient color functionality
  public Color calcColor() {
    if (powerLevel > 0) {
      return new Color(255, 255, 0, (255 / LightEmAll.radius * this.powerLevel));
    }
    else {
      return Color.GRAY;
    }
  }

  // rotates the GamePiece
  public void rotatePiece(int dir) {
    boolean ogLeft = this.left;
    boolean ogRight = this.right;
    boolean ogTop = this.top;
    boolean ogBottom = this.bottom;
    if (dir > 0) { // rotate clockwise
      this.top = ogLeft;
      this.right = ogTop;
      this.bottom = ogRight;
      this.left = ogBottom;
    }
    else if (dir < 0) { // rotate counter
      this.top = ogRight;
      this.right = ogBottom;
      this.bottom = ogLeft;
      this.left = ogTop;
    }
  }

  // adds this gamePiece to the neighbors
  void updateNeighbor(String location, GamePiece neighbor) {
    this.neighbors.replace(location, neighbor);
  }

  // tests if the passed in piece is the same, mainly used for testing
  public Object samePiece(GamePiece that) {
    return this.row == that.row && this.col == that.col && this.left == that.left
        && this.right == that.right && this.top == that.top && this.bottom == that.bottom
        && this.powerStation == that.powerStation && this.powerLevel == that.powerLevel;
  }

  // checks if this GamePiece is connected to the piece in the given direction
  public boolean isConnectedTo(String direction) {
    if (this.neighbors.get(direction) != null) {
      if (direction.equals("top")) {
        return this.neighbors.get(direction).bottom && this.top;
      }
      if (direction.equals("bottom")) {
        return this.neighbors.get(direction).top && this.bottom;
      }
      if (direction.equals("left")) {
        return this.neighbors.get(direction).right && this.left;
      }
      if (direction.equals("right")) {
        return this.neighbors.get(direction).left && this.right;
      }
    }
    return false;
  }

  // sends power thru the neighbors if possible
  public void powerNeighbors(ArrayList<GamePiece> seen) {
    seen.add(this);
    if (this.powerLevel > 0) {
      int neighborPowerLevel = this.powerLevel - 1;
      if (this.isConnectedTo("top") && !seen.contains(this.neighbors.get("top"))) {
          this.neighbors.get("top").powerLevel = neighborPowerLevel;
          this.neighbors.get("top").powerNeighbors(seen);
      }
      if (this.isConnectedTo("right") && !seen.contains(this.neighbors.get("right"))) {
          this.neighbors.get("right").powerLevel = neighborPowerLevel;
          this.neighbors.get("right").powerNeighbors(seen);
      }
      if (this.isConnectedTo("bottom") && !seen.contains(this.neighbors.get("bottom"))) {
          this.neighbors.get("bottom").powerLevel = neighborPowerLevel;
          this.neighbors.get("bottom").powerNeighbors(seen);
      }
      if (this.isConnectedTo("left") && !seen.contains(this.neighbors.get("left"))) {
          this.neighbors.get("left").powerLevel = neighborPowerLevel;
          this.neighbors.get("left").powerNeighbors(seen);
      }
    }
  }
}