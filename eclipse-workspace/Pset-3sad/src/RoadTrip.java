interface ILoDirection {

  ILoRoadTripChunk splitUpTripAccumulator(String driver1, String driver2, int splitSize,
      int distanceTraveledInThisChunk, ILoDirection directionsOfThisChunk,
      ILoRoadTripChunk chunksSoFar);

  // ILoDirection firstChunkOfDirections(String switchDriver, int splitSize);

  // RoadTripChunk firstRoadTripChunk(String driver, int splitSize);

  // ILoDirection restChunkOfDirections(String switchDriver, int splitSize);
  ILoDirection reverseDirections(ILoDirection reversedSoFar);
}

//interface ILoLoDirection { }

interface ILoRoadTripChunk {

}

class ConsLoDirection implements ILoDirection {
  Direction first;
  ILoDirection rest;

  ConsLoDirection(Direction first, ILoDirection rest) {
    this.first = first;
    this.rest = rest;
  }

  public ILoRoadTripChunk splitUpTripAccumulator(String driver, String switchDriver, int splitSize,
      int distanceTraveledSoFar, ILoDirection directionsOfThisChunk, ILoRoadTripChunk chunksSoFar) {
    // if the switch will happen after this direction
    if (this.first.switchInThisDirection(splitSize) == 1) {
      return this.splitUpTripAccumulator(driver, switchDriver, splitSize,
          distanceTraveledSoFar + this.first.milesOfThisDirection(),
          new ConsLoDirection(this.first, directionsOfThisChunk), chunksSoFar);
    } // if the switch will happen at the end of this direction
    else if (this.first.switchInThisDirection(splitSize) == 0) {
      return this.splitUpTripAccumulator(switchDriver, driver, splitSize, 0, new MtLoDirection(),
          new ConsLoRoadTripChunk(new RoadTripChunk(driver,
              (new ConsLoDirection(new Direction("Switch with" + switchDriver, 0),
                  new ConsLoDirection(this.first, directionsOfThisChunk)))
                      .reverseDirections(new MtLoDirection())),
              chunksSoFar));
      // split the direction and call accumulator with 0 distance traveled, new
    } // if the switch will happen before this direction ends
    else {

    }
  }

  // reverses the order directions in a ConsLoDirection
  public ILoDirection reverseDirections(ILoDirection reversedSoFar) {
    return this.rest.reverseDirections(new ConsLoDirection(this.first, reversedSoFar));
  }

  /*
   * // grabs the first chunk of directions
   * public ILoDirection firstChunkOfDirections(String switchDriver, int splitSize) {
   * return splitDirections(switchDriver, splitSize).first;
   * }
   * 
   * // grabs the rest of the directions after the first chunk is taken out
   * public ILoDirection restChunkOfDirections(String switchDriver, int splitSize) {
   * return splitDirections(switchDriver, splitSize).rest;
   * }
   * 
   * private ILoLoDirection splitDirections(String switchDriver, int splitSize) {
   * return new ConsLoLoDirection(new ConsLoDirection(new Direction("test", 1), new
   * MtLoDirection()), new MtLoDirection());
   * }
   */
  /*
   * // returns the first RoadTripChunk of a RoadTrip
   * public RoadTripChunk firstRoadTripChunk(String driver, int splitSize) {
   * return firstRoadTripChunkHelper(driver, splitSize, 0);
   * }
   * 
   * // returns the first RoadTripChunk of a RoadTrip with an accumulator
   * // to track distance to go in this chunk
   * // acc: distance to go in this chunk
   * public RoadTripChunk firstRoadTripChunkHelper(String driver, int splitSize, int
   * distToGo) {
   * new RoadTripChunk(driver, firstChunkDirections(splitSize, distToGo));
   * }
   * 
   * // returns the first chunk of directions
   * private ILoDirection firstChunkDirections(int splitSize, int distToGo) {
   * if (this.first.splitVsDirectionMiles(splitSize) == -1) {
   * 
   * }
   * }
   */

}

class MtLoDirection implements ILoDirection {
  MtLoDirection() {
  }

  // splits up a roadTrip
  public ILoRoadTripChunk splitUpTripAccumulator(String driver, String switchDriver, int splitSize,
      int distanceTraveledSoFar, ILoDirection directionsOfThisChunk, ILoRoadTripChunk chunksSoFar) {
    return (new ConsLoRoadTripChunk(new RoadTripChunk(driver, directionsOfThisChunk), chunksSoFar).reverseChunks();
  }

  // reverses an empty list of directions
  public ILoDirection reverseDirections(ILoDirection reversedSoFar) {
    return reversedSoFar;
  }

  /*
   * //grabs the first chunk of directions from an empty list of directions
   * public ILoDirection firstChunkOfDirections(String switchDriver, int splitSize) {
   * return this;
   * }
   * 
   * // grabs the rest of the directions after the first chunk is taken out on an empty
   * // list of directions
   * public ILoDirection restChunkOfDirections(String switchDriver, int splitSize) {
   * return this;
   * }
   */

}

/*
 * class ConsLoLoDirection implements ILoLoDirection {
 * ILoDirection first;
 * ILoLoDirection rest;
 * 
 * ConsLoLoDirection(ILoDirection first, ILoLoDirection rest) {
 * this.first = first;
 * this.rest = rest;
 * }
 * }
 * 
 * class MtLoLoDirection implements ILoLoDirection {
 * MtLoLoDirection() {
 * }
 * }
 */

class ConsLoRoadTripChunk implements ILoRoadTripChunk {
  RoadTripChunk first;
  ILoRoadTripChunk rest;

  ConsLoRoadTripChunk(RoadTripChunk first, ILoRoadTripChunk rest) {
    this.first = first;
    this.rest = rest;
  }
}

class MtLoRoadTripChunk implements ILoRoadTripChunk {
  MtLoRoadTripChunk() {
  }
}

class Direction {
  String description;
  int miles;

  Direction(String description, int miles) {
    this.description = description;
    this.miles = miles;
  }

  public int milesOfThisDirection() {
    return miles;
  }

  // checks if the split should come this direction or not
  public int switchInThisDirection(int splitSize) {
    if (this.miles > splitSize) {
      return -1;
    }
    else if (this.miles == 0) {
      return 0;
    }
    else {
      return 1;
    }
    // return this.miles >= splitSize;
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

  ILoRoadTripChunk splitUpTrip(int splitSize) {
    return this.directions.splitUpTripAccumulator(this.driver1, this.driver2, splitSize, 0,
        new MtLoDirection(), new MtLoRoadTripChunk());

    /*
     * third attempt
     * return new ConsLoRoadTripChunk(
     * new RoadTripChunk(this.driver1,
     * this.directions.firstChunkOfDirections(this.driver2, splitSize)),
     * (new RoadTrip(driver2, driver1,
     * this.directions.restChunkOfDirections(this.driver2, splitSize)))
     * .splitUpTrip(splitSize));
     * 
     * second attempt
     * return new ILoRoadTripChunk(this.directions.firstRoadTripChunk(this.driver1,
     * splitSize),
     * this.directions.restRoadTrip(this.driver1, this.driver2, splitSize));
     * // RoadTripChunk firstChunk = someFunctionToGrabFirstDirectionsWithDriver,
     * // uses the first driver, and grabs the first chunk of directions
     * // first chunk will have to call a function or also hold miles left to limit
     * // RoadTrip restRoadTrip = will grab the rest of the directions and switch drivers
     * // this function will switch the drivers and grab the rest of directions
     * // you access directions first so that it delegates better and
     * 
     * // previous ideas
     * // new ILoRoadTripChunk(new RoadTripChunk(this.driver1,
     * // this.directions.firstDirectionsChunk(splitSize))
     * // , splitUpTrip new RoadTripChunk(restChunk(splitSize)));
     * // new ConsLoRoadTrip(new RoadTripChunk(this.driver1,
     * // this.directions.firstChunk(splitSize)),
     * }
     */
    /*
     * RoadTrip trimTripFirst(int splitSize) {
     * if (this.directions.biggerThanSplit()){
     * new RoadTripChunk (this.directions.first.description, )
     * }
     * }
     */
  }
}

class RoadTripChunk {
  String driver;
  ILoDirection directions;

  RoadTripChunk(String driver, ILoDirection directions) {
    this.driver = driver;
    this.directions = directions;
  }
}

class ExamplesRoadTrip {
  ILoDirection hazelHenryDirections = new ConsLoDirection(
      new Direction("Make a left at Alberquerque", 13),
      new ConsLoDirection(new Direction("Make a right at the fork", 2),
          new ConsLoDirection(new Direction("Make a left at the next fork", 3),
              new ConsLoDirection(new Direction("Take the overpass", 45), new ConsLoDirection(
                  new Direction("Destination on left", 12), new MtLoDirection())))));
  RoadTrip hazelHenryRoadTrip = new RoadTrip("Hazel", "Henry", this.hazelHenryDirections);
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

  // RoadTrip hazelHenrySplitRoadTrip = new RoadTrip("Hazel", "Henry",
  // this.hazelHenrySplitDirections);
  ILoDirection shortDirections = new ConsLoDirection(new Direction("Destination on right", 20),
      new MtLoDirection());
  RoadTrip shortTrip = new RoadTrip("short1", "short2", this.shortDirections);
  ILoRoadTripChunk shortTripChunks = 
      new ConsLoRoadTripChunk(
          new RoadTripChunk("short1", new ConsLoDirection(new Direction("Switch with short2", 15), new MtLoDirection())),
        new ConsLoRoadTripChunk(
              new RoadTripChunk("short2", new ConsLoDirection(
                  new Direction("Destination on right", 5), new MtLoDirection())),
              new MtLoRoadTripChunk()));

  boolean testSplitUpTrip(Tester t) {

  }
}

