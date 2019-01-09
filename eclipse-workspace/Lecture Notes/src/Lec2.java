
class Lec2 {

}

interface IBeverage {
}

class Milk implements IBeverage {
}

class Coffee implements IBeverage {
  String name;
  boolean isFairTrade;

  Coffee(String name, boolean isFairTrade) {
    this.name = name;
    this.isFairTrade = isFairTrade;
  }
}

class Tea implements IBeverage {
  String name;
  String color;

  Tea(String name, String color) {
    this.name = name;
    this.color = color;
  }
}

class Blend implements IBeverage {
  IBeverage first;
  IBeverage second;

  Blend(IBeverage first, IBeverage second) {
    this.first = first;
    this.second = second;
  }
}


class ExamplesBeverage {
  IBeverage espresso = new Coffee("Espresso", True);
  IBeverage americano = new Blend(this.espresso, new Milk());
  IBeverage heavyCaff = new Blend(this.espresso, new Tea("Chai", "almond"));
  IBeverage twoMilk = new Blend(new Milk(), new Milk());
  IBeverage dirtyChai = new Blend(this.heavyCaff, this.twoMilk);
}

interface IAT { }

class Person implements IAT {
  String name;
  IAT lParent;
  IAT rParent;
  Person(String name, IAT lParent, IAT rParent) {
    this.name = name;
    this.lParent = lParent;
    this.rParent = rParent;
  }
}

class Unknown {

}
