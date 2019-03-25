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
    this(isMine, isFlagged, isShown, new ArrayList<Cell>());
  }

//convenience constructor that doesn't have neighbors
  Cell() {
    this(false, false, false, new ArrayList<Cell>());
  }

  // counts the number of neighbors that are mines
  // part 2 of assignment says we should make operating over neighbors modular?
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
        if (validCoordinates(y, z, placedCells)
            && !(i == y && j == z)) {
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
}