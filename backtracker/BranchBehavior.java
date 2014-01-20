/*
 * 1/20/14
 * BranchBehavior.java
 * by Dani Dickstein
 */
package backtracker;

/**
 * A class that is used to represent the behavior of a generic tree.  A
 * Branchable class that has a children will have a compositional relationship
 * with this class or one of its subclasses to indicate this behavior.
  *
 * @author Dani Dickstein
 * @version 0.2
 */
public abstract class BranchBehavior {
    /**
     * Get the children of this node
     * @return The children nodes
     */
    public abstract Branchable[] getChildren();
}
