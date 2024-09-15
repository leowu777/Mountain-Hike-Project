# Mountain Hike Simulation

## Overview

This project simulates a mountain hiking expedition using a custom Binary Search Tree (BST) to represent the mountain. Each node in the BST represents a rest stop, with unique supplies and obstacles. The goal of the simulation is to determine all feasible paths for a hiker to successfully descend from the summit to the base while managing resources effectively.

## Features

- **Binary Search Tree (BST) Representation**: The mountain is structured as a BST, where each rest stop (node) contains data about available supplies and potential obstacles.
- **Hiker Navigation**: The hiker must navigate through the tree, deciding on the best path based on the available supplies and obstacles at each rest stop.
- **Custom Classes**:
  - `BST`: Manages the tree structure of the mountain.
  - `MountainClimb`: Implements the main functionality and control flow for the simulation.
  - `RestStop`: Represents the various attributes of each stop, such as supplies and obstacles.
  - `Hiker`: Simulates the decision-making process of a hiker based on the resources at each rest stop.
- **Robust Pathfinding**: Implements an algorithm to find all feasible paths from the summit to the base of the mountain.

## Project Structure

The project consists of the following key classes:

- **BST.java**: Implements the core data structure, with operations to add, search, and navigate through the rest stops in the mountain.
- **BSTMountain.java**: Extends `BST` to customize the structure and behavior specifically for representing the mountain.
- **RestStop.java**: Represents an individual rest stop with attributes such as supplies (food, water) and obstacles (animals, fallen trees).
- **Hiker.java**: Simulates the hiker's journey by deciding which path to take based on the resources available at each stop.
- **MountainClimb.java**: Coordinates the overall simulation, processing user input, and determining feasible paths down the mountain.

## How to Run

1. **Compilation**: Ensure you have a Java compiler installed. You can compile the project with the following command:
   ```bash
   javac *.java
   ```

2. **Execution**: After compiling, run the `MountainClimb` class, which serves as the main entry point for the program:
   ```bash
   java MountainClimb
   ```

3. **Input**: The program accepts input that defines the rest stops on the mountain, including available supplies and obstacles. The hiker will attempt to descend the mountain based on the given input, using the custom BST to determine possible paths.

## Key Concepts

- **Binary Search Tree (BST)**: The project uses a custom BST to represent the mountain, where each node (rest stop) is connected to its left and right children based on some comparison, enabling efficient navigation.
- **Pathfinding**: The core challenge in the simulation is to navigate through the mountain's BST structure, ensuring the hiker has enough supplies to survive and overcome obstacles along the way.
- **Simulation Robustness**: The program includes comprehensive error handling to manage invalid inputs and unexpected situations that could occur during runtime.

## Example

An example of input might define rest stops as follows:

- Rest stop at the summit with water and food but has an obstacle (fallen tree).
- Rest stop halfway down with only water and no obstacles.
- Base with no supplies or obstacles.

The program will evaluate paths from the summit to the base, taking into account available resources and obstacles.

## Future Improvements

- **Enhanced Visualization**: Integrating a graphical representation of the mountain and the hiker's progress through the tree.
- **Advanced AI for Pathfinding**: Implementing more sophisticated decision-making algorithms for the hiker based on terrain and supply factors.
- **Dynamic Terrain Generation**: Automatically generate random mountain structures for more varied simulation experiences.

## Requirements

- Java 8 or later
- Terminal or command-line interface for compiling and running the project

## License

This project is open-source and available under the MIT License.
