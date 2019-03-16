import tester.Tester;

// An updatable object that has some state of type X
// that can be updated
interface Updatable<X> {
  // get the current state
  X currentState();

  // update to the next state
  void nextState();
}

// Functional object
interface IFunc<X, Y> {
  Y apply(X x);
}

// produces function objects that adds 0, then 1, then 2... etc.
class Adders implements Updatable<IFunc<Integer, Integer>> {
  int addThisMuch = 0;

  // initial state: produces function objects that add 0
  Adders() {
  }

  public IFunc<Integer, Integer> currentState() {
    return new Adder(addThisMuch);
  }

  public void nextState() {
    this.addThisMuch = this.addThisMuch + 1;
  }
}

//extra class
class Adder implements IFunc<Integer, Integer> {
  int toAdd;

  Adder(int toAdd) {
    this.toAdd = toAdd;
  }

  public Integer apply(Integer i) {
    return i + this.toAdd;
  }
}

class ExamplesAdder {
  Updatable<IFunc<Integer, Integer>> adders;

  // set adders to have a current state of outputting functions that add 0
  void initializeData() {
    this.adders = new Adders();
  }

  // ? = 19, because the next add would be 16 + 3
  void testAdders(Tester t) {
    this.initializeData();
    t.checkExpect(adders.currentState().apply(50), 50);
    adders.nextState();
    t.checkExpect(adders.currentState().apply(11), 12);
    adders.nextState();
    t.checkExpect(adders.currentState().apply(400), 402);
    adders.nextState();
    t.checkExpect(adders.currentState().apply(16), ?);
  }
}
