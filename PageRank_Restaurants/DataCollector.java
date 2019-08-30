import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;



public class DataCollector {
    Graph g;
    LinkedList<Node> nodes;
    
    // from a name to a node
    HashMap<String, Node> names;
    int id;
    
    
    public DataCollector(String filename, String sugg) {
        id = 0;
        nodes = new LinkedList<Node>();
        names = new HashMap<String, Node>();
        
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String[] strings;
            String s;
            try {
                while ((s = in.readLine()) != null) {
                    strings = s.split(",");
                    boolean[] att = new boolean[22];
                    for (int i = 0; i < 22; i++) {
                        if (strings[i+5].equals("Y")) {
                            att[i] = true;
                        } else {
                            att[i] = false;
                        }  
                    }
                    
                    strings[2] = strings[2] + "," + strings[3] + "," + strings[4];
                    Node n = new Node(att, strings[0], strings[1], strings[2], strings[27], strings[28], id);
                    id++;
                    nodes.add(n);
                    names.put(strings[0].toLowerCase(), n);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        try {
            BufferedReader in = new BufferedReader(new FileReader(sugg));
            String[] strings1;
            String s;
            try {
                while ((s = in.readLine()) != null) {
                    strings1 = s.split(",");
                    Node n = names.get(strings1[0].toLowerCase());
                    
                    for (int i = 1; i < strings1.length; i++) {
                        if (!strings1[i].equals("null")) {
                            //System.out.println(i);
                            //System.out.println(strings1[i]);
                            //System.out.print(strings1[i]);
                            Node add = names.get(strings1[i].toLowerCase());
                            n.addSuggestions(add);
                        }
                    }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        g = new Graph(nodes);
    }
    
    
    public Graph getGraph() {
        return g;
    }
    
    public LinkedList<Node> getNodes() {
        return nodes;
    }
}
