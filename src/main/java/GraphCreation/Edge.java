package GraphCreation;

@SuppressWarnings("unchecked")
public class Edge<T extends Comparable<T>, E extends Comparable<E>>  implements Comparable<Edge<T, E>> {
    
    T label = null;
    T node = null;
    T labelBegin = null;
    T nodeBegin = null;
    E edgeWeight = null;
    String direction = "";

    //rep invariant: the label begin will always equal the label and the nodebeginw ill always equal the node
    //abstract function: the label and node represent a edge
    /*
    @effects creates a new edge
    */
    public Edge(T Node, T Label){
        label = Label;
        node = Node;
        labelBegin = Label;
        nodeBegin = Node;
        checkRep();
    }
    /*
    @effects creates a new edge acts like a copy constructor
    */
    public Edge(T Label,
    T Node, E EdgeWeight){
        this.label = Label;
        this.node = Node;
        this.edgeWeight = EdgeWeight;
        this.nodeBegin = Node;
        this.labelBegin = Label;
    }
    public Edge(T Label,
    T Node, E EdgeWeight, String dir){
        this.label = Label;
        this.node = Node;
        this.edgeWeight = EdgeWeight;
        this.nodeBegin = Node;
        this.labelBegin = Label;
        this.direction = dir;
    }

   
    /*
    
    
    @returns the node of the edge it connects to in the form of a string

    */
    public T getNode(){
        return node;
    }
    public E getEdgeWeight(){
        return edgeWeight;
    }
    public void setEdgeWeight(E weight){
        edgeWeight = weight;
    }

    public void setDir(String temp){
        this.direction = temp;

    }
    
    public String getDir(){
        return this.direction;
    }
    
    /*
    
    
    @returns the label of the edge in the form of a  string

    */
    public T getLabel(){
        return label;
    }
    /*
    
    
    @return a string represntation
    
    */
    @Override
    public String toString(){
        return this.node.toString() + "(" + this.label.toString() + ")";
    }


    /*
    @param a edge to compare
    
    @effects compares two edges together to see which one is lower
    @return 0 if equal, 1 if this is greater,-1 if this is lower
    */
    @Override
    public int compareTo(Edge<T, E> arg0) {
        
       

        if(this.edgeWeight !=null && arg0.edgeWeight !=null){
            if(this.edgeWeight.compareTo(arg0.edgeWeight) == 0){
                if(this.node.compareTo(arg0.node) ==0){
                    return 0;
        
                }
                if(this.node.compareTo(arg0.node) > 0){
                    return 1;
                }
                else{
                    return -1;
                }
            }
            if(this.edgeWeight.compareTo(arg0.edgeWeight) > 0){
                return 1;
            }
            if(this.edgeWeight.compareTo(arg0.edgeWeight) < 0){
                return -1;
            }
        }

        if(this.node.compareTo(arg0.node) ==0){
            if(this.label.compareTo(arg0.label) == 0){
                return 0;
            }
            if(this.label.compareTo(arg0.label) > 0)
            {
                return 1;
            }
            else{
                return -1;
            }

        }
        if(this.node.compareTo(arg0.node) > 0){
            return 1;
        }
        else{
            return -1;
        }
        
    }
    /*
    @param a edge to compare
    
    @effects compares two edges together to see if it is equal
    @return true if it is equal otherwise false
    */
    @Override
    public boolean equals(Object  edge){
        
        if(edge == this){
            return true;
        }
        if(!(edge instanceof Edge)){
            return false;
        }
        Edge<T,E> temp = (Edge<T,E>) edge;
        if(this.label.equals(temp.label)){
            if(this.node.equals(temp.node)){
                return true;
            }
        }
        return false;
    }
    protected void checkRep(){
        if (labelBegin != label) {
            throw new RuntimeException(" label changed");
        }
        if(nodeBegin != node){
            throw new RuntimeException(" node changed");
        }
    }

}