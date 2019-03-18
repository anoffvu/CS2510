import tester.Tester;

//Represents a boolean-valued question over values of type T
interface IPred<T> {
  boolean apply(T t);
}

// predicate that tests of two strings are the same
class SameString implements IPred<String> {
  String s;

  SameString(String s) {
    this.s = s;
  }

  public boolean apply(String that) {
    return this.s.equals(that);
  }
}

// predicate that test of two ints are the same
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

// abstract class for deque nodes
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

// a sentinel of a list
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

// a node inside a deque
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

class ExamplesDeque {
  // example
  Deque<String> deque1;

  Sentinel<String> deque2Sent;
  ANode<String> nodeABC;
  ANode<String> nodeBCD;
  ANode<String> nodeCDE;
  ANode<String> nodeDEF;
  Deque<String> deque2;

  Sentinel<String> deque3Sent;
  ANode<String> nodeFirst;
  ANode<String> nodeSecond;
  ANode<String> nodeThird;
  ANode<String> nodeFourth;
  ANode<String> nodeFifth;
  Deque<String> deque3;

  Deque<String> shortDeque;
  ANode<String> shortNode;

  ANode<String> ENode1;
  Sentinel<String> ESentinel1;
  Deque<String> EDeque1;

  // Effect: resets all data
  void initData() {
    this.deque1 = new Deque<String>();

    this.deque2Sent = new Sentinel<String>();
    this.nodeABC = new Node<String>("abc", this.deque2Sent, this.deque2Sent);
    this.nodeBCD = new Node<String>("bcd", this.deque2Sent, this.nodeABC);
    this.nodeCDE = new Node<String>("cde", this.deque2Sent, this.nodeBCD);
    this.nodeDEF = new Node<String>("def", this.deque2Sent, this.nodeCDE);
    this.deque2 = new Deque<String>(this.deque2Sent);

    this.deque3Sent = new Sentinel<String>();
    this.nodeFirst = new Node<String>("first", this.deque3Sent, this.deque3Sent);
    this.nodeSecond = new Node<String>("second", this.deque3Sent, this.nodeFirst);
    this.nodeThird = new Node<String>("third", this.deque3Sent, this.nodeSecond);
    this.nodeFourth = new Node<String>("fourth", this.deque3Sent, this.nodeThird);
    this.nodeFifth = new Node<String>("fifth", this.deque3Sent, this.nodeFourth);
    this.deque3 = new Deque<String>(this.deque3Sent);

    this.ENode1 = new Node<String>("node 1");
    this.ESentinel1 = new Sentinel<String>();
    this.EDeque1 = new Deque<String>(this.ESentinel1);

    this.shortDeque = new Deque<String>();
    this.shortNode = new Node<String>("short 1", this.shortDeque.header, this.shortDeque.header);

  }

  // tests for node constructor
  void testNodeConstructor(Tester t) {
    initData();
    t.checkConstructorException(
        new IllegalArgumentException("You cannot make a node pointing to null objects!"), "Node",
        "hi", null, null);
    t.checkConstructorException(
        new IllegalArgumentException("You cannot make a node pointing to null objects!"), "Node",
        "hi", this.ENode1, null);
    t.checkConstructorException(
        new IllegalArgumentException("You cannot make a node pointing to null objects!"), "Node",
        "hi", null, this.ENode1);
  }

  // test for sentinel constructor
  void testsentinelConstructor(Tester t) {
    initData();
    t.checkExpect(this.ESentinel1.next, this.ESentinel1);
  }

  // testing to see if examples are constructed correctly, order and linking
  void testDequeBuilding(Tester t) {
    initData();
    t.checkExpect(this.deque3.header.next, this.nodeFirst);
    t.checkExpect(this.deque3.header.next.next, this.nodeSecond);
    t.checkExpect(this.deque3.header.next.next.next.next.next.next, this.deque3.header);
    t.checkExpect(this.deque3.header.next.next.next.next.next, this.nodeFifth);
    t.checkExpect(this.deque3.header.prev, this.nodeFifth);
    t.checkExpect(this.deque3.header.prev.prev, this.nodeFourth);
    t.checkExpect(this.deque3.header.prev.prev.prev, this.nodeThird);
    t.checkExpect(this.deque3.header.prev.prev.prev.prev, this.nodeSecond);
    t.checkExpect(this.deque3.header.prev.prev.prev.prev.prev, this.nodeFirst);
    t.checkExpect(this.deque3.header.prev.prev.prev.prev.prev.prev, this.deque3.header);
  }

  // tests for size
  void testSize(Tester t) {
    initData();
    t.checkExpect(this.deque1.size(), 0);
    t.checkExpect(this.deque2.size(), 4);
    t.checkExpect(this.deque3.size(), 5);
    t.checkExpect(this.shortDeque.size(), 1);
  }

  // tests for addAtHead
  void testaddAtHead(Tester t) {
    initData();
    t.checkExpect(this.EDeque1.header.next, this.ESentinel1);
    this.EDeque1.addAtHead("hello");
    t.checkExpect(this.EDeque1.header.next,
        new Node<String>("hello", this.ESentinel1, this.ESentinel1));
    this.deque3.addAtHead("hello");
    t.checkExpect(this.deque3.header.next,
        new Node<String>("hello", this.nodeFirst, this.deque3.header));
    t.checkExpect(this.nodeFirst.prev,
        new Node<String>("hello", this.nodeFirst, this.deque3.header));
  }

  // tests for addAtTail
  void testaddAtTail(Tester t) {
    initData();
    t.checkExpect(this.EDeque1.header.prev, this.ESentinel1);
    this.EDeque1.addAtTail("hello");
    t.checkExpect(this.EDeque1.header.prev,
        new Node<String>("hello", this.ESentinel1, this.ESentinel1));
    this.deque3.addAtTail("hello");
    t.checkExpect(this.deque3.header.prev,
        new Node<String>("hello", this.deque3.header, this.nodeFifth));
    t.checkExpect(this.nodeFifth.next,
        new Node<String>("hello", this.deque3.header, this.nodeFifth));
  }

  // tests for removeFromHead
  void testremoveFromHead(Tester t) {
    initData();
    t.checkExpect(this.shortDeque.header.next, this.shortNode);
    t.checkExpect(this.shortDeque.size(), 1);
    t.checkExpect(this.shortDeque.removeFromHead(), "short 1");
    t.checkExpect(this.shortDeque.size(), 0);
    t.checkExpect(this.deque3.header.next, this.nodeFirst);
    t.checkExpect(this.deque3.removeFromHead(), "first");
    t.checkExpect(this.deque3.header.next, this.nodeSecond);
    t.checkExpect(this.deque3.header.next.next, this.nodeThird);
    t.checkExpect(this.deque3.header.prev.prev.prev.prev.prev, this.deque3.header);

  }

  // tests for removeFromTail
  void testremoveFromTail(Tester t) {
    initData();
    t.checkExpect(this.shortDeque.header.next, this.shortNode);
    t.checkExpect(this.shortDeque.size(), 1);
    t.checkExpect(this.shortDeque.removeFromTail(), "short 1");
    t.checkExpect(this.shortDeque.size(), 0);
    t.checkExpect(this.deque3.removeFromTail(), "fifth");
  }

  // tests for find
  void testFind(Tester t) {
    initData();
    t.checkExpect(this.deque2.find(new SameString("abc")), this.nodeABC);
    t.checkExpect(this.deque2.find(new SameString("def")), this.nodeDEF);
    t.checkExpect(this.deque2.find(new SameString("abcc")), this.deque2Sent);

  }

  // tests for removeNode
  void testRemoveNode(Tester t) {
    initData();
    t.checkExpect(this.deque2.header.next, this.nodeABC);
    this.deque2.removeNode(this.nodeABC);
    t.checkExpect(this.deque2.header.next, this.nodeBCD);
    t.checkExpect(this.deque2.header.prev.prev.prev.prev, this.deque2Sent);
    this.deque2.removeNode(this.nodeABC);
    t.checkExpect(this.deque2.header.next, this.nodeBCD);
    t.checkExpect(this.deque2.header.prev.prev.prev.prev, this.deque2Sent);

    t.checkExpect(this.deque3.header.next.next.next, this.nodeThird);
    this.deque2.removeNode(this.nodeThird);
    t.checkExpect(this.deque3.header.next.next.next, this.nodeFourth);
    t.checkExpect(this.deque3.header.prev.prev.prev, this.nodeSecond);

  }
}