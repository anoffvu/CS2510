import java.util.ArrayList;
import java.util.Arrays;

import tester.Tester;

/**
 * 
 * JSON is a way of storing tree-shaped data, much like XML.
 * You have four tasks:
 * 1. Create the JSONObject class. The JSONObject class
 * is a JSON and contains a list of JSONPairs.
 * A JSONPair consists of a key, which is a String,
 * and a value, which is a JSON. For a Fundies 1
 * style data definition, see:
 * https://course.ccs.neu.edu/cs2500f17/lab8.html
 * Beware the data definition in this link uses Symbols,
 * which Java does not have; as said above, use Strings instead.
 * 
 * 2. Create the following example in the ExamplesJSON class
 * and name it myBigExample, and be sure to use JSONNull
 * as opposed to the Java null (see the classes below).
 * 
 * {
 * "zero": 0,
 * "junk": [
 * "",
 * 1,
 * "cat",
 * {
 * "foo": null
 * }
 * ],
 * "stuff": {
 * "hello": true,
 * "goodbye": "cat"
 * }
 * }
 * 3. Design a method containsAllDefaults which determines if a JSON
 * contains *all* four "default values." These are false,
 * the empty string, 0, and null (again, JSONNull, not Java null).
 * Note that the keys in a JSONPair do not count as value contained
 * within the JSON. For brevity's sake, you do not have to write
 * purpose statements. Testing requirements are described in task 4.
 *
 * 4. Test containsAllDefaults on this object. Let's say
 * myBigExample.containsAllDefaults() equals b.
 * Write a test on another JSON object where containsAllDefaults
 * would return !b. See the code in testContainsDefaults if
 * this is confusing. For time's sake, you do not have to test helpers,
 * but are welcome to.
 * 
 * Some differences between real-world JSON and this file:
 * In real-world JSON, numbers don't have to be integers.
 * Also, in real-world JSON, the keys in a JSONObject
 * must be unique. We omit both of these for simplicity.
 * 
 * Best of luck!
 */

abstract class JSON {

  public boolean isAtomic() {
    return false;
  }

  // checks if this JSON contains all defaults
  public boolean containsAllDefaults() {
    // index maps to different tests passing:
    // explore the whole thing, accumulate if you've found all 4 of these instances
    // if atomic, check if it passes any of our tests, update outer variable
    // else, iterate through
    // if array: continue until MTLoJSON
    // if pair, check key
    // recursively call until natural termination and run the final check
    ArrayList<Boolean> tests = new ArrayList<Boolean>(Arrays.asList(false, false, false, false));

    // had a really bad Algo assignment today :(
    // ran out of time for this :(

    // final check if all test cases pass
    boolean ret = true;
    for (boolean b : tests) {
      if (!b) {
        ret = false;
      }
    }
    return ret;
  }
}

abstract class JSONAtom extends JSON {
  public boolean isAtomic() {
    return true;
  }

}

class JSONBool extends JSONAtom {

  Boolean bool;

  JSONBool(Boolean bool) {
    this.bool = bool;
  }
}

class JSONString extends JSONAtom {

  String str;

  JSONString(String str) {
    this.str = str;
  }
}

class JSONNumber extends JSONAtom {

  Integer number;

  JSONNumber(Integer number) {
    this.number = number;
  }
}

class JSONNull extends JSONAtom {
}

class JSONArray extends JSON {

  ILoJSON array;

  JSONArray(ILoJSON array) {
    this.array = array;
  }
}

// a package of JSON information, stored key-value format
class JSONPair extends JSON {
  String key;
  JSON val;

  JSONPair(String key, JSON val) {
    this.key = key;
    this.val = val;
  }
}

interface ILoJSON {
}

class MTLoJSON implements ILoJSON {
}

class ConsLoJSON implements ILoJSON {

  JSON first;
  ILoJSON rest;

  ConsLoJSON(JSON first, ILoJSON rest) {
    this.first = first;
    this.rest = rest;
  }
}

// a list of JSON pairs
class JSONObject extends JSON {
  ILoJSON pairs;

  JSONObject(ILoJSON pairs) {
    this.pairs = pairs;
  }
}

class ExamplesJSON {
  JSONBool falseAtom = new JSONBool(false);
  JSONString emptyStringAtom = new JSONString("");
  JSONNumber zeroAtom = new JSONNumber(0);
  JSONNull nullAtom = new JSONNull();
  
  JSONArray oneDefaultAtoms = new JSONArray(
      new ConsLoJSON(this.falseAtom, new MTLoJSON()));
  JSONArray twoDefaultAtoms = new JSONArray(
      new ConsLoJSON(this.falseAtom, 
          new ConsLoJSON(this.emptyStringAtom, new MTLoJSON())));
  JSONArray threeDefaultAtoms = new JSONArray(
      new ConsLoJSON(this.falseAtom, 
          new ConsLoJSON(this.emptyStringAtom, 
              new ConsLoJSON(this.zeroAtom, new MTLoJSON()))));
  JSONArray fourDefaultAtoms = new JSONArray(
      new ConsLoJSON(this.falseAtom, 
          new ConsLoJSON(this.emptyStringAtom, 
              new ConsLoJSON(this.zeroAtom, 
                  new ConsLoJSON(this.nullAtom, new MTLoJSON())))));

  JSONPair zero = new JSONPair("zero", new JSONNumber(0));
  JSONPair junk = new JSONPair("junk",
      new JSONArray(
          new ConsLoJSON(new JSONString(""), 
              new ConsLoJSON(new JSONNumber(1), 
                  new ConsLoJSON(new JSONString("cat"),
                      new ConsLoJSON(
                          new JSONObject(
                              new ConsLoJSON(new JSONPair("foo", new JSONNull()), new MTLoJSON())),
                          new MTLoJSON()))))));
  // that was fun

  JSONPair hello = new JSONPair("hello", new JSONBool(true));
  JSONPair goodbye = new JSONPair("goodbye", new JSONString("cat"));
  JSONPair stuff = new JSONPair("stuff",
      new JSONObject(new ConsLoJSON(this.hello, new ConsLoJSON(this.goodbye, new MTLoJSON()))));
  JSONObject myBigExample = new JSONObject(new ConsLoJSON(this.zero, new ConsLoJSON(this.junk,
      new ConsLoJSON(this.stuff, new ConsLoJSON(this.stuff, new MTLoJSON())))));
  
  JSONObject myBiggerExample = new JSONObject(
      new ConsLoJSON(new JSONPair("false", new JSONBool(false)),
          new ConsLoJSON(this.zero, 
              new ConsLoJSON(this.junk,
                  new ConsLoJSON(this.stuff, 
                      new ConsLoJSON(this.stuff, new MTLoJSON()))))));
  
  

  boolean testContainsDefaults(Tester t) {
    return t.checkExpect(this.myBigExample.containsAllDefaults(), false)
        && t.checkExpect(this.zero.containsAllDefaults(), false)
        && t.checkExpect(new JSONString("foo").containsAllDefaults(), false)
        && t.checkExpect(new JSONNull().containsAllDefaults(), false)
        && t.checkExpect(this.junk.containsAllDefaults(), false)
        && t.checkExpect(new JSONNull().containsAllDefaults(), false)
        && t.checkExpect(this.myBiggerExample.containsAllDefaults(), true)
        && t.checkExpect(this.oneDefaultAtoms.containsAllDefaults(), false)
        && t.checkExpect(this.twoDefaultAtoms.containsAllDefaults(), false)
        && t.checkExpect(this.threeDefaultAtoms.containsAllDefaults(), false)
        && t.checkExpect(this.fourDefaultAtoms.containsAllDefaults(), true);
  }
}