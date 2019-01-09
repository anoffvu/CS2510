
class GraphicNovel {
  String title;
  String author;
  String artist;
  int year;
  double cost;
  boolean monochrome;

  // constructor
  GraphicNovel(String title, String author, String artist, int year, double cost,
      boolean monochrome) {
    this.title = title;
    this.author = author;
    this.artist = artist;
    this.year = year;
    this.cost = cost;
    this.monochrome = monochrome;
  }
}

class ExamplesGraphicNovel {
  GraphicNovel maus = new GraphicNovel("Maus", "Spiegelman", "Spiegelman", 1980, 17.60, true);
  GraphicNovel logicomix = 
      new GraphicNovel("Logicomix", "Doxiadis", "Papadatos", 2009, 21.00, false);
  GraphicNovel sabrina = 
      new GraphicNovel("Sabrina", "Drnaso", "Drnaso", 2018, 19.00, false);
}
