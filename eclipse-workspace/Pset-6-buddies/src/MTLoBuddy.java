
// represents an empty list of Person's buddies
class MTLoBuddy implements ILoBuddy {
  MTLoBuddy() {
  }

  // returns if that person is in this empty list of buddies
  public boolean personInList(Person that) {
    return false;
  }

  // counts the number of shared buddies in this empty list
  public int countBuddiesHelper(Person that) {
    return 0;
  }

  // determines if that person is a buddy to anyone in this empty list
  public boolean friendOfFriend(Person that, ILoBuddy visited) {
    return false;
  }

  // visits all unvisited buddies in this empty list
  public ILoBuddy visitUnvisited(ILoBuddy visited) {
    return visited;
  }

  // returns the length of this empty list
  public int length() {
    return 0;
  }

  // returns the max likelihood that a message could be conveyed to
  // that person through this empty list of buddies
  public double checkLikelihoods(double diction, double currentScore, Person that,
      ILoBuddy visited) {
    return 0;
  }

}
