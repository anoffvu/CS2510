import tester.Tester;

//interface for a generic list
interface IList<T> {

// accepts a visitor to this list
<R> R accept(IListVisitor<T, R> visitor);

// performs a function on every item in this list
<U> IList<U> map(IFunc<T, U> func);

// implementation of foldr
<U> U foldr(IFunc2<T, U, U> func, U base);

}

//generic cons list
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

//generic empty list
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

//interface for a 1 input function
interface IFunc<A, R> {
  R apply(A arg);
}

//visitors for generic lists
interface IListVisitor<T, R> extends IFunc<IList<T>, R> {

// applies an operation to a empty list of courses
  R visitMt(MtList<T> arg);

// applies an operation to a non empty list of courses
  R visitCons(ConsList<T> arg);

}

//interface for predicate functions
interface IPred<T> extends IFunc<T, Boolean> {
  Boolean apply(T t);
}

//A course with a name and prerequisite courses
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

//visitor for deepest path length
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

//a direct function object for deepest path
class DeepestPathLength implements IFunc<Course, Integer> {
// applies the get deepest path length method on a passed in course
  public Integer apply(Course arg) {
    return arg.getDeepestPathLength();
  }
}

//interface for a functio that takes in 2 inputs
interface IFunc2<A1, A2, R> {
  R apply(A1 input1, A2 input2);
}

//returns the max of 2 inputs
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

//finds if the passed in class is a prereq for this course or
//a prereq of a prereq etc.
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

//Predicate that determines if the course has the same name as the passed in string
class SameName implements IPred<Course> {
  String name;

  SameName(String name) {
    this.name = name;
  }

  public Boolean apply(Course course) {
    return course.name.equals(this.name);
  }
}

//Ormap visitor
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

import tester.Tester;

//interface for a generic list
interface IList<T> {

// accepts a visitor to this list
  <R> R accept(IListVisitor<T, R> visitor);

// performs a function on every item in this list
  <U> IList<U> map(IFunc<T, U> func);

// implementation of foldr
  <U> U foldr(IFunc2<T, U, U> func, U base);

}

//generic cons list
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

//generic empty list
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

//interface for a 1 input function
interface IFunc<A, R> {
  R apply(A arg);
}

//interface for a functio that takes in 2 inputs
interface IFunc2<A1, A2, R> {
  R apply(A1 input1, A2 input2);
}

//visitors for generic lists
interface IListVisitor<T, R> extends IFunc<IList<T>, R> {

// applies an operation to a empty list of courses
  R visitMt(MtList<T> arg);

// applies an operation to a non empty list of courses
  R visitCons(ConsList<T> arg);

}

//interface for predicate functions
interface IPred<T> extends IFunc<T, Boolean> {
  Boolean apply(T t);
}

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

//class representing a university course
class Course {
  String name;
  Instructor prof;
  IList<Student> students;

  Course(String name, Instructor prof) {
    this(name, prof, new MtList<Student>());
  }

  Course(String name, Instructor prof, IList<Student> students) {
    this.name = name;
    this.prof = prof;
    this.prof.updateCourses(this);
    this.students = students;
  }
}

//represents an instructor of a course
class Instructor {
  String name;
  IList<Course> courses;

  Instructor(String name) {
    this(name, new MtList<Course>());
  }

  public void updateCourses(Course course) {
    this.courses = new ConsList<Course>(course, this.courses);

  }

  Instructor(String name, IList<Course> courses) {
    this.name = name;
    this.courses = courses;
  }

  public boolean sameProf(Instructor prof) {
    return this.name.equals(prof.name);
  }

  public boolean dejavu(Student s) {
    return new CountStudentAppearancesVisitor(s).apply(this.courses) > 1;
  }
}

//represents a student
class Student {
  String name;
  int id;
  IList<Course> courses;

  Student(String name, int id) {
    this(name, id, new MtList<Course>());
  }

  Student(String name, int id, IList<Course> courses) {
    this.name = name;
    this.id = id;
    this.courses = courses;
  }

// Effect: enrolls a student in a course
  public void enroll(Course c) {
    this.courses = new ConsList<Course>(c, courses);
    c.students = new ConsList<Student>(this, c.students);
  }

// checks if the passed in student is classmates with this student
  public boolean classmates(Student s) {
    return new CountStudentAppearancesVisitor(s).apply(this.courses) >= 1;
  }
}

//counts the number of times a passed in student apppears as a student
//in a list of courses
class CountStudentAppearancesVisitor implements IListVisitor<Course, Integer> {
  Student student;

  CountStudentAppearancesVisitor(Student student) {
    this.student = student;
  }

// accepts the visitor
  public Integer apply(IList<Course> arg) {
    return arg.accept(this);
  }

// visits an empty list of Courses;
  public Integer visitMt(MtList<Course> arg) {
    return 0;
  }

// counts how many times the student appears in a list of courses's students list
  public Integer visitCons(ConsList<Course> arg) {
    if (new Ormap<Student>(new SameStudent(this.student)).apply(arg.first.students)) {
      return 1 + new CountStudentAppearancesVisitor(this.student).apply(arg.rest);
    }
    else {
      return new CountStudentAppearancesVisitor(this.student).apply(arg.rest);
    }
  }

}

class SameStudent implements IPred<Student> {
  Student student;

  SameStudent(Student student) {
    this.student = student;
  }

  public Boolean apply(Student thatStudent) {
    return this.student.name.equals(thatStudent.name) && this.student.id == thatStudent.id;
  }
}


//represents a list of Person's buddies
class ConsLoBuddy implements ILoBuddy {

  Person first;
  ILoBuddy rest;

  ConsLoBuddy(Person first, ILoBuddy rest) {
    this.first = first;
    this.rest = rest;
  }

// returns true if that person is in this list of buddies
  public boolean personInList(Person that) {
    return this.first.samePerson(that) || this.rest.personInList(that);
  }

// counts the number of common buddies in this non empty list of buddies
  public int countBuddiesHelper(Person that) {
    if (that.hasDirectBuddy(this.first)) {
      // why did this fail when I did (this.first.hasDirectBuddy(that))
      return 1 + this.rest.countBuddiesHelper(that);
    }
    else {
      return this.rest.countBuddiesHelper(that);
    }
  }

// determines if that person is an extended buddy to anyone in this list of buddies
  public boolean friendOfFriend(Person that, ILoBuddy visited) {
    if (!visited.personInList(this.first)) {
      return this.first.hasDirectBuddy(that)
          || this.first.hasExtendedBuddyHelper(that, new ConsLoBuddy(this.first, visited))
          || this.rest.friendOfFriend(that, new ConsLoBuddy(this.first, visited));
    }
    else {
      return this.rest.friendOfFriend(that, visited);
    }
  }

// counts the uncounted buddies in this network of everyone connected to these buddies
// counting the buddies of this list
// acc: visited buddies so far
  public ILoBuddy visitUnvisited(ILoBuddy visited) {
    if (!visited.personInList(this.first)) {
      return this.rest
          .visitUnvisited(this.first.visitNetwork(new ConsLoBuddy(this.first, visited)));
    }
    else {
      return this.rest.visitUnvisited(visited);
    }
  }

// counts the size of this non empty list of buddies
  public int length() {
    return 1 + this.rest.length();
  }

// returns the max likelihood that a message could be conveyed to
// that person through this non empty list of buddies
  public double maxLikelihood(Person startPerson, Person that, ILoBuddy visited) {
    if (visited.personInList(this.first)) {
      return this.rest.maxLikelihood(startPerson, that, visited);
    }
    else {
      return Math.max(
          startPerson.calcScore(this.first)
              * this.first.maxLikelihoodAcc(that, new ConsLoBuddy(this.first, visited)),
          this.rest.maxLikelihood(startPerson, that, new ConsLoBuddy(this.first, visited)));
    }
  }
}

//represents a Person with a user name and a list of buddies
class Person {

  String username;
  ILoBuddy buddies;
  double diction;
  double hearing;

  Person(String username) {
    this.username = username;
    this.buddies = new MTLoBuddy();
    this.diction = 0.0;
    this.hearing = 0.0;
  }

  Person(String username, double diction, double hearing) {
    this.username = username;
    this.buddies = new MTLoBuddy();
    if (0.0 <= diction && diction <= 1.0) {
      this.diction = diction;
    }
    else {
      throw new IllegalArgumentException("Diction should be a value between 0 and 1.");
    }
    if (0.0 <= hearing && hearing <= 1.0) {
      this.hearing = hearing;
    }
    else {
      throw new IllegalArgumentException("Hearing should be a value between 0 and 1.");
    }
  }

// returns true if this Person has that as a direct buddy
  boolean hasDirectBuddy(Person that) {
    return buddies.personInList(that);
  }

// returns the number of people who will show up at the party
// given by this person
  int partyCount() {
    return this.visitNetwork(new ConsLoBuddy(this, new MTLoBuddy())).length();
  }

// visits the network of this person
// acc: buddies visited so far
  public ILoBuddy visitNetwork(ILoBuddy visited) {
    return this.buddies.visitUnvisited(visited);
  }

// returns the number of people that are direct buddies
// of both this and that person
  int countCommonBuddies(Person that) {
    return this.buddies.countBuddiesHelper(that);
  }

// will the given person be invited to a party
// organized by this person?
  boolean hasExtendedBuddy(Person that) {
    return this.hasDirectBuddy(that) || this.hasExtendedBuddyHelper(that, new MTLoBuddy());
    //
  }

// searches the buddies to see if that person is a friend of a friend etc.
// acc: the people visited so far
  public boolean hasExtendedBuddyHelper(Person that, ILoBuddy visited) {
    return this.buddies.friendOfFriend(that, visited);
  }

// EFFECT:
// Change this person's buddy list so that it includes the given person
  void addBuddy(Person buddy) {
    this.buddies = new ConsLoBuddy(buddy, this.buddies);
  }

// determines if this person is the same as that person
  public boolean samePerson(Person that) {
    return this.username.equals(that.username);
  }

// determines the maximum likelihood that
// this person can correctly convey a
// message to the given person
  public double maxLikelihood(Person that) {
    return this.maxLikelihoodAcc(that, new ConsLoBuddy(this, new MTLoBuddy()));

  }

// determines the maximum likelihood that
// this person can correctly convey a
// message to the given person
// ACC: people we have visited so far
  public double maxLikelihoodAcc(Person that, ILoBuddy visited) {
    if (this.samePerson(that)) {
      return 1.0;
    }
    else if (this.hasDirectBuddy(that)) {
      return this.calcScore(that);
    }
    else if (this.hasExtendedBuddy(that)) {
      return this.buddies.maxLikelihood(this, that, visited);
    }
    else {
      return 0.0;
    }
  }

// calculates the score of passing on a message to that person
  public double calcScore(Person that) {
    return this.diction * that.hearing;
  }
}

import tester.Tester;
}

import tester.Tester;

//Represents a boolean-valued question over values of type T
interface IPred<T> {
boolean apply(T t);
}

//predicate that tests of two strings are the same
class SameString implements IPred<String> {
  String s;

  SameString(String s) {
    this.s = s;
  }

  public boolean apply(String that) {
    return this.s.equals(that);
  }
}

//predicate that test of two ints are the same
class SameInt implements IPred<Integer> {
  Integer num;

  SameInt(Integer num) {
    this.num = num;
  }

  public boolean apply(Integer that) {
    return this.num == that;
  }
}

//represents a list that can add things on at both ends
class Deque<T> {
  Sentinel<T> header;

// default constructor
  Deque() {
    this.header = new Sentinel<T>();
  }

// convenience constructor passing in a sentinel node
  Deque(Sentinel<T> header) {
    this.header = header;
  }

// returns the size of this
  public int size() {
    return this.header.next.calcSize(0);
  }

// adds that data to the head of this deque
  public void addAtHead(T that) {
    this.header.next = new Node<T>(that, this.header.next, this.header);
  }

// adds that data to the tail of this deque
  public void addAtTail(T that) {
    this.header.prev = new Node<T>(that, this.header, this.header.prev);
  }

// adds that data to the head of this deque
  public T removeFromHead() {
    return this.header.next.removeThis();
  }

// adds that data to the tail of this deque
  public T removeFromTail() {
    return this.header.prev.removeThis();
  }

// takes a predicate and produces the first node in this Deque for which the given
// predicate returns true
  public ANode<T> find(IPred<T> pred) {
    return this.header.next.findHelp(pred);
  }

// removes a given node from
  public void removeNode(ANode<T> node) {
    node.removeNodeHelp();
  }

}

//abstract class for deque nodes
abstract class ANode<T> {
  ANode<T> next;
  ANode<T> prev;

// default constructor
  ANode(ANode<T> next, ANode<T> prev) {
    this.next = next;
    this.prev = prev;
  }

// convenience constructor for no arguments
  ANode() {
    this.next = null;
    this.prev = null;
  }

// calculates the size of the this linked list of nodes
  abstract int calcSize(int count);

// removes this ANode
  abstract T removeThis();

// returns the first node that satisfies the given predicate
// if no node does, it returns the header
  abstract ANode<T> findHelp(IPred<T> pred);

// removes the desired node
  abstract void removeNodeHelp();
}

//a sentinel of a list
class Sentinel<T> extends ANode<T> {
  Sentinel(ANode<T> next, ANode<T> prev) {
    super(next, prev);
  }

  Sentinel() {
    super();
    this.next = this;
    this.prev = this;
  }

// throws an error if the user tries to remove an item from an empty list
  public T removeThis() {
    throw new RuntimeException("Cannot remove items from an empty list!");
  }

// finds the first node that passes a predicate
  public ANode<T> findHelp(IPred<T> pred) {
    return this;
  }

// removes the desired node, does nothing on a sentinel
  public void removeNodeHelp() {
    // shouldn't do anything when inside a sentinel
  }

// returns the size of this list
  int calcSize(int count) {
    return count;
  }
}

//a node inside a deque
class Node<T> extends ANode<T> {
  T data;

// default node constructor, next and prev are set to null
  Node(T data) {
    super(null, null);
    this.data = data;
  }

// convenience node constructor where data, next, and prev nodes are passed in
  Node(T data, ANode<T> next, ANode<T> prev) {
    super(next, prev);
    if (next == null || prev == null) {
      throw new IllegalArgumentException("You cannot make a node pointing to null objects!");
    }
    else {
      this.data = data;
      this.next.prev = this;
      this.prev.next = this;
    }
  }

// calculates the size of this list
  int calcSize(int count) {
    return this.next.calcSize(count + 1);
  }

// removes this node from the list that its in
  public T removeThis() {
    ANode<T> tempNext = this.next;
    ANode<T> tempPrev = this.prev;
    this.prev.next = tempNext;
    this.next.prev = tempPrev;
    return this.data;
  }

// finds the first node that passes this predicate
  public ANode<T> findHelp(IPred<T> pred) {
    if (pred.apply(this.data)) {
      return this;
    }
    else {
      return next.findHelp(pred);
    }
  }

// removes the desired node
  public void removeNodeHelp() {
    this.removeThis();
  }
}

/**
 * A class that defines a new permutation code, as well as methods for encoding
 * and decoding of the messages that use this code.
 */

public class PermutationCode {
  // The original list of characters to be encoded
  ArrayList<Character> alphabet = new ArrayList<Character>(
      Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
          'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));

  // size of the given alphabet
  public final int ALPHABET_SIZE = alphabet.size();

  // moves the ascii code to lowercase letters
  public static final int CHAR_TO_LOWER = 97;

  ArrayList<Character> code = new ArrayList<Character>(ALPHABET_SIZE);

  // A random number generator
  // Random rand = new Random();

  // Create a new instance of the encoder/decoder with a new permutation code
  PermutationCode() {
    this.code = this.initEncoder();
  }

  // Create a new instance of the encoder/decoder with the given code
  PermutationCode(ArrayList<Character> code) {
    this.code = code;
  }

  // Initialize the encoding permutation of the characters
  ArrayList<Character> initEncoder() {
    ArrayList<Character> encoded = new ArrayList<Character>(alphabet);
    for (int i = 0; i < ALPHABET_SIZE; i++) {
      char charHolder = encoded.get(i);
      int randIndex = (int) (Math.random() * ALPHABET_SIZE);
      encoded.set(i, encoded.get(randIndex));
      encoded.set(randIndex, charHolder);
    }
    return encoded;
  }

  // produce an encoded String from the given String
  String encode(String toEncode) {
    int toEncodeLength = toEncode.length();
    ArrayList<Character> toEncodeChars = stringToAList(toEncode);
    for (int i = 0; i < toEncodeLength; i++) {
      toEncodeChars.set(i, code.get(toEncodeChars.get(i) - CHAR_TO_LOWER));
    }
    return arrayListToString(toEncodeChars);
  }

  // produce a decoded String from the given String
  String decode(String toDecode) {
    int toDecodeLength = toDecode.length();
    ArrayList<Character> codeChars = stringToAList(toDecode);
    for (int i = 0; i < toDecodeLength; i++) {
      codeChars.set(i, (char) (code.indexOf(codeChars.get(i)) + CHAR_TO_LOWER));
    }
    return arrayListToString(codeChars);
  }

  // converts a string to an ArrayList of chars
  ArrayList<Character> stringToAList(String s) {
    int stringLength = s.length();
    ArrayList<Character> charAList = new ArrayList<Character>();
    for (int i = 0; i < stringLength; i++) {
      charAList.add(s.charAt(i));
    }
    return charAList;
  }

  // converts an ArrayList of chars to a string
  String arrayListToString(ArrayList<Character> chars) {
    String builtString = "";
    for (Character c : chars) {
      builtString += c;
    }
    return builtString;
  }
}


//Shall we play a game?

/*
 * "snake" "cat" "horse" "bat" "dog", rotate to the right by 3
 * "horse" "bat" "dog" "snake" "cat"
 */

class ArrayUtils {

  // *shudder*
  // A short 2-line solution of effectively write-only code!
  // and to make it work negative as well we had to:
  // (((n % m) + m) % m)
  <T> void rotateRight(ArrayList<T> al, int numPlaces) {
    for (numPlaces = numPlaces % al.size(); numPlaces > 0; numPlaces--) {
      al.add(0, al.remove(al.size() - 1));
    }

    /*
     * for(int i = 0; i < al.size(); i++) {
     * T old = al.get((i + numPlaces) % al.size());
     * al.set(i, old);
     * }
     */
  }

}

// Returns only every nth element of the previous iterator
class EveryN<X> implements Iterator<X> {
  Iterator<X> iter;
  int c;

  EveryN(Iterator<X> iter, int c) {
    this.iter = iter;
    this.c = c;
  }

  public boolean hasNext() {
    return this.iter.hasNext();
  }

  public X next() {
    X res = this.iter.next();
    for (int i = 1; i < c; i++) {
      if (this.iter.hasNext()) {
        this.iter.next();
      }
    }
    return res;
  }

}

class Pair<X, Y> {
  X x;
  Y y;

  Pair(X x, Y y) {
    this.x = x;
    this.y = y;
  }

}

class Zipperator<X, Y> implements Iterator<Pair<X, Y>> {
  Iterator<X> itx;
  Iterator<Y> ity;

  // the obvious constructor

  public boolean hasNext() {
    return this.itx.hasNext() && this.ity.hasNext();
  }

  public Pair<X, Y> next() {
    return new Pair<X, Y>(this.itx.next(), this.ity.next());
  }

}

// or-predicates

interface IFunc<Input, Output> {
  Output apply(Input in);
}

interface IPred<Input> extends IFunc<Input, Boolean> {
}

class OrPred<Input> implements IPred<Input> {
  IPred<Input> left;
  IPred<Input> right;

  OrPred(IPred<Input> left, IPred<Input> right) {
    this.left = left;
    this.right = right;
  }

  public Boolean apply(Input in) {
    return this.left.apply(in) || this.right.apply(in);
  }

}

// Hungry for more??
//And Predicates
//Left-and-not-right
//XOR
// Doesn't it feel like there's a missing abstraction! Go find it! 

class ExamplesReview {
  ArrayUtils au = new ArrayUtils();
  ArrayList<String> exArrL;
  ArrayList<String> critters = new ArrayList<String>(
      Arrays.asList("snake", "cat", "horse", "bat", "dog"));

  // We weren't sure about how the arithmetic works
  // So we *tested it*!!
  int answer = (((-13) % 4) + 4) % 4;
  int answer2 = ((7 % 4) + 4) % 4;

  void initData() {
    this.exArrL = new ArrayList<String>(Arrays.asList("fish", "horse", "turtle", "hampster"));
  }

  void testRotateRight(Tester t) {
    this.initData();
    t.checkExpect(this.exArrL.size(), 4);
    t.checkExpect(this.exArrL,
        new ArrayList<String>(Arrays.asList("fish", "horse", "turtle", "hampster")));
    this.au.rotateRight(this.exArrL, 2);
    t.checkExpect(this.exArrL,
        new ArrayList<String>(Arrays.asList("turtle", "hampster", "fish", "horse")));
    this.initData();
    this.au.rotateRight(this.exArrL, 13);
    t.checkExpect(this.exArrL,
        new ArrayList<String>(Arrays.asList("hampster", "fish", "horse", "turtle")));

  }

  HashMap<String, String> hm;
  Iterator<String> strs;
  Iterator<String> strs2;

  void initHM() {
    this.hm = new HashMap<String, String>();
    hm.put("fish", "filet");
    hm.put("turtle", "soup");
    hm.put("horse", "tartare");
  }

  void testHashMap(Tester t) {
    this.initHM();
    strs = hm.values().iterator(); // for the values
    strs = hm.keySet().iterator(); // for the keys

  }
}

import java.util.ArrayList;
import java.util.Iterator;

// Equality: Line 8
// Visitors: Line 89
// Iterators: Line 150

/********** Equality & Hash codes **********/

class Circle {
  int radius;

  Circle(int rad) {
    this.radius = rad;
  }

  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    // or something like isCircle etc.
    else if (o instanceof Circle) {
      return this.radius == ((Circle) o).radius;
    }
    else {
      // o isn't a circle! we know it can't be the same then
      return false;
    }
  }

  public int hashCode() {
    return radius;
  }
}

class Oval {
  int majorRadius;
  int minorRadius;

  Oval(int major, int minor) {
    this.majorRadius = major;
    this.minorRadius = minor;
  }

  public int hashCode() {
    // assuming these big numbers are prime
    return (majorRadius * 713412413) + (minorRadius * 174124131); // continue for every field of the
                                                                  // class
  }
}

//A place to play around with different tests
class EqualityScratchPad {
  int messingAroundWithEquality() {

    // testing equality with ==
    if (1 == 1) {
      System.out.println("I knew it!");
    }
    else {
      System.out.println("Math is a lie!");
    }

    // testing equalit with .equals()
    String str1 = "im gonna ace this exam";
    String str2 = "im gonna ~finesse~ this exam";
    if (str1.equals(str2)) {
      System.out.println("Test with equals failed");
    }
    else {
      System.out.println("Test with equals succeeded (like me on the exam)");
    }

    Circle circle1 = new Circle(6);
    Oval oval1 = new Oval(6, 6); // circle1 and oval1 are kind of the same, but are they equal, and
                                 // are they the
    // same in memory?
    Circle circle2 = new Circle(4);
    Oval oval2 = new Oval(2, 3);
    Circle circle3 = new Circle(6);// circl1 and circl2 are kind of the same, but are they equal,
                                   // and are they the
    // same in memory?
    // 6 //wrong! 4
    if (circle1.radius == circle2.radius) {
      // wont go here
    }
    if (circle1.equals(circle3)) {
      // wont go here
    }

    return 0;
  }
}

/********** Visitors **********/

interface IList<T> {
  // a visitor's "entry point" into a list
  <U> U accept(IListVisitor<T, U> visitor);
}

class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;

  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }

  public <U> U accept(IListVisitor<T, U> visitor) {
    return visitor.visitCons(this); // let this visitor handle this IList as a ConsList
  }
}

class MTList<T> implements IList<T> {

  public <U> U accept(IListVisitor<T, U> visitor) {
    return visitor.visitEmpty(this);// let this visitor handle this IList as an MTList
  }

}

interface IListFunc<T, U> {
  U apply(IList<T> t);
}

interface IListVisitor<T, U> extends IListFunc<T, U> {

  U visitEmpty(MTList<T> t);

  U visitCons(ConsList<T> t);

}

class TotalLengthVisitor implements IListVisitor<String, Integer> {

  TotalLengthVisitor() {

  }

  public Integer apply(IList<String> t) {
    return t.accept(this);
  }

  public Integer visitEmpty(MTList<String> t) {
    return 0;
  }

  public Integer visitCons(ConsList<String> t) {
    return t.first.length() + this.apply(t.rest);
  }

}

/********** Iterators **********/

/**
 * A Class that creates an holds a certain kind of Iterator
 * https://docs.oracle.com/javase/8/docs/api/java/lang/Iterable.html
 *
 */
class EachCharacter implements Iterable<String> {
  String stringWereIteratingOver = ""; // the string to iterate over

  EachCharacter(String s) {
    this.stringWereIteratingOver = s;
  }

  @Override
  public Iterator<String> iterator() {
    return new AnIterator(stringWereIteratingOver);
  }

}

/**
 * https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
 * This example was made by mutating the string as we went, and always returning
 * the charector at index 0.
 * 
 * Try making another implemention on your own, but
 * use a counter that doesn't mutate the string but changes the counter to
 * represent the index to retrieve from.
 *
 */
class AnIterator implements Iterator<String> {
  String stringWereIteratingOver;

  AnIterator(String s) {
    this.stringWereIteratingOver = s;
  }

//  //Potential constructor (we can iterate over any object as long next() returns a String)
//  AnIterator(ArrayList<String> list) {
//    
//  }

  @Override
  public boolean hasNext() {
    return stringWereIteratingOver.length() >= 1;
    // or make a counter as a field
    // int curChar = 0;
    // return (curChar <= length )
  }

  @Override
  public String next() {
    String result = stringWereIteratingOver.substring(0, 1);
    // optional vvv
    if (stringWereIteratingOver.length() > 1) {
      stringWereIteratingOver = stringWereIteratingOver.substring(1);
    }
    // when the length is 1
    else {
      stringWereIteratingOver = "";
    }

    return result;
  }

}

class usingOurStringIterator {
  Iterator<String> ourIterator = new AnIterator("dog");

  ArrayList<String> giveUsTheCharacter() {
    ArrayList<String> listOfCharacters = new ArrayList<String>();
    while (ourIterator.hasNext()) {
      listOfCharacters.add(ourIterator.next());
    }
    return listOfCharacters;
  }
}

class ExamplesIterator {

  void testIterator(Tester t) {
    EachCharacter thing1 = new EachCharacter("dog"); Iterator<String> thing1siter = thing1.iterator();
    t.checkExpect(thing1siter.next(), "d"); t.checkExpect(thing1siter.hasNext(),true);
  
    t.checkExpect(thing1siter.next(), "o"); t.checkExpect(thing1siter.hasNext(),true);
  
    t.checkExpect(thing1siter.next(), "g"); t.checkExpect(thing1siter.hasNext(),false);
  
    t.checkException("some exception", thing1siter, "next()")
  }
}

interface IFoo {
  boolean sameFoo(IFoo that);

  boolean sameX(X that);

  boolean sameY(Y that);

  boolean sameZ(Z that);}

// In X:
public boolean sameFoo(IFoo that) { return that.sameX(this); }

public boolean sameX(X that) { ... compares two X values ... }

public boolean sameY(Y that) { return false; }

public boolean sameZ(Z that) { return false; }

  class hashCodeRandomClass {
    int one;
    int two;
    int three;

    boolean equals(object o) {
      if (this == o) {
        return true;
      }
      else if (o instanceof hashCodeRandomClass) {
        return this.one == o.one && this.two == o.two && this.three == o.three;
      }
      else {
        return false;
      }
    }

    public int hashCode() {
      return (one * 31) * (two * 31) * (three * 31); // can use string.hashCode() if a string
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import tester.Tester;

interface IFunc<A, R> {
  R apply(A arg);
}

interface IPred<T> extends IFunc<T, Boolean> {
  Boolean apply(T arg);
}

interface IComparator<T> {
  Boolean compare(T arg, T arg1);
}

interface IListVisitor<A, R> extends IFunc<IList<A>, R> {

  R visitMt(MtList<A> arg);

  R visitCons(ConsList<A> arg);

  R apply(IList<A> arr);
}

class OrPred<T> implements IPred<T> {

  IPred<T> left;
  IPred<T> right;

  OrPred(IPred<T> left, IPred<T> right) {
    this.left = left;
    this.right = right;
  }

  public Boolean apply(T arg) {
    return left.apply(arg) || right.apply(arg);
  }

}

class ArrayUtils {

  int sum(ArrayList<Integer> list) {
    int sum = 0;
    for (int i : list) {
      sum += i;
    }
    return sum;
  }

  boolean positivePartialSums(ArrayList<Integer> list) {
    int sum = 0;
    for (int i : list) {
      sum += i;
      if (sum < 0) {
        return false;
      }
    }
    return true;
  }

  boolean containsSequence(ArrayList<Integer> source, ArrayList<Integer> sequence) {
    ArrayList<Integer> partial = new ArrayList<Integer>();
    int indInSeq = 0;
    for (int i = 0; i < source.size(); i++) {
      if (sequence.get(indInSeq) == source.get(i)) {
        partial.add(source.get(i));
        indInSeq++;
      }
    }
    System.out.println(partial);
    return sequence.equals(partial);
  }
}

class StringIterator implements Iterator<Character> {

  String str;
  int curInd;

  StringIterator(String str) {
    this.str = str;
    this.curInd = 0;
  }

  public boolean hasNext() {
    return curInd < str.length();
  }

  public Character next() {
    char temp = str.charAt(curInd);
    curInd++;
    return temp;
  }

}

class StringWrapper implements Iterable<Character> {

  String str;

  StringWrapper(String str) {
    this.str = str;
  }

  public StringIterator iterator() {
    return new StringIterator(this.str);
  }
}

class EveryOtherIter<T> implements Iterator<T>, Iterable<T> {
  Iterator<T> iter;

  EveryOtherIter(Iterator<T> iter) {
    this.iter = iter;
  }

  public boolean hasNext() {
    return iter.hasNext();
  }

  public T next() {
    T temp = this.iter.next();
    if (this.iter.hasNext()) {
      this.iter.next();
    }
    return temp;
  }

  @Override
  public Iterator<T> iterator() {
    return this;
  }
}

class ExamplesPractice {

  ArrayList<Integer> ints = new ArrayList<Integer>(Arrays.asList(1, 2, -5, 4));
  ArrayList<Integer> seq = new ArrayList<Integer>(Arrays.asList(1, 2, 4));
  StringWrapper hi = new StringWrapper("hello");
  EveryOtherIter<Character> iter = new EveryOtherIter<Character>(hi.iterator());

  void testPartialSum(Tester t) {
    t.checkExpect(new ArrayUtils().positivePartialSums(this.ints), false);
    t.checkExpect(new ArrayUtils().containsSequence(this.ints, this.seq), true);

    for (char c : iter) {
      System.out.println(c);
    }

  }

}

import java.util.Iterator;

import javax.management.RuntimeErrorException;

import tester.Tester;

/*
 * THINGS TO STUDY: ALIASING, ITERABLE() & ITERATOR(), LOOPS, DEQUES, TREE
 * REVIEW, EQUALITY & HASHCODE
 * 
 * THINGS TO DO: IN CHRONOLOGICAL ORDER
 * 
 * MONDAY:
 * 
 * DO DEQUE IMPLEMENTATION FROM SCRATCH: implement Deque.reverse(), which
 * reverses all the items in a deque do deque homework assignment
 * 
 * DO PRACTICE ALIASING PROBLEMS: problems on piazza
 * 
 * TUESDAY:
 * 
 * HASHCODE() / EQUALS()
 * 
 * ITERABLE() AND ITERATOR(): make IList<T> iterable, use them in loops review
 * lecture notes on topic
 * 
 * WEDNESDAY:
 * 
 * DO ALL REVIEW PROBLEMS ON PIAZZA
 * 
 * (IF NECESSARY) IFunc<T> and IPred<T>, Tree Review
 * 
 * CHEATSHEET
 * 
 * ACCOUTNING
 * 
 * PROSPER :>
 */

class IListIterator<T> implements Iterator<T> {

  IList<T> list;

  IListIterator(IList<T> l) {
    this.list = l;
  }

  // tells the Iterator that there is another item
  public boolean hasNext() {
    // doesnt matter if this.rest is empty!!!!
    return this.list instanceof ConsList<?>;
  }

  // gets the next item and progresses through the list
  public T next() {
    ConsList<T> temp = this.list.asCons();
    this.list = temp.rest;
    return temp.first;
  }

}

interface IList<T> extends Iterable<T> {

  ConsList<T> asCons();

  <R> R accept(IListVisitor<T, R> visitor);

}

class MtList<T> implements IList<T> {

  public Iterator<T> iterator() {
    return new IListIterator<T>(this);
  }

  public ConsList<T> asCons() {
    throw new IllegalArgumentException("Cannot Cast Mt to Cons!");
  }

  public <R> R accept(IListVisitor<T, R> visitor) {
    return visitor.visitMt(this);
  }

}

class ConsList<T> implements IList<T> {

  T first;
  IList<T> rest;

  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }

  public Iterator<T> iterator() {
    return new IListIterator<T>(this);
  }

  public ConsList<T> asCons() {
    return this;
  }

  public <R> R accept(IListVisitor<T, R> visitor) {
    return visitor.visitCons(this);
  }

}

class DequeIterator<T> implements Iterator<T> {

  Deque<T> list;
  ANode<T> node;

  DequeIterator(Deque<T> list) {
    this.list = list;
    this.node = this.list.header.next.asNode();
  }

  // checks if the current node is a Node, not a Sentinel, which means that it can continue
  // to traverse
  public boolean hasNext() {
    return this.node instanceof Node;
  }

  // gets the data from the current node, returns it, and continues to traverse
  public T next() {
    T temp = this.node.asNode().data;
    this.node = this.node.next;
    return temp;
  }

}

class Queue<T> {
  Deque<T> contents;

  Queue(Deque<T> contents) {
    this.contents = contents;
  }

  boolean isEmpty() {
    return this.contents.isEmpty();
  }

  void enqueue(T item) {
    contents.addAtTail(item);
  }

  T dequeue() {
    return this.contents.removeFromHead();
  }
}

interface INumBinTree {
  boolean isLeaf();

  NumNode asNode();
}

class NumNode implements INumBinTree {
  int value;
  INumBinTree left;
  INumBinTree right;

  NumNode(int value, INumBinTree left, INumBinTree right) {
    this.value = value;
    this.left = left;
    this.right = right;
  }

  public boolean isLeaf() {
    return false;
  }

  public NumNode asNode() {
    return this;
  }
}

class NumLeaf implements INumBinTree {

  public boolean isLeaf() {
    return true;
  }

  public NumNode asNode() {
    throw new IllegalArgumentException("Cannot Cast a Leaf to a Node");
  }
}

class Num implements Iterator<Integer> {
  int n;

  Num(int n) {
    this.n = n;
  }

  public boolean hasNext() {
    return true;
  }

  public Integer next() {
    this.n += 1;
    return n;
  }
}

class GetN implements Iterator<Integer>, Iterable<Integer> {

  Iterator<Integer> iter;
  int n;

  GetN(Iterator<Integer> iter, int n) {
    this.iter = iter;
    this.n = n;
  }

  public Iterator<Integer> iterator() {
    return this;
  }

  public boolean hasNext() {
    return this.iter.hasNext() && n > 0;
  }

  public Integer next() {
    int result = this.iter.next();
    n--;
    return result;
  }

}

class ExamplesIterable {
  IList<Integer> mtint = new MtList<Integer>();
  IList<Integer> int1 = new ConsList<Integer>(1, this.mtint);
  IList<Integer> int2 = new ConsList<Integer>(2, this.int1);
  Node<Integer> n1 = new Node<Integer>(1);
  Node<Integer> n2 = new Node<Integer>(2);
  Sentinel<Integer> s1 = new Sentinel<Integer>(this.n1, this.n2);
  Deque<Integer> d1 = new Deque<Integer>(this.s1);
  Num num1 = new Num(0);
  GetN get3 = new GetN(num1, 4);

  void initDeque() {
    this.n1 = new Node<Integer>(1, this.n2, this.s1);
    this.n2 = new Node<Integer>(2, this.s1, this.n1);
    this.d1 = new Deque<Integer>(this.s1);
  }

  void testIterable(Tester t) {
    this.initDeque();

    int sum = 0;
    for (int i : this.int2) {
      sum = sum + i;
    }
    t.checkExpect(sum, 3);

    int sum2 = 0;
    for (int n : this.d1) {
      sum2 += n;
    }
    t.checkExpect(sum2, 3);
  }

  void testGetN(Tester t) {
    for (int n : this.get3) {
      System.out.println(n);
    }
  }
}
