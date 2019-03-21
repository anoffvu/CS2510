import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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
  int ALPHABET_SIZE = alphabet.size();

  // moves the ascii code to lowercase letters
  int CHAR_TO_LOWER = 97;

  ArrayList<Character> code = new ArrayList<Character>(ALPHABET_SIZE);

  // A random number generator
  Random rand = new Random();

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
  String encode(String source) {
    ArrayList<Character> sourceChars = stringToAList(source);
    int stringSize = sourceChars.size();
    for (int i = 0; i < stringSize; i++) {
      sourceChars.set(i, code.get(sourceChars.get(i) - CHAR_TO_LOWER));
    }
    return AListToString(sourceChars);
  }

  // produce a decoded String from the given String
  String decode(String code) {
    ArrayList<Character> codeChars = stringToAList(code);
    int codeLength = codeChars.size();
    for (int i = 0; i < code.length(); i++) {
      codeChars.set(i, (char) (code.indexOf(codeChars.get(i)) + CHAR_TO_LOWER));
    }
    return AListToString(codeChars);
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
  String AListToString(ArrayList<Character> chars) {
    String builtString = "";
    for (Character c : chars) {
      builtString += c;
    }
    return builtString;
  }
}

class ExamplesPerm {

  PermutationCode p1 = new PermutationCode();
  PermutationCode p2 = new PermutationCode(p1.alphabet);
  PermutationCode p3 = new PermutationCode(new ArrayList<Character>(Arrays.asList('b', 'a', 'c')));
  // ^ it would probably be better to cast here,
  // but I don't want to get unnecessary cheese I mean points off

  // tests initEncoder, stringToCharAL, charALToString
  void testHelpers(Tester t) {
    ArrayList<Character> generated = p1.initEncoder();
    t.checkExpect(generated.size(), 26);
    t.checkExpect(generated.contains('a'), true);
    t.checkExpect(generated.contains('b'), true);
    t.checkExpect(generated.contains('c'), true);
    t.checkExpect(generated.contains('d'), true);
    t.checkExpect(generated.contains('e'), true);
    t.checkExpect(generated.contains('f'), true);
    t.checkExpect(generated.contains('g'), true);
    t.checkExpect(generated.contains('h'), true);
    t.checkExpect(generated.contains('i'), true);
    t.checkExpect(generated.contains('j'), true);
    t.checkExpect(generated.contains('k'), true);
    t.checkExpect(generated.contains('l'), true);
    t.checkExpect(generated.contains('m'), true);
    t.checkExpect(generated.contains('n'), true);
    t.checkExpect(generated.contains('o'), true);
    t.checkExpect(generated.contains('p'), true);
    t.checkExpect(generated.contains('q'), true);
    t.checkExpect(generated.contains('r'), true);
    t.checkExpect(generated.contains('s'), true);
    t.checkExpect(generated.contains('t'), true);
    t.checkExpect(generated.contains('u'), true);
    t.checkExpect(generated.contains('v'), true);
    t.checkExpect(generated.contains('w'), true);
    t.checkExpect(generated.contains('x'), true);
    t.checkExpect(generated.contains('y'), true);
    t.checkExpect(generated.contains('z'), true);
    // this is to test if it is scrambled. Technically it is possible to have a
    // code that is exactly the same as the alphabet, but it is incredibly unlikely
    t.checkExpect(p1.encode("abcdefghijklmnopqrstuvwxyz").equals("abcdefghijklmnopqrstuvwxyz"),
        false);

    t.checkExpect(p2.stringToAList("test"),
        new ArrayList<Character>(Arrays.asList('t', 'e', 's', 't')));
    t.checkExpect(p2.stringToAList(""), new ArrayList<Character>());

    t.checkExpect(p2.AListToString(new ArrayList<Character>(Arrays.asList('t', 'e', 's', 't'))),
        "test");
    t.checkExpect(p2.AListToString(new ArrayList<Character>()), "");
  }

  // tests encode
  void testEncode(Tester t) {
    t.checkExpect(p2.encode("test"), "test");
    t.checkExpect(p3.encode("cba"), "cab");
    t.checkExpect(p1.encode(""), "");
  }

  // tests decode
  void testDecode(Tester t) {
    t.checkExpect(p2.decode("cheese"), "cheese");
    t.checkExpect(p3.decode("cab"), "cba");
    t.checkExpect(p2.decode(""), "");
  }
}