package project5;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Represents a binary search tree (BST) that organizes elements according to their natural ordering.
 * Provides functionality to add, remove, and query elements efficiently if they implement the {@link Comparable} interface.
 * Supports different tree traversal iterators (inorder, preorder, postorder).
 *
 * @author Leo Wu
 * @param <E> the type of elements maintained by this tree; the type must be comparable to itself.
 */
public class  BST<E extends Comparable<E>> implements Iterable<E>{

   /**
    * Node class representing elements of the binary search tree.
    */
    private class Node {
        E data;
        Node left;
        Node right;

        int height;

        Node(E data) {
            this.data = data;
            left = null;
            right = null;
            height = 0;
        }
    }

   /**
    * Iterator implementation for inorder traversal of the binary search tree.
    */
    private class InOrderIterator implements Iterator<E> {
        private Stack<Node> stack;

        InOrderIterator() {
            stack = new Stack<>();
            pushLeftChildren(root);
        }
       

       /**
        * Pushes left children of a given node onto the stack.
        * @param node The node whose left children are to be pushed onto the stack.
        */
        private void pushLeftChildren(Node node) {
            // Iterate down the left side of the tree starting from the node provided
            while (node != null) {
                stack.push(node);  // Push the current node onto the stack
                node = node.left;  // Move to the left child of the current node
            }
        }

        
       /**
        *Returns true if there are more elements in the iteration.
        *@return true if the iteration has more elements, false otherwise 
        */ 
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }


       /**
        * Returns the next element in the iteration.
        *
        * @return the next element in the iteration
        * @throws NoSuchElementException if the iteration has no more elements
        */
        @Override
        public E next() {
            // Check if there are more elements to traverse.
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            // Pop the top node from the stack which represents the current node being processed.
            Node current = stack.pop();

            // Push all left children of the right child of the current node onto the stack.
            // This ensures that these nodes are processed in the correct order.
            pushLeftChildren(current.right);

            // Return the data of the current node.
            return current.data;
        }
    }


   /**
    * Iterator implementation for preorder traversal of the binary search tree.
    */
    private class PreOrderIterator implements Iterator<E> {
        private Stack<Node> stack;

        public PreOrderIterator() {
            stack = new Stack<>();  // Initialize a new empty stack

            // Check if the binary tree’s root node is not null, initiating the traversal process
            if (root != null) {
                stack.push(root);  // Push the root node onto the stack
            }
        }



       /**
        *Returns true if there are more elements in the iteration.
        *@return true if the iteration has more elements, false otherwise 
        */ 
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }


       /**
        * Returns the next element in the iteration.
        *
        * @return the next element in the iteration
        * @throws NoSuchElementException if the iteration has no more elements
        */
        @Override
        public E next() {
            // Check if there are more elements to traverse.
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            // Pop the top node from the stack which represents the current node being processed.
            Node current = stack.pop();

            // If the current node has a right child, push it onto the stack to be processed later.
            if (current.right != null) {
                stack.push(current.right);
            }
            // If the current node has a left child, push it onto the stack to be processed next.
            if (current.left != null) {
                stack.push(current.left);
            }

            // Return the data of the current node.
            return current.data;
        }
    }


   /**
    * Iterator implementation for postorder traversal of the binary search tree.
    */
    private class PostOrderIterator implements Iterator<E> {
        private Stack<Node> stack1;
        private Stack<Node> stack2;

        public PostOrderIterator() {
            // Initialize two stacks to manage the nodes during traversal
            stack1 = new Stack<>();
            stack2 = new Stack<>();

            // Begin from the root node, assuming 'root' is the root of the binary tree
            if (root != null) {
                stack1.push(root); // Push the root onto the first stack
            }

            // Process all nodes until the first stack is empty
            while (!stack1.isEmpty()) {
                Node current = stack1.pop(); // Pop the top node from the first stack
                stack2.push(current); // Push it onto the second stack to reverse the order later

                // If the current node has a left child, push it onto the first stack
                if (current.left != null) {
                    stack1.push(current.left);
                }
                
                // If the current node has a right child, push it onto the first stack
                if (current.right != null) {
                    stack1.push(current.right);
                }
            }
        }


       /**
        *Returns true if there are more elements in the iteration.
        *@return true if the iteration has more elements, false otherwise 
        */ 
        @Override
        public boolean hasNext() {
            return !stack2.isEmpty();
        }


       /**
        * Returns the next element in the iteration.
        *
        * @return the next element in the iteration
        * @throws NoSuchElementException if the iteration has no more elements
        */
        @Override
        public E next() {
            // Check if there are more elements to traverse by verifying the secondary stack is not empty.
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            // Pop the top node from the secondary stack, stack2, which stores nodes in the order they should be visited.
            return stack2.pop().data;
        }
    }

    /**
     * root element
     */
    private Node root;

    /**
     * the size of tree
     */
    private int size;



    /**
     * Constructs a new, empty tree, sorted according to the natural ordering of its elements.
     * All elements inserted into the tree must implement the Comparable interface.
     */
    public BST() {
        root = null;
    }

    /**
     * Constructs a new tree containing the elements in the specified collection array, sorted according to the natural ordering of its elements.
     * All elements inserted into the tree must implement the Comparable interface.
     * @param collection collection whose elements will comprise the new tree
     * @throws  if the specified collection is null
     */
    public BST(E[] collection) throws NullPointerException {
        // Call the default constructor to initialize any necessary properties
        this();
        
        // Check if the collection array is null or empty and throw an exception if true
        if (collection == null || collection.length == 0) {
            throw new NullPointerException("The array of elements cannot be null or empty.");
        }
        
        // Iterate over the array of elements
        for (int i = 0; i < collection.length; i++) {
            // Add each element from the array to the BST
            add(collection[i]);
        }
    }


    /**
     * Adds the specified element to this set if it is not already present. More formally, adds the specified element e to this tree if the set contains no element e2 such that Objects.equals(e, e2).
     * If this set already contains the element, the call leaves the set unchanged and returns false.
     * @param e element to be added to this set
     * @return true if this set did not already contain the specified element
     * @throws  if the specified element is null and this set uses natural ordering, or its comparator does not permit null elements
     */
    public boolean add(E e) throws NullPointerException {
        // Check if the element passed is null and throw an exception if true
        if (e == null) {
            throw new NullPointerException("Cannot add a null element to the BST.");
        }
        
        // Check if the root is null, indicating the tree is currently empty
        if (root == null) {
            // Create a new node with the element, setting it as the root of the tree
            root = new Node(e);
            // Increment the size of the BST as a new element has been added
            size++;
            // Set the height of the root node, assuming a height-balanced tree like an AVL
            root.height = 1;
            return true; // Return true as the element has been successfully added
        }
        
        // If the tree is not empty, use a recursive method to insert the element
        return insertRecursive(root, e);
    }

    /**
     * Removes the specified element from this tree if it is present. More formally, removes an element e such that Objects.equals(o, e),
     * if this tree contains such an element. Returns true if this tree contained the element
     * @param o  object to be removed from this set, if present
     * @return true if this set contained the specified element
     * @throws NullPointerException if the specified element is null
     * @throws ClassCastException  if the specified object cannot be compared with the elements currently in this tree
     */
    public boolean remove(Object o) throws NullPointerException, ClassCastException {
        // Check if the passed object is null and throw an exception if true
        if (o == null) {
            throw new NullPointerException("Cannot remove a null object from the BST.");
        }

        // Check if the root is null, implying the tree is empty, and return false as there's nothing to remove
        if (root == null) {
            return false;
        }

        // Ensure the class of the object matches the class of the data in the root of the BST
        if (o.getClass() != root.data.getClass()) {
            throw new ClassCastException("The object type does not match the elements in the BST.");
        }

        // Record the size of the BST before attempting to remove the object
        int oldSize = size;

        // Attempt to remove the object using a recursive method and update the root of the tree
        root = removeRecursive(root, (E) o);

        // Return true if the size of the BST has decreased, indicating a successful removal
        return size < oldSize;
    }

    /**
     * Removes all of the elements from this set. The set will be empty after this call returns.
     */
    public void clear(){
        root = null;
        this.size = 0;
    }

    /**
     *  Returns true if this set contains the specified element. More formally, returns true if and only if this set contains an element e such that Objects.equals(o, e).
     * @param o  object to be checked for containment in this set
     * @return true if this set contains the specified element
     * @throws NullPointerException  if the specified element is null and this set uses natural ordering, or its comparator does not permit null elements
     * @throws ClassCastException if the specified object cannot be compared with the elements currently in the set
     */
    public boolean contains(Object o) throws NullPointerException, ClassCastException {
        // Check if the passed object is null and throw an exception if true
        if (o == null) {
            throw new NullPointerException("The object to check for presence cannot be null.");
        }
        
        // Check if the root is null, implying the tree is empty, and return false
        if (root == null) {
            return false;
        }
        
        // Check if the class of the object matches the class of the data in the root of the BST
        if (o.getClass() != root.data.getClass()) {
            throw new ClassCastException("The object type does not match the elements in the BST.");
        }
        
        // Use a recursive method to check if the object is contained within the tree
        return containsRecursive(root, (E)o);
    }

    /**
     * Returns the first (smallest) element currently in this tree.
     * @return the first (smallest) element currently in this tree
     * @throws NoSuchElementException  if this set is empty
     */
    public E first() throws NoSuchElementException{
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return iterator().next();
    }

    /**
     *  Returns the last (largest) element currently in this tree.
     * @return the last (largest) element currently in this tree
     * @throws NoSuchElementException if this set is empty
     */
    public E last() throws NoSuchElementException {
        // Check if the collection is empty and throw an exception if true
        if (isEmpty()) {
            throw new NoSuchElementException("The collection is empty.");
        }
        
        // Create an iterator to traverse the collection
        Iterator<E> iterator = iterator();
        
        // Variable to hold the last element encountered in the iteration
        E last = null;
        
        // Iterate through the collection until the end
        while (iterator.hasNext()) {
            last = iterator.next(); // Update 'last' to the current element
        }
        
        // Return the last element encountered
        return last;
    }


   /**
    * Compares this binary search tree with the specified object for equality. Returns true if the given object 
    * represents a BST instance with the same elements in the same order. This method recursively compares each 
    * node of the tree.
    *
    * @param o the object to be compared for equality with this tree
    * @return true if the specified object is equal to this tree
    */
    public boolean equals(Object o) {
        // Check if the current instance and the object to compare are the same
        if (this == o) {
            return true;
        }
        
        // Check if the object is null or if the classes of the two objects do not match
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        // Cast the object to the same type of BST
        BST<E> other = (BST<E>) o;
        
        // Use a recursive method to compare the trees starting from the root nodes
        return equalsRecursive(root, other.root);
    }


   /**
    * Retrieves the element at the specified position in this tree based on inorder traversal. This method 
    * throws an {@code IndexOutOfBoundsException} if the index is out of range (index < 0 || index >= size).
    *
    * @param index the index of the element to return
    * @return the element at the specified position in this tree
    * @throws IndexOutOfBoundsException if the index is out of range
    */
    public E get(int index) throws IndexOutOfBoundsException {
        // Check if the index is out of bounds
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
        }
        
        // Create an iterator to traverse the collection
        Iterator<E> iterator = iterator();
        
        // Initialize a counter to track the current position in the collection
        int i = 0;
        
        // Iterate through the collection to find the element at the specified index
        while (iterator.hasNext()) {
            E next = iterator.next();
            if (i == index) {
                return next; // Return the element when the index matches the position
            }
            i++; // Increment the position counter
        }
        
        // Return null if the element is not found (this should not happen if index checks are correct)
        return null;
    }


   /**
    * Returns the height of the tree, which is defined as the number of edges in the longest path from the root 
    * to a leaf node. If the tree is empty, the height is 0.
    *
    * @return the height of the tree, or 0 if the tree is empty
    */
    public int height(){
        return root == null ? 0 : root.height;
    }

    /**
     * Returns true if this set contains no elements.
     * @return true if this set contains no elements
     */
    public boolean isEmpty(){
        return root == null;
    }

    /**
     * Returns an iterator over the elements in this tree in ascending order.
     * @return an iterator over the elements in this set in ascending order
     */
    public Iterator<E> iterator() {
        return new InOrderIterator();
    }

    /**
     * Returns an iterator over the elements in this tree in order of the preorder traversal.
     * @return an iterator over the elements in this tree in order of the preorder traversal
     */
    public Iterator<E> postorderIterator() {
        return new PostOrderIterator();
    }

    /**
     * Returns an iterator over the elements in this tree in order of the postorder traversal.
     * @return an iterator over the elements in this tree in order of the postorder traversal
     */
    public Iterator<E> preorderIterator() {
        return new PreOrderIterator();
    }

    /**
     * Returns the number of elements in this tree.
     * @return the number of elements in this tree
     */
    public int size() {
        return size;
    }

    /**
     * Returns a string representation of this tree. The string representation consists of a list of the tree's elements in the order they are returned by its iterator (inorder traversal), enclosed in square brackets ("[]").
     * Adjacent elements are separated by the characters ", " (comma and space). Elements are converted to strings as by String.valueOf(Object).
     * @return a string representation of this collection
     */
    public String toString() {
        // Create an iterator to traverse through the elements of the data structure
        Iterator<E> iterator = iterator();
        // Start the string representation with an opening bracket
        String result = "[";
        // Loop through all elements in the data structure using the iterator
        while (iterator.hasNext()){
            // Append each element to the result string, followed by a comma
            result += String.valueOf(iterator.next()) + ",";
        }
        // Check if the data structure is not empty to avoid removing a comma from an empty string
        if (!isEmpty()){
            // Remove the last comma to correct the string format before closing bracket
            result = result.substring(0, result.length() - 1);
        }
        // Append a closing bracket to complete the string representation
        result += "]";
        // Return the final string representation of the data structure
        return result;
    }


    /**
     * Returns a string representation of this tree. The string representation consists of a list of the tree's elements in the order they are returned by its
     * iterator (inorder traversal), enclosed in square brackets ("[]"). Adjacent elements are separated by the characters ", " (comma and space).
     * Elements are converted to strings as by String.valueOf(Object).
     * @return a string representation of this collection
     */
    public String toStringTreeFormat() {
        StringBuilder result = new StringBuilder();
        toStringTreeFormatRecursive(root, result, 0);
        return result.toString();
    }


   /**
    * Helper method to create a string representation of the binary search tree in a tree format.
    * This method appends each node's data to the StringBuilder along with indentations based on the node's level in the tree.
    *
    * @param node the current node being processed
    * @param builder the StringBuilder to which the tree format is being built
    * @param level the depth level of the current node, used for indentation
    */
    private void toStringTreeFormatRecursive(Node node, StringBuilder builder, int level) {
        // Base case: if the current node is null, just return
        if (node == null) {
            return;
        }
        // Append spaces for each level to indent the node's value, creating a visual tree structure
        for (int i = 0; i < level; i++) {
            builder.append("   "); // Append 3 spaces for each level of depth
        }
        // Append the node's data prefixed with |-- to signify branch and start a new line
        builder.append("|--").append(node.data).append("\n");

        // Recursively call the function for both left and right children if they exist
        if (node.left != null || node.right != null) {
            toStringTreeFormatRecursive(node.left, builder, level + 1); // Process left subtree
            toStringTreeFormatRecursive(node.right, builder, level + 1); // Process right subtree
        } else {
            // If node is a leaf, append placeholder for visual completeness
            for (int i = 0; i < level + 1; i++) {
                builder.append("   "); // Append spaces for indentation
            }
            builder.append("|--null\n"); // Indicate that there are no children in this part of the tree
        }
    }



   /**
    * Recursively inserts an element into the binary search tree while maintaining the BST properties.
    * Updates the height of the nodes as needed.
    *
    * @param node the node that is currently being considered for insertion
    * @param e the element to be inserted
    * @return true if the element was successfully inserted, false if the element already exists in the tree
    */
    private boolean insertRecursive(Node node, E e) {
    // Base case: Check if the current node's data is greater or smaller than the element to be inserted
    if (e.compareTo(node.data) < 0) {
        // If the element is smaller, explore the left subtree
        if (node.left == null) {
            // If left child doesn't exist, create new node here
            node.left = new Node(e);
            node.left.height = 1; // Initialize the height of the new node
            // Update the height of the current node considering the new subtree height
            node.height = Math.max(node.left == null ? 0 : node.left.height, node.right == null ? 0 : node.right.height) + 1;
            size++; // Increment size of the tree
            return true; // Insertion was successful
        } else {
            // Recur on the left child
            boolean inserted = insertRecursive(node.left, e);
            if (inserted) {
                // Update the height of the current node if insertion in left subtree was successful
                node.height = Math.max(node.left == null ? 0 : node.left.height, node.right == null ? 0 : node.right.height) + 1;
            }
            return inserted;
        }
    } else if (e.compareTo(node.data) > 0) {
        // If the element is larger, explore the right subtree
        if (node.right == null) {
            // If right child doesn't exist, create new node here
            node.right = new Node(e);
            node.right.height = 1; // Initialize the height of the new node
            // Update the height of the current node considering the new subtree height
            node.height = Math.max(node.left == null ? 0 : node.left.height, node.right == null ? 0 : node.right.height) + 1;
            size++; // Increment size of the tree
            return true; // Insertion was successful
        } else {
            // Recur on the right child
            boolean inserted = insertRecursive(node.right, e);
            if(inserted){
                // Update the height of the current node if insertion in right subtree was successful
                node.height = Math.max(node.left == null ? 0 : node.left.height, node.right == null ? 0 : node.right.height) + 1;
            }
            return inserted;
        }
    } else {
        // Element is equal to current node's data, insertion is not performed (assuming no duplicate values)
        return false;
    }
}



   /**
    * Recursively removes an element from the binary search tree.
    * If the element is found, this method rearranges the tree to maintain the BST properties and updates the height.
    *
    * @param node the node that is currently being considered for removal
    * @param e the element to be removed
    * @return the new root of the subtree after removal
    */
    private Node removeRecursive(Node node, E e) {
        // If the node is null, return null (base case for recursion)
        if (node == null) {
            return null;
        }
        
        // If the element to be removed is less than the current node's data, go left
        if (e.compareTo(node.data) < 0) {
            node.left = removeRecursive(node.left, e);
        } 
        // If the element to be removed is greater than the current node's data, go right
        else if (e.compareTo(node.data) > 0) {
            node.right = removeRecursive(node.right, e);
        } 
        // If the current node is the node to be removed
        else {
            // If the current node has no left child, replace it with its right child
            if (node.left == null) {
                size--; // Decrement the size of the tree
                return node.right;
            } 
            // If the current node has no right child, replace it with its left child
            else if (node.right == null) {
                size--; // Decrement the size of the tree
                return node.left;
            }
            // If the current node has both left and right children
            else {
                // Replace the current node's data with the smallest value in its right subtree
                node.data = findMin(node.right).data;
                // Remove the node with the smallest value in the right subtree
                node.right = removeRecursive(node.right, node.data);
            }
        }
        
        // Update the height of the current node after removal
        node.height = Math.max(node.left == null ? 0 : node.left.height, node.right == null ? 0 : node.right.height) + 1;
        // Return the updated node
        return node;
    }



   /**
    * Finds the minimum node in the subtree rooted at the given node.
    *
    * @param node the root node of the subtree
    * @return the node with the minimum value in the subtree
    */
    private Node findMin(Node node) {
    // Loop to traverse to the leftmost node
        while (node.left != null) {
            node = node.left;  // Move to the left child of the current node
        }
        // When left is null, the current node is the smallest
        return node;
    }


   /**
    * Recursively checks if the binary search tree contains a specified element.
    *
    * @param node the node that is currently being considered
    * @param e the element being searched for
    * @return true if the element is found, false otherwise
    */
    private boolean containsRecursive(Node node, E e){
    // Base case: if the node is null, the element is not found in this branch
    if (node == null) {
        return false;
    }

    // If the element is less than the current node's data, search in the left subtree
    if (e.compareTo(node.data) < 0) {
        return containsRecursive(node.left, e); 
    } 
    // If the element is greater than the current node's data, search in the right subtree
    else if (e.compareTo(node.data) > 0) {
        return containsRecursive(node.right, e); 
    } 
    // If the element matches the current node's data, the element is found
    else {
        return true;
    }
}


   /**
    * Recursively checks if two binary search trees are equal.
    *
    * @param node1 the root of the first tree
    * @param node2 the root of the second tree
    * @return true if both trees are structurally identical and contain the same elements, false otherwise
    */
    private boolean equalsRecursive(Node node1, Node node2) {
    // Check if both nodes are null, indicating identical structure at this branch (both ends simultaneously)
    if (node1 == null && node2 == null) {
        return true;
    }
    
    // If one is null and the other is not, structures differ, return false
    if (node1 == null || node2 == null) {
        return false;
    }
    
    // Check current nodes' data for equality and recursively check both left and right subtrees
    return node1.data.equals(node2.data) &&
           equalsRecursive(node1.left, node2.left) &&
           equalsRecursive(node1.right, node2.right);
    }
}
