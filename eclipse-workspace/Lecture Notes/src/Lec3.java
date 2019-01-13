import tester.Tester;

// a class to represent coffee
class Coffee {
  String name;
  boolean isEspresso;
  double price;
  Origin origin;

  Coffee(String name, boolean isEspresso, double price, Origin origin) {
    this.name = name;
    this.isEspresso = isEspresso;
    this.price = price;
    this.origin = origin;
  }

  /* fields:
   * this.name ... String
   * this.isEspresso ... boolean
   * this.price ... int
   * this.origin ... Origin
   * methods:
   * this.couponPrice(int) ...double
   * this.sameCoffee(Coffee) ... boolean
   * methods for fields:
   * this.origin.sameOrigin(Origin) ... boolean
   */

  //compute the price of this Coffee after using a coupon represented as a percentage
  double couponPrice(int coupon) {
    return this.price - (this.price * coupon)/100;
  }

  //is this coffee the same as the given one?
  boolean sameCoffee(Coffee that) {
    return this.name.equals(that.name)
        && this.isEspresso == that.isEspresso
        && this.price == that.price
        && this.origin.sameOrigin(that.origin);
  }

}

// a class to represent origin of a coffee
class Origin {
  String location;
  boolean isFairTrade;

  Origin(String location, boolean isFairTrade) {
    this.location = location;
    this.isFairTrade = isFairTrade;
  }

  /* this.location ... String
   * this.isFairTrade ... boolean
   * this.sameOrigin(Origin) ... boolean
   */

  //is this Origin the same as that Origin
  boolean sameOrigin(Origin that) {
    return this.location.equals(that.location)
        && this.isFairTrade == that.isFairTrade;
  }

}


class Examples {
  Origin brazil = new Origin("Brazil", false);
  Origin indonesia = new Origin("Indonesia", true);
  Coffee morningCoffee = new Coffee("morning coffee", false, 10.0, this.brazil);
  Coffee espressoCoffee = new Coffee("espresso coffee", true, 5.0, this.indonesia);

  //test for coupon price
  boolean testCoupon(Tester t) {
    return t.checkExpect(this.morningCoffee.couponPrice(20), 8.0) &&
        t.checkExpect(this.espressoCoffee.couponPrice(50), 2.5);
  }

  //test for Coffee sameness
  boolean testSameness(Tester t) {
    return t.checkExpect(this.morningCoffee.sameCoffee(this.morningCoffee), true) &&
        t.checkExpect(this.espressoCoffee.sameCoffee(this.morningCoffee), false) &&
        t.checkExpect(this.brazil.sameOrigin(this.brazil), true) &&
        t.checkExpect(this.indonesia.sameOrigin(this.brazil), false);
  }
}
