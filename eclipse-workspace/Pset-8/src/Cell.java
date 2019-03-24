import java.util.ArrayList;

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
    this(isMine, isFlagged, isShown, null);
  }

//convenience constructor that doesn't have neighbors
  Cell() {
    this(false, false, false, null);
  }

  // adds a cell to the neighbor list of this cell
  public void addNeighbor(Cell c) {
    this.neighbors.add(c);
  }

}
