/*
 * 1/19/14
 * Branchable.java
 * by Dani Dickstein
 */
package backtracker;

/**
 * The nodes that form the tree that will be used in the BackTracker algorithm
 * must implement this interface.
 *
 * @author Dani Dickstein
 * @version 0.1
 */
public interface Branchable {
    /**
     * Get the left child of this node
     * @return The left child, or null if none found
     */
    public Branchable getLeft();

    /**
     * Get the right child of this node
     * @return The right child, or null if none found
     */
    public Branchable getRight();
}