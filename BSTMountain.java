package project Mountain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class represents a binary search tree specialized for storing RestStop objects and performing search operations.
 * It maintains the tree structure and provides depth-first search functionality to generate paths in the mountain.
 * 
 * @author Leo Wu
 * @param <RestStop> The type of elements stored in the binary search tree.
 */
public class BSTMountain extends BST<RestStop>{
    /**
     * Perform a depth-first search (DFS) traversal on the binary search tree to generate all possible paths.
     * 
     * @return A List of List of RestStop objects representing all the paths in the mountain.
     */
    public List<List<RestStop>> dfs() {
        List<List<RestStop>> paths = new ArrayList<>();

        List<RestStop> path = new ArrayList<>();
        Iterator<RestStop> restStopIterator = preorderIterator();
        while(restStopIterator.hasNext()){
            RestStop next = restStopIterator.next();
            
            // Check if the current path has more than one element and if the next RestStop is in ascending order
            if(path.size()>1 && path.get(path.size()-1).compareTo(next)<0){
                paths.add(new ArrayList<>(path));
                List<RestStop> newPath = new ArrayList<>();
                // Construct a new path until finding a suitable insertion point for the next RestStop
                while(!path.isEmpty()){
                    RestStop remove = path.remove(0);
                    newPath.add(remove);
                    // Check if the current RestStop needs to be added before the next RestStop
                    if(!hasRightItem(remove,paths) && remove.compareTo(next)<0){
                        newPath.add(next);
                        path = newPath;
                        break;
                    }
                }

            } else {
                path.add(next);
            }
        }
        paths.add(path);
        return paths;
    }

    /**
     * Check if there is a RestStop object with a greater value than the provided node in any existing paths.
     * 
     * @param node The RestStop object to be compared with other RestStop objects in paths.
     * @param paths A List of List of RestStop objects representing paths in the mountain.
     * @return true if a RestStop object with greater value than the provided node exists in the paths, false otherwise.
     */
    private boolean hasRightItem(RestStop node, List<List<RestStop>> paths) {
        boolean result = false;  // Initialize the result to false

        // Iterate over each path in the list of paths
        for (List<RestStop> path : paths) {
            // Iterate over each RestStop in the current path
            for (int i = 0; i < path.size(); i++) {
                RestStop restStop = path.get(i);  // Current RestStop in the iteration
                // Check if the current RestStop is the node we are looking for and it is not the last in the path
                if (restStop == node && i != path.size() - 1) {
                    // Compare the next RestStop in the path with the current one to check if it's greater
                    if (path.get(i + 1).compareTo(restStop) > 0) {
                        result = true;  // Set result to true if the next RestStop is greater
                        break;  // Exit the inner loop since we found a match
                    }
                }
            }
            if (result) {
                break;  // Exit the outer loop if result is true
            }
        }
        return result;  // Return the result
    }
}
