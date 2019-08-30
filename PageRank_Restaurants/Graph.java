import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;


public class Graph { 
    
    HashMap<Node, LinkedList<Node>> adj;
    
    public Graph(LinkedList<Node> n) {
        adj = new HashMap<Node, LinkedList<Node>>();
        
        while (!n.isEmpty()) {
            Node first = n.removeFirst();
            adj.put(first, first.getSuggestions());
        }
    }
    
    public int getSize() {
        return adj.size();
    }
    
    public HashMap<Node, LinkedList<Node>> getMap() {
        return adj;
    }
    
    /**
     * from node u to node v
     * @param u
     * @param v
     * @return
     */
    public boolean hasEdge(Node u, Node v) {
        if (!adj.containsKey(u) || !adj.containsKey(v)) {
            throw new IllegalArgumentException();
        }
        
        if (u.getSuggestions().isEmpty()) {
            return false;
        }
        
        LinkedList<Node> nodes = adj.get(u);
        
        for (Node n : nodes) {
            if (n.getID() == v.getID()) {
                return true;
            }
        }
       
        return false;
    }
    
    public boolean addEdge(Node u, Node v) {
        if (!adj.containsKey(u) || !adj.containsKey(v)) {
            throw new IllegalArgumentException();
        }

        if (hasEdge(u, v)) {
            return false;
        }
        
        u.addSuggestions(v);
        
        LinkedList<Node> l = adj.get(u);
        
        l.add(v);
        adj.replace(u, l);
        
        return true;
    }
    
    
    public LinkedList<Node> getOutNeighbors(Node node) {
        if (node.getID() < 0 || node.getID() >= adj.size()) {
            throw new IllegalArgumentException();
        }
        return adj.get(node);
    }
    
    
    public int outDegree(Node node) {
        return getOutNeighbors(node).size();
    }
    
}
