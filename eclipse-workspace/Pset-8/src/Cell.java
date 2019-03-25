import java.awt.Color;
import java.util.ArrayList;

import javalib.worldimages.CircleImage;
import javalib.worldimages.EmptyImage;
import javalib.worldimages.EquilateralTriangleImage;
import javalib.worldimages.OutlineMode;
import javalib.worldimages.OverlayImage;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldImage;

public class Cell {
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

//convenience constructor that doesn't have neighbors
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

  // adds all the neighbors to this cell
  public void addCellNeighbors(int i, int j, ArrayList<ArrayList<Cell>> placedCells) {
    for (int y = (i - 1); y <= (i + 1); y++) {
      for (int z = (j - 1); z <= (j + 1); z++) {
        if (validCoordinates(y, z, placedCells) && !(i == y && j == z)) {
          this.neighbors.add(placedCells.get(y).get(z));
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
    WorldImage cellRevealed = new RectangleImage(18, 18, OutlineMode.SOLID, Color.darkGray);
    WorldImage cellCovered = new RectangleImage(18, 18, OutlineMode.SOLID, Color.lightGray);
    WorldImage flag = new EquilateralTriangleImage(10, OutlineMode.SOLID, Color.red);
    WorldImage mine = new CircleImage(6, OutlineMode.SOLID, Color.black);
    // nested logic here because if we kicked out to helpers it wouldn't add anything, would
    // actually
    // be more lines of code for these 1/2 liner helper functions
    if (this.isShown) { // if the cell is revealed
      if (this.isMine) { // if the cell is a revealed mine
        return new OverlayImage(mine, cellRevealed);
      }
      else { // if the cell is a revealed non mine
        return new OverlayImage(this.drawMineCount(), cellRevealed);
      }
    }
    else { // if the cell is hidden
      if (this.isFlagged) { // if the non revealed cell is flagged
        return new OverlayImage(flag, cellCovered);
      }
      else { // if the non revealed cell is not flagged
        return cellCovered;
      }
    }
  }

  // draws the mine count with a different color depending on how many mines there are
  private WorldImage drawMineCount() {
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
}