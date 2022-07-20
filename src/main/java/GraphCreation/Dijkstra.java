package GraphCreation;

import java.util.*;

public class Dijkstra <T extends Comparable<T>, E extends Number & Comparable<E>>{

    Graph<T,E> current;
    public Dijkstra(Graph<T,E> temp){
        current = temp;
    }


	/*
    @param the parent node, the destination node, 2 fixed default values and the type of path it is
    
    
    @return a path that goes through all the edges necessary to go to the destination
    */
    public Paths<T,E> findPath(T Char1, T Char2, E fixedValE, T fixedValT, String whatType){
		
		T start = Char1;
		T dest = Char2;
		
		PriorityQueue<Paths<T,E>> active = new PriorityQueue<>();
		ArrayList<Paths<T,E>> returnVals =  new ArrayList<>();

		List<T> finished = new ArrayList<T>();
		
		Edge<T, E> selfNode = new Edge<T, E>(start, fixedValT);

		selfNode.setEdgeWeight(fixedValE);
		Paths<T, E> first = new Paths<T,E>();
		first.addEdge(selfNode);
		active.add(first);
		String finalValue =  "";
		

		
		
		while(active.isEmpty() == false){
			Paths<T,E> minPath = active.poll();
			Edge<T,E> minDest = minPath.getLastEdge();
			
			if(minDest.getNode().equals(dest)){
				
				return minPath;
				//return finalValue;
			}
			if(finished.contains(minDest.getNode())){
				continue;
			}
			Iterator<Edge<T, E>> i = current.listChildrenEdge(minDest.getNode());
			
			while(i.hasNext()){
				
				Edge<T, E> temp = i.next();
				if(finished.contains(temp.getNode()) == false){
					Paths<T,E> newPath = new Paths<T,E>(minPath);
					newPath.addEdge(new Edge<T, E>(temp.getLabel(), temp.getNode(), temp.getEdgeWeight(), temp.getDir()));
					active.add(newPath);
				}
			}
			
			finished.add(minDest.getNode());

		}
		

		finalValue = finalValue + "There is no path from " + start + " to " + dest + ".";
		Paths<T,E> noPath = new Paths<>();

		return (noPath);



	}
}
