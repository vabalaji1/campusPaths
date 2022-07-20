package CampusPaths1;

import hw4.*;
import java.util.*;
import java.io.IOException;
import java.lang.Math;

public class CampusPaths{
	Graph<Node, Double> graph = new Graph<Node, Double>();
    Map<Node, Set<Node>> charPaths = new java.util.HashMap<Node, Set<Node>>(); 
    Map<Integer, Node> chars = new HashMap<Integer, Node>();
    Map<String, Integer> chars2 = new HashMap<String, Integer>();
    public CampusPaths(){
    	
    }

	/*
    @param the filename that we want to parse through to get the data
    @modifies this.Graph
    @effects fills in the Graph to then store all the datapoints in
    @throws printStackTrace(); if the file cant be found or is invalid
    */
    
    public void createNewGraph(String filename1, String filename2) {
		//System.out.println("HIII");
    	
		try { 
            
			CampusParser.readCharacters(filename1,charPaths, chars, chars2);
            CampusParser.readEdges(filename2,charPaths, chars);


            Set<Node> Characters1 = charPaths.keySet();
            for(Node Char: Characters1){
                graph.addNode(Char);
                for(Node CharPath: charPaths.get(Char)){
                    double edgeLabel = Math.sqrt(Math.pow((CharPath.getX()-Char.getX()),2) + Math.pow((CharPath.getY()-Char.getY()),2));
                    double angle = (double) Math.toDegrees(Math.atan2(CharPath.getY() - Char.getY(), CharPath.getX() - Char.getX()));
    
                    if(angle < 0){
                        angle += 360;
                    }
                    String direction = "blah";
                    if( 337.5 <= angle || angle < 22.5){
                        direction = "East";
                    }
                    if( 22.5 <= angle && angle < 67.5){
                        direction = "SouthEast";
                    }
                    if( 67.5 <= angle && angle < 112.5){
                        direction = "South"; 
                    }
                    if( 112.5 <= angle && angle < 157.5){
                        direction = "SouthWest";
                    }
                    if( 157.5 <= angle && angle < 202.5){
                         direction = "West";

                    }
                    if( 202.5 <= angle && angle < 247.5){
                        direction  = "NorthWest";
                    }
                    if( 247.5 <= angle && angle < 292.5){
                        direction = "North";
                    }
                    if( 292.5 <= angle && angle < 337.5){
                        direction = "NorthEast";
                    }

                    try{
                        graph.addEdge(Char, CharPath, CharPath, edgeLabel,direction );

                    }
                    catch(Exception e){
                        System.out.println("UHOHHHHHHHH we messed up\n big time");
                        e.printStackTrace();
                    }

                }

            }

			
			checkRep();
			//System.out.println(graph);
		} catch (IOException e) {
			System.out.println();
			e.printStackTrace();
		}	
    	
    }
    /*
    @param the start and the end node
    
    @return a string representation of the path
    
    */
    public String findPath(String Char1, String Char2){
		
		checkRep();
        Node start =null;
        Node dest = null;
        
        boolean building = false;
        boolean dest2Pass = true;
        try{
            start = chars.get(Integer.parseInt(Char1));
           
		    
        }
        catch(Exception e){
            start = chars.get(chars2.get(Char1));
            
        }
        try{
            dest = chars.get(Integer.parseInt(Char2));
        }
        catch(Exception e){
            dest = chars.get(chars2.get(Char2));

        }
        if(start!=null){
            if(start.getName().length() >=12 && start.getName().charAt(0) == 'I' && start.getName().charAt(1) == 'n' && start.getName().charAt(2) == 't'){
               start = null;
            }
        }
        if(dest!=null){
            if(dest.getName().length() >=12 && dest.getName().charAt(0) == 'I' && dest.getName().charAt(1) == 'n' && dest.getName().charAt(2) == 't'){
               dest = null;
            }
        }
        
        
        if(start == null){
            String finalValue =  "Unknown building: [" + Char1 + "]";
			
			if(Char1.equals(Char2)){
				checkRep();
				return finalValue;
			}
			if(dest == null){
				finalValue = finalValue + "\n" + "Unknown building: [" + Char2 + "]";
			}
			checkRep();
			return finalValue;
        }
        

        
		if(dest == null){
			String finalValue =  "Unknown building: [" + Char2 + "]";
			
			checkRep();
			return finalValue;
		}
        String finalValue = "";
        if(Char1.equals(Char2)){
			finalValue = finalValue + "Path from " + start + " to " + dest + ":\n";
			return finalValue + "Total distance: 0.000 pixel units.";
		}
        int h = start.compareTo(dest);
        Dijkstra<Node, Double> FindPaths = new Dijkstra<Node, Double>(graph);
        Node fixed = new Node();
		//String finalValue = "";
        Paths<Node,Double> DijPath = FindPaths.findPath(start, dest, 0.0, fixed, "distance");

        //finalValue = finalValue +  FindPaths.findPath(start, dest, 0.0, fixed, "distance");

        if(DijPath.getSize() == 0){
            return finalValue+ "There is no path from " + start.getName() +  " to " + dest.getName() +".";
        }
        finalValue = finalValue + "Path from " + start.getName() + " to " + dest.getName() +":\n";
        for(int i =1; i < DijPath.getSize(); i++){
            finalValue= finalValue + "\tWalk " + DijPath.getEdge(i).getDir();
            finalValue = finalValue + " to ";						
            finalValue = finalValue +  "(" +DijPath.getEdge(i).getNode() +")\n";					
                        
        }
        finalValue = finalValue + "Total distance: " + String.format("%.3f", DijPath.sum()) + " pixel units.";
        

		checkRep();
		return (finalValue);



	}




    private void checkRep(){
        if(graph == null){
			throw new RuntimeException("graph is null");
		}        
    }


    public static void main(String args[]){
        CampusPaths current = new CampusPaths();
        current.createNewGraph("data/RPI_map_data_Nodes.csv","data/RPI_map_data_Edges.csv");
        Scanner scanner = new Scanner(System.in);
        boolean next = true;
        String val = "";
        while(next){
            val = scanner.nextLine();
            if(val.equals("b")){
                ArrayList<String> temp = (new ArrayList<> (current.chars2.keySet()));
                Collections.sort(temp);
                for(String key: temp){
                    if(key.length() >=12 && key.charAt(0) == 'I' && key.charAt(1) == 'n' && key.charAt(2) == 't'){
                        //continue;
                    }
                    else{
                        System.out.print(key + "," + current.chars2.get(key) + "\n");
                    }
                    
                }
            }

            else if(val.equals("m")){

                System.out.println("b lists all buildings\nr prints directions for the shortest route between any two buildings\nq quits the program\nm prints a menu of all commands");
            }
            else if(val.equals("r")){

                System.out.print("First building id/name, followed by Enter: ");
                String Char1 = scanner.nextLine();
                System.out.print("Second building id/name, followed by Enter: ");
                String Char2 = scanner.nextLine();

                System.out.println(current.findPath(Char1, Char2));
            }

            else if(val.equals("q")){

                next = false;


            }
            else{
                System.out.println("Unknown option");
                
            }
        }

        

    }
}