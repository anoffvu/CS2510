import java.awt.Color;

import javalib.impworld.World;
import javalib.impworld.WorldScene;
import javalib.worldimages.CircleImage;
import javalib.worldimages.OutlineMode;
import javalib.worldimages.Posn;
import tester.Tester;

class BallGame extends World {
  public static final int GAME_WIDTH = 500;
  public static final int GAME_HEIGHT = 500;
  public static final int BALL_X_VEL = 1;

  Posn ballPosn;
  Color ballColor;

  BallGame() {
    this.ballPosn = new Posn(BallGame.GAME_WIDTH / 2, BallGame.GAME_HEIGHT / 2);
    this.ballColor = Color.BLUE;
  }

  public WorldScene makeScene() {
    WorldScene scene = this.getEmptyScene();
    scene.placeImageXY(new CircleImage(30, OutlineMode.SOLID, this.ballColor), this.ballPosn.x,
        this.ballPosn.y);
    return scene;
    }

  public void onTick() {
    this.ballPosn = new Posn(this.ballPosn.x + BallGame.BALL_X_VEL, this.ballPosn.y);
    }

  public void onMouseClicked(Posn pos) {
    this.ballPosn = pos;
    }

  public void onKeyEvent(String key) {
    if (key.equals("r")) {
      this.ballColor = Color.RED;
    }
    else if (key.equals("b")) {
      this.ballColor = Color.BLUE;
    }
    }
}

class Examples {
  void testGame(Tester t) {
    BallGame game = new BallGame();
    game.bigBang(500, 500, .01);
    }
}
