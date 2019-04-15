import java.util.ArrayList;
import java.util.HashMap;

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

  HashMap<String, GamePiece> gamePiece1Neighbors;
  HashMap<String, GamePiece> gamePiece2Neighbors;
  HashMap<String, GamePiece> gamePiece3Neighbors;
  HashMap<String, GamePiece> gamePiece4Neighbors;
  HashMap<String, GamePiece> gamePiece5Neighbors;
  HashMap<String, GamePiece> gamePiece6Neighbors;
  HashMap<String, GamePiece> gamePiece7Neighbors;
  HashMap<String, GamePiece> gamePiece8Neighbors;
  HashMap<String, GamePiece> gamePiece9Neighbors;

  LightEmAll twoByTwoBlank;
  LightEmAll twoByTwo;


  public void initData() {
    this.mt = new ArrayList<GamePiece>();
    // TODO: edit game pieces to add neighbors

    this.gamePiece1Neighbors = new HashMap<String, GamePiece>();
    this.gamePiece2Neighbors = new HashMap<String, GamePiece>();
    this.gamePiece3Neighbors = new HashMap<String, GamePiece>();
    this.gamePiece4Neighbors = new HashMap<String, GamePiece>();
    this.gamePiece5Neighbors = new HashMap<String, GamePiece>();
    this.gamePiece6Neighbors = new HashMap<String, GamePiece>();
    this.gamePiece7Neighbors = new HashMap<String, GamePiece>();
    this.gamePiece8Neighbors = new HashMap<String, GamePiece>();
    this.gamePiece9Neighbors = new HashMap<String, GamePiece>();

    this.gamePiece1 = new GamePiece(0, 0, this.gamePiece1Neighbors, false, false, false, true, true,
        0);
    this.gamePiece2 = new GamePiece(1, 0, this.gamePiece2Neighbors, false, false, false, true,
        false, 0);
    this.gamePiece3 = new GamePiece(2, 0, this.gamePiece3Neighbors, false, false, false, true,
        false, 0);
    this.gamePiece4 = new GamePiece(0, 1, this.gamePiece4Neighbors, false, true, true, true, false,
        0);
    this.gamePiece5 = new GamePiece(1, 1, this.gamePiece5Neighbors, true, true, true, true, false,
        0);
    this.gamePiece6 = new GamePiece(2, 1, this.gamePiece6Neighbors, true, false, true, true, false,
        0);
    this.gamePiece7 = new GamePiece(0, 2, this.gamePiece7Neighbors, false, false, false, true,
        false, 0);
    this.gamePiece8 = new GamePiece(1, 2, this.gamePiece8Neighbors, false, false, true, false,
        false, 0);
    this.gamePiece9 = new GamePiece(2, 2, this.gamePiece9Neighbors, false, false, false, true,
        false, 0);

    this.gamePiece1Neighbors.put("right", gamePiece2);
    this.gamePiece1Neighbors.put("bottom", gamePiece4);
    this.gamePiece2Neighbors.put("left", gamePiece1);
    this.gamePiece2Neighbors.put("right", gamePiece3);
    this.gamePiece2Neighbors.put("bottom", gamePiece5);
    this.gamePiece3Neighbors.put("bottom", gamePiece6);
    this.gamePiece3Neighbors.put("left", gamePiece2);
    this.gamePiece4Neighbors.put("top", gamePiece1);
    this.gamePiece4Neighbors.put("bottom", gamePiece7);
    this.gamePiece4Neighbors.put("right", gamePiece5);
    this.gamePiece5Neighbors.put("left", gamePiece4);
    this.gamePiece5Neighbors.put("top", gamePiece2);
    this.gamePiece5Neighbors.put("right", gamePiece6);
    this.gamePiece5Neighbors.put("bottom", gamePiece8);
    this.gamePiece6Neighbors.put("top", gamePiece3);
    this.gamePiece6Neighbors.put("left", gamePiece5);
    this.gamePiece6Neighbors.put("bottom", gamePiece9);
    this.gamePiece7Neighbors.put("top", gamePiece4);
    this.gamePiece7Neighbors.put("right", gamePiece8);
    this.gamePiece8Neighbors.put("top", gamePiece5);
    this.gamePiece9Neighbors.put("top", gamePiece6);

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

    this.twoByTwoBlank = new LightEmAll(2, 2, -1);
    this.twoByTwo = new LightEmAll(2, 2, 2);
  }

  // TODO tests for drawPiece
  void testDrawPiece(Tester t) {
    initData();

  }

  // TODO tests for calcColor
  void testCalcColor(Tester t) {
    initData();

  }

  // tests for updateNeighbor
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

  // tests for samePiece
  void testSamePiece(Tester t) {
    initData();
    t.checkExpect(this.gamePiece01.samePiece(this.gamePiece01), true);
    t.checkExpect(this.gamePiece01.samePiece(this.gamePiece02), false);
    t.checkExpect(this.gamePiece9.samePiece(this.gamePiece9), true);
    t.checkExpect(this.gamePiece9.samePiece(this.gamePiece10), false);
    t.checkExpect(this.gamePiece04.samePiece(this.gamePiece04), true);
  }

  // tests for isConnectedTo
  void testIsConnecteTo(Tester t) {
    initData();
    t.checkExpect(this.gamePiece1.isConnectedTo("top"), false);
    t.checkExpect(this.gamePiece1.isConnectedTo("bottom"), true);
    t.checkExpect(this.gamePiece1.isConnectedTo("left"), false);
    t.checkExpect(this.gamePiece1.isConnectedTo("right"), false);

    t.checkExpect(this.gamePiece3.isConnectedTo("top"), false);
    t.checkExpect(this.gamePiece3.isConnectedTo("bottom"), true);
    t.checkExpect(this.gamePiece3.isConnectedTo("left"), false);
    t.checkExpect(this.gamePiece3.isConnectedTo("right"), false);

    t.checkExpect(this.gamePiece5.isConnectedTo("top"), true);
    t.checkExpect(this.gamePiece5.isConnectedTo("bottom"), true);
    t.checkExpect(this.gamePiece5.isConnectedTo("left"), true);
    t.checkExpect(this.gamePiece5.isConnectedTo("right"), true);
  }

  // tests for rotatePiece
  void testRotatePiece(Tester t) {
    initData();
    t.checkExpect(this.gamePiece1.left, false);
    t.checkExpect(this.gamePiece1.top, false);
    t.checkExpect(this.gamePiece1.right, false);
    t.checkExpect(this.gamePiece1.bottom, true);

    this.gamePiece1.rotatePiece(1);

    t.checkExpect(this.gamePiece1.left, true);
    t.checkExpect(this.gamePiece1.top, false);
    t.checkExpect(this.gamePiece1.right, false);
    t.checkExpect(this.gamePiece1.bottom, false);

    t.checkExpect(this.gamePiece2.left, false);
    t.checkExpect(this.gamePiece2.top, false);
    t.checkExpect(this.gamePiece2.right, false);
    t.checkExpect(this.gamePiece2.bottom, true);

    this.gamePiece2.rotatePiece(1);

    t.checkExpect(this.gamePiece2.left, true);
    t.checkExpect(this.gamePiece2.top, false);
    t.checkExpect(this.gamePiece2.right, false);
    t.checkExpect(this.gamePiece2.bottom, false);

    t.checkExpect(this.gamePiece3.left, false);
    t.checkExpect(this.gamePiece3.top, false);
    t.checkExpect(this.gamePiece3.right, false);
    t.checkExpect(this.gamePiece3.bottom, true);

    this.gamePiece3.rotatePiece(-1);
    t.checkExpect(this.gamePiece3.left, false);
    t.checkExpect(this.gamePiece3.top, false);
    t.checkExpect(this.gamePiece3.right, true);
    t.checkExpect(this.gamePiece3.bottom, false);
  }

  // TODO tests for powerNeighbors
  void testPowerNeighbors(Tester t) {
    initData();
  }

  // tests for grabAllNodes
  public void testGrabAllNodes(Tester t) {
    initData();
    t.checkExpect(this.game1.grabAllNodes().size(), 9);
    t.checkExpect(this.game1.nodes.size(), 9);
    t.checkExpect(this.game1.grabAllNodes().get(0), this.game1.board.get(0).get(0));
    t.checkExpect(this.game1.grabAllNodes().get(2), this.game1.board.get(0).get(2));
    t.checkExpect(this.game1.grabAllNodes().get(8), this.game1.board.get(2).get(2));
    t.checkExpect(this.game2.nodes.size(), 100);
  }

  // TODO tests for locatePiece
  void testLocatePiece(Tester t) {
    initData();

  }

  // tests for restartGame
  void testRestartGame(Tester t) {
    initData();
    this.game1.onMouseClicked(new Posn(1, 1), "LeftButton");
    t.checkExpect(this.game1.score, 1);
    this.game1.restartGame();
    t.checkExpect(this.game1.score, 0);

    this.game2.onMouseClicked(new Posn(1, 1), "RightButton");
    t.checkExpect(this.game2.score, 1);
    this.game2.restartGame();
    t.checkExpect(this.game2.score, 0);
  }

  // TODO tests for updatePower
  void testUpdatePower(Tester t) {
    initData();
  }

  // TODO tests for onKeyEvent
  void testOnKeyEvent(Tester t) {
    initData();

  }

  // tests for updateAllNeighbors
  public void testUpdateAllNeighbors(Tester t) {
    initData();
    t.checkExpect(this.gamePiece1.neighbors.get("top"), null);
    t.checkExpect(this.gamePiece1.neighbors.get("left"), null);
    t.checkExpect(this.gamePiece1.neighbors.get("right"), this.gamePiece2);
    t.checkExpect(this.gamePiece1.neighbors.get("bottom"), this.gamePiece4);

    initData();
    this.gamePiece1.rotatePiece(1);
    this.game1.updateAllNeighbors();

    t.checkExpect(this.gamePiece1.neighbors.get("top"), null);
    t.checkExpect(this.gamePiece1.neighbors.get("left"), null);
    t.checkExpect(this.gamePiece1.neighbors.get("right"), this.gamePiece2);
    t.checkExpect(this.gamePiece1.neighbors.get("bottom"), this.gamePiece4);

  }

  // tests for splitBoard
  void testSplitBoard(Tester t) {
    initData();
    t.checkExpect(this.game2.splitBoard(1, new LightEmAll(4, 4, 2).board).size(), 4);
    t.checkExpect(this.game2.splitBoard(1, new LightEmAll(4, 4, 2).board).get(0).size(), 2);
    t.checkExpect(this.game2.splitBoard(1, new LightEmAll(4, 4, 2).board).get(0).get(0).size(), 2);
    t.checkExpect(this.game2.splitBoard(1, new LightEmAll(4, 4, 2).board).get(1).get(0).size(), 2);
    t.checkExpect(this.game2.splitBoard(1, new LightEmAll(4, 4, 2).board).get(2).get(0).size(), 2);
    t.checkExpect(this.game2.splitBoard(1, new LightEmAll(4, 4, 2).board).get(3).get(0).size(), 2);
    t.checkExpect(this.game2.splitBoard(2, new LightEmAll(2, 4, 2).board).size(), 2);
    t.checkExpect(this.game2.splitBoard(1, new LightEmAll(7, 7, 2).board).size(), 4);
    t.checkExpect(this.game2.splitBoard(1, new LightEmAll(7, 7, 2).board).get(0).size(), 4);
    t.checkExpect(this.game2.splitBoard(1, new LightEmAll(7, 7, 2).board).get(1).size(), 3);
    t.checkExpect(this.game2.splitBoard(1, new LightEmAll(7, 7, 2).board).get(2).size(), 4);
    t.checkExpect(this.game2.splitBoard(1, new LightEmAll(7, 7, 2).board).get(3).size(), 3);
    t.checkExpect(this.game2.splitBoard(1, new LightEmAll(7, 7, 2).board).get(0).get(0).size(), 4);
    t.checkExpect(this.game2.splitBoard(1, new LightEmAll(7, 7, 2).board).get(1).get(0).size(), 4);
    t.checkExpect(this.game2.splitBoard(1, new LightEmAll(7, 7, 2).board).get(2).get(0).size(), 3);
    t.checkExpect(this.game2.splitBoard(1, new LightEmAll(7, 7, 2).board).get(3).get(0).size(), 3);

  }

  // tests for determineSplitType
  void testDetermineSplitType(Tester t) {
    initData();
    t.checkExpect(this.game2.determineSplitType(game2.board), 1);
    t.checkExpect(this.game2.determineSplitType(new LightEmAll(4, 4, 1).board), 1);
    t.checkExpect(this.game2.determineSplitType(new LightEmAll(3, 4, 1).board), 2);
    t.checkExpect(this.game2.determineSplitType(new LightEmAll(10, 2, 1).board), 3);
    t.checkExpect(this.game2.determineSplitType(new LightEmAll(2, 10, 1).board), 2);
    t.checkExpect(this.game2.determineSplitType(new LightEmAll(7, 7, 1).board), 1);
  }

  // tests for buildU
  void testBuildU(Tester t) {
    initData();
    t.checkExpect(this.game2.board.get(0).get(0).bottom, false);
    t.checkExpect(this.game2.board.get(9).get(0).bottom, false);
    t.checkExpect(this.game2.board.get(0).get(9).top, false);
    t.checkExpect(this.game2.board.get(9).get(9).top, false);
    t.checkExpect(this.game2.board.get(0).get(4).top, false);
    t.checkExpect(this.game2.board.get(9).get(4).top, false);
    t.checkExpect(this.game2.board.get(0).get(4).top, false);
    t.checkExpect(this.game2.board.get(9).get(4).top, false);
    this.game2.buildU(this.game2.board);
    t.checkExpect(this.game2.board.get(0).get(0).bottom, true);
    t.checkExpect(this.game2.board.get(9).get(0).bottom, true);
    t.checkExpect(this.game2.board.get(0).get(9).top, true);
    t.checkExpect(this.game2.board.get(9).get(9).top, true);
    t.checkExpect(this.game2.board.get(0).get(4).top, true);
    t.checkExpect(this.game2.board.get(9).get(4).top, true);
    t.checkExpect(this.game2.board.get(0).get(4).top, true);
    t.checkExpect(this.game2.board.get(9).get(4).top, true);
    initData();
    t.checkExpect(this.twoByTwoBlank.board.get(0).get(0).bottom, false);
    t.checkExpect(this.twoByTwoBlank.board.get(1).get(0).bottom, false);
    t.checkExpect(this.twoByTwoBlank.board.get(0).get(1).top, false);
    t.checkExpect(this.twoByTwoBlank.board.get(1).get(1).top, false);
    this.twoByTwoBlank.buildU(twoByTwoBlank.board);
    t.checkExpect(this.twoByTwoBlank.board.get(0).get(0).bottom, true);
    t.checkExpect(this.twoByTwoBlank.board.get(1).get(0).bottom, true);
    t.checkExpect(this.twoByTwoBlank.board.get(0).get(1).top, true);
    t.checkExpect(this.twoByTwoBlank.board.get(1).get(1).top, true);
  }

  // TODO tests for generateFractalConnections
  void testGenerateFractalConnections(Tester t) {
    initData();
  }

  /*
   * // TODO tests for
   * void testBreadthFirstTransversal(Tester t) {
   * initData();
   * this.twoByTwo.buildU(twoByTwo.board);
   * t.checkExpect(twoByTwo.breadthFirstTransversal(twoByTwo.board.get(0).get(0)).size(),
   * 4);
   * }
   */

  /*
   * // tests for big bang
   * void testBigBang(Tester t) {
   * initData();
   * LightEmAll game = new LightEmAll(2, 2);
   * game.bigBang(game.width * LightEmAll.CELL_SIZE,
   * (game.height * LightEmAll.CELL_SIZE) + (LightEmAll.CELL_SIZE * 2), 0.25);
   * }
   */

}