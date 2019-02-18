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

interface ICompareRunners {
  // determines if the first runner comes before the second in a sorted list
  int comesBefore(Runner r1, Runner r2);
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

//To compute a three-way comparison between two Runners
interface IRunnerComparator {
// Returns a negative number if r1 comes before r2 in this order
// Returns zero              if r1 is tied with r2 in this order
// Returns a positive number if r1 comes after  r2 in this order
  int compare(Runner r1, Runner r2);
}

class CompareByTime implements IRunnerComparator {
  public int compare(Runner r1, Runner r2) {
    return r1.time - r2.time;
  }
}

class CompareByName implements IRunnerComparator {
  public int compare(Runner r1, Runner r2) {
    return r1.name.compareTo(r2.name);
  }
}

interface ILoRunner {
  ILoRunner find(IRunnerPredicate pred);

  ILoRunner sortBy(IRunnerComparator comp);

  ILoRunner insertBy(IRunnerComparator comp, Runner r);

  Runner findMin(IRunnerComparator comp);

//Returns the minimum Runner of the given accumulator and every Runner
//in this list, according to the given comparator
  Runner findMinHelp(IRunnerComparator comp, Runner acc);

}

class MtLoRunner implements ILoRunner {

  public ILoRunner find(IRunnerPredicate pred) {
    return this;
  }

  public ILoRunner sortBy(IRunnerComparator comp) {
    return this;
  }

  public ILoRunner insertBy(IRunnerComparator comp, Runner r) {
    return new ConsLoRunner(r, this);
  }

  // returns an error when finding the fastest finisher of an empty list
  public Runner findMin(IRunnerComparator comp) {
    throw new RuntimeException("There is no winner of an empty list!");
  }

  @Override
  public Runner findMinHelp(IRunnerComparator comp, Runner acc) {
    return acc;
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

  public ILoRunner sortBy(IRunnerComparator comp) {
    return this.rest.sortBy(comp).insertBy(comp, this.first);
  }

  public ILoRunner insertBy(IRunnerComparator comp, Runner r) {
    // comp.compare will return a negative number if its first argument comes first
    if (comp.compare(this.first, r) < 0) {
      return new ConsLoRunner(this.first, this.rest.insertBy(comp, r));
    }
    else {
      return new ConsLoRunner(r, this);
    }
  }

  public Runner findMinHelp(IRunnerComparator comp, Runner acc) {
    if (comp.compare(acc, this.first) < 0) {
      return this.rest.findMinHelp(comp, acc);
    }
    else {
      return this.rest.findMinHelp(comp, this.first);
    }
  }

  public Runner findMin(IRunnerComparator comp) {
    /*
     * Fields:
     * this.first
     * this.rest
     *
     * Methods on fields:
     * this.rest.findMin(ICompareRunner)
     *
     * Methods on parameters:
     * comp.comesBefore(Runner, Runner)
     */
    return findMinHelp(comp, this.first);

  }

  public Runner findWinner() {
    return this.findMin(new CompareByTime());
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

  boolean testFindUnder4Hours(Tester t) {
    return t.checkExpect(this.list2.find(new FinishIn4Hours()), new ConsLoRunner(this.frank,
        new ConsLoRunner(this.bill, new ConsLoRunner(this.joan, new MtLoRunner()))));
  }

  // tests on the time sorting algorithm
  boolean testSortBy(Tester t) {
    return t.checkExpect(this.list2.sortBy(new CompareByTime()),
        new ConsLoRunner(this.bill, new ConsLoRunner(this.frank,
            new ConsLoRunner(this.joan, new ConsLoRunner(this.johnny, this.mtlist)))));
  }

  // test for findMin
  boolean testFindWinner(Tester t) {
    return t.checkExpect(this.list2.findMin(new CompareByTime()), this.bill);
  }

}
