import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import java.util.Set;
import java.util.TreeSet;
import org.junit.Test;
import java.util.ArrayList;
import java.util.LinkedList;

public class DataText {

    DataCollector d = new DataCollector("rest.txt", "sugg.txt");
    @Test
    public void simple() {
        LinkedList<Node> n = d.getNodes();
        while (!n.isEmpty()) {
            Node node = n.remove();
            System.out.println();
            System.out.println(node.toString());
            LinkedList<Node> s = node.getSuggestions();
            while (!s.isEmpty()) {
                System.out.println(s.remove().name);
            }
            
        }
        
        
        
    }
}
