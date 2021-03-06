package numberspell;

import java.util.*;
import java.io.*;

class Dictionary {
  private static Set<String> mDictionaryWords;

  public static void load(){
    try {
      Scanner scanner = new Scanner(new File("C:\\Users\\Smile\\Documents\\NetBeansProjects\\NumberSpell\\src\\numberspell\\wordList.txt"));
      mDictionaryWords = new HashSet<>();
      mDictionaryWords.clear();
      while (scanner.hasNext()){
          mDictionaryWords.add(scanner.next());
      }
      scanner.close();
    } catch(FileNotFoundException ex) {
      System.out.println("File not found. wtf");
      System.exit(0);
    }
  }

  public static Set<String> getDictionaryWordList(){
    return mDictionaryWords;
  }
}
