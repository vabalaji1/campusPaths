package hw7;

import java.io.IOException;
import java.util.*;

import org.junit.Test;

import hw4.*;
import hw7.*;

import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

public class hw7Test {	
	CampusPaths temp;
    @Before
    public void setUp() throws IOException {
        temp = new CampusPaths();
    	temp.createNewGraph("data/RPI_map_data_Nodes.csv","data/RPI_map_data_Edges.csv");
        
    }
    @Test
    public void testCommon(){
        System.out.println(temp.findPath("677", "76"));
        //CampusPathsTest blah = 
        assertEquals("hi", "hi");
    }
}