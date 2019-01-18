import tester.Tester;

interface ILoIFeature {
  // calculates the capacity of venues from a list of features
  int capacityFromFeatures();
}

interface IFeature {
  // calculates the capacity of a single feature
  int capacityFeature();
}

class ConsLoIFeature implements ILoIFeature {
  IFeature first;
  ILoIFeature rest;

  ConsLoIFeature(IFeature first, ILoIFeature rest) {
    this.first = first;
    this.rest = rest;
    }
  /*
   * Template
   * Fields:
   * this.first ... IFeature
   * this.rest ... ILoIFeature
   * Methods:
   */
  // calculates the capacity of a non empty list of IFeatures
  public int capacityFromFeatures() {
    return this.first.capacityFeature() + this.rest.capacityFromFeatures();
  }


}

class MtLoIFeature implements ILoIFeature {
    MtLoIFeature() { }

  // calculates the capacity of and empty list of IFeatures
  public int capacityFromFeatures() {
    return 0;
  }
}

class Place {
  String name;
  ILoIFeature features;

  Place(String name, ILoIFeature features) {
    this.name = name;
    this.features = features;
  }
  /*
   * Template
   * Fields:
   * this.name ... String
   * this.features ... ILoIFeature
   * Methods:
   * this.totalCapacity ... int
   */

  // computes the total available seating in all the Venues reachable from the current place

  int totalCapacity() {
    return this.features.capacityFromFeatures();
  }
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
   * Template
   * Fields:
   * this.name ... String
   * this.type ... String
   * this.averageRating ... double
   * Methods:
   * capacityFeature ... int
   */

  public int capacityFeature() {
    return 0;
  }
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
   * Template
   * Fields:
   * this.name ... String
   * this.type ... String
   * this.capcity ... int
   * Methods:
   * capacityFeature ... int
   */

  public int capacityFeature() {
    return this.capacity;
  }

}

class SchuttleBus implements IFeature {
  String name;
  Place destination;

  SchuttleBus(String name, Place destination) {
    this.name = name;
    this.destination = destination;
  }
  /*
   * Template
   * Fields:
   * this.name ... String
   * this.destination ... Place
   * Methods:
   */
  public int capacityFeature() {
    return this.destination.totalCapacity();
  }
}


/*
 * This city is called Anville. Everything is about me, An. There are 3 places: AnSide
 * Galleria, An Station, An End. AnSide Galleria has a teriyaki restaurant named An Japan
 * that is rated 3.9 stars, a coffee shop named Anbucks that is rated 4.1 stars, aStationnd a
 * schuttle called An Bridge Schuttle that goes to An Station. An Station has a schuttle
 * called An Express that goes to An End, another schuttle called An Cruiser that goes to
 * An Side, and a public venue called An Common that can hold 150,000 people. An End has a
 * schuttle bus called An Vroomer that goes to An Station.
 */

class ExamplesPlaces {
  Place anSide = new Place("AnSide Galleria", this.anSideFeatures);
  IFeature anJapan = new Restaurant("An Japan", "teriyaki", 3.9);
  IFeature anbucks = new Restaurant("Anbucks", "coffee", 4.1);
  IFeature anBridgeSchuttle = new SchuttleBus("An Bridge Schuttle", this.anStation);
  ConsLoIFeature anSideFeaturesOne = new ConsLoIFeature(this.anJapan, new MtLoIFeature());
  ConsLoIFeature anSideFeaturesTwo = new ConsLoIFeature(this.anbucks, this.anSideFeaturesOne);
  ConsLoIFeature anSideFeatures = new ConsLoIFeature(this.anBridgeSchuttle, this.anSideFeaturesTwo);

  Place anStation = new Place("An Station", this.anStationFeatures);
  IFeature anExpress = new SchuttleBus("An Express", this.anEnd);
  IFeature anCruiser = new SchuttleBus("An Cruiser", this.anSide);
  IFeature anVroomer = new SchuttleBus("An Vroomer", this.anStation);
  ConsLoIFeature anStationFeaturesOne = new ConsLoIFeature(this.anExpress, new MtLoIFeature());
  ConsLoIFeature anStationFeaturesTwo = new ConsLoIFeature(this.anCruiser, this.anStationFeaturesOne);
  ConsLoIFeature anStationFeatures = new ConsLoIFeature(this.anVroomer, this.anStationFeaturesTwo);

  Place anEnd = new Place("An End", this.anEndFeatures);
  IFeature anCommon = new Venue("An Common", "public", 150000);
  ConsLoIFeature anEndFeatures = new ConsLoIFeature(this.anCommon, new MtLoIFeature());


  Place cambridgeSide = new Place("CambridgeSide Galleria", this.cambridgeSideFeatures);
  IFeature sarkuJapan = new Restaurant("Sarku Japan", "teriyaki", 3.9);
  IFeature starbucks = new Restaurant("Starbucks", "coffee", 4.1);
  IFeature bridgeSchuttle = new SchuttleBus("bridge schuttle", this.southStation);
  ConsLoIFeature cambridgeSideFeaturesOne = new ConsLoIFeature(this.sarkuJapan, new MtLoIFeature ());
  ConsLoIFeature cambridgeSideFeaturesTwo = new ConsLoIFeature(this.starbucks, this.cambridgeSideFeaturesOne);
  ConsLoIFeature cambridgeSideFeatures = new ConsLoIFeature(this.bridgeSchuttle, this.cambridgeSideFeaturesTwo);

  Place southStation = new Place("South Station", this.southStationFeatures);
  IFeature littleItalyExpress = new SchuttleBus("Little Italy Express", this.northEnd);
  IFeature reginasPizza = new Restaurant("Regina's Pizza", "pizza", 4.0);
  IFeature crimsonCruiser = new SchuttleBus("Crimson Cruiser", this.harvard);
  IFeature bostonCommon = new Venue("Boston Common", "public", 150000);
  ConsLoIFeature southStationFeaturesOne = new ConsLoIFeature(this.littleItalyExpress, new MtLoIFeature ());
  ConsLoIFeature southStationFeaturesTwo = new ConsLoIFeature(this.reginasPizza, this.southStationFeaturesOne);
  ConsLoIFeature southStationFeaturesThree = new ConsLoIFeature(this.crimsonCruiser, this.southStationFeaturesTwo);
  ConsLoIFeature southStationFeatures = new ConsLoIFeature(this.bostonCommon, this.southStationFeaturesThree);

  Place northEnd = new Place("North End", this.northEndFeatures);
  IFeature tdGarden = new Venue("TD Garden", "stadium", 19580);
  IFeature theDailyCatch = new Restaurant("The Daily Catch", "Sicilian", 4.4);
  ConsLoIFeature northEndFeaturesOne = new ConsLoIFeature(this.tdGarden, new MtLoIFeature ());
  ConsLoIFeature northEndFeatures = new ConsLoIFeature(this.theDailyCatch, this.northEndFeaturesOne);

  Place harvard = new Place("Harvard", this.harvardFeatures);
  IFeature freshman15 = new SchuttleBus("Freshmen-15", this.northEnd);
  IFeature borderCafe = new Restaurant("Border Cafe", "Tex-Mex", 4.5);
  IFeature harvardStadium = new Venue("Harvard Stadium", "football", 30323);
  ConsLoIFeature harvardFeaturesOne = new ConsLoIFeature(this.freshman15, new MtLoIFeature ());
  ConsLoIFeature harvardFeaturesTwo = new ConsLoIFeature(this.borderCafe, this.harvardFeaturesOne);
  ConsLoIFeature harvardFeatures = new ConsLoIFeature(this.harvardStadium, this.harvardFeaturesTwo);
  
  boolean testTotalCapacity(Tester t) {
    return t.checkExpect(this.anEnd.totalCapacity(), 150000);
  }
}
