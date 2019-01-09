
class Person {
  String name;
  int age;
  String gender;
  
  // constructor
  Person(String name, int age, String gender) {
    this.name = name;
    this.age = age;
    this.gender = gender;
  }
}

class ExamplesPerson {
  Person p1 = new Person("An", 19, "male");
}