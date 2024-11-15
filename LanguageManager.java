/**
 * Language Manager class to be used to interpret language files
 *
 * @author Liv Long
 * @version 11-11-2024
 * */

package ProgrammingLanguages;

import java.io.*;
import java.util.*;

/**
 * The type Language manager.
 */
public class LanguageManager implements LanguageManagerInterface{

    private Language[] languages;

    /**
     * Instantiates a new Language manager.
     *
     * @param languageFile the language file
     * @throws FileNotFoundException the file not found exception
     */
    public LanguageManager(File languageFile) throws FileNotFoundException{
        if(languageFile == null){
            throw new IllegalArgumentException("languageFile must not be null, please provide a valid File");
        }

        // initialize null Scanner object
        Scanner languageScanner;

        // Prepare file to be read; throws exception if file cannot be found
        try {
            languageScanner = new Scanner(languageFile);
        } catch (FileNotFoundException e) {
            throw e;
        }

        // Count languages
        int languageCount = languageScanner.nextInt();
        languages = new Language[languageCount];

        // Skip column labels
        languageScanner.nextLine();
        languageScanner.nextLine();

        for(int i = 0; i < languageCount; i++){
            // Separate each column into separate fields
            String line = languageScanner.nextLine();
            String[] fields = line.split(",");

            String name = fields[0];

//            String fileName = "./ProgrammingLanguages/datafiles/"+fields[1]; // fileName needed when running LanguageManagerTest

            String fileName = "./datafiles/"+fields[1];// fileName needed when running Main.java

            Language.Type type = LanguageInterface.Type.valueOf(fields[2].toUpperCase());

            Language language = new Language(name, fileName, type);
            languages[i] = language;
        }
        sortLangs();
    }

    /**
     * {@inheritDoc}
     *
     * */
    public int getLanguageCount() {
        return languages.length;
    }

    /**
     * {@inheritDoc}
     *
     * */
    public Language getLanguage(int index) {
        return languages[index];
    }

    /**
     * {@inheritDoc}
     *
     * */
    public int findShortestKwdLength() {
        int shortestKwdLength = 0;
        for(Language language : languages){
            if (language.findShortestKwdLength() > 0 && shortestKwdLength == 0){
                shortestKwdLength = language.findShortestKwdLength();
            } else if (language.findShortestKwdLength() < shortestKwdLength && language.findShortestKwdLength() > 0) {
                shortestKwdLength = language.findShortestKwdLength();
            }
        }
        return shortestKwdLength;
    }

    /**
     * {@inheritDoc}
     *
     * */
    public int findLongestKwdLength() {
        int longestKwdLength = languages[0].findLongestKwdLength();
        for(Language language : languages){
            if (language.findLongestKwdLength() > longestKwdLength){
                longestKwdLength = language.findLongestKwdLength();
            }
        }
        return longestKwdLength;
    }

    /**
     * {@inheritDoc}
     *
     * */
    public int findLangWithFewestKwds() {
        int langIndex = -1;
        int fewestKwds = languages[0].getKwdCount();

        for (int i = 0; i < languages.length; i++) {
            int currentKwdCount = languages[i].getKwdCount();
            if (currentKwdCount > 0) {
                if (fewestKwds == 0 || currentKwdCount < fewestKwds) {
                    fewestKwds = currentKwdCount;
                    langIndex = i;
                }
            }
        }
        return langIndex;
    }

    /**
     * {@inheritDoc}
     *
     * */
    public int findLangWithMostKwds(){
        int langIndex = -1;
        int mostKwds = 0;

        for(int i = 0; i < languages.length; i ++){
            int currentKwdCount = languages[i].getKwdCount();
            if (currentKwdCount > mostKwds){
                mostKwds = currentKwdCount;
                langIndex = i;
            }
        }
        return langIndex;
        }


        /**
         * {@inheritDoc}
         *
         * */
    public int[] findLangKwdMatches(String keyword) {
        // initialize a null array and counter for tracking how many languages have a match
        int[] langsWithMatches;
        int matchCount = 0;

        // Count how many languages have a match
        for(Language lang : languages){
            if(lang.findKwd(keyword) != -1){
                matchCount++;
            }
        }

        // initialize null array to be an array with length of matchCount
        langsWithMatches = new int[matchCount];
        int matchIdx = 0;
        for(int i = 0; i < languages.length; i++){
            if (languages[i].findKwd(keyword) != -1){
                langsWithMatches[matchIdx] = i;
                matchIdx++;
            }
        }
        return langsWithMatches;
    }

    /**
     * {@inheritDoc}
     *
     * */
    public int[] findLangsOfType(Language.Type type) {
        int[] langsOfType;
        int matchCount = 0;

        for(Language lang : languages){
            if(lang.getType().equals(type)){
                matchCount++;
            }
        }

        langsOfType = new int[matchCount];
        int langsIdx = 0;

        for(int i = 0; i < languages.length; i++){
                if(languages[i].getType().equals(type)){
                    langsOfType[langsIdx] = i;
                    langsIdx++;
                }
        }
        return langsOfType;
    }

    /**
     * {@inheritDoc}
     *
     * */
    public void sortLangs() {
        for(int pass = 1; pass < languages.length; pass++){
            Language placeholder = languages[pass];
            int checkPos = pass-1;
            while(checkPos >= 0 && placeholder.getName().compareTo(languages[checkPos].getName())<0){
                languages[checkPos+1] =  languages[checkPos];
                checkPos--;
            }
            languages[checkPos+1] = placeholder;
        }

    }
}
