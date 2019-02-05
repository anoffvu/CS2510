import tester.Tester;

interface ILoDirection {

  ILoRoadTripChunk directionsChunker(String driver1, String driver2);
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

  @Override
  public ILoRoadTripChunk directionsChunker(String driver1, String driver2) {
    return chunkSplitListOfDirections(driver1, driver2);
  }

  private ILoRoadTripChunk chunkSplitListOfDirections(String driver1, String driver2) {
    // TODO Auto-generated method stub
    return null;
  }

  public ILoDirection splitDirections(int splitSize) {
    return splitDirectionsAcc(splitSize, 0);
  }

  public ILoDirection splitDirectionsAcc(int splitSize, int distanceSoFar) {
    if (this.first.splitPosition == -1) {

    }
  }

}

class MtLoDirection implements ILoDirection {
  MtLoDirection() {
  }

  public ILoRoadTripChunk directionsChunker(String driver1, String driver2) {
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
    return this.directions.directionsChunker(driver1, driver2);
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
  ILoRoadTripChunk shortTripChunks = new ConsLoRoadTripChunk(
      new RoadTripChunk("short1",
          new ConsLoDirection(new Direction("Switch with short2", 15), new MtLoDirection())),
      new ConsLoRoadTripChunk(
          new RoadTripChunk("short2",
              new ConsLoDirection(new Direction("Destination on right", 5), new MtLoDirection())),
          new MtLoRoadTripChunk()));

  boolean testSplitUpTrip(Tester t) {
    // return t.checkExpect(this.hazelHenryRoadTrip.splitUpTrip(15),
    // this.hazelHenryRoadTripChunks);
    System.out.println("Road Trip (actual): \n");
    this.hazelHenryRoadTrip.splitUpTrip(15).printChunks();
    System.out.println("");
    System.out.println("Road Trip (expected): \n");
    this.hazelHenryRoadTripChunks.printChunks();

    return true;

  }

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

  boolean testAddDirectionMiles(Tester t) {
    return t.checkExpect((new Direction("20", 20).addDirectionMiles(0)), 20)
        && t.checkExpect((new Direction("20", 20).addDirectionMiles(1)), 21)
        && t.checkExpect((new Direction("20", 20).addDirectionMiles(20)), 40)
        && t.checkExpect((new Direction("20", 0).addDirectionMiles(20)), 20);
  }

}
