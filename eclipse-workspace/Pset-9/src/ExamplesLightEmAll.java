import java.util.ArrayList;

import javalib.worldimages.Posn;
import tester.Tester;

// examples class
class ExamplesLightEmAll {

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
    this.game1 = new LightEmAll(3, 3, -1);
    this.game2 = new LightEmAll(10, 10, -1);

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

  // TODO tests for drawPiece
  void testDrawPiece(Tester t) {
    initData();
    // t.checkExpect(this.gamePiece01.drawPiece(), null);
  }

  // TODO tests for calcColor
  void testCalcColor(Tester t) {
    initData();
    //
  }

  // tests for updateNeighobor
  void testUpdateNeighbor(Tester t) {
    initData();
    t.checkExpect(this.game1.nodes.get(0).neighbors.get("right"), null);
    t.checkExpect(this.game1.nodes.get(0).neighbors.get("top"), null);
    t.checkExpect(this.game1.nodes.get(0).neighbors.get("bottom"), null);
    t.checkExpect(this.game1.nodes.get(0).neighbors.get("left"), null);
    this.game1.nodes.get(0).updateNeighbor("right", this.gamePiece01);
    t.checkExpect(this.game1.nodes.get(0).neighbors.get("right"), this.gamePiece01);
    t.checkExpect(this.game1.nodes.get(0).neighbors.get("top"), null);
    t.checkExpect(this.game1.nodes.get(0).neighbors.get("bottom"), null);
    t.checkExpect(this.game1.nodes.get(0).neighbors.get("left"), null);
    this.game1.nodes.get(0).updateNeighbor("bottom", this.gamePiece02);
    t.checkExpect(this.game1.nodes.get(0).neighbors.get("right"), this.gamePiece01);
    t.checkExpect(this.game1.nodes.get(0).neighbors.get("top"), null);
    t.checkExpect(this.game1.nodes.get(0).neighbors.get("bottom"), this.gamePiece02);
    t.checkExpect(this.game1.nodes.get(0).neighbors.get("left"), null);
  }

  // TODO tests for samePiece
  // redo thse test
  void testSamePiece(Tester t) {
    initData();
    t.checkExpect(this.gamePiece01.samePiece(this.gamePiece01), true);
    t.checkExpect(this.gamePiece01.samePiece(this.gamePiece02), false);
    t.checkExpect(this.gamePiece9.samePiece(this.gamePiece9), true);
    t.checkExpect(this.gamePiece9.samePiece(this.gamePiece10), false);
  }

  // TODO tests for isConnectedTo
  void testIsConnecteTo(Tester t) {

  }

  // tests for rotatePiece
  // TODO test added functionality
  void testRotatePiece(Tester t) {
    initData();
    t.checkExpect(this.gamePiece1.left, false);
    t.checkExpect(this.gamePiece1.top, false);
    t.checkExpect(this.gamePiece1.right, true);
    t.checkExpect(this.gamePiece1.bottom, false);
    this.gamePiece1.rotatePiece(1);
    t.checkExpect(this.gamePiece1.left, false);
    t.checkExpect(this.gamePiece1.top, false);
    t.checkExpect(this.gamePiece1.right, false);
    t.checkExpect(this.gamePiece1.bottom, true);
    initData();
    t.checkExpect(this.gamePiece1.left, false);
    t.checkExpect(this.gamePiece1.top, false);
    t.checkExpect(this.gamePiece1.right, true);
    t.checkExpect(this.gamePiece1.bottom, false);
    this.gamePiece1.rotatePiece(-1);
    t.checkExpect(this.gamePiece1.left, false);
    t.checkExpect(this.gamePiece1.top, true);
    t.checkExpect(this.gamePiece1.right, false);
    t.checkExpect(this.gamePiece1.bottom, false);
    initData();
    t.checkExpect(this.gamePiece5.left, true);
    t.checkExpect(this.gamePiece5.top, true);
    t.checkExpect(this.gamePiece5.right, true);
    t.checkExpect(this.gamePiece5.bottom, true);
    this.gamePiece5.rotatePiece(1);
    t.checkExpect(this.gamePiece5.left, true);
    t.checkExpect(this.gamePiece5.top, true);
    t.checkExpect(this.gamePiece5.right, true);
    t.checkExpect(this.gamePiece5.bottom, true);
  }

  // Todo tests for powerNeighbors
  void testPowerNeighbors(Tester t) {
    initData();
  }

  // TODO tests for grabAllNodes
  public void testGrabAllNodes(Tester t) {
    initData();
    t.checkExpect(this.game1.grabAllNodes().size(), 9);
    t.checkExpect(this.game1.nodes.size(), 9);
    t.checkExpect(this.game2.nodes.size(), 100);

  }

  // TODO tests for generateManualConnections
  public void testGenerateManualConnections(Tester t) {

  }

  // TODO tests for generateBoard
  public void testGenerateBoard(Tester t) {
    initData();
    t.checkExpect(this.game1.generateBoard().size(), 3);
    t.checkExpect(this.game1.generateBoard().get(0).size(), 3);
  }

  // tests for onMouseClicked
  // TODO added functionality
  void testOnMouseClicked(Tester t) {
    initData();
    t.checkExpect(this.game1.board.get(0).get(0).left, false);
    t.checkExpect(this.game1.board.get(0).get(0).top, false);
    t.checkExpect(this.game1.board.get(0).get(0).right, true);
    t.checkExpect(this.game1.board.get(0).get(0).bottom, false);
    t.checkExpect(this.game1.score, 0);
    this.game1.onMouseClicked(new Posn(1, 1), "LeftButton");
    t.checkExpect(this.game1.score, 1);
    t.checkExpect(this.game1.board.get(0).get(0).left, false);
    t.checkExpect(this.game1.board.get(0).get(0).top, false);
    t.checkExpect(this.game1.board.get(0).get(0).right, false);
    t.checkExpect(this.game1.board.get(0).get(0).bottom, true);
    this.game1.onMouseClicked(new Posn(1, 1), "RightButton");
    t.checkExpect(this.game1.score, 2);
    t.checkExpect(this.game1.board.get(0).get(0).left, false);
    t.checkExpect(this.game1.board.get(0).get(0).top, false);
    t.checkExpect(this.game1.board.get(0).get(0).right, true);
    t.checkExpect(this.game1.board.get(0).get(0).bottom, false);
    this.game1.onMouseClicked(new Posn(1, 1), "RightButton");
    t.checkExpect(this.game1.score, 3);
    t.checkExpect(this.game1.board.get(0).get(0).left, false);
    t.checkExpect(this.game1.board.get(0).get(0).top, true);
    t.checkExpect(this.game1.board.get(0).get(0).right, false);
    t.checkExpect(this.game1.board.get(0).get(0).bottom, false);
  }

  // tests for updateAllNeighbors
  // TODO test added functionality
  public void testUpdateAllNeighbors(Tester t) {
    initData();
    t.checkExpect(this.gamePiece1.neighbors.get("top"), null);
    t.checkExpect(this.gamePiece1.neighbors.get("left"), null);
    t.checkExpect(this.gamePiece1.neighbors.get("right"), null);
    t.checkExpect(this.gamePiece1.neighbors.get("bottom"), null);

    t.checkExpect(this.gamePiece5.neighbors.get("top"), null);
    t.checkExpect(this.gamePiece5.neighbors.get("left"), null);
    t.checkExpect(this.gamePiece5.neighbors.get("right"), null);
    t.checkExpect(this.gamePiece5.neighbors.get("bottom"), null);

    this.game1.updateAllNeighbors();

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

    this.game3.updateAllNeighbors();

    t.checkExpect(this.game3.board.get(0).get(0).neighbors.get("top"), null);
    t.checkExpect(this.game3.board.get(0).get(0).neighbors.get("right").samePiece(this.gamePiece02),
        true);
    t.checkExpect(
        this.game3.board.get(0).get(0).neighbors.get("bottom").samePiece(this.gamePiece06), true);
    t.checkExpect(this.game3.board.get(0).get(0).neighbors.get("left"), null);

  }

  // TODO tests for locatePiece
  void testLocatePiece(Tester t) {
    initData();
  }

  // TODO tests for restartGame
  void testRestartGame(Tester t) {
    initData();
  }

  // TODO tests for updatePower
  void testUpdatePower(Tester t) {
    initData();
  }

  // TODO tests for onKeyEvent
  void testOnKeyEvent(Tester t) {
    initData();
  }

  // tests for big bang
  void testBigBang(Tester t) {
    initData();
    LightEmAll game = new LightEmAll(19, 10, 1);
    game.bigBang(game.width * LightEmAll.CELL_SIZE,
        (game.height * LightEmAll.CELL_SIZE) + (LightEmAll.CELL_SIZE * 2), 0.25);
  }
}