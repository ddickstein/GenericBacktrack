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
public interface Branchable extends AbstractBranchable {
    /**
     * Get the children of this node.  They should be ordered in the order in
     * which they should be searched.
     * @return The children, or null if none found
     */
    public Branchable[] getChildren();
}