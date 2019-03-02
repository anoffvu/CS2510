
// represents a list of Person's buddies
interface ILoBuddy {

  // returns true if this person is in this list of buddies
  boolean personInList(Person that);

  // counts the number of common buddies
  int countBuddiesHelper(Person that);

  // determines if that is a buddy to anyone in this list
  boolean friendOfFriend(Person that);

}
