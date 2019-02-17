// bagel recipe

import tester.Tester;

// utils class to check validity of inputs
class Utils {
  /*
   * Template
   * Fields:
   * Methods:
   * checkIngredient ... double
   * approxEqual ... boolean
   * Methods of fields:
   */
  double checkIngredient(double amt1, double amt2, String error) {
    if (approxEqual(amt1, amt2)) {
      return amt1;
    }
    else {
      throw new IllegalArgumentException(error);
    }
  }

  // calculates if two numbers are approximately equal, up to 0.001
  boolean approxEqual(double first, double second) {
    return (Math.abs(first - second) < 0.001);
  }
}

// represents a recipe to make bagels
class BagelRecipe {
  double flour;
  double water;
  double yeast;
  double malt;
  double salt;

  // main constructor with all arguments and enforces proportional weights
  BagelRecipe(double flour, double water, double yeast, double salt, double malt) {
    this.flour = new Utils().checkIngredient(flour, water,
        "You should have the same amounts of flour and water.");
    this.water = new Utils().checkIngredient(water, flour,
        "You should have the same amounts of flour and water.");
    this.yeast = new Utils().checkIngredient(yeast, malt,
        "You should have the same amounts of yeast and malt.");
    this.salt = (new Utils().checkIngredient((salt + yeast) * 20, water,
        "Salt and yeast should be 1/20th the weight of flour.") / 20) - yeast;
    this.malt = new Utils().checkIngredient(malt, yeast,
        "You should have the same amounts of yeast and malt.");

  }

  /*
   * Template
   * Fields:
   * flour ... double
   * water ... double
   * yeast ... double
   * malt ... double
   * salt ... double
   * Methods:
   * Methods of fields:
   */

  // only takes in flour and yeast
  BagelRecipe(double flour, double yeast) {
    this(flour, flour, yeast, flour / 20 - yeast, yeast);
  }

  // constructor with volumes
  BagelRecipe(double flour, double yeast, double salt) {
    this(flour * 4.25, flour * 4.25, (yeast / 48) * 5, (salt / 48) * 10, (yeast / 48) * 5);
  }

  // determines if the given recipe is the same as this recipe
  boolean sameRecipe(BagelRecipe other) {
    return new Utils().approxEqual(this.flour, other.flour)
        && new Utils().approxEqual(this.water, other.water)
        && new Utils().approxEqual(this.yeast, other.yeast)
        && new Utils().approxEqual(this.malt, other.malt)
        && new Utils().approxEqual(this.salt, other.salt);
  }

}

// campus tour

import tester.Tester;

// a campus tour
class CampusTour {
  int startTime; // minutes from midnight
  ITourLocation startingLocation;

  CampusTour(int startTime, ITourLocation startingLocation) {
    this.startTime = startTime;
    this.startingLocation = startingLocation;
  }

  /*
   * TEMPLATE:
   * Fields:
   * this.startTime ... int
   * this.startingLocation ... ITourLocation
   * Methods:
   * this.sameTour(CampusTour other) ... boolean
   * Methods for fields:
   * this.sameTourLocation(ItourLocation other) ... boolean
   */

  // is this tour the same tour as the given one?
  boolean sameTour(CampusTour other) {
    return this.startTime == other.startTime
        && this.startingLocation.sameTourLocation(other.startingLocation);
  }
}

// a spot on the tour
interface ITourLocation {

  // determines if this ITourLocation is the same as that ITourLocation
  boolean sameTourLocation(ITourLocation other);

  // determines if this ITourLocation is the same as that TourEnd
  boolean sameTourEnd(TourEnd other);

  // determines if this ITourLocation is the same as that Mandatory
  boolean sameMandatory(Mandatory other);

  // determines if this ITourLocation is the same as that BranchingTour
  boolean sameBranchingTour(BranchingTour other);
}

// abstract class which implements ITourLocation
abstract class ATourLocation implements ITourLocation {
  String speech; // the speech to give at this spot on the tour

  ATourLocation(String speech) {
    this.speech = speech;
  }

  // determines if this tourLocation is the same as that tourLocation
  public abstract boolean sameTourLocation(ITourLocation other);

  // determines if this TourLocation is the same as that tourEnd
  public boolean sameTourEnd(TourEnd other) {
    return false;
  }

  // determines if this TourLocation is the same as that Mandatory
  public boolean sameMandatory(Mandatory other) {
    return false;
  }

  // determines if this ITourLocation is the same as that BranchingTour
  public boolean sameBranchingTour(BranchingTour other) {
    return false;
  }
}

// the end of the tour
class TourEnd extends ATourLocation {
  ICampusLocation location;

  TourEnd(String speech, ICampusLocation location) {
    super(speech);
    this.location = location;
  }

  /*
   * TEMPLATE:
   * Fields:
   * this.speech ... String
   * this.location ... ICampusLocation
   * Methods:
   * this.sameTourLocation(ITourLocation other) ... boolean
   * this.sameTourEnd(TourEnd other) ... boolean
   * this.sameMandatory(Mandatory other) ... boolean
   * this.sameBranchingTour(BranchingTour other) ... boolean
   * Methods for fields:
   * this.sameTourEnd(TourEnd other) ... boolean
   * this.sameCampusLocation(ICampusLocation other) ... boolean
   */

  // determines if this TourEnd is the same as the other ITourLocation
  public boolean sameTourLocation(ITourLocation other) {
    return other.sameTourEnd(this);
  }

  // determines if this TourEnd is the same as that TourEnd
  public boolean sameTourEnd(TourEnd other) {
    return this.speech.equals(other.speech) && this.location.sameCampusLocation(other.location);
  }
}

// a mandatory spot on the tour with the next place to go
class Mandatory extends ATourLocation {
  ICampusLocation location;
  ITourLocation next;

  Mandatory(String speech, ICampusLocation location, ITourLocation next) {
    super(speech);
    this.location = location;
    this.next = next;
  }

  /*
   * TEMPLATE:
   * Fields:
   * this.speech ... String
   * this.location ... ICampusLocation
   * this.next ... ITourLocation
   * Methods:
   * this.sameTourLocation(ITourLocation other) ... boolean
   * this.sameTourEnd(TourEnd other) ... boolean
   * this.sameMandatory(Mandatory other) ... boolean
   * this.sameBranchingTour(BranchingTour other) ... boolean
   * Methods for fields:
   * this.sameMandatory(Mandatory other) ... boolean
   * this.sameCampusLocation(ICampusLocation other) ... boolean
   * this.sameTourLocation(ITourLocation other) ... boolean
   */

  // determines if this Mandatory is the same as that ITourLocation
  public boolean sameTourLocation(ITourLocation other) {
    return other.sameMandatory(this);
  }

  // determines if this TourLocation is the same as that Mandatory
  public boolean sameMandatory(Mandatory other) {
    return this.speech.equals(other.speech) && this.location.sameCampusLocation(other.location)
        && this.next.sameTourLocation(other.next);
  }
}

// up to the tour guide where to go next
class BranchingTour extends ATourLocation {
  ITourLocation option1;
  ITourLocation option2;

  BranchingTour(String speech, ITourLocation option1, ITourLocation option2) {
    super(speech);
    this.option1 = option1;
    this.option2 = option2;
  }

  /*
   * TEMPLATE:
   * Fields:
   * this.speech ... String
   * this.option1 ... ITourLocation
   * this.option2 ... ITourLocation
   * Methods:
   * this.sameTourLocation(ITourLocation other) ... boolean
   * this.sameTourEnd(TourEnd other) ... boolean
   * this.sameMandatory(Mandatory other) ... boolean
   * this.sameBranchingTour(BranchingTour other) ... boolean
   * Methods for fields:
   * this.sameBranchingTour(BranchingTour other) ... boolean
   * this.sameTourLocation(ITourLocation other) ... boolean
   */

  // determines if this BranchingTour is the same as that ITourLocation
  public boolean sameTourLocation(ITourLocation other) {
    return other.sameBranchingTour(this);
  }

  // determines if this BranchingTour is the same as that BranchingTour
  public boolean sameBranchingTour(BranchingTour other) {
    return this.speech.equals(other.speech) && ((this.option1.sameTourLocation(other.option1)
        && this.option2.sameTourLocation(other.option2))
        || (this.option1.sameTourLocation(other.option2)
            && this.option2.sameTourLocation(other.option1)));
  }
}

// represents a campus location
interface ICampusLocation {

  // is this Building the same as that Building?
  boolean sameBuilding(Building other);

  // is this Quad the same as the that Quad?
  boolean sameQuad(Quad other);

  // is this CampusLocation the same as that CampusLocation?
  boolean sameCampusLocation(ICampusLocation other);

}

// represents a building
class Building implements ICampusLocation {
  String name;
  Address address;

  Building(String name, Address address) {
    this.name = name;
    this.address = address;
  }

  /*
   * TEMPLATE:
   * Fields:
   * this.name ... String
   * this.address ... Address
   * Methods:
   * this.sameBuilding(Building other) ... boolean
   * this.sameQuad(Quad other) ... boolean
   * this.sameCampusLocation(ICampusLocation other) ... boolean
   * Methods for fields:
   * this.sameBuilding(Building other) ... boolean
   */

  // is this Building the same as that Building?
  public boolean sameBuilding(Building other) {
    return this.name.equals(other.name) && this.address.sameAddress(other.address);
  }

  // is this Building the same as the that Quad?
  public boolean sameQuad(Quad other) {
    return false;
  }

  // is this CampusLocation the same as that CampusLocation?
  public boolean sameCampusLocation(ICampusLocation other) {
    return other.sameBuilding(this);
  }
}

// represents an address
class Address {
  String street;
  int number;

  Address(String street, int number) {
    this.number = number;
    this.street = street;
  }

  /*
   * TEMPLATE:
   * Fields:
   * this.street ... String
   * this.number ... int
   * Methods:
   * this.sameAddress(Address other) ... boolean
   */

  // checks equality of Addresses
  public boolean sameAddress(Address other) {
    return this.street.equals(other.street) && this.number == other.number;
  }
}

// represents a quad
class Quad implements ICampusLocation {
  String name;
  ILoCampusLocation surroundings; // in clockwise order, starting north

  Quad(String name, ILoCampusLocation surroundings) {
    this.name = name;
    this.surroundings = surroundings;
  }

  /*
   * TEMPLATE:
   * Fields:
   * this.name ... String
   * this.surroundings ... ILoCampusLocation
   * Methods:
   * this.sameBuilding(Building other) ... boolean
   * this.sameQuad(Quad other) ... boolean
   * this.sameCampusLocation(ICampusLocation other) ... boolean
   * Methods for fields:
   * this.sameILoCampusLocation(ILoCampusLocation that) ... boolean
   * this.sameQuad(Quad other) ... boolean
   */

  // is this Building the same as that Building?
  public boolean sameBuilding(Building other) {
    return false;
  }

  // is this Quad the same as the that Quad?
  public boolean sameQuad(Quad other) {
    return this.name.equals(other.name)
        && this.surroundings.sameILoCampusLocation(other.surroundings);
  }

  // is this CampusLocation the same as that CampusLocation?
  public boolean sameCampusLocation(ICampusLocation other) {
    return other.sameQuad(this);
  }

}

// represents a list of campus locations
interface ILoCampusLocation {

  // is this ILoCampusLocation the same as that ILoCampusLocation?
  boolean sameILoCampusLocation(ILoCampusLocation that);

  // is this ILoCampusLocation the same as that MtLoCampusLocation?
  boolean sameMtLoCampusLocation(MtLoCampusLocation that);

  // is this ILoCampusLocation the same as that ConsLoCampusLocation?
  boolean sameConsLoCampusLocation(ConsLoCampusLocation that);

}

// represents an empty list of campus locations
class MtLoCampusLocation implements ILoCampusLocation {

  /*
   * TEMPLATE:
   * Methods:
   * this.sameILoCampusLocation(ILoCampusLocation that) ... boolean
   * this.sameMtLoCampusLocation(MtLoCampusLocation that) ... boolean
   * this.sameConsLoCampusLocation(ConsLoCampusLocation that) ... boolean
   * Methods for fields:
   * this.sameMtLoCampusLocation(MtLoCampusLocation that) ... boolean
   */

  // is this ILoCampusLocation the same as that ILoCampusLocation?
  public boolean sameILoCampusLocation(ILoCampusLocation that) {
    return that.sameMtLoCampusLocation(this);
  }

  // is this ILoCampusLocation the same as that MtLoCampusLocation?
  public boolean sameMtLoCampusLocation(MtLoCampusLocation that) {
    return true;
  }

  // is this ILoCampusLocation the same as that ConsLoCampusLocation?
  public boolean sameConsLoCampusLocation(ConsLoCampusLocation that) {
    return false;
  }

}

// represents a list of campus locations
class ConsLoCampusLocation implements ILoCampusLocation {
  ICampusLocation first;
  ILoCampusLocation rest;

  ConsLoCampusLocation(ICampusLocation first, ILoCampusLocation rest) {
    this.first = first;
    this.rest = rest;
  }

  /*
   * TEMPLATE:
   * Methods:
   * this.sameILoCampusLocation(ILoCampusLocation that) ... boolean
   * this.sameMtLoCampusLocation(MtLoCampusLocation that) ... boolean
   * this.sameConsLoCampusLocation(ConsLoCampusLocation that) ... boolean
   * Methods for fields:
   * this.sameConsLoCampusLocation(ConsLoCampusLocation that) ... boolean
   * this.sameCampusLocation(ICampusLocation other) ... boolean
   * this.sameILoCampusLocation(ILoCampusLocation that) ... boolean
   */

  // is this ILoCampusLocation the same as that ILoCampusLocation?
  public boolean sameILoCampusLocation(ILoCampusLocation that) {
    return that.sameConsLoCampusLocation(this);
  }

  // is this ILoCampusLocation the same as that MtLoCampusLocation?
  public boolean sameMtLoCampusLocation(MtLoCampusLocation that) {
    return false;
  }

  // is this ILoCampusLocation the same as that ConsLoCampusLocation?
  public boolean sameConsLoCampusLocation(ConsLoCampusLocation that) {
    return this.first.sameCampusLocation(that.first) && this.rest.sameILoCampusLocation(that.rest);
  }

}


// ITrees

import java.awt.Color;
import javalib.worldimages.*;
import tester.*;

interface ITree {

  // draws this tree as an image
  WorldImage draw();

  // returns whether any twigs (stems or branches) are pointing downward
  boolean isDrooping();

  //takes the current tree and a given tree and produces a Branch using the given arguments
  ITree combine(int leftLength, int rightLength, double leftTheta, double rightTheta,
      ITree otherTree);

  // returns a singular tree to be combined
  ITree combineHelp(double theta);

  // computes the width of this tree
  double getWidth();

  // calculates the total width of the left side
  // (acc: keeps track of the total width of left side)
  double getWidthLeft(double acc);


  // calculates the total width of the right side
  // (acc: keeps track of the total width of right side)
  double getWidthRight(double acc);
}

// to represent a leaf
class Leaf implements ITree {

  int size; // represents the radius of the leaf
  Color color; // the color to draw it

  Leaf(int size, Color color) {
    this.size = size;
    this.color = color;
  }

  /* TEMPLATE
   * Fields:
   * this.size ... int
   * this.color ... Color
   * Methods:
   * this.draw() ... WorldImage
   * this.isDrooping() ... boolean
   * this.combine(int leftLength, int rightLength, double leftTheta, double rightTheta,
   * ITree otherTree) ... ITree
   * this.combineHelp(double theta) ... ITree
   */

  // draws this leaf as an image
  public WorldImage draw() {
    return new CircleImage(this.size, OutlineMode.SOLID, this.color);
  }

  // returns whether any twigs (stems or branches) are pointing downward
  public boolean isDrooping() {
    return false;
  }

  // takes the current tree and a given tree and produces a Branch using the given arguments
  public ITree combine(int leftLength, int rightLength,
      double leftTheta, double rightTheta, ITree otherTree) {
    return new Branch(leftLength, rightLength, leftTheta, rightTheta,
        this.combineHelp(leftTheta), otherTree.combineHelp(rightTheta));
  }

  // returns a singular tree to be combined
  public ITree combineHelp(double theta) {
    return this;
  }

  // computes the width of this tree
  public double getWidth() {
    return this.size * 2;
  }

  // calculates the total width of the left side
  // (acc: keeps track of the total width of left side)
  public double getWidthLeft(double acc) {
    return this.size + acc;
  }

  // calculates the total width of the right side
  // (acc: keeps track of the total width of right side)
  public double getWidthRight(double acc) {
    return this.size + acc;
  }
}

// to represent a stem
class Stem implements ITree {
  int length; // How long this stick is
  double theta; // The angle (in degrees) of this stem, relative to the +x axis
  ITree tree; // The rest of the tree


  Stem(int length, double theta, ITree tree) {
    this.length = length;
    this.theta = theta;
    this.tree = tree;
  }

  /* TEMPLATE
   * Fields:
   * this.length ... int
   * this.theta ... double
   * this.tree ... ITree
   * Methods:
   * this.draw() ... WorldImage
   * this.isDrooping() ... boolean
   * this.combine(int leftLength, int rightLength, double leftTheta, double rightTheta,
   * ITree otherTree) ... ITree
   * this.combineHelp(double theta) ... ITree
   * this.getWidth() ... double
   * this.getWidthLeft(double acc) ... double
   * this.getWidthRight(double acc) ... double
   * Methods for fields:
   * this.draw() ... WorldImage
   * this.isDrooping() ... boolean
   * this.combineHelp(double theta) ... ITree
   * this.getWidthLeft(double acc) ... double
   * this.getWidthRight(double acc) ... double
   */

  // draws this stem as an image
  public WorldImage draw() {
    double width = (- this.length) * Math.cos(Math.toRadians(this.theta));
    double height = this.length * Math.sin(Math.toRadians(this.theta));

    WorldImage image = new LineImage(new Posn((int)width, (int)height),
        Color.red).movePinhole(width / 2, height / 2);
    WorldImage tree = this.tree.draw().movePinhole(width, height);
    return new OverlayImage(tree, image);
  }

  // returns whether any stems are pointing downward
  public boolean isDrooping() {
    double angle = this.theta % 360;
    return (180 < angle && angle < 360) || this.tree.isDrooping();
  }

  // takes the current tree and a given tree and produces a Branch using the given arguments
  public ITree combine(int leftLength, int rightLength,
      double leftTheta, double rightTheta, ITree otherTree) {
    return new Branch(leftLength, rightLength, leftTheta, rightTheta,
        this.combineHelp(leftTheta), otherTree.combineHelp(rightTheta));
  }

  // returns a singular stem to be combined
  public ITree combineHelp(double theta) {
    return new Stem(this.length, this.theta - 90 + theta, this.tree.combineHelp(theta));
  }

  // computes the width of this tree
  public double getWidth() {
    return Math.max(this.getWidthLeft(0), 0) + Math.max(0, this.getWidthRight(0));
  }

  // calculates the total width of the left side
  // (acc: keeps track of the total width of left side)
  public double getWidthLeft(double acc) {
    double widthLeft = this.length * Math.cos(Math.toRadians(this.theta));
    return Math.max(this.tree.getWidthLeft(acc) + acc - widthLeft, acc - widthLeft);
  }

  // calculates the total width of the right side
  // (acc: keeps track of the total width of right side)
  public double getWidthRight(double acc) {
    double widthRight = this.length * Math.cos(Math.toRadians(this.theta));
    return Math.max(this.tree.getWidthLeft(acc) + acc + widthRight, acc + widthRight);
  }
}

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

  Branch(int leftLength, int rightLength, double leftTheta,
      double rightTheta, ITree left, ITree right) {
    this.leftLength = leftLength;
    this.rightLength = rightLength;
    this.leftTheta = leftTheta;
    this.rightTheta = rightTheta;
    this.left = left;
    this.right = right;
  }

  /* TEMPLATE
   * Fields:
   * this.leftLength ... int
   * this.rightLength ... int
   * this.leftTheta ... double
   * this.rightTheta ... double
   * this.left ... ITree
   * this.right ... ITree
   * Methods:
   * this.draw() ... WorldImage
   * this.isDrooping() ... boolean
   * this.combine(int leftLength, int rightLength, double leftTheta, double rightTheta,
   * ITree otherTree) ... ITree
   * this.combineHelp(double theta) ... ITree
   * this.getWidth() ... double
   * this.getWidthLeft(double acc) ... double
   * this.getWidthRight(double acc) ... double
   * Methods for fields:
   * this.isDrooping() ... boolean
   * this.combineHelp(double theta) ... ITree
   * this.getWidthLeft(double acc) ... double
   * this.getWidthRight(double acc) ... double
   */

  // draws this branch as an image
  public WorldImage draw() {
    double leftWidth = (- this.leftLength) * Math.cos(Math.toRadians(this.leftTheta));
    double rightWidth = (- this.rightLength) * Math.cos(Math.toRadians(this.rightTheta));
    double leftHeight = this.leftLength * Math.sin(Math.toRadians(this.leftTheta));
    double rightHeight = this.rightLength * Math.sin(Math.toRadians(this.rightTheta));

    Posn leftPosn = new Posn((int)leftWidth, (int)leftHeight);
    Posn rightPosn = new Posn((int)rightWidth, (int)rightHeight);
    WorldImage leftBranch = new LineImage(leftPosn, Color.BLUE).movePinhole(leftWidth / 2,
        leftHeight / 2);
    WorldImage rightBranch = new LineImage(rightPosn, Color.green).movePinhole(rightWidth / 2,
        rightHeight / 2);
    WorldImage left = this.left.draw().movePinhole(leftWidth, leftHeight);
    WorldImage right = this.right.draw().movePinhole(rightWidth, rightHeight);
    WorldImage leftImage = new OverlayImage(left, leftBranch);
    WorldImage rightImage = new OverlayImage(right, rightBranch);

    return new OverlayImage(leftImage, rightImage);
  }

  // returns whether any branches are pointing downward
  public boolean isDrooping() {
    double angleLeft = this.leftTheta % 360;
    double angleRight = this.rightTheta % 360;
    return ((180.0 < angleLeft) && (angleLeft < 360.0))
        || ((180.0 < angleRight) && (angleRight < 360.0))
        || this.left.isDrooping()
        || this.right.isDrooping();
  }

  // takes the current tree and a given tree and produces a Branch using the given arguments
  public ITree combine(int leftLength, int rightLength,
      double leftTheta, double rightTheta, ITree otherTree) {
    return new Branch(leftLength, rightLength, leftTheta, rightTheta, this.combineHelp(leftTheta),
        otherTree.combineHelp(rightTheta));
  }

  // returns a singular branch to be combined
  public ITree combineHelp(double theta) {
    return new Branch(this.leftLength, this.rightLength, this.leftTheta - 90 + theta,
        this.rightTheta - 90 + theta, this.left.combineHelp(theta), this.right.combineHelp(theta));
  }

  // computes the width of this tree
  public double getWidth() {
    return Math.max(0, this.getWidthLeft(0))
        + Math.max(0, this.getWidthRight(0));
  }

  // calculates the total width of the left side
  // (acc: keeps track of the total width of left side)
  public double getWidthLeft(double acc) {
    double leftWidth = this.leftLength * Math.cos(Math.toRadians(this.leftTheta));
    double rightWidth = this.rightLength * Math.cos(Math.toRadians(this.rightTheta));
    double leftMax = Math.max(this.left.getWidthLeft(acc) + acc - leftWidth, acc - leftWidth);
    double rightMax = Math.max(this.right.getWidthLeft(acc) + acc - rightWidth, acc - rightWidth);
    return Math.max(leftMax, rightMax);
  }

  // calculates the total width of the right side
  // (acc: keeps track of the total width of right side)
  public double getWidthRight(double acc) {
    double leftWidth = this.leftLength * Math.cos(Math.toRadians(this.leftTheta));
    double rightWidth = this.rightLength * Math.cos(Math.toRadians(this.rightTheta));
    double leftMax = Math.max(this.left.getWidthRight(acc) + acc + leftWidth, acc + leftWidth);
    double rightMax = Math.max(this.right.getWidthRight(acc) + acc + rightWidth, acc + rightWidth);
    return Math.max(leftMax, rightMax);
  }
}

// media.java

import tester.*;

// a piece of media
interface IMedia {

  // is this media really old?
  boolean isReallyOld();

  // are captions available in this language?
  boolean isCaptionAvailable(String language);

  // a string showing the proper display of the media
  String format();
}

abstract class AMedia implements IMedia {
  String title;
  ILoString captionOptions;

  AMedia(String title, ILoString captionOptions) {
    this.title = title;
    this.captionOptions = captionOptions;
  }

  // is this media really old?
  public boolean isReallyOld() {
    return false;
  }

  // are captions available in this language?
  public boolean isCaptionAvailable(String language) {
    return this.captionOptions.isCaptionAvailableHelp(language);
  }

  // a string showing the proper display of the media
  public abstract String format();
}

// represents a movie
class Movie extends AMedia {
  int year;

  Movie(String title, int year, ILoString captionOptions) {
    super(title, captionOptions);
    this.year = year;
  }

  /* TEMPLATE
   * Fields:
   * this.title ... String
   * this.year ... year
   * this.captionOptions ... ILoString
   * Methods:
   * this.isReallyOld() ... boolean
   * this.isCaptionAvailable(String language) ... boolean
   * this.format() ... String
   * Methods for fields:
   * this.isCaptionAvailableHelp(String language) ... boolean
   */

  // is this media really old?
  public boolean isReallyOld() {
    return this.year < 1930;
  }

  // a string showing the proper display of the media
  public String format() {
    return this.title + " (" + this.year + ")";
  }
}

// represents a TV episode
class TVEpisode extends AMedia {
  String showName;
  int seasonNumber;
  int episodeOfSeason;

  TVEpisode(String title, String showName, int seasonNumber, int episodeOfSeason,
      ILoString captionOptions) {
    super(title, captionOptions);
    this.showName = showName;
    this.seasonNumber = seasonNumber;
    this.episodeOfSeason = episodeOfSeason;
  }

  /* TEMPLATE
   * Fields:
   * this.title ... String
   * this.showName ... String
   * this.seasonNumber ... int
   * this.episodeOfSeason ... int
   * this.captionOptions ... ILoString
   * Methods:
   * this.isReallyOld() ... boolean
   * this.isCaptionAvailable(String language) ... boolean
   * this.format() ... String
   * Methods for fields:
   * this.isCaptionAvailableHelp(String language) ... boolean
   */

  // a string showing the proper display of the media
  public String format() {
    return this.showName + " " + this.seasonNumber + "." + this.episodeOfSeason + " - "
        + this.title;
  }
}

// represents a YouTube video
class YTVideo extends AMedia {
  String channelName;

  public YTVideo(String title, String channelName, ILoString captionOptions) {
    super(title, captionOptions);
    this.channelName = channelName;
  }

  // a string showing the proper display of the media
  public String format() {
    return this.title + " by " + this.channelName;
  }

  /* TEMPLATE
   * Fields:
   * this.title ... String
   * this.channelName ... String
   * this.captionOptions ... ILoString
   * Methods:
   * this.isReallyOld() ... boolean
   * this.isCaptionAvailable(String language) ... boolean
   * this.format() ... String
   * Methods for fields:
   * this.isCaptionAvailableHelp(String language) ... boolean
   */

}

// lists of strings
interface ILoString {

  // computes if the caption is available
  boolean isCaptionAvailableHelp(String language);

}

// an empty list of strings
class MtLoString implements ILoString {

  // computes if the caption is available in the MtLoString
  public boolean isCaptionAvailableHelp(String language) {
    return false;
  }
}

// a non-empty list of strings
class ConsLoString implements ILoString {
  String first;
  ILoString rest;

  ConsLoString(String first, ILoString rest) {
    this.first = first;
    this.rest = rest;
  }

  // computes if the caption is available in the ConsLoString
  public boolean isCaptionAvailableHelp(String language) {
    if (this.first.equals(language)) {
      return true;
    }
    else {
      return this.rest.isCaptionAvailableHelp(language);
    }
  }
}

// pictures.java


import tester.Tester;

// represents a picture
interface IPicture {
  // computes the width of this picture
  int getWidth();

  // computes the number of shapes in this picture
  int countShapes();

  // computes how deeply operations are nested in the construction of this picture
  int comboDepth();

  // reflects the picture down the middle like a mirror
  IPicture mirror();

  // produces a String representing the contents of this picture, and the steps
  // to produce it
  String pictureRecipe(int depth);
}

// represents an operation
interface IOperation {
  // computes the total width of this Operation
  int widthOfOperation();

  // computes the count of shapes in this Operation
  int shapeCountOfOperation();

  // computes the combo depth of this Operation
  int comboDepthOfOperation();

  // mirrors the picture created by this operation
  IOperation mirrorOperation();

  // returns the recipe of this operation given a starting name and depth
  String operationRecipe(int depth);

}

// represents a shape
class Shape implements IPicture {
  String kind; // the type of shape
  int size; // the size of the shape

  Shape(String kind, int size) {
    this.kind = kind;
    this.size = size;
  }
  /* TEMPLATE
   * FIELDS
   * this.kind ... String
   * this.size ... int
   * METHODS
   * this.getWidth() ... int
   * this.countShapes() ... int
   * this.comboDepth() ... int
   * this.mirror() ... IPicture
   * this.pictureRecipe(int depth) ... String
   */

  public int getWidth() {
    // computes the width of this Shape
    return this.size;
  }

  // computes the count of shapes in this Shape
  public int countShapes() {
    return 1;
  }

  // computes the combo depth of this Shape
  public int comboDepth() {
    return 0;
  }

  // mirrors this Shape across the middle
  public IPicture mirror() {
    return new Shape(this.kind, this.size);
  }

  // returns the recipe of this Shape at given depth
  public String pictureRecipe(int depth) {
    return this.kind;
  }

}

// represents a combo
class Combo implements IPicture {
  String name; // the name of the combo
  IOperation operation; // the operation that will take place

  Combo(String name, IOperation operation) {
    this.name = name;
    this.operation = operation;
  }
  /* TEMPLATE
   * FIELDS
   * this.name ... String
   * this.operation ... IOperation
   * METHODS
   * this.getWidth() ... int
   * this.countShapes() ... int
   * this.comboDepth() ... int
   * this.mirror() ... IPicture
   * this.pictureRecipe(int depth) ... String
   * METHODS FOR FIELDS
   * this.widthOfOperation() ... int
   * this.shapeCountOfOperation() ... int
   * this.comboDepthOfOperation() ... int
   * this.mirrorOperation() ... IOperation
   * this.operationRecipe(int depth) ... String
   */

  public int getWidth() {
    // computes the width of this Combo
    return this.operation.widthOfOperation();
  }

  public int countShapes() {
    return this.operation.shapeCountOfOperation();
  }

  // computes the combo depth of this Combo
  public int comboDepth() {
    return this.operation.comboDepthOfOperation();
  }

  // mirrors this combo across the middle
  public IPicture mirror() {
    return new Combo(this.name, this.operation.mirrorOperation());
  }

  // returns the recipe of a Combo
  public String pictureRecipe(int depth) {
    if (depth <= 0) {
      return this.name;
    }
    else {
      return this.operation.operationRecipe(depth);
    }
  }
}

// represents a scale operation
class Scale implements IOperation {
  IPicture picture; // a picture

  Scale(IPicture picture) {
    this.picture = picture;
  }

  /* TEMPLATE
   * FIELDS
   * this.picture ... IPicture
   * METHODS
   * this.widthOfOperation() ... int
   * this.shapeCountOfOperation() ... int
   * this.comboDepthOfOperation() ... int
   * this.mirrorOperation() ... IOperation
   * this.operationRecipe(int depth) ... String
   * METHODS FOR FIELDS
   * this.getWidth() ... int
   * this.countShapes() ... int
   * this.comboDepth() ... int
   * this.mirror() ... IPicture
   * this.pictureRecipe(int depth) ... String
   */

  // returns the width of this Scale operation
  public int widthOfOperation() {
    return 2 * this.picture.getWidth();
  }

  // computes the shape count of this Scale operation
  public int shapeCountOfOperation() {
    return this.picture.countShapes();
  }

  // computes the combo depth of this Scale operation
  public int comboDepthOfOperation() {
    return 1 + this.picture.comboDepth();
  }

  // mirrors this Scale operation
  public IOperation mirrorOperation() {
    return new Scale(this.picture.mirror());
  }

  // returns the operation recipe of this Scale operation
  public String operationRecipe(int depth) {
    if (depth == 1) {
      return "scale(" + this.picture.pictureRecipe(0) + ")";
    }
    return "scale(" + this.picture.pictureRecipe(depth - 1) + ")";
  }

}

// represents a beside operation
class Beside implements IOperation {
  IPicture picture1; // the picture on the left
  IPicture picture2; // the picture on the right

  Beside(IPicture picture1, IPicture picture2) {
    this.picture1 = picture1;
    this.picture2 = picture2;
  }

  /* TEMPLATE
   * FIELDS
   * this.picture1 ... IPicture
   * this.picture2 ... IPicture
   * METHODS
   * this.widthOfOperation() ... int
   * this.shapeCountOfOperations() ... int
   * this.comboDepthOfOperation() ... int
   * this.mirrorOperation() ... IOperation
   * this.operationRecipe(int depth) ... String
   * METHODS FOR FIELDS
   * this.getWidth() ... int
   * this.countShapes() ... int
   * this.comboDepth() ... int
   * this.mirror() ... IPicture
   * this.pictureRecipe(int depth) ... String
   */

  // computes the width of this Beside operation
  public int widthOfOperation() {
    return this.picture1.getWidth() + this.picture2.getWidth();
  }

  // computes the shape count of this Beside Operation
  public int shapeCountOfOperation() {
    return this.picture1.countShapes() + this.picture2.countShapes();
  }

  // computes the combo depth of this Beside operation
  public int comboDepthOfOperation() {
    return 1 + Math.max(this.picture1.comboDepth(), this.picture2.comboDepth());
  }

  // mirrors this Beside operation
  public IOperation mirrorOperation() {
    return new Beside(this.picture2.mirror(), this.picture1.mirror());
  }

  // returns the operation recipe of this Beside operation
  public String operationRecipe(int depth) {
    if (depth == 1) {
      return "beside(" + this.picture1.pictureRecipe(0) + ", " + this.picture2.pictureRecipe(0)
          + ")";
    }
    return "beside(" + this.picture1.pictureRecipe(depth - 1) + ", "
        + this.picture2.pictureRecipe(depth - 1) + ")";
  }
}

// represents an overlay operation
class Overlay implements IOperation {
  IPicture topPicture; // the picture on top
  IPicture bottomPicture; // the picture on bottom

  Overlay(IPicture topPicture, IPicture bottomPicture) {
    this.topPicture = topPicture;
    this.bottomPicture = bottomPicture;
  }

  /* TEMPLATE
   * FIELDS
   * this.topPicture ... IPicture
   * this.bottomPicture ... IPicture
   * METHODS
   * this.widthOfOperation() ... int
   * this.shapeCountOfOperations() ... int
   * this.comboDepthOfOperation() ... int
   * this.mirrorOperation() ... IOperation
   * this.operationRecipe(int depth) ... String
   * METHODS FOR FIELDS
   * this.getWidth() ... int
   * this.countShapes() ... int
   * this.comboDepth() ... int
   * this.mirror() ... IPicture
   * this.pictureRecipe(int depth) ... String
   */

  // computes the width of this Overlay operation
  public int widthOfOperation() {
    if (this.topPicture.getWidth() > this.bottomPicture.getWidth()) {
      return this.topPicture.getWidth();
    }
    else {
      return this.bottomPicture.getWidth();
    }
  }

  // computes the count of shapes of this Overlay operation
  public int shapeCountOfOperation() {
    return this.topPicture.countShapes() + this.bottomPicture.countShapes();
  }

  // computes the combo depth of this Overlay operation
  public int comboDepthOfOperation() {
    return 1 + Math.max(this.topPicture.comboDepth(), this.bottomPicture.comboDepth());
  }

  // mirrors this Overlay operation
  public IOperation mirrorOperation() {
    return new Overlay(this.topPicture.mirror(), this.bottomPicture.mirror());
  }

  // returns the operation recipe of this Overlay operation
  public String operationRecipe(int depth) {
    if (depth == 1) {
      return "overlay(" + this.topPicture.pictureRecipe(0) + ", "
          + this.bottomPicture.pictureRecipe(0) + ")";
    }
    return "overlay(" + this.topPicture.pictureRecipe(depth - 1) + ", "
        + this.bottomPicture.pictureRecipe(depth - 1) + ")";
  }
}

/// places. java

import tester.Tester;

// represents a list of features
interface ILoFeature {

  // calculates the capacity of venues from a list of features
  int capacityFromFeatures();

  // Calculates the total foodiness rating of all the features in a list
  double sumFoodinessRating();

  // counts the number of restaurants in a list of features
  int countRestaurants();

  // creates a restaurant info string from a list of features
  String restaurantInfoFromFeatures();
}

// represents a feature
interface IFeature {

  // calculates the capacity of a single feature
  int capacityFeature();

  // Calculates the total foodiness rating of this feature
  double foodinessRatingOfFeature();

  // calculates the number of restaurants in this feature
  int restaurantCountOfThisFeature();

  // computes the restaurant string of this feature
  String restaurantInfoOfThisFeature();
}

// represents a non-empty list of features
class ConsLoFeature implements ILoFeature {
  IFeature first; // the first feature in this list of features
  ILoFeature rest; // the rest of the features in this list of features


  ConsLoFeature(IFeature first, ILoFeature rest) {
    this.first = first;
    this.rest = rest;
  }

  /*
   * Template
   * Fields:
   * this.first ... IFeature
   * this.rest ... ILoFeature
   * Methods:
   * capacityFromFeatures ... int
   * sumFoodinessRating ... double
   * countRestaurants ... int
   * restaurantInfoFromFeatures ... String
   * Methods on Fields:
   * this.first.capacityFeature ... int
   * this.rest.capacityFeature ... int
   * this.first.foodiniessRatingOfFeature() ... double
   * this.rest.sumFoodinessRating() ... double
   * this.first.restaurantCountOfThisFeature ... int
   * this.rest.countRestaurants() ... int
   */

  // calculates the capacity of a non empty list of IFeatures
  public int capacityFromFeatures() {
    return this.first.capacityFeature() + this.rest.capacityFromFeatures();
  }

  // calculates the total foodiness ratings from all reachable restaurants
  public double sumFoodinessRating() {
    return this.first.foodinessRatingOfFeature() + this.rest.sumFoodinessRating();
  }

  // calculates the total number of reachable restaurants
  public int countRestaurants() {
    return this.first.restaurantCountOfThisFeature() + this.rest.countRestaurants();
  }

  // creates a restaurant string from a non empty list of features
  public String restaurantInfoFromFeatures() {
    return this.first.restaurantInfoOfThisFeature() + this.rest.restaurantInfoFromFeatures();
  }
}

// represents an empty list of features
class MtLoFeature implements ILoFeature {
  MtLoFeature() {

  }

  /*
   * Template
   * Fields:
   * this.first ... IFeature
   * this.rest ... ILoFeature
   * Methods:
   * capacityFromFeatures ... int
   * sumFoodinessRating ... double
   * countRestaurants ... int
   * restaurantInfoFromFeatures ... String
   */

  // calculates the capacity of a empty list of IFeatures
  public int capacityFromFeatures() {
    return 0;
  }

  // calculates the total foodiness ratings from all reachable restaurants in an empty list
  // of IFeatures
  public double sumFoodinessRating() {
    return 0.0;
  }

  // calculates the total number of reachable restaurants in an empty list of IFeatures
  public int countRestaurants() {
    return 0;
  }

  // creates a restaurant string from a empty list of features
  public String restaurantInfoFromFeatures() {
    return "";
  }
}

// represents a place
class Place {
  String name; // name of this place
  ILoFeature features; // a list of all the features that belong to this place

  Place(String name, ILoFeature features) {
    this.name = name;
    this.features = features;
  }
  /*
   * Template
   * Fields:
   * this.name ... String
   * this.features ... ILoFeature
   * Methods:
   * totalCapacity ... int
   * restaurantCountOfThisPlace ... int
   * sumfoodinessrating ... double
   * foodinessRating ... double
   * Methods on Fields
   * this.features.capacityFromFeatures ... int
   * this.features.countRestaurants ... int
   * this.features.sumFoodinessRating ... double
   */

  // computes the total available seating in all the Venues reachable from the current place
  int totalCapacity() {
    return this.features.capacityFromFeatures();
  }

  // calculates the number of restaurants in this place
  int restaurantCountOfThisPlace() {
    return this.features.countRestaurants();
  }

  // calculates the sum foodiness ratings of this place
  double sumFoodinessRatingOfThisPlace() {
    return this.features.sumFoodinessRating();
  }

  // computes the average rating of all restaurants reachable at the current place
  double foodinessRating() {
    if (this.features.countRestaurants() == 0) {
      return 0.0;
    }
    else {
      return this.features.sumFoodinessRating() / this.features.countRestaurants();
    }
  }

  // produces one String that has in it all names and types of restaurants reachable from
  // this place
  String restaurantInfo() {
    if (this.features.countRestaurants() == 0) {
      return "";
    }
    else {
      return this.features.restaurantInfoFromFeatures().substring(0,
          this.features.restaurantInfoFromFeatures().length() - 2);
    }
  }

  // produces the restaurant info of a place when it is accessed by a shuttle
  public String restaurantInfoOfThisPlace() {
    return this.features.restaurantInfoFromFeatures();
  }
}

// represents a class
class Restaurant implements IFeature {
  String name; // the name of this restaurant
  String type; // the kind of food this restaurant sells
  double averageRating; // the average customer rating of this restaurant

  Restaurant(String name, String type, double averageRating) {
    // name of this restaurant
    this.name = name;
    // what kind of food this restaurant sells
    this.type = type;
    // the average rating of this restaurant
    this.averageRating = averageRating;
  }

  /*
   * Template
   * Fields:
   * this.name ... String
   * this.type ... String
   * this.averageRating ... double
   * Methods:
   * capacityFeature ... int
   * foodinessRatingOfFeature ... double
   * restaurantCountOfThisFeature ... int
   */

  // calculates the capacity of this IFeature
  public int capacityFeature() {
    return 0;
  }

  // calculates the foodiness rating of this restaurant
  public double foodinessRatingOfFeature() {
    return this.averageRating;
  }

  // adds 1 to a count if this IFeature is a Restaurant
  public int restaurantCountOfThisFeature() {
    return 1;
  }

  // computes the restaurant string of this Restaurant
  public String restaurantInfoOfThisFeature() {
    return this.name + " (" + this.type + "), ";
  }
}

// represents a venue
class Venue implements IFeature {
  String name; // the name of this venue
  String type; // the type of this venue
  int capacity; // how many people this venue can hold


  Venue(String name, String type, int capacity) {
    this.name = name; // the name of this venue
    this.type = type; // what kind of venue this is
    this.capacity = capacity; // how many people this venue can hold
  }

  /*
   * Template
   * Fields:
   * this.name ... String
   * this.type ... String
   * this.capcity ... int
   * Methods:
   * capacityFeature ... int
   * foodinessRatingOfFeature ... double
   * restaurantCountOfThisFeature ... int
   */

  // calculates the capacity of a feature
  public int capacityFeature() {
    return this.capacity;
  }

  // calculates the foodiness rating of this Venue
  public double foodinessRatingOfFeature() {
    return 0.0;
  }

  // computes the count of restaurants in this feature
  public int restaurantCountOfThisFeature() {
    return 0;
  }

  // computes the restaurant info string of this Venue
  public String restaurantInfoOfThisFeature() {
    return "";
  }

}

// represents a shuttle bus
class ShuttleBus implements IFeature {
  String name; // the name of this shuttle bus
  Place destination; // the place where this shuttle drops you off

  ShuttleBus(String name, Place destination) {
    this.name = name;
    this.destination = destination;
  }

  /*
   * Template
   * Fields:
   * this.name ... String
   * this.destination ... Place
   * Methods:
   * capacityFeature ... int
   * foodinessRatingOfFeature ... double
   * restaurantCountOfThisFeature ... int
   * Methods on fields
   * this.destination.totalCapacity ... int
   * this.destination.sumFoodinessRatingOfThisPlace ... double
   * this.destination.restaurantCountOfThisPlace ... int
   */

  // calculates the capacity of this IFeature
  public int capacityFeature() {
    return this.destination.totalCapacity();
  }

  // calculates the foodiness rating of this IFeature
  public double foodinessRatingOfFeature() {
    return this.destination.sumFoodinessRatingOfThisPlace();
  }

  // adds 1 to a count if this IFeature is a Restaurant
  public int restaurantCountOfThisFeature() {
    return this.destination.restaurantCountOfThisPlace();
  }

  // computes the restaurant info string of this ShuttleBus
  public String restaurantInfoOfThisFeature() {
    return this.destination.restaurantInfoOfThisPlace();
  }
}

/*
 * This city is called Anville. Everything is about me, An. There are 3 places: AnSide
 * Galleria, An Station, An End. AnSide Galleria has a teriyaki restaurant named An Japan
 * that is rated 3.9 stars, a coffee shop named Anbucks that is rated 4.1 stars, and a
 * Shuttle called
 * An Bridge Shuttle that goes to An End. An Station has a Shuttle
 * called An Express that goes to An End, another Shuttle called An Cruiser that goes to
 * An Side. An End has a public venue called An Common that can hold 150,000 people.
 */


// roadtrip.java

import tester.Tester;

// a list of directions
interface ILoDirection {

  // returns a list of road trip chunks using an accumulator
  // accumulator - keeps track of distance traveled in this road trip chunk
  ILoRoadTripChunk splitUpTripAccumulator(String driver1, String driver2, int splitSize,
      int distanceTraveledInThisChunk, ILoDirection directionsOfThisChunk,
      ILoRoadTripChunk chunksSoFar);

  // reverses the directions
  ILoDirection reverseDirections(ILoDirection reversedSoFar);

  // prints out the directions
  void printDirections();

  // computes whether or not the list of directions is empty
  boolean isEmpty();

  // removes the last direction from the list of directions
  ILoDirection removeLastDirection();
}

interface ILoRoadTripChunk {

  // reverses a list of road trip chunks
  ILoRoadTripChunk reverseChunks(ILoRoadTripChunk reversedSoFar);

  // prints the road trip chunks
  void printChunks();


  ILoRoadTripChunk parseFinal();

  // removes the last driver switch from the list of road trip chunks
  ILoRoadTripChunk removeLastSwitch();

  // removes the last road trip chunk
  ILoRoadTripChunk removeLastChunk();

  // computes whether or not the list of road trip chunks is empty
  boolean isEmpty();
}

class ConsLoDirection implements ILoDirection {
  Direction first;
  ILoDirection rest;

  ConsLoDirection(Direction first, ILoDirection rest) {
    this.first = first;
    this.rest = rest;
  }

  /* TEMPLATE
   * Fields:
   * this.first ... Direction
   * this.rest ... ILoDirection
   * Methods:
   * this.splitUpTripAccumulator(String driver, String switchDriver, int splitSize,
      int distanceTraveledSoFar, ILoDirection directionsOfThisChunk, ILoRoadTripChunk chunksSoFar)
      ... ILoRoadTripChunk
   * this.reverseDirections(ILoDirection reversedSoFar) ... ILoDirection
   * this.printDirections() ... void
   * this.isEmpty() ... boolean
   * this.removeLastDirection() ... ILoDirection
   * Methods for fields:
   * this.splitUpTrimAccumulator(String driver1, String driver2, int splitSize,
      int distanceTraveledInThisChunk, ILoDirection directionsOfThisChunk,
      ILoRoadTripChunk chunksSoFar) ... ILoRoadTripChunk
   * this.reverseDirections(ILoDirection reversedSoFar) ... ILoDirection
   * this.printDirections() ... void
   * this.isEmpty() ... boolean
   * this.removeLastDirection() ... ILoDirection
   */

  // returns a list of road trip chunks using an accumulator
  // accumulator - keeps track of distance traveled in this road trip chunk
  public ILoRoadTripChunk splitUpTripAccumulator(String driver, String switchDriver, int splitSize,
      int distanceTraveledSoFar, ILoDirection directionsOfThisChunk, ILoRoadTripChunk chunksSoFar) {

    // if the switch will happen after this direction
    if (this.first.switchInThisDirection(splitSize, distanceTraveledSoFar) == 1) {
      return this.rest.splitUpTripAccumulator(driver, switchDriver, splitSize,
          this.first.addDirectionMiles(distanceTraveledSoFar),
          new ConsLoDirection(this.first, directionsOfThisChunk), chunksSoFar);

    } // if the switch will happen at the end of this direction
    else if (this.first.switchInThisDirection(splitSize, distanceTraveledSoFar) == 0) {
      return this.rest.splitUpTripAccumulator(switchDriver, driver, splitSize, 0,
          new MtLoDirection(),
          new ConsLoRoadTripChunk(new RoadTripChunk(driver,
              (new ConsLoDirection(new Direction("Switch with " + switchDriver, 0),
                  new ConsLoDirection(this.first, directionsOfThisChunk)))
                      .reverseDirections(new MtLoDirection())),
              chunksSoFar));

    } // if the switch will happen before this direction ends
    else if (this.first.switchInThisDirection(splitSize, distanceTraveledSoFar) == -1) {
      return (new ConsLoDirection(
          this.first.splitNewChunkDirection(splitSize, distanceTraveledSoFar),
          this.rest)).splitUpTripAccumulator(switchDriver, driver, splitSize, 0,
              new MtLoDirection(),
          new ConsLoRoadTripChunk(
              new RoadTripChunk(driver,
                  (new ConsLoDirection(new Direction("Switch with " + switchDriver,
                      splitSize - distanceTraveledSoFar),
                      directionsOfThisChunk)).reverseDirections(new MtLoDirection())),
                  chunksSoFar));
    }
    else {
      return new MtLoRoadTripChunk();
    }
  }

  // reverses the order directions in a ConsLoDirection
  public ILoDirection reverseDirections(ILoDirection reversedSoFar) {
    return this.rest.reverseDirections(new ConsLoDirection(this.first, reversedSoFar));
  }

  // prints the directions of a ConsLoDirection
  public void printDirections() {
    System.out.println(this.first.description + ", " + this.first.miles + "\n");
    this.rest.printDirections();
  }

  // returns whether or not the ConsLoDirection is empty
  public boolean isEmpty() {
    return false;
  }

  // removes the last direction of the ConsLoDirection
  public ILoDirection removeLastDirection() {
    if (this.rest.isEmpty()) {
      return this.rest;
    }
    else {
      return new ConsLoDirection(this.first, this.rest.removeLastDirection());
    }
  }
}

class MtLoDirection implements ILoDirection {
  MtLoDirection() {

  }

  /* TEMPLATE
   * Methods:
   * this.splitUpTripAccumulator(String driver, String switchDriver, int splitSize,
      int distanceTraveledSoFar, ILoDirection directionsOfThisChunk, ILoRoadTripChunk chunksSoFar)
      ... ILoRoadTripChunk
   * this.reverseDirections(ILoDirection reversedSoFar) ... ILoDirection
   * this.printDirections() ... void
   * this.isEmpty() ... boolean
   * this.removeLastDirection() ... ILoDirection
   */

  // splits up a roadTrip using an accumulator
  public ILoRoadTripChunk splitUpTripAccumulator(String driver, String switchDriver, int splitSize,
      int distanceTraveledSoFar, ILoDirection directionsOfThisChunk, ILoRoadTripChunk chunksSoFar) {
    return (new ConsLoRoadTripChunk(new RoadTripChunk(driver, directionsOfThisChunk), chunksSoFar)
        .reverseChunks(new MtLoRoadTripChunk()));
  }

  // reverses an empty list of directions
  public ILoDirection reverseDirections(ILoDirection reversedSoFar) {
    return reversedSoFar;
  }

  // prints directions of an empty list of direction
  public void printDirections() {
    System.out.println("");
  }

  // returns whether or not the empty list of direction is empty
  public boolean isEmpty() {
    return true;
  }

  // removes the last direction from the empty list of direction
  public ILoDirection removeLastDirection() {
    return this;
  }

}

class ConsLoRoadTripChunk implements ILoRoadTripChunk {
  RoadTripChunk first;
  ILoRoadTripChunk rest;

  ConsLoRoadTripChunk(RoadTripChunk first, ILoRoadTripChunk rest) {
    this.first = first;
    this.rest = rest;
  }

  /* TEMPLATE
   * Fields:
   * this.first ... RoadTripChunk
   * this.rest ... ILoRoadTripChunk
   * Methods:
   * this.reverseChunks(ILoRoadTripChunk reversedSoFar) ... ILoRoadTripChunk
   * this.printChunks() .. void
   * this.parseFinal() ... ILoRoadTripChunk
   * this.removeLastSwitch() ... ILoRoadTripChunk
   * this.removeLastChunk() ... ILoRoadTripChunk
   * this.isEmpty() ... boolean
   * Methods for fields:
   * this.reverseChunks(ILoRoadTripChunk reversedSoFar) ... ILoRoadTripChunk
   * this.printDirections() .. void
   * this.printChunks() ... void
   * this.removeLastSwitch() ... ILoRoadTripChunk
   * this.isEmpty() ... boolean
   */

  // reverses a non empty list of RoadTripChunks
  public ILoRoadTripChunk reverseChunks(ILoRoadTripChunk reversedSoFar) {
    return this.rest.reverseChunks(new ConsLoRoadTripChunk(this.first, reversedSoFar));
  }

  // prints out the chunks
  public void printChunks() {
    this.first.directions.printDirections();
    System.out.println("- " + this.first.driver + "\n\n");
    this.rest.printChunks();

  }

  public ILoRoadTripChunk parseFinal() {
    ILoRoadTripChunk ret = this.removeLastChunk();
    return ret.removeLastSwitch();
  }

  // removes the last driver switch
  public ILoRoadTripChunk removeLastSwitch() {
    if (this.rest.isEmpty()) {
      return new ConsLoRoadTripChunk(this.first.removeLastSwitchHelper(), this.rest);
    }
    else {
      return new ConsLoRoadTripChunk(this.first, this.rest.removeLastSwitch());
    }
  }

  // removes the last chunks from the list of road trip chunks
  public ILoRoadTripChunk removeLastChunk() {
    if (this.rest.isEmpty()) {
      return this.rest;
    }
    else {
      return new ConsLoRoadTripChunk(this.first, this.rest.removeLastChunk());
    }
  }

  // checks whether or not the list of road trip chunks is empty
  public boolean isEmpty() {
    return false;
  }
}

class MtLoRoadTripChunk implements ILoRoadTripChunk {
  MtLoRoadTripChunk() {

  }

  /* TEMPLATE
   * Methods:
   * this.reverseChunks(ILoRoadTripChunk reversedSoFar) ... ILoRoadTripChunk
   * this.printChunks() .. void
   * this.parseFinal() ... ILoRoadTripChunk
   * this.removeLastSwitch() ... ILoRoadTripChunk
   * this.removeLastChunk() ... ILoRoadTripChunk
   * this.isEmpty() ... boolean
   */

  // reverses an empty list of RoadTripChunks
  public ILoRoadTripChunk reverseChunks(ILoRoadTripChunk reversedSoFar) {
    return reversedSoFar;
  }

  // prints the list of road trip chunks
  public void printChunks() {
    System.out.println("");
  }

  public ILoRoadTripChunk parseFinal() {
    return this;
  }

  // removes the last switch from the list of road trip chunks
  public ILoRoadTripChunk removeLastSwitch() {
    return this;
  }

  // removes last chunk from the list of road trip chunks
  public ILoRoadTripChunk removeLastChunk() {
    return this;
  }

  // checks whether or not the empty list of road trip chunks is empty
  public boolean isEmpty() {
    return true;
  }
}

class Direction {
  String description;
  int miles;

  Direction(String description, int miles) {
    this.description = description;
    this.miles = miles;
  }

  /* TEMPLATE
   * Fields:
   * this.direction ... String
   * this.miles ... int
   * Methods:
   * this.splitNewChunkDirection(int splitSize, int distanceTraveledSoFar) ... Direction
   * this.addDirectionMiles(int distanceTraveledSoFar) ... int
   * this.milesOfThisDirection() ... int
   * this.switchInThisDirection(int splitSize, int distanceTraveledSoFar) ... int
   */

  // splits a new chunk direction depending on the given split size and distance traveled so far
  public Direction splitNewChunkDirection(int splitSize, int distanceTraveledSoFar) {
    return new Direction(this.description, this.miles - (splitSize - distanceTraveledSoFar));
  }

  // adds the distance traveled so far with current miles
  public int addDirectionMiles(int distanceTraveledSoFar) {
    return distanceTraveledSoFar + this.miles;
  }

  // returns the miles of the direction
  public int milesOfThisDirection() {
    return miles;
  }

  // checks if the split should come this direction or not
  public int switchInThisDirection(int splitSize, int distanceTraveledSoFar) {
    if (this.miles + distanceTraveledSoFar > splitSize) {
      return -1;
    }
    else if (this.miles + distanceTraveledSoFar == splitSize) {
      return 0;
    }
    else {
      return 1;
    }
  }
}

class RoadTrip {
  String driver1;
  String driver2;
  ILoDirection directions;

  RoadTrip(String driver1, String driver2, ILoDirection directions) {
    this.driver1 = driver1;
    this.driver2 = driver2;
    this.directions = directions;
  }

  /* TEMPLATE
   * Fields:
   * this.driver1 ... String
   * this.driver2 ... String
   * Methods:
   * this.splitUpTrip(int splitSize) ... ILoRoadTripChunk
   * Methods for fields:
   * this.splitUpTripAccumulator(String driver1, String driver2, int splitSize,
      int distanceTraveledInThisChunk, ILoDirection directionsOfThisChunk,
      ILoRoadTripChunk chunksSoFar) ... ILoRoadTripChunk
   */

  // splits up the trip with the given split size
  ILoRoadTripChunk splitUpTrip(int splitSize) {
    return (this.directions.splitUpTripAccumulator(this.driver1, this.driver2, splitSize, 0,
        new MtLoDirection(), new MtLoRoadTripChunk())).parseFinal();
  }
}

class RoadTripChunk {
  String driver;
  ILoDirection directions;

  RoadTripChunk(String driver, ILoDirection directions) {
    this.driver = driver;
    this.directions = directions;
  }

  /* TEMPLATE
   * Fields:
   * this.driver ... String
   * this.directions ... ILoDirection
   * Methods:
   * this.removeLastSwitchHelper() ... RoadTripChunk
   * Methods for fields:
   * this.removeLastDirection() ... ILoDirection
   */

  // returns the RoadTripChunk without the last direction
  public RoadTripChunk removeLastSwitchHelper() {
    return new RoadTripChunk(driver, this.directions.removeLastDirection());
  }
}

// ExamplesGame

// to represent a resource
interface IResource { }

// to represent a denial
class Denial implements IResource {
  String subject;
  int believability;

  Denial(String subject, int believability) {
    this.subject = subject;
    this.believability = believability;
  }
}

// to represent a bribe
class Bribe implements IResource {
  String target;
  int value;

  Bribe(String target, int value) {
    this.target = target;
    this.value = value;
  }
}

// to represent an apology
class Apology implements IResource {
  String excuse;
  boolean reusable;

  Apology(String excuse, boolean reusable) {
    this.excuse = excuse;
    this.reusable = reusable;
  }
}

// to represent an action
interface IAction { }

// to represent a purchase
class Purchase implements IAction {
  int cost;
  IResource item;

  Purchase(int cost, IResource item) {
    this.cost = cost;
    this.item = item;
  }
}

// to represent a swap
class Swap implements IAction {
  IResource consumed;
  IResource received;

  Swap(IResource consumed, IResource received) {
    this.consumed = consumed;
    this.received = received;
  }
}

// polynomial.java

import tester.Tester;

// represents a monomial, a polynomial with only one term
class Monomial {
  int degree;
  int coefficient;

  Monomial(int degree, int coefficient) {
    if (degree >= 0) {
      this.degree = degree;
    }
    else {
      throw new IllegalArgumentException("Degrees of monomials cannot be negative");
    }
    this.coefficient = coefficient;
  }
  /*
   * Template
   * Fields:
   * degree ... int
   * coefficient ... int
   * Methods:
   * isZero ... boolean
   * sameMonomial ... boolean
   * sameDegree ... boolean
   * Methods of fields:
   */

  // returns if this monomial has a coefficient of 0
  public boolean isZero() {
    return this.coefficient == 0;
  }

  // checks if the given monomial is the same as this monomial
  public boolean sameMonomial(Monomial desired) {
    return this.degree == desired.degree && this.coefficient == desired.coefficient;
  }

  // checks if this monomial and that monomial have the same degree
  public boolean sameDegree(Monomial that) {
    return this.degree == that.degree;
  }
}

// represents a list of monomials
interface ILoMonomial {

  // checks if this ILoMonomial is equivalent as that ILoMonomial
  boolean sameMonomials(ILoMonomial other);

  // determines if this ILoMonomial is empty
  boolean isEmpty();

  // removes the monomials with a coefficient of 0 in a list of monomials
  ILoMonomial removeZeros();

  // removes that monomial from this ILoMonomial
  ILoMonomial findAndRemove(Monomial desired);

  // detects if this ILoMonomial has duplicate degree monomials
  boolean containsSameDegree();

  // detects if there are any monomials of the same degree in this ILoMonomial
  boolean duplicateMonomialDegree(Monomial that);
}

// represents an empty list of monomial
class MtLoMonomial implements ILoMonomial {

  /*
   * Template
   * Fields:
   * Methods:
   * sameMonomials ... boolean
   * isEmpty ... boolean
   * findAndRemove ... ILoMonomial
   * removeZeros ... ILoMonomial
   * containsSameDegree ... boolean
   * duplicateMonomialDegree .. boolean
   * Methods of fields:
   */

  // determines if this MtLoMonomial is equivalent to the other ILoMonomial
  public boolean sameMonomials(ILoMonomial other) {
    return other.isEmpty();
  }

  // determines if this MtLoMonomial is empty
  public boolean isEmpty() {
    return true;
  }

  // if this list of monomials has that monomial, remove it
  public ILoMonomial findAndRemove(Monomial desired) {
    return this;
  }

  // removes the monomials with a coefficient of 0 in an MtLoMonomial
  public ILoMonomial removeZeros() {
    return this;
  }

  // detects if this ILoMonomial has duplicate degree monomials
  public boolean containsSameDegree() {
    return false;
  }

  // detects if there are any monomials with the same degree in this MtLoMonomial
  public boolean duplicateMonomialDegree(Monomial that) {
    return false;
  }
}

// represents a non-empty list of monomials
class ConsLoMonomial implements ILoMonomial {
  Monomial first;
  ILoMonomial rest;

  ConsLoMonomial(Monomial first, ILoMonomial rest) {
    this.first = first;
    this.rest = rest;
  }

  /*
   * Template
   * Fields:
   * first ... Monomial
   * rest ... ILoMonomial
   * Methods:
   * sameMonomials ... boolean
   * isEmpty ... boolean
   * findAndRemove ... ILoMonomial
   * removeZeros ... ILoMonomial
   * containsSameDegree ... boolean
   * duplicateMonomialDegree .. boolean
   * Methods of fields:
   */

  // determines if this ConsLoMonomial is equivalent to the other ILoMonomial
  public boolean sameMonomials(ILoMonomial other) {
    return this.rest.sameMonomials(other.findAndRemove(this.first));
  }

  // determines if this ConsLoMonomial is empty
  public boolean isEmpty() {
    return false;
  }

  // if this list of monomials has that monomial, remove it
  public ILoMonomial findAndRemove(Monomial desired) {
    if (this.first.sameMonomial(desired)) {
      return this.rest;
    }
    else {
      return new ConsLoMonomial(this.first, this.rest.findAndRemove(desired));
    }
  }

  // removes the monomials with a coefficient of 0 in this ConsLoMonomial
  public ILoMonomial removeZeros() {
    if (this.first.isZero()) {
      return this.rest.removeZeros();
    }
    else {
      return new ConsLoMonomial(this.first, this.rest.removeZeros());
    }
  }

  // determines if this ConsLoMonomial contains any duplicate degree monomials
  public boolean containsSameDegree() {
    return this.rest.duplicateMonomialDegree(this.first)
        || this.rest.containsSameDegree();
  }

  // detects if there are any monomials with the same degree in this ConsLoMonomial
  public boolean duplicateMonomialDegree(Monomial that) {
    return this.first.sameDegree(that) || this.rest.duplicateMonomialDegree(that);
  }
}

// represents a polynomial, consisting of a list of monomials
class Polynomial {
  ILoMonomial monomials;

  Polynomial(ILoMonomial monomials) {
    if (monomials.containsSameDegree()) {
      throw new IllegalArgumentException("Polynomials cannot have monomials with the same degree.");
    }
    else {
      this.monomials = monomials;
    }
  }

  /*
   * Template
   * Fields:
   * monomials ... ILoMonomial
   * Methods:
   * samePolynomial ... boolean
   * Methods of fields:
   * removeZeros ... ILoMonomial
   * sameMonomials ... boolean
   */

  // determines if this polynomial is equal to that polynomial
  public boolean samePolynomial(Polynomial other) {
    return this.monomials.removeZeros().sameMonomials(other.monomials.removeZeros())
        && other.monomials.removeZeros().sameMonomials(this.monomials.removeZeros());
  }
}

// represents examples and tests of polynomial
class ExamplesPolynomial {
  Monomial oneToOne = new Monomial(1, 1);
  Monomial twoToThree = new Monomial(2, 3);
  Monomial fourToZero = new Monomial(4, 0);
  Monomial oneToFour = new Monomial(0, 4);
  ILoMonomial monomialsEmpty = new MtLoMonomial();
  // 0x^0 -> 2x^2
  ILoMonomial monomials1 = new ConsLoMonomial(new Monomial(0, 0), new ConsLoMonomial(
      new Monomial(1, 1), new ConsLoMonomial(new Monomial(2, 2), new MtLoMonomial())));
  // 0x^0 -> 3x^3
  ILoMonomial monomials2 = new ConsLoMonomial(new Monomial(0, 0),
      new ConsLoMonomial(new Monomial(1, 1), new ConsLoMonomial(new Monomial(2, 2),
          new ConsLoMonomial(new Monomial(3, 3), new MtLoMonomial()))));
  // changed order of monomials2
  ILoMonomial monomials2Jumbled = new ConsLoMonomial(new Monomial(2, 2),
      new ConsLoMonomial(new Monomial(3, 3), new ConsLoMonomial(new Monomial(0, 0),
          new ConsLoMonomial(new Monomial(1, 1), new MtLoMonomial()))));
  // 1x^1 -> 3x^3
  ILoMonomial monomials3 = new ConsLoMonomial(new Monomial(1, 1), new ConsLoMonomial(
      new Monomial(2, 2), new ConsLoMonomial(new Monomial(3, 3), new MtLoMonomial())));

  ILoMonomial lotsOfZeros = new ConsLoMonomial(new Monomial(4, 0),
      new ConsLoMonomial(new Monomial(1, 1),
          new ConsLoMonomial(new Monomial(10, 0), new ConsLoMonomial(new Monomial(3, 3),
              new ConsLoMonomial(new Monomial(66, 0), new MtLoMonomial())))));
  ILoMonomial filteredLotsOfZeros = new ConsLoMonomial(new Monomial(1, 1),
      new ConsLoMonomial(new Monomial(3, 3), new MtLoMonomial()));

  ILoMonomial illegalMonomials1 = new ConsLoMonomial(new Monomial(1, 1), new ConsLoMonomial(
      new Monomial(1, 2), new ConsLoMonomial(new Monomial(3, 3), new MtLoMonomial())));
  ILoMonomial illegalMonomials2 = new ConsLoMonomial(new Monomial(1, 1), new ConsLoMonomial(
      new Monomial(2, 2), new ConsLoMonomial(new Monomial(2, 3), new MtLoMonomial())));

  Polynomial poly1 = new Polynomial(this.monomials1);
  Polynomial poly2 = new Polynomial(this.monomials2);
  Polynomial poly2Jumbled = new Polynomial(this.monomials2Jumbled);
  Polynomial poly3 = new Polynomial(this.monomials3);
  Polynomial polyEmpty = new Polynomial(this.monomialsEmpty);

  // tests for samePolynomial
  boolean testSamePolynomial(Tester t) {
    return t.checkExpect(this.poly1.samePolynomial(this.poly1), true)
        && t.checkExpect(this.poly1.samePolynomial(this.poly2), false)
        && t.checkExpect(this.poly1.samePolynomial(this.poly3), false)
        && t.checkExpect(this.poly2.samePolynomial(this.poly2Jumbled), true)
        && t.checkExpect(this.polyEmpty.samePolynomial(this.poly1), false)
        && t.checkExpect(this.poly1.samePolynomial(this.polyEmpty), false);
  }

  // tests for sameMonomials
  boolean testSameMonomials(Tester t) {
    return t.checkExpect(this.monomials1.sameMonomials(this.monomials1), true)
        && t.checkExpect(this.monomials1.sameMonomials(this.monomials2), false)
        && t.checkExpect(this.monomials1.sameMonomials(this.monomials3), false)
        && t.checkExpect(this.monomials2.sameMonomials(this.monomials2Jumbled), true)
        && t.checkExpect(new MtLoMonomial().sameMonomials(new MtLoMonomial()), true);
  }

  // tests for isEmpty
  boolean testIsEmpty(Tester t) {
    return t.checkExpect(this.monomials1.isEmpty(), false)
        && t.checkExpect(this.monomialsEmpty.isEmpty(), true);
  }

  // tests for isZero
  boolean testIsZero(Tester t) {
    return t.checkExpect(new Monomial(0, 0).isZero(), true)
        && t.checkExpect(new Monomial(0, 1).isZero(), false)
        && t.checkExpect(new Monomial(1, 0).isZero(), true)
        && t.checkExpect(new Monomial(1, 1).isZero(), false);
  }

  // tests for removeZeros
  boolean testRemoveZeros(Tester t) {
    return t.checkExpect(this.lotsOfZeros.removeZeros(), this.filteredLotsOfZeros)
        && t.checkExpect(this.monomials3.removeZeros(), this.monomials3)
        && t.checkExpect(this.monomials2.removeZeros(), this.monomials3)
        && t.checkExpect(this.monomialsEmpty.removeZeros(), this.monomialsEmpty);
  }

  // tests for sameMonomial
  boolean testSameMonomial(Tester t) {
    return t.checkExpect(this.oneToOne.sameMonomial(this.oneToOne), true)
        && t.checkExpect(this.oneToOne.sameMonomial(this.oneToFour), false)
        && t.checkExpect(this.twoToThree.sameMonomial(this.oneToFour), false)
        && t.checkExpect(this.fourToZero.sameMonomial(this.oneToFour), false);
  }

  // tests for findAndRemove
  boolean testFindAndRemove(Tester t) {
    return t.checkExpect(this.monomials2.findAndRemove(new Monomial(0, 0)), this.monomials3)
        && t.checkExpect(this.monomialsEmpty.findAndRemove(new Monomial(0, 0)), this.monomialsEmpty)
        && t.checkExpect(this.monomials2.findAndRemove(new Monomial(2, 2)),
            new ConsLoMonomial(new Monomial(0, 0), new ConsLoMonomial(new Monomial(1, 1),
                new ConsLoMonomial(new Monomial(3, 3), new MtLoMonomial()))));
  }

  // tests for Monomial constructor
  boolean testMonomialConstructorException(Tester t) {
    return t.checkConstructorException(
        new IllegalArgumentException("Degrees of monomials cannot be negative"), "Monomial", -1, 0)
        && t.checkConstructorException(
            new IllegalArgumentException("Degrees of monomials cannot be negative"), "Monomial",
            -10, 0);
  }

  // tests for Polynomial constructor
  boolean testPolynomialConstructorException(Tester t) {
    return t.checkConstructorException(
        new IllegalArgumentException("Polynomials cannot have monomials with the same degree."),
        "Polynomial", this.illegalMonomials1)
        && t.checkConstructorException(
            new IllegalArgumentException("Polynomials cannot have monomials with the same degree."),
            "Polynomial", this.illegalMonomials2);
  }

  // tests for containsSameDegree
  boolean testContainsSameDegree(Tester t) {
    return t.checkExpect(this.illegalMonomials1.containsSameDegree(), true)
        && t.checkExpect(this.illegalMonomials2.containsSameDegree(), true)
        && t.checkExpect(this.monomials1.containsSameDegree(), false)
        && t.checkExpect(this.monomials2.containsSameDegree(), false);
  }

  // tests for sameDegree
  boolean testSameDegree(Tester t) {
    return t.checkExpect(new Monomial(1, 1).sameDegree(new Monomial(1, 0)), true)
        && t.checkExpect(new Monomial(1, 1).sameDegree(new Monomial(0, 0)), false)
        && t.checkExpect(new Monomial(1, 1).sameDegree(new Monomial(0, 1)), false);
  }

  // test for duplicateMonomialDegree
  boolean testDuplicateMonomialDegree(Tester t) {
    return t.checkExpect(this.monomials1.duplicateMonomialDegree(new Monomial(1, 0)), true)
        && t.checkExpect(this.monomials1.duplicateMonomialDegree(new Monomial(1, 1)), true)
        && t.checkExpect(this.monomials1.duplicateMonomialDegree(new Monomial(2, 1)), true)
        && t.checkExpect(this.monomials1.duplicateMonomialDegree(new Monomial(4, 1)), false);
  }
}
