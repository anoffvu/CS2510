import java.awt.Color;

import javalib.funworld.World;
import javalib.funworld.WorldScene;
import javalib.worldimages.CircleImage;
import tester.Tester;

interface IList<T> {

}

class MtList<T> implements IList<T> {
}

class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;

  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }
}

interface IScreenObject {
  IScreenObject move();
}

abstract class AScreenObjects implements IScreenObject {
  int radius;
  Color c = Color.CYAN;
  int x;
  int y; 
  double direction;
  double velocity;

  AScreenObjects(int radius, Color c, int x, int y, double direction, double velocity) {
    this.radius = radius;
    this.c = c;
    this.x = x;
    this.y = y;
    this.direction = direction;
    this.velocity = velocity;
  }
}

class Bullet extends AScreenObjects {
  int roundNumber;

  Bullet(int radius, Color c, int x, int y, double direction, double velocity, int roundNumber) {
    super(radius, c, x, y, direction, velocity);
    this.roundNumber = roundNumber;
  }

  WorldScene draw(WorldScene ws) {
    return ws.placeImageXY(new CircleImage(this.radius, "solid", this.c), this.x, this.y);
  }

  public Bullet move() {
    double xAdd = -this.velocity * Math.cos(Math.toRadians(this.direction));
    double yAdd = this.velocity * Math.sin(Math.toRadians(this.direction));

    return new Bullet(this.radius, this.c, (int) xAdd + this.x, (int) yAdd + this.y, this.direction,
        this.velocity, this.roundNumber);

  }
}

class Spaceship extends AScreenObjects {

  Spaceship(int radius, Color c, int x, int y, double direction, double velocity) {
    super(radius, c, x, y, direction, velocity);
  }

  public Spaceship move() {
    double xAdd = -this.velocity * Math.cos(Math.toRadians(this.direction));
    double yAdd = this.velocity * Math.sin(Math.toRadians(this.direction));

    return new Spaceship(this.radius, this.c, (int) xAdd + this.x, (int) yAdd + this.y,
        this.direction, this.velocity);
  }
}

class Bullets extends World {
  IList<IScreenObject> bullet;

  Bullets(IList<IScreenObject> bullet) {
    this.bullet = bullet;
  }

  public WorldScene makeScene() {
    // return this.dot.draw(new WorldScene(600,400)); // should probably be constants, eh?
    return this.bullet.draw(this.getEmptyScene());
  }

  public World onTick() {
    return new Bullets(this.bullet.move());
  }

}

class ExamplesBullet {
  IScreenObject bullet1 = new Bullet(2, Color.CYAN, 250, 300, 90, 1, 1);
  IScreenObject spaceship1 = new Spaceship(10, Color.RED, 250, 300, 90, 1);
  IList<IScreenObject> mt = new MtList<IScreenObject>();
  IList<IScreenObject> list1 = new ConsList<IScreenObject>(bullet1, mt);
  IList<IScreenObject> list2 = new ConsList<IScreenObject>(spaceship1, list1);

  boolean testBigBang(Tester t) {
    IList<IScreenObject> w = list1;
    int worldWidth = 500;
    int worldHeight = 300;
    double tickRate = 0.04;
    return w.bigBang(worldWidth, worldHeight, tickRate);
  }
}