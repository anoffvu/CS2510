import tester.Tester;

class Utils {
  double checkIngredient(double amt1, double amt2, String error) {
    if (amt1 == amt2) {
      return amt1;
    }
    else {
      throw new IllegalArgumentException(error);
    }
  }

  double checkIngredientAtMost(double amt1, double amt2, String error) {
    if (amt1 <= amt2) {
      return amt1;
    }
    else {
      throw new IllegalArgumentException(error);
    }
  }
}

class BagelRecipe {
  double flour;
  double water;
  double yeast;
  double malt;
  double salt;

  BagelRecipe(double flour, double water, double yeast, double malt, double salt) {
    this.flour = new Utils().checkIngredient(flour, water,
        "You should have the same amounts of flour and water.");
    this.water = new Utils().checkIngredient(water, flour,
        "You should have the same amounts of flour and water.");
    this.yeast = new Utils().checkIngredient(yeast, malt,
        "You should have the same amounts of yeast and malt.");
    this.malt = new Utils().checkIngredient(malt, yeast,
        "You should have the same amounts of yeast and malt.");
    this.salt = new Utils().checkIngredient((salt + yeast) * 20, water,
        "Salt and yeast should be 1/20th the weight of flour.");
  }

  BagelRecipe(double flour, double yeast) {
    this.flour = flour;
    this.water = flour;
    this.yeast = new Utils().checkIngredientAtMost(yeast * 20, flour,
        "Yeast should be at most 1/20th the weight of flour.");
    this.malt = yeast;
    this.salt = (flour / 20) - yeast;
  }

  /*
   * BagelRecipe(double flour, double yeast, double salt) {
   * this.flour =
   * }
   */

}

class ExamplesBagelRecipe {
  BagelRecipe correct = new BagelRecipe(20.0, 20.0, 0.5, 0.5, 0.5);
  // BagelRecipe incorrect = new BagelRecipe(19.0, 20.0, 0.5, 0.5, 0.5);
  BagelRecipe cons2bagel1 = new BagelRecipe(20.0, 1.0);
  BagelRecipe cons2bagel2 = new BagelRecipe(20.0, 0.5);
  // BagelRecipe invalidCons2bagel = new BagelRecipe(20.0, 20.0);

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
            "BagelRecipe", 20.0, 20.0, 0.5, 1.0, 0.5)
        && t.checkConstructorException(
            new IllegalArgumentException("Salt and yeast should be 1/20th the weight of flour."),
            "BagelRecipe", 20.0, 20.0, 0.5, 0.5, 1.0);
  }

}
