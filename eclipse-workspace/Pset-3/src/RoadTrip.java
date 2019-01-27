interface ILoDirection {

}

interface ILoRoadTripChunk {

}

class ConsLoDirection implements ILoDirection {
  Direction first;
  ILoDirection rest;

  ConsLoDirection(Direction first, ILoDirection rest) {
    this.first = first;
    this.rest = rest;
  }
}

class MtLoDirection implements ILoDirection {
  MtLoDirection() {
  }
}

interface ILoObject {

}

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
    return new ILoRoadTripChunk(this.directions.firstRoadTripChunk(this.driver1, splitSize),
        this.directions.restRoadTrip(this.driver1, this.driver2, splitSize));
    // RoadTripChunk firstChunk = someFunctionToGrabFirstDirectionsWithDriver,
    // uses the first driver, and grabs the first chunk of directions
    // RoadTrip restRoadTrip = will grab the rest of the directions and switch drivers
    // this function will switch the drivers and grab the rest of directions
    // you access directions first so that it delegates better and

    // previous ideas
    // new ILoRoadTripChunk(new RoadTripChunk(this.driver1,
    // this.directions.firstDirectionsChunk(splitSize))
    // , splitUpTrip new RoadTripChunk(restChunk(splitSize)));
    // new ConsLoRoadTrip(new RoadTripChunk(this.driver1,
    // this.directions.firstChunk(splitSize)),
  }
  /*
   * RoadTrip trimTripFirst(int splitSize) {
   * if (this.directions.biggerThanSplit()){
   * new RoadTripChunk (this.directions.first.description, )
   * }
   * }
   */
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
  ILoDirection shortDirections = new ConsLoDirection(
      new Direction("Destination on right", 20), new MtLoDirection());
  RoadTrip shortTrip = new RoadTrip("short1", "short2", this.shortDirections);
  ILoRoadTripChunk shortTripChunks = 
      new ConsLoRoadTripChunk(
          new RoadTripChunk("short1", new ConsLoDirection(new Direction("Switch with short2", 15), new MtLoDirection())),
        new ConsLoRoadTripChunk(
            new RoadTripChunk("short2", new ConsLoDirection(new Direction("Destination on right", 5), new MtLoDirection()));
}
