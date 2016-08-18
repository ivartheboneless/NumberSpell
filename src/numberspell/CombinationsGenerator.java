package numberspell;

import java.util.*;

public class CombinationsGenerator {
  /*
   *The following arrays represent keys 2-9 on a mobile keypad and their
   *corresponding letters.
   */
  private char[] mKeyTwo = {'a', 'b', 'c'};
  private char[] mKeyThree = {'d', 'e', 'f'};
  private char[] mKeyFour = {'g', 'h', 'i'};
  private char[] mKeyFive = {'j', 'k', 'l'};
  private char[] mKeySix = {'m', 'n', 'o'};
  private char[] mKeySeven = {'p', 'r', 's'};
  private char[] mKeyEight = {'t', 'u', 'v'};
  private char[] mKeyNine = {'w', 'y', 'z'};

  private final List<String> mPhonewordsGenerated;

  public CombinationsGenerator(String numberEntered) {
    char[] numberEnteredArray = numberEntered.toCharArray();
    int numOfDigitsEntered = numberEnteredArray.length;

    //the following generates a list of char arrays using the memeber key arrays
    List<char[]> listOfCharArrays = new ArrayList<>(numOfDigitsEntered);
    for(char number : numberEnteredArray) {
      if(number == '2') {
        listOfCharArrays.add(mKeyTwo);
      } else if(number == '3') {
        listOfCharArrays.add(mKeyThree);
      } else if(number == '4') {
        listOfCharArrays.add(mKeyFour);
      } else if(number == '5') {
        listOfCharArrays.add(mKeyFive);
      } else if(number == '6') {
        listOfCharArrays.add(mKeySix);
      } else if(number == '7') {
        listOfCharArrays.add(mKeySeven);
      } else if(number == '8') {
        listOfCharArrays.add(mKeyEight);
      } else if(number == '9') {
        listOfCharArrays.add(mKeyNine);
      } else {
        System.out.println("Invalid Entry. Please try again.");
        System.exit(0);
      }
    }

    //the following generates a list of numbers with base 3. 3^(digits entered)
    //numbers are generated.
    List<int[]> finalList = new ArrayList<>();
    int[] keys = new int[numOfDigitsEntered];
    int size = numOfDigitsEntered - 1;
    int combinationCounter = 0;
    while(true) {
      int[] keyTemp = new int[numOfDigitsEntered];
      System.arraycopy(keys, 0, keyTemp, 0, keys.length);
      finalList.add(keyTemp);
      combinationCounter++;
      if(combinationCounter == Math.pow(3, size + 1)) {
        break;
      }
      keys[size] += 1;
      int i = 0;
      while(keys[size - i] == 3) {
        keys[size - i] = 0;
        keys[size - (i + 1)] += 1;
        i++;
      }
    }

    //the following uses the integer Arrays in "finalList" to generate strings
    mPhonewordsGenerated = new ArrayList<>();
    for(int x = 0; x < finalList.size(); x++) {
      int y = 0;
      String combination = "";
      for(char[] combo : listOfCharArrays) {
        int index = finalList.get(x)[y];
        combination += combo[index];
        y++;
      }
      mPhonewordsGenerated.add(combination);
    }
  }

  public List<String> getPhonewords() {
    return mPhonewordsGenerated;
  }

}
