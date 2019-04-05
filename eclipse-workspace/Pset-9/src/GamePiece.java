import java.awt.Color;

import javalib.worldimages.OutlineMode;
import javalib.worldimages.OverlayImage;
import javalib.worldimages.RectangleImage;
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

  GamePiece(int row, int col, boolean left, boolean right, boolean top, boolean bottom,
      boolean powerStation, int powerLevel) {
    this.row = row;
    this.col = col;
    this.left = left;
    this.right = right;
    this.top = top;
    this.bottom = bottom;
    this.powerStation = powerStation;
    this.powerLevel = powerLevel;
  }

  GamePiece(int row, int col, boolean left, boolean right, boolean top, boolean bottom,
      boolean powerStation) {
    this(row, col, left, right, top, bottom, powerStation, 0);
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
        (int) LightEmAll.CELL_SIZE / 2, OutlineMode.SOLID, Color.BLUE)
            .movePinhole((int) LightEmAll.CELL_SIZE / 2, (int) LightEmAll.CELL_SIZE / 2);
    // TODO draw the correct connections by rotating shit
    if (true) {
      base = new OverlayImage(connection, base);
    }
    return base;
  }

  // TODO gradient color functionality
  public Color calcColor() {
    return Color.yellow;
  }

  // rotates the GamePiece
  public void rotatePiece(int dir) {
    boolean pos1 = this.left;
    boolean pos2 = this.right;
    boolean pos3 = this.top;
    boolean pos4 = this.bottom;
    if (dir == 1) {
      this.left = pos2;
      this.right = pos3;
      this.top = pos4;
      this.bottom = pos1;
    }
    else if (dir == 2) {
      this.left = pos4;
      this.right = pos1;
      this.top = pos2;
      this.bottom = pos3;
    }

  }
}
