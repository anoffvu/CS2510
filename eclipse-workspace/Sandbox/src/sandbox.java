import java.awt.Color;
import java.util.Random;

import javalib.funworld.World;
import javalib.funworld.WorldScene;
import javalib.worldimages.CircleImage;
import tester.Tester;

interface ILoBullet {

  // draw world
  WorldScene draw(WorldScene ws);

  // moves an entire list of bullets
  ILoBullet moveLOB();

}

class MtLoBullet implements ILoBullet {

  // draws a WorldScene of an empty list of bullets
  public WorldScene draw(WorldScene ws) {
    return ws;
  }

  // returning this because of an empty list of bullets
  public ILoBullet moveLOB() {
    return this;
  }
}

class ConsLoBullet implements ILoBullet {
  Bullet first;
  ILoBullet rest;

  ConsLoBullet(Bullet first, ILoBullet rest) {
    this.first = first;
    this.rest = rest;
  }

  public WorldScene draw(WorldScene ws) {
    return this.first.draw(this.rest.draw(ws));
  }

  public ILoBullet moveLOB() {
    return new ConsLoBullet(this.first.move(), this.rest.moveLOB());
  }
}

interface ILoShip {
  WorldScene draw(WorldScene ws);

  // moves an entire list of ships
  public ILoShip moveLOS();

  // spawns ships on the screen
  public ILoShip spawn();
}

class MtLoShip implements ILoShip {

  public WorldScene draw(WorldScene ws) {
    return ws;
  }

  public ILoShip moveLOS() {
    return this;
  }

  public ILoShip spawn() {
    int startX = new Utils().start();
    return new ConsLoShip(new Ship(10, startX, new Utils().randomNumber(50, 250),
        new Utils().pickDirection(startX), 5, Color.pink), new MtLoShip());
  }
}

class ConsLoShip implements ILoShip {
  Ship first;
  ILoShip rest;

  ConsLoShip(Ship first, ILoShip rest) {
    this.first = first;
    this.rest = rest;
  }

  public WorldScene draw(WorldScene ws) {
    return this.first.draw(this.rest.draw(ws));
  }

  public ILoShip moveLOS() {
    return new ConsLoShip(this.first.move(), this.rest.moveLOS());
  }

  public ILoShip spawn() {
    int startX = new Utils().start();
    return new ConsLoShip(new Ship(10, startX, new Utils().randomNumber(50, 250),
        new Utils().pickDirection(startX), 5, Color.pink), this);
  }
}

//represents an object on the screen, either a ship or bullet 
abstract class AObjects {
  int size;
  int x;
  int y;
  double direction;
  double velocity;
  Color color;

  AObjects(int size, int x, int y, double direction, double velocity, Color color) {
    this.size = size;
    this.x = x;
    this.y = y;
    this.direction = direction;
    this.velocity = velocity;
    this.color = color;
  }

  // moves an object on the screen
  public abstract AObjects move();
}

class Bullet extends AObjects {
  int bulletNumber;

  Bullet() {
    this(2, 250, 300, 270, 8, Color.pink, 1);
  }

  Bullet(int size, int x, int y, double direction, double velocity, Color color, int bulletNumber) {
    super(size, x, y, direction, velocity, color);
    this.bulletNumber = bulletNumber;
  }

  WorldScene draw(WorldScene ws) {
    return ws.placeImageXY(new CircleImage(this.size, "solid", this.color), this.x, this.y);
  }

  public Bullet move() {
    double x = -this.velocity * Math.cos(Math.toRadians(this.direction));
    double y = this.velocity * Math.sin(Math.toRadians(this.direction));

    return new Bullet(this.size, (int) x + this.x, (int) y + this.y, this.direction, this.velocity,
        this.color, this.bulletNumber);
  }
}

class Utils {
  Random rand;

  public int start() {
    if (rand.nextBoolean()) {
      return 0;
    }
    else {
      return 500;
    }
  }

  public int randomNumber(int min, int max) {
    return (rand.nextInt(max - min) + min);
  }

  public double pickDirection(int x) {
    if (x == 0) {
      return 0.0;
    }
    else {
      return 180.0;
    }
  }
}

class Ship extends AObjects {

  // this creates a random ship

  Ship(int size, int x, int y, double direction, double velocity, Color color) {
    super(size, x, y, direction, velocity, color);
  }

  public Ship move() {
    double x = -this.velocity * Math.cos(Math.toRadians(this.direction));
    return new Ship(this.size, (int) x + this.x, this.y, this.direction, this.velocity, this.color);
  }

  WorldScene draw(WorldScene ws) {
    return ws.placeImageXY(new CircleImage(this.size, "solid", this.color), this.x, this.y);
  }
}

class GameScene extends World {
  public static final int screenWidth = 500;
  public static final int screenHeight = 300;
  public static final double tickRate = 1.0 / 28.0;
  public static final int shipSpeed = 5;
  public static final int bulletSpeed = 5;
  int bulletsLeft;
  int destroyed;
  ILoShip loShips;
  ILoBullet loBullets;
  Random random;
  int clock;

  public GameScene(int bulletsLeft, int destroyed, ILoShip loShips, ILoBullet loBullets,
      Random random, int clock) {
    this.bulletsLeft = bulletsLeft;
    this.destroyed = destroyed;
    this.loShips = loShips;
    this.loBullets = loBullets;
    this.random = random;
    this.clock = clock;
  }

  public GameScene() {
    this.bulletsLeft = 10;
    this.destroyed = 0;
    this.loShips = new MtLoShip();
    this.loBullets = new MtLoBullet();
    this.clock = 0;
  }

  public GameScene spawnObjects() {
    ILoShip spawnShips = this.loShips.spawn();

    if (this.clock % 28 == 0) {
      return new GameScene(this.bulletsLeft, this.destroyed, spawnShips, this.loBullets,
          this.random, this.clock);
    }
    else {
      return this;
    }
  }

  public GameScene moveObjects() {
    ILoShip newShips = this.loShips.moveLOS();
    ILoBullet newBullets = this.loBullets.moveLOB();
    return new GameScene(this.bulletsLeft, this.destroyed, newShips, newBullets, this.random,
        this.clock);
  }

  public GameScene onTick() {
    GameScene advanceClock = new GameScene(this.bulletsLeft, this.destroyed, this.loShips,
        this.loBullets, this.random, this.clock + 1);
    return advanceClock.moveObjects();
  }

  public WorldScene makeScene() {
    return this.loShips.draw(this.loBullets.draw(new WorldScene(500, 300)));
  }

}

class ExamplesNBullets {
  boolean testBigBang(Tester t) {
    GameScene game = new GameScene();
    int worldWidth = 500;
    int worldHeight = 300;
    double tickRate = 1.0 / 28.0;
    return game.bigBang(worldWidth, worldHeight, tickRate);
  }

  Ship ship = new Ship(10, 10, 10, 10, 10, Color.pink);

}
