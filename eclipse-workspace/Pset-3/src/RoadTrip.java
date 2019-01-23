// and predefined colors (Red, Green, Yellow, Blue, Black, White)

interface ILoDirection {

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
}

class ExamplesRoadTrip {
  ILoDirection HazelHenryDirections = new ConsLoDirection(
      new Direction("Make a left at Alberquerque", 13),
      new ConsLoDirection(new Direction("Make a right at the fork", 2),
          new ConsLoDirection(new Direction("Make a left at the next fork", 3),
              new ConsLoDirection(new Direction("Take the overpass", 45), new ConsLoDirection(
                  new Direction("Destination on left", 12), new MtLoDirection())))));
  RoadTrip HazelHenryRoadTrip = new RoadTrip("Hazel", "Henry", this.HazelHenryDirections);
  ILoDirection HazelHenrySplitDirections = new ConsLoDirection(
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
  RoadTrip HazelHenrySplitRoadTrip = new RoadTrip("Hazel", "Henry", this.HazelHenrySplitDirections);

}