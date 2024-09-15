package project Mountain;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * The MountainClimb class simulates a mountain climbing scenario where a hiker must navigate a mountain
 * using resources found along the way. The program reads an input file detailing paths and resources on the mountain,
 * then determines if a successful path exists.
 * 
 * @author Leo Wu
 */
public class MountainClimb {

    public static void main(String[] args)  {
        // Check if the correct number of command line arguments is provided
        if (args.length < 1) {
            System.err.println("Usage: java Simulation <input file> ");
            System.exit(1);
        }

        // Check if the input file exists
        File fileMap = new File(args[0]);
        if (!fileMap.exists()) {
            System.err.println("File not found: " + args[0]);
            System.exit(1);
        }

        // Create an instance of BSTMountain to represent the mountain
        BSTMountain mountain = new BSTMountain();

        try {
            // Read from the input file
            BufferedReader reader = new BufferedReader(new FileReader(fileMap));
            String line;
            // Split the line by space and add each segment as a RestStop to the mountain
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] arr = line.split(" ");
                    mountain.add(new RestStop(arr));
                }
            }
        } catch (IOException e) {
            // Handle file reading errors
            System.err.println("Read file error: " + args[0]);
            System.exit(1);
        }

        // Perform DFS traversal on the mountain to get all possible paths
        List<List<RestStop>> paths = mountain.dfs();
        for (List<RestStop> path : paths) {
            if(path.size() == mountain.height()){
                Hiker hiker = new Hiker();
                boolean flag = true;
                String result = "";
                // Traverse each RestStop in the path
                for (int i = 0; i < path.size(); i++) {
                    RestStop restStop = path.get(i);
                    result+=restStop.getLabel()+" ";
                    // Check if hiker has enough food supply for the journey
                    if(i!=0){
                        if (!hiker.expendSupply("food")) {
                            flag = false;
                            break;
                        }
                    }
                    // Check and manage different supplies encountered at RestStops
                    for (String supply : restStop.getSupplies()) {
                        if(supply.equals("river")){
                            if (!hiker.expendSupply("raft")) {
                                flag = false;
                                break;
                            }
                        }else if(supply.equals("fallen tree")){
                            if (!hiker.expendSupply("axe")) {
                                flag = false;
                                break;
                            }
                        }else{
                            hiker.addSupply(supply);
                        }
                    }
                }
                // Display the result if hiker has successfully completed the path
                if(flag) {
                    System.out.println(result);
                }
            }
        }
    }
}
