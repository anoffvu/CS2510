import tester.Tester;

interface ILoIFeature {
  // calculates the capacity of venues from a list of features
  int capacityFromFeatures();
  double sumFoodinessRating();
  int countRestaurants();
}

interface IFeature {
  // calculates the capacity of a single feature
  int capacityFeature();
  double foodinessRatingOfFeature();
  int restaurantCountOfThisFeature();
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
   * capacityFromFeatures ... int
   * sumFoodinessRating ... double
   * countRestaurants ... int
   * Methods on Fields:
   * this.first.capacityFeature ... int
   * this.rest.capacityFeature ... int
   * this.first.foodiniessRatingOfFeature() ... double
   * this.rest.sumFoodinessRating() ... double
   * this.first.restaurantCountOfThisFeature ... int
   * this.rest.countRestaurants() ... int
   */
  // calculates the capacity of a non empty list of IFeatures
  public int capacityFromFeatures() {
    return this.first.capacityFeature() + this.rest.capacityFromFeatures();
  }

  // calculates the total foodiness ratings from all reachable restaurants
  public double sumFoodinessRating() {
    return this.first.foodinessRatingOfFeature() + this.rest.sumFoodinessRating();
  }

  // calculates the total number of reachable restaurants
  public int countRestaurants() {
    return this.first.restaurantCountOfThisFeature() + this.rest.countRestaurants();
  }
}

class MtLoIFeature implements ILoIFeature {
    MtLoIFeature() { }

  /*
   * Template
   * Fields:
   * this.first ... IFeature
   * this.rest ... ILoIFeature
   * Methods:
   * capacityFromFeatures ... int
   * sumFoodinessRating ... double
   * countRestaurants ... int
   * Methods on Fields:
   */
  // calculates the capacity of a empty list of IFeatures
  public int capacityFromFeatures() {
    return 0;
  }

  // calculates the total foodiness ratings from all reachable restaurants in an empty list
  // of IFeatures
  public double sumFoodinessRating() {
    return 0.0;
  }

  // calculates the total number of reachable restaurants in an empty list of IFeatures
  public int countRestaurants() {
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
   * this.foodinessRating ... double
   * this.restaurantCountOfThisPlace ... int
   * Methods on Fields
   * this.features.sumFoodinessRating ... double
   * this.features.countRestaurants ... int
   */

  // computes the total available seating in all the Venues reachable from the current place
  int totalCapacity() {
    return this.features.capacityFromFeatures();
  }

  // calculates the number of restaurants in this place
  int restaurantCountOfThisPlace() {
   return this.features.countRestaurants();
 }

  // calculates the sum foodiness ratings of this place
  double sumFoodinessRatingOfThisPlace() {
    return this.features.sumFoodinessRating();
  }

  // computes the average rating of all restaurants reachable at the current place
  double foodinessRating() {
    return this.features.sumFoodinessRating() / this.features.countRestaurants();
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
   * foodinessRatingOfFeature ... double
   * restaurantCountOfThisFeature ... int
   * Methods on fields
   */
// calculates the capacity of this IFeature
  public int capacityFeature() {
    return 0;
  }

// calculates the foodiness rating of this restaurant
  public double foodinessRatingOfFeature() {
    return this.averageRating;
  }

// adds 1 to a count if this IFeature is a Restaurant
  public int restaurantCountOfThisFeature() {
    return 1;
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
   * foodinessRatingOfFeature ... double
   * restaurantCountOfThisFeature ... int
   * Methods on fields
   */
// calculates the capacity of a feature
  public int capacityFeature() {
    return this.capacity;
  }

// calculates the foodiness rating of this Venue
  public double foodinessRatingOfFeature() {
    return 0.0;
  }

// adds 1 to a count if this feature is a restaurant
  public int restaurantCountOfThisFeature() {
    return 0;
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
   * capacityFeature ... int
   * foodinessRatingOfFeature ... double
   * restaurantCountOfThisFeature ... int
   * Methods on fields
   * this.destination.totalCapacity ... int
   * this.destination.sumFoodinessRatingOfThisPlace ... double
   */
// calculates the capacity of this IFeature
  public int capacityFeature() {
    return this.destination.totalCapacity();
  }

// calculates the foodiness rating of this IFeature
  public double foodinessRatingOfFeature() {
    return this.destination.sumFoodinessRatingOfThisPlace();
  }

// adds 1 to a count if this IFeature is a Restaurant
  public int restaurantCountOfThisFeature() {
    return this.destination.restaurantCountOfThisPlace();
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
// An Side
  IFeature anJapan = new Restaurant("An Japan", "teriyaki", 3.9);
  IFeature anbucks = new Restaurant("Anbucks", "coffee", 4.1);
  IFeature anBridgeSchuttle = new SchuttleBus("An Bridge Schuttle", this.anStation);
  ConsLoIFeature anSideFeaturesOne = new ConsLoIFeature(this.anJapan, new MtLoIFeature());
  ConsLoIFeature anSideFeaturesTwo = new ConsLoIFeature(this.anbucks, this.anSideFeaturesOne);
  ConsLoIFeature anSideFeatures = new ConsLoIFeature(this.anBridgeSchuttle, this.anSideFeaturesTwo);
// An Station
  IFeature anExpress = new SchuttleBus("An Express", this.anEnd);
  IFeature anCruiser = new SchuttleBus("An Cruiser", this.anSide);
  IFeature anVroomer = new SchuttleBus("An Vroomer", this.anStation);
  ConsLoIFeature anStationFeaturesOne = new ConsLoIFeature(this.anExpress, new MtLoIFeature());
  ConsLoIFeature anStationFeaturesTwo = new ConsLoIFeature(this.anCruiser, this.anStationFeaturesOne);
  ConsLoIFeature anStationFeatures = new ConsLoIFeature(this.anVroomer, this.anStationFeaturesTwo);
// An End
  IFeature anCommon = new Venue("An Common", "public", 150000);
  ConsLoIFeature anEndFeatures = new ConsLoIFeature(this.anCommon, new MtLoIFeature());
// Anville places
  Place anSide = new Place("AnSide Galleria", this.anSideFeatures);
  Place anStation = new Place("An Station", this.anStationFeatures);
  Place anEnd = new Place("An End", this.anEndFeatures);

// Cambridge Side
  IFeature sarkuJapan = new Restaurant("Sarku Japan", "teriyaki", 3.9);
  IFeature starbucks = new Restaurant("Starbucks", "coffee", 4.1);
  ConsLoIFeature cambridgeSideFeaturesOne = new ConsLoIFeature(this.sarkuJapan, new MtLoIFeature ());
  ConsLoIFeature cambridgeSideFeaturesTwo = new ConsLoIFeature(this.starbucks, this.cambridgeSideFeaturesOne);
// South Station
  IFeature reginasPizza = new Restaurant("Regina's Pizza", "pizza", 4.0);
  IFeature bostonCommon = new Venue("Boston Common", "public", 150000);
  ConsLoIFeature southStationFeaturesOne = new ConsLoIFeature(this.littleItalyExpress, new MtLoIFeature ());
  ConsLoIFeature southStationFeaturesTwo = new ConsLoIFeature(this.reginasPizza, this.southStationFeaturesOne);
  ConsLoIFeature southStationFeaturesThree = new ConsLoIFeature(this.bostonCommon,
      this.southStationFeaturesTwo);
// North End
  IFeature tdGarden = new Venue("TD Garden", "stadium", 19580);
  IFeature theDailyCatch = new Restaurant("The Daily Catch", "Sicilian", 4.4);
  ConsLoIFeature northEndFeaturesOne = new ConsLoIFeature(this.tdGarden, new MtLoIFeature ());
// Harvard
  IFeature borderCafe = new Restaurant("Border Cafe", "Tex-Mex", 4.5);
  IFeature harvardStadium = new Venue("Harvard Stadium", "football", 30323);
  ConsLoIFeature harvardFeaturesOne = new ConsLoIFeature(this.borderCafe, new MtLoIFeature());
  ConsLoIFeature harvardFeaturesTwo = new ConsLoIFeature(this.harvardStadium,
      this.harvardFeaturesOne);
// arranging feature lists, places, and schuttles so they can past the tester
// defining objects before they are used
  ConsLoIFeature northEndFeatures = new ConsLoIFeature(this.theDailyCatch,
      this.northEndFeaturesOne);
  Place northEnd = new Place("North End", this.northEndFeatures);
  IFeature freshman15 = new SchuttleBus("Freshmen-15", this.northEnd);
  ConsLoIFeature harvardFeatures = new ConsLoIFeature(this.freshman15, harvardFeaturesTwo);
  Place harvard = new Place("Harvard", this.harvardFeatures);
  IFeature crimsonCruiser = new SchuttleBus("Crimson Cruiser", this.harvard);
  ConsLoIFeature southStationFeatures = new ConsLoIFeature(this.crimsonCruiser,
      this.southStationFeaturesThree);
  Place southStation = new Place("South Station", this.southStationFeatures);
  IFeature bridgeSchuttle = new SchuttleBus("bridge schuttle", this.southStation);
  ConsLoIFeature cambridgeSideFeatures = new ConsLoIFeature(this.bridgeSchuttle,
      this.cambridgeSideFeaturesTwo);
  Place cambridgeSide = new Place("CambridgeSide Galleria", this.cambridgeSideFeatures);
  IFeature littleItalyExpress = new SchuttleBus("Little Italy Express", this.northEnd);

  // tests for totalCapacity
  boolean testTotalCapacity(Tester t) {
    return t.checkExpect(this.anEnd.totalCapacity(), 150000)
        && t.checkExpect(this.harvard.totalCapacity(), 49903);
  }

  // tests for capacityFromFeatures
  boolean testCapacityFromFeatures(Tester t) {
    return t.checkExpect(this.harvardFeatures.capacityFromFeatures(), 49903)
        && t.checkExpect(this.anEndFeatures.capacityFromFeatures(), 150000);
  }

  // tests for capacityFeature
  boolean testCapacityFeature(Tester t) {
    return t.checkExpect(this.harvardStadium.capacityFeature(), 30323)
        && t.checkExpect(this.theDailyCatch.capacityFeature(), 0);
  }

  // tests for foodinessRating
  // boolean testFoodinessRating(Tester t) {
  // return t.checkExpect(this.harvard.foodinessRating(), 4.45);
  // }

  // tests for foodinessRatingOfFeature
  boolean testFoodinessRatingOfFeature(Tester t) {
    return t.checkExpect(this.theDailyCatch.foodinessRatingOfFeature(), 4.4)
        && t.checkExpect(this.harvardStadium.foodinessRatingOfFeature(), 0.0);
  }

  // tests for sumFoodinessRating
  boolean testSumFoodinessRating(Tester t) {
    return t.checkExpect(this.harvardFeatures.sumFoodinessRating(), 8.9)
        && t.checkExpect(this.anEndFeatures.sumFoodinessRating(), 0.0);
  }

  // tests for restaurantCountOfThisFeature
  boolean testrestaurantCountOfThisFeature(Tester t) {
    return t.checkExpect(this.theDailyCatch.restaurantCountOfThisFeature(), 1)
        && t.checkExpect(this.harvardStadium.restaurantCountOfThisFeature(), 0);
  }

  // tests for countRestaurants
  boolean testCountRestaurants(Tester t) {
    return t.checkExpect(this.harvardFeatures.countRestaurants(), 2)
        && t.checkExpect(this.anEndFeatures.countRestaurants(), 0);
  }

  // tests for restaurantCountOfThisPlace
  boolean testRestaurantCountOfThisPlace(Tester t) {
    return t.checkExpect(this.harvard.restaurantCountOfThisPlace(), 2)
        && t.checkExpect(this.anEnd.restaurantCountOfThisPlace(), 0);
  }
}
