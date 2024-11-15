/**
 * Language class to be used to interpret keyword files
 *
 * @author Liv Long
 * @version 11-11-2024
 * */

package ProgrammingLanguages;

import java.io.*;
import java.util.*;

/**
 * The type Language.
 */
public class Language implements LanguageInterface {

  // Language class attributes
  private String name;
  private String fileName;
  private Type type;
  private String[] keywords;


    /**
     * {@inheritDoc}
     *
     * @param name     the name
     * @param fileName the file name
     * @param type     the type
     */
    public Language(String name, String fileName, Type type) {

      // Constructor Pre-conditions
      if(name == null || name.isBlank()){
          throw new IllegalArgumentException("name cannot be null or empty, please provide a valid String for name.");
      }
      if(fileName == null || fileName.isBlank()){
          throw new IllegalArgumentException("fileName cannot be null or empty, please provide a valid String for fileName.");
      }
      if(type == null){
          throw new IllegalArgumentException("type cannot be null, please provide a valid Type");
      }

      this.name = name;
      this.fileName = fileName;
      this.type = type;


      File kwdFile = new File(fileName);
      Scanner fileScan = null;
      int kwdCount;

      // Prepare file to be read
      try{
          fileScan = new Scanner(kwdFile);
          kwdCount = fileScan.nextInt();
      }catch (FileNotFoundException e){
          kwdCount = 0;
          System.out.printf("Error: %s\n", e.getMessage());
          System.out.printf("%s does not contain any key words.\n" +
                  "If you think this is a mistake, please verify the information contained in %s and try again.\n\n", name, fileName);
      }

          // Initialize array with keyword count
          keywords = new String[kwdCount];

          for (int i = 0; i < keywords.length; i++) {
              String kwd = fileScan.next();
              keywords[i] = kwd;
          }
          sortKwds();

          if (fileScan != null) {
              fileScan.close();
          }
  }

 /**
  * {@inheritDoc}
  *
  * */
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     *
     * */
    public String getFilename() {
        return fileName;
    }

    /**
     * {@inheritDoc}
     *
     * */
    public Type getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     * */
    public int getKwdCount() {
        return keywords.length;
    }


    /**
     * {@inheritDoc}
     * */
    public String getKwd(int index) {
      String e;
      int maxIndex = keywords.length-1;
      // Preconditions
      if(index < 0){
          e = String.format("index must not be negative, please provide a whole number between 0 & %d", maxIndex);
          throw new IllegalArgumentException(e);
      }else if(index >= keywords.length){
          e = String.format("index must not be greater than %d, please provide a whole number between 0 & %d", maxIndex );
          throw new IllegalArgumentException(e);
      }
        return keywords[index];
    }

    /**
     * {@inheritDoc}
     * */
    public int findKwd(String keyword) {
        // Precondition
      if(keyword == null || keyword.isBlank()){
          throw new IllegalArgumentException("keyword cannot be null or empty, please provide a valid String");
      }
       int kwdIdx = -1;
       for (int i = 0; i < keywords.length; i++){
           if (keywords[i].equals(keyword)){
               kwdIdx = i;
           }
       }
       return kwdIdx;
    }

    /**
     * {@inheritDoc}
     * */
    public int findShortestKwdLength() {
        if(keywords.length == 0){return -1;}
      int shortestKwd = keywords[0].length();
      for (String kwd : keywords){
            if (kwd.length() < shortestKwd){
                shortestKwd = kwd.length();
            }
        }
        return shortestKwd;
    }

    /**
     * {@inheritDoc}
     * */
    public int findLongestKwdLength() {
        if(keywords.length == 0){return -1;}
        int longestKwd = keywords[0].length();
        for (String kwd : keywords){
            if (kwd.length() > longestKwd){
                longestKwd = kwd.length();
            }
        }
        return longestKwd;
    }


    /**
     * {@inheritDoc}
     * */
    public void sortKwds() {
        for(int pass = 1; pass < keywords.length; pass++){
            String placeholder = keywords[pass];
            int checkPos = pass-1;
            while(checkPos >= 0 && placeholder.compareTo(keywords[checkPos])<0){
                keywords[checkPos+1] =  keywords[checkPos];
                checkPos--;
            }
            keywords[checkPos+1] = placeholder;
        }
    }

    public String toString(){
      int kwdCount = getKwdCount();
      return " Language Name: "+name+"\nLanguage Type: "+type+"\nKeyword count: "+kwdCount;
    }

}

