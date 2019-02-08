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

  // if this monomial is in the other list of monomials, remove it
  public ILoMonomial findAndRemove(ILoMonomial other) {
    if (this.sameMonomial(other.first)) {
      
    } else {
      new ConsLoMonomial(other.first, other.)
    }
  }
}

interface ILoMonomial {

  // checks if this ILoMonomial is equivalent as that ILoMonomial
  boolean sameMonomials(ILoMonomial other);

  // determines if this ILoMonomial is empty
  boolean isEmpty();
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
    return this.rest.sameMonomials(this.first.findAndRemove(other));
  }

  // determines if this MtLoMonomial is empty
  public boolean isEmpty() {
    return false;
  }
}

class Polynomial {
  ILoMonomial monomials;

  Polynomial(ILoMonomial monomials) {
    this.monomials = monomials;
  }

  // determines if this polynomial is equal to that polynomial
  public boolean samePolynomial(Polynomial other) {
    return this.monomials.sameMonomials(other.monomials);
    // && other.monomials.sameMonomials(this.monomials)
  }
}

class ExamplesPolynomial {
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

  Polynomial poly1 = new Polynomial(this.monomials1);
  Polynomial poly2 = new Polynomial(this.monomials2);
  Polynomial poly2Jumbled = new Polynomial(this.monomials2Jumbled);
  Polynomial poly3 = new Polynomial(this.monomials3);

  // tests for samePolynomial
  boolean testSamePolynomial(Tester t) {
    return t.checkExpect(this.poly1.samePolynomial(this.poly1), true)
        && t.checkExpect(this.poly1.samePolynomial(this.poly2), false)
        && t.checkExpect(this.poly1.samePolynomial(this.poly3), false)
        && t.checkExpect(this.poly2.samePolynomial(this.poly2Jumbled), true);
  }

  // tests for sameMonomials
  boolean testSameMonomials(Tester t) {
    return t.checkExpect(this.monomials1.sameMonomials(this.monomials1), true)
        && t.checkExpect(this.monomials1.sameMonomials(this.monomials2), false)
        && t.checkExpect(this.monomials1.sameMonomials(this.monomials3), false)
        && t.checkExpect(this.monomials2.sameMonomials(this.monomials2Jumbled), true);
  }

  // tests for isEmpty
  boolean testIsEmpty(Tester t) {
    return t.checkExpect(this.monomials1.isEmpty(), false)
        && t.checkExpect(new MtLoMonomial().isEmpty(), true);
  }
}
