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
    if (this.first.samePerson(that) || this.rest.personInList(that)) {
      return true;
    }
    else {
      return false;
    }
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

  // determines if that person is a buddy to anyone in this list of buddies
  public boolean friendOfFriend(Person that) {
    return this.first.hasDirectBuddy(that) || this.rest.friendOfFriend(that);
  }

}
