class places {

}

interface ILoIFeature {

}
class ConsLoIFeature implements ILoIFeature {
    IFeature first;
    ILoFeature rest;
    ConsLoIFeature(IFeature first, ILoIFeature rest) {
        this.first = first;
        this.rest = rest;
    }
    /*
    fields:
    this.first ... IFeature
    this.rest ... ILoIFeature
    methods:
    */
}
class MtLoIFeature implements ILoIFeature {
    MtLoIFeature() { }
}

class Place {
  String name;
  ILoIFeature features;
  place(String name, ILoIFeature features) {
    this.name = name;
    this.features = features;
  }
  /*
  fields:
  this.name ... String
  this.features ... ILoIFeature
  methods:
  */
  // computes the total available seating in all the venues reachable from the current place
  int totalCapacity () {
    if (first.this.features instanceof Venue) {
      int
    } else {
      return
    }

  }

}

interface IFeature {
}

class Restaurant implements IFeature {
  String name;
  String type;
  double averageRating;

  Restaurant(String name, String type, double averageRating) {
    this.name = name;
    this.type = type;
    this.averageRating = averageRating;
  }
  /*
  fields:
  this.name ... String
  this.type ... String
  this.averageRating ... double
  methods:
  */
}

class Venue implements IFeature {
  String name;
  String type;
  int capacity;

  Venue(String name, String type, int capacity) {
    this.name = name;
    this.type = type;
    this.capacity = capacity;
  }
  /*
  fields:
  this.name ... String
  this.type ... String
  this.capcity ... int
  methods:
  */
}

class SchuttleBus implements IFeature {
  String name;
  Place destination;

  SchuttleBus(String name, Place destination) {
    this.name = name;
    this.destination = destination;
  }
  /*
  fields:
  this.name ... String
  this.type ... String
  this.capcity ... int
  methods:
  */
}


// !! Describe (in English, or in a diagram, or in code...) the contents of a map that has at least one venue, two restaurants, three places, and four shuttle busses. !!
// !! Design the data representation of the example you just described. !!

class ExamplesPlaces {
  Place cambridgeSide = new ("CambridgeSide Galleria", this.cambridgeSideFeatures);
  IFeature sarkuJapan = new Restaurant("Sarku Japan", "teriyaki", 3.9);
  IFeature starbucks = new Restaurant("Starbucks", "coffee", 4.1);
  IFeature bridgeSchuttle = new SchuttleBus("bridge schuttle", this.southStation);

  Place southStation = new Place("South Station", this.southStationFeatures);
  IFeature littleItalyExpress = new SchuttleBus("Little Italy Express", this.northEnd);
  IFeature reginasPizza = new Restaurant("Regina's Pizza", "pizza", 4.0);
  IFeature crimsonCruiser = new SchuttleBus("Crimson Cruiser", this.harvard);
  IFeature bostonCommon = new Venue("Boston Common", "public", 150,000);

  Place northEnd = new Place("North End", this.northEndFeatures);
  IFeature tdGarden = new Venue("TD Garden", "stadium", 19,580);
  IFeature theDailyCatch = new Restaurant("The Daily Catch", "Sicilian", 4.4);

  Place harvard = new Place("Harvard", this.harvardFeatures);
  IFeature freshman15 = new SchuttleBus("Freshmen-15", this.northEnd);
  IFeature borderCafe = new Restaurant("Border Cafe", "Tex-Mex", 4.5);
  IFeature harvardStadium = new Venue("Harvard Stadium", "football", 30,323);

}
