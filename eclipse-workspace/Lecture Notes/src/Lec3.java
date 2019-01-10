import tester.Tester;

class Lec3 {

}

/*
 * //Design recipe 1. Data definition (template) 2. Signature and Purpose (short, concise
 * sentence) 3. "Check Expects" !! 4. Code 5. Run & Test it.
 *
 * SL : "function" Java: "method"
 *
 * put the template right under the constructor
 */
interface IBeverage {
}

class Milk implements IBeverage {
}

class Coffee implements IBeverage {
  String name;
  boolean isFairTrade;
  int price;

  Coffee(String name, boolean isFairTrade, int price) {
    this.name = name;
    this.isFairTrade = isFairTrade;
    this.price = price;
  }
  /*
   * this.name ... s
   * this.isFairTrade ... boolean
   * this.price ... int
   * this.couponPrice(double) ... double
   * this.sameCoffee(Coffee) ... Boolean
   */

  // compute the sale price of this Coffee given coupon
  double couponPrice(double discount) {
    // you dont need to input a coffee, because it automatically associates with this coffee,
    // the coffee that it is initialized with, the class
    return this.price - this.price * discount / 100;
  }
  boolean sameCoffee(Coffee theOther) {
    return this.name.equals(theOther.name)
        && this.price == theOther.price
        && this.isFairTrade == theOther.isFairTrade;
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
  Coffee espresso = new Coffee("Espresso", true, 20);
  IBeverage americano = new Blend(this.espresso, new Milk());
  IBeverage heavyCaff = new Blend(this.espresso, new Tea("Chai", "almond"));
  IBeverage twoMilk = new Blend(new Milk(), new Milk());
  IBeverage dirtyChai = new Blend(this.heavyCaff, this.twoMilk);

  // to test coffee prices
  boolean testCoffee(Tester t) {
    return t.checkExpect(this.espresso.couponPrice(90), 2.0);
  }

  boolean testAnotherCoffee(Tester t) {
    return t.checkExpect(this.espresso.couponPrice(18), 16.4);
  }

  // test the sameness and differentude of some coffees
  boolean testSameness(Tester t) {
    return t.checkExpect(this.espresso.sameCoffee(this.espresso), true)
        && t.checkExpect(this.espresso.sameCoffee(new Coffee("",true,15)),false);
  }
}

interface IAT {
}

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
