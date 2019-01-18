interface ILoNumber {
  int sum();
}

interface ILoPerson {
  double valueOfDownstream(double keepPercentage);
}

// an empty list of numbers
class MtLoNumber implements ILoNumber {
  /*
   * Template
   * Fields:
   * Methods:
   * Methods of fields:
   */
  // calculate the sum of an empty list
  public int sum() {
    return 0;
  }
}

// a non-empty list of numbers
class ConsLoNumber implements ILoNumber {
  int first;
  ILoNumber rest;

  ConsLoNumber(int first, ILoNumber rest) {
    this.first = first;
    this.rest = rest;
  }

  /*
   * Template
   * Fields:
   * Methods:
   * Methods of fields:
   */
  // calculate sum of a non-empty list
  public int sum() {
    return this.first + this.rest.sum();
  }
}


//an empty list of numbers
class MtLoPerson implements ILoPerson {
  /*
   * Template
   * Fields:
   * Methods:
   * Methods of fields:
   */
//calculate the total amount of money that a person has to hand upward
  public double valueOfDownstream(double keepPercentage) {
    return 0;
  }

}

//a non-empty list of numbers
class ConsLoPerson implements ILoPerson {
  Person first;
  ILoPerson rest;

  ConsLoPerson(Person first, ILoPerson rest) {
    this.first = first;
    this.rest = rest;
  }

  /*
   * Template
   * Fields:
   * Methods:
   * Methods of fields:
   */
//calculate the total amount of money that a person has to hand upward
  public double valueOfDownstream(double keepPercentage) {
    return (this.first.income(keepPercentage) * (100 - keepPercentage) / 100)
        + this.rest.valueOfDownstream(keepPercentage);
  }
}

class Person {
  String name;
  int personalSalesProfit;
  ILoPerson downstream;

  Person(String name, int personalSalesProfit, ILoPerson downstream) {
    this.name = name;
    this.personalSalesProfit = personalSalesProfit;
    this.downstream = downstream;
  }

  /*
   * Template
   * Fields:
   * name ... String
   * personalSalesProfit ... int
   * downstream ... ILoPerson
   * Methods:
   * income ... double
   * Methods of fields:
   * 
   */
  // calculate personal income for pyramid scheme of pass-up percentage
  double income(double keepPercentage) {
    return this.personalSalesProfit
        + (this.downstream.valueOfDownstream(keepPercentage) * (keepPercentage / 100));
  }
}

class ExamplesBusinessRacket {
  Person alice = new Person("Alice", 1000, new MtLoPerson ());
  Person bob = new Person("Bob", 1900, new MtLoPerson ());
  Person charlie = new Person("Charlie", 200, new MtLoPerson ());
  Person dennis = new Person("Dennis", 20,
    new ConsLoPerson(this.alice,
      new ConsLoPerson(this.bob,
              new ConsLoPerson(this.charlie, new MtLoPerson()))));

}
