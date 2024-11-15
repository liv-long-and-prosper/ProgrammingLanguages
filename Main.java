package ProgrammingLanguages;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * Main class used to run Language and LanguageManager
 */
public class Main{

    /**
     * Main
     *
     * @param args      the args
     */
    public static void main(String[] args){
        
    // Initializing a Language object and testing its methods
    System.out.println("Testing python language");
    Language python = new Language("python", "./datafiles/python.txt", LanguageInterface.Type.INTERPRETED);
    python.toString();

    System.out.println("Kwd at the 3rd idx: "+ python.getKwd(3));
    System.out.println("Idx of kwd 'if': "+ python.findKwd("if"));
    System.out.println("Length of the shortest kwd: "+ python.findShortestKwdLength());
    System.out.println("Length of the longest kwd: "+ python.findLongestKwdLength());
    System.out.print("\n\n\n-----------------------------\n");

    
    // Initializing LanguageManager obj and testing its methods
    System.out.println("Testing LanguageManager");
    File l = new File("./datafiles/languages.txt");
    LanguageManager m = null;

    try{
        m = new LanguageManager(l);
    } catch (FileNotFoundException e) {
       System.out.println("File not found");
    }

    System.out.println("Language count: "+m.getLanguageCount());
    System.out.println("Language: "+m.getLanguage(0));
    System.out.println("Kwd matches: "+Arrays.toString(m.findLangKwdMatches("if")));
    System.out.println("Shortest kwd: "+m.findShortestKwdLength());
    System.out.println("Longest kwd: "+m.findLongestKwdLength());
    System.out.println("Language w/ fewest kwds: "+m.findLangWithFewestKwds());
    System.out.println("Language w/ most kwds: "+m.findLangWithMostKwds());
    System.out.print("Compiled languages: "+Arrays.toString(m.findLangsOfType(LanguageInterface.Type.COMPILED)));
    }

}