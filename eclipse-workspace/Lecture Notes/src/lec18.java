interface IFunc<T, R> {
  R apply(T t);
}

interface IPred<T> extends IFunc<T, Boolean> {
  Boolean apply(T t);
}

interface IList<T> {
  // Effect: changes an item according to a given function in this list if it passes the
  // predicate
  void changeItem(IPred<T> whichOne, IFunc<T, Void> whatToDo);

}

class MtList<T> implements IList<T> {

  public void changeItem(IPred<T> whichOne, IFunc<T, Void> whatToDo) {
    // TODO Auto-generated method stub

  }

}

class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;

  @Override
  public void changeItem(IPred<T> whichOne, IFunc<T, Void> whatToDo) {
    if (whichOne.apply(this.first)) {
      whatToDo.apply(this.first);
    }
    else {
      this.rest.changeItem(whichOne, whatToDo);
    }

  }

}