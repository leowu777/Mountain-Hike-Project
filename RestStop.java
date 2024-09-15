package project Mountain;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a rest stop on a trail, storing supplies and a label.
 * Each RestStop object contains a list of supplies that could include food, 
 * tools, or natural features encountered at the stop.
 * 
 * @author Leo Wu
 */
public class RestStop implements Comparable<RestStop>{


    private String label ;

    private List<String> supplies = new ArrayList<String>();


   /**
    * Constructs a new RestStop object using the provided array of strings representing supplies.
    * The first element of the array is used as the label for the rest stop.
    * The supplies are parsed from the array according to specific rules.
    * @param arr an array of strings representing supplies, where the first element is the label of the rest stop
    */
    public RestStop(String[] arr) {
    this.label = arr[0]; // Set the label of the rest stop
    boolean isEnd = false; // Flag to track if the stop is the end of the journey
    for (int i = 1; i < arr.length; i++) {
        String supply = arr[i].trim(); // Trim the whitespace from the supply string
        // Check if the stop is the end of the journey
        if(isEnd){
            // If the stop is the end, only "river" supply can be added
            if("river".equals(supply)){
                supplies.add(supply); // Add "river" supply
            }else if("fallen".equals(supply)){
                // If the stop is the end and followed by "fallen" supply, check for "tree" supply
                if(i+1 < arr.length){
                    i++;
                    if("tree".equals(arr[i].trim())){
                        supplies.add(supply+" "+arr[i].trim()); // Add "fallen tree" supply
                    }
                }
            }
        }else{
            // If the stop is not the end
            if("food".equals(supply) || "raft".equals(supply) || "axe".equals(supply) ||"river".equals(supply)){
                // Add common supplies and mark the stop as the end if it has "river" supply
                supplies.add(supply);
                if("river".equals(supply)){
                    isEnd = true;
                }
            }else if("fallen".equals(supply)){
                // If the stop has "fallen" supply, check for "tree" supply and mark it as the end
                if(i+1 < arr.length){
                    i++;
                    if("tree".equals(arr[i].trim())){
                        supplies.add(supply+" "+arr[i]);
                        isEnd = true; // Mark the stop as the end
                    }
                }
            }
        }
    }
}


   /**
    * Compares this rest stop with another based on their labels.
    * @param o the rest stop to be compared
    * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
    */
    @Override
    public int compareTo(RestStop o) {
        return this.label .compareTo(o.label );
    }


   /**
    * Gets the label of the rest stop.
    * @return the label of the rest stop
    */
    public String getLabel() {
        return label;
    }


   /**
    * Gets the list of supplies available at the rest stop.
    * @return the list of supplies available at the rest stop
    */
    public List<String> getSupplies() {
        return supplies;
    }


   /**
    * Returns the label of the rest stop when converted to a string.
    * @return the label of the rest stop
    */
    @Override
    public String toString() {
        return label;
    }
}
