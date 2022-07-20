package CampusPaths1;

import java.util.*;
import java.io.*;

public class CampusParser {
    /*
    @param the filename that we want to parse through to get the data the char paths to see the paths and the chars2 that will then associate name to the id
    @modifies charpaths and chars and chars2
    @effects fills in the the parameters sent to the method
    
    */
    public static void readCharacters(String filename, Map<Node, Set<Node>> charPaths, Map<Integer, Node> chars,Map<String, Integer> chars2)
                throws IOException {
                    

            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line = null;

                while ((line = reader.readLine()) != null) {
                    int i = line.indexOf(",");
                   
                    String[] res = line.split("[,]", 0);
                    if(res[0].equals("")){
                        res[0] = "Intersection " + res[1];
                    }
                    
                    
                    Node character = new Node(res[0], res[1], res[2], res[3]);

                    //String character = line.substring(1, i);
                    //String book = line.substring(i + 1, line.length() - 1);

                    // Adds the character to the character set. If character is already in, add has
                    // no effect.
                    chars.put(character.getID(), character);
                    chars2.put(character.getName(), character.getID());

                    

                    // Adds the character to the set for the given book
                    Set<Node> s = charPaths.get(character);
                    s = new HashSet<Node>();
                    charPaths.put(character, s);
                    
                    
                }
            }
        }
       /*
    @param the filename that we want to parse through to get the data the char paths to see the paths and the chars2 that will then associate name to the id
    @modifies charpaths and chars and chars2
    @effects fills in the edges of the nodes
    
    */
        public static void readEdges(String filename, Map<Node, Set<Node>> charPaths, Map<Integer, Node> chars)
        throws IOException {
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line = null;

                while ((line = reader.readLine()) != null) {
                    int i = line.indexOf(",");
                   
                    String[] res = line.split("[,]", 0);
                    
                    
                    
                    
                    Node parent = chars.get(Integer.parseInt(res[0]));
                    Node child = chars.get(Integer.parseInt(res[1]));

                    charPaths.get(parent).add(child);
                    charPaths.get(child).add(parent);

                    
                }
            }
        }
}