import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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
    ArrayList<Character> encoded = new ArrayList<Character>(ALPHABET_SIZE);
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
    for (int i = 0; i < codeLength; i++) {
      codeChars.set(i, (char) (code.indexOf(codeChars.get(i)) + CHAR_TO_LOWER));
    }
    return AListToString(codeChars);
  }

  // converts a string to an ArrayList of chars
  ArrayList<Character> stringToAList(String s) {
    int sLength = s.length();
    ArrayList<Character> charAList = new ArrayList<Character>(sLength);
    for (int i = 0; i < sLength; i++) {
      charAList.set(i, s.charAt(i));
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
