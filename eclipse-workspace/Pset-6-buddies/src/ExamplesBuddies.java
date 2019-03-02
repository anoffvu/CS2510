import tester.Tester;


// runs tests for the buddies problem
public class ExamplesBuddies{
  Person p1 = new Person("p1");
  Person p2 = new Person("p2");
  Person p3 = new Person("p3");
  Person p4 = new Person("p4");
  Person p5 = new Person("p5");
  Person p6 = new Person("p6");
  Person p7 = new Person("p7");

  // initializes the data
  void initData() {
    this.p1 = new Person("p1");
    this.p2 = new Person("p2");
    this.p3 = new Person("p3");
    this.p4 = new Person("p4");
    this.p5 = new Person("p5");
    this.p6 = new Person("p6");
    this.p7 = new Person("p7");
    this.p3.addBuddy(this.p1);
    this.p4.addBuddy(this.p2);
    this.p5.addBuddy(this.p3);
    this.p5.addBuddy(this.p4);
    this.p6.addBuddy(this.p5);
    this.p6.addBuddy(this.p4);
    this.p6.addBuddy(this.p1);
    this.p7.addBuddy(this.p5);
    this.p7.addBuddy(this.p4);
    this.p7.addBuddy(this.p1);
    this.p7.addBuddy(this.p2);
    this.p5.addBuddy(this.p6);

  }

  // tests for addBuddy
  void testAddBuddy(Tester t) {
    initData();
    t.checkExpect(p1.buddies, new MTLoBuddy());
    this.p1.addBuddy(this.p2);
    t.checkExpect(p1.buddies, new ConsLoBuddy(this.p2, new MTLoBuddy()));
    t.checkExpect(p2.buddies, new MTLoBuddy());
    this.p2.addBuddy(this.p1);
    t.checkExpect(p2.buddies, new ConsLoBuddy(this.p1, new MTLoBuddy()));
  }

  // tests for hasDirectBuddy
  void testHasDirectBuddy(Tester t) {
    initData();
    t.checkExpect(this.p1.hasDirectBuddy(p2), false);
    t.checkExpect(this.p1.hasDirectBuddy(p1), false);
    t.checkExpect(this.p3.hasDirectBuddy(p2), false);
    t.checkExpect(this.p3.hasDirectBuddy(p1), true);
    t.checkExpect(this.p5.hasDirectBuddy(p1), false);
    t.checkExpect(this.p5.hasDirectBuddy(p3), true);
    t.checkExpect(this.p5.hasDirectBuddy(p4), true);
    t.checkExpect(this.p6.hasDirectBuddy(p1), true);
    t.checkExpect(this.p6.hasDirectBuddy(p4), true);
    t.checkExpect(this.p6.hasDirectBuddy(p5), true);
  }

  // tests for countCommonBuddies
  void testCountCommonBuddies(Tester t) {
    initData();
    t.checkExpect(this.p1.countCommonBuddies(p2), 0);
    t.checkExpect(this.p1.countCommonBuddies(p1), 0);
    t.checkExpect(this.p4.countCommonBuddies(p5), 0);
    t.checkExpect(this.p5.countCommonBuddies(p4), 0);
    t.checkExpect(this.p6.countCommonBuddies(p5), 1);
    t.checkExpect(this.p6.countCommonBuddies(p3), 1);
    t.checkExpect(this.p6.countCommonBuddies(p7), 3);
    t.checkExpect(this.p7.countCommonBuddies(p6), 3);
    t.checkExpect(this.p7.countCommonBuddies(p1), 0);
  }

  // TODO: tests on circular data
}