import tester.Tester;

// utils class to check validity of inputs
class Utils {
  /*
   * Template
   * Fields:
   * Methods:
   * checkIngredient ... double
   * approxEqual ... boolean
   * Methods of fields:
   */
  double checkIngredient(double amt1, double amt2, String error) {
    if (approxEqual(amt1, amt2)) {
      return amt1;
    }
    else {
      throw new IllegalArgumentException(error);
    }
  }

  // calculates if two numbers are approximately equal, up to 0.001
  boolean approxEqual(double first, double second) {
    return (Math.abs(first - second) < 0.001);
  }
}

// represents a recipe to make bagels
class BagelRecipe {
  double flour;
  double water;
  double yeast;
  double malt;
  double salt;

  // main constructor with all arguments and enforces proportional weights
  BagelRecipe(double flour, double water, double yeast, double salt, double malt) {
    this.flour = new Utils().checkIngredient(flour, water,
        "You should have the same amounts of flour and water.");
    this.water = new Utils().checkIngredient(water, flour,
        "You should have the same amounts of flour and water.");
    this.yeast = new Utils().checkIngredient(yeast, malt,
        "You should have the same amounts of yeast and malt.");
    this.salt = (new Utils().checkIngredient((salt + yeast) * 20, water,
        "Salt and yeast should be 1/20th the weight of flour.") / 20) - yeast;
    this.malt = new Utils().checkIngredient(malt, yeast,
        "You should have the same amounts of yeast and malt.");

  }

  /*
   * Template
   * Fields:
   * flour ... double
   * water ... double
   * yeast ... double
   * malt ... double
   * salt ... double
   * Methods:
   * Methods of fields:
   */

  // only takes in flour and yeast
  BagelRecipe(double flour, double yeast) {
    this(flour, flour, yeast, flour / 20 - yeast, yeast);
  }

  // constructor with volumes
  BagelRecipe(double flour, double yeast, double salt) {
    this(flour * 4.25, flour * 4.25, (yeast / 48) * 5, (salt / 48) * 10, (yeast / 48) * 5);
  }

  // determines if the given recipe is the same as this recipe
  boolean sameRecipe(BagelRecipe other) {
    return new Utils().approxEqual(this.flour, other.flour)
        && new Utils().approxEqual(this.water, other.water)
        && new Utils().approxEqual(this.yeast, other.yeast)
        && new Utils().approxEqual(this.malt, other.malt)
        && new Utils().approxEqual(this.salt, other.salt);
  }

}

// examples and tests
class ExamplesBagelRecipe {
  // constructor 1
  BagelRecipe cons1Bagel1 = new BagelRecipe(20.0, 20.0, 0.5, 0.5, 0.5);
  BagelRecipe cons1Bagel2 = new BagelRecipe(40.0, 40.0, 1, 1, 1);
  // BagelRecipe incorrect = new BagelRecipe(19.0, 20.0, 0.5, 0.5, 0.5);
  // constructor 2
  BagelRecipe cons2Bagel1 = new BagelRecipe(20.0, 0.9);
  BagelRecipe cons2Bagel2 = new BagelRecipe(20.0, 0.5);
  // BagelRecipe invalidCons2bagel = new BagelRecipe(20.0, 1.0);
  // BagelRecipe invalidCons2bagel2 = new BagelRecipe(20.0, 20.0);
  // constructor 3
  BagelRecipe cons3Bagel1 = new BagelRecipe(5.0, 2.0, 4.1);

  boolean testConstructorException(Tester t) {
    return t.checkConstructorException(
        new IllegalArgumentException("You should have the same amounts of flour and water."),
        "BagelRecipe", 19.0, 20.0, 0.5, 0.5, 0.5)
        && t.checkConstructorException(
            new IllegalArgumentException("You should have the same amounts of flour and water."),
            "BagelRecipe", 20.0, 19.0, 0.5, 0.5, 0.5)
        && t.checkConstructorException(
            new IllegalArgumentException("You should have the same amounts of yeast and malt."),
            "BagelRecipe", 20.0, 20.0, 1.0, 0.5, 0.5)
        && t.checkConstructorException(
            new IllegalArgumentException("You should have the same amounts of yeast and malt."),
            "BagelRecipe", 20.0, 20.0, 0.5, 0.5, 0.1)
        && t.checkConstructorException(
            new IllegalArgumentException("Salt and yeast should be 1/20th the weight of flour."),
            "BagelRecipe", 20.0, 20.0, 0.5, 1.0, 0.5)
        && t.checkConstructorException(
            new IllegalArgumentException("Salt and yeast should be 1/20th the weight of flour."),
            "BagelRecipe", 5.0, 2.0, 200.0);
  }

  // tests for approxEqual
  boolean testApproxEqual(Tester t) {
    return t.checkExpect(new Utils().approxEqual(0.1, 0.1), true)
        && t.checkExpect(new Utils().approxEqual(0.001, 0.001), true)
        && t.checkExpect(new Utils().approxEqual(0.001, 0.0009), true)
        && t.checkExpect(new Utils().approxEqual(0.001, 1), false)
        && t.checkExpect(new Utils().approxEqual(0.001, 0.002), false);
  }

  // tests for sameRecipe
  boolean testSameRecipe(Tester t) {
    return t.checkExpect(this.cons2Bagel2.sameRecipe(this.cons1Bagel1), true)
        && t.checkExpect(this.cons2Bagel1.sameRecipe(this.cons1Bagel1), false)
        && t.checkExpect(this.cons2Bagel1.sameRecipe(this.cons1Bagel2), false);
  }

}
