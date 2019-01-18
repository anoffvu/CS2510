// Represents a mode of transportation
import tester.Tester;

interface IMOT {
  // returns whether an mot meets a target fuel efficiency
  boolean mpgFuelEfficiency(int mpg);
}

// Represents a bicycle as a mode of transportation
class Bicycle implements IMOT {
  String brand;

  Bicycle(String brand) {
    this.brand = brand;
  }

  // returns whether a bicycle reaches a target fuel efficiency
  public boolean mpgFuelEfficiency(int mpg) {
    return true;
  }
}

// Represents a car as a mode of transportation
class Car implements IMOT {
  String make;
  int mpg; // represents the fuel efficiency in miles per gallon

  Car(String make, int mpg) {
    this.make = make;
    this.mpg = mpg;
  }

//returns whether a bicycle reaches a target fuel efficiency
  public boolean mpgFuelEfficiency(int mpg) {
    return mpg <= this.mpg;
  }
}

// Keeps track of how a person is transported
class Person {
  String name;
  IMOT mot;

  Person(String name, IMOT mot) {
    this.name = name;
    this.mot = mot;
  }

//Does this person's mode of transportation meet the given fuel
//efficiency target (in miles per gallon)?
  boolean motMeetsFuelEfficiency(int mpg) {
      return this.mot.mpgFuelEfficiency(mpg);
  }
}

//to represent a pet owner
class PetPerson {
  String name;
  IPet pet;
  int age;

  PetPerson(String name, IPet pet, int age) {
    this.name = name;
    this.pet = pet;
    this.age = age;
  }

//is this Person older than the given Person?
  boolean isOlder(PetPerson other) {
    return this.age > other.age;
  }
}

//to represent a pet
interface IPet {
}

//to represent a pet cat
class Cat implements IPet {
  String name;
  String kind;
  boolean longhaired;

  Cat(String name, String kind, boolean longhaired) {
    this.name = name;
    this.kind = kind;
    this.longhaired = longhaired;
  }
}

//to represent a pet dog
class Dog implements IPet {
  String name;
  String kind;
  boolean male;

  Dog(String name, String kind, boolean male) {
    this.name = name;
    this.kind = kind;
    this.male = male;
  }
}

class ExamplesPerson {
  IMOT diamondback = new Bicycle("Diamondback");
  IMOT toyota = new Car("Toyota", 30);
  IMOT lamborghini = new Car("Lamborghini", 17);
  IMOT vroom = new Car("Vroom", 100);

  Person bob = new Person("Bob", diamondback);
  Person ben = new Person("Ben", toyota);
  Person becca = new Person("Becca", lamborghini);
  
  IPet steveDog = new Dog("Steve", "black", true);
  IPet stephDog = new Dog("Steph", "brown", false);
  IPet carlCat = new Cat("Carl", "red", true);
  IPet catherineCat = new Cat("Catherine", "maroon", false);
  
  PetPerson steve = new PetPerson("Steve", steveDog, 20);
  PetPerson steph = new PetPerson("Steph", stephDog, 30);
  PetPerson carl = new PetPerson("Carl", carlCat, 40);
  PetPerson catherine = new PetPerson("Catherine", catherineCat, 50);
  
  boolean testIsOlder(Tester t) {
    return t.checkExpect(this.steve.isOlder(this.steph), false);
  }
  boolean testMotMeetsFuelEfficiency2(Tester t) {
    return t.checkExpect(this.bob.motMeetsFuelEfficiency(20), true);
  }

  boolean testMotMeetsFuelEfficiency(Tester t) {
    return t.checkExpect(this.ben.motMeetsFuelEfficiency(1000), false)
        && t.checkExpect(this.becca.motMeetsFuelEfficiency(16), true)
        && t.checkExpect(this.bob.motMeetsFuelEfficiency(1), true);
  }

  boolean testMpgFuelEfficiency(Tester t) {
    return t.checkExpect(this.toyota.mpgFuelEfficiency(15), true)
        && t.checkExpect(this.lamborghini.mpgFuelEfficiency(18), false)
        && t.checkExpect(this.diamondback.mpgFuelEfficiency(1), true);
  }
}


