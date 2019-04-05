import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javalib.impworld.World;
import javalib.impworld.WorldScene;
import javalib.worldimages.Posn;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldEnd;

// game class, represents the world state
class MinesweeperGame extends World {

  int colCount;
  int rowCount;
  int mineCount;
  Random rand;
  ArrayList<ArrayList<Cell>> cells;
  boolean theGameIsOver;
  boolean wonGame;

  public static int CELL_SIZE = 20;

  // extra constructor for manually inputting game end and game win states
  MinesweeperGame(int colCount, int rowCount, int mineCount, Random rand, boolean theGameIsOver,
      boolean wonGame) {
    this.colCount = colCount;
    this.rowCount = rowCount;
    this.mineCount = mineCount;
    this.rand = rand;
    this.cells = this.buildBoard();
    this.theGameIsOver = theGameIsOver;
    this.wonGame = wonGame;

  }

  MinesweeperGame(int colCount, int rowCount, int mineCount, Random rand) {
    this(colCount, rowCount, mineCount, rand, false, false);

  }

  MinesweeperGame(int colCount, int rowCount, int mineCount) {
    this(colCount, rowCount, mineCount, new Random());
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
    int totalCells = this.colCount * this.rowCount;
    ArrayList<Cell> allCells = new ArrayList<Cell>();
    for (int i = 0; i < totalCells; i++) {
      allCells.add(new Cell());
    }
    addMines(allCells);
    return allCells;
  }

  // EFFECT: adds the mines in randomly
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
    int allCellsIndex = 0;
    ArrayList<ArrayList<Cell>> rows = new ArrayList<ArrayList<Cell>>();
    for (int x = 0; x < this.colCount; x++) {
      rows.add(new ArrayList<Cell>());
      // ArrayList<Cell> cols = new ArrayList<Cell>(this.rowCount);
      for (int y = 0; y < this.rowCount; y++) {
        rows.get(x).add(allCells.get(allCellsIndex));
        allCellsIndex++;
      }
    }
    return rows;
  }

  // EFFECT: adds neighbors to the cells of the grid of cells passed in
  public void addAllNeighbors(ArrayList<ArrayList<Cell>> placedCells) {
    for (int x = 0; x < this.colCount; x++) {
      for (int y = 0; y < this.rowCount; y++) {
        placedCells.get(x).get(y).addCellNeighbors(x, y, placedCells);
      }
    }
  }

  // big bang
  public WorldScene makeScene() {
    WorldScene scene = new WorldScene(this.colCount * CELL_SIZE, this.rowCount * CELL_SIZE);
    for (int x = 0; x < this.colCount; x++) {
      for (int y = 0; y < this.rowCount; y++) {
        scene.placeImageXY(
            this.cells.get(x).get(y).drawCell().movePinhole((-.5 * MinesweeperGame.CELL_SIZE),
                (-.5 * MinesweeperGame.CELL_SIZE)),
            (x * MinesweeperGame.CELL_SIZE), (y * MinesweeperGame.CELL_SIZE));
      }
    }
    return scene;
  }

  // handles the clicks for the world, interacts with the cells that the moust is over
  public void onMouseClicked(Posn mouse, String button) {
    Cell clicked = locateCell(mouse);
    if (button.equals("LeftButton")) { // left click
      if (clicked.countMines() == 0 && !clicked.isMine) { // if its a fillable cell
        clicked.floodFill();
      }
      else if (clicked.isMine) { // if its a mine, it'll end the game
        clicked.isShown = true;
        this.makeScene();
        this.theGameIsOver = true;
      }
      else { // if its a cell you don't want to floodfill, but still show
        clicked.isShown = true;
      }
    }

    if (button.equals("RightButton")) { // right click
      if (clicked.isFlagged) {
        clicked.isFlagged = false;
      }
      else {
        clicked.isFlagged = true;
      }
    }
    this.makeScene();
    this.checkWin();
  }

  // checks for a game win
  public void checkWin() {
    int nonMineCount = (this.colCount * this.rowCount) - this.mineCount;
    int revealedNonMines = 0;
    for (int x = 0; x < this.colCount; x++) {
      for (int y = 0; y < this.rowCount; y++) {
        if (this.cells.get(x).get(y).isShown) {
          revealedNonMines += 1;
        }
      }
    }
    if (revealedNonMines >= nonMineCount) {
      this.theGameIsOver = true;
      this.wonGame = true;
    }
  }

  // ends the world and checks win/loss
  public WorldEnd worldEnds() {
    int middleX = (int) (this.colCount * MinesweeperGame.CELL_SIZE) / 2;
    int middleY = (int) (this.rowCount * MinesweeperGame.CELL_SIZE) / 2;
    WorldScene end = this.getEmptyScene();
    if (this.theGameIsOver && this.wonGame) {
      end.placeImageXY(new TextImage("You Win!", (int) middleY / 2, Color.GREEN), middleX, middleY);
      return new WorldEnd(true, end);
    }
    else if (this.theGameIsOver && !this.wonGame) {
      end.placeImageXY(new TextImage("You Lose!", (int) middleY / 2, Color.RED), middleX, middleY);
      return new WorldEnd(true, end);
    }
    else {
      return new WorldEnd(false, this.makeScene());
    }
  }

  // finds the cell at the given posn
  public Cell locateCell(Posn mouse) {
    int colNum = (int) Math.floor(mouse.x / MinesweeperGame.CELL_SIZE);
    int rowNum = (int) Math.floor(mouse.y / MinesweeperGame.CELL_SIZE);
    return cells.get(colNum).get(rowNum);
  }

}
