interface IResource {
}

class Denial implements IResource {
  String subject;
  int believability;

  Denial(String subject, int believability) {
    this.subject = subject;
    this.believability = believability;
  }
}

class Bribe implements IResource {
  String target;
  int value;

  Bribe(String target, int value) {
    this.target = target;
    this.value = value;
  }
}

class Apology implements IResource {
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
  IResource item;

  Purchase(int cost, IResource item) {
    this.cost = cost;
    this.item = item;
  }
}

class Swap implements IAction {
  IResource consumed;
  IResource received;

  Swap(IResource consumed, IResource received) {
    this.consumed = consumed;
    this.received = received;
  }

}

class ExamplesGame {
  IResource iDidntKnow = new Denial("knowledge", 51);
  IResource witness = new Bribe("innocent witness", 49);
  IResource iWontDoItAgain = new Apology("I won't do it again", false);
  IResource iCouldntAct = new Denial("action", 49);
  IResource boss = new Bribe("boss", 99);
  IResource iAmSorry = new Apology("I am sorry", true);
  IAction purchase1 = new Purchase(10, this.iWontDoItAgain);
  IAction purchase2 = new Purchase(10, this.iCouldntAct);
  IAction swap1 = new Swap(this.iDidntKnow, this.witness);
  IAction swap2 = new Swap(this.boss, this.iAmSorry);
}
