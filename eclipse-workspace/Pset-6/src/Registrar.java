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

  // Effect: puts the student in the list of enrolled students
  public void enroll(Student student) {
    this.students = new ConsList<Student>(student, students);

  }
}

// represents an instructor of a course
class Instructor {
  String name;
  IList<Course> courses;

  Instructor(String name, IList<Course> courses) {
    this.name = name;
    this.courses = courses;
  }

  public boolean sameProf(Instructor prof) {
    return this.name.equals(prof.name);
  }
}

// represents a student
class Student {
  String name;
  int ID;
  IList<Course> courses;

  Student(String name, int ID, IList<Course> courses) {
    this.name = name;
    this.ID = ID;
    this.courses = courses;
  }

  // Effect: enrolls a student in a course
  public void enroll(Course c) {
    this.courses = new ConsList<Course>(c, courses);
    c.enroll(this);
  }

  // checks if the passed in student is classmates with this student
  public boolean classmates(Student s) {
    return new NumSharedCoursesVisitor(s.courses).apply(this.courses) >= 1;
  }
}

class NumSharedCoursesVisitor implements IListVisitor<Course, Integer> {
  IList<Course> courses;

  NumSharedCoursesVisitor(IList<Course> courses) {
    this.courses = courses;
  }

  // accepts the visitor
  public Integer apply(IList<Course> arg) {
    return arg.accept(this);
  }

  // visits an empty list of Courses;
  public Integer visitMt(MtList<Course> arg) {
    return 0;
  }

  //
  public Integer visitCons(ConsList<Course> arg) {
    /*
     * return new HasFirstCourse(this.courses).apply(arg)
     * + new NumSharedCoursesVisitor(this.courses.rest);
     */
    return 0;
  }

}

class SameCourse implements IPred<Course> {
  Course course;

  SameCourse(Course course) {
    this.course = course;
  }

  public Boolean apply(Course thatCourse) {
    return this.course.name.equals(thatCourse.name) && this.course.prof.sameProf(thatCourse.prof);
  }
}

// tests
class ExamplesRegistrar {
    Student s1 = new Student("s1", 01, new MtList<Course>());
    Student s2 = new Student("s2", 02, new MtList<Course>());
    Student s3 = new Student("s3", 03, new MtList<Course>());
    Student s4 = new Student("s4", 04, new MtList<Course>());
    Student s5 = new Student("s5", 05, new MtList<Course>());

  Instructor i1 = new Instructor("i1", new MtList<Course>());
  Instructor i2 = new Instructor("i2", new MtList<Course>());

    Course c1 = new Course("c1", null, new MtList<Student>());
    Course c2 = new Course("c2", null, new MtList<Student>());
    Course c3 = new Course("c3", null, new MtList<Student>());
    Course c4 = new Course("c4", null, new MtList<Student>());

  public void reset() {
      this.s1 = new Student("s1", 01, new MtList<Course>());
      this.s2 = new Student("s2", 02, new MtList<Course>());
      this.s3 = new Student("s3", 03, new MtList<Course>());
      this.s4 = new Student("s4", 04, new MtList<Course>());
      this.s5 = new Student("s5", 05, new MtList<Course>());

    this.i1 = new Instructor("i1", new MtList<Course>());
    this.i2 = new Instructor("i2", new MtList<Course>());

    this.c1 = new Course("c1", i1, new MtList<Student>());
      this.c2 = new Course("c2", null, new MtList<Student>());
      this.c3 = new Course("c3", null, new MtList<Student>());
      this.c4 = new Course("c4", null, new MtList<Student>());



  }

  // tests for student enroll
  void testEnroll(Tester t) {
    this.reset();
// check if both lists are empty here:
    
    this.s1.enroll(this.c1);
    // System.out.println(c1.name + " " + c1.students.first);
    // check if both lists are updated here:
    /*t.checkExpect(s1.courses.first, new ConsList<Course>(
        new Course("c1", null, new ConsList<Student>(s1, new MtList<Student>()), new MtList<Course>()));
    reset();*/
  }

  void testSameProf(Tester t) {
    t.checkExpect(this.i1.sameProf(this.i1), true);
    t.checkExpect(this.i1.sameProf(this.i2), false);
  }
}