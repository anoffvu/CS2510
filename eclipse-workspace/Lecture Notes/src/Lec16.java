import org.w3c.dom.css.Rect;

// An IShapeVisitor is a function over IShapes
interface IShapeVisitor<R> extends IFunc<IShape, R> {
  R visitCircle(Circle c);

  R visitSquare(Square s);

  R visitRect(Rect r);
}

// ShapeArea is a function object over IShapes that computes their area
class ShapeArea implements IShapeVisitor<Double> {
  // Everything from the IShapeVisitor interface:
  public Double visitCircle(Circle c) {
    return Math.PI * c.radius * c.radius;
  }

  public Double visitSquare(Square s) {
    return s.side * s.side;
  }

  public Double visitRect(Rect r) {
    return r.w * r.h;
  }

  // Everything from the IFunc interface:
  public Double apply(IShape s) {
    return s.accept(this);
  }
}