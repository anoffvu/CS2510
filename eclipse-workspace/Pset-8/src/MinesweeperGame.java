import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javalib.impworld.World;
import javalib.impworld.WorldScene;
import javalib.worldimages.RectangleImage;


public class MinesweeperGame extends World{

  Random defaultGameRand = new Random();

  Color red = Color.red;
  RectangleImage test = null;

  // this will build the game board
  public ArrayList<ArrayList<Cell>> buildBoard(int rowCount, int colCount, int mineCount,
      Random rand) {
    ArrayList<Cell> allCells = generateAllCells(rowCount, colCount, mineCount, rand);
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
  private ArrayList<Cell> generateAllCells(int rowCount, int colCount, int mineCount, Random rand) {
    ArrayList<Cell> allCells = new ArrayList<Cell>();
    int totalCells = rowCount * colCount;
    for (int i = 0; i < totalCells; i++) {
      allCells.add(new Cell(false, false, false));
    }
    addMines(allCells, mineCount, rand);
    return allCells;
  }

  // adds the mines in randomly
  private void addMines(ArrayList<Cell> cells, int mineCount, Random rand) {
    // creates a list of indicies that we can play with later
    ArrayList<Integer> indicies = new ArrayList<Integer>(cells.size());
    for (int i = 0; i < cells.size(); i++) {
      indicies.add(i);
    }
    // generates the indicies that mines should be placed
    for (int i = 0; i < mineCount; i++) {
      int randomIndex = rand.nextInt(indicies.size());
      cells.get(randomIndex).isMine = true;
      indicies.remove(randomIndex);
    }
  }

  @Override
  public WorldScene makeScene() {
    // TODO Auto-generated method stu
    return null;
  }

}
