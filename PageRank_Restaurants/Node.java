import java.util.LinkedList;
import java.util.List;

public class Node {
    int id;
    String name;
    String headChef;
    String description;
    double rank;
    // can also store this as a JSoup document
    String foodType1;
    String foodType2;
    
    List<String> hours;
    String phoneNumber;
    String address;
    LinkedList<Node> suggestions = new LinkedList<Node>();
    
    boolean[] amenities;
    // WHAT IS ORDER
    // [Reservations, Delivery, Takeout, Credit Cards, Apple Pay, Vegan Options, Outdoor Seating,
    //      Dress ATTIRE(casual, classy), Noise Level, Good for kids, meal type (lunch, dinner, breakfast),
    //      Good for dancing, Pool table, Private Lot Parking, Bike Parking, Waiters, Free Wi-Fi,
    //      Best Nights of the week to go(split into 7), Happy Hour, Alcohol(beer, wine, liquor?), smoking allowed,
    //      Dogs allowed, wheelchair accessible, TV]
    
    
    LinkedList<Integer> vertices = new LinkedList<Integer>();
    
    /**
     * don't need a description
     * @param rank
     * @param name
     * @param headChef
     */
    public Node(int num) {
        id = num;
            }
    
    public Node(boolean[] amenities, String name, String phoneNumber, String address, 
            String foodType1, String foodType2, int id) {
        this.phoneNumber = phoneNumber;
        this.amenities = amenities;
        this.name = name;
        this.address = address;
        this.foodType1 = foodType1;
        if (foodType2.equals("null")) {
            this.foodType2 = "null";
        } else {
            this.foodType2 = foodType2;
        }
        this.id = id;
    }
    
    
    public void addSuggestions(Node n) {
        suggestions.add(n);
    }
    
    public int getID() {
        return id;
    }
    
    public void changeRank(double rank) {
        this.rank = rank;
    }
        
    
    public String indexToAmenity(int index) {
        // [Reservations 0, Delivery 1, Takeout 2, Credit Cards 3, Apple Pay 4, Vegan Options 5, Outdoor Seating 6,
        //      casual 7, fancy 8, Quiet 9, Good for kids 10, breakfast 11, lunch 12, dinner 13,
        //      Good for dancing 14, Pool table 15, Private Lot Parking 16, Bike Parking 17, Waiters 18, Free Wi-Fi 19,
        //      Happy Hour 20, Alcohol served 21, liquor served 22, smoking allowed 23,
        //      Dogs allowed 24, wheelchair accessible 25, TV 26, Loud 27]
        // can get all this info from Yelp
            
        if (index < 0 || index >= amenities.length) {
            throw new IllegalArgumentException("invalid index for an amenity");            
        }
            
        // when return a list of amenities for a restaurant, only return the strings from 
        // indices that are true
            
        String amenity;
        switch (index) { 
        case 0: 
            amenity = "Reservations"; 
            break; 
        case 1: 
            amenity = "Delivery"; 
            break; 
        case 2: 
            amenity = "Takeout"; 
            break; 
        case 3: 
            amenity = "Credit Cards Accepted"; 
            break; 
        case 4: 
            amenity = "Vegan Options"; 
            break; 
        case 5: 
            amenity = "Outdoor Seating"; 
            break; 
        case 6: 
            amenity = "Casual Dress"; 
            break; 
        case 7: 
            amenity = "Average Noise"; 
            break; 
        case 8: 
            amenity = "Good For Kids"; 
            break; 
        case 9: 
            amenity = "Breakfast"; 
            break; 
        case 10: 
            amenity = "Lunch"; 
            break; 
        case 11: 
            amenity = "Dinner"; 
            break; 
        case 12: 
            amenity = "Private Lot Parking Available"; 
            break; 
        case 13: 
            amenity = "Bike Parking"; 
            break; 
        case 14: 
            amenity = "Waiters"; 
            break; 
        case 15: 
            amenity = "Free Wi-Fi"; 
            break; 
        case 16:
            amenity = "Happy Hour"; 
            break; 
        case 17:
            amenity = "Wine/Beer Served"; 
            break; 
        case 18:
            amenity = "Liquor Served"; 
            break; 

            ///
            
            
        case 19:
            amenity = "Dogs Allowed"; 
            break; 

            ///
            
        case 20:
            amenity = "TV"; 
            break; 
        case 21:
            amenity = "Loud"; 
            break; 
                
        default: 
            amenity = "SOMETHING MESSED UP"; 
            break; 
        } 
            
        return amenity;
            
    }
        
    public void changeAmenityArray(int index, boolean input) {
        amenities[index] = input;
            
    }
    
    public boolean hasAmenity(int index) {
        return amenities[index];
            
    }
    
    public LinkedList<Node> getSuggestions() {
        return suggestions;
    }
        
    public LinkedList<Integer> getVertices() {
        return vertices;
    }
        
    public String toString() {
        StringBuilder ans = new StringBuilder();
        ans.append("Restaurant: " + name + System.lineSeparator());
        ans.append("Address: " + address + System.lineSeparator());
        ans.append("Phone Number: " + phoneNumber + System.lineSeparator());
        if (foodType2 != "null") {
            ans.append("Food Type: " + foodType1 + ", " + foodType2 + System.lineSeparator());
        } else {
            ans.append("Food Type: " + foodType1 + System.lineSeparator());
        }
        
        ans.append("Amenities: " + System.lineSeparator());
        for (int i = 0; i < amenities.length; i++) {
            if (amenities[i]) {
                ans.append(indexToAmenity(i) + System.lineSeparator());
            }
        }
        
        return ans.toString();
    }
        
    public boolean equals(Node n) {
        if (n.getID() == this.id) {
            return true;
        }
        return false;
    }
    
    
        
       
    public int compareTo(Node n) {
        return this.id - n.getID();
    }
} 

