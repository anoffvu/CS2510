
public class test {

}

interface IText {
}

// a book is an IText
class Book implements IText {
  int pages;
  String title;
  String author;

  // constructor
  Book(int pages, String title, String author) {
    this.pages = pages;
    this.title = title;
    this.author = author;
  }
}

// a pampthlet is an IText
class Pamphlet implements IText {
  String title;
  String author;

  Pamphlet(String title, String author) {
    this.title = title;
    this.author = author;
  }
}

class ExamplesBooks {
  Book book1 = new Book(420, "book1", "Ozzy Osbourne");
}

class ExmaplesPamphlet {
  IText pamphlet1 = new Pamphlet("pamphlet1", "Jason");
}
