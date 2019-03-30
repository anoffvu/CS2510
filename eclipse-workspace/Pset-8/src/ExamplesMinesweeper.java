import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javalib.worldimages.CircleImage;
import javalib.worldimages.EmptyImage;
import javalib.worldimages.EquilateralTriangleImage;
import javalib.worldimages.FrameImage;
import javalib.worldimages.OutlineMode;
import javalib.worldimages.OverlayImage;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.TextImage;
import tester.Tester;

// class for testing
class ExamplesMinesweeper {
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
  Cell zeroHidden;
  Cell zeroRevealed;
  Cell zeroFlaggedHidden;
  Cell zeroFlaggedRevealed;
  Cell zeroMineHidden;
  Cell zeroMineRevealed;
  Cell threeHidden;
  Cell threeRevealed;
  Cell threeFlaggedHidden;
  Cell threeFlaggedRevealed;
  Cell threeMineHidden;
  Cell threeMineRevealed;
  ArrayList<Cell> nineCells;
  Cell nineCellsCell;
  ArrayList<Cell> nineNonMines;
  MinesweeperGame game3;
  Cell nineNonMinesCell;

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
    this.zeroHidden = new Cell(false, false, false);
    this.zeroRevealed = new Cell(false, false, true);
    this.zeroFlaggedHidden = new Cell(false, true, false);
    this.zeroFlaggedRevealed = new Cell(false, true, true);
    this.zeroMineHidden = new Cell(true, false, false);
    this.zeroMineRevealed = new Cell(true, false, true);
    this.threeHidden = new Cell(false, false, false);
    threeHidden.addCellNeighbors(1, 1, this.grid1);
    this.threeRevealed = new Cell(false, false, true);
    threeRevealed.addCellNeighbors(1, 1, this.grid1);
    this.threeFlaggedHidden = new Cell(false, true, false);
    threeFlaggedHidden.addCellNeighbors(1, 1, this.grid1);
    this.threeFlaggedRevealed = new Cell(false, true, true);
    threeFlaggedRevealed.addCellNeighbors(1, 1, this.grid1);
    this.threeMineHidden = new Cell(true, false, false);
    threeMineHidden.addCellNeighbors(1, 1, this.grid1);
    this.threeMineRevealed = new Cell(true, false, true);
    threeMineRevealed.addCellNeighbors(1, 1, this.grid1);
    this.nineCells = new ArrayList<Cell>();
    this.nineCells.add(this.cell1);
    this.nineCells.add(this.cell2);
    this.nineCells.add(this.cell3);
    this.nineCells.add(this.cell4);
    this.nineCells.add(this.cell5);
    this.nineCells.add(this.cell6);
    this.nineCells.add(this.cell7);
    this.nineCells.add(this.cell8);
    this.nineCells.add(this.cell9);
    this.nineCellsCell = new Cell(false, false, false, this.nineCells);
    this.nineNonMines = new ArrayList<Cell>();
    this.nineNonMines.add(new Cell());
    this.nineNonMines.add(new Cell());
    this.nineNonMines.add(new Cell());
    this.nineNonMines.add(new Cell());
    this.nineNonMines.add(new Cell());
    this.nineNonMines.add(new Cell());
    this.nineNonMines.add(new Cell());
    this.nineNonMines.add(new Cell());
    this.nineNonMines.add(new Cell());
    this.nineNonMinesCell = new Cell(false, false, false, this.nineNonMines);
    this.game3 = new MinesweeperGame(3, 3, 4);
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
    // just adds the list of cells to neighbors so we can check mine count with existing
    // functions
    Cell dummy2 = new Cell(false, false, false, this.game2.generateAllCells());
    t.checkExpect(dummy2.countMines(), 80);
    t.checkExpect(this.game2.generateAllCells().size(), 100);

    Cell dummy3 = new Cell(false, false, false, this.game1.generateAllCells());
    t.checkExpect(dummy3.countMines(), 4);
    t.checkExpect(this.game1.generateAllCells().size(), 9);
  }

  // tests for drawCell
  public void testDrawCell(Tester t) {
    initData();
    t.checkExpect(zeroHidden.drawCell(),
        new FrameImage(new RectangleImage(MinesweeperGame.CELL_SIZE, MinesweeperGame.CELL_SIZE,
            OutlineMode.SOLID, Color.lightGray)));
    t.checkExpect(zeroMineHidden.drawCell(),
        new FrameImage(new RectangleImage(MinesweeperGame.CELL_SIZE, MinesweeperGame.CELL_SIZE,
            OutlineMode.SOLID, Color.lightGray)));
    t.checkExpect(zeroRevealed.drawCell(),
        new FrameImage(
            new OverlayImage(new EmptyImage(), new RectangleImage(MinesweeperGame.CELL_SIZE,
                MinesweeperGame.CELL_SIZE, OutlineMode.SOLID, Color.darkGray))));
    t.checkExpect(zeroFlaggedHidden.drawCell(), new FrameImage(new OverlayImage(
            new EquilateralTriangleImage(MinesweeperGame.CELL_SIZE / 2.5, OutlineMode.SOLID,
                Color.red),
            new RectangleImage(MinesweeperGame.CELL_SIZE, MinesweeperGame.CELL_SIZE,
                OutlineMode.SOLID, Color.lightGray))));
    t.checkExpect(zeroFlaggedRevealed.drawCell(),
        new FrameImage(
            new OverlayImage(new EmptyImage(), new RectangleImage(MinesweeperGame.CELL_SIZE,
                MinesweeperGame.CELL_SIZE, OutlineMode.SOLID, Color.darkGray))));
    t.checkExpect(zeroMineRevealed.drawCell(), new FrameImage(new OverlayImage(
            new CircleImage(MinesweeperGame.CELL_SIZE / 3, OutlineMode.SOLID, Color.black),
            new RectangleImage(MinesweeperGame.CELL_SIZE, MinesweeperGame.CELL_SIZE,
                OutlineMode.SOLID, Color.darkGray))));

    t.checkExpect(threeHidden.drawCell(),
        new FrameImage(new RectangleImage(MinesweeperGame.CELL_SIZE, MinesweeperGame.CELL_SIZE,
            OutlineMode.SOLID, Color.lightGray)));
    t.checkExpect(threeRevealed.drawCell(),
        new FrameImage(new OverlayImage(new TextImage("3", 12, new Color(95, 255, 253)),
            new RectangleImage(MinesweeperGame.CELL_SIZE, MinesweeperGame.CELL_SIZE,
                OutlineMode.SOLID, Color.darkGray))));
    t.checkExpect(threeFlaggedHidden.drawCell(), new FrameImage(new OverlayImage(
            new EquilateralTriangleImage(MinesweeperGame.CELL_SIZE / 2.5, OutlineMode.SOLID,
                Color.red),
            new RectangleImage(MinesweeperGame.CELL_SIZE, MinesweeperGame.CELL_SIZE,
                OutlineMode.SOLID, Color.lightGray))));
    t.checkExpect(threeFlaggedRevealed.drawCell(),
        new FrameImage(new OverlayImage(new TextImage("3", 12, new Color(95, 255, 253)),
            new RectangleImage(MinesweeperGame.CELL_SIZE, MinesweeperGame.CELL_SIZE,
                OutlineMode.SOLID, Color.darkGray))));
    t.checkExpect(threeMineHidden.drawCell(),
        new FrameImage(new RectangleImage(MinesweeperGame.CELL_SIZE, MinesweeperGame.CELL_SIZE,
            OutlineMode.SOLID, Color.lightGray)));
    t.checkExpect(threeMineRevealed.drawCell(),
        new FrameImage(new OverlayImage(new CircleImage(6, OutlineMode.SOLID, Color.black),
            new RectangleImage(MinesweeperGame.CELL_SIZE, MinesweeperGame.CELL_SIZE,
                OutlineMode.SOLID, Color.darkGray))));

  }

  // tests for placeCells
  public void testPlaceCells(Tester t) {
    initData();
    t.checkExpect(nineCells.size(), 9);
    t.checkExpect(game1.placeCells(nineCells).size(), 3);
    t.checkExpect(game1.placeCells(nineCells).get(0).size(), 3);
    t.checkExpect(game1.placeCells(nineCells).get(0).get(0), this.cell1);
    t.checkExpect(game1.placeCells(nineCells).get(0).get(1), this.cell2);
    t.checkExpect(game1.placeCells(nineCells).get(0).get(2), this.cell3);
    t.checkExpect(game1.placeCells(nineCells).get(1).get(0), this.cell4);
    t.checkExpect(game1.placeCells(nineCells).get(1).get(1), this.cell5);
    t.checkExpect(game1.placeCells(nineCells).get(1).get(2), this.cell6);
    t.checkExpect(game1.placeCells(nineCells).get(2).get(0), this.cell7);
    t.checkExpect(game1.placeCells(nineCells).get(2).get(1), this.cell8);
    t.checkExpect(game1.placeCells(nineCells).get(2).get(2), this.cell9);
  }

  // tests for buildBoard
  public void testBuildBoard(Tester t) {
    // checks size, that there are 4 mines, and that neighbors are fetched correctly
    initData();
    // constructing a game calls buildBoard() and puts it into the cells field
    t.checkExpect(this.game1.cells.size(), 3);
    t.checkExpect(this.game1.cells.get(0).size(), 3);
    t.checkExpect(this.game1.cells.get(0).get(0).isMine, true);
    t.checkExpect(this.game1.cells.get(0).get(1).isMine, true);
    t.checkExpect(this.game1.cells.get(0).get(2).isMine, false);
    t.checkExpect(this.game1.cells.get(1).get(0).isMine, true);
    t.checkExpect(this.game1.cells.get(1).get(1).isMine, true);
    t.checkExpect(this.game1.cells.get(1).get(2).isMine, false);
    t.checkExpect(this.game1.cells.get(2).get(0).isMine, false);
    t.checkExpect(this.game1.cells.get(2).get(1).isMine, false);
    t.checkExpect(this.game1.cells.get(2).get(2).isMine, false);
    t.checkExpect(this.game1.cells.get(0).get(0).neighbors.size(), 3);
    t.checkExpect(this.game1.cells.get(1).get(1).neighbors.size(), 8);
    t.checkExpect(this.game2.cells.size(), 10);
  }

  // tests for addMines
  public void testAddMines(Tester t) {
    initData();
    Cell nineNonMinesCell = new Cell(false, false, false, this.nineNonMines);
    t.checkExpect(nineNonMinesCell.countMines(), 0);
    this.game1.addMines(this.nineNonMines);
    Cell nineNonMinesCell2 = new Cell(false, false, false, this.nineNonMines);
    t.checkExpect(nineNonMinesCell2.countMines(), 4);
    ArrayList<Cell> oldMines = this.nineNonMines;
    initData();
    Cell nineNonMinesCell3 = new Cell(false, false, false, this.nineNonMines);
    t.checkExpect(nineNonMinesCell3.countMines(), 0);
    // randomly seeded game
    this.game3.addMines(this.nineNonMines);
    Cell nineNonMinesCell4 = new Cell(false, false, false, this.nineNonMines);
    t.checkExpect(nineNonMinesCell4.countMines(), 4);
    t.checkExpect(oldMines.equals(this.nineNonMines), false);
    initData();
  }

  // tests for drawMineCount
  void testDrawMineCount(Tester t) {
    initData();
    // no neighbors here
    t.checkExpect(this.cell1.drawMineCount(), new EmptyImage());
    // added all neighbors to this grid
    this.game1.addAllNeighbors(grid1);
    t.checkExpect(this.cell1.drawMineCount(), new TextImage("2", 12, new Color(63, 255, 254)));
    t.checkExpect(this.cell2.drawMineCount(), new TextImage("2", 12, new Color(63, 255, 254)));
    t.checkExpect(this.cell3.drawMineCount(), new TextImage("3", 12, new Color(95, 255, 253)));
    t.checkExpect(this.cell4.drawMineCount(), new TextImage("3", 12, new Color(95, 255, 253)));
    t.checkExpect(this.cell5.drawMineCount(), new TextImage("3", 12, new Color(95, 255, 253)));
    t.checkExpect(this.cell6.drawMineCount(), new TextImage("3", 12, new Color(95, 255, 253)));
    t.checkExpect(this.cell7.drawMineCount(), new TextImage("2", 12, new Color(63, 255, 254)));
    t.checkExpect(this.cell8.drawMineCount(), new TextImage("2", 12, new Color(63, 255, 254)));
    t.checkExpect(this.cell9.drawMineCount(), new TextImage("3", 12, new Color(95, 255, 253)));
    t.checkExpect(this.nineNonMinesCell.drawMineCount(), new EmptyImage());
    t.checkExpect(this.nineCellsCell.drawMineCount(),
        new TextImage("4", 12, new Color(127, 255, 252)));
  }

  // TODO tests for floodFill
  void testFloodFill(Tester t) {

  }

  // TODO tests for onMouseClicked
  void testOnMouseClicked(Tester t) {

  }

  // TODO tests for locateCell
  void testLocateCell(Tester t) {

  }

  // TODO tests for checkWin
  void testCheckWin(Tester t) {

  }

  // tests for big bang
  void testBigBang(Tester t) {
    initData();
    MinesweeperGame game = new MinesweeperGame(50, 20, 80);
    game.bigBang(game.colCount * MinesweeperGame.CELL_SIZE,
        game.rowCount * MinesweeperGame.CELL_SIZE);
  }

}
