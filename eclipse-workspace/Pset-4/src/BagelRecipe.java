import tester.*;

class BagelRecipe {
  double flour;
  double water;
  double yeast;
  double malt;
  double salt;

  BagelRecipe(double flour, double water, double yeast, double malt, double salt) {
    if (flour == water) {
      this.flour = flour;
      this.water = water;
    }
    else {
      throw new IllegalArgumentException("Invalid weight");
    }

    if (yeast == malt) {
      this.yeast = yeast;
      this.malt = malt;
    }
    else {
      throw new IllegalArgumentException("Invalid weight");
    }
    if (salt + yeast == flour * 1 / 20) {
      this.salt = salt;
    }
    else {
      throw new IllegalArgumentException("Invalid weight");
    }
  }

  BagelRecipe(double flour, double yeast) {
    this.flour = flour;
    this.water = flour;
    this.yeast = yeast;
    this.malt = yeast;
    this.salt = (flour * 1 / 20) - yeast;
  }

  BagelRecipe(double flour, double yeast, double salt) {
    this.flour = 
  }

}