import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        DataCollector d = new DataCollector("restaurantAttributes.txt", "restaurantSuggestions.txt");
        Graph g = d.getGraph();
        PageRank pr = new PageRank(g);
        pr.giveNodesRank();
        //System.out.println(pr.finalVectorToString());
        Node[] restaurantArray = pr.restaurants;
        HashMap<String, Integer> amenities = new HashMap<String, Integer>();
        
        /*System.out.println("Damping factor: " + pr.damping);
        System.out.println("Iterations: " + pr.matrixExp);
        System.out.println();
        
        for (int i = 0; i < finalPageRank.length; i++) {
            System.out.println(restaurantArray[i]);
            System.out.println(finalPageRank[i]);
        } */
        
        for (int i = 0; i < restaurantArray.length - 1; i++) {
            double max;
            max = restaurantArray[i].rank;
            for (int j = i + 1; j < restaurantArray.length; j++) {
                if (max < restaurantArray[j].rank) {
                    Node temp = restaurantArray[i];
                    restaurantArray[i] = restaurantArray[j];
                    restaurantArray[j] = temp;
                    max = restaurantArray[i].rank;
                }
            }
        }
        
        /*for (int i = 0; i < restaurantArray.length; i++) {
            System.out.println(restaurantArray[i].rank);
        }*/
        
        for (int i = 0; i < 22; i++) {
            String s = restaurantArray[0].indexToAmenity(i).toLowerCase();
            amenities.put(s, i);
            
        }
        
        
        while (true) {
            //ORDER NEW 
            Scanner input = new Scanner(System.in);
            System.out.println("Please type the number of the field you want to search by:");
            System.out.println("1. Food Type");
            System.out.println("2. Amenity");
            
            int x = input.nextInt();
            
            if (x == 1) {
                System.out.println("\nYou selected \"Filter by food type\"");
                System.out.println("Please type the food type you would like to filter by\n");
                System.out.println("Options:\n"
                        + "American, Seafood, Cafe, French, Vegan, Vegetarian,\n"
                        + "Bars, Mediterranean, Middle Eastern, Italian, sushi,\n"
                        + "sandwiches");
                
                input.nextLine();
                String s = input.nextLine();
            
                System.out.println("\nFood Type Selected: " + s);
                System.out.println("The Top 5 Restaurants You Should Try");
                
                int num = 1;
                int i = 0;
                while (num < 6 && i < restaurantArray.length) { 
                    Node rest = restaurantArray[i];
                    s= s.toLowerCase();
                    String f1 = rest.foodType1.toLowerCase();
                    String f2 = rest.foodType2.toLowerCase();
                    if (f1.equals(s) || f2.equals(s)) {
                        System.out.print(num + ". ");
                        System.out.println(restaurantArray[i]);
                        num++;
                    }
                    i++;
                }
                
                if (num == 0) {
                    System.out.println("Sorry! We could not find any restaurants that met "
                            + "your requirements.\n");
                }
                
                if (num < 6 && num != 0) { 
                    System.out.println("Sorry! We could not find 5 restaurants that met "
                            + "your requirements.\n"
                            + "We gave you our top " + (num - 1) + " choice(s) given your criteria");
                }
                
                System.out.println("Enter 1 to conduct a new search");
                int y = input.nextInt();
                while (y != 1) {
                    y =input.nextInt();
                }
                System.out.println("");
                
            } else if (x == 2) {
                System.out.println("\nYou selected \"Filter by amenity\"");
                System.out.println("Type the amenities would you like your restaurant to have");
                System.out.println("(Separate amenities with a comma and space. Ex: dinner, takeout)\n");
                System.out.println("Options:\n"
                        + "Reservations Delivery, Takeout, Credit Cards Accepted,\n"
                        + "Vegan Options, Outdoor Seating, Casual Dress, Average Noise,\n"
                        + "Good For Kids, Breakfast, Lunch, Dinner,\n"
                        + "Private Lot Parking Available, Bike Parking Available,\n"
                        + "Waiters Free Wi-Fi, Happy Hour, Wine/beer Served,\n"
                        + "Liquor served, Dogs allowed, TV, Loud");
                
                input.nextLine();
                String s = input.nextLine();
                String[] ammen = s.split(", ");
            
                System.out.println("\namenities Chosen: " + s + "\n");
                System.out.println("The Top 5 Restaurants You Should Try");
                
                int num = 1;
                int i = 0;
                while (num < 6 && i < restaurantArray.length) { 
                    Node rest = restaurantArray[i];
                    boolean print = true;
                    for (int k = 0; k < ammen.length; k++) {
                        s = ammen[k].toLowerCase();
                        int a = amenities.get(s);
                        if (!rest.hasAmenity(a)) {
                            print = false;
                        }
                    }
                    if (print) {
                        System.out.print(num + ". ");
                        System.out.println(restaurantArray[i]);
                        num++;
                    }
                    i++;
                } 
                
                if (num == 0) {
                    System.out.println("Sorry! We could not find any restaurants that met "
                            + "your requirements.\n");
                }
                
                if (num < 6 && num != 0) { 
                    System.out.println("Sorry! We could not find 5 restaurants that met "
                            + "your requirements.\n"
                            + "We gave you our top " + (num - 1) + " choices given your criteria");
                }
                
                System.out.println("Enter 1 to conduct a new search");
                int y = input.nextInt();
                while (y != 1) {
                    y =input.nextInt();
                }
                System.out.println("");
                
            }
        }
    }
}

