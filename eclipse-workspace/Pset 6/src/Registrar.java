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

//interface for a functio that takes in 2 inputs
interface IFunc2<A1, A2, R> {
  R apply(A1 input1, A2 input2);
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

// class representing a university course
class Course {
  String name;
  Instructor prof;
  IList<Student> students;

  Course(String name, Instructor prof, IList<Student> students) {
    this.name = name;
    this.prof = prof;
    this.students = students;
  }
}

class Instructor {
  String name;
  IList<Course> courses;

  Instructor(String name, IList<Course> courses) {
    this.name = name;
    this.courses = courses;
  }
}

class Student {
  String name;
  int ID;
  IList<Course> courses;

  Student(String name, int ID, IList<Course> courses) {
    this.name = name;
    this.ID = ID;
    this.courses = courses;
  }
}