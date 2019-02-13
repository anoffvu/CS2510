import tester.Tester;

// to represent an ancestor tree
interface IAT {
  // counts the amount of people in this ancestor's trees
  int count();
}

// to represent an unknown member of an ancestor tree
class Unknown implements IAT {
  Unknown() {
  }

  // counts the amount of people in this unknown trees
  public int count() {
    return 0;
  }
}

// to represent a person with the person's ancestor tree
class Person implements IAT {
  String name;
  IAT mom;
  IAT dad;

  Person(String name, IAT mom, IAT dad) {
    this.name = name;
    this.mom = mom;
    this.dad = dad;
  }

  // counts this person and every known person in his family tree
  public int count() {
    return 1 + mom.count() + dad.count();
  }
}

class ExamplesIAT {
  IAT unknown = new Unknown();
  IAT one = new Person("1", this.unknown, this.unknown);
  IAT two = new Person("1", this.one, this.unknown);
  IAT three = new Person("1", this.two, this.one);

  boolean testCount(Tester t) {
    return t.checkExpect(this.three.count(), 4);
  }
}