package hw5;

import java.io.IOException;
import java.util.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

public class MarvelPathsTest {
	
	MarvelPaths temp;
    @Before
    public void setUp() throws IOException {
        temp = new MarvelPaths();
    	temp.createNewGraph("data/PathsTest.csv");
    }
    @Test
    //tests normal find method
    public void checkStandardPath(){
        
    	//System.out.print(("HIII"));
    	String answer = (temp.findPath("uno", "fivo"));
        String checkAnswer = "path from uno to fivo:\nuno to tres via 1\ntres to quatro via 3\nquatro to fivo via 4\n";
        //System.out.println(answer);
        
        //System.out.println(checkAnswer);
    	assertEquals(answer, checkAnswer);
    	
    }
    
    @Test
    //tests the no path
    public void checkNoPath() {
    	String answer = (temp.findPath("uno", "fiver"));
        String checkAnswer = "path from uno to fiver:\nno path found\n";
        //System.out.println(answer);
        //System.out.println(checkAnswer);
    	assertEquals(answer, checkAnswer);
    }

    @Test
    //tests the no character found on second
    public void checkNoCharacterSeondOnly() {
    	String answer = (temp.findPath("uno", "pie"));
        String checkAnswer = "unknown character pie\n";
        //System.out.println(answer);
        //System.out.println(checkAnswer);
    	assertEquals(answer, checkAnswer);
    }

    @Test
    //tests the no character found on second
    public void checkNoCharacterSecondOnly() {
    	String answer = (temp.findPath("pie", "uno"));
        String checkAnswer = "unknown character pie\n";
        //System.out.println(answer);
        //System.out.println(checkAnswer);
    	assertEquals(answer, checkAnswer);
    }

    @Test
    //tests the no character found on second
    public void checkNoCharacterBoth() {
    	String answer = (temp.findPath("pie", "zaza"));
        String checkAnswer = "unknown character pie\nunknown character zaza\n";
        //System.out.println(answer);
        //System.out.println(checkAnswer);
    	assertEquals(answer, checkAnswer);
    }


    @Test
    //tests the no character found on second
    public void checkSameCharacter() {
    	String answer = (temp.findPath("uno", "uno"));
        String checkAnswer = "path from uno to uno:\n";
        //System.out.println(answer);
        //System.out.println(checkAnswer);
    	assertEquals(answer, checkAnswer);
    }

    @Test
    public void checkUnknownCharacterPathToSelf() {
    	String answer = (temp.findPath("zaza", "zaza"));
        String checkAnswer = "unknown character zaza\n";
        //System.out.println(answer);
        //System.out.println(checkAnswer);
    	assertEquals(answer, checkAnswer);
    }



}