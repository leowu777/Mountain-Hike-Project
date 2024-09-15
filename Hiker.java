package project Mountain;

import java.util.ArrayList;
import java.util.List;
/**
 * Represents a hiker who carries a list of supplies.
 * This class manages the supplies by allowing additions and removals.
 * 
 * @author Leo Wu
 */
public class Hiker {


    private List<String> supplies = new ArrayList<>();


    /**
     * Retrieves the list of supplies available to the hiker.
     * 
     * @return A List<String> containing the hiker's supplies.
     * 
    public List<String> getSupplies() {
        return supplies;
    }

   /**
    * Adds a supply item to the hiker's list of supplies. 
    * This method allows dynamic addition of supplies to the hiker's inventory.
    * 
    * @param supply The supply item to be added. It should not be null.
    */
    public void addSupply(String supply) {
        this.supplies.add(supply);
    }


   /**
    * Removes a specific supply item from the hiker's list of supplies, if it exists.
    * This method iterates through the list of supplies and removes the first occurrence
    * of the specified item.
    * 
    * @param supply The supply item to be removed.
    * @return true if the supply was successfully found and removed, false otherwise.
    */
    public boolean expendSupply(String supply) {
        // Iterate through the list of supplies
        for (int i = 0; i < supplies.size(); i++) {
            // Check if the current supply in the list matches the supply to be removed
            if (supplies.get(i).equals(supply)) {
                supplies.remove(i);  // Remove the supply from the list
                return true;  // Return true indicating successful removal
            }
        }
        return false;  // Return false if the supply was not found in the list
    }
}
