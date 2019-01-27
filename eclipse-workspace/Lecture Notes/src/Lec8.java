interface IDocument {
  // format the document in bibliography form
  String bibFormat();

  // format the author into bibliography form
  String bibFormatAuthor();
}

interface ILoDocument {

}

interface ILoString {

}

class ConsLoDocument implements ILoDocument {
  IDocument first;
  ILoDocument rest;

  ConsLoDocument(IDocument first, ILoDocument rest) {
    this.first = first;
    this.rest = rest;
  }
}

class MtLoDocument implements ILoDocument {
  MtLoDocument() {
  }
}

class ConsLoString implements ILoString {
  String first;
  ILoString rest;

  ConsLoString(String first, ILoString rest) {
    this.first = first;
    this.rest = rest;
  }
}

class MtLoString implements ILoString {
  MtLoString() {
  }
}

class Book implements IDocument {
  String author;
  String title;
  String publisher;
  ILoDocument bookBib;

  Book(String author, String title, String publisher, ILoDocument bookBib) {
    this.author = author;
    this.title = title;
    this.publisher = publisher;
    this.bookBib = bookBib;
  }
  
  String bibFormatAuthor() {
    return this.
  }
}

class WikiArticle implements IDocument {
  String author;
  String title;
  String URL;
  ILoDocument wikiBib;

  WikiArticle(String author, String title, String URL, ILoDocument wikiBib) {
    this.author = author;
    this.title = title;
    this.URL = URL;
    this.wikiBib = wikiBib;
  }
}
