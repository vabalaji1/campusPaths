package GraphCreation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


import java.util.Collections;



public class Graph<T extends Comparable<T>, E extends Comparable<E>> {
    
	public HashMap<T, ArrayList<Edge<T,E>>> Graph;
    //rep invariant: data has no nulls and no duplicates
    // abstract function: HashMap<String, ArrayList<Edge>> 
    //                    represents <Node, [Edges originating from Node]>
    //                    which represents the graph
    int nodeCount;
    int edgeCount;
	/*
    @effects creates a new graph
    */
    
    
	public Graph(){
        Graph = new HashMap<T, ArrayList<Edge<T,E>>> ();
        nodeCount = 0;
        edgeCount = 0;
        
        //checkRep();
	}
	/*
    @param a node name in the form of a String
    
    @returns true if the graph node is in the graph false if otherwise

    */
    public boolean doesExist(T node) {
        return Graph.containsKey(node);
    }
    
    
	/*
    @param a node name in the form of a String
    @modifies this.graph
    @effects adds the new node to the grpah
    @throws throw new IllegalArgumentException("Node Already Exists"); if the node already exist

    */
    public void addNode(T nodeData){
        if(nodeData ==null){
            throw new IllegalArgumentException("Node is null");
        }
        if(Graph.containsKey(nodeData)){
            //need to throw an exception
            throw new IllegalArgumentException("Node Already Exists");
        }
        Graph.put(nodeData, new ArrayList<Edge<T,E>>());
        nodeCount++;
        //checkRep();
        //Graph.put(nodeData, new ArrayList<String>());

    }

     /*
    @param a edge name in the form of a String
    @modifies this.graph 
              adjacency list
    @effects adds the new edge to the specififed node to the parent node
    @throws throw new IllegalArgumentException("edge Already Exists"); if the edge already exist
            throw new IllegalArgumentException("Node doesnt Exists"); if the child or parent node doesnt exist
    */
    public void addEdge(T parentNode, T childNode, T edgeLabel){
        if(parentNode ==null || childNode == null){
            throw new IllegalArgumentException("Node is null");
        }
        if(edgeLabel ==null){
            throw new IllegalArgumentException("Node is null");
        }
        if(Graph.containsKey(parentNode) == false){
            //throw new IllegalArgumentException("Node doesnt Exists");
        }
        if(Graph.containsKey(childNode) == false){
            //throw new IllegalArgumentException("Node doesnt Exists");
        }
        Edge<T,E> temp = new Edge<T,E> (childNode, edgeLabel);
        if(Graph.get(parentNode).contains(temp)){
            throw new IllegalArgumentException("edge Already Exists");
        }
        Graph.get(parentNode).add(new Edge<T,E>(childNode, edgeLabel));
        edgeCount++;
        //Collections.sort(Graph.get(parentNode));
        // if the alphabetical order does not work then check here
        //checkRep();
        
    }
    /*
    @param a edge name in the form of a String
    
    
    @retrun the edge wight of the edge if there is one
    */
    public E getEdgeWeight(T parentNode, T childNode, T edgeLabel){
        
        Edge<T,E> temp = new Edge<T,E> (childNode, edgeLabel);
       
        int index = Graph.get(parentNode).indexOf(temp);
        if(index !=-1){
            return Graph.get(parentNode).get(index).getEdgeWeight();
            //throw new IllegalArgumentException("edge Already Exists");
        }
        //System.out.println("ROOOOOWWOWOWOWOWOWOOWOWOWWO");
        return null;
        
    }
    /*
    @param a edge name in the form of a String and the edge weight
    @modifies this.graph 
              adjacency list
    @effects adds the new edge to the specififed node to the parent node
    @throws 
            throw new IllegalArgumentException("Node doesnt Exists"); if the child or parent node doesnt exist
    */
    public void addEdge(T parentNode, T childNode, T edgeLabel, E edgeWeight){
        
        if(parentNode ==null || childNode == null){
            throw new IllegalArgumentException("Node is null");
        }
        if(edgeLabel ==null){
            //throw new IllegalArgumentException("Node is null");
        }
        if(Graph.containsKey(parentNode) == false){
            //throw new IllegalArgumentException("Node doesnt Exists");
        }
        if(Graph.containsKey(childNode) == false){
            //throw new IllegalArgumentException("Node doesnt Exists");
        }
        Edge<T,E> temp = new Edge<T,E> (childNode, edgeLabel);
        temp.setEdgeWeight(edgeWeight);
        int index = Graph.get(parentNode).indexOf(temp);
        if(index !=-1){
            Graph.get(parentNode).get(index).setEdgeWeight(edgeWeight);
            //throw new IllegalArgumentException("edge Already Exists");
        }
        else{
            Graph.get(parentNode).add(temp);
            edgeCount++;
        }
        
        
        //Collections.sort(Graph.get(parentNode));
        // if the alphabetical order does not work then check here
        //checkRep();
        
    }

    public void addEdge(T parentNode, T childNode, T edgeLabel, E edgeWeight, String direction){
        
        if(parentNode ==null || childNode == null){
            throw new IllegalArgumentException("Node is null");
        }
        if(edgeLabel ==null){
            //throw new IllegalArgumentException("Node is null");
        }
        if(Graph.containsKey(parentNode) == false){
            //throw new IllegalArgumentException("Node doesnt Exists");
        }
        if(Graph.containsKey(childNode) == false){
            //throw new IllegalArgumentException("Node doesnt Exists");
        }
        Edge<T,E> temp = new Edge<T,E> (childNode, edgeLabel);
        temp.setEdgeWeight(edgeWeight);
        
        temp.setDir(direction);
        int index = Graph.get(parentNode).indexOf(temp);
        if(index !=-1){
            Graph.get(parentNode).get(index).setEdgeWeight(edgeWeight);
            Graph.get(parentNode).get(index).setDir(direction);
            //throw new IllegalArgumentException("edge Already Exists");
        }
        else{
            Graph.get(parentNode).add(temp);
            //System.out.println(temp.getDir());
            edgeCount++;
        }
        
        
        //Collections.sort(Graph.get(parentNode));
        // if the alphabetical order does not work then check here
        //checkRep();
        
    }

    /*
    @return a iterator to the node names sorted alphabetically
    */
    public Iterator<T> listNodes(){
        ArrayList<T> temp = new ArrayList<T>();
        Set<T> keys = Graph.keySet();
        for(T key: keys){
            temp.add(key);
        }
        Collections.sort(temp);
        return temp.iterator();

    }
    /*
    @param a parent node name
    @throws IllegalArgumentException("Node doesnt Exists"); if parent node doesnt exist
    @return a iterator to the node names sorted alphabetically
    */
    public Iterator<String> listChildren(T parentNode)                       {
        if(Graph.containsKey(parentNode) == false){
            throw new IllegalArgumentException("Node doesnt Exists");
        }
        //ArrayList<Edge> temp = new ArrayList<Edge>();
        //temp = (ArrayList)Graph.get(parentNode).clone();
        Collections.sort(Graph.get(parentNode));
        ArrayList<String> temp2 = new ArrayList<String>();
        
        for(Edge<T, E> key: Graph.get(parentNode)){
            temp2.add(key.toString());

        }
        return temp2.iterator();

    }

    public Iterator<Edge<T, E>> listChildrenEdge(T parentNode){
        if(Graph.containsKey(parentNode) == false){
            throw new IllegalArgumentException("Node doesnt Exists");
        }
        Collections.sort(Graph.get(parentNode));
        return Graph.get(parentNode).iterator();
        

    }
    /*
    
    
    @effects resets the graph
    */
    public void clear(){
        Graph = new HashMap<T, ArrayList<Edge<T, E>>> ();

    }
    
    /*
    
    
    @effects checks that all elements are in the hashmap and none are missing
    */
    /*
    private void checkRep(){
        if (nodeCount != this.Graph.size()) {
            throw new RuntimeException(" node size is off");
        }
        Collection<ArrayList<Edge>> values = Graph.values();
        int count = 0;
        for (ArrayList<Edge> v : values) {
            if(v == null){
                throw new RuntimeException(" the edge is null");
            }
            count+=v.size();
        }
        if(count!= edgeCount){
            throw new RuntimeException(" edge size is off");
        }

        
    }
    */

}


