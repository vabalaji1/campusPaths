package GraphCreation;

import java.util.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

public class GraphWrapperTest {
	
	GraphWrapper graphWrap;
    @Before
    public void setUp() {
		graphWrap = new GraphWrapper();
    	
    }
    @Test
    //tests clear method
    public void testClear() {
    	graphWrap.addNode("A");
    	graphWrap.addNode("B");
    	graphWrap.addEdge("A", "B", "1");
    	graphWrap.clear();
    	String tempStr = "";
    	Iterator<String> temp = graphWrap.listNodes();
        while(temp.hasNext()) {
     	   tempStr = tempStr + temp.next();
     	   
        }
	    assertEquals("", tempStr);
    	
    }
    @Test
    //tests the children iterator
    public void testListNode() {
    	graphWrap.clear();
    	graphWrap.addNode("A");
    	graphWrap.addNode("B");
    	graphWrap.addNode("C");
    	graphWrap.addEdge("A", "B", "1");
        Iterator<String> temp = graphWrap.listChildren("B");
        Iterator<String> temp2 = graphWrap.listChildren("A");
        assertEquals(temp.hasNext(), false);
        assertEquals(temp2.hasNext(), true);
        temp2 = graphWrap.listChildren("A");
        assertEquals(temp2.next(), "B(1)");
        

    }
    @Test
    // tests the Nodes iterator
    public void testListEdge() {
    	graphWrap.clear();
        Iterator<String> temp = graphWrap.listNodes();
        assertEquals(temp.hasNext(), false);
        graphWrap.addNode("A");
    	graphWrap.addNode("B");
    	graphWrap.addNode("C");
    	temp = graphWrap.listNodes();
    	assertEquals(temp.next(), "A");
    	assertEquals(temp.next(), "B");
    	assertEquals(temp.next(), "C");
    	assertEquals(temp.hasNext(), false);
    	
        

    }
    //add node tests
    @Test
    public void testAddNodeNew() {
    	graphWrap.clear();
    	graphWrap.addNode("A");
    	graphWrap.addNode("B");
    	graphWrap.addNode("C");
    	Iterator<String> temp = graphWrap.listNodes();
        String tempStr = "";
        while(temp.hasNext()) {
     	   
     	   tempStr = tempStr + temp.next();
     	   
        }
	    assertEquals("ABC", tempStr);
    	
    }
    
    @Test
    public void testRepeatNode() {
    	graphWrap.clear();
    	graphWrap.addNode("A");
    	boolean checkDup = false;
    	try{
    		graphWrap.addNode(null);
    	} catch(IllegalArgumentException x) {
    		checkDup = true;
    	}
	    assertEquals(checkDup, true);
    	checkDup = false;
    	try{
    		graphWrap.addNode("A");
    	} catch(IllegalArgumentException x) {
    		checkDup = true;
    	}
	    assertEquals(checkDup, true);

    	graphWrap.addNode("C");
    	Iterator<String> temp = graphWrap.listNodes();
        String tempStr = "";
        while(temp.hasNext()) {
     	   
     	   tempStr = tempStr + temp.next();
     	   
        }
	    assertEquals("AC", tempStr);
    }
    // add edge tests
    @Test
    public void testAddEdgeNew() {
    	graphWrap.clear();
    	graphWrap.addNode("A");
    	
    	graphWrap.addNode("B");
    	graphWrap.addNode("C");
    	graphWrap.addNode("D");
    	graphWrap.addEdge("A", "B", "1");
    	graphWrap.addEdge("A", "C", "2");
    	graphWrap.addEdge("B", "A", "1");
    	graphWrap.addEdge("B", "C", "1");
    	graphWrap.addEdge("B", "C", "2");
    	graphWrap.addEdge("D", "D", "2");
    	graphWrap.addEdge("D", "D", "1");
    	Iterator<String> temp = graphWrap.listChildren("A");
    	Iterator<String> temp2 = graphWrap.listChildren("B");
    	Iterator<String> temp3 = graphWrap.listChildren("C");
    	Iterator<String> temp4 = graphWrap.listChildren("D");
        String tempStr = "";
        while(temp.hasNext()) {
     	   
     	   tempStr = tempStr + temp.next();
     	   
        }
        String tempStr2 = "";
        while(temp2.hasNext()) {
     	   
     	   tempStr2 = tempStr2 + temp2.next();
     	   
        }
        String tempStr3 = "";
        while(temp3.hasNext()) {
     	   
     	   tempStr3 = tempStr3 + temp3.next();
     	   
        }
        String tempStr4 = "";
        while(temp4.hasNext()) {
     	   
     	   tempStr4 = tempStr4 + temp4.next();
     	   
        }
        assertEquals("B(1)C(2)", tempStr);
        assertEquals("A(1)C(1)C(2)", tempStr2);
        assertEquals("", tempStr3);
        assertEquals("D(1)D(2)", tempStr4);
	    
    }
    @Test
    public void testRepeatEdge() {
    	
    	graphWrap.clear();
    	graphWrap.addNode("A");
    	graphWrap.addNode("B");
    	graphWrap.addNode("C");
    	graphWrap.addEdge("A", "B", "1");
    	boolean checkDup = false;
    	try{
    		graphWrap.addEdge("A", null, "1");
    	} catch(IllegalArgumentException x) {
    		checkDup = true;
    	}
	    assertEquals(checkDup, true);
	    checkDup = false;
	    try{
    		graphWrap.addEdge("A", "1", null);
    	} catch(IllegalArgumentException x) {
    		checkDup = true;
    	}
	    assertEquals(checkDup, true);
    	checkDup = false;
    	try {
    		graphWrap.addEdge("A", "B", "1");
    	}
    	catch(IllegalArgumentException x) {
    		checkDup = true;
    	}
    	assertEquals(checkDup, true);
    	
    	graphWrap.addEdge("A", "C", "1");
    	Iterator<String> temp = graphWrap.listChildren("A");

    	String tempStr = "";
        while(temp.hasNext()) {
     	   
     	   tempStr = tempStr + temp.next();
     	   
        }
       System.out.println(tempStr);
       assertEquals("B(1)C(1)", tempStr);
    	
    }
   
    
}
