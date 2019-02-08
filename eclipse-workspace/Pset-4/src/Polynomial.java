import tester.Tester;

class Monomial {
  int degree;
  int coefficient;

  Monomial(int degree, int coefficient) {
    if (degree >= 0) {
      this.degree = degree;
    }
    else {
      throw new IllegalArgumentException("Degrees of monomials cannot be negative");
    }
    this.coefficient = coefficient;
  }
  /*
   * Template
   * Fields:
   * degree ... int
   * coefficient ... int
   * Methods:
   * isZero ... boolean
   * sameMonomial ... boolean
   * Methods of fields:
   */

  // returns if this monomial has a coefficient of 0
  public boolean isZero() {
    return this.coefficient == 0;
  }

  // checks if the given monomial is the same as this monomial
  public boolean sameMonomial(Monomial desired) {
    return this.degree == desired.degree && this.coefficient == desired.coefficient;
  }
}

interface ILoMonomial {

  // checks if this ILoMonomial is equivalent as that ILoMonomial
  boolean sameMonomials(ILoMonomial other);

  // determines if this ILoMonomial is empty
  boolean isEmpty();

  // removes the monomials with a coefficient of 0 in a list of monomials
  ILoMonomial removeZeros();

  // removes that monomial from this ILoMonomial
  ILoMonomial findAndRemove(Monomial desired);
}

class MtLoMonomial implements ILoMonomial {

  /*
   * Template
   * Fields:
   * Methods:
   * sameMonomials ... boolean
   * isEmpty ... boolean
   * findAndRemove ... ILoMonomial
   * removeZeros ... ILoMonomial
   * Methods of fields:
   */

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

  /*
   * Template
   * Fields:
   * first ... Monomial
   * rest ... ILoMonomial
   * Methods:
   * sameMonomials ... boolean
   * isEmpty ... boolean
   * findAndRemove ... ILoMonomial
   * removeZeros ... ILoMonomial
   * Methods of fields:
   */

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
      return this.rest;
    }
    else {
      return new ConsLoMonomial(this.first, this.rest.findAndRemove(desired));
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
    if (!this.containsSameDegree()) {
      this.monomials = monomials;
    } else {
      throw new IllegalArgumentException("Polynomials cannot have monomials with the same degree.")
    }
  }

  /*
   * Template
   * Fields:
   * monomials ... ILoMonomial
   * Methods:
   * samePolynomial ... boolean
   * Methods of fields:
   */

  // determines if this polynomial is equal to that polynomial
  public boolean samePolynomial(Polynomial other) {
    return this.monomials.removeZeros().sameMonomials(other.monomials.removeZeros())
        && other.monomials.removeZeros().sameMonomials(this.monomials.removeZeros());
  }
}

class ExamplesPolynomial {
  Monomial oneToOne = new Monomial(1, 1);
  Monomial twoToThree = new Monomial(2, 3);
  Monomial fourToZero = new Monomial(4, 0);
  Monomial oneToFour = new Monomial(0, 4);
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

  ILoMonomial illegalMonomials1 = new ConsLoMonomial(new Monomial(1, 1), new ConsLoMonomial(
      new Monomial(2, 1), new ConsLoMonomial(new Monomial(3, 3), new MtLoMonomial())));
  ILoMonomial illegalMonomials2 = new ConsLoMonomial(new Monomial(1, 1), new ConsLoMonomial(
      new Monomial(2, 2), new ConsLoMonomial(new Monomial(3, 2), new MtLoMonomial())));

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

  // tests for sameMonomial
  boolean testSameMonomial(Tester t) {
    return t.checkExpect(this.oneToOne.sameMonomial(this.oneToOne), true)
        && t.checkExpect(this.oneToOne.sameMonomial(this.oneToFour), false)
        && t.checkExpect(this.twoToThree.sameMonomial(this.oneToFour), false)
        && t.checkExpect(this.fourToZero.sameMonomial(this.oneToFour), false);
  }

  // tests for findAndRemove
  boolean testFindAndRemove(Tester t) {
    return t.checkExpect(this.monomials2.findAndRemove(new Monomial(0, 0)), this.monomials3)
        && t.checkExpect(this.monomialsEmpty.findAndRemove(new Monomial(0, 0)), this.monomialsEmpty)
        && t.checkExpect(this.monomials2.findAndRemove(new Monomial(2, 2)),
            new ConsLoMonomial(new Monomial(0, 0), new ConsLoMonomial(new Monomial(1, 1),
                new ConsLoMonomial(new Monomial(3, 3), new MtLoMonomial()))));
  }

  // tests for Monomial constructor
  boolean testMonomialConstructorException(Tester t) {
    return t.checkConstructorException(
        new IllegalArgumentException("Degrees of monomials cannot be negative"), "Monomial", -1, 0)
        && t.checkConstructorException(
            new IllegalArgumentException("Degrees of monomials cannot be negative"), "Monomial",
            -10, 0);
  }

  // tests for Monomial constructor
  boolean testPolynomialConstructorException(Tester t) {
    return t.checkConstructorException(
        new IllegalArgumentException("Polynomials cannot have monomials with the same degree."),
        "Polynomial", this.illegalMonomials1)
        && t.checkConstructorException(
            new IllegalArgumentException("Polynomials cannot have monomials with the same degree."),
            "Polynomial", this.illegalMonomials2);
  }

}
