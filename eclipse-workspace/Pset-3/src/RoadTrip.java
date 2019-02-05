import tester.Tester;

// a list of directions
interface ILoDirection {

  // returns a list of road trip chunks using an accumulator
  // accumulator - keeps track of distance traveled in this road trip chunk
  ILoRoadTripChunk splitUpTripAccumulator(String driver1, String driver2, int splitSize,
      int distanceTraveledInThisChunk, ILoDirection directionsOfThisChunk,
      ILoRoadTripChunk chunksSoFar);

  // reverses the directions
  ILoDirection reverseDirections(ILoDirection reversedSoFar);

  // prints out the directions
  void printDirections();

  // computes whether or not the list of directions is empty
  boolean isEmpty();

  // removes the last direction from the list of directions
  ILoDirection removeLastDirection();
}

interface ILoRoadTripChunk {
  
  // reverses a list of road trip chunks
  ILoRoadTripChunk reverseChunks(ILoRoadTripChunk reversedSoFar);

  // prints the road trip chunks
  void printChunks();

  
  ILoRoadTripChunk parseFinal();

  // removes the last driver switch from the list of road trip chunks
  ILoRoadTripChunk removeLastSwitch();

  // removes the last road trip chunk
  ILoRoadTripChunk removeLastChunk();

  // computes whether or not the list of road trip chunks is empty
  boolean isEmpty();
}

class ConsLoDirection implements ILoDirection {
  Direction first;
  ILoDirection rest;

  ConsLoDirection(Direction first, ILoDirection rest) {
    this.first = first;
    this.rest = rest;
  }
  
  /* TEMPLATE
   * Fields:
   * this.first ... Direction
   * this.rest ... ILoDirection
   * Methods:
   * this.splitUpTripAccumulator(String driver, String switchDriver, int splitSize,
      int distanceTraveledSoFar, ILoDirection directionsOfThisChunk, ILoRoadTripChunk chunksSoFar)
      ... ILoRoadTripChunk
   * this.reverseDirections(ILoDirection reversedSoFar) ... ILoDirection
   * this.printDirections() ... void
   * this.isEmpty() ... boolean
   * this.removeLastDirection() ... ILoDirection
   * Methods for fields:
   * this.splitUpTrimAccumulator(String driver1, String driver2, int splitSize,
      int distanceTraveledInThisChunk, ILoDirection directionsOfThisChunk,
      ILoRoadTripChunk chunksSoFar) ... ILoRoadTripChunk
   * this.reverseDirections(ILoDirection reversedSoFar) ... ILoDirection
   * this.printDirections() ... void
   * this.isEmpty() ... boolean
   * this.removeLastDirection() ... ILoDirection
   */

  // returns a list of road trip chunks using an accumulator
  // accumulator - keeps track of distance traveled in this road trip chunk
  public ILoRoadTripChunk splitUpTripAccumulator(String driver, String switchDriver, int splitSize,
      int distanceTraveledSoFar, ILoDirection directionsOfThisChunk, ILoRoadTripChunk chunksSoFar) {
    
    // if the switch will happen after this direction
    if (this.first.switchInThisDirection(splitSize, distanceTraveledSoFar) == 1) {
      return this.rest.splitUpTripAccumulator(driver, switchDriver, splitSize,
          this.first.addDirectionMiles(distanceTraveledSoFar),
          new ConsLoDirection(this.first, directionsOfThisChunk), chunksSoFar);
      
    } // if the switch will happen at the end of this direction
    else if (this.first.switchInThisDirection(splitSize, distanceTraveledSoFar) == 0) {
      return this.rest.splitUpTripAccumulator(switchDriver, driver, splitSize, 0,
          new MtLoDirection(),
          new ConsLoRoadTripChunk(new RoadTripChunk(driver,
              (new ConsLoDirection(new Direction("Switch with " + switchDriver, 0),
                  new ConsLoDirection(this.first, directionsOfThisChunk)))
                      .reverseDirections(new MtLoDirection())),
              chunksSoFar));
      
    } // if the switch will happen before this direction ends
    else if (this.first.switchInThisDirection(splitSize, distanceTraveledSoFar) == -1) {
      return (new ConsLoDirection(
          this.first.splitNewChunkDirection(splitSize, distanceTraveledSoFar),
          this.rest)).splitUpTripAccumulator(switchDriver, driver, splitSize, 0,
              new MtLoDirection(),
          new ConsLoRoadTripChunk(
              new RoadTripChunk(driver,
                  (new ConsLoDirection(new Direction("Switch with " + switchDriver,
                      splitSize - distanceTraveledSoFar),
                      directionsOfThisChunk)).reverseDirections(new MtLoDirection())),
                  chunksSoFar));
    }
    else {
      return new MtLoRoadTripChunk();
    }
  }

  // reverses the order directions in a ConsLoDirection
  public ILoDirection reverseDirections(ILoDirection reversedSoFar) {
    return this.rest.reverseDirections(new ConsLoDirection(this.first, reversedSoFar));
  }

  // prints the directions of a ConsLoDirection
  public void printDirections() {
    System.out.println(this.first.description + ", " + this.first.miles + "\n");
    this.rest.printDirections();
  }

  // returns whether or not the ConsLoDirection is empty
  public boolean isEmpty() {
    return false;
  }

  // removes the last direction of the ConsLoDirection
  public ILoDirection removeLastDirection() {
    if (this.rest.isEmpty()) {
      return this.rest;
    }
    else {
      return new ConsLoDirection(this.first, this.rest.removeLastDirection());
    }
  }
}

class MtLoDirection implements ILoDirection {
  MtLoDirection() {
    
  }
  
  /* TEMPLATE
   * Methods:
   * this.splitUpTripAccumulator(String driver, String switchDriver, int splitSize,
      int distanceTraveledSoFar, ILoDirection directionsOfThisChunk, ILoRoadTripChunk chunksSoFar)
      ... ILoRoadTripChunk
   * this.reverseDirections(ILoDirection reversedSoFar) ... ILoDirection
   * this.printDirections() ... void
   * this.isEmpty() ... boolean
   * this.removeLastDirection() ... ILoDirection
   */
  
  // splits up a roadTrip using an accumulator
  public ILoRoadTripChunk splitUpTripAccumulator(String driver, String switchDriver, int splitSize,
      int distanceTraveledSoFar, ILoDirection directionsOfThisChunk, ILoRoadTripChunk chunksSoFar) {
    return (new ConsLoRoadTripChunk(new RoadTripChunk(driver, directionsOfThisChunk), chunksSoFar)
        .reverseChunks(new MtLoRoadTripChunk()));
  }

  // reverses an empty list of directions
  public ILoDirection reverseDirections(ILoDirection reversedSoFar) {
    return reversedSoFar;
  }

  // prints directions of an empty list of direction
  public void printDirections() {
    System.out.println("");
  }

  // returns whether or not the empty list of direction is empty
  public boolean isEmpty() {
    return true;
  }

  // removes the last direction from the empty list of direction
  public ILoDirection removeLastDirection() {
    return this;
  }

}

class ConsLoRoadTripChunk implements ILoRoadTripChunk {
  RoadTripChunk first;
  ILoRoadTripChunk rest;

  ConsLoRoadTripChunk(RoadTripChunk first, ILoRoadTripChunk rest) {
    this.first = first;
    this.rest = rest;
  }
  
  /* TEMPLATE
   * Fields:
   * this.first ... RoadTripChunk
   * this.rest ... ILoRoadTripChunk
   * Methods:
   * this.reverseChunks(ILoRoadTripChunk reversedSoFar) ... ILoRoadTripChunk
   * this.printChunks() .. void
   * this.parseFinal() ... ILoRoadTripChunk
   * this.removeLastSwitch() ... ILoRoadTripChunk
   * this.removeLastChunk() ... ILoRoadTripChunk
   * this.isEmpty() ... boolean
   * Methods for fields:
   * this.reverseChunks(ILoRoadTripChunk reversedSoFar) ... ILoRoadTripChunk
   * this.printDirections() .. void
   * this.printChunks() ... void
   * this.removeLastSwitch() ... ILoRoadTripChunk
   * this.isEmpty() ... boolean
   */

  // reverses a non empty list of RoadTripChunks
  public ILoRoadTripChunk reverseChunks(ILoRoadTripChunk reversedSoFar) {
    return this.rest.reverseChunks(new ConsLoRoadTripChunk(this.first, reversedSoFar));
  }

  // prints out the chunks 
  public void printChunks() {
    this.first.directions.printDirections();
    System.out.println("- " + this.first.driver + "\n\n");
    this.rest.printChunks();

  }

  public ILoRoadTripChunk parseFinal() {
    ILoRoadTripChunk ret = this.removeLastChunk();
    return ret.removeLastSwitch();
  }

  // removes the last driver switch
  public ILoRoadTripChunk removeLastSwitch() {
    if (this.rest.isEmpty()) {
      return new ConsLoRoadTripChunk(this.first.removeLastSwitchHelper(), this.rest);
    }
    else {
      return new ConsLoRoadTripChunk(this.first, this.rest.removeLastSwitch());
    }
  }

  // removes the last chunks from the list of road trip chunks
  public ILoRoadTripChunk removeLastChunk() {
    if (this.rest.isEmpty()) {
      return this.rest;
    }
    else {
      return new ConsLoRoadTripChunk(this.first, this.rest.removeLastChunk());
    }
  }

  // checks whether or not the list of road trip chunks is empty
  public boolean isEmpty() {
    return false;
  }
}

class MtLoRoadTripChunk implements ILoRoadTripChunk {
  MtLoRoadTripChunk() {
    
  }

  /* TEMPLATE
   * Methods:
   * this.reverseChunks(ILoRoadTripChunk reversedSoFar) ... ILoRoadTripChunk
   * this.printChunks() .. void
   * this.parseFinal() ... ILoRoadTripChunk
   * this.removeLastSwitch() ... ILoRoadTripChunk
   * this.removeLastChunk() ... ILoRoadTripChunk
   * this.isEmpty() ... boolean
   */
  
  // reverses an empty list of RoadTripChunks
  public ILoRoadTripChunk reverseChunks(ILoRoadTripChunk reversedSoFar) {
    return reversedSoFar;
  }

  // prints the list of road trip chunks
  public void printChunks() {
    System.out.println("");
  }

  public ILoRoadTripChunk parseFinal() {
    return this;
  }

  // removes the last switch from the list of road trip chunks
  public ILoRoadTripChunk removeLastSwitch() {
    return this;
  }

  // removes last chunk from the list of road trip chunks
  public ILoRoadTripChunk removeLastChunk() {
    return this;
  }

  // checks whether or not the empty list of road trip chunks is empty
  public boolean isEmpty() {
    return true;
  }
}

class Direction {
  String description;
  int miles;

  Direction(String description, int miles) {
    this.description = description;
    this.miles = miles;
  }
  
  /* TEMPLATE
   * Fields:
   * this.direction ... String
   * this.miles ... int
   * Methods:
   * this.splitNewChunkDirection(int splitSize, int distanceTraveledSoFar) ... Direction
   * this.addDirectionMiles(int distanceTraveledSoFar) ... int
   * this.milesOfThisDirection() ... int
   * this.switchInThisDirection(int splitSize, int distanceTraveledSoFar) ... int
   */

  // splits a new chunk direction depending on the given split size and distance traveled so far
  public Direction splitNewChunkDirection(int splitSize, int distanceTraveledSoFar) {
    return new Direction(this.description, this.miles - (splitSize - distanceTraveledSoFar));
  }

  // adds the distance traveled so far with current miles
  public int addDirectionMiles(int distanceTraveledSoFar) {
    return distanceTraveledSoFar + this.miles;
  }

  // returns the miles of the direction
  public int milesOfThisDirection() {
    return miles;
  }

  // checks if the split should come this direction or not
  public int switchInThisDirection(int splitSize, int distanceTraveledSoFar) {
    if (this.miles + distanceTraveledSoFar > splitSize) {
      return -1;
    }
    else if (this.miles + distanceTraveledSoFar == splitSize) {
      return 0;
    }
    else {
      return 1;
    }
  }
}

class RoadTrip {
  String driver1;
  String driver2;
  ILoDirection directions;

  RoadTrip(String driver1, String driver2, ILoDirection directions) {
    this.driver1 = driver1;
    this.driver2 = driver2;
    this.directions = directions;
  }
  
  /* TEMPLATE
   * Fields:
   * this.driver1 ... String
   * this.driver2 ... String
   * Methods:
   * this.splitUpTrip(int splitSize) ... ILoRoadTripChunk
   * Methods for fields:
   * this.splitUpTripAccumulator(String driver1, String driver2, int splitSize,
      int distanceTraveledInThisChunk, ILoDirection directionsOfThisChunk,
      ILoRoadTripChunk chunksSoFar) ... ILoRoadTripChunk
   */

  // splits up the trip with the given split size
  ILoRoadTripChunk splitUpTrip(int splitSize) {
    return (this.directions.splitUpTripAccumulator(this.driver1, this.driver2, splitSize, 0,
        new MtLoDirection(), new MtLoRoadTripChunk())).parseFinal();
  }
}

class RoadTripChunk {
  String driver;
  ILoDirection directions;

  RoadTripChunk(String driver, ILoDirection directions) {
    this.driver = driver;
    this.directions = directions;
  }
  
  /* TEMPLATE
   * Fields:
   * this.driver ... String
   * this.directions ... ILoDirection
   * Methods:
   * this.removeLastSwitchHelper() ... RoadTripChunk
   * Methods for fields:
   * this.removeLastDirection() ... ILoDirection
   */

  // returns the RoadTripChunk without the last direction
  public RoadTripChunk removeLastSwitchHelper() {
    return new RoadTripChunk(driver, this.directions.removeLastDirection());
  }
}

// examples and tests for road trip
class ExamplesRoadTrip {
  
  ILoDirection hazelHenryDirections = new ConsLoDirection(
      new Direction("Make a left at Alberquerque", 13),
      new ConsLoDirection(new Direction("Make a right at the fork", 2),
          new ConsLoDirection(new Direction("Make a left at the next fork", 3),
              new ConsLoDirection(new Direction("Take the overpass", 45), new ConsLoDirection(
                  new Direction("Destination on left", 12), new MtLoDirection())))));
  
  ILoDirection hazelHenryDirectionsRemoveLast = new ConsLoDirection(
      new Direction("Make a left at Alberquerque", 13),
      new ConsLoDirection(new Direction("Make a right at the fork", 2),
          new ConsLoDirection(new Direction("Make a left at the next fork", 3),
              new ConsLoDirection(new Direction("Take the overpass", 45), new MtLoDirection()))));
  
  ILoDirection hazelHenryDirectionsReversed = new ConsLoDirection(
      new Direction("Destination on left", 12),
      new ConsLoDirection(new Direction("Take the overpass", 45),
          new ConsLoDirection(new Direction("Make a left at the next fork", 3),
              new ConsLoDirection(new Direction("Make a right at the fork", 2),
                  new ConsLoDirection(
                      new Direction("Make a left at Alberquerque", 13), new MtLoDirection())))));
  
  ILoDirection mtDirections = new MtLoDirection();
  
  RoadTrip hazelHenryRoadTrip = new RoadTrip("Hazel", "Henry", this.hazelHenryDirections);
  
  RoadTripChunk chunkOne = new RoadTripChunk("Hazel", this.hazelHenryDirections);
  RoadTripChunk chunkRemoveLast = new RoadTripChunk("Hazel", this.hazelHenryDirectionsRemoveLast);
  
  ILoRoadTripChunk hazelHenryRoadTripChunks = new ConsLoRoadTripChunk(
      new RoadTripChunk("Hazel",
          new ConsLoDirection(new Direction("Make a left at Alberquerque", 13),
              new ConsLoDirection(new Direction("Make a right at the fork", 2),
                  new ConsLoDirection(new Direction("Switch with Henry", 0),
                      new MtLoDirection())))),
      new ConsLoRoadTripChunk(
          new RoadTripChunk("Henry",
              new ConsLoDirection(new Direction("Make a left at the next fork", 3),
                  new ConsLoDirection(new Direction("Switch with Hazel", 12),
                      new MtLoDirection()))),
          new ConsLoRoadTripChunk(
              new RoadTripChunk("Hazel",
                  new ConsLoDirection(new Direction("Switch with Henry", 15), new MtLoDirection())),
              new ConsLoRoadTripChunk(
                  new RoadTripChunk("Henry",
                      new ConsLoDirection(new Direction("Switch with Hazel", 15),
                          new MtLoDirection())),
                  new ConsLoRoadTripChunk(new RoadTripChunk("Hazel",
                      new ConsLoDirection(new Direction("Take the overpass", 3),
                          new ConsLoDirection(new Direction("Destination on left", 12),
                              new MtLoDirection()))),
                      new MtLoRoadTripChunk())))));
  
  ILoRoadTripChunk shortRoadTripChunks = new ConsLoRoadTripChunk(
      new RoadTripChunk("Hazel",
          new ConsLoDirection(new Direction("Make a left at Alberquerque", 13),
              new ConsLoDirection(new Direction("Make a right at the fork", 2),
                  new ConsLoDirection(new Direction("Switch with Henry", 0),
                      new MtLoDirection())))),
      new ConsLoRoadTripChunk(
          new RoadTripChunk("Henry",
              new ConsLoDirection(new Direction("Make a left at the next fork", 3),
                      new MtLoDirection())), new MtLoRoadTripChunk()));
  
  ILoRoadTripChunk shortRoadTripChunksReversed = new ConsLoRoadTripChunk(
      new RoadTripChunk("Henry",
          new ConsLoDirection(new Direction("Make a left at the next fork", 3),
                  new MtLoDirection())), new ConsLoRoadTripChunk(new RoadTripChunk("Hazel",
                      new ConsLoDirection(new Direction("Make a left at Alberquerque", 13),
                          new ConsLoDirection(new Direction("Make a right at the fork", 2),
                              new ConsLoDirection(new Direction("Switch with Henry", 0),
                                  new MtLoDirection())))), new MtLoRoadTripChunk()));
  
  ILoRoadTripChunk hazelHenryRoadTripChunksRemoveLastSwitch = new ConsLoRoadTripChunk(
      new RoadTripChunk("Hazel",
          new ConsLoDirection(new Direction("Make a left at Alberquerque", 13),
              new ConsLoDirection(new Direction("Make a right at the fork", 2),
                  new ConsLoDirection(new Direction("Switch with Henry", 0),
                      new MtLoDirection())))),
      new ConsLoRoadTripChunk(
          new RoadTripChunk("Henry",
              new ConsLoDirection(new Direction("Make a left at the next fork", 3),
                  new ConsLoDirection(new Direction("Switch with Hazel", 12),
                      new MtLoDirection()))),
          new ConsLoRoadTripChunk(
              new RoadTripChunk("Hazel",
                  new ConsLoDirection(new Direction("Switch with Henry", 15), new MtLoDirection())),
              new ConsLoRoadTripChunk(
                  new RoadTripChunk("Henry",
                      new ConsLoDirection(new Direction("Switch with Hazel", 15),
                          new MtLoDirection())),
                  new ConsLoRoadTripChunk(new RoadTripChunk("Hazel",
                      new ConsLoDirection(new Direction("Take the overpass", 3),
                          new ConsLoDirection(new Direction("Destination on left", 12),
                              new MtLoDirection()))),
                      new MtLoRoadTripChunk())))));
  ILoRoadTripChunk mtLoRoadTripChunk = new MtLoRoadTripChunk();

  ILoDirection hazelHenrySplitDirections = new ConsLoDirection(
      new Direction("Make a left at Alberquerque", 13),
      new ConsLoDirection(new Direction("Make a right at the fork", 2),
          new ConsLoDirection(new Direction("Switch with Henry", 0),
              new ConsLoDirection(new Direction("Make a left at the next fork", 3),
                  new ConsLoDirection(new Direction("Switch with Hazel", 12),
                      new ConsLoDirection(new Direction("Switch with Henry", 15),
                          new ConsLoDirection(new Direction("Switch with Hazel", 15),
                              new ConsLoDirection(new Direction("Take the overpass", 3),
                                  new ConsLoDirection(new Direction("Destination on left", 12),
                                      new MtLoDirection())))))))));

  ILoDirection shortDirections = new ConsLoDirection(new Direction("Destination on right", 20),
      new MtLoDirection());
  RoadTrip shortTrip = new RoadTrip("short1", "short2", this.shortDirections);
  
  ILoRoadTripChunk shortTripChunks = new ConsLoRoadTripChunk(
      new RoadTripChunk("short1",
          new ConsLoDirection(new Direction("Switch with short2", 15), new MtLoDirection())),
      new ConsLoRoadTripChunk(
          new RoadTripChunk("short2",
              new ConsLoDirection(new Direction("Destination on right", 5), new MtLoDirection())),
          new MtLoRoadTripChunk()));
  
  ILoRoadTripChunk shortTripChunksReversed = new ConsLoRoadTripChunk(
      new RoadTripChunk("short2",
          new ConsLoDirection(new Direction("Destination on right", 5), new MtLoDirection())),
      new ConsLoRoadTripChunk(
          new RoadTripChunk("short1", new ConsLoDirection(new Direction("Switch with short2", 15),
              new MtLoDirection())), new MtLoRoadTripChunk()));

  
  // tests for reverseDirections method in ILoDirection
  boolean testReverseDirections(Tester t) {
    return t.checkExpect(this.hazelHenryDirections.reverseDirections(this.mtDirections),
        this.hazelHenryDirectionsReversed);
  }
  
  // tests for isEmpty method in ILoDirection
  boolean testLoDirectionIsEmpty(Tester t) {
    return t.checkExpect(this.hazelHenryDirections.isEmpty(), false)
        && t.checkExpect(this.mtDirections.isEmpty(), true);
  }
  
  // test for removeLastDirection method in ILoDirection
  boolean testRemoveLastDirection(Tester t) {
    return t.checkExpect(this.hazelHenryDirections.removeLastDirection(), 
        this.hazelHenryDirectionsRemoveLast);
  }

  
  // tests for reverseChunks method in ILoRoadTripChunk
  boolean testReverseChunks(Tester t) {
    return t.checkExpect(this.shortTripChunks.reverseChunks(this.mtLoRoadTripChunk),
        this.shortTripChunksReversed)
        && t.checkExpect(this.shortRoadTripChunks.reverseChunks(this.mtLoRoadTripChunk), 
        this.shortRoadTripChunksReversed);
  }
  
  // tests for isEmpty method in ILoRoadTripChunk
  boolean testIsEmpty(Tester t) {
    return t.checkExpect(this.hazelHenryRoadTripChunks.isEmpty(), false)
        && t.checkExpect(this.mtLoRoadTripChunk.isEmpty(), true);
  }
  
  // tests for splitNewChunkDirection method in Direction class
  boolean testSplitNewChunkDirection(Tester t) {
    return t.checkExpect(new Direction("20", 20).splitNewChunkDirection(15, 5),
        new Direction("20", 10))
        && t.checkExpect(new Direction("50", 50).splitNewChunkDirection(25, 0),
            new Direction("50", 25))
        && t.checkExpect(new Direction("100", 100).splitNewChunkDirection(50, 10),
            new Direction("100", 60))
        && t.checkExpect(new Direction("10", 10).splitNewChunkDirection(5, 1),
            new Direction("10", 6))
        && t.checkExpect(new Direction("20", 20).splitNewChunkDirection(20, 18),
            new Direction("20", 18))
        && t.checkExpect(new Direction("60", 60).splitNewChunkDirection(30, 5),
            new Direction("60", 35));
  } 

  // tests for addDirectionMiles method in Direction class
  boolean testAddDirectionMiles(Tester t) {
    return t.checkExpect((new Direction("20", 20).addDirectionMiles(0)), 20)
        && t.checkExpect((new Direction("20", 20).addDirectionMiles(1)), 21)
        && t.checkExpect((new Direction("20", 20).addDirectionMiles(20)), 40)
        && t.checkExpect((new Direction("20", 0).addDirectionMiles(20)), 20);
  }
  
  // tests for milesOfThisDirection method in Direction class
  boolean testMilesOfThisDirection(Tester t) {
    return t.checkExpect(new Direction("100", 100).milesOfThisDirection(), 100)
        && t.checkExpect(new Direction("98", 98).milesOfThisDirection(), 98)
        && t.checkExpect(new Direction("24", 24).milesOfThisDirection(), 24)
        && t.checkExpect(new Direction("1", 1).milesOfThisDirection(), 1)
        && t.checkExpect(new Direction("0", 0).milesOfThisDirection(), 0);
  }
  
  // tests for switchInThisDirection method in Direction class
  boolean testSwitchInThisDirection(Tester t) {
    return t.checkExpect((new Direction("20", 20).switchInThisDirection(15, 0)), -1)
        && t.checkExpect((new Direction("20", 20).switchInThisDirection(20, 0)), 0)
        && t.checkExpect((new Direction("20", 20).switchInThisDirection(25, 0)), 1)
        && t.checkExpect((new Direction("20", 20).switchInThisDirection(21, 0)), 1)
        && t.checkExpect((new Direction("20", 20).switchInThisDirection(21, 1)), 0)
        && t.checkExpect((new Direction("20", 20).switchInThisDirection(21, 2)), -1)
        && t.checkExpect((new Direction("20", 20).switchInThisDirection(25, 5)), 0)
        && t.checkExpect((new Direction("20", 20).switchInThisDirection(25, 4)), 1)
        && t.checkExpect((new Direction("20", 20).switchInThisDirection(20, 5)), -1);
  }
  
  // test for splitUpTrip in RoadTrip class
  boolean testSplitUpTrip(Tester t) {
    return t.checkExpect(this.hazelHenryRoadTrip.splitUpTrip(15), this.hazelHenryRoadTripChunks);
  }
   
  // test for removeLastSwitchHelper in RoadTripChunk class
  boolean testRemoveLastSwitchHelper(Tester t) {
    return t.checkExpect(this.chunkOne.removeLastSwitchHelper(), this.chunkRemoveLast);
  }
}
