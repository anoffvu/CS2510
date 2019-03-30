import java.util.ArrayList;

import tester.Tester;

class Office {
  String building;
  int room;
  String occupant;

  Office(String building, int room, String occupant) {
    this.building = building;
    this.room = room;
    this.occupant = occupant;
  }

  void F1(Office forOffice, String toOccupant) {
    forOffice = new Office(forOffice.building, forOffice.room, toOccupant);
  }

  public String question1() {
    Office carmenOffice = new Office("CS", 176, "Carmen");
    F1(carmenOffice, "Fred");
    return carmenOffice.occupant;
  }

  void F2(Office o1, Office o2) {
    o2 = o1;
    o2.room = 63;
  }

  String question2() {
    Office labOffice = new Office("CS", 130, "Tyler Smith");
    Office commonOffice = new Office("CS", 275, "everyone");
    F2(labOffice, commonOffice);
    return labOffice.room + ", " + commonOffice.room;
  }

  void F4(Office o1) {
    Office o = o1;
    o = new Office("CS", 640, "Walter");
  }

  String question4() {
    Office dillonOffice = new Office("CS", 808, "Dillon");
    F4(dillonOffice);
    return dillonOffice.occupant;
  }

  public int f() {
    int x = 17;
    return g();
  }

  public int g() {
    int y = 4;
    return x / y;
  }

  public int question5() {
    return f();
  }

  boolean positivePartialSums(ArrayList<Integer> arr) {
    boolean isNeg = false;
    int acc = 0;
    for (int i = 0; i < arr.size(); i++) {
      acc += arr.get(i);
      if (acc < 0) {
        isNeg = true;
      }

    }
    return isNeg;
  }

}

class Building {
  Office office;

  Building(Office office) {
    this.office = office;
  }

  void F3(Office o1) {
    Building bill = new Building(o1);
    bill.office.room = 999;
  }

  int question3() {
    Office peterOffice = new Office("CS", 37, "Peter Gibbons");
    Building cs = new Building(peterOffice);
    F3(peterOffice);
    return cs.office.room;
  }

}

class ExamplesSandbox {
  Office office;
  Building building;

  void initData() {
    this.office = new Office("CS", 176, "Carmen");
    this.building = new Building(office);

  }
  void testQuestion(Tester t) {
    initData();
    t.checkExpect(new Office("CS", 176, "Carmen").question1(), "Carmen");
    t.checkExpect(new Office("CS", 176, "Carmen").question2(), "63, 275");
    t.checkExpect(building.question3(), 999); // confusing because you edit the field of a void
                                              // object
    t.checkExpect(new Office("CS", 176, "Carmen").question4(), "Dillon");
    t.checkExpect(new Office("CS", 176, "Carmen").question5(), "Dillon");
  }
}