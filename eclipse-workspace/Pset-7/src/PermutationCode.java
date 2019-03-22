import java.util.ArrayList;
import java.util.Arrays;

import tester.Tester;

/**
 * A class that defines a new permutation code, as well as methods for encoding
 * and decoding of the messages that use this code.
 */

public class PermutationCode {
  // The original list of characters to be encoded
  ArrayList<Character> alphabet = new ArrayList<Character>(
      Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
          'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));

  // size of the given alphabet
  public final int ALPHABET_SIZE = alphabet.size();

  // moves the ascii code to lowercase letters
  public static final int CHAR_TO_LOWER = 97;

  ArrayList<Character> code = new ArrayList<Character>(ALPHABET_SIZE);

  // A random number generator
  // Random rand = new Random();

  // Create a new instance of the encoder/decoder with a new permutation code
  PermutationCode() {
    this.code = this.initEncoder();
  }

  // Create a new instance of the encoder/decoder with the given code
  PermutationCode(ArrayList<Character> code) {
    this.code = code;
  }

  // Initialize the encoding permutation of the characters
  ArrayList<Character> initEncoder() {
    ArrayList<Character> encoded = new ArrayList<Character>(alphabet);
    for (int i = 0; i < ALPHABET_SIZE; i++) {
      char charHolder = encoded.get(i);
      int randIndex = (int) (Math.random() * ALPHABET_SIZE);
      encoded.set(i, encoded.get(randIndex));
      encoded.set(randIndex, charHolder);
    }
    return encoded;
  }

  // produce an encoded String from the given String
  String encode(String toEncode) {
    int toEncodeLength = toEncode.length();
    ArrayList<Character> toEncodeChars = stringToAList(toEncode);
    for (int i = 0; i < toEncodeLength; i++) {
      toEncodeChars.set(i, code.get(toEncodeChars.get(i) - CHAR_TO_LOWER));
    }
    return arrayListToString(toEncodeChars);
  }

  // produce a decoded String from the given String
  String decode(String toDecode) {
    int toDecodeLength = toDecode.length();
    ArrayList<Character> codeChars = stringToAList(toDecode);
    for (int i = 0; i < toDecodeLength; i++) {
      codeChars.set(i, (char) (code.indexOf(codeChars.get(i)) + CHAR_TO_LOWER));
    }
    return arrayListToString(codeChars);
  }

  // converts a string to an ArrayList of chars
  ArrayList<Character> stringToAList(String s) {
    int stringLength = s.length();
    ArrayList<Character> charAList = new ArrayList<Character>();
    for (int i = 0; i < stringLength; i++) {
      charAList.add(s.charAt(i));
    }
    return charAList;
  }

  // converts an ArrayList of chars to a string
  String arrayListToString(ArrayList<Character> chars) {
    String builtString = "";
    for (Character c : chars) {
      builtString += c;
    }
    return builtString;
  }
}

//"perm" stands for permasan cheese
class ExamplesPerm {

  // a random alphabet
  PermutationCode p1 = new PermutationCode();
  // just the normal alphabet
  PermutationCode p2 = new PermutationCode(p1.alphabet);
  // a 3 letter permutation code
  PermutationCode p3 = new PermutationCode(new ArrayList<Character>(Arrays.asList('d', 'e', 'f')));

  // tests initEncoder, stringToCharAL, charALToString
  void testHelpers(Tester t) {
    ArrayList<Character> dummy = p1.initEncoder();
    t.checkExpect(dummy.size(), 26);
    t.checkExpect(dummy.contains('a'), true);
    t.checkExpect(dummy.contains('b'), true);
    t.checkExpect(dummy.contains('c'), true);
    t.checkExpect(dummy.contains('d'), true);
    t.checkExpect(dummy.contains('e'), true);
    t.checkExpect(dummy.contains('f'), true);
    t.checkExpect(dummy.contains('g'), true);
    t.checkExpect(dummy.contains('h'), true);
    t.checkExpect(dummy.contains('i'), true);
    t.checkExpect(dummy.contains('j'), true);
    t.checkExpect(dummy.contains('k'), true);
    t.checkExpect(dummy.contains('l'), true);
    t.checkExpect(dummy.contains('m'), true);
    t.checkExpect(dummy.contains('n'), true);
    t.checkExpect(dummy.contains('o'), true);
    t.checkExpect(dummy.contains('p'), true);
    t.checkExpect(dummy.contains('q'), true);
    t.checkExpect(dummy.contains('r'), true);
    t.checkExpect(dummy.contains('s'), true);
    t.checkExpect(dummy.contains('t'), true);
    t.checkExpect(dummy.contains('u'), true);
    t.checkExpect(dummy.contains('v'), true);
    t.checkExpect(dummy.contains('w'), true);
    t.checkExpect(dummy.contains('x'), true);
    t.checkExpect(dummy.contains('y'), true);
    t.checkExpect(dummy.contains('z'), true);
    t.checkExpect(p1.encode("abcdefghijklmnopqrstuvwxyz").equals("abcdefghijklmnopqrstuvwxyz"),
        false);
  }

  // test for stringToAList
  void testStringToAList(Tester t) {
    t.checkExpect(p2.stringToAList("hello"),
        new ArrayList<Character>(Arrays.asList('h', 'e', 'l', 'l', 'o')));
    t.checkExpect(p2.stringToAList(""), new ArrayList<Character>());
  }

  // tests for arrayListToString
  void testArrayListToString(Tester t) {
    t.checkExpect(p2.arrayListToString(
        new ArrayList<Character>(Arrays.asList('h', 'i', 't', 'h', 'e', 'r', 'e'))), "hithere");
    t.checkExpect(p2.arrayListToString(new ArrayList<Character>()), "");
  }

  // tests for encode
  void testEncode(Tester t) {
    t.checkExpect(p2.encode("hello"), "hello");
    t.checkExpect(p3.encode("abc"), "def");
    t.checkExpect(p1.encode(""), "");
  }

  // tests for decode
  void testDecode(Tester t) {
    t.checkExpect(p2.decode("hello"), "hello");
    t.checkExpect(p3.decode("def"), "abc");
    t.checkExpect(p2.decode(""), "");
  }
}
