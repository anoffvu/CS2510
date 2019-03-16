
// represents a list of Person's buddies
interface ILoBuddy {

  // returns true if this person is in this list of buddies
  boolean personInList(Person that);

  // counts the number of common buddies
  int countBuddiesHelper(Person that);

  // determines if that is a buddy to anyone in this list
  boolean friendOfFriend(Person that, ILoBuddy visited);

  // counts the number of uncounted
  ILoBuddy visitUnvisited(ILoBuddy visited);

  // grabs the size of this ILoBuddy
  int length();

  // returns the max likelihood that a message could be conveyed to
  // that person through this list of buddies
  double maxLikelihood(Person startPerson, Person that, ILoBuddy visited);

}
