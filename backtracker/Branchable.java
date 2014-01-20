/*
 * 1/20/14
 * Branchable.java
 * by Dani Dickstein
 */
package backtracker;

/**
 * An interface implemented by any class that represents a node in a tree.
 *
 * @author Dani Dickstein
 * @version 0.2
 */
public interface Branchable {
    /**
     * Get the branching behavior of this class.
     * @return The implementing class' branching behavior
     */
    public BranchBehavior getBranchBehavior();
}