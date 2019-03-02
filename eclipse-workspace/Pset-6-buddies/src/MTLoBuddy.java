
// represents an empty list of Person's buddies
class MTLoBuddy implements ILoBuddy {
    MTLoBuddy() {}

  // returns if that person is in this empty list of buddies
  public boolean personInList(Person that) {
    return false;
  }

  // counts the number of shared buddies in this empty list
  public int countBuddiesHelper(Person that) {
    return 0;
  }

  // determines if that person is a buddy to anyone in this empty list
  public boolean friendOfFriend(Person that) {
    return false;
  }

}
