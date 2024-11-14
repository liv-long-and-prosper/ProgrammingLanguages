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
            String fileName = "./datafiles/"+fields[1];
            Language.Type type = LanguageInterface.Type.valueOf(fields[2].toUpperCase());

            Language language = new Language(name, fileName, type);
            System.out.printf("Current language: %s\n-----------------------------\n", language.getName());
            languages[i] = language;
            System.out.println("Languages Array: "+Arrays.toString(languages)+"\n---------------------------------\n");
        }

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
        int shortestKwdLength = languages[0].findShortestKwdLength();
        for(Language language : languages){
            if (language.findShortestKwdLength() < shortestKwdLength){
                shortestKwdLength = language.findLongestKwdLength();
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
        int lastMatchIndex = -1;

        for (int i = 0; i < langsWithMatches.length; i++){
            for(int j = 0; j < languages.length; j++){
                int kwdFound = languages[j].findKwd(keyword);
                if (kwdFound != -1 && j > lastMatchIndex){
                    lastMatchIndex = j;
                    langsWithMatches[i] = j;
                }
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
            if(lang.getType() == type){
                matchCount++;
            }
        }

        langsOfType = new int[matchCount];

        for(int i = 0; i < langsOfType.length; i++){
            int langsIndex = 0;
            for(Language lang : languages){
                if(lang.getType() == type){
                    langsOfType[i] = langsIndex;
                }
                langsIndex++;
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
