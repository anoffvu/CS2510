import tester.Tester;

//represents a list that can add things on at both ends
class Deque<T> {
  Sentinal<T> header;

  // default constructor
  Deque() {
    this.header = new Sentinal<T>();
  }

  // convenience constructor passing in a sentinal node
  Deque(Sentinal<T> header) {
    this.header = header;
  }

  // adds that data to the head of this deque
  public void addAtHead(T that) {
    this.header.addAtHead(that);
  }

  // adds that data to the tail of this deque
  public void addAtTail(T that) {
    this.header.addAtTail(that);
  }

  // adds that data to the head of this deque
  public void removeAtHead() {
    this.header.removeAtHead();
  }

  // adds that data to the tail of this deque
  public void removeAtTail() {
    this.header.removeAtTail();
  }

  // returns the size of this
  public int size() {
    return this.header.calcSize(0);
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

  // links the next node
  public void updateNext(ANode<T> node) {
    this.prev = node;
  }

  // links the previous node
  public void updatePrev(ANode<T> node) {
    this.next = node;
  }

  // tests if this ANode is a sentinal
  public boolean isSentinal() {
    return false;
  }

  // calculates the size of the this linked list of nodes
  public int calcSize(int count) {
    if (this.next.isSentinal()) {
      return count;
    }
    else {
      return this.next.calcSize(count + 1);
    }
  }

}

// a sentinal of a list
class Sentinal<T> extends ANode<T> {
  Sentinal(ANode<T> next, ANode<T> prev) {
    super(next, prev);
  }

  Sentinal() {
    super();
    this.next = this;
    this.prev = this;
  }

  // adds a new node with that data at the head
  public void addAtHead(T that) {
    this.next = new Node<T>(that, this.next, this);
  }

  // adds a new node with that data at the tail
  public void addAtTail(T that) {
    this.prev = new Node<T>(that, this, this.prev);

  }

  // tests if this sentinal is a sentinal
  public boolean isSentinal() {
    return true;
  }

  // removes the node at the head of this list
  public void removeAtHead() {
    if (this.next.isSentinal()) {
      throw new RuntimeException("Can't remove any nodes from an empty list!");
    }
    else {
      this.next = this.next.next;
      this.next.next.prev = this;
    }
  }

  // removes the node at the tail of this list
  public void removeAtTail() {
    if (this.next.isSentinal()) {
      throw new RuntimeException("Can't remove any nodes from an empty list!");
    }
    else {
      this.prev = this.prev.prev;
      this.prev.prev.next = this;
    }
  }
}

// a node inside a deque
class Node<T> extends ANode<T> {
  T data;
  ANode<T> next;
  ANode<T> prev;

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
      next.updateNext(this);
      prev.updatePrev(this);
    }
  }

}

class ExamplesDeque {
  // example
  Deque<String> deque1;

  Sentinal<String> deque2Sent;
  ANode<String> nodeABC;
  ANode<String> nodeBCD;
  ANode<String> nodeCDE;
  ANode<String> nodeDEF;
  Deque<String> deque2;

  Sentinal<String> deque3Sent;
  ANode<String> nodeFirst;
  ANode<String> nodeSecond;
  ANode<String> nodeThird;
  ANode<String> nodeFourth;
  ANode<String> nodeFifth;
  Deque<String> deque3;

  Deque<String> shortDeque;
  ANode<String> shortNode;

  ANode<String> ENode1;
  Sentinal<String> ESentinal1;
  Deque<String> EDeque1;

  // Effect: resets all data
  void initData() {
    this.deque1 = new Deque<String>();

    this.deque2Sent = new Sentinal<String>();
    this.nodeABC = new Node<String>("abc", this.deque2Sent, this.deque2Sent);
    this.nodeBCD = new Node<String>("bcd", this.deque2Sent, this.nodeABC);
    this.nodeCDE = new Node<String>("cde", this.deque2Sent, this.nodeBCD);
    this.nodeDEF = new Node<String>("def", this.deque2Sent, this.nodeCDE);
    this.deque2 = new Deque<String>(this.deque2Sent);

    this.deque3Sent = new Sentinal<String>();
    this.nodeFirst = new Node<String>("first", this.deque3Sent, this.deque3Sent);
    this.nodeSecond = new Node<String>("second", this.deque3Sent, this.nodeFirst);
    this.nodeThird = new Node<String>("third", this.deque3Sent, this.nodeSecond);
    this.nodeFourth = new Node<String>("fourth", this.deque3Sent, this.nodeThird);
    this.nodeFifth = new Node<String>("fifth", this.deque3Sent, this.nodeFourth);
    this.deque3 = new Deque<String>(this.deque3Sent);

    this.ENode1 = new Node<String>("node 1");
    this.ESentinal1 = new Sentinal<String>();
    this.EDeque1 = new Deque<String>(this.ESentinal1);

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

  // test for sentinal constructor
  void testSentinalConstructor(Tester t) {
    initData();
    t.checkExpect(this.ESentinal1.next, this.ESentinal1);
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
    t.checkExpect(this.EDeque1.header.next, this.ESentinal1);
    this.EDeque1.addAtHead("hello");
    t.checkExpect(this.EDeque1.header.next,
        new Node<String>("hello", this.ESentinal1, this.ESentinal1));
    // tests with longer lists
  }

  // tests for addAtTail
  void testaddAtTail(Tester t) {
    initData();
    t.checkExpect(this.EDeque1.header.prev, this.ESentinal1);
    this.EDeque1.addAtTail("hello");
    t.checkExpect(this.EDeque1.header.prev,
        new Node<String>("hello", this.ESentinal1, this.ESentinal1));
    // tests with longer lists
  }

  void testRemoveAtHead(Tester t) {
    initData();
    t.checkExpect(this.shortDeque.header.next, this.shortNode);
    t.checkExpect(this.shortDeque.size(), 1);
    this.shortDeque.removeAtHead();
    t.checkExpect(this.shortDeque.header.next, this.shortDeque.header);
    t.checkExpect(this.shortDeque.size(), 0);
  }

  void testRemoveAtTail(Tester t) {
    initData();
    t.checkExpect(this.shortDeque.header.next, this.shortNode);
    t.checkExpect(this.shortDeque.size(), 1);
    this.shortDeque.removeAtTail();
    t.checkExpect(this.shortDeque.header.next, this.shortDeque.header);
    t.checkExpect(this.shortDeque.size(), 0);
  }

}