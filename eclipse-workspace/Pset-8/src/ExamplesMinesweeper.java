import java.util.ArrayList;
import java.util.Random;

import tester.Tester;

public class ExamplesMinesweeper {
  ArrayList<Cell> mt;
  Cell cell1;
  Cell cell2;
  Cell cell3;
  Cell cell4;
  Cell cell5;
  Cell cell6;
  Cell cell7;
  Cell cell8;
  Cell cell9;
  ArrayList<Cell> row1;
  ArrayList<Cell> row2;
  ArrayList<Cell> row3;
  ArrayList<ArrayList<Cell>> grid1;
  MinesweeperGame game1;
  MinesweeperGame game2;
  Cell dummy;

  public void initData() {
    this.mt = new ArrayList<Cell>();
    this.cell1 = new Cell(false, false, false);
    this.cell2 = new Cell(true, false, false);
    this.cell3 = new Cell(false, true, false);
    this.cell4 = new Cell(false, false, true);
    this.cell5 = new Cell(true, true, false);
    this.cell6 = new Cell(true, false, true);
    this.cell7 = new Cell(false, true, true);
    this.cell8 = new Cell(true, true, true);
    this.cell9 = new Cell(false, false, false);
    this.row1 = new ArrayList<Cell>();
    this.row1.add(cell1);
    this.row1.add(cell2);
    this.row1.add(cell3);
    this.row2 = new ArrayList<Cell>();
    this.row2.add(cell4);
    this.row2.add(cell5);
    this.row2.add(cell6);
    this.row3 = new ArrayList<Cell>();
    this.row3.add(cell7);
    this.row3.add(cell8);
    this.row3.add(cell9);
    this.grid1 = new ArrayList<ArrayList<Cell>>();
    this.grid1.add(row1);
    this.grid1.add(row2);
    this.grid1.add(row3);
    this.game1 = new MinesweeperGame(3, 3, 4, new Random(10));
    this.game2 = new MinesweeperGame(10, 10, 80, new Random(10));
    this.dummy = new Cell();

  }

  // tests for validCoordinates
  public void testValidCoordinates(Tester t) {
    initData();
    t.checkExpect(this.cell1.validCoordinates(3, 3, this.grid1), false);
    t.checkExpect(this.cell1.validCoordinates(2, 3, this.grid1), false);
    t.checkExpect(this.cell1.validCoordinates(2, 2, this.grid1), true);
    t.checkExpect(this.cell1.validCoordinates(0, 0, this.grid1), true);
  }

  // tests for constructing the grid
  public void testGridConstruction(Tester t) {
    initData();
    t.checkExpect(this.grid1.get(0).get(0), this.cell1);
    t.checkExpect(this.grid1.get(0).get(1), this.cell2);
    t.checkExpect(this.grid1.get(0).get(2), this.cell3);
    t.checkExpect(this.grid1.get(1).get(0), this.cell4);
    t.checkExpect(this.grid1.get(1).get(1), this.cell5);
    t.checkExpect(this.grid1.get(1).get(2), this.cell6);
    t.checkExpect(this.grid1.get(2).get(0), this.cell7);
    t.checkExpect(this.grid1.get(2).get(1), this.cell8);
    t.checkExpect(this.grid1.get(2).get(2), this.cell9);
  }

  // tests for testAddCellNeighbors
  public void testAddCellNeighbors(Tester t) {
    initData();
    t.checkExpect(this.mt.contains(this.cell1), false);
    this.mt.add(cell1);
    t.checkExpect(this.mt.contains(this.cell1), true);
    t.checkExpect(this.cell5.neighbors, new ArrayList<Cell>());
    this.cell5.addCellNeighbors(1, 1, this.grid1);
    t.checkExpect(this.cell5.neighbors.get(0), this.cell1);
    t.checkExpect(this.cell5.neighbors.get(1), this.cell2);
    t.checkExpect(this.cell5.neighbors.get(2), this.cell3);
    t.checkExpect(this.cell5.neighbors.get(3), this.cell4);
    t.checkExpect(this.cell5.neighbors.get(4), this.cell6);
    t.checkExpect(this.cell5.neighbors.get(5), this.cell7);
    t.checkExpect(this.cell5.neighbors.get(6), this.cell8);
    t.checkExpect(this.cell5.neighbors.get(7), this.cell9);
    t.checkExpect(this.cell5.neighbors.size(), 8);

    initData();
    this.cell1.addCellNeighbors(0, 0, this.grid1);
    t.checkExpect(this.cell1.neighbors.get(0), this.cell2);
    t.checkExpect(this.cell1.neighbors.get(1), this.cell4);
    t.checkExpect(this.cell1.neighbors.get(2), this.cell5);

    initData();
    this.cell6.addCellNeighbors(1, 2, this.grid1);
    t.checkExpect(this.cell6.neighbors.get(0), this.cell2);
    t.checkExpect(this.cell6.neighbors.get(1), this.cell3);
    t.checkExpect(this.cell6.neighbors.get(2), this.cell5);
    t.checkExpect(this.cell6.neighbors.get(3), this.cell8);
    t.checkExpect(this.cell6.neighbors.get(4), this.cell9);
  }

  // tests for testAddCellNeighbors
  public void testAddAllNeighbors(Tester t) {
    initData();
    t.checkExpect(this.cell5.neighbors, new ArrayList<Cell>());
    t.checkExpect(this.cell1.neighbors, new ArrayList<Cell>());
    t.checkExpect(this.cell6.neighbors, new ArrayList<Cell>());
    this.game1.addAllNeighbors(grid1);
    t.checkExpect(this.cell5.neighbors.get(0), this.cell1);
    t.checkExpect(this.cell5.neighbors.get(1), this.cell2);
    t.checkExpect(this.cell5.neighbors.get(2), this.cell3);
    t.checkExpect(this.cell5.neighbors.get(3), this.cell4);
    t.checkExpect(this.cell5.neighbors.get(4), this.cell6);
    t.checkExpect(this.cell5.neighbors.get(5), this.cell7);
    t.checkExpect(this.cell5.neighbors.get(6), this.cell8);
    t.checkExpect(this.cell5.neighbors.get(7), this.cell9);
    t.checkExpect(this.cell5.neighbors.size(), 8);
    t.checkExpect(this.cell1.neighbors.get(0), this.cell2);
    t.checkExpect(this.cell1.neighbors.get(1), this.cell4);
    t.checkExpect(this.cell1.neighbors.get(2), this.cell5);
    t.checkExpect(this.cell6.neighbors.get(0), this.cell2);
    t.checkExpect(this.cell6.neighbors.get(1), this.cell3);
    t.checkExpect(this.cell6.neighbors.get(2), this.cell5);
    t.checkExpect(this.cell6.neighbors.get(3), this.cell8);
    t.checkExpect(this.cell6.neighbors.get(4), this.cell9);
    t.checkExpect(this.cell9.neighbors.get(0), this.cell5);
    t.checkExpect(this.cell9.neighbors.get(1), this.cell6);
    t.checkExpect(this.cell9.neighbors.get(2), this.cell8);
  }
  
  // tests for countMines
  public void testCountMines(Tester t) {
    initData();
    this.game1.addAllNeighbors(grid1);
    t.checkExpect(this.cell5.countMines(), 3);
    t.checkExpect(this.cell1.countMines(), 2);
    t.checkExpect(this.cell4.countMines(), 3);

  }

  // tests for generateAllCells
  public void testGenerateAllCells(Tester t) {
    initData();
    t.checkExpect(this.game1.generateAllCells().size(), 9);
    t.checkExpect(this.game2.generateAllCells().size(), 100);
    initData();
    Cell dummy2 = new Cell(false, false, false, this.game2.generateAllCells());
    t.checkExpect(dummy2.countMines(), 80);
    Cell dummy3 = new Cell(false, false, false, this.game1.generateAllCells());
    t.checkExpect(dummy3.countMines(), 4);

  }

  /*
   * // TODO tests for buildBoard
   * public void testBuildBoard(Tester t) {
   * t.checkExpect(actual, expected);
   * }
   * 
   * // TODO tests for addMines
   * public void testAddMines(Tester t) {
   * t.checkExpect(actual, expected);
   * }
   * 
   * // TODO tests for placeCells
   * public void testPlaceCells(Tester t) {
   * t.checkExpect(actual, expected);
   * }
   */
}
