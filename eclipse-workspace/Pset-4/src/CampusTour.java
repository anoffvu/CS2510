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
    return this.startTime == other.startTime
        && this.startingLocation.sameTourLocation(other.startingLocation);
  }
}

// a spot on the tour
interface ITourLocation {

  // determines if this tourLocation is the same as that tourLocation
  boolean sameTourLocation(ITourLocation other);

  // determines if this ITourLocation is the same as that TourEnd
  boolean sameTourEnd(TourEnd other);

  // determines if this ITourLocation is the same as that Mandatory
  boolean sameMandatory(Mandatory other);

  // determines if this ITourLocation is the same as that BranchingTour
  boolean sameBranchingTour(BranchingTour other);
}

abstract class ATourLocation implements ITourLocation {
  String speech; // the speech to give at this spot on the tour

  ATourLocation(String speech) {
    this.speech = speech;
  }

  // determines if this TourLocation is the same as that tourEnd
  public boolean sameTourEnd(TourEnd other) {
    return false;
  }

  // determines if this TourLocation is the same as that Mandatory
  public boolean sameMandatory(Mandatory other) {
    return false;
  }

  // determines if this ITourLocation is the same as that BranchingTour
  public boolean sameBranchingTour(BranchingTour other) {
    return false;
  }

}

// the end of the tour
class TourEnd extends ATourLocation {
  ICampusLocation location;

  TourEnd(String speech, ICampusLocation location) {
    super(speech);
    this.location = location;
  }

  // determines if this TourEnd is the same as the other ITourLocation
  public boolean sameTourLocation(ITourLocation other) {
    return other.sameTourEnd(this);
  }

  // determines if this TourEnd is the same as that TourEnd
  public boolean sameTourEnd(TourEnd other) {
    return this.speech.equals(other.speech) && this.location.sameCampusLocation(other.location);
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

  // determines if this Mandatory is the same as that ITourLocation
  public boolean sameTourLocation(ITourLocation other) {
    return other.sameMandatory(this);
  }

  // determines if this TourLocation is the same as that Mandatory
  public boolean sameMandatory(Mandatory other) {
    return this.speech.equals(other.speech) && this.location.sameCampusLocation(other.location)
        && this.next.sameTourLocation(other.next);
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

  // determines if this BranchingTour is the same as that ITourLocation
  public boolean sameTourLocation(ITourLocation other) {
    return other.sameBranchingTour(this);
  }

  // determines if this BranchingTour is the same as that BranchingTour
  public boolean sameBranchingTour(BranchingTour other) {
    return this.speech.equals(other.speech) && ((this.option1.sameTourLocation(other.option1)
        && this.option2.sameTourLocation(other.option2))
        || (this.option1.sameTourLocation(other.option2)
            && this.option2.sameTourLocation(other.option1)));
  }
}

interface ICampusLocation {

  // is this Building the same as that Building?
  boolean sameBuilding(Building other);

  // is this Quad the same as the that Quad?
  boolean sameQuad(Quad other);

  // is this CampusLocation the same as that CampusLocation?
  boolean sameCampusLocation(ICampusLocation other);

}

class Building implements ICampusLocation {
  String name;
  Address address;

  Building(String name, Address address) {
    this.name = name;
    this.address = address;
  }

  // is this Building the same as that Building?
  public boolean sameBuilding(Building other) {
    return this.name.equals(other.name) && this.address.sameAddress(other.address);
  }

  // is this Building the same as the that Quad?
  public boolean sameQuad(Quad other) {
    return false;
  }

  // is this CampusLocation the same as that CampusLocation?
  public boolean sameCampusLocation(ICampusLocation other) {
    return other.sameBuilding(this);
  }
}

class Address {
  String street;
  int number;

  Address(String street, int number) {
    this.number = number;
    this.street = street;
  }

  // checks equality of Addresses
  public boolean sameAddress(Address other) {
    return this.street.equals(other.street) && this.number == other.number;
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
  public boolean sameBuilding(Building other) {
    return false;
  }

  // is this Quad the same as the that Quad?
  public boolean sameQuad(Quad other) {
    return this.name.equals(other.name)
        && this.surroundings.sameILoCampusLocation(other.surroundings);
  }

  // is this CampusLocation the same as that CampusLocation?
  public boolean sameCampusLocation(ICampusLocation other) {
    return other.sameQuad(this);
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

class ExamplesCampusTour {
  // TODO: add examples + tests
  // check nested branchings with order switched, deeper trees, deeper trees that are the
  // same
  // deeper trees that are almost the same

  Address speareAdd = new Address("Speare Pl", 10);
  Address steastAdd = new Address("Speare Pl", 11);
  Address stwestAdd = new Address("Speare Pl", 12);
  Address marinoAdd = new Address("Huntington Ave", 259);
  Address dodgeAdd = new Address("Huntington Ave", 324);
  Address ellAdd = new Address("Huntington Ave", 346);
  Address richardsAdd = new Address("Huntington Ave", 360);

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
  TourEnd stwestTour = new TourEnd("stwest tour end", this.stetsonWest);
  ITourLocation marinoTour = new TourEnd("marino tour end", this.marinoCenter);
  TourEnd tourEnd2 = new TourEnd("tour end 2", this.stetsonWest);

  // tests for sameTourEnd
  boolean testSameTourEnd(Tester t) {
    return t.checkExpect(this.stwestTour.sameTourEnd(this.stwestTour), true)
        && t.checkExpect(this.marinoTour.sameTourEnd(this.stwestTour), false)
        && t.checkExpect(this.stwestOrMarino.sameTourEnd(this.stwestTour), false)
        && t.checkExpect(this.steastTour.sameTourEnd(this.stwestTour), false);
  }

  // examples and tests for sameMandatory

  Mandatory man1 = new Mandatory("man1 speech", this.speareHall, stwestTour);
  Mandatory man2 = new Mandatory("man2 speech", this.speareHall, stwestTour);
  Mandatory man3 = new Mandatory("man1 speech", this.stetsonEast, stwestTour);
  Mandatory man4 = new Mandatory("man1 speech", this.speareHall, tourEnd2);

  boolean testSameMandatory(Tester t) {
    return t.checkExpect(this.man1.sameMandatory(this.man1), true)
        && t.checkExpect(this.man2.sameMandatory(this.man1), false)
        && t.checkExpect(this.man3.sameMandatory(this.man1), false)
        && t.checkExpect(this.man4.sameMandatory(this.man1), false)
        && t.checkExpect(this.tourEnd2.sameMandatory(this.man1), false)
        && t.checkExpect(this.stwestOrMarino.sameMandatory(this.man1), false);
  }

  // examples and tests for sameBranchingTour

  ITourLocation branchEnd = new TourEnd("Branch end 1", this.speareHall);
  ITourLocation branchEnd2 = new TourEnd("Branch end 2", this.speareHall);
  BranchingTour branching1 = new BranchingTour("branching 1", this.branchEnd, this.branchEnd);
  // changes names
  BranchingTour branching2 = new BranchingTour("branching 2", this.branchEnd, this.branchEnd);
  // switches options
  BranchingTour branching3 = new BranchingTour("branching 1", this.dodgeTour, this.speareTour);
  // different options
  BranchingTour branching4 = new BranchingTour("branching 4", this.branchEnd2, this.branchEnd2);

  boolean testSameBranchingTour(Tester t) {
    return t.checkExpect(this.branching1.sameBranchingTour(this.branching1), true)
        && t.checkExpect(this.branching2.sameBranchingTour(this.branching1), false)
        && t.checkExpect(this.branching3.sameBranchingTour(this.branching1), false)
        && t.checkExpect(this.branching4.sameBranchingTour(this.branching1), false)
        && t.checkExpect(this.tourEnd2.sameBranchingTour(this.branching1), false)
        && t.checkExpect(this.dodgeTour.sameBranchingTour(this.branching1), false);
  }

  // examples and tests for sameAddress
  Address address1 = new Address("address 1", 1);
  Address address2 = new Address("address 2", 1);
  Address address3 = new Address("address 1", 2);
  Address address4 = new Address("address 4", 4);

  boolean testSameAddress(Tester t) {
    return t.checkExpect(this.address1.sameAddress(this.address1), true)
        && t.checkExpect(this.address2.sameAddress(this.address1), false)
        && t.checkExpect(this.address3.sameAddress(this.address1), false)
        && t.checkExpect(this.address4.sameAddress(this.address1), false);

  }

  // tests for sameQuad
  boolean testSameQuad(Tester t) {
    return t.checkExpect(this.krentzmanQuad.sameQuad(this.krentzmanQuad2), false)
        && t.checkExpect(this.krentzmanQuad.sameQuad(this.krentzmanQuad), true);

  }

  // tests for sameBuilding
  boolean testSameBuilding(Tester t) {
    return t.checkExpect(this.speareHall.sameBuilding(this.speareHall), true)
        && t.checkExpect(this.speareHall.sameBuilding(this.stetsonWest), false);
  }

  // tests for sameCampusLocation
  ICampusLocation emptyQuad = new Quad("Empty Quad", new MtLoCampusLocation());

  boolean testSameCampusLocation(Tester t) {
    return t.checkExpect(this.marinoCenter.sameCampusLocation(this.marinoCenter), true)
        && t.checkExpect(this.marinoCenter.sameCampusLocation(this.dodgeHall), false)
        && t.checkExpect(this.marinoCenter.sameCampusLocation(this.emptyQuad), false);
  }

}
