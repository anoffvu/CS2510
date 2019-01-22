import tester.Tester;

interface ILoFeature {
  // calculates the capacity of venues from a list of features
  int capacityFromFeatures();

  // Calculates the total foodiness rating of all the features in a list
  double sumFoodinessRating();

  // counts the number of restaurants in a list of features
  int countRestaurants();

  // creates a restaurant info string from a list of features
  String restaurantInfoFromFeatures();
}

interface IFeature {
  // calculates the capacity of a single feature
  int capacityFeature();

  // Calculates the total foodiness rating of this feature
  double foodinessRatingOfFeature();

  // calculates the number of restaurants in this feature
  int restaurantCountOfThisFeature();

  // computes the restaurant string of this feature
  String restaurantInfoOfThisFeature();
}

class ConsLoFeature implements ILoFeature {
  // the first feature in this list of features
  IFeature first;
  // the rest of the features in this list of features
  ILoFeature rest;

  ConsLoFeature(IFeature first, ILoFeature rest) {
    this.first = first;
    this.rest = rest;
  }

  /*
   * Template
   * Fields:
   * this.first ... IFeature
   * this.rest ... ILoFeature
   * Methods:
   * capacityFromFeatures ... int
   * sumFoodinessRating ... double
   * countRestaurants ... int
   * restaurantInfoFromFeatures ... String
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

  // creates a restaurant string from a non empty list of features
  public String restaurantInfoFromFeatures() {
    return this.first.restaurantInfoOfThisFeature() + this.rest.restaurantInfoFromFeatures();
  }
}

class MtLoFeature implements ILoFeature {
  MtLoFeature() {
  }

  /*
   * Template
   * Fields:
   * this.first ... IFeature
   * this.rest ... ILoFeature
   * Methods:
   * capacityFromFeatures ... int
   * sumFoodinessRating ... double
   * countRestaurants ... int
   * restaurantInfoFromFeatures ... String
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

  // creates a restaurant string from a empty list of features
  public String restaurantInfoFromFeatures() {
    return "";
  }
}

class Place {
  // name of this place
  String name;
  // a list of all the features that belong to this place
  ILoFeature features;

  Place(String name, ILoFeature features) {
    this.name = name;
    this.features = features;
  }
  /*
   * Template
   * Fields:
   * this.name ... String
   * this.features ... ILoFeature
   * Methods:
   * totalCapacity ... int
   * restaurantCountOfThisPlace ... int
   * sumfoodinessrating ... double
   * foodinessRating ... double
   * Methods on Fields
   * this.features.capacityFromFeatures ... int
   * this.features.countRestaurants ... int
   * this.features.sumFoodinessRating ... double
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
    if (this.features.countRestaurants() == 0) {
      return 0.0;
    }
    else {
      return this.features.sumFoodinessRating() / this.features.countRestaurants();
    }
  }

  // produces one String that has in it all names and types of restaurants reachable from
  // this place
  String restaurantInfo() {
    if (this.features.countRestaurants() == 0) {
      return "";
    }
    else {
      return this.features.restaurantInfoFromFeatures().substring(0,
          this.features.restaurantInfoFromFeatures().length() - 2);
    }
  }

  // produces the restaurant info of a place when it is accessed by a shuttle
  public String restaurantInfoOfThisPlace() {
    return this.features.restaurantInfoFromFeatures();
  }
}

class Restaurant implements IFeature {
  // the name of this restaurant
  String name;
  // the kind of food this restaurant sells
  String type;
  // the average customer rating of this restaurant
  double averageRating;

  Restaurant(String name, String type, double averageRating) {
    // name of this restaurant
    this.name = name;
    // what kind of food this restaurant sells
    this.type = type;
    // the average rating of this restaurant
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

  // computes the restaurant string of this Restaurant
  public String restaurantInfoOfThisFeature() {
    return this.name + " (" + this.type + "), ";
  }
}

class Venue implements IFeature {
  // the name of this venue
  String name;
  // the type of this venue
  String type;
  // how many people this venue can hold
  int capacity;

  Venue(String name, String type, int capacity) {
    // the name of this venue
    this.name = name;
    // what kind of venue this is
    this.type = type;
    // how many people this venue can hold
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

  // computes the count of restaurants in this feature
  public int restaurantCountOfThisFeature() {
    return 0;
  }

  // computes the restaurant info string of this Venue
  public String restaurantInfoOfThisFeature() {
    return "";
  }

}

class ShuttleBus implements IFeature {
  // the name of this shuttle bus
  String name;
  // the place where this shuttle drops you off
  Place destination;

  ShuttleBus(String name, Place destination) {
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
   * this.destination.restaurantCountOfThisPlace ... int
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

  // computes the restaurant info string of this ShuttleBus
  public String restaurantInfoOfThisFeature() {
    return this.destination.restaurantInfoOfThisPlace();
  }
}

/*
 * This city is called Anville. Everything is about me, An. There are 3 places: AnSide
 * Galleria, An Station, An End. AnSide Galleria has a teriyaki restaurant named An Japan
 * that is rated 3.9 stars, a coffee shop named Anbucks that is rated 4.1 stars, and a
 * Shuttle called
 * An Bridge Shuttle that goes to An End. An Station has a Shuttle
 * called An Express that goes to An End, another Shuttle called An Cruiser that goes to
 * An Side. An End has a public venue called An Common that can hold 150,000 people.
 */

class ExamplesPlaces {
  // An End
  IFeature anCommon = new Venue("An Common", "public", 150000);
  ConsLoFeature anEndFeatures = new ConsLoFeature(this.anCommon, new MtLoFeature());
  Place anEnd = new Place("An End", this.anEndFeatures);
  // An Side
  IFeature anJapan = new Restaurant("An Japan", "teriyaki", 3.9);
  IFeature anbucks = new Restaurant("Anbucks", "coffee", 4.1);
  IFeature anBridgeShuttle = new ShuttleBus("An Bridge Shuttle", this.anEnd);
  ConsLoFeature anSideFeaturesOne = new ConsLoFeature(this.anJapan, new MtLoFeature());
  ConsLoFeature anSideFeaturesTwo = new ConsLoFeature(this.anbucks, this.anSideFeaturesOne);
  ConsLoFeature anSideFeatures = new ConsLoFeature(this.anBridgeShuttle, this.anSideFeaturesTwo);
  Place anSide = new Place("AnSide Galleria", this.anSideFeatures);
  // An Station
  IFeature anExpress = new ShuttleBus("An Express", this.anEnd);
  IFeature anCruiser = new ShuttleBus("An Cruiser", this.anSide);
  ConsLoFeature anStationFeaturesOne = new ConsLoFeature(this.anExpress, new MtLoFeature());
  ConsLoFeature anStationFeatures = new ConsLoFeature(this.anCruiser, this.anStationFeaturesOne);
  Place anStation = new Place("An Station", this.anStationFeatures);

  // North End
  IFeature tdGarden = new Venue("TD Garden", "stadium", 19580);
  IFeature theDailyCatch = new Restaurant("The Daily Catch", "Sicilian", 4.4);
  ConsLoFeature northEndFeaturesOne = new ConsLoFeature(this.theDailyCatch, new MtLoFeature());
  ConsLoFeature northEndFeatures = new ConsLoFeature(this.tdGarden, this.northEndFeaturesOne);
  Place northEnd = new Place("North End", this.northEndFeatures);
  // Harvard
  IFeature borderCafe = new Restaurant("Border Cafe", "Tex-Mex", 4.5);
  IFeature harvardStadium = new Venue("Harvard Stadium", "football", 30323);
  IFeature freshman15 = new ShuttleBus("Freshmen-15", this.northEnd);
  ConsLoFeature harvardFeaturesOne = new ConsLoFeature(this.harvardStadium, new MtLoFeature());
  ConsLoFeature harvardFeaturesTwo = new ConsLoFeature(this.borderCafe, this.harvardFeaturesOne);
  ConsLoFeature harvardFeatures = new ConsLoFeature(this.freshman15, harvardFeaturesTwo);
  Place harvard = new Place("Harvard", this.harvardFeatures);
  // South Station
  IFeature littleItalyExpress = new ShuttleBus("Little Italy Express", this.northEnd);
  IFeature reginasPizza = new Restaurant("Regina's Pizza", "pizza", 4.0);
  IFeature crimsonCruiser = new ShuttleBus("Crimson Cruiser", this.harvard);
  IFeature bostonCommon = new Venue("Boston Common", "public", 150000);
  ConsLoFeature southStationFeaturesOne = new ConsLoFeature(this.bostonCommon, new MtLoFeature());
  ConsLoFeature southStationFeaturesTwo = new ConsLoFeature(this.crimsonCruiser,
      this.southStationFeaturesOne);
  ConsLoFeature southStationFeaturesThree = new ConsLoFeature(this.reginasPizza,
      this.southStationFeaturesTwo);
  ConsLoFeature southStationFeatures = new ConsLoFeature(this.littleItalyExpress,
      this.southStationFeaturesThree);
  Place southStation = new Place("South Station", this.southStationFeatures);
  // Cambridge Side
  IFeature sarkuJapan = new Restaurant("Sarku Japan", "teriyaki", 3.9);
  IFeature starbucks = new Restaurant("Starbucks", "coffee", 4.1);
  IFeature bridgeShuttle = new ShuttleBus("bridge shuttle", this.southStation);
  ConsLoFeature cambridgeSideFeaturesOne = new ConsLoFeature(this.bridgeShuttle, new MtLoFeature());
  ConsLoFeature cambridgeSideFeaturesTwo = new ConsLoFeature(this.starbucks,
      this.cambridgeSideFeaturesOne);
  ConsLoFeature cambridgeSideFeatures = new ConsLoFeature(this.sarkuJapan,
      this.cambridgeSideFeaturesTwo);
  Place cambridgeSide = new Place("CambridgeSide Galleria", this.cambridgeSideFeatures);

  // mist test examples
  IFeature busToCambridgeSide = new ShuttleBus("Bus to Cambridge", this.cambridgeSide);

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
        && t.checkExpect(this.theDailyCatch.capacityFeature(), 0)
        && t.checkExpect(this.anBridgeShuttle.capacityFeature(), 150000);
  }

  // tests for foodinessRating
  boolean testFoodinessRating(Tester t) {
    return t.checkExpect(this.harvard.foodinessRating(), 4.45)
        && t.checkExpect(this.anEnd.foodinessRating(), 0.0);
  }

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
        && t.checkExpect(this.harvardStadium.restaurantCountOfThisFeature(), 0)
        && t.checkExpect(this.anCruiser.restaurantCountOfThisFeature(), 2);
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

  // tests for restaurantInfo
  boolean testRestaurantInfo(Tester t) {
    return t.checkExpect(this.cambridgeSide.restaurantInfo(),
        "Sarku Japan (teriyaki), Starbucks (coffee), The Daily Catch (Sicilian), "
            + "Regina's Pizza (pizza), The Daily Catch (Sicilian), Border Cafe (Tex-Mex)")
        && t.checkExpect(this.anEnd.restaurantInfo(), "");
  }

  // tests for restaurantInfoFromFeatures
  boolean testRestaurantInfoFromFeatures(Tester t) {
    return t.checkExpect(this.cambridgeSideFeatures.restaurantInfoFromFeatures(),
        "Sarku Japan (teriyaki), Starbucks (coffee), The Daily Catch (Sicilian), "
            + "Regina's Pizza (pizza), The Daily Catch (Sicilian), Border Cafe (Tex-Mex), ")
        && t.checkExpect(this.anEndFeatures.restaurantInfoFromFeatures(), "");
  }

  // tests for restaurantInfoOfThisFeature
  boolean testRestaurantInfoOfThisFeature(Tester t) {
    return t.checkExpect(this.busToCambridgeSide.restaurantInfoOfThisFeature(),
        "Sarku Japan (teriyaki), Starbucks (coffee), The Daily Catch (Sicilian), "
            + "Regina's Pizza (pizza), The Daily Catch (Sicilian), Border Cafe (Tex-Mex), ")
        && t.checkExpect(this.tdGarden.restaurantInfoOfThisFeature(), "")
        && t.checkExpect(this.anJapan.restaurantInfoOfThisFeature(), "An Japan (teriyaki), ");
  }

  // tests for restaurantInfoOfThisPlace
  boolean testRestaurantInfoOfThisPlace(Tester t) {
    return t.checkExpect(this.cambridgeSide.restaurantInfoOfThisPlace(),
        "Sarku Japan (teriyaki), Starbucks (coffee), The Daily Catch (Sicilian), "
            + "Regina's Pizza (pizza), The Daily Catch (Sicilian), Border Cafe (Tex-Mex), ")
        && t.checkExpect(this.anEnd.restaurantInfoOfThisPlace(), "");
  }
}

/*
 * Some methods will double count because they will visit the same places twice. For
 * example, a method call on South Station will visit the destinations of its shuttles. in
 * the case of South Station, it will visit North End, then Harvard. Once it is at
 * Harvard, it will access it's shuttles which will visit North End again. This means that
 * all of North End and it's features are counted twice.
 * 
 * restaurantInfo(), totalCapacity(), and foodinessRating() should all be victim of this
 * kind of double counting, as it does access the shuttles of the places.
 */