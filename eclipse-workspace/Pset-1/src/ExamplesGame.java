interface IResources {
}

class Denial implements IResources {
  String subject;
  int believability;

  Denial(String subject, int believability) {
    this.subject = subject;
    this.believability = believability;
  }
}

class Bribe implements IResources {
  String target;
  int value;

  Bribe(String target, int value) {
    this.target = target;
    this.value = value;
  }
}

class Apology implements IResources {
  String excuse;
  boolean reusable;

  Apology(String excuse, boolean reusable) {
    this.excuse = excuse;
    this.reusable = reusable;
  }
}

interface IAction {
}

class Purchase implements IAction {
  int cost;
  IResources item;

  Purchase(int cost, IResources item) {
    this.cost = cost;
    this.item = item;
  }
}

class Swap implements IAction {
  IResources consumed;
  IResources received;

  Swap(IResources consumed, IResources received) {
    this.consumed = consumed;
    this.received = received;
  }

}

class ExamplesGame {
  IResources iDidntKnow = new Denial("knowledge", 51);
  IResources witness = new Bribe("innocent witness", 49);
  IResources iWontDoItAgain = new Apology("I won't do it again", false);
  IResources iCouldntAct = new Denial("action", 49);
  IResources boss = new Bribe("boss", 99);
  IResources iAmSorry = new Apology("I am sorry", true);
  IAction purchase1 = new Purchase(10, this.iWontDoItAgain);
  IAction purchase2 = new Purchase(10, this.iCouldntAct);
  IAction swap1 = new Swap(this.iDidntKnow, this.witness);
  IAction swap2 = new Swap(this.boss, this.iAmSorry);
}
