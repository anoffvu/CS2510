interface IAT {
  // how many ancestors in this tree?
  int countAnc();

  // how many people in this tree?
  int countHelp();

  // is this tree well formed?
  boolean wellFormed();

  // is this person born before the given person?
  boolean bornBefore(int year);

}

interface ILoString {

  //list all the names in a tree
  ILoString names();

  // append this list to that one
  ILoString append(ILoString that);
  
//list all the names in a tree
  ILoString names2();
}

class ConsLoString implements ILoString {
  String first;
  ILoString rest;

  ConsLoString(String first, ILoString rest) {
    this.first = first;
    this.rest = rest;
  }

  public ILoString append(ILoString that) {
    return new ConsLoString(this.first, this.rest.append(that));
  }}

  class MtLoString implements ILoString {

    public ILoString append(ILoString that) {
      return this;
    }
  }

class Unknown implements IAT {

  public int countAnc() {
    return 0;
  }

  public int countHelp() {
    return 0;
  }

  public boolean wellFormed() {
    return true;
  }

  public boolean bornBefore(int year) {
    return true;
  }

    public ILoString names() {
      return new MtLoString();
    }

}

class Person implements IAT {
  String name;
  int yob;
  boolean isMale;
  IAT mom;
  IAT dad;

  Person(String name, int yob, boolean isMale, IAT mom, IAT dad) {
    this.name = name;
    this.yob = yob;
    this.isMale = isMale;
    this.mom = mom;
    this.dad = dad;
  }

  /*
   * Template
   * Fields:
   * Methods:
   * Methods of Fields:
   */


  public int countAnc() {
    return this.mom.countHelp() + this.dad.countHelp();
  }

  public int countHelp() {
    return 1 + this.mom.countHelp() + this.dad.countHelp();
  }

  public boolean wellFormed() {
    return this.mom.bornBefore(this.yob) && this.dad.bornBefore(this.yob) && this.mom.wellFormed()
        && this.dad.wellFormed();
  }

    // is this Person born before the given year
    public boolean bornBefore(int year) {
      return this.yob < year;
    }

  public ILoString names() {
    return new ConsLoString(this.name, this.mom.names(), this.dad.names()).append(that));
  }
}
class ExamplesPerson {
  IAT an = new Person("An", 1999, true, new Unknown(), new Unknown());
  IAT dan = new Person("Dan", 1995, true, new Unknown(), new Unknown());
  
  boolean testIAT(Tester t) {
    return t.checkExpect(this.an.bornBefore(2000),true)
        && t.checkExpect(this.dan.bornBefore(1990), false);
    
  }
  
}
@Override
public ILoString names() {
  // TODO Auto-generated method stub
  return null;
}

@Override
public ILoString names2() {
  // TODO Auto-generated method stub
  return null;
}
