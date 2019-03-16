import tester.Tester;

abstract class ANode<T> {
  ANode<T> next;
  ANode<T> prev;

  ANode(ANode<T> next, ANode<T> prev) {
    this.next = next;
    this.prev = prev;
  }

  ANode() {
    this.next = null;
    this.prev = null;
  }

  public void updateNext(ANode<T> node) {
    this.prev = node;
  }

  public void updatePrev(ANode<T> node) {
    this.next = node;
  }

}

class Sentinal<T> extends ANode<T> {
  Sentinal(ANode<T> next, ANode<T> prev) {
    super(next, prev);
  }

  Sentinal() {
    super();
    this.next = this;
    this.prev = this;
  }
}

class Node<T> extends ANode<T> {
  T data;
  ANode<T> next;
  ANode<T> prev;

  Node(T data) {
    super(null, null);
    this.data = data;
  }

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

class Deque<T>{
  Sentinal<T> header;

  Deque() {
    this.header = new Sentinal<T>();
  }

  Deque(Sentinal<T> header) {
    this.header = header;
  }
}

class ExamplesDeque {
  // examples
  ANode<String> ESentinal1 = new Sentinal<String>();
  ANode<String> ENode1 = new Node<String>("ENode1");
  ANode<String> ENode2 = new Node<String>("ENode2");
  ANode<String> Node1 = new Node<String>("Node1", this.ESentinal1, this.ESentinal1);
  ANode<String> Sentinal1 = new Sentinal<String>(this.ENode1, this.ENode2);
  
  ANode<String> ESentinal2 = new Sentinal<String>();

  // state sanctioned examples
  Deque<String> deque1 = new Deque<String>();

  Deque<String> deque2 = new Deque<String>();
  ANode<String> nodeABC = new Node<String>("abc", this.deque2.header, this.deque2.header);
  ANode<String> nodeBCD = new Node<String>("bcd", this.deque2.header, this.nodeABC);
  ANode<String> nodeCDE = new Node<String>("cde", this.deque2.header, this.nodeBCD);
  ANode<String> nodeDEF = new Node<String>("def", this.deque2.header, this.nodeCDE);

  Deque<String> deque3 = new Deque<String>();
  ANode<String> nodeFirst = new Node<String>("first", this.deque3.header, this.deque3.header);
  ANode<String> nodeSecond = new Node<String>("second", this.deque3.header, this.nodeFirst);
  ANode<String> nodeThird = new Node<String>("third", this.deque3.header, this.nodeSecond);
  ANode<String> nodeFourth = new Node<String>("fourth", this.deque3.header, this.nodeThird);

  // resets all data
  void initData() {
    this.ESentinal1 = new Sentinal<String>();
    this.ENode1 = new Node<String>("ENode1");
    this.ENode2 = new Node<String>("ENode2");
    this.Node1 = new Node<String>("Node1", this.ESentinal1, this.ESentinal1);
    this.Sentinal1 = new Sentinal<String>(this.ENode1, this.ENode2);

    this.deque1 = new Deque<String>();

    this.deque2 = new Deque<String>();
    this.nodeABC = new Node<String>("abc", this.deque2.header, this.deque2.header);
    this.nodeBCD = new Node<String>("bcd", this.deque2.header, this.nodeABC);
    this.nodeCDE = new Node<String>("cde", this.deque2.header, this.nodeBCD);
    this.nodeDEF = new Node<String>("def", this.deque2.header, this.nodeCDE);

    this.deque3 = new Deque<String>();
    this.nodeFirst = new Node<String>("first", this.deque3.header, this.deque3.header);
    this.nodeSecond = new Node<String>("second", this.deque3.header, this.nodeFirst);
    this.nodeThird = new Node<String>("third", this.deque3.header, this.nodeSecond);
    this.nodeFourth = new Node<String>("fourth", this.deque3.header, this.nodeThird);
  }

  // tests for node constructor
  void testNodeConstructor(Tester t) {
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

  void testSentinalConstructor(Tester t) {
    t.checkExpect(this.ESentinal2.next, this.ESentinal2);
  }
}