import java.util.ArrayList;

public class Cell {
  boolean isMine;
  boolean isFlagged;
  boolean isShown;
  ArrayList<Cell> neighbors;

  Cell(boolean isMine, boolean isFlagged, boolean isShown) {
    this.isMine = isMine;
    this.isFlagged = isFlagged;
    this.isShown = isShown;
  }

}
