import java.awt.Color;
import java.util.Random;

import javalib.funworld.World;
import javalib.funworld.WorldScene;
import javalib.worldimages.CircleImage;
import tester.Tester;

// general utils class for the project
class Utils {
  // initiates a random variable
  Random rand = new Random();
  int screenWidth = 500;
  int screenHeight = 300;

  // returns a random number between the given min and max values
  public int randomNumber(int min, int max) {
    return (rand.nextInt(max - min) + min);
  }

  // determines if an element is onscreen
  boolean onScreen(int radius, int x, int y) {
    return (0 - radius < x && x < screenWidth + radius)
        && (0 - radius < y && y < screenHeight + radius);
  }
}

// bullet interface 
interface ILoBullet {

  // draw world
  WorldScene draw(WorldScene ws);

  // moves an entire list of bullets
  ILoBullet moveLOB(ILoShip los);

  // explodes a bullet
  ILoBullet explode(Bullet hitBullet);

}

// represents an empty list of bullets 
class MtLoBullet implements ILoBullet {

  // draws a WorldScene of an empty list of bullets
  public WorldScene draw(WorldScene ws) {
    return ws;
  }

  // returning this because of an empty list of bullets
  public ILoBullet moveLOB(ILoShip los) {
    return this;
  }

  // explodes a bullet and adds it to this empty list of bullets
  public ILoBullet explode(Bullet hitBullet) {
    double explodeAngle = hitBullet.explodeAngles();
    int explodeCount = hitBullet.explodeCount();
    return this.explodeHelper(explodeAngle, explodeCount);
  }

  private ILoBullet explodeHelper(double explodeAngle, int explodeCount) {
    // TODO Auto-generated method stub
    return null;
  }
}

// represents a list of Bullets 
class ConsLoBullet implements ILoBullet {
  Bullet first;
  ILoBullet rest;

  ConsLoBullet(Bullet first, ILoBullet rest) {
    this.first = first;
    this.rest = rest;
  }

  // draws this non empty list of bullets
  public WorldScene draw(WorldScene ws) {
    return this.first.draw(this.rest.draw(ws));
  }

  // moves this list of bullets
  public ILoBullet moveLOB(ILoShip los) {
    if (this.first.onScreen() && this.first.isColliding(los)) {
      return this.rest.moveLOB(los).explode(this.first);
    }
    else if (this.first.onScreen()) {
      return new ConsLoBullet(this.first.move(), this.rest.moveLOB(los));
    }
    else {
      return this.rest.moveLOB(los);
    }
  }

  // explodes a bullet onto this non empty list of bullets
  public ILoBullet explode(Bullet hitBullet) {
    return null;
  }
}

// ship interface 
interface ILoShip {

  // draws an ILoShip
  WorldScene draw(WorldScene ws);

  // moves an entire list of ships
  public ILoShip moveLOS();

  // spawns ships on the screen
  public ILoShip spawn(int currentClock);

  // determines if the passed in bullet has hit any ships
  boolean anyHits(Bullet bullet);
}

//represents an empty list of ships 
class MtLoShip implements ILoShip {

  // draws this empty list of ships
  public WorldScene draw(WorldScene ws) {
    return ws;
  }

  // moves this empty list of ships
  public ILoShip moveLOS() {
    return this;
  }

  // checks if its time to spawn a ship, and does so
  public ILoShip spawn(int currentClock) {
    if (currentClock % 28 == 0) {
      return this.spawnShip();
    }
    else {
      return this;
    }
  }

  // spawns a new ship if time is right
  public ILoShip spawnShip() {
    if (new Random().nextBoolean()) {
      return new ConsLoShip(
          new Ship(10, 0, new Utils().randomNumber(50, 250), 180.0, 5, Color.pink), new MtLoShip());
    }
    else {
      return new ConsLoShip(
          new Ship(10, 500, new Utils().randomNumber(50, 250), 0.0, 5, Color.pink), new MtLoShip());
    }
  }

  // has this bullet hit any ships in this empty list of Ships
  public boolean anyHits(Bullet bullet) {
    return false;
  }
}

//represents a list of ships 
class ConsLoShip implements ILoShip {
  Ship first;
  ILoShip rest;
  Random rand;

  ConsLoShip(Ship first, ILoShip rest) {
    this.first = first;
    this.rest = rest;
  }

  // draws this non empty list of ships
  public WorldScene draw(WorldScene ws) {
    return this.first.draw(this.rest.draw(ws));
  }

  // moves this non empty list of ships
  public ILoShip moveLOS() {
    if (this.first.onScreen()) {
      return new ConsLoShip(this.first.move(), this.rest.moveLOS());
    }
    else {
      return this.rest.moveLOS();
    }
  }

  // checks if its time to spawn a ship, and does so
  public ILoShip spawn(int currentClock) {
    if (currentClock % 28 == 0) {
      return this.spawnShip();
    }
    else {
      return this;
    }
  }

  // spawns a ship onto this non empty list of ships
  public ILoShip spawnShip() {
    if (new Random().nextBoolean()) {
      return new ConsLoShip(
          new Ship(10, 0, new Utils().randomNumber(50, 250), 180.0, 5, Color.pink), this);
    }
    else {
      return new ConsLoShip(
          new Ship(10, 500, new Utils().randomNumber(50, 250), 0.0, 5, Color.pink), this);
    }
  }

  // checks if the bullet has hit any ships in this non empty list of ships
  public boolean anyHits(Bullet bullet) {
    return this.first.hit(bullet) || this.rest.anyHits(bullet);
  }
}

//represents all the objects 
interface IObjects {

}

//represents an object on the screen, either a ship or bullet 
abstract class AObjects implements IObjects {
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

  // returns the x distance between two objects
  public int distanceX(AObjects object) {
    return this.x - object.x;
  }

  // returns the y distance between two objects
  public int distanceY(AObjects object) {
    return this.y - object.y;
  }

  // did two objects collide with eachother
  public boolean hit(AObjects object) {
    int x = this.distanceX(object);
    int y = this.distanceY(object);

    return Math.hypot(x, y) <= object.size + this.size;
  }

  // moves an object on the screen
  public abstract AObjects move();

  // determines if an object is within the screen width and height
  public boolean onScreen() {
    return new Utils().onScreen(size, x, y);
  }
}

// represents a bullet 
class Bullet extends AObjects {
  int bulletRound;

  // convenience constructor for a brand new bullet at the bottom middle of the screen
  Bullet() {
    this(2, 250, 300, 270, 8, Color.blue, 1);
  }

  // determines if this bullet is hitting any ships
  public boolean isColliding(ILoShip los) {
    return los.anyHits(this);
  }

  Bullet(int size, int x, int y, double direction, double velocity, Color color, int bulletRound) {
    super(size + ((bulletRound - 1) * 2), x, y, direction, velocity, color);
    this.bulletRound = bulletRound;
  }

  // calculates the explosion angles if this bullet explodes
  public double explodeAngles() {
    return 360.00 / this.bulletRound;
  }

//calculates the explosion angles if this bullet explodes
  public int explodeCount() {
    return this.bulletRound + 1;
  }

  // draws this single bullet
  WorldScene draw(WorldScene ws) {
    return ws.placeImageXY(new CircleImage(this.size, "solid", this.color), this.x, this.y);
  }

  // moves this single bullet according to its velocity
  public Bullet move() {
    double x = -this.velocity * Math.cos(Math.toRadians(this.direction));
    double y = this.velocity * Math.sin(Math.toRadians(this.direction));

    return new Bullet(this.size, (int) x + this.x, (int) y + this.y, this.direction, this.velocity,
        this.color, this.bulletRound);
  }
}

// represents a ship 
class Ship extends AObjects {

  // this creates a random ship

  Ship(int size, int x, int y, double direction, double velocity, Color color) {
    super(size, x, y, direction, velocity, color);
  }

  // move a single ship across the screen
  public Ship move() {
    double x = -this.velocity * Math.cos(Math.toRadians(this.direction));
    return new Ship(this.size, (int) x + this.x, this.y, this.direction, this.velocity, this.color);
  }

  // draws this single ship
  WorldScene draw(WorldScene ws) {
    return ws.placeImageXY(new CircleImage(this.size, "solid", this.color), this.x, this.y);
  }

}

// world state of the game
class GameScene extends World {
  public static final int screenWidth = 500;
  public static final int screenHeight = 300;
  public static final double tickRate = 1.0 / 28.0;
  int bulletsLeft;
  int destroyed;
  ILoShip loShips;
  ILoBullet loBullets;
  Random random;
  int clock;

  // constructing a game scene
  public GameScene(int bulletsLeft, int destroyed, ILoShip loShips, ILoBullet loBullets,
      Random random, int clock) {
    this.bulletsLeft = bulletsLeft;
    this.destroyed = destroyed;
    this.loShips = loShips;
    this.loBullets = loBullets;
    this.random = random;
    this.clock = clock;
  }

  // convenience constructor representing the start of the game
  public GameScene() {
    this.bulletsLeft = 10;
    this.destroyed = 0;
    this.loShips = new MtLoShip();
    this.loBullets = new MtLoBullet();
    this.clock = 0;
  }

  // updates the game very tick
  public GameScene onTick() {
    return new GameScene(this.bulletsLeft, this.destroyed, this.loShips.spawn(clock).moveLOS(),
        this.loBullets.moveLOB(this.loShips), this.random, this.clock + 1);
  }

  // if the spacebar is pressed and there is enough remaining bullets fire a bullet
  public GameScene onKeyEvent(String key) {
    if (key.equals(" ") && bulletsLeft > 0) {
      return new GameScene(this.bulletsLeft - 1, this.destroyed, this.loShips,
          new ConsLoBullet(new Bullet(), this.loBullets), this.random, this.clock);
    }
    else {
      return this;
    }
  }

  // draw the ships and the bullets on to the new worldscene
  public WorldScene makeScene() {

    WorldScene canvas = new WorldScene(500, 300);
    WorldScene drawnBullets = this.loBullets.draw(canvas);
    WorldScene drawnShips = this.loShips.draw(drawnBullets);
    return drawnShips;
  }

}

class ExamplesNBullets {

  GameScene world = new GameScene();

  Bullet defaultBullet = new Bullet(2, 250, 300, 270, 8, Color.blue, 1);
  Bullet secondBullet = new Bullet(2, 250, 292, 270, 8, Color.blue, 1);
  Ship missShip = new Ship(20, 250, 270, 180.0, 10.0, Color.blue);
  Ship hitShip = new Ship(20, 250, 279, 180.0, 10.0, Color.blue);
  Ship hitShip2 = new Ship(20, 250, 285, 180.0, 10.0, Color.blue);

  // TODO: test for randomNumber
  boolean testRandomNumber(Tester t) {
    return true;
  }

  // TODO: test for onScreen
  boolean testOnScreen(Tester t) {
    return true;
  }

  // TODO: test for spawn
  boolean testSpawn(Tester t) {
    return true;
  }

  // TODO: test for distanceX
  boolean testDistanceX(Tester t) {
    return true;
  }

  // TODO: test for distanceY
  boolean testDistanceY(Tester t) {
    return true;
  }

  // test for move function
  boolean testMove(Tester t) {
    return t.checkExpect(this.defaultBullet.move(), new Bullet(2, 250, 292, 270, 8, Color.blue, 1))
        && t.checkExpect(this.secondBullet.move(), new Bullet(2, 250, 284, 270, 8, Color.blue, 1));
  }

  // TODO: test for moveLOS
  boolean testMoveLOS(Tester t) {
    return true;
  }

  // TODO: test for moveLOB
  boolean testMoveLOB(Tester t) {
    return true;
  }

  // test for hit
  boolean testHit(Tester t) {
    return t.checkExpect(this.defaultBullet.hit(this.hitShip), true)
        && t.checkExpect(this.defaultBullet.hit(this.hitShip2), true)
        && t.checkExpect(this.defaultBullet.hit(this.missShip), false);
  }

  // TODO: test for anyHit
  boolean testAnyHit(Tester t) {
    return true;
  }

  // TODO: test for isColliding
  boolean testIsColliding(Tester t) {
    return true;
  }

  // TODO: test for explode
  boolean testExplode(Tester t) {
    return true;
  }

  // TODO: test for explodeAngles
  boolean testExplodeAngles(Tester t) {
    return true;
  }

  // TODO: test for explodeCount
  boolean testExplodeCount(Tester t) {
    return true;
  }

  // TODO: test for onTick
  boolean testOnTick(Tester t) {
    return true;
  }

  // TODO: test for onKeyEvent
  boolean testOnKeyEvent(Tester t) {
    return true;
  }

  boolean testBigBang(Tester t) {
    GameScene game = new GameScene();
    int worldWidth = 500;
    int worldHeight = 300;
    double tickRate = 1.0 / 500.0;
    return game.bigBang(worldWidth, worldHeight, tickRate);
  }

}