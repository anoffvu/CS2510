import tester.Tester;

class Monomial {
  int degree;
  int coefficient;

  Monomial(int degree, int coefficient) {
    if (this.degree >= 0) {
      this.degree = degree;
    }
    else {
      throw new IllegalArgumentException("Degrees of monomials cannot be negative");
    }
    this.coefficient = coefficient;
  }

  // returns if this monomial has a coefficient of 0
  public boolean isZero() {
    return this.coefficient == 0;
  }

  /*
   * // if this monomial is in the other list of monomials, remove it
   * public ILoMonomial findAndRemove(ILoMonomial other) {
   * if (this.sameMonomial(other.first)) {
   * 
   * } else {
   * new ConsLoMonomial(other.first, other.)
   * }
   * }
   */
}

interface ILoMonomial {

  // checks if this ILoMonomial is equivalent as that ILoMonomial
  boolean sameMonomials(ILoMonomial other);

  // determines if this ILoMonomial is empty
  boolean isEmpty();

  // removes the monomials with a coefficient of 0 in a list of monomials
  ILoMonomial removeZeros();
}

class MtLoMonomial implements ILoMonomial {

  // determines if this MtLoMonomial is equivalent to the other ILoMonomial
  public boolean sameMonomials(ILoMonomial other) {
    return other.isEmpty();
  }

  // determines if this MtLoMonomial is empty
  public boolean isEmpty() {
    return true;
  }

  // if this list of monomials has that monomial, remove it
  public ILoMonomial findAndRemove(Monomial desired) {
    return this;
  }

  // removes the monomials with a coefficient of 0 in an MtLoMonomial
  public ILoMonomial removeZeros() {
    return this;
  }
}

class ConsLoMonomial implements ILoMonomial {
  Monomial first;
  ILoMonomial rest;

  ConsLoMonomial(Monomial first, ILoMonomial rest) {
    this.first = first;
    this.rest = rest;
  }

  // determines if this MtLoMonomial is equivalent to the other ILoMonomial
  public boolean sameMonomials(ILoMonomial other) {
    return this.rest.sameMonomials(other.findAndRemove(this.first));
  }

  // determines if this MtLoMonomial is empty
  public boolean isEmpty() {
    return false;
  }

  // if this list of monomials has that monomial, remove it
  public ILoMonomial findAndRemove(Monomial desired) {
    if (this.first.sameMonomial(desired)) {

    }
  }

  // removes the monomials with a coefficient of 0 in this ConsLoMonomial
  public ILoMonomial removeZeros() {
    if (this.first.isZero()) {
      return this.rest.removeZeros();
    }
    else {
      return new ConsLoMonomial(this.first, this.rest.removeZeros());
    }
  }
}

class Polynomial {
  ILoMonomial monomials;

  Polynomial(ILoMonomial monomials) {
    this.monomials = monomials;
  }

  // determines if this polynomial is equal to that polynomial
  public boolean samePolynomial(Polynomial other) {
    return this.monomials.removeZeros().sameMonomials(other.monomials.removeZeros());
    // && other.monomials.sameMonomials(this.monomials)
  }
}

class ExamplesPolynomial {
  ILoMonomial monomialsEmpty = new MtLoMonomial();
  // 0x^0 -> 2x^2
  ILoMonomial monomials1 = new ConsLoMonomial(new Monomial(0, 0), new ConsLoMonomial(
      new Monomial(1, 1), new ConsLoMonomial(new Monomial(2, 2), new MtLoMonomial())));
  // 0x^0 -> 3x^3
  ILoMonomial monomials2 = new ConsLoMonomial(new Monomial(0, 0),
      new ConsLoMonomial(new Monomial(1, 1), new ConsLoMonomial(new Monomial(2, 2),
          new ConsLoMonomial(new Monomial(3, 3), new MtLoMonomial()))));
  // changed order of monomials2
  ILoMonomial monomials2Jumbled = new ConsLoMonomial(new Monomial(2, 2),
      new ConsLoMonomial(new Monomial(3, 3), new ConsLoMonomial(new Monomial(0, 0),
          new ConsLoMonomial(new Monomial(1, 1), new MtLoMonomial()))));
  // 1x^1 -> 3x^3
  ILoMonomial monomials3 = new ConsLoMonomial(new Monomial(1, 1), new ConsLoMonomial(
      new Monomial(2, 2), new ConsLoMonomial(new Monomial(3, 3), new MtLoMonomial())));

  ILoMonomial lotsOfZeros = new ConsLoMonomial(new Monomial(4, 0),
      new ConsLoMonomial(new Monomial(1, 1),
          new ConsLoMonomial(new Monomial(10, 0), new ConsLoMonomial(new Monomial(3, 3),
              new ConsLoMonomial(new Monomial(66, 0), new MtLoMonomial())))));

  ILoMonomial filteredLotsOfZeros = new ConsLoMonomial(new Monomial(1, 1),
      new ConsLoMonomial(new Monomial(3, 3), new MtLoMonomial()));

  Polynomial poly1 = new Polynomial(this.monomials1);
  Polynomial poly2 = new Polynomial(this.monomials2);
  Polynomial poly2Jumbled = new Polynomial(this.monomials2Jumbled);
  Polynomial poly3 = new Polynomial(this.monomials3);
  Polynomial polyEmpty = new Polynomial(this.monomialsEmpty);

  // tests for samePolynomial
  boolean testSamePolynomial(Tester t) {
    return t.checkExpect(this.poly1.samePolynomial(this.poly1), true)
        && t.checkExpect(this.poly1.samePolynomial(this.poly2), false)
        && t.checkExpect(this.poly1.samePolynomial(this.poly3), false)
        && t.checkExpect(this.poly2.samePolynomial(this.poly2Jumbled), true)
        && t.checkExpect(this.polyEmpty.samePolynomial(this.poly1), false)
        && t.checkExpect(this.poly1.samePolynomial(this.polyEmpty), false);
  }

  // tests for sameMonomials
  boolean testSameMonomials(Tester t) {
    return t.checkExpect(this.monomials1.sameMonomials(this.monomials1), true)
        && t.checkExpect(this.monomials1.sameMonomials(this.monomials2), false)
        && t.checkExpect(this.monomials1.sameMonomials(this.monomials3), false)
        && t.checkExpect(this.monomials2.sameMonomials(this.monomials2Jumbled), true)
        && t.checkExpect(new MtLoMonomial().sameMonomials(new MtLoMonomial()), true);
  }

  // tests for isEmpty
  boolean testIsEmpty(Tester t) {
    return t.checkExpect(this.monomials1.isEmpty(), false)
        && t.checkExpect(this.monomialsEmpty.isEmpty(), true);
  }

  // tests for isZero
  boolean testIsZero(Tester t) {
    return t.checkExpect(new Monomial(0, 0).isZero(), true)
        && t.checkExpect(new Monomial(0, 1).isZero(), false)
        && t.checkExpect(new Monomial(1, 0).isZero(), true)
        && t.checkExpect(new Monomial(1, 1).isZero(), false);
  }

  // tests for removeZeros
  boolean testRemoveZeros(Tester t) {
    return t.checkExpect(this.lotsOfZeros.removeZeros(), this.filteredLotsOfZeros)
        && t.checkExpect(this.monomials3.removeZeros(), this.monomials3)
        && t.checkExpect(this.monomials2.removeZeros(), this.monomials3)
        && t.checkExpect(this.monomialsEmpty.removeZeros(), this.monomialsEmpty);
  }

}
