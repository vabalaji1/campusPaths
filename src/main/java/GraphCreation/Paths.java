package GraphCreation;

import java.util.*;

public class Paths<T extends Comparable<T>, E extends Number & Comparable<E>> implements Comparable<Paths<T,E>>{
    List<Edge<T,E>> paths = new ArrayList<Edge<T, E>>();

    /*
   
    @modifies makes a new paths
    
    */
    public Paths(){

    }

    public Paths(Paths<T,E> e){
        for(int i = 0; i < e.paths.size(); i++){
            this.paths.add(e.getEdge(i));
        }
    }
    /*
    @param the edge to add
    @modifies this.paths
    @effects adds the edge to the list
    
    */
    public void addEdge(Edge<T,E> temp){
        //System.out.println(temp.getDir());
        paths.add(temp);
    }
    /*
    
    
    
    @return the sum of all the edges in the paths
    */
    public double sum(){
        double sum = 0.0;
            for(Edge<T, E> i: paths ){
                sum= sum+ i.getEdgeWeight().doubleValue();

            }
        return sum;
    }
    /*
    @param none
    
    
    @return the last edge is returned
    */
    public Edge<T, E> getLastEdge(){
        return new Edge<T, E>(paths.get(paths.size()-1).getLabel(),paths.get(paths.size()-1).getNode(), paths.get(paths.size()-1).getEdgeWeight(),paths.get(paths.size()-1).getDir());
    }
    /*
    @param the location
   
   
    @returns the edge at that position
    */
    public Edge<T, E> getEdge(int i){
        return new Edge<T, E>(paths.get(i).getLabel(),paths.get(i).getNode(), paths.get(i).getEdgeWeight(),paths.get(i).getDir() );
    }
 
    /*
    @param the paths to comapre with
    
    
    @return 0 if equal, 1 if this is greater,-1 if this is lower
    */
    @Override
    public int compareTo(Paths<T,E> compare) {
        
            double sumSelf = this.sum();
            double sumCompare = compare.sum();
            
            if(sumSelf > sumCompare){
                return 1;
            }
            if(sumSelf < sumCompare){
                return -1;
            }
            return 0;

        
        // TODO Auto-generated method stub
        
    }

    /*
    
    
    
    @return the size of the paths
    */
    public int getSize(){
        return this.paths.size();
    }
     

    
}
