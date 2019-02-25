import tester.Tester;

// interface for a generic list
interface IList<T> {

  // accepts a visitor to this list
  <R> R accept(IListVisitor<T, R> visitor);

  // performs a function on every item in this list
  <U> IList<U> map(IFunc<T, U> func);

  // implementation of foldr
  <U> U foldr(IFunc2<T, U, U> func, U base);

}

// generic cons list
class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;

  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }

  // accepts a visitor to this ConsList
  public <R> R accept(IListVisitor<T, R> visitor) {
    return visitor.visitCons(this);
  }

  // maps a function over a ConsList
  public <U> IList<U> map(IFunc<T, U> func) {
    return new ConsList<U>(func.apply(this.first), this.rest.map(func));
  }

  // implements foldr onto this ConsList
  public <U> U foldr(IFunc2<T, U, U> func, U base) {
    return func.apply(this.first, this.rest.foldr(func, base));
  }


}

// generic empty list
class MtList<T> implements IList<T> {

  // accepts a visitor to this ConsList
  public <R> R accept(IListVisitor<T, R> visitor) {
    return visitor.visitMt(this);
  }

  // maps a function over an empty list
  public <U> IList<U> map(IFunc<T, U> func) {
    return new MtList<U>();
  }

  // implements foldr onto an empty list
  public <U> U foldr(IFunc2<T, U, U> func, U base) {
    return base;
  }

}

// interface for a 1 input function
interface IFunc<A, R> {
  R apply(A arg);
}

// visitors for generic lists
interface IListVisitor<T, R> extends IFunc<IList<T>, R> {

  // applies an operation to a empty list of courses
  R visitMt(MtList<T> arg);

  // applies an operation to a non empty list of courses
  R visitCons(ConsList<T> arg);

}

// interface for predicate functions
interface IPred<T> extends IFunc<T, Boolean> {
  Boolean apply(T t);
}

// A course with a name and prerequisite courses
class Course {
  String name;
  IList<Course> prereqs;

  Course(String name, IList<Course> prereqs) {
    this.name = name;
    this.prereqs = prereqs;
  }

  // gets the deepest path length to an empty list of prereqs
  int getDeepestPathLength() {
    return new DeepestPathLengthVisitor().apply(this.prereqs);
  }

  // determines if this course or any courses in its prereqs have a passed in prereq
  boolean hasPrereq(String desired) {
    return new HasPrereq(desired).apply(this);
  }

}

// visitor for deepest path length
class DeepestPathLengthVisitor implements IListVisitor<Course, Integer> {

  // accepts the visitor
  public Integer apply(IList<Course> arg) {
    return arg.accept(this);
  }

  // visits the MtList
  public Integer visitMt(MtList<Course> arg) {
    return 0;
  }

  // visits the ConsList
  public Integer visitCons(ConsList<Course> arg) {
    return 1 + arg.map(new DeepestPathLength()).foldr(new FindMax(), 0);
  }

}

// a direct function object for deepest path
class DeepestPathLength implements IFunc<Course, Integer> {
  // applies the get deepest path length method on a passed in course
  public Integer apply(Course arg) {
    return arg.getDeepestPathLength();
  }
}

// interface for a functio that takes in 2 inputs
interface IFunc2<A1, A2, R> {
  R apply(A1 input1, A2 input2);
}

// returns the max of 2 inputs
class FindMax implements IFunc2<Integer, Integer, Integer> {
  public Integer apply(Integer input1, Integer input2) {
    if (input1 > input2) {
      return input1;
    }
    else {
      return input2;
    }
  }
}

// finds if the passed in class is a prereq for this course or
// a prereq of a prereq etc.
class HasPrereq implements IPred<Course> {
  String name;

  HasPrereq(String name) {
    this.name = name;
  }

  public Boolean apply(Course arg) {
    // return arg.prereqs.ormap(new SameName(name)) || arg.prereqs.ormap(new HasPrereq(name));
    return new Ormap<Course>(new SameName(this.name)).apply(arg.prereqs)
        || new Ormap<Course>(new HasPrereq(name)).apply(arg.prereqs);
  }
}

// Predicate that determines if the course has the same name as the passed in string
class SameName implements IPred<Course> {
  String name;

  SameName(String name) {
    this.name = name;
  }

  public Boolean apply(Course course) {
    return course.name.equals(this.name);
  }
}

// Ormap visitor
class Ormap<T> implements IListVisitor<T, Boolean> {
  IPred<T> predicate;

  Ormap(IPred<T> predicate) {
    this.predicate = predicate;
  }

  // applies the ormap
  public Boolean apply(IList<T> arg) {
    return arg.accept(this);
  }

  // ormaps over an empty list
  public Boolean visitMt(MtList<T> arg) {
    return false;
  }

  // ormaps over a non empty list
  public Boolean visitCons(ConsList<T> arg) {
    return this.predicate.apply(arg.first) || arg.rest.accept(this);
  }
}

class ExamplesCourse {
  Course bio100 = new Course("Biology 100", new MtList<Course>());
  Course math100 = new Course("Math 100", new MtList<Course>());
  Course math101 = new Course("Math 101", new MtList<Course>());
  Course art100 = new Course("Art 100", new MtList<Course>());
  Course bio200 = new Course("Biology 200",
      new ConsList<Course>(this.bio100, new MtList<Course>()));
  Course math200 = new Course("Math 200", new ConsList<Course>(this.math100, new MtList<Course>()));
  Course CS200 = new Course("Computer Science 200",
      new ConsList<Course>(this.math100, new ConsList<Course>(this.bio100, new MtList<Course>())));
  Course bioMath200 = new Course("Math and Biology 200", new ConsList<Course>(this.bio100,
      new ConsList<Course>(this.math100, new ConsList<Course>(this.bio100, new MtList<Course>()))));
  Course math300 = new Course("Math 300", new ConsList<Course>(this.math200, new MtList<Course>()));
  Course mathArt300 = new Course("Math and Art 300",
      new ConsList<Course>(this.math200, new ConsList<Course>(this.art100, new MtList<Course>())));
  Course math400 = new Course("Math 400", new ConsList<Course>(this.math300, new MtList<Course>()));
  Course mathArt500 = new Course("Math and Art 500", new ConsList<Course>(this.mathArt300,
      new ConsList<Course>(this.math300, new MtList<Course>())));
  Course bioMath300 = new Course("Bio and Math 300",
      new ConsList<Course>(this.bio200, new ConsList<Course>(this.math200, new MtList<Course>())));

  // tests for getDeepestPathLength
  void testDeepestPathLength(Tester t) {
    t.checkExpect(this.bio100.getDeepestPathLength(), 0);
    t.checkExpect(this.math200.getDeepestPathLength(), 1);
    t.checkExpect(this.CS200.getDeepestPathLength(), 1);
    t.checkExpect(this.mathArt300.getDeepestPathLength(), 2);
    t.checkExpect(this.math400.getDeepestPathLength(), 3);
  }

  // tests for getLargest
  void testGetLargest(Tester t) {
    FindMax findMax = new FindMax();
    t.checkExpect(findMax.apply(21, 0), 21);
    t.checkExpect(findMax.apply(0, 21), 21);
    t.checkExpect(findMax.apply(-4, 4), 4);
    t.checkExpect(findMax.apply(0, 0), 0);
  }

  // tests for hasPrereq
  void testHasPrereq(Tester t) {
    t.checkExpect(this.bio100.hasPrereq("Math 300"), false);
    t.checkExpect(this.bioMath200.hasPrereq("Math 100"), true);
    t.checkExpect(this.math300.hasPrereq("Math 300"), false);
    t.checkExpect(this.mathArt300.hasPrereq("Art 100"), true);
    t.checkExpect(this.mathArt500.hasPrereq("Art 100"), true);
    t.checkExpect(this.bioMath300.hasPrereq("Math 100"), true);
    t.checkExpect(this.bioMath300.hasPrereq("Art 100"), false);

  }

  // tests for ormap
  void testOrMap(Tester t) {
    t.checkExpect(new Ormap<Course>(new SameName("Math 100")).apply(this.bioMath200.prereqs), true);
    t.checkExpect(new Ormap<Course>(new SameName("Math 100")).apply(this.math100.prereqs), false);
    t.checkExpect(new Ormap<Course>(new SameName("Math 100")).apply(this.bioMath300.prereqs),
        false);
  }
}
