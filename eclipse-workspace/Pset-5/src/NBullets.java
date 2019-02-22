import java.awt.Color;
import java.util.Random;

import javalib.funworld.World;
import javalib.funworld.WorldScene;
import javalib.worldimages.CircleImage;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldEnd;
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

  // determines if this list of bullets is empty
  boolean isEmpty();

  // determines if the passed in ship has hit any bullets
  boolean anyHits(Ship ship);

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

//explodes a bullet and adds it to this empty list of bullets
  public ILoBullet explode(Bullet hitBullet) {
    double explodeAngle = hitBullet.explodeAngles();
    int explodeCount = hitBullet.explodeCount();
    return explodeHelper(explodeAngle, explodeCount, hitBullet.x, hitBullet.y,
        hitBullet.bulletRound, hitBullet.size, this);
  }

  public ILoBullet explodeHelper(double explodeAngle, int explodeCount, int x, int y,
      int bulletRound, int initialSize, ILoBullet base) {

    if (explodeCount > 0 && bulletRound < 4) {
      return new ConsLoBullet(
          new Bullet(initialSize + (2 * bulletRound), x, y, explodeAngle * explodeCount, 10,
              Color.blue, bulletRound + 1),
          this.explodeHelper(explodeAngle, explodeCount - 1, x, y, bulletRound, initialSize, base));
    }
    else if (explodeCount > 0 && bulletRound >= 4) {
      return new ConsLoBullet(
          new Bullet(initialSize, x, y, explodeAngle * explodeCount, 10, Color.blue,
              bulletRound + 1),
          this.explodeHelper(explodeAngle, explodeCount - 1, x, y, bulletRound, initialSize, base));
    }
    else {
      return base;
    }

  }

  // determines if this empty list is empty
  public boolean isEmpty() {
    return true;
  }

  // determines if the passed in ship has hit any bullets in this empty list
  // of bullets
  public boolean anyHits(Ship ship) {
    return false;
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
    // checks if the first bullet is colliding with a ship
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

  // explodes a bullet and adds it to this empty list of bullets
  public ILoBullet explode(Bullet hitBullet) {
    double explodeAngle = hitBullet.explodeAngles();
    int explodeCount = hitBullet.explodeCount();
    return explodeHelper(explodeAngle, explodeCount, hitBullet.x, hitBullet.y,
        hitBullet.bulletRound, hitBullet.size, this);
  }

  public ILoBullet explodeHelper(double explodeAngle, int explodeCount, int x, int y,
      int bulletRound, int initialSize, ILoBullet base) {

    if (explodeCount > 0 && bulletRound < 4) {
      return new ConsLoBullet(
          new Bullet(initialSize + (2 * bulletRound), x, y, explodeAngle * explodeCount, 10,
              Color.blue, bulletRound + 1),
          this.explodeHelper(explodeAngle, explodeCount - 1, x, y, bulletRound, initialSize, base));
    }
    else if (explodeCount > 0 && bulletRound >= 4) {
      return new ConsLoBullet(
          new Bullet(initialSize, x, y, explodeAngle * explodeCount, 10, Color.blue,
              bulletRound + 1),
          this.explodeHelper(explodeAngle, explodeCount - 1, x, y, bulletRound, initialSize, base));
    }
    else {
      return base;
    }

  }

  // determines if this non empty list is empty
  public boolean isEmpty() {
    return false;
  }

  // determines if the passed in ship has hit any bullets
  public boolean anyHits(Ship ship) {
    return this.first.collided(ship) || this.rest.anyHits(ship);
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

  // counts the collisions on this list of ships
  public int countCollisions(ILoBullet lob);

  // removes any ships that have collided with a bullet
  ILoShip removeCollisions(ILoBullet lob);
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
    if (currentClock % 14 == 0) {
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
          new Ship(20, 0, new Utils().randomNumber(50, 250), 180.0, 5, Color.pink), new MtLoShip());
    }
    else {
      return new ConsLoShip(
          new Ship(20, 500, new Utils().randomNumber(50, 250), 0.0, 5, Color.pink), new MtLoShip());
    }
  }

  // has this bullet hit any ships in this empty list of Ships
  public boolean anyHits(Bullet bullet) {
    return false;
  }

  // counts the number of ships that have collided with a bullet
  public int countCollisions(ILoBullet lob) {
    return 0;
  }

  // removes any collided ships from this empty list of ships
  public ILoShip removeCollisions(ILoBullet lob) {
    return this;
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
    if (currentClock % 14 == 0) {
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
          new Ship(20, 0, new Utils().randomNumber(50, 250), 180.0, 5, Color.pink), this);
    }
    else {
      return new ConsLoShip(
          new Ship(20, 500, new Utils().randomNumber(50, 250), 0.0, 5, Color.pink), this);
    }
  }

  // checks if the bullet has hit any ships in this non empty list of ships
  public boolean anyHits(Bullet bullet) {
    return this.first.hit(bullet) || this.rest.anyHits(bullet);
  }

  // counts the number of ships that have collided with a bullet
  public int countCollisions(ILoBullet lob) {
    int count = 0;
    if (this.first.isCollided(lob)) {
      return count + 1;
    }
    return count + rest.countCollisions(lob);
  }

  // removes ships that have collided with a bullet
  public ILoShip removeCollisions(ILoBullet lob) {
    if (this.first.isCollided(lob)) {
      return this.rest.removeCollisions(lob);
    }
    else {
      return new ConsLoShip(this.first, this.rest.removeCollisions(lob));
    }

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

  public boolean collided(Ship ship) {
    return this.hit(ship);
  }

  // determines if this bullet is hitting any ships
  public boolean isColliding(ILoShip los) {
    return los.anyHits(this);
  }

  Bullet(int size, int x, int y, double direction, double velocity, Color color, int bulletRound) {
    super(size, x, y, direction, velocity, color);
    this.bulletRound = bulletRound;
  }

  // calculates the explosion angles if this bullet explodes
  public double explodeAngles() {
    return 360.00 / (this.bulletRound + 1);
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

  public boolean isCollided(ILoBullet lob) {
    return lob.anyHits(this);
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
    int collisionCount = this.loShips.countCollisions(this.loBullets);
    if (collisionCount > 0) {
      return new GameScene(this.bulletsLeft, this.destroyed + collisionCount,
          this.loShips.spawn(clock).removeCollisions(this.loBullets).moveLOS(),
          this.loBullets.moveLOB(this.loShips), this.random, this.clock + 1);
    }
    else {
      return new GameScene(this.bulletsLeft, this.destroyed, this.loShips.spawn(clock).moveLOS(),
          this.loBullets.moveLOB(this.loShips), this.random, this.clock + 1);
    }
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

  public WorldScene drawInfo(WorldScene ws) {
    return ws.placeImageXY(
        new TextImage("Score: " + this.destroyed + "   Bullets Left: " + this.bulletsLeft, 12,
            Color.black),
        80, 280);
  }

  public WorldEnd worldEnds() {
    if (bulletsLeft <= 0 && this.loBullets.isEmpty()) {
      return new WorldEnd(true, makeScene());
    }
    return new WorldEnd(false, new WorldScene(screenWidth, screenHeight));
  }

  // draw the ships and the bullets on to the new worldscene
  public WorldScene makeScene() {
    return this.drawInfo(this.loBullets.draw(this.loShips.draw(new WorldScene(500, 300))));
  }

}

class ExamplesNBullets {

  GameScene world = new GameScene();

  Bullet defaultBullet = new Bullet(2, 250, 300, 270, 8, Color.blue, 1);
  Bullet secondBullet = new Bullet(2, 250, 292, 270, 8, Color.blue, 1);
  Ship defaultShip = new Ship(2, 250, 300, 180, 8, Color.blue);
  Ship secondShip = new Ship(2, 258, 300, 180, 8, Color.blue);
  Ship missShip = new Ship(20, 250, 270, 180.0, 10.0, Color.blue);
  Ship hitShip = new Ship(20, 250, 279, 180.0, 10.0, Color.blue);
  Ship hitShip2 = new Ship(20, 250, 285, 180.0, 10.0, Color.blue);
  ILoBullet bList1 = new ConsLoBullet(defaultBullet,
      new ConsLoBullet(new Bullet(2, 250, 290, 180, 8, Color.blue, 1), new MtLoBullet()));
  ILoBullet bList2 = new ConsLoBullet(secondBullet,
      new ConsLoBullet(new Bullet(2, 258, 290, 180, 8, Color.blue, 1), new MtLoBullet()));
  ILoShip sList1 = new ConsLoShip(defaultShip,
      new ConsLoShip(new Ship(2, 250, 290, 180, 8, Color.blue), new MtLoShip()));
  ILoShip sList2 = new ConsLoShip(secondShip,
      new ConsLoShip(new Ship(2, 258, 290, 180, 8, Color.blue), new MtLoShip()));

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

  // test for distanceX
  boolean testDistanceX(Tester t) {
    return t.checkExpect(this.defaultBullet.distanceX(this.secondBullet), 0)
        && t.checkExpect(this.defaultShip.distanceX(this.secondShip), -8);
  }

  // test for distanceY
  boolean testDistanceY(Tester t) {
    return t
        .checkExpect(new Bullet(2, 250, 290, 270, 8, Color.blue, 1)
            .distanceY(new Bullet(2, 250, 298, 270, 8, Color.blue, 1)), -8)
        && t.checkExpect(new Ship(2, 250, 290, 270, 8, Color.blue)
            .distanceY(new Ship(2, 250, 298, 270, 8, Color.blue)), -8);
  }

  // test for move function
  boolean testMove(Tester t) {
    return t.checkExpect(this.defaultBullet.move(), new Bullet(2, 250, 292, 270, 8, Color.blue, 1))
        && t.checkExpect(this.secondBullet.move(), new Bullet(2, 250, 284, 270, 8, Color.blue, 1))
        && t.checkExpect(this.defaultShip.move(), this.secondShip);
  }

  // test for moveLOS
  boolean testMoveLOS(Tester t) {
    return t.checkExpect(this.sList1.moveLOS(), this.sList2)
        && t.checkExpect(new MtLoShip().moveLOS(), new MtLoShip());
  }

  // test for moveLOB
  boolean testMoveLOB(Tester t) {
    return t.checkExpect(this.bList1.moveLOB(new MtLoShip()), this.bList2)
        && t.checkExpect(new MtLoBullet().moveLOB(new MtLoShip()), new MtLoBullet());
  }

  // test for hit
  boolean testHit(Tester t) {
    return t.checkExpect(this.defaultBullet.hit(this.hitShip), true)
        && t.checkExpect(this.defaultBullet.hit(this.hitShip2), true)
        && t.checkExpect(this.defaultBullet.hit(this.missShip), false);
  }

  // TODO: test for anyHits
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

  // TODO: test for explodeHelper
  boolean testExplodeHelper(Tester t) {
    return true;
  }


  // test for isEmpty
  boolean testIsEmpty(Tester t) {
    return t.checkExpect(this.bList1.isEmpty(), false)
        && t.checkExpect(new MtLoBullet().isEmpty(), true);
  }

  // test for explodeAngles
  boolean testExplodeAngles(Tester t) {
    return t.checkExpect(new Bullet().explodeAngles(), 180.0)
        && t.checkExpect(new Bullet(20, 0, 0, 180.0, 10, Color.blue, 9).explodeAngles(), 36.0);
  }

  // test for explodeCount
  boolean testExplodeCount(Tester t) {
    return t.checkExpect(new Bullet().explodeCount(), 2)
        && t.checkExpect(new Bullet(20, 0, 0, 180.0, 10, Color.blue, 9).explodeCount(), 10);
  }

  // TODO: test for countCollisions
  boolean testCountCollisions(Tester t) {
    return true;
  }

  // TODO: test for removeCollisions
  boolean testRemoveCollisions(Tester t) {
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
  /*
   * boolean testBigBang(Tester t) {
   * GameScene game = new GameScene();
   * int worldWidth = 500;
   * int worldHeight = 300;
   * double tickRate = 1.0 / 40.0;
   * return game.bigBang(worldWidth, worldHeight, tickRate);
   * }
   */

}