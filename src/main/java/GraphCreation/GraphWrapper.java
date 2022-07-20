package GraphCreation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Collections;



public class GraphWrapper {
	public Graph graph;
    // rep invariant and abstraction function would go here
	
	public GraphWrapper(){
        this.graph = new Graph();
	}
    

    public void addNode(String nodeData){
        
        graph.addNode(nodeData);

    }

    public void addEdge(String parentNode, String childNode, String edgeLabel){
        graph.addEdge(parentNode, childNode, edgeLabel);

    }
    public Iterator<String> listNodes(){
        return graph.listNodes();

    }

    public Iterator<String> listChildren(String parentNode){
        return graph.listChildren(parentNode);

    }

    public void clear(){
        graph = new Graph();

    }
    

}


