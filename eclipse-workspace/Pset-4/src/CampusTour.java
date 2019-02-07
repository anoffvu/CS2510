import tester.Tester;

// a campus tour
class CampusTour {
  int startTime; // minutes from midnight
  ITourLocation startingLocation;

  CampusTour(int startTime, ITourLocation startingLocation) {
    this.startTime = startTime;
    this.startingLocation = startingLocation;
  }

  // is this tour the same tour as the given one?
  boolean sameTour(CampusTour other) {
    return;
  }
}

// a spot on the tour
interface ITourLocation {
}

abstract class ATourLocation implements ITourLocation {
  String speech; // the speech to give at this spot on the tour

  ATourLocation(String speech) {
    this.speech = speech;
  }
}

// the end of the tour
class TourEnd extends ATourLocation {
  ICampusLocation location;

  TourEnd(String speech, ICampusLocation location) {
    super(speech);
    this.location = location;
  }
}

// a mandatory spot on the tour with the next place to go
class Mandatory extends ATourLocation {
  ICampusLocation location;
  ITourLocation next;

  Mandatory(String speech, ICampusLocation location, ITourLocation next) {
    super(speech);
    this.location = location;
    this.next = next;
  }
}

// up to the tour guide where to go next
class BranchingTour extends ATourLocation {
  ITourLocation option1;
  ITourLocation option2;

  BranchingTour(String speech, ITourLocation option1, ITourLocation option2) {
    super(speech);
    this.option1 = option1;
    this.option2 = option2;
  }
}

interface ICampusLocation {

  // is this Building the same as that Building?
  boolean sameBuilding(Building that);

  // is this Quad the same as the that Quad?
  boolean sameQuad(Quad that);

  // is this CampusLocation the same as that CampusLocation?
  boolean sameCampusLocation(ICampusLocation that);

}

class Building implements ICampusLocation {
  String name;
  Address address;

  Building(String name, Address address) {
    this.name = name;
    this.address = address;
  }

  // is this Building the same as that Building?
  public boolean sameBuilding(Building that) {
    return this.name.equals(that.name) && this.address.equals(that.address);
  }

  // is this Quad the same as the that Quad?
  public boolean sameQuad(Quad that) {
    return false;
  }

  // is this CampusLocation the same as that CampusLocation?
  public boolean sameCampusLocation(ICampusLocation that) {
    return that.sameBuilding(this);
  }
}

class Address {
  String street;
  int number;

  Address(String street, int number) {
    this.number = number;
    this.street = street;
  }
}

class Quad implements ICampusLocation {
  String name;
  ILoCampusLocation surroundings; // in clockwise order, starting north

  Quad(String name, ILoCampusLocation surroundings) {
    this.name = name;
    this.surroundings = surroundings;
  }

  // is this Building the same as that Building?
  public boolean sameBuilding(Building that) {
    return false;
  }

  // is this Quad the same as the that Quad?
  public boolean sameQuad(Quad that) {
    return this.name.equals(that.name)
        && this.surroundings.sameILoCampusLocation(that.surroundings);
  }

  // is this CampusLocation the same as that CampusLocation?
  public boolean sameCampusLocation(ICampusLocation that) {
    return that.sameQuad(this);
  }

}

interface ILoCampusLocation {

  // is this ILoCampusLocation the same as that ILoCampusLocation?
  boolean sameILoCampusLocation(ILoCampusLocation that);

  // is this ILoCampusLocation the same as that MtLoCampusLocation?
  boolean sameMtLoCampusLocation(MtLoCampusLocation that);

  // is this ILoCampusLocation the same as that ConsLoCampusLocation?
  boolean sameConsLoCampusLocation(ConsLoCampusLocation that);

}

class MtLoCampusLocation implements ILoCampusLocation {

  // is this ILoCampusLocation the same as that ILoCampusLocation?
  public boolean sameILoCampusLocation(ILoCampusLocation that) {
    return that.sameMtLoCampusLocation(this);
  }

  // is this ILoCampusLocation the same as that MtLoCampusLocation?
  public boolean sameMtLoCampusLocation(MtLoCampusLocation that) {
    return true;
  }

  // is this ILoCampusLocation the same as that ConsLoCampusLocation?
  public boolean sameConsLoCampusLocation(ConsLoCampusLocation that) {
    return false;
  }

}

class ConsLoCampusLocation implements ILoCampusLocation {
  ICampusLocation first;
  ILoCampusLocation rest;

  ConsLoCampusLocation(ICampusLocation first, ILoCampusLocation rest) {
    this.first = first;
    this.rest = rest;
  }

  // is this ILoCampusLocation the same as that ILoCampusLocation?
  public boolean sameILoCampusLocation(ILoCampusLocation that) {
    return that.sameConsLoCampusLocation(this);
  }

  // is this ILoCampusLocation the same as that MtLoCampusLocation?
  public boolean sameMtLoCampusLocation(MtLoCampusLocation that) {
    return false;
  }

  // is this ILoCampusLocation the same as that ConsLoCampusLocation?
  public boolean sameConsLoCampusLocation(ConsLoCampusLocation that) {
    return this.first.sameCampusLocation(that.first) && this.rest.sameILoCampusLocation(that.rest);
  }

}

class ExamplesCampus {
  // TODO: add examples + tests
  // check nested branchings with order switched, deeper trees, deeper trees that are the
  // same
  // deeper trees that are almost the same
  Address speareAdd = new Address("10 Speare Pl", 10);
  Address steastAdd = new Address("11 Speare Pl", 11);
  Address stwestAdd = new Address("12 Speare Pl", 12);
  Address marinoAdd = new Address("259 Huntington Ave", 259);
  Address dodgeAdd = new Address("324 Huntington Ave", 324);
  Address ellAdd = new Address("346 Huntington Ave", 346);
  Address richardsAdd = new Address("360 Huntington Ave", 360);

  Building speareHall = new Building("Speare Hall", speareAdd);
  ICampusLocation stetsonEast = new Building("Stetson East", steastAdd);
  Building stetsonWest = new Building("Stetson West", stwestAdd);
  ICampusLocation marinoCenter = new Building("Marino Center", marinoAdd);
  ICampusLocation dodgeHall = new Building("Dodge Hall", dodgeAdd);
  ICampusLocation ellHall = new Building("Ell Hall", ellAdd);
  ICampusLocation richardsHall = new Building("Richards Hall", richardsAdd);

  ILoCampusLocation krentzmanLocs = new ConsLoCampusLocation(this.dodgeHall,
      new ConsLoCampusLocation(this.ellHall,
          new ConsLoCampusLocation(this.richardsHall, new MtLoCampusLocation())));
  Quad krentzmanQuad = new Quad("Krentzman Quad", krentzmanLocs);

  ILoCampusLocation krentzmanLocs2 = new ConsLoCampusLocation(this.richardsHall,
      new ConsLoCampusLocation(this.ellHall,
          new ConsLoCampusLocation(this.dodgeHall, new MtLoCampusLocation())));
  Quad krentzmanQuad2 = new Quad("Krentzman Quad", krentzmanLocs2);

  CampusTour tourOne = new CampusTour(600, this.dodgeTour);
  ITourLocation dodgeTour = new Mandatory("", this.dodgeHall, this.ellOrRichards);
  ITourLocation ellOrRichards = new BranchingTour("", this.ellTour, this.richardsTour);
  ITourLocation richardsTour = new TourEnd("", this.richardsHall);
  ITourLocation ellTour = new TourEnd("", this.ellHall);

  CampusTour tourTwo = new CampusTour(600, this.speareTour);
  ITourLocation speareTour = new Mandatory("aaa", this.speareHall, this.steastTour);
  ITourLocation steastTour = new Mandatory("a", this.stetsonEast, this.stwestOrMarino);
  ITourLocation stwestOrMarino = new BranchingTour("aa", this.stwestTour, this.marinoTour);
  ITourLocation stwestTour = new TourEnd("", this.stetsonWest);
  ITourLocation marinoTour = new TourEnd("", this.marinoCenter);

  boolean testsameQuad(Tester t) {
    return t.checkExpect(this.krentzmanQuad.sameQuad(this.krentzmanQuad2), false)
        && t.checkExpect(this.krentzmanQuad.sameQuad(this.krentzmanQuad), true);

  }

  boolean testsameBuilding(Tester t) {
    return t.checkExpect(this.speareHall.sameBuilding(this.speareHall), true)
        && t.checkExpect(this.speareHall.sameBuilding(this.stetsonWest), false);
  }

}
