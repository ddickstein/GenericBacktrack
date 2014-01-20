/*
 * 1/20/14
 * BinaryBranchBehavior.java
 * by Dani Dickstein
 */
package backtracker;

/**
 * A class that is used to represent the behavior of a binary tree.  A
 * Branchable class that has a left and right node will have a compositional
 * relationship with this class to indicate this behavior.
 *
 * @author Dani Dickstein
 * @version 0.2
 */
public abstract class BinaryBranchBehavior extends BranchBehavior {
    /**
     * Get the child node on the left
     * @return The left child
     */
    public abstract Branchable getLeft();

    /**
     * Get the child node on the right
     * @return The right child
     */
    public abstract Branchable getRight();
    
    @Override
    public final Branchable[] getChildren() {
        return new Branchable[]{getLeft(),getRight()};
    }
}