// represents a Person with a user name and a list of buddies
class Person {

  String username;
  ILoBuddy buddies;
  double diction;
  double hearing;

  Person(String username) {
    this.username = username;
    this.buddies = new MTLoBuddy();
    this.diction = 0.0;
    this.hearing = 0.0;
  }

  Person(String username, double diction, double hearing) {
    this.username = username;
    this.buddies = new MTLoBuddy();
    if (0.0 <= diction && diction <= 1.0) {
      this.diction = diction;
    }
    else {
      throw new IllegalArgumentException("Diction should be a value between 0 and 1.");
    }
    if (0.0 <= hearing && hearing <= 1.0) {
      this.hearing = hearing;
    }
    else {
      throw new IllegalArgumentException("Hearing should be a value between 0 and 1.");
    }
  }

  // returns true if this Person has that as a direct buddy
  boolean hasDirectBuddy(Person that) {
    return buddies.personInList(that);
  }

  // returns the number of people who will show up at the party
  // given by this person
  int partyCount() {
    return this.visitNetwork(new ConsLoBuddy(this, new MTLoBuddy())).length();
  }

  // visits the network of this person
  // acc: buddies visited so far
  public ILoBuddy visitNetwork(ILoBuddy visited) {
    return this.buddies.visitUnvisited(visited);
  }

  // returns the number of people that are direct buddies
  // of both this and that person
  int countCommonBuddies(Person that) {
    return this.buddies.countBuddiesHelper(that);
  }

  // will the given person be invited to a party
  // organized by this person?
  boolean hasExtendedBuddy(Person that) {
    return this.hasDirectBuddy(that) || this.hasExtendedBuddyHelper(that, new MTLoBuddy());
    //
  }

  // searches the buddies to see if that person is a friend of a friend etc.
  // acc: the people visited so far
  public boolean hasExtendedBuddyHelper(Person that, ILoBuddy visited) {
    return this.buddies.friendOfFriend(that, visited);
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

  // determines the maximum likelihood that
  // this person can correctly convey a
  // message to the given person
  public double maxLikelihood(Person that) {
    return this.maxLikelihoodAcc(that, new ConsLoBuddy(this, new MTLoBuddy()));

  }

  // determines the maximum likelihood that
  // this person can correctly convey a
  // message to the given person
  // ACC: people we have visited so far
  public double maxLikelihoodAcc(Person that, ILoBuddy visited) {
    if (this.samePerson(that)) {
      return 1.0;
    }
    else if (this.hasDirectBuddy(that)) {
      return this.calcScore(that);
    }
    else if (this.hasExtendedBuddy(that)) {
      return this.buddies.maxLikelihood(this, that, visited);
    }
    else {
      return 0.0;
    }
  }

  // calculates the score of passing on a message to that person
  public double calcScore(Person that) {
    return this.diction * that.hearing;
  }
}
