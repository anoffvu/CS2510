// represents a list of Person's buddies
class ConsLoBuddy implements ILoBuddy {

  Person first;
  ILoBuddy rest;

  ConsLoBuddy(Person first, ILoBuddy rest) {
    this.first = first;
    this.rest = rest;
  }

  // returns true if that person is in this list of buddies
  public boolean personInList(Person that) {
    return this.first.samePerson(that) || this.rest.personInList(that);
  }

  // counts the number of common buddies in this non empty list of buddies
  public int countBuddiesHelper(Person that) {
    if (that.hasDirectBuddy(this.first)) {
      // why did this fail when I did (this.first.hasDirectBuddy(that))
      return 1 + this.rest.countBuddiesHelper(that);
    }
    else {
      return this.rest.countBuddiesHelper(that);
    }
  }

  // determines if that person is an extended buddy to anyone in this list of buddies
  public boolean friendOfFriend(Person that, ILoBuddy visited) {
    if (!visited.personInList(this.first)) {
      return this.first.hasDirectBuddy(that)
          || this.first.hasExtendedBuddyHelper(that, new ConsLoBuddy(this.first, visited))
          || this.rest.friendOfFriend(that, new ConsLoBuddy(this.first, visited));
    }
    else {
      return this.rest.friendOfFriend(that, visited);
    }
  }

  // counts the uncounted buddies in this network of everyone connected to these buddies
  // counting the buddies of this list
  // acc: visited buddies so far
  public ILoBuddy visitUnvisited(ILoBuddy visited) {
    if (!visited.personInList(this.first)) {
      return this.rest
          .visitUnvisited(this.first.visitNetwork(new ConsLoBuddy(this.first, visited)));
    }
    else {
      return this.rest.visitUnvisited(visited);
    }
  }

  // counts the size of this non empty list of buddies
  public int length() {
    return 1 + this.rest.length();
  }

  // returns the max likelihood that a message could be conveyed to
  // that person through this non empty list of buddies
  public double maxLikelihood(Person startPerson, Person that, ILoBuddy visited) {
    if (visited.personInList(this.first)) {
      return this.rest.maxLikelihood(startPerson, that, visited);
    }
    else {
      return Math.max(
          startPerson.calcScore(this.first)
              * this.first.maxLikelihoodAcc(that, new ConsLoBuddy(this.first, visited)),
          this.rest.maxLikelihood(startPerson, that, new ConsLoBuddy(this.first, visited)));
    }
  }
}
