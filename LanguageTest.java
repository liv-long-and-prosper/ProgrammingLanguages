package ProgrammingLanguages;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LanguageTest {

    @Test
    public void constructorPreconditions(){
        // testing null or blank name
        assertThrows(IllegalArgumentException.class, ()-> new Language("","./datafiles/cplusplus.txt", LanguageInterface.Type.COMPILED));
        assertThrows(IllegalArgumentException.class, ()-> new Language(null,"./datafiles/cplusplus.txt", LanguageInterface.Type.COMPILED));

        // testing null or blank fileName
        assertThrows(IllegalArgumentException.class, ()-> new Language("C++","", LanguageInterface.Type.COMPILED));
        assertThrows(IllegalArgumentException.class, ()-> new Language("C++",null, LanguageInterface.Type.COMPILED));

        //testing null type
        assertThrows(IllegalArgumentException.class, ()-> new Language("C++","./datafiles/cplusplus.txt", null));
    }

    Language testLanguage = new Language("python", "./ProgrammingLanguages/datafiles/python.txt", LanguageInterface.Type.INTERPRETED);

    @Test
    void getName() {
        assertEquals("python", testLanguage.getName());
    }

    @Test
    void getFilename() {
        assertEquals("./ProgrammingLanguages/datafiles/python.txt", testLanguage.getFilename());
    }

    @Test
    void getType() {
        assertEquals(LanguageInterface.Type.INTERPRETED, testLanguage.getType());
    }

    @Test
    void getKwdCount() {
        assertEquals(30, testLanguage.getKwdCount());
    }


    @Test
    void getKwd() {
        assertEquals("and", testLanguage.getKwd(0));
    }

    @Test
    void findKwd() {
        assertEquals(0, testLanguage.findKwd("and"));
    }

    @Test
    void findShortestKwdLength() {
        assertEquals(2, testLanguage.findShortestKwdLength());
    }

    @Test
    void findLongestKwdLength() {
        assertEquals(8, testLanguage.findLongestKwdLength());
    }

}