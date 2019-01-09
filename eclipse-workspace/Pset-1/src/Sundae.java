
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
  ISundae rainbowChocolate = new Topping(chocolateScoop, "rainbow sprinkles");
  ISundae caramelRainbow = new Topping(rainbowChocolate, "caramel");
  ISundae yummy = new Topping(caramelRainbow, "whipped cream");

  ISundae vanillaScoop = new Scoop("vanilla");
  ISundae chocolateVanilla = new Topping(vanillaScoop, "chocolate sprinkles");
  ISundae fudgeChocolate = new Topping(chocolateVanilla, "fudge");
  ISundae noThankYou = new Topping(fudgeChocolate, "plum sauce");
}
