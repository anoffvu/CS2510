
interface ISundae {
}

class Scoop implements ISundae {
  String flavor;

  Scoop(String flavor) {
    this.flavor = flavor;
  }
}

class Topping implements ISundae {
  ISundae inner;
  String name;

  Topping(ISundae inner, String name) {
    this.inner = inner;
    this.name = name;
  }
}

class ExamplesSundae {
  ISundae chocolateScoop = new Scoop("chocolate");
  ISundae rainbowChocolate = new Topping(this.chocolateScoop, "rainbow sprinkles");
  ISundae caramelRainbow = new Topping(this.rainbowChocolate, "caramel");
  ISundae yummy = new Topping(this.caramelRainbow, "whipped cream");

  ISundae vanillaScoop = new Scoop("vanilla");
  ISundae chocolateVanilla = new Topping(this.vanillaScoop, "chocolate sprinkles");
  ISundae fudgeChocolate = new Topping(this.chocolateVanilla, "fudge");
  ISundae noThankYou = new Topping(this.fudgeChocolate, "plum sauce");
}
