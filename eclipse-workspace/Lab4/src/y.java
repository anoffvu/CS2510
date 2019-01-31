import tester.Tester;

interface IBook {
  // calculates the days overdue of an IBook
  int daysOverdue(int i);

  // determines if this book is overdue
  boolean isOverdue(int i);

  public double computeFine(int today);

}

abstract class ABook implements IBook {
  String title;
  int dayTaken;

  ABook(String title, int dayTaken) {
    this.title = title;
    this.dayTaken = dayTaken;
  }

  // calculates the default days over due of an ABook
  public int daysOverdue(int today) {
    return today - this.dayTaken - 14;
  }

  public boolean isOverdue(int today) {
    return this.daysOverdue(today) > 0;
  }

  public double computeFine(int today) {
    if (!this.isOverdue(today)) {
      return 0;
    }
    else {
      return (today - dayTaken) * 0.1;
    }
  }

}

class Book extends ABook {
  String author;

  Book(String title, String author, int dayTaken) {
    super(title, dayTaken);
    this.author = author;
  }
}

class RefBook extends ABook {
  RefBook(String title, int dayTaken) {
    super(title, dayTaken);
  }

  // calculates days overdue for a refbook
  public int daysOverdue(int today) {
    return today - this.dayTaken - 2;
  }

}

class AudioBook extends ABook {
  String author;

  AudioBook(String title, String author, int dayTaken) {
    super(title, dayTaken);
    this.author = author;
  }

  public double computeFine(int today) {
    if (!this.isOverdue(today)) {
      return 0;
    }
    else {
      return (today - dayTaken) * 0.2;
    }
  }
}

class ExamplesBook {
  IBook day0Book = new Book("day0 book", "An", 0);
  IBook day0RefBook = new RefBook("day0 ref", 0);
  IBook day0AudioBook = new AudioBook("day0 audio", "An", 0);

  boolean testDaysOverdue(Tester t) {
    return t.checkExpect(this.day0Book.daysOverdue(14), 0)
        && t.checkExpect(this.day0Book.daysOverdue(20), 6)
        && t.checkExpect(this.day0AudioBook.daysOverdue(4), -10)
        && t.checkExpect(this.day0RefBook.daysOverdue(4), 2)
        && t.checkExpect(this.day0RefBook.daysOverdue(2), 0)
        && t.checkExpect(this.day0RefBook.daysOverdue(1), -1);
  }

  boolean testIsOverdue(Tester t) {
    return t.checkExpect(this.day0Book.isOverdue(14), false)
        && t.checkExpect(this.day0Book.isOverdue(20), true)
        && t.checkExpect(this.day0AudioBook.isOverdue(4), false)
        && t.checkExpect(this.day0RefBook.isOverdue(4), true)
        && t.checkExpect(this.day0RefBook.isOverdue(2), false)
        && t.checkExpect(this.day0RefBook.isOverdue(1), false);
  }
}