import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javalib.impworld.World;
import javalib.impworld.WorldScene;
import javalib.worldimages.RectangleImage;

public class MinesweeperGame extends World {

  int rowCount;
  int colCount;
  int mineCount;
  Random rand;

  Color red = Color.red;
  RectangleImage test = null;

  MinesweeperGame(int rowCount, int colCount, int mineCount, Random rand) {
    this.rowCount = rowCount;
    this.colCount = colCount;
    this.mineCount = mineCount;
    this.rand = rand;
  }

  MinesweeperGame(int rowCount, int colCount, int mineCount) {
    this(rowCount, colCount, mineCount, new Random());
  }

  // this will build the game board
  public ArrayList<ArrayList<Cell>> buildBoard() {
    // makes all the cells, non-mines and mines
    ArrayList<Cell> allCells = generateAllCells();
    // puts the cells inside the grid
    ArrayList<ArrayList<Cell>> placedCells = placeCells(allCells);
    // adds neighbors to each cell
    addAllNeighbors(placedCells);
    return placedCells;
  }

  // will generate all the cells in one list, adding mines where necessary
  public ArrayList<Cell> generateAllCells() {
    int totalCells = this.rowCount * this.colCount;
    ArrayList<Cell> allCells = new ArrayList<Cell>();
    for (int i = 0; i < totalCells; i++) {
      allCells.add(new Cell());
    }
    addMines(allCells);
    return allCells;
  }

  // adds the mines in randomly
  public void addMines(ArrayList<Cell> cells) {
    // creates a list of indices of every cell in the that we can place mines
    ArrayList<Integer> safeIndices = new ArrayList<Integer>();
    for (int i = 0; i < cells.size(); i++) {
      safeIndices.add(i);
    }
    // creates a list of indices of where the mines should be placed
    ArrayList<Integer> mineIndices = new ArrayList<Integer>();
    for (int i = 0; i < this.mineCount; i++) {
      // picks a random index up to the size of the indices list
      int randomIndex = this.rand.nextInt(safeIndices.size());
      // adds a random number from "indices" to mineIndices
      mineIndices.add(safeIndices.get(randomIndex));
      // removes the added number from the list of possible indices to place mines
      safeIndices.remove(randomIndex);
    }

    // converts the cells at those indices to mines
    for (Integer i : mineIndices) {
      cells.get(i).isMine = true;
    }
  }

  // places the given list of cells into a grid with these dimensions
  public ArrayList<ArrayList<Cell>> placeCells(ArrayList<Cell> allCells) {
    ArrayList<ArrayList<Cell>> rows = new ArrayList<ArrayList<Cell>>(this.rowCount);
    for (int i = 0; i < this.rowCount; i++) {
      ArrayList<Cell> cols = new ArrayList<Cell>(this.colCount);
      for (int j = 0; j < this.colCount; j++) {
        cols.add(allCells.get(0));
        allCells.remove(0);
      }
    }
    return rows;
  }

  // adds neighbors to the cells of the grid of cells passed in
  public void addAllNeighbors(ArrayList<ArrayList<Cell>> placedCells) {
    for (int i = 0; i < this.rowCount; i++) {
      for (int j = 0; j < this.colCount; j++) {
        // TODO this shouldnt have access to row and column count but its ok for now
        placedCells.get(i).get(j).addCellNeighbors(i, j, placedCells);
      }
    }
  }

  @Override
  public WorldScene makeScene() {
    // TODO Auto-generated method stub
    return null;
  }

}
