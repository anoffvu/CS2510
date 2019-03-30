import java.awt.Color;
import java.util.ArrayList;

import javalib.worldimages.CircleImage;
import javalib.worldimages.EmptyImage;
import javalib.worldimages.EquilateralTriangleImage;
import javalib.worldimages.FrameImage;
import javalib.worldimages.OutlineMode;
import javalib.worldimages.OverlayImage;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldImage;

// a cell inside Minesweeper
class Cell {
  boolean isMine;
  boolean isFlagged;
  boolean isShown;
  ArrayList<Cell> neighbors;

  // default cell constructor
  Cell(boolean isMine, boolean isFlagged, boolean isShown, ArrayList<Cell> neighbors) {
    this.isMine = isMine;
    this.isFlagged = isFlagged;
    this.isShown = isShown;
    this.neighbors = neighbors;
  }

  // convenience constructor that doesn't have neighbors
  Cell(boolean isMine, boolean isFlagged, boolean isShown) {
    this(isMine, isFlagged, isShown, new ArrayList<Cell>());
  }

  // convenience constructor that doesn't have neighbors
  Cell() {
    this(false, false, false, new ArrayList<Cell>());
  }

  // counts the number of neighbors that are mines
  public int countMines() {
    int mineCount = 0;
    for (Cell c : this.neighbors) {
      if (c.isMine) {
        mineCount++;
      }
    }
    return mineCount;
  }

  // EFFECT: adds all the neighbors to this cell
  public void addCellNeighbors(int i, int j, ArrayList<ArrayList<Cell>> placedCells) {
    for (int x = (i - 1); x <= (i + 1); x++) {
      for (int y = (j - 1); y <= (j + 1); y++) {
        if (validCoordinates(x, y, placedCells) && !(i == x && j == y)) {
          this.neighbors.add(placedCells.get(x).get(y));
        }
      }
    }
  }

  // determines if these are valid index values within this board
  public boolean validCoordinates(int rowNum, int colNum, ArrayList<ArrayList<Cell>> placedCells) {
    return (0 <= rowNum && rowNum < placedCells.size())
        && (0 <= colNum && colNum < placedCells.get(0).size());
  }

  // draws the cell
  public WorldImage drawCell() {
    WorldImage cellRevealed = new RectangleImage(MinesweeperGame.CELL_SIZE,
        MinesweeperGame.CELL_SIZE, OutlineMode.SOLID, Color.darkGray);
    WorldImage cellCovered = new RectangleImage(MinesweeperGame.CELL_SIZE,
        MinesweeperGame.CELL_SIZE, OutlineMode.SOLID, Color.lightGray);
    WorldImage flag = new EquilateralTriangleImage(MinesweeperGame.CELL_SIZE / 2.5,
        OutlineMode.SOLID, Color.red);
    WorldImage mine = new CircleImage((int) (MinesweeperGame.CELL_SIZE / 3), OutlineMode.SOLID,
        Color.black);
    if (this.isShown) { // if the cell is revealed
      if (this.isMine) { // if the cell is a revealed mine
        return new FrameImage(new OverlayImage(mine, cellRevealed));
      }
      else { // if the cell is a revealed non mine
        return new FrameImage(new OverlayImage(this.drawMineCount(), cellRevealed));
      }
    }
    else { // if the cell is hidden
      if (this.isFlagged) { // if the non revealed cell is flagged
        return new FrameImage(new OverlayImage(flag, cellCovered));
      }
      else { // if the non revealed cell is not flagged
        return new FrameImage(cellCovered);
      }
    }
  }

  // draws the mine count with a different color depending on how many mines there are
  public WorldImage drawMineCount() {
    int evenDivisor = 0xFFFFFF / 8;
    int newCode = this.countMines() * evenDivisor;
    Color numColor = new Color(newCode);
    if (this.countMines() == 0) {
      return new EmptyImage();
    }
    else {
      return new TextImage(Integer.toString(this.countMines()), 12, numColor);
    }
  }

  // EFFECT: adds all the neighbors to this cell
  public void floodFill() {
    this.isShown = true;
    if (this.isMine) {
      // TODO: end game
    }
    for (Cell c : this.neighbors) {
      if (!c.isShown && (c.countMines() == 0)) {
        c.floodFill();
      }
      else if (!c.isShown) {
        c.isShown = true;
      }
    }
  }
}