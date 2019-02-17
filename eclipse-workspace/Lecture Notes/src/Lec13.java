import tester.Tester;

class Runner {
  String name;
  int age;
  int bib;
  boolean isMale;
  int pos;
  int time;

  Runner(String name, int age, int bib, boolean isMale, int pos, int time) {
    this.name = name;
    this.age = age;
    this.bib = bib;
    this.isMale = isMale;
    this.pos = pos;
    this.time = time;
  }
}

interface IRunnerPredicate {
  boolean apply(Runner r);
}

class RunnerIsMale implements IRunnerPredicate {
  public boolean apply(Runner r) {
    return r.isMale;
  }
}

class RunnerIsFemale implements IRunnerPredicate {
  public boolean apply(Runner r) {
    return !r.isMale;
  }
}

class RunnerIsInFirst50 implements IRunnerPredicate {
  public boolean apply(Runner r) {
    return r.pos <= 50;
  }
}

class FinishIn4Hours implements IRunnerPredicate {
  public boolean apply(Runner r) {
    return r.time < 240;
  }
}

class AndPredicate implements IRunnerPredicate {
  IRunnerPredicate left, right;

  AndPredicate(IRunnerPredicate left, IRunnerPredicate right) {
    this.left = left;
    this.right = right;
  }

  public boolean apply(Runner r) {
    return left.apply(r) && right.apply(r);
  }
}

interface ILoRunner {
  ILoRunner find(IRunnerPredicate pred);
}

class MtLoRunner implements ILoRunner {

  public ILoRunner find(IRunnerPredicate pred) {
    return this;
  }
}

class ConsLoRunner implements ILoRunner {
  Runner first;
  ILoRunner rest;

  ConsLoRunner(Runner first, ILoRunner rest) {
    this.first = first;
    this.rest = rest;
  }

  public ILoRunner find(IRunnerPredicate pred) {
    if (pred.apply(this.first)) {
      return new ConsLoRunner(this.first, this.rest.find(pred));
    }
    else {
      return this.rest.find(pred);
    }
  }
}

class ExamplesRunner {
//In Examples class
  Runner johnny = new Runner("Kelly", 97, 999, true, 30, 360);
  Runner frank = new Runner("Shorter", 32, 888, true, 245, 130);
  Runner bill = new Runner("Rogers", 36, 777, true, 119, 129);
  Runner joan = new Runner("Benoit", 29, 444, false, 18, 155);

  ILoRunner mtlist = new MtLoRunner();
  ILoRunner list1 = new ConsLoRunner(johnny, new ConsLoRunner(joan, mtlist));
  ILoRunner list2 = new ConsLoRunner(frank, new ConsLoRunner(bill, list1));

  boolean testFindMethods(Tester t) {
    return t.checkExpect(this.list2.find(new RunnerIsFemale()),
        new ConsLoRunner(this.joan, new MtLoRunner()))
        && t.checkExpect(this.list2.find(new RunnerIsMale()), new ConsLoRunner(this.frank,
            new ConsLoRunner(this.bill, new ConsLoRunner(this.johnny, new MtLoRunner()))));
  }

  boolean testCombinedQuestions(Tester t) {
    return t.checkExpect(
        this.list2.find(new AndPredicate(new RunnerIsMale(), new FinishIn4Hours())),
        new ConsLoRunner(this.frank, new ConsLoRunner(this.bill, new MtLoRunner())));
  }

  // In Examples class
  boolean testFindUnder4Hours(Tester t) {
    return t.checkExpect(this.list2.find(new FinishIn4Hours()), new ConsLoRunner(this.frank,
        new ConsLoRunner(this.bill, new ConsLoRunner(this.joan, new MtLoRunner()))));
  }

}

