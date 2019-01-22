import tester.Tester;

interface IPicture {
  // computes the width of this picture
  int getWidth();

  // computes the number of shapes in this picture
  int countShapes();

  // computes how deeply operations are nested in the construction of this picture
  int comboDepth();

  // reflects the picture down the middle like a mirror
  IPicture mirror();
}

interface IOperation {
  // computes the total width of this Operation
  int widthOfOperation();

  // computes the count of shapes in this Operation
  int shapeCountOfOperation();

  // computes the combo depth of this Operation
  int comboDepthOfOperation();

  // mirrors the picture created by this operation
  IOperation mirrorOperation();

}

class Shape implements IPicture {
  String kind;
  int size;

  Shape(String kind, int size) {
    this.kind = kind;
    this.size = size;
  }

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
    return this;
  }

}

class Combo implements IPicture {
  String name;
  IOperation operation;

  Combo(String name, IOperation operation) {
    this.name = name;
    this.operation = operation;
  }

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
}

class Scale implements IOperation {
  IPicture picture;

  Scale(IPicture picture) {
    this.picture = picture;
  }
  // double the pic size somehow, i think youll have to take it apart and double each
  // individual shape

  // returns the width of this Scale operation
  public int widthOfOperation() {
    return 2 * this.picture.getWidth();
  }

  // computes the shape count of this Scale operation
  public int shapeCountOfOperation() {
    return 1;
  }

  // computes the combo depth of this Scale operation
  public int comboDepthOfOperation() {
    return 1;
  }

  // mirrors this Scale operation
  public IOperation mirrorOperation() {
    return this;
  }

}

class Beside implements IOperation {
  IPicture picture1;
  IPicture picture2;

  Beside(IPicture picture1, IPicture picture2) {
    this.picture1 = picture1;
    this.picture2 = picture2;
  }

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
    return 1 + this.picture1.comboDepth() + this.picture2.comboDepth();
  }

  // mirrors this Beside operation
  public IOperation mirrorOperation() {
    return new Beside(this.picture2, this.picture1);
  }
}

class Overlay implements IOperation {
  IPicture topPicture;
  IPicture bottomPicture;

  Overlay(IPicture topPicture, IPicture bottomPicture) {
    this.topPicture = topPicture;
    this.bottomPicture = bottomPicture;
  }

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
    return 1 + this.topPicture.comboDepth() + this.bottomPicture.comboDepth();
  }

  // mirrors this Overlay operation
  public IOperation mirrorOperation() {
    return this;
  }
}

class ExamplesPicture {
  // explicit examples
  IPicture circle = new Shape("circle", 20);
  IPicture square = new Shape("square", 30);
  IPicture smallSquare = new Shape("square", 10);
  IPicture bigCircle = new Combo("big circle", new Scale(this.circle));
  IPicture squareOnCircle = new Combo("square on circle",
      new Overlay(this.smallSquare, this.circle));
  IPicture doubledSquareOnCircle = new Combo("doubled square on circle",
      new Beside(this.squareOnCircle, this.squareOnCircle));

  // examples of each operation
  IPicture scaleExample = new Combo("big square", new Scale(this.square));
  IPicture overalyExample = new Combo("circle on square", new Overlay(this.circle, this.square));
  IPicture besideExample = new Combo("two squares", new Beside(this.square, this.square));
  IPicture twoBesideExample = new Combo("three squares",
      new Beside(this.square, this.besideExample));
  IPicture squareNextToCircle = new Combo("square next to circle",
      new Beside(this.square, this.circle));
  IPicture doubleCircle = new Combo("double circle", new Beside(this.circle, this.circle));
  IPicture squareNextToDoubleCircle = new Combo("square next to double circle",
      new Beside(this.square, this.doubleCircle));

  IOperation bigCircleOperation = new Scale(this.circle);
  IOperation squareOnCircleOperation = new Overlay(this.smallSquare, this.circle);
  IOperation doubledSquareOnCircleOperation = new Beside(this.squareOnCircle, this.squareOnCircle);
  IOperation twoBesideExampleOperation = new Beside(this.square, this.besideExample);
  IOperation squareNextToCircleOperation = new Beside(this.square, this.circle);
  IOperation squareNextToDoubleCircleOperation = new Beside(this.square, this.doubleCircle);

  // tests for getWidth
  boolean testGetWidth(Tester t) {
    return t.checkExpect(this.circle.getWidth(), 20) && t.checkExpect(this.square.getWidth(), 30)
        && t.checkExpect(this.bigCircle.getWidth(), 40)
        && t.checkExpect(this.squareOnCircle.getWidth(), 20)
        && t.checkExpect(this.doubledSquareOnCircle.getWidth(), 40)
        && t.checkExpect(this.twoBesideExample.getWidth(), 90);
  }

  // tests for widthOfOperation
  boolean testWidthOfOperation(Tester t) {
    return t.checkExpect(this.bigCircleOperation.widthOfOperation(), 40)
        && t.checkExpect(this.squareOnCircleOperation.widthOfOperation(), 20)
        && t.checkExpect(this.doubledSquareOnCircleOperation.widthOfOperation(), 40)
        && t.checkExpect(this.twoBesideExampleOperation.widthOfOperation(), 90);
  }

  // tests for countShapes
  boolean testCountShapes(Tester t) {
    return t.checkExpect(this.circle.countShapes(), 1)
        && t.checkExpect(this.bigCircle.countShapes(), 1)
        && t.checkExpect(this.squareOnCircle.countShapes(), 2)
        && t.checkExpect(this.doubledSquareOnCircle.countShapes(), 4)
        && t.checkExpect(this.twoBesideExample.countShapes(), 3);
  }

  // tests for shapecountOfOperation
  boolean testShapeCountOfOperation(Tester t) {
    return t.checkExpect(this.bigCircleOperation.shapeCountOfOperation(), 1)
        && t.checkExpect(this.squareOnCircleOperation.shapeCountOfOperation(), 2)
        && t.checkExpect(this.doubledSquareOnCircleOperation.shapeCountOfOperation(), 4)
        && t.checkExpect(this.twoBesideExampleOperation.shapeCountOfOperation(), 3);
  }

  // tests for comboDepth
  boolean testComboDepth(Tester t) {
    return t.checkExpect(this.circle.comboDepth(), 0)
        && t.checkExpect(this.bigCircle.comboDepth(), 1)
        && t.checkExpect(this.twoBesideExample.comboDepth(), 2)
        && t.checkExpect(this.doubledSquareOnCircle.comboDepth(), 3);
  }

  // tests for comboDepthOfOperation
  boolean testComboDepthOfOperation(Tester t) {
    return t.checkExpect(this.bigCircleOperation.comboDepthOfOperation(), 1)
        && t.checkExpect(this.squareOnCircleOperation.comboDepthOfOperation(), 1)
        && t.checkExpect(this.doubledSquareOnCircleOperation.comboDepthOfOperation(), 3)
        && t.checkExpect(this.twoBesideExampleOperation.comboDepthOfOperation(), 2);
  }

  // tests for mirror
  boolean testMirror(Tester t) {
    return t.checkExpect(this.circle.mirror(), this.circle)
        && t.checkExpect(this.squareOnCircle.mirror(), this.squareOnCircle)
        && t.checkExpect(this.doubledSquareOnCircle.mirror(), this.doubledSquareOnCircle)
        && t.checkExpect(this.twoBesideExample.mirror(),
            new Combo("three squares", new Beside(this.besideExample, this.square)))
        && t.checkExpect(this.squareNextToCircle.mirror(),
            new Combo("square next to circle", new Beside(this.circle, this.square)))
        && t.checkExpect(this.squareNextToDoubleCircle.mirror(),
            new Combo("square next to double circle", new Beside(this.doubleCircle, this.square)));
  }

  // tests for mirror
  boolean testMirrorOperation(Tester t) {
    return t.checkExpect(this.bigCircleOperation.mirrorOperation(), this.bigCircleOperation)
        && t.checkExpect(this.squareOnCircleOperation.mirrorOperation(),
            this.squareOnCircleOperation)
        && t.checkExpect(this.doubledSquareOnCircleOperation.mirrorOperation(),
            this.doubledSquareOnCircleOperation)
        && t.checkExpect(this.twoBesideExampleOperation.mirrorOperation(),
            new Beside(this.besideExample, this.square))
        && t.checkExpect(this.squareNextToCircleOperation.mirrorOperation(),
            new Beside(this.circle, this.square))
        && t.checkExpect(this.squareNextToDoubleCircleOperation.mirrorOperation(),
            new Beside(this.doubleCircle, this.square));
  }
}
