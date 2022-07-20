package CampusPaths1;
import java.util.*;
public class Node implements Comparable<Node> {
    String Name = null;
    int ID;
    int X;
    int Y;

    public Node(){

    }
    /*
    @param the name the id and the x,y coordinates
    @modifies the current node
    @effects fmakes a new node
    @return a node representation
    
    */
    public Node(String name, String id, String x, String y){

        this.Name = name;
        this.ID = Integer.parseInt(id);
        this.X = Integer.parseInt(x);
        this.Y = Integer.parseInt(y);

    }
    
    /*
    @return the name
    
    */
    public String getName(){
        return this.Name;

    }
    /*
    @return the ID
    
    */
    public int getID(){
        return this.ID;

    }
    /*
    @return the x val
    
    */
    public int getX(){
        return X;
    }
    /*
    @return the y va
    
    */
    public int getY(){
        return Y;
    }
    /*
    @param the Node to compare
   
    
    @return -1 if less, 0 if same and 1 if larger
    
    */
    @Override
    public int compareTo(Node arg0) {
       if(arg0.ID < this.ID){

        return -1;
       }
       if(arg0.ID == this.ID){
           return 0;
       }
       return 1;
    
    }
    /*
    @param the name the id and the x,y coordinates
    
    @return string represntation of the node.
    
    */
    @Override
    public String toString(){
        return this.Name;
    }

}
