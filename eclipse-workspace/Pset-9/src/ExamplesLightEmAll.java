import java.util.ArrayList;

import tester.Tester;

public class ExamplesLightEmAll {

  ArrayList<GamePiece> mt;
  GamePiece gamePiece1;
  GamePiece gamePiece2;
  GamePiece gamePiece3;
  GamePiece gamePiece4;
  GamePiece gamePiece5;
  GamePiece gamePiece6;
  GamePiece gamePiece7;
  GamePiece gamePiece8;
  GamePiece gamePiece9;
  ArrayList<GamePiece> row1;
  ArrayList<GamePiece> row2;
  ArrayList<GamePiece> row3;
  ArrayList<ArrayList<GamePiece>> grid1;
  ArrayList<GamePiece> grid1Nodes;
  LightEmAll game1;
  LightEmAll game2;

  // making a 3 x 5 grid
  GamePiece gamePiece01;
  GamePiece gamePiece02;
  GamePiece gamePiece03;
  GamePiece gamePiece04;
  GamePiece gamePiece05;
  GamePiece gamePiece06;
  GamePiece gamePiece07;
  GamePiece gamePiece08;
  GamePiece gamePiece09;
  GamePiece gamePiece10;
  GamePiece gamePiece11;
  GamePiece gamePiece12;
  GamePiece gamePiece13;
  GamePiece gamePiece14;
  GamePiece gamePiece15;
  ArrayList<GamePiece> row01;
  ArrayList<GamePiece> row02;
  ArrayList<GamePiece> row03;
  ArrayList<ArrayList<GamePiece>> grid3;
  ArrayList<GamePiece> grid3Nodes;
  LightEmAll game3;

  public void initData() {
    this.mt = new ArrayList<GamePiece>();
    this.gamePiece1 = new GamePiece(0, 0, false, true, false, false);
    this.gamePiece2 = new GamePiece(1, 0, true, true, true, true);
    this.gamePiece3 = new GamePiece(2, 0, true, false, false, false);
    this.gamePiece4 = new GamePiece(0, 1, false, true, false, false);
    this.gamePiece5 = new GamePiece(1, 1, true, true, true, true, true);
    this.gamePiece6 = new GamePiece(2, 1, true, false, false, false);
    this.gamePiece7 = new GamePiece(0, 2, false, true, false, false);
    this.gamePiece8 = new GamePiece(1, 2, true, true, true, true);
    this.gamePiece9 = new GamePiece(2, 2, true, false, false, false);
    this.row1 = new ArrayList<GamePiece>();
    this.row1.add(gamePiece1);
    this.row1.add(gamePiece2);
    this.row1.add(gamePiece3);
    this.row2 = new ArrayList<GamePiece>();
    this.row2.add(gamePiece4);
    this.row2.add(gamePiece5);
    this.row2.add(gamePiece6);
    this.row3 = new ArrayList<GamePiece>();
    this.row3.add(gamePiece7);
    this.row3.add(gamePiece8);
    this.row3.add(gamePiece9);
    this.grid1 = new ArrayList<ArrayList<GamePiece>>();
    this.grid1.add(row1);
    this.grid1.add(row2);
    this.grid1.add(row3);
    this.grid1Nodes = new ArrayList<GamePiece>();
    this.grid1Nodes.add(this.gamePiece1);
    this.grid1Nodes.add(this.gamePiece2);
    this.grid1Nodes.add(this.gamePiece3);
    this.grid1Nodes.add(this.gamePiece4);
    this.grid1Nodes.add(this.gamePiece5);
    this.grid1Nodes.add(this.gamePiece6);
    this.grid1Nodes.add(this.gamePiece7);
    this.grid1Nodes.add(this.gamePiece8);
    this.grid1Nodes.add(this.gamePiece9);
    this.game1 = new LightEmAll(3, 3);
    this.game2 = new LightEmAll(10, 10);

    this.gamePiece01 = new GamePiece(0, 0, false, true, false, false);
    this.gamePiece02 = new GamePiece(1, 0, true, true, false, false);
    this.gamePiece03 = new GamePiece(2, 0, true, true, true, true);
    this.gamePiece04 = new GamePiece(3, 0, true, true, false, false);
    this.gamePiece05 = new GamePiece(4, 0, true, false, false, false);
    this.gamePiece06 = new GamePiece(0, 1, false, true, false, false);
    this.gamePiece07 = new GamePiece(1, 1, true, true, false, false);
    this.gamePiece08 = new GamePiece(2, 1, true, true, true, true, true);
    this.gamePiece09 = new GamePiece(3, 1, true, true, false, false);
    this.gamePiece10 = new GamePiece(4, 1, true, false, false, false);
    this.gamePiece11 = new GamePiece(0, 2, false, true, false, false);
    this.gamePiece12 = new GamePiece(1, 2, true, true, false, false);
    this.gamePiece13 = new GamePiece(2, 2, true, true, true, true);
    this.gamePiece14 = new GamePiece(3, 2, true, true, false, false);
    this.gamePiece15 = new GamePiece(4, 2, true, false, false, false);
    this.row01 = new ArrayList<GamePiece>();
    this.row01.add(gamePiece01);
    this.row01.add(gamePiece02);
    this.row01.add(gamePiece03);
    this.row01.add(gamePiece04);
    this.row01.add(gamePiece05);
    this.row02 = new ArrayList<GamePiece>();
    this.row02.add(gamePiece06);
    this.row02.add(gamePiece07);
    this.row02.add(gamePiece08);
    this.row02.add(gamePiece09);
    this.row02.add(gamePiece10);
    this.row03 = new ArrayList<GamePiece>();
    this.row03.add(gamePiece11);
    this.row03.add(gamePiece12);
    this.row03.add(gamePiece13);
    this.row03.add(gamePiece14);
    this.row03.add(gamePiece15);
    this.grid3 = new ArrayList<ArrayList<GamePiece>>();
    this.grid3.add(row01);
    this.grid3.add(row02);
    this.grid3.add(row03);
    this.grid3Nodes = new ArrayList<GamePiece>();
    this.grid3Nodes.add(this.gamePiece01);
    this.grid3Nodes.add(this.gamePiece02);
    this.grid3Nodes.add(this.gamePiece03);
    this.grid3Nodes.add(this.gamePiece04);
    this.grid3Nodes.add(this.gamePiece05);
    this.grid3Nodes.add(this.gamePiece06);
    this.grid3Nodes.add(this.gamePiece07);
    this.grid3Nodes.add(this.gamePiece08);
    this.grid3Nodes.add(this.gamePiece09);
    this.grid3Nodes.add(this.gamePiece10);
    this.grid3Nodes.add(this.gamePiece11);
    this.grid3Nodes.add(this.gamePiece12);
    this.grid3Nodes.add(this.gamePiece13);
    this.grid3Nodes.add(this.gamePiece14);
    this.grid3Nodes.add(this.gamePiece15);
    this.game3 = new LightEmAll(5, 3);

  }

  // tests for generateAllNodes
  public void testgenerateAllNodes(Tester t) {
    initData();
    t.checkExpect(this.game1.generateAllNodes().size(), 9);
    t.checkExpect(this.game1.nodes.size(), 9);
    t.checkExpect(this.game1.nodes.get(4).powerStation, true);
    t.checkExpect(this.game1.nodes.get(1).left, false);
    t.checkExpect(this.game1.nodes.get(1).right, true);
    t.checkExpect(this.game1.nodes.get(1).top, false);
    t.checkExpect(this.game1.nodes.get(1).bottom, false);
    t.checkExpect(this.game1.nodes.get(4).left, true);
    t.checkExpect(this.game1.nodes.get(4).right, true);
    t.checkExpect(this.game1.nodes.get(4).top, true);
    t.checkExpect(this.game1.nodes.get(4).bottom, true);
    t.checkExpect(this.game1.nodes.get(7).left, true);
    t.checkExpect(this.game1.nodes.get(7).right, false);
    t.checkExpect(this.game1.nodes.get(7).top, false);
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
    t.checkExpect(this.game1.placeNodes().get(0).get(0), this.gamePiece1);
    t.checkExpect(this.game1.placeNodes().get(1).get(0), this.gamePiece2);
    t.checkExpect(this.game1.placeNodes().get(2).get(0), this.gamePiece3);
    t.checkExpect(this.game1.placeNodes().get(0).get(1), this.gamePiece4);
    t.checkExpect(this.game1.placeNodes().get(1).get(1), this.gamePiece5);
    t.checkExpect(this.game1.placeNodes().get(2).get(1), this.gamePiece6);
    t.checkExpect(this.game1.placeNodes().get(0).get(2), this.gamePiece7);
    t.checkExpect(this.game1.placeNodes().get(1).get(2), this.gamePiece8);
    t.checkExpect(this.game1.placeNodes().get(2).get(2), this.gamePiece9);
    t.checkExpect(this.game3.board.get(0).get(0), this.gamePiece01);
    t.checkExpect(this.game3.board.get(4).get(0), this.gamePiece05);
    t.checkExpect(this.game3.board.get(4).get(2), this.gamePiece15);
  }

  // tests for addNeighbors
  public void testAddNeighbors(Tester t) {
    initData();
    t.checkExpect(this.gamePiece1.neighbors.get("top"), null);
    t.checkExpect(this.gamePiece1.neighbors.get("left"), null);
    t.checkExpect(this.gamePiece1.neighbors.get("right"), null);
    t.checkExpect(this.gamePiece1.neighbors.get("bottom"), null);

    t.checkExpect(this.gamePiece5.neighbors.get("top"), null);
    t.checkExpect(this.gamePiece5.neighbors.get("left"), null);
    t.checkExpect(this.gamePiece5.neighbors.get("right"), null);
    t.checkExpect(this.gamePiece5.neighbors.get("bottom"), null);

    this.game1.addNeighbors();

    t.checkExpect(this.game1.board.get(0).get(0).neighbors.get("right").samePiece(this.gamePiece2),
        true);
    t.checkExpect(this.game1.board.get(0).get(0).neighbors.get("left"), null);
    t.checkExpect(this.game1.board.get(0).get(0).neighbors.get("bottom").samePiece(this.gamePiece4),
        true);
    t.checkExpect(this.game1.board.get(0).get(0).neighbors.get("top"), null);

    t.checkExpect(this.game1.board.get(1).get(1).neighbors.get("top").samePiece(this.gamePiece2),
        true);
    t.checkExpect(this.game1.board.get(1).get(1).neighbors.get("right").samePiece(this.gamePiece6),
        true);
    t.checkExpect(this.game1.board.get(1).get(1).neighbors.get("bottom").samePiece(this.gamePiece8),
        true);
    t.checkExpect(this.game1.board.get(1).get(1).neighbors.get("left").samePiece(this.gamePiece4),
        true);

    t.checkExpect(this.gamePiece01.neighbors.get("top"), null);
    t.checkExpect(this.gamePiece01.neighbors.get("left"), null);
    t.checkExpect(this.gamePiece01.neighbors.get("right"), null);
    t.checkExpect(this.gamePiece01.neighbors.get("bottom"), null);

    this.game3.addNeighbors();

    t.checkExpect(this.game3.board.get(0).get(0).neighbors.get("top"), null);
    t.checkExpect(this.game3.board.get(0).get(0).neighbors.get("right").samePiece(this.gamePiece02),
        true);
    t.checkExpect(
        this.game3.board.get(0).get(0).neighbors.get("bottom").samePiece(this.gamePiece06), true);
    t.checkExpect(this.game3.board.get(0).get(0).neighbors.get("left"), null);

  }

  // TODO tests for drawPiece
  void testDrawPiece(Tester t) {

  }

  // TODO tests for calcColor
  void testCalcColor(Tester t) {

  }

  // TODO tests for rotatePiece
  void testRotatePiece(Tester t) {

  }

  // TODO tests for addNeighbor
  void testAddNeighbor(Tester t) {

  }

  // TODO tests for samePiece
  void testSamePiece(Tester t) {

  }

  // TODO tests for onMouseClicked
  void testOnMouseClicked(Tester t) {

  }

  // TODO tests for locatePiece
  void testLocatePiece(Tester t) {

  }

  // TODO tests for makeScene
  void testMakeScene(Tester t) {

  }

  public void testIsConnected(Tester t) {
    initData();

    t.checkExpect(this.gamePiece1.isConnected(), false);
    t.checkExpect(this.gamePiece5.isConnected(), false);
    t.checkExpect(this.gamePiece9.isConnected(), false);

    this.game1.addNeighbors();

    t.checkExpect(this.gamePiece1.isConnected(), true);
    t.checkExpect(this.gamePiece5.isConnected(), true);
    t.checkExpect(this.gamePiece9.isConnected(), true);
  }

  // tests for big bang
  void testBigBang(Tester t) {
    initData();
    LightEmAll game = new LightEmAll(19, 10);
    game.bigBang(game.width * LightEmAll.CELL_SIZE, game.height * LightEmAll.CELL_SIZE, 0.25);
  }
  /*
   * // tests for validCoordinates
   * public void testValidCoordinates(Tester t) {
   * initData();
   * t.checkExpect(this.gamePiece1.validCoordinates(3, 3, this.grid1), false);
   * t.checkExpect(this.gamePiece1.validCoordinates(2, 3, this.grid1), false);
   * t.checkExpect(this.gamePiece1.validCoordinates(2, 2, this.grid1), true);
   * t.checkExpect(this.gamePiece1.validCoordinates(0, 0, this.grid1), true);
   * }
   * 
   * // tests for constructing the grid
   * public void testGridConstruction(Tester t) {
   * initData();
   * t.checkExpect(this.grid1.get(0).get(0), this.gamePiece1);
   * t.checkExpect(this.grid1.get(0).get(1), this.gamePiece2);
   * t.checkExpect(this.grid1.get(0).get(2), this.gamePiece3);
   * t.checkExpect(this.grid1.get(1).get(0), this.gamePiece4);
   * t.checkExpect(this.grid1.get(1).get(1), this.gamePiece5);
   * t.checkExpect(this.grid1.get(1).get(2), this.gamePiece6);
   * t.checkExpect(this.grid1.get(2).get(0), this.gamePiece7);
   * t.checkExpect(this.grid1.get(2).get(1), this.gamePiece8);
   * t.checkExpect(this.grid1.get(2).get(2), this.gamePiece9);
   * }
   * 
   * // tests for testAddgamePieceNeighbors
   * public void testAddgamePieceNeighbors(Tester t) {
   * initData();
   * t.checkExpect(this.mt.contains(this.gamePiece1), false);
   * this.mt.add(gamePiece1);
   * t.checkExpect(this.mt.contains(this.gamePiece1), true);
   * t.checkExpect(this.gamePiece5.neighbors, new ArrayList<gamePiece>());
   * this.gamePiece5.addgamePieceNeighbors(1, 1, this.grid1);
   * t.checkExpect(this.gamePiece5.neighbors.get(0), this.gamePiece1);
   * t.checkExpect(this.gamePiece5.neighbors.get(1), this.gamePiece2);
   * t.checkExpect(this.gamePiece5.neighbors.get(2), this.gamePiece3);
   * t.checkExpect(this.gamePiece5.neighbors.get(3), this.gamePiece4);
   * t.checkExpect(this.gamePiece5.neighbors.get(4), this.gamePiece6);
   * t.checkExpect(this.gamePiece5.neighbors.get(5), this.gamePiece7);
   * t.checkExpect(this.gamePiece5.neighbors.get(6), this.gamePiece8);
   * t.checkExpect(this.gamePiece5.neighbors.get(7), this.gamePiece9);
   * t.checkExpect(this.gamePiece5.neighbors.size(), 8);
   * 
   * initData();
   * this.gamePiece1.addgamePieceNeighbors(0, 0, this.grid1);
   * t.checkExpect(this.gamePiece1.neighbors.get(0), this.gamePiece2);
   * t.checkExpect(this.gamePiece1.neighbors.get(1), this.gamePiece4);
   * t.checkExpect(this.gamePiece1.neighbors.get(2), this.gamePiece5);
   * 
   * initData();
   * this.gamePiece6.addgamePieceNeighbors(1, 2, this.grid1);
   * t.checkExpect(this.gamePiece6.neighbors.get(0), this.gamePiece2);
   * t.checkExpect(this.gamePiece6.neighbors.get(1), this.gamePiece3);
   * t.checkExpect(this.gamePiece6.neighbors.get(2), this.gamePiece5);
   * t.checkExpect(this.gamePiece6.neighbors.get(3), this.gamePiece8);
   * t.checkExpect(this.gamePiece6.neighbors.get(4), this.gamePiece9);
   * }
   * 
   * // tests for testAddgamePieceNeighbors
   * public void testAddAllNeighbors(Tester t) {
   * initData();
   * t.checkExpect(this.gamePiece5.neighbors, new ArrayList<gamePiece>());
   * t.checkExpect(this.gamePiece1.neighbors, new ArrayList<gamePiece>());
   * t.checkExpect(this.gamePiece6.neighbors, new ArrayList<gamePiece>());
   * this.game1.addAllNeighbors(grid1);
   * t.checkExpect(this.gamePiece5.neighbors.get(0), this.gamePiece1);
   * t.checkExpect(this.gamePiece5.neighbors.get(1), this.gamePiece2);
   * t.checkExpect(this.gamePiece5.neighbors.get(2), this.gamePiece3);
   * t.checkExpect(this.gamePiece5.neighbors.get(3), this.gamePiece4);
   * t.checkExpect(this.gamePiece5.neighbors.get(4), this.gamePiece6);
   * t.checkExpect(this.gamePiece5.neighbors.get(5), this.gamePiece7);
   * t.checkExpect(this.gamePiece5.neighbors.get(6), this.gamePiece8);
   * t.checkExpect(this.gamePiece5.neighbors.get(7), this.gamePiece9);
   * t.checkExpect(this.gamePiece5.neighbors.size(), 8);
   * t.checkExpect(this.gamePiece1.neighbors.get(0), this.gamePiece2);
   * t.checkExpect(this.gamePiece1.neighbors.get(1), this.gamePiece4);
   * t.checkExpect(this.gamePiece1.neighbors.get(2), this.gamePiece5);
   * t.checkExpect(this.gamePiece6.neighbors.get(0), this.gamePiece2);
   * t.checkExpect(this.gamePiece6.neighbors.get(1), this.gamePiece3);
   * t.checkExpect(this.gamePiece6.neighbors.get(2), this.gamePiece5);
   * t.checkExpect(this.gamePiece6.neighbors.get(3), this.gamePiece8);
   * t.checkExpect(this.gamePiece6.neighbors.get(4), this.gamePiece9);
   * t.checkExpect(this.gamePiece9.neighbors.get(0), this.gamePiece5);
   * t.checkExpect(this.gamePiece9.neighbors.get(1), this.gamePiece6);
   * t.checkExpect(this.gamePiece9.neighbors.get(2), this.gamePiece8);
   * }
   * 
   * // tests for countMines
   * public void testCountMines(Tester t) {
   * initData();
   * this.game1.addAllNeighbors(grid1);
   * t.checkExpect(this.gamePiece5.countMines(), 3);
   * t.checkExpect(this.gamePiece1.countMines(), 2);
   * t.checkExpect(this.gamePiece4.countMines(), 3);
   * 
   * }
   * 
   * // tests for drawgamePiece
   * public void testDrawGamePiece(Tester t) {
   * initData();
   * t.checkExpect(zeroHidden.drawGamePiece(),
   * new FrameImage(new RectangleImage(LightEmAll.gamePiece_SIZE, LightEmAll.gamePiece_SIZE,
   * OutlineMode.SOLID, Color.lightGray)));
   * t.checkExpect(zeroMineHidden.drawGamePiece(),
   * new FrameImage(new RectangleImage(LightEmAll.gamePiece_SIZE, LightEmAll.gamePiece_SIZE,
   * OutlineMode.SOLID, Color.lightGray)));
   * t.checkExpect(zeroRevealed.drawGamePiece(),
   * new FrameImage(
   * new OverlayImage(new EmptyImage(), new RectangleImage(LightEmAll.gamePiece_SIZE,
   * LightEmAll.gamePiece_SIZE, OutlineMode.SOLID, Color.darkGray))));
   * t.checkExpect(zeroFlaggedHidden.drawGamePiece(),
   * new FrameImage(new OverlayImage(
   * new EquilateralTriangleImage(LightEmAll.gamePiece_SIZE / 2.5, OutlineMode.SOLID,
   * Color.red),
   * new RectangleImage(LightEmAll.gamePiece_SIZE, LightEmAll.gamePiece_SIZE,
   * OutlineMode.SOLID, Color.lightGray))));
   * t.checkExpect(zeroFlaggedRevealed.drawGamePiece(),
   * new FrameImage(
   * new OverlayImage(new EmptyImage(), new RectangleImage(LightEmAll.gamePiece_SIZE,
   * LightEmAll.gamePiece_SIZE, OutlineMode.SOLID, Color.darkGray))));
   * t.checkExpect(zeroMineRevealed.drawGamePiece(),
   * new FrameImage(new OverlayImage(
   * new CircleImage(LightEmAll.gamePiece_SIZE / 3, OutlineMode.SOLID, Color.black),
   * new RectangleImage(LightEmAll.gamePiece_SIZE, LightEmAll.gamePiece_SIZE,
   * OutlineMode.SOLID, Color.darkGray))));
   * 
   * t.checkExpect(threeHidden.drawGamePiece(),
   * new FrameImage(new RectangleImage(LightEmAll.gamePiece_SIZE, LightEmAll.gamePiece_SIZE,
   * OutlineMode.SOLID, Color.lightGray)));
   * t.checkExpect(threeRevealed.drawGamePiece(),
   * new FrameImage(new OverlayImage(new TextImage("3", 12, new Color(95, 255, 253)),
   * new RectangleImage(LightEmAll.gamePiece_SIZE, LightEmAll.gamePiece_SIZE,
   * OutlineMode.SOLID, Color.darkGray))));
   * t.checkExpect(threeFlaggedHidden.drawGamePiece(),
   * new FrameImage(new OverlayImage(
   * new EquilateralTriangleImage(LightEmAll.gamePiece_SIZE / 2.5, OutlineMode.SOLID,
   * Color.red),
   * new RectangleImage(LightEmAll.gamePiece_SIZE, LightEmAll.gamePiece_SIZE,
   * OutlineMode.SOLID, Color.lightGray))));
   * t.checkExpect(threeFlaggedRevealed.drawGamePiece(),
   * new FrameImage(new OverlayImage(new TextImage("3", 12, new Color(95, 255, 253)),
   * new RectangleImage(LightEmAll.gamePiece_SIZE, LightEmAll.gamePiece_SIZE,
   * OutlineMode.SOLID, Color.darkGray))));
   * t.checkExpect(threeMineHidden.drawGamePiece(),
   * new FrameImage(new RectangleImage(LightEmAll.gamePiece_SIZE, LightEmAll.gamePiece_SIZE,
   * OutlineMode.SOLID, Color.lightGray)));
   * t.checkExpect(threeMineRevealed.drawGamePiece(),
   * new FrameImage(new OverlayImage(new CircleImage(6, OutlineMode.SOLID, Color.black),
   * new RectangleImage(LightEmAll.gamePiece_SIZE, LightEmAll.gamePiece_SIZE,
   * OutlineMode.SOLID, Color.darkGray))));
   * 
   * }
   * 
   * // tests for placegamePieces
   * public void testPlacegamePieces(Tester t) {
   * initData();
   * t.checkExpect(ninegamePieces.size(), 9);
   * t.checkExpect(game1.placegamePieces(ninegamePieces).size(), 3);
   * t.checkExpect(game1.placegamePieces(ninegamePieces).get(0).size(), 3);
   * t.checkExpect(game1.placegamePieces(ninegamePieces).get(0).get(0), this.gamePiece1);
   * t.checkExpect(game1.placegamePieces(ninegamePieces).get(0).get(1), this.gamePiece2);
   * t.checkExpect(game1.placegamePieces(ninegamePieces).get(0).get(2), this.gamePiece3);
   * t.checkExpect(game1.placegamePieces(ninegamePieces).get(1).get(0), this.gamePiece4);
   * t.checkExpect(game1.placegamePieces(ninegamePieces).get(1).get(1), this.gamePiece5);
   * t.checkExpect(game1.placegamePieces(ninegamePieces).get(1).get(2), this.gamePiece6);
   * t.checkExpect(game1.placegamePieces(ninegamePieces).get(2).get(0), this.gamePiece7);
   * t.checkExpect(game1.placegamePieces(ninegamePieces).get(2).get(1), this.gamePiece8);
   * t.checkExpect(game1.placegamePieces(ninegamePieces).get(2).get(2), this.gamePiece9);
   * }
   * 
   * // tests for buildBoard
   * public void testBuildBoard(Tester t) {
   * // checks size, that there are 4 mines, and that neighbors are fetched
   * // correctly
   * initData();
   * // constructing a game calls buildBoard() and puts it into the gamePieces field
   * t.checkExpect(this.game1.gamePieces.size(), 3);
   * t.checkExpect(this.game1.gamePieces.get(0).size(), 3);
   * t.checkExpect(this.game1.gamePieces.get(0).get(0).isMine, true);
   * t.checkExpect(this.game1.gamePieces.get(0).get(1).isMine, true);
   * t.checkExpect(this.game1.gamePieces.get(0).get(2).isMine, false);
   * t.checkExpect(this.game1.gamePieces.get(1).get(0).isMine, true);
   * t.checkExpect(this.game1.gamePieces.get(1).get(1).isMine, true);
   * t.checkExpect(this.game1.gamePieces.get(1).get(2).isMine, false);
   * t.checkExpect(this.game1.gamePieces.get(2).get(0).isMine, false);
   * t.checkExpect(this.game1.gamePieces.get(2).get(1).isMine, false);
   * t.checkExpect(this.game1.gamePieces.get(2).get(2).isMine, false);
   * t.checkExpect(this.game1.gamePieces.get(0).get(0).neighbors.size(), 3);
   * t.checkExpect(this.game1.gamePieces.get(1).get(1).neighbors.size(), 8);
   * t.checkExpect(this.game2.gamePieces.size(), 10);
   * }
   * 
   * // tests for addMines
   * public void testAddMines(Tester t) {
   * initData();
   * gamePiece nineNonMinesgamePiece = new GamePiece(false, false, false,
   * this.nineNonMines);
   * t.checkExpect(nineNonMinesgamePiece.countMines(), 0);
   * this.game1.addMines(this.nineNonMines);
   * gamePiece nineNonMinesgamePiece2 = new GamePiece(false, false, false,
   * this.nineNonMines);
   * t.checkExpect(nineNonMinesgamePiece2.countMines(), 4);
   * ArrayList<gamePiece> oldMines = this.nineNonMines;
   * initData();
   * gamePiece nineNonMinesgamePiece3 = new GamePiece(false, false, false,
   * this.nineNonMines);
   * t.checkExpect(nineNonMinesgamePiece3.countMines(), 0);
   * // randomly seeded game
   * this.game3.addMines(this.nineNonMines);
   * gamePiece nineNonMinesgamePiece4 = new GamePiece(false, false, false,
   * this.nineNonMines);
   * t.checkExpect(nineNonMinesgamePiece4.countMines(), 4);
   * t.checkExpect(oldMines.equals(this.nineNonMines), false);
   * initData();
   * }
   * 
   * // tests for drawMineCount
   * void testDrawMineCount(Tester t) {
   * initData();
   * // no neighbors here
   * t.checkExpect(this.gamePiece1.drawMineCount(), new EmptyImage());
   * // added all neighbors to this grid
   * this.game1.addAllNeighbors(grid1);
   * t.checkExpect(this.gamePiece1.drawMineCount(), new TextImage("2", 12, new Color(63,
   * 255, 254)));
   * t.checkExpect(this.gamePiece2.drawMineCount(), new TextImage("2", 12, new Color(63,
   * 255, 254)));
   * t.checkExpect(this.gamePiece3.drawMineCount(), new TextImage("3", 12, new Color(95,
   * 255, 253)));
   * t.checkExpect(this.gamePiece4.drawMineCount(), new TextImage("3", 12, new Color(95,
   * 255, 253)));
   * t.checkExpect(this.gamePiece5.drawMineCount(), new TextImage("3", 12, new Color(95,
   * 255, 253)));
   * t.checkExpect(this.gamePiece6.drawMineCount(), new TextImage("3", 12, new Color(95,
   * 255, 253)));
   * t.checkExpect(this.gamePiece7.drawMineCount(), new TextImage("2", 12, new Color(63,
   * 255, 254)));
   * t.checkExpect(this.gamePiece8.drawMineCount(), new TextImage("2", 12, new Color(63,
   * 255, 254)));
   * t.checkExpect(this.gamePiece9.drawMineCount(), new TextImage("3", 12, new Color(95,
   * 255, 253)));
   * t.checkExpect(this.nineNonMinesgamePiece.drawMineCount(), new EmptyImage());
   * t.checkExpect(this.ninegamePiecesgamePiece.drawMineCount(),
   * new TextImage("4", 12, new Color(127, 255, 252)));
   * }
   * 
   * // tests for floodFill
   * void testFloodFill(Tester t) {
   * initData();
   * this.game1.addAllNeighbors(grid1);
   * t.checkExpect(this.gamePiece1.neighbors.get(0).isShown, false);
   * t.checkExpect(this.gamePiece1.neighbors.get(1).isShown, true);
   * t.checkExpect(this.gamePiece1.neighbors.get(2).isShown, false);
   * this.gamePiece1.floodFill();
   * t.checkExpect(this.gamePiece1.isShown, true);
   * t.checkExpect(this.gamePiece1.neighbors.get(0).isShown, true);
   * t.checkExpect(this.gamePiece1.neighbors.get(1).isShown, true);
   * t.checkExpect(this.gamePiece1.neighbors.get(2).isShown, true);
   * initData();
   * this.game1.addAllNeighbors(grid1);
   * t.checkExpect(this.gamePiece3.neighbors.get(0).isShown, false);
   * t.checkExpect(this.gamePiece3.neighbors.get(1).isShown, false);
   * t.checkExpect(this.gamePiece3.neighbors.get(2).isShown, true);
   * this.gamePiece3.floodFill();
   * t.checkExpect(this.gamePiece3.isShown, true);
   * t.checkExpect(this.gamePiece3.neighbors.get(0).isShown, true);
   * t.checkExpect(this.gamePiece3.neighbors.get(1).isShown, true);
   * t.checkExpect(this.gamePiece3.neighbors.get(2).isShown, true);
   * initData();
   * this.game1.addAllNeighbors(grid1);
   * t.checkExpect(this.gamePiece5.neighbors.get(0).isShown, false);
   * t.checkExpect(this.gamePiece5.neighbors.get(1).isShown, false);
   * t.checkExpect(this.gamePiece5.neighbors.get(2).isShown, false);
   * t.checkExpect(this.gamePiece5.neighbors.get(3).isShown, true);
   * this.gamePiece5.floodFill();
   * t.checkExpect(this.gamePiece5.isShown, true);
   * t.checkExpect(this.gamePiece5.neighbors.get(0).isShown, true);
   * t.checkExpect(this.gamePiece5.neighbors.get(1).isShown, true);
   * t.checkExpect(this.gamePiece5.neighbors.get(2).isShown, true);
   * t.checkExpect(this.gamePiece5.neighbors.get(3).isShown, true);
   * }
   * 
   * // tests for onMouseClicked
   * void testOnMouseClicked(Tester t) {
   * // left click a mine
   * initData();
   * this.game1.addAllNeighbors(grid1);
   * t.checkExpect(this.game1.gamePieces.get(0).get(0).isShown, false);
   * t.checkExpect(this.game1.gamePieces.get(0).get(0).isMine, true);
   * t.checkExpect(this.game1.gamePieces.get(0).get(0).isFlagged, false);
   * t.checkExpect(this.game1.theGameIsOver, false);
   * this.game1.onMouseClicked(new Posn(1, 1), "LeftButton");
   * t.checkExpect(this.game1.gamePieces.get(0).get(0).isShown, true);
   * t.checkExpect(this.game1.gamePieces.get(0).get(0).isMine, true);
   * t.checkExpect(this.game1.gamePieces.get(0).get(0).isFlagged, false);
   * t.checkExpect(this.game1.theGameIsOver, true);
   * // right click a mine
   * initData();
   * this.game1.addAllNeighbors(grid1);
   * t.checkExpect(this.game1.gamePieces.get(0).get(0).isShown, false);
   * t.checkExpect(this.game1.gamePieces.get(0).get(0).isMine, true);
   * t.checkExpect(this.game1.gamePieces.get(0).get(0).isFlagged, false);
   * t.checkExpect(this.game1.theGameIsOver, false);
   * this.game1.onMouseClicked(new Posn(1, 1), "RightButton");
   * t.checkExpect(this.game1.gamePieces.get(0).get(0).isShown, false);
   * t.checkExpect(this.game1.gamePieces.get(0).get(0).isMine, true);
   * t.checkExpect(this.game1.gamePieces.get(0).get(0).isFlagged, true);
   * t.checkExpect(this.game1.theGameIsOver, false);
   * 
   * // left click a nonMine
   * initData();
   * this.game1.addAllNeighbors(grid1);
   * t.checkExpect(this.game1.gamePieces.get(2).get(0).isShown, false);
   * t.checkExpect(this.game1.gamePieces.get(2).get(0).isMine, false);
   * t.checkExpect(this.game1.gamePieces.get(2).get(0).isFlagged, false);
   * t.checkExpect(this.game1.theGameIsOver, false);
   * this.game1.onMouseClicked(new Posn(40, 1), "LeftButton");
   * t.checkExpect(this.game1.gamePieces.get(2).get(0).isShown, true);
   * t.checkExpect(this.game1.gamePieces.get(2).get(0).isMine, false);
   * t.checkExpect(this.game1.gamePieces.get(2).get(0).isFlagged, false);
   * t.checkExpect(this.game1.theGameIsOver, false);
   * 
   * // right click a nonMine
   * initData();
   * this.game1.addAllNeighbors(grid1);
   * t.checkExpect(this.game1.gamePieces.get(2).get(0).isShown, false);
   * t.checkExpect(this.game1.gamePieces.get(2).get(0).isMine, false);
   * t.checkExpect(this.game1.gamePieces.get(2).get(0).isFlagged, false);
   * t.checkExpect(this.game1.theGameIsOver, false);
   * this.game1.onMouseClicked(new Posn(40, 1), "RightButton");
   * t.checkExpect(this.game1.gamePieces.get(2).get(0).isShown, false);
   * t.checkExpect(this.game1.gamePieces.get(2).get(0).isMine, false);
   * t.checkExpect(this.game1.gamePieces.get(2).get(0).isFlagged, true);
   * t.checkExpect(this.game1.theGameIsOver, false);
   * }
   * 
   * // tests for locategamePiece
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
   * t.checkExpect(this.game1.gamePieces.get(0).get(0).isMine, true);
   * t.checkExpect(this.game1.gamePieces.get(0).get(1).isMine, true);
   * t.checkExpect(this.game1.gamePieces.get(0).get(2).isMine, false);
   * t.checkExpect(this.game1.gamePieces.get(1).get(0).isMine, true);
   * t.checkExpect(this.game1.gamePieces.get(1).get(1).isMine, true);
   * t.checkExpect(this.game1.gamePieces.get(1).get(2).isMine, false);
   * t.checkExpect(this.game1.gamePieces.get(2).get(0).isMine, false);
   * t.checkExpect(this.game1.gamePieces.get(2).get(1).isMine, false);
   * t.checkExpect(this.game1.gamePieces.get(2).get(2).isMine, false);
   * this.game1.checkWin();
   * t.checkExpect(this.game1.wonGame, false);
   * this.game1.gamePieces.get(0).get(2).isShown = true;
   * this.game1.checkWin();
   * t.checkExpect(this.game1.wonGame, false);
   * this.game1.gamePieces.get(1).get(2).isShown = true;
   * this.game1.checkWin();
   * t.checkExpect(this.game1.wonGame, false);
   * this.game1.gamePieces.get(2).get(0).isShown = true;
   * this.game1.checkWin();
   * t.checkExpect(this.game1.wonGame, false);
   * this.game1.gamePieces.get(2).get(1).isShown = true;
   * this.game1.checkWin();
   * t.checkExpect(this.game1.wonGame, false);
   * this.game1.gamePieces.get(2).get(2).isShown = true;
   * this.game1.checkWin();
   * t.checkExpect(this.game1.wonGame, true);
   * }
   * 
   * // tests for worldEnds
   * void testWorldEnds(Tester t) {
   * initData();
   * int middleX = (int) (this.game1.width * LightEmAll.gamePiece_SIZE) / 2;
   * int middleY = (int) (this.game1.height * LightEmAll.gamePiece_SIZE) / 2;
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
   * game.bigBang(game.width * LightEmAll.gamePiece_SIZE,
   * game.height * LightEmAll.gamePiece_SIZE, 0.25);
   * 
   * 
   * }
   */
}