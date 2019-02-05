import java.awt.Color; // general colors (as triples of red,green,blue values)

//and predefined colors (Red, Green, Yellow, Blue, Black, White)
// the abstract World class and the big-bang library
import javalib.funworld.WorldScene;
// world canvas library to draw objects on
import javalib.worldcanvas.WorldCanvas;
// images, like RectangleImage or OverlayImages
import javalib.worldimages.CircleImage;
import javalib.worldimages.LineImage;
import javalib.worldimages.OutlineMode;
import javalib.worldimages.OverlayImage;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;
// The tester library
import tester.Tester;

interface ITree {
  // draws this tree
  WorldImage draw();

  // determines if any branches are pointing downward
  boolean isDrooping();

  // rotates the tree by a given theta
  ITree rotateTree(double newTheta);

  // combines 2 trees
  ITree combine(int leftLength, int rightLength, double leftTheta, double rightTheta,
      ITree otherTree);

  // gets the left width of this tree
  double getLeftWidth();

  // gets the right width of this tree
  double getRightWidth();

  // gets the width of this tree
  double getWidth();
}

// represents a leaf of a tree
class Leaf implements ITree {
  int size; // represents the radius of the leaf
  Color color; // the color to draw it

  Leaf(int size, Color color) {
    this.size = size;
    this.color = color;
  }

  // draws a leaf
  public WorldImage draw() {
    return new CircleImage(this.size, OutlineMode.SOLID, this.color);
  }

  // determines if this leaf is drooping
  public boolean isDrooping() {
    return false;
  }

  // rotate this leaf
  public ITree rotateTree(double newTheta) {
    return this;
  }

  // combines this leaf with a tree
  public ITree combine(int leftLength, int rightLength, double leftTheta, double rightTheta,
      ITree otherTree) {
    return new Branch(leftLength, rightLength, leftTheta, rightTheta, this.rotateTree(leftTheta),
        otherTree.rotateTree(rightTheta));
  }

  // gets the width of the left half of this leaf
  public double getLeftWidth() {
    return this.size;
  }

  // gets the width of the right half of this leaf
  public double getRightWidth() {
    return this.size;
  }

  // gets the width of this
  public double getWidth() {
    // TODO Auto-generated method stub
    return this.getLeftWidth() + this.getRightWidth();
  }

}

// represents a stem of a tree
class Stem implements ITree {
  // How long this stick is
  int length;
  // The angle (in degrees) of this stem, relative to the +x axis
  double theta;
  // The rest of the tree
  ITree tree;

  Stem(int length, double theta, ITree tree) {
    this.length = length;
    this.theta = theta;
    this.tree = tree;
  }

  // draws a stem with its tree
  public WorldImage draw() {
    return new OverlayImage(this.tree.draw(),
        ((new LineImage(this.computePosn(), Color.BLUE))).movePinholeTo(this.computeNewPosn()))
            .movePinholeTo(posnToBeginning());
  }

  // computes the the coordinates of this stem
  public Posn computePosn() {
    return new Posn(((int) (Math.cos(Math.toRadians(this.theta)) * this.length)),
        (-(int) (Math.sin(Math.toRadians(this.theta)) * this.length)));
  }

  // computes the end of this stem
  public Posn computeNewPosn() {
    return new Posn(((int) (this.computePosn().x * 0.5)), ((int) (this.computePosn().y * 0.5)));
  }

  // finds the beginning of this stem
  public Posn posnToBeginning() {
    return new Posn(((int) (this.computePosn().x * -1)), ((int) (this.computePosn().y * -1)));
  }

  // returns if this Stem is drooping downward
  public boolean isDrooping() {
    return 180 < (this.theta % 360) || this.tree.isDrooping();
  }

  // rotate this stem
  public ITree rotateTree(double newTheta) {
    return new Stem(this.length, this.theta + newTheta, this.rotateTree(newTheta));
  }

  // combines this stem with a tree
  public ITree combine(int leftLength, int rightLength, double leftTheta, double rightTheta,
      ITree otherTree) {
    return new Branch(leftLength, rightLength, leftTheta, rightTheta, this.rotateTree(leftTheta),
        otherTree.rotateTree(rightTheta));
  }

  @Override
  public double getLeftWidth() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public double getRightWidth() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public double getWidth() {
    // TODO Auto-generated method stub
    return 0;
  }

}

// represents a branch of a tree
class Branch implements ITree {
  // How long the left and right branches are
  int leftLength;
  int rightLength;
  // The angle (in degrees) of the two branches, relative to the +x axis,
  double leftTheta;
  double rightTheta;
  // The remaining parts of the tree
  ITree left;
  ITree right;

  Branch(int leftLength, int rightLength, double leftTheta, double rightTheta, ITree left,
      ITree right) {
    this.leftLength = leftLength;
    this.rightLength = rightLength;
    this.leftTheta = leftTheta;
    this.rightTheta = rightTheta;
    this.left = left;
    this.right = right;
  }

  // draws this branch
  public WorldImage draw() {
    return new OverlayImage(new Stem(this.leftLength, this.leftTheta, left).draw(),
        new Stem(this.rightLength, this.rightTheta, right).draw());
  }

  // check if this branch has drooping arms or any of the trees it's connected to
  public boolean isDrooping() {
    return (180 < (this.rightTheta % 360)) || (180 < (this.leftTheta % 360))
        || this.right.isDrooping() || this.left.isDrooping();
  }

  // rotate this branch
  public ITree rotateTree(double newTheta) {
    return new Branch(this.leftLength, this.rightLength, this.leftTheta + newTheta,
        this.rightTheta + newTheta, this.left.rotateTree(newTheta),
        this.right.rotateTree(newTheta));
  }

  // combines this leaf with a tree
  public ITree combine(int leftLength, int rightLength, double leftTheta, double rightTheta,
      ITree otherTree) {
    return new Branch(leftLength, rightLength, leftTheta, rightTheta, this.rotateTree(leftTheta),
        otherTree.rotateTree(rightTheta));
  }

  @Override
  public double getLeftWidth() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public double getRightWidth() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public double getWidth() {
    // TODO Auto-generated method stub
    return 0;
  }

}

class ExamplesTrees {
  ITree tree1 = new Branch(30, 30, 135, 40, new Leaf(10, Color.RED), new Leaf(15, Color.BLUE));
  ITree TREE1 = this.tree1; // to match assignment naming
  ITree tree2 = new Branch(30, 30, 115, 65, new Leaf(15, Color.GREEN), new Leaf(8, Color.ORANGE));
  ITree TREE2 = this.tree2; // to match assignment naming
  ITree tree3 = new Branch(30, 30, 210, 65, new Leaf(15, Color.GREEN), new Leaf(8, Color.ORANGE));
  ITree branch1 = new Branch(40, 50, 150, 30, tree1, tree2);
  ITree branch2 = new Branch(40, 50, 180, 20, tree1, tree2);
  ITree branch3 = new Branch(40, 50, 180.1, 20, tree1, tree2);
  ITree branch4 = new Branch(40, 50, 170, 20, tree1, tree3);
  ITree leaf1 = new Leaf(5, Color.RED);
  ITree leaf2 = new Leaf(5, Color.BLUE);
  ITree stem1 = new Stem(50, 180, leaf1);

  // examples for combine test
  ITree combinet1ands1 = tree1.combine(100, 100, 150, 50, stem1);
  ITree combinedTreeExpected = new Branch(100, 100, 150, 50, tree1.rotateTree(150),
      stem1.rotateTree(50));

  // to test draw method
  boolean testDrawTree(Tester t) {
    WorldCanvas c = new WorldCanvas(500, 500);
    WorldScene s = new WorldScene(500, 500);
    return c.drawScene(
        s.placeImageXY((this.leaf1.combine(30, 30, 30, 150, this.leaf2)).draw(), 250, 250))
        && c.show();
  }

  // tests for combine
  boolean testCombine(Tester t) {
    return t.checkExpect(combinet1ands1, combinedTreeExpected);
  }

  // tests for isDrooping
  boolean testIsDrooping(Tester t) {
    return t.checkExpect(this.leaf1.isDrooping(), false)
        && t.checkExpect(this.branch1.isDrooping(), false)
        && t.checkExpect(this.branch2.isDrooping(), false)
        && t.checkExpect(this.branch3.isDrooping(), true)
        && t.checkExpect(this.branch4.isDrooping(), true);
  }

}