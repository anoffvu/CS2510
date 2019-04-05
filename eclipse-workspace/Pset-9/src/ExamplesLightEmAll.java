import java.util.ArrayList;

import tester.Tester;

public class ExamplesLightEmAll {

  ArrayList<GamePiece> mt;
  GamePiece GamePiece1;
  GamePiece GamePiece2;
  GamePiece GamePiece3;
  GamePiece GamePiece4;
  GamePiece GamePiece5;
  GamePiece GamePiece6;
  GamePiece GamePiece7;
  GamePiece GamePiece8;
  GamePiece GamePiece9;
  ArrayList<GamePiece> row1;
  ArrayList<GamePiece> row2;
  ArrayList<GamePiece> row3;
  ArrayList<ArrayList<GamePiece>> grid1;
  ArrayList<GamePiece> grid1Nodes;
  LightEmAll game1;
  LightEmAll game2;

  public void initData() {
    this.mt = new ArrayList<GamePiece>();
    this.GamePiece1 = new GamePiece(0, 0, false, false, false, true);
    this.GamePiece2 = new GamePiece(0, 1, false, false, false, true);
    this.GamePiece3 = new GamePiece(0, 2, false, false, false, true);
    this.GamePiece4 = new GamePiece(1, 0, true, true, true, true);
    this.GamePiece5 = new GamePiece(1, 1, true, true, true, true, true);
    this.GamePiece6 = new GamePiece(1, 2, true, true, true, true);
    this.GamePiece7 = new GamePiece(2, 0, false, false, true, false);
    this.GamePiece8 = new GamePiece(2, 1, false, false, true, false);
    this.GamePiece9 = new GamePiece(2, 2, false, false, true, false);
    this.row1 = new ArrayList<GamePiece>();
    this.row1.add(GamePiece1);
    this.row1.add(GamePiece2);
    this.row1.add(GamePiece3);
    this.row2 = new ArrayList<GamePiece>();
    this.row2.add(GamePiece4);
    this.row2.add(GamePiece5);
    this.row2.add(GamePiece6);
    this.row3 = new ArrayList<GamePiece>();
    this.row3.add(GamePiece7);
    this.row3.add(GamePiece8);
    this.row3.add(GamePiece9);
    this.grid1 = new ArrayList<ArrayList<GamePiece>>();
    this.grid1.add(row1);
    this.grid1.add(row2);
    this.grid1.add(row3);
    this.grid1Nodes = new ArrayList<GamePiece>();
    this.grid1Nodes.add(this.GamePiece1);
    this.grid1Nodes.add(this.GamePiece2);
    this.grid1Nodes.add(this.GamePiece3);
    this.grid1Nodes.add(this.GamePiece4);
    this.grid1Nodes.add(this.GamePiece5);
    this.grid1Nodes.add(this.GamePiece6);
    this.grid1Nodes.add(this.GamePiece7);
    this.grid1Nodes.add(this.GamePiece8);
    this.grid1Nodes.add(this.GamePiece9);
    this.game1 = new LightEmAll(3, 3);
    this.game2 = new LightEmAll(10, 10);

  }

  // tests for generateAllNodes
  public void testgenerateAllNodes(Tester t) {
    initData();
    t.checkExpect(this.game1.generateAllNodes().size(), 9);
    t.checkExpect(this.game1.nodes.size(), 9);
    t.checkExpect(this.game1.nodes.get(4).powerStation, true);
    t.checkExpect(this.game1.nodes.get(1).left, false);
    t.checkExpect(this.game1.nodes.get(1).right, false);
    t.checkExpect(this.game1.nodes.get(1).top, false);
    t.checkExpect(this.game1.nodes.get(1).bottom, true);
    t.checkExpect(this.game1.nodes.get(4).left, true);
    t.checkExpect(this.game1.nodes.get(4).right, true);
    t.checkExpect(this.game1.nodes.get(4).top, true);
    t.checkExpect(this.game1.nodes.get(4).bottom, true);
    t.checkExpect(this.game1.nodes.get(7).left, false);
    t.checkExpect(this.game1.nodes.get(7).right, false);
    t.checkExpect(this.game1.nodes.get(7).top, true);
    t.checkExpect(this.game1.nodes.get(7).bottom, false);
    t.checkExpect(this.game2.generateAllNodes().size(), 100);
    t.checkExpect(this.game2.nodes.size(), 100);
    t.checkExpect(this.game2.nodes.get(50).powerStation, true);

  }

  // tests for placeNodes
  public void testPlaceNodes(Tester t) {
    initData();
    t.checkExpect(this.game1.placeNodes().size(), 3);
    t.checkExpect(this.game1.placeNodes().get(0).size(), 3);
    t.checkExpect(this.game1.placeNodes().get(1).size(), 3);
    t.checkExpect(this.game1.placeNodes().get(2).size(), 3);
    t.checkExpect(this.game1.placeNodes().get(0).get(0), this.GamePiece1);
    t.checkExpect(this.game1.placeNodes().get(0).get(1), this.GamePiece2);
    t.checkExpect(this.game1.placeNodes().get(0).get(2), this.GamePiece3);
    t.checkExpect(this.game1.placeNodes().get(1).get(0), this.GamePiece4);
    t.checkExpect(this.game1.placeNodes().get(1).get(1), this.GamePiece5);
    t.checkExpect(this.game1.placeNodes().get(1).get(2), this.GamePiece6);
    t.checkExpect(this.game1.placeNodes().get(2).get(0), this.GamePiece7);
    t.checkExpect(this.game1.placeNodes().get(2).get(1), this.GamePiece8);
    t.checkExpect(this.game1.placeNodes().get(2).get(2), this.GamePiece9);
  }

  /*
   * // tests for validCoordinates
   * public void testValidCoordinates(Tester t) {
   * initData();
   * t.checkExpect(this.GamePiece1.validCoordinates(3, 3, this.grid1), false);
   * t.checkExpect(this.GamePiece1.validCoordinates(2, 3, this.grid1), false);
   * t.checkExpect(this.GamePiece1.validCoordinates(2, 2, this.grid1), true);
   * t.checkExpect(this.GamePiece1.validCoordinates(0, 0, this.grid1), true);
   * }
   * 
   * // tests for constructing the grid
   * public void testGridConstruction(Tester t) {
   * initData();
   * t.checkExpect(this.grid1.get(0).get(0), this.GamePiece1);
   * t.checkExpect(this.grid1.get(0).get(1), this.GamePiece2);
   * t.checkExpect(this.grid1.get(0).get(2), this.GamePiece3);
   * t.checkExpect(this.grid1.get(1).get(0), this.GamePiece4);
   * t.checkExpect(this.grid1.get(1).get(1), this.GamePiece5);
   * t.checkExpect(this.grid1.get(1).get(2), this.GamePiece6);
   * t.checkExpect(this.grid1.get(2).get(0), this.GamePiece7);
   * t.checkExpect(this.grid1.get(2).get(1), this.GamePiece8);
   * t.checkExpect(this.grid1.get(2).get(2), this.GamePiece9);
   * }
   * 
   * // tests for testAddGamePieceNeighbors
   * public void testAddGamePieceNeighbors(Tester t) {
   * initData();
   * t.checkExpect(this.mt.contains(this.GamePiece1), false);
   * this.mt.add(GamePiece1);
   * t.checkExpect(this.mt.contains(this.GamePiece1), true);
   * t.checkExpect(this.GamePiece5.neighbors, new ArrayList<GamePiece>());
   * this.GamePiece5.addGamePieceNeighbors(1, 1, this.grid1);
   * t.checkExpect(this.GamePiece5.neighbors.get(0), this.GamePiece1);
   * t.checkExpect(this.GamePiece5.neighbors.get(1), this.GamePiece2);
   * t.checkExpect(this.GamePiece5.neighbors.get(2), this.GamePiece3);
   * t.checkExpect(this.GamePiece5.neighbors.get(3), this.GamePiece4);
   * t.checkExpect(this.GamePiece5.neighbors.get(4), this.GamePiece6);
   * t.checkExpect(this.GamePiece5.neighbors.get(5), this.GamePiece7);
   * t.checkExpect(this.GamePiece5.neighbors.get(6), this.GamePiece8);
   * t.checkExpect(this.GamePiece5.neighbors.get(7), this.GamePiece9);
   * t.checkExpect(this.GamePiece5.neighbors.size(), 8);
   * 
   * initData();
   * this.GamePiece1.addGamePieceNeighbors(0, 0, this.grid1);
   * t.checkExpect(this.GamePiece1.neighbors.get(0), this.GamePiece2);
   * t.checkExpect(this.GamePiece1.neighbors.get(1), this.GamePiece4);
   * t.checkExpect(this.GamePiece1.neighbors.get(2), this.GamePiece5);
   * 
   * initData();
   * this.GamePiece6.addGamePieceNeighbors(1, 2, this.grid1);
   * t.checkExpect(this.GamePiece6.neighbors.get(0), this.GamePiece2);
   * t.checkExpect(this.GamePiece6.neighbors.get(1), this.GamePiece3);
   * t.checkExpect(this.GamePiece6.neighbors.get(2), this.GamePiece5);
   * t.checkExpect(this.GamePiece6.neighbors.get(3), this.GamePiece8);
   * t.checkExpect(this.GamePiece6.neighbors.get(4), this.GamePiece9);
   * }
   * 
   * // tests for testAddGamePieceNeighbors
   * public void testAddAllNeighbors(Tester t) {
   * initData();
   * t.checkExpect(this.GamePiece5.neighbors, new ArrayList<GamePiece>());
   * t.checkExpect(this.GamePiece1.neighbors, new ArrayList<GamePiece>());
   * t.checkExpect(this.GamePiece6.neighbors, new ArrayList<GamePiece>());
   * this.game1.addAllNeighbors(grid1);
   * t.checkExpect(this.GamePiece5.neighbors.get(0), this.GamePiece1);
   * t.checkExpect(this.GamePiece5.neighbors.get(1), this.GamePiece2);
   * t.checkExpect(this.GamePiece5.neighbors.get(2), this.GamePiece3);
   * t.checkExpect(this.GamePiece5.neighbors.get(3), this.GamePiece4);
   * t.checkExpect(this.GamePiece5.neighbors.get(4), this.GamePiece6);
   * t.checkExpect(this.GamePiece5.neighbors.get(5), this.GamePiece7);
   * t.checkExpect(this.GamePiece5.neighbors.get(6), this.GamePiece8);
   * t.checkExpect(this.GamePiece5.neighbors.get(7), this.GamePiece9);
   * t.checkExpect(this.GamePiece5.neighbors.size(), 8);
   * t.checkExpect(this.GamePiece1.neighbors.get(0), this.GamePiece2);
   * t.checkExpect(this.GamePiece1.neighbors.get(1), this.GamePiece4);
   * t.checkExpect(this.GamePiece1.neighbors.get(2), this.GamePiece5);
   * t.checkExpect(this.GamePiece6.neighbors.get(0), this.GamePiece2);
   * t.checkExpect(this.GamePiece6.neighbors.get(1), this.GamePiece3);
   * t.checkExpect(this.GamePiece6.neighbors.get(2), this.GamePiece5);
   * t.checkExpect(this.GamePiece6.neighbors.get(3), this.GamePiece8);
   * t.checkExpect(this.GamePiece6.neighbors.get(4), this.GamePiece9);
   * t.checkExpect(this.GamePiece9.neighbors.get(0), this.GamePiece5);
   * t.checkExpect(this.GamePiece9.neighbors.get(1), this.GamePiece6);
   * t.checkExpect(this.GamePiece9.neighbors.get(2), this.GamePiece8);
   * }
   * 
   * // tests for countMines
   * public void testCountMines(Tester t) {
   * initData();
   * this.game1.addAllNeighbors(grid1);
   * t.checkExpect(this.GamePiece5.countMines(), 3);
   * t.checkExpect(this.GamePiece1.countMines(), 2);
   * t.checkExpect(this.GamePiece4.countMines(), 3);
   * 
   * }
   * 
   * // tests for drawGamePiece
   * public void testDrawGamePiece(Tester t) {
   * initData();
   * t.checkExpect(zeroHidden.drawGamePiece(),
   * new FrameImage(new RectangleImage(LightEmAll.GamePiece_SIZE, LightEmAll.GamePiece_SIZE,
   * OutlineMode.SOLID, Color.lightGray)));
   * t.checkExpect(zeroMineHidden.drawGamePiece(),
   * new FrameImage(new RectangleImage(LightEmAll.GamePiece_SIZE, LightEmAll.GamePiece_SIZE,
   * OutlineMode.SOLID, Color.lightGray)));
   * t.checkExpect(zeroRevealed.drawGamePiece(),
   * new FrameImage(
   * new OverlayImage(new EmptyImage(), new RectangleImage(LightEmAll.GamePiece_SIZE,
   * LightEmAll.GamePiece_SIZE, OutlineMode.SOLID, Color.darkGray))));
   * t.checkExpect(zeroFlaggedHidden.drawGamePiece(),
   * new FrameImage(new OverlayImage(
   * new EquilateralTriangleImage(LightEmAll.GamePiece_SIZE / 2.5, OutlineMode.SOLID,
   * Color.red),
   * new RectangleImage(LightEmAll.GamePiece_SIZE, LightEmAll.GamePiece_SIZE,
   * OutlineMode.SOLID, Color.lightGray))));
   * t.checkExpect(zeroFlaggedRevealed.drawGamePiece(),
   * new FrameImage(
   * new OverlayImage(new EmptyImage(), new RectangleImage(LightEmAll.GamePiece_SIZE,
   * LightEmAll.GamePiece_SIZE, OutlineMode.SOLID, Color.darkGray))));
   * t.checkExpect(zeroMineRevealed.drawGamePiece(),
   * new FrameImage(new OverlayImage(
   * new CircleImage(LightEmAll.GamePiece_SIZE / 3, OutlineMode.SOLID, Color.black),
   * new RectangleImage(LightEmAll.GamePiece_SIZE, LightEmAll.GamePiece_SIZE,
   * OutlineMode.SOLID, Color.darkGray))));
   * 
   * t.checkExpect(threeHidden.drawGamePiece(),
   * new FrameImage(new RectangleImage(LightEmAll.GamePiece_SIZE, LightEmAll.GamePiece_SIZE,
   * OutlineMode.SOLID, Color.lightGray)));
   * t.checkExpect(threeRevealed.drawGamePiece(),
   * new FrameImage(new OverlayImage(new TextImage("3", 12, new Color(95, 255, 253)),
   * new RectangleImage(LightEmAll.GamePiece_SIZE, LightEmAll.GamePiece_SIZE,
   * OutlineMode.SOLID, Color.darkGray))));
   * t.checkExpect(threeFlaggedHidden.drawGamePiece(),
   * new FrameImage(new OverlayImage(
   * new EquilateralTriangleImage(LightEmAll.GamePiece_SIZE / 2.5, OutlineMode.SOLID,
   * Color.red),
   * new RectangleImage(LightEmAll.GamePiece_SIZE, LightEmAll.GamePiece_SIZE,
   * OutlineMode.SOLID, Color.lightGray))));
   * t.checkExpect(threeFlaggedRevealed.drawGamePiece(),
   * new FrameImage(new OverlayImage(new TextImage("3", 12, new Color(95, 255, 253)),
   * new RectangleImage(LightEmAll.GamePiece_SIZE, LightEmAll.GamePiece_SIZE,
   * OutlineMode.SOLID, Color.darkGray))));
   * t.checkExpect(threeMineHidden.drawGamePiece(),
   * new FrameImage(new RectangleImage(LightEmAll.GamePiece_SIZE, LightEmAll.GamePiece_SIZE,
   * OutlineMode.SOLID, Color.lightGray)));
   * t.checkExpect(threeMineRevealed.drawGamePiece(),
   * new FrameImage(new OverlayImage(new CircleImage(6, OutlineMode.SOLID, Color.black),
   * new RectangleImage(LightEmAll.GamePiece_SIZE, LightEmAll.GamePiece_SIZE,
   * OutlineMode.SOLID, Color.darkGray))));
   * 
   * }
   * 
   * // tests for placeGamePieces
   * public void testPlaceGamePieces(Tester t) {
   * initData();
   * t.checkExpect(nineGamePieces.size(), 9);
   * t.checkExpect(game1.placeGamePieces(nineGamePieces).size(), 3);
   * t.checkExpect(game1.placeGamePieces(nineGamePieces).get(0).size(), 3);
   * t.checkExpect(game1.placeGamePieces(nineGamePieces).get(0).get(0), this.GamePiece1);
   * t.checkExpect(game1.placeGamePieces(nineGamePieces).get(0).get(1), this.GamePiece2);
   * t.checkExpect(game1.placeGamePieces(nineGamePieces).get(0).get(2), this.GamePiece3);
   * t.checkExpect(game1.placeGamePieces(nineGamePieces).get(1).get(0), this.GamePiece4);
   * t.checkExpect(game1.placeGamePieces(nineGamePieces).get(1).get(1), this.GamePiece5);
   * t.checkExpect(game1.placeGamePieces(nineGamePieces).get(1).get(2), this.GamePiece6);
   * t.checkExpect(game1.placeGamePieces(nineGamePieces).get(2).get(0), this.GamePiece7);
   * t.checkExpect(game1.placeGamePieces(nineGamePieces).get(2).get(1), this.GamePiece8);
   * t.checkExpect(game1.placeGamePieces(nineGamePieces).get(2).get(2), this.GamePiece9);
   * }
   * 
   * // tests for buildBoard
   * public void testBuildBoard(Tester t) {
   * // checks size, that there are 4 mines, and that neighbors are fetched
   * // correctly
   * initData();
   * // constructing a game calls buildBoard() and puts it into the GamePieces field
   * t.checkExpect(this.game1.GamePieces.size(), 3);
   * t.checkExpect(this.game1.GamePieces.get(0).size(), 3);
   * t.checkExpect(this.game1.GamePieces.get(0).get(0).isMine, true);
   * t.checkExpect(this.game1.GamePieces.get(0).get(1).isMine, true);
   * t.checkExpect(this.game1.GamePieces.get(0).get(2).isMine, false);
   * t.checkExpect(this.game1.GamePieces.get(1).get(0).isMine, true);
   * t.checkExpect(this.game1.GamePieces.get(1).get(1).isMine, true);
   * t.checkExpect(this.game1.GamePieces.get(1).get(2).isMine, false);
   * t.checkExpect(this.game1.GamePieces.get(2).get(0).isMine, false);
   * t.checkExpect(this.game1.GamePieces.get(2).get(1).isMine, false);
   * t.checkExpect(this.game1.GamePieces.get(2).get(2).isMine, false);
   * t.checkExpect(this.game1.GamePieces.get(0).get(0).neighbors.size(), 3);
   * t.checkExpect(this.game1.GamePieces.get(1).get(1).neighbors.size(), 8);
   * t.checkExpect(this.game2.GamePieces.size(), 10);
   * }
   * 
   * // tests for addMines
   * public void testAddMines(Tester t) {
   * initData();
   * GamePiece nineNonMinesGamePiece = new GamePiece(false, false, false,
   * this.nineNonMines);
   * t.checkExpect(nineNonMinesGamePiece.countMines(), 0);
   * this.game1.addMines(this.nineNonMines);
   * GamePiece nineNonMinesGamePiece2 = new GamePiece(false, false, false,
   * this.nineNonMines);
   * t.checkExpect(nineNonMinesGamePiece2.countMines(), 4);
   * ArrayList<GamePiece> oldMines = this.nineNonMines;
   * initData();
   * GamePiece nineNonMinesGamePiece3 = new GamePiece(false, false, false,
   * this.nineNonMines);
   * t.checkExpect(nineNonMinesGamePiece3.countMines(), 0);
   * // randomly seeded game
   * this.game3.addMines(this.nineNonMines);
   * GamePiece nineNonMinesGamePiece4 = new GamePiece(false, false, false,
   * this.nineNonMines);
   * t.checkExpect(nineNonMinesGamePiece4.countMines(), 4);
   * t.checkExpect(oldMines.equals(this.nineNonMines), false);
   * initData();
   * }
   * 
   * // tests for drawMineCount
   * void testDrawMineCount(Tester t) {
   * initData();
   * // no neighbors here
   * t.checkExpect(this.GamePiece1.drawMineCount(), new EmptyImage());
   * // added all neighbors to this grid
   * this.game1.addAllNeighbors(grid1);
   * t.checkExpect(this.GamePiece1.drawMineCount(), new TextImage("2", 12, new Color(63,
   * 255, 254)));
   * t.checkExpect(this.GamePiece2.drawMineCount(), new TextImage("2", 12, new Color(63,
   * 255, 254)));
   * t.checkExpect(this.GamePiece3.drawMineCount(), new TextImage("3", 12, new Color(95,
   * 255, 253)));
   * t.checkExpect(this.GamePiece4.drawMineCount(), new TextImage("3", 12, new Color(95,
   * 255, 253)));
   * t.checkExpect(this.GamePiece5.drawMineCount(), new TextImage("3", 12, new Color(95,
   * 255, 253)));
   * t.checkExpect(this.GamePiece6.drawMineCount(), new TextImage("3", 12, new Color(95,
   * 255, 253)));
   * t.checkExpect(this.GamePiece7.drawMineCount(), new TextImage("2", 12, new Color(63,
   * 255, 254)));
   * t.checkExpect(this.GamePiece8.drawMineCount(), new TextImage("2", 12, new Color(63,
   * 255, 254)));
   * t.checkExpect(this.GamePiece9.drawMineCount(), new TextImage("3", 12, new Color(95,
   * 255, 253)));
   * t.checkExpect(this.nineNonMinesGamePiece.drawMineCount(), new EmptyImage());
   * t.checkExpect(this.nineGamePiecesGamePiece.drawMineCount(),
   * new TextImage("4", 12, new Color(127, 255, 252)));
   * }
   * 
   * // tests for floodFill
   * void testFloodFill(Tester t) {
   * initData();
   * this.game1.addAllNeighbors(grid1);
   * t.checkExpect(this.GamePiece1.neighbors.get(0).isShown, false);
   * t.checkExpect(this.GamePiece1.neighbors.get(1).isShown, true);
   * t.checkExpect(this.GamePiece1.neighbors.get(2).isShown, false);
   * this.GamePiece1.floodFill();
   * t.checkExpect(this.GamePiece1.isShown, true);
   * t.checkExpect(this.GamePiece1.neighbors.get(0).isShown, true);
   * t.checkExpect(this.GamePiece1.neighbors.get(1).isShown, true);
   * t.checkExpect(this.GamePiece1.neighbors.get(2).isShown, true);
   * initData();
   * this.game1.addAllNeighbors(grid1);
   * t.checkExpect(this.GamePiece3.neighbors.get(0).isShown, false);
   * t.checkExpect(this.GamePiece3.neighbors.get(1).isShown, false);
   * t.checkExpect(this.GamePiece3.neighbors.get(2).isShown, true);
   * this.GamePiece3.floodFill();
   * t.checkExpect(this.GamePiece3.isShown, true);
   * t.checkExpect(this.GamePiece3.neighbors.get(0).isShown, true);
   * t.checkExpect(this.GamePiece3.neighbors.get(1).isShown, true);
   * t.checkExpect(this.GamePiece3.neighbors.get(2).isShown, true);
   * initData();
   * this.game1.addAllNeighbors(grid1);
   * t.checkExpect(this.GamePiece5.neighbors.get(0).isShown, false);
   * t.checkExpect(this.GamePiece5.neighbors.get(1).isShown, false);
   * t.checkExpect(this.GamePiece5.neighbors.get(2).isShown, false);
   * t.checkExpect(this.GamePiece5.neighbors.get(3).isShown, true);
   * this.GamePiece5.floodFill();
   * t.checkExpect(this.GamePiece5.isShown, true);
   * t.checkExpect(this.GamePiece5.neighbors.get(0).isShown, true);
   * t.checkExpect(this.GamePiece5.neighbors.get(1).isShown, true);
   * t.checkExpect(this.GamePiece5.neighbors.get(2).isShown, true);
   * t.checkExpect(this.GamePiece5.neighbors.get(3).isShown, true);
   * }
   * 
   * // tests for onMouseClicked
   * void testOnMouseClicked(Tester t) {
   * // left click a mine
   * initData();
   * this.game1.addAllNeighbors(grid1);
   * t.checkExpect(this.game1.GamePieces.get(0).get(0).isShown, false);
   * t.checkExpect(this.game1.GamePieces.get(0).get(0).isMine, true);
   * t.checkExpect(this.game1.GamePieces.get(0).get(0).isFlagged, false);
   * t.checkExpect(this.game1.theGameIsOver, false);
   * this.game1.onMouseClicked(new Posn(1, 1), "LeftButton");
   * t.checkExpect(this.game1.GamePieces.get(0).get(0).isShown, true);
   * t.checkExpect(this.game1.GamePieces.get(0).get(0).isMine, true);
   * t.checkExpect(this.game1.GamePieces.get(0).get(0).isFlagged, false);
   * t.checkExpect(this.game1.theGameIsOver, true);
   * // right click a mine
   * initData();
   * this.game1.addAllNeighbors(grid1);
   * t.checkExpect(this.game1.GamePieces.get(0).get(0).isShown, false);
   * t.checkExpect(this.game1.GamePieces.get(0).get(0).isMine, true);
   * t.checkExpect(this.game1.GamePieces.get(0).get(0).isFlagged, false);
   * t.checkExpect(this.game1.theGameIsOver, false);
   * this.game1.onMouseClicked(new Posn(1, 1), "RightButton");
   * t.checkExpect(this.game1.GamePieces.get(0).get(0).isShown, false);
   * t.checkExpect(this.game1.GamePieces.get(0).get(0).isMine, true);
   * t.checkExpect(this.game1.GamePieces.get(0).get(0).isFlagged, true);
   * t.checkExpect(this.game1.theGameIsOver, false);
   * 
   * // left click a nonMine
   * initData();
   * this.game1.addAllNeighbors(grid1);
   * t.checkExpect(this.game1.GamePieces.get(2).get(0).isShown, false);
   * t.checkExpect(this.game1.GamePieces.get(2).get(0).isMine, false);
   * t.checkExpect(this.game1.GamePieces.get(2).get(0).isFlagged, false);
   * t.checkExpect(this.game1.theGameIsOver, false);
   * this.game1.onMouseClicked(new Posn(40, 1), "LeftButton");
   * t.checkExpect(this.game1.GamePieces.get(2).get(0).isShown, true);
   * t.checkExpect(this.game1.GamePieces.get(2).get(0).isMine, false);
   * t.checkExpect(this.game1.GamePieces.get(2).get(0).isFlagged, false);
   * t.checkExpect(this.game1.theGameIsOver, false);
   * 
   * // right click a nonMine
   * initData();
   * this.game1.addAllNeighbors(grid1);
   * t.checkExpect(this.game1.GamePieces.get(2).get(0).isShown, false);
   * t.checkExpect(this.game1.GamePieces.get(2).get(0).isMine, false);
   * t.checkExpect(this.game1.GamePieces.get(2).get(0).isFlagged, false);
   * t.checkExpect(this.game1.theGameIsOver, false);
   * this.game1.onMouseClicked(new Posn(40, 1), "RightButton");
   * t.checkExpect(this.game1.GamePieces.get(2).get(0).isShown, false);
   * t.checkExpect(this.game1.GamePieces.get(2).get(0).isMine, false);
   * t.checkExpect(this.game1.GamePieces.get(2).get(0).isFlagged, true);
   * t.checkExpect(this.game1.theGameIsOver, false);
   * }
   * 
   * // tests for locateGamePiece
   * void testLocateGamePiece(Tester t) {
   * this.game1.addAllNeighbors(grid1);
   * t.checkExpect(this.tinyGame.locateGamePiece(new Posn(1, 1)), new GamePiece(true, false,
   * false));
   * t.checkExpect(this.game4.locateGamePiece(new Posn(0, 0)), new GamePiece(true, false,
   * false));
   * t.checkExpect(this.tinyGame.locateGamePiece(new Posn(10, 10)),
   * new GamePiece(true, false, false));
   * t.checkExpect(this.tinyGame.locateGamePiece(new Posn(5, 5)), new GamePiece(true, false,
   * false));
   * t.checkExpect(this.game5.locateGamePiece(new Posn(3, 3)), new GamePiece(true, false,
   * false));
   * t.checkExpect(this.game5.locateGamePiece(new Posn(0, 0)), new GamePiece(true, false,
   * false));
   * }
   * 
   * void testCheckWin(Tester t) {
   * // finding location of the mines
   * initData();
   * t.checkExpect(this.game1.GamePieces.get(0).get(0).isMine, true);
   * t.checkExpect(this.game1.GamePieces.get(0).get(1).isMine, true);
   * t.checkExpect(this.game1.GamePieces.get(0).get(2).isMine, false);
   * t.checkExpect(this.game1.GamePieces.get(1).get(0).isMine, true);
   * t.checkExpect(this.game1.GamePieces.get(1).get(1).isMine, true);
   * t.checkExpect(this.game1.GamePieces.get(1).get(2).isMine, false);
   * t.checkExpect(this.game1.GamePieces.get(2).get(0).isMine, false);
   * t.checkExpect(this.game1.GamePieces.get(2).get(1).isMine, false);
   * t.checkExpect(this.game1.GamePieces.get(2).get(2).isMine, false);
   * this.game1.checkWin();
   * t.checkExpect(this.game1.wonGame, false);
   * this.game1.GamePieces.get(0).get(2).isShown = true;
   * this.game1.checkWin();
   * t.checkExpect(this.game1.wonGame, false);
   * this.game1.GamePieces.get(1).get(2).isShown = true;
   * this.game1.checkWin();
   * t.checkExpect(this.game1.wonGame, false);
   * this.game1.GamePieces.get(2).get(0).isShown = true;
   * this.game1.checkWin();
   * t.checkExpect(this.game1.wonGame, false);
   * this.game1.GamePieces.get(2).get(1).isShown = true;
   * this.game1.checkWin();
   * t.checkExpect(this.game1.wonGame, false);
   * this.game1.GamePieces.get(2).get(2).isShown = true;
   * this.game1.checkWin();
   * t.checkExpect(this.game1.wonGame, true);
   * }
   * 
   * // tests for worldEnds
   * void testWorldEnds(Tester t) {
   * initData();
   * int middleX = (int) (this.game1.width * LightEmAll.GamePiece_SIZE) / 2;
   * int middleY = (int) (this.game1.height * LightEmAll.GamePiece_SIZE) / 2;
   * WorldScene winScene = this.game1.getEmptyScene();
   * winScene.placeImageXY(new TextImage("You Win!", (int) middleY / 2, Color.GREEN),
   * middleX,
   * middleY);
   * WorldScene loseScene = this.game1.getEmptyScene();
   * loseScene.placeImageXY(new TextImage("You Lose!", (int) middleY / 2, Color.GREEN),
   * middleX,
   * middleY);
   * t.checkExpect(this.game1.worldEnds(), new WorldEnd(false, this.game1.makeScene()));
   * this.game1.wonGame = true;
   * this.game1.theGameIsOver = true;
   * t.checkExpect(this.game1.worldEnds(), new WorldEnd(true, winScene));
   * initData();
   * t.checkExpect(this.game1.worldEnds(), new WorldEnd(false, this.game1.makeScene()));
   * this.game1.wonGame = false;
   * this.game1.theGameIsOver = true;
   * t.checkExpect(this.game1.worldEnds(), new WorldEnd(true, loseScene));
   * }
   * 
   * // can we get extra credit for doing 200 tests pretty please
   * 
   * // tests for big bang
   * void testBigBang(Tester t) {
   * initData();
   * LightEmAll game = new LightEmAll(50, 20, 80);
   * game.bigBang(game.width * LightEmAll.GamePiece_SIZE,
   * game.height * LightEmAll.GamePiece_SIZE, 0.25);
   * 
   * 
   * }
   */
}