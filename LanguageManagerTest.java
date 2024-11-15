package ProgrammingLanguages;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.*;
class LanguageManagerTest {

    @Test
    public void constructorPreconditions(){
        assertThrows(IllegalArgumentException.class, ()-> new LanguageManager(null));
    }

    File testFile = new File("./ProgrammingLanguages/datafiles/languages.txt");
    LanguageManager testLangMgr;



    @Test
    void getLanguageCount() throws FileNotFoundException {
        try{
            testLangMgr = new LanguageManager(testFile);
        }catch(FileNotFoundException e){
            throw e;
        }
    assertEquals(27, testLangMgr.getLanguageCount());
    }

    @Test
    void getLanguage() throws FileNotFoundException {
        try{
            testLangMgr = new LanguageManager(testFile);
        }catch(FileNotFoundException e){
            throw e;
        }
        Language testLanguage = testLangMgr.getLanguage(0);
        assertEquals(testLanguage, testLangMgr.getLanguage(0));
    }

    @Test
    void findShortestKwdLength() throws FileNotFoundException {
        try{
            testLangMgr = new LanguageManager(testFile);
        }catch(FileNotFoundException e){
            throw e;
        }
    assertEquals(2, testLangMgr.findShortestKwdLength());
    }

    @Test
    void findLongestKwdLength() throws FileNotFoundException {
        try{
            testLangMgr = new LanguageManager(testFile);
        }catch(FileNotFoundException e){
            throw e;
        }
        assertEquals(13, testLangMgr.findLongestKwdLength());
    }

    @Test
    void findLangWithFewestKwds() throws FileNotFoundException {
        try{
            testLangMgr = new LanguageManager(testFile);
        }catch(FileNotFoundException e){
            throw e;
        }
        assertEquals(3, testLangMgr.findLangWithFewestKwds());
    }

    @Test
    void findLangWithMostKwds() throws FileNotFoundException {
        try{
            testLangMgr = new LanguageManager(testFile);
        }catch(FileNotFoundException e){
            throw e;
        }
        assertEquals(7, testLangMgr.findLangWithMostKwds());
    }

    @Test
    void findLangKwdMatches() throws FileNotFoundException {
        try{
            testLangMgr = new LanguageManager(testFile);
        }catch(FileNotFoundException e){
            throw e;
        }
        int[] ansArray = {3, 5, 6, 7, 10, 12, 18, 20};
        assertArrayEquals(ansArray, testLangMgr.findLangKwdMatches("if"));
    }

    @Test
    void findLangsOfType() throws FileNotFoundException {
        try{
            testLangMgr = new LanguageManager(testFile);
        }catch(FileNotFoundException e){
            throw e;
        }
        int[] ansArray = {0, 5, 6, 7, 8, 10, 12, 23, 25};
        assertArrayEquals(ansArray,testLangMgr.findLangsOfType(LanguageInterface.Type.COMPILED));
    }
}