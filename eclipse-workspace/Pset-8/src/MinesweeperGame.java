import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javalib.impworld.World;
import javalib.impworld.WorldScene;
import javalib.worldimages.RectangleImage;

public class MinesweeperGame extends World {

  Random defaultGameRand = new Random(10);

  Color red = Color.red;
  RectangleImage test = null;

  MinesweeperGame(int rows, int cols, int mineCount, Random rand) {
    buildBoard(rows, cols, mineCount, rand);
  }

  MinesweeperGame(int rows, int cols, int mineCount) {
    buildBoard(rows, cols, mineCount, defaultGameRand);
  }

  // this will build the game board
  public ArrayList<ArrayList<Cell>> buildBoard(int rowCount, int colCount, int mineCount,
      Random rand) {
    int totalCells = rowCount * colCount;
    // makes all the cells, non-mines and mines
    ArrayList<Cell> allCells = generateAllCells(totalCells, mineCount, rand);
    // puts the cells inside the grid
    ArrayList<ArrayList<Cell>> placedCells = placeCells(rowCount, colCount, allCells);
    // adds neighbors to each cell
    ArrayList<ArrayList<Cell>> cellsWithNeighbors = addAllNeighbors(placedCells);
    return cellsWithNeighbors;
  }

  // places the given list of cells into a grid with these dimensions
  private ArrayList<ArrayList<Cell>> placeCells(int rowCount, int colCount,
      ArrayList<Cell> allCells) {
    ArrayList<ArrayList<Cell>> rows = new ArrayList<ArrayList<Cell>>(rowCount);
    for (int i = 0; i < rowCount; i++) {
      ArrayList<Cell> cols = new ArrayList<Cell>(colCount);
      for (int j = 0; j < colCount; j++) {
        cols.add(allCells.get(0));
        allCells.remove(0);
      }
    }
    return rows;
  }

  // will generate all the cells in one list, adding mines where necessary
  private ArrayList<Cell> generateAllCells(int totalCells, int mineCount, Random rand) {
    ArrayList<Cell> allCells = new ArrayList<Cell>();
    for (int i = 0; i < totalCells; i++) {
      allCells.add(new Cell());
    }
    addMines(allCells, mineCount, rand);
    return allCells;
  }

  // adds the mines in randomly
  private void addMines(ArrayList<Cell> cells, int mineCount, Random rand) {
    // creates a list of indices of every cell in the that we can place mines
    ArrayList<Integer> indices = new ArrayList<Integer>();
    for (int i = 0; i < cells.size(); i++) {
      indices.add(i);
    }
    // creates a list of indices of where the mines should be placed
    ArrayList<Integer> mineIndices = new ArrayList<Integer>();
    for (int i = 0; i < mineCount; i++) {
      // picks a random index up to the size of the indices list
      int randomIndex = rand.nextInt(indices.size());
      // adds a random number from "indices" to mineIndices
      mineIndices.add(indices.get(randomIndex));
      // removes the added number from the list of possible indices to place mines
      indices.remove(randomIndex);
    }

    // assigns the cells at those indices to mines
    for (Integer i : mineIndices) {
      cells.get(i).isMine = true;
    }
  }

  // adds neighbors to the cells of the grid of cells passed in
  private ArrayList<ArrayList<Cell>> addAllNeighbors(ArrayList<ArrayList<Cell>> placedCells) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public WorldScene makeScene() {
    // TODO Auto-generated method stu
    return null;
  }

}
