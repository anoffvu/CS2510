import java.util.ArrayList;

public class Utils {
  // applies C, Y -> Y from right to left
  <U, T> U foldl(ArrayList<T> aList, IFunc2<T, U, U> func, U base) {
    for (T t : aList) {
      base = func.apply(t, base);
    }
    return base;
  }
}

interface IFunc2<A1, A2, R> {
  R apply(A1 arg1, A2 arg2);
}
