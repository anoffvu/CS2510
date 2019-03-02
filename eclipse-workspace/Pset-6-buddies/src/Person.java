
// represents a Person with a user name and a list of buddies
class Person {

  String username;
  ILoBuddy buddies;

  Person(String username) {
    this.username = username;
    this.buddies = new MTLoBuddy();
  }

  // returns true if this Person has that as a direct buddy
  boolean hasDirectBuddy(Person that) {
    return buddies.personInList(that);
  }

  // returns the number of people who will show up at the party
  // given by this person
  int partyCount() {
    return 1;
  }

  // returns the number of people that are direct buddies
  // of both this and that person
  int countCommonBuddies(Person that) {
    return this.buddies.countBuddiesHelper(that);
  }

  // will the given person be invited to a party
  // organized by this person?
  // TODO: probably wanna modify all functions so that you keep a list of visited nodes
  boolean hasExtendedBuddy(Person that) {
    return this.hasDirectBuddy(that) || this.buddies.friendOfFriend(that);
  }

  // EFFECT:
  // Change this person's buddy list so that it includes the given person
  void addBuddy(Person buddy) {
    this.buddies = new ConsLoBuddy(buddy, this.buddies);
  }

  // determines if this person is the same as that person
  public boolean samePerson(Person that) {
    return this.username.equals(that.username);
  }
}
