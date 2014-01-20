/*
 * 1/19/14
 * BackTracker.java
 * by Dani Dickstein
 */
package backtracker;
import java.util.ArrayList;

/**
 * BackTracker is a generic backtracking tool that lets you perform a
 * preordered traversal on a tree (root before children).  At each phase you
 * can decide whether to return the current branch, prune the rest of the tree,
 * or mark the current branch and keep traversing.
 * 
 * @author Dani Dickstein
 * @version 0.1
 */
public class BackTracker {

    /**
     * Every BackTracker has a BackTrackCallback that is invoked each time a
     * node is hit during the search.  This interface should be implemented so
     * that the backtracking search can be customized - the implementation
     * should be assigned to this BackTracker's callback.
     * @see BackTracker#setCallback
     */
    public interface BackTrackCallback {

        /**
         * The found() method is called each time the backtracking search
         * reaches a new node.  If the search should terminate at this point
         * and return the current branch, return true.  If the search should
         * continue, return false.
         *
         * @param path A list of nodes that make up the branch from the root of
         *             the tree to this point
         * @return True if the search has finished successfully, False
         *         otherwise
         */
        public boolean found(ArrayList path);

        /**
         * The prune() method is called each time the backtracking search
         * reaches a new node (after found() is called).  If the search should
         * stop investigating this branch, return true.  If the search should
         * continue down this branch, return false.
         * 
         * @param path A list of nodes that make up the branch from the root of
         *             the tree to this point
         * @return True if algorithm should stop searching this branch, False
         *         otherwise
         */
        public boolean prune(ArrayList path);
    }

    /**
     * Often when using backtracking we want to search the entire tree and then
     * return the best result we have found.  This class is used to store the
     * best result so far.  Like BackTrackCallback, it should be implemented
     * so that the backtracking search can be customized.  The implementation
     * should be assigned to this BackTracker's bestTracker
     * @see BackTracker#setBestTracker
     */
    public static abstract class BestTracker {
        // Keep track of the best path so far
        private ArrayList<AbstractBranchable> bestPath;
        
        public BestTracker() {
            setBestPath(null);
        }

        /**
         * Mark this path as the best path found so far.
         * @param bestPath The best path found so far
         */
        public void setBestPath(ArrayList<AbstractBranchable> bestPath) {
            this.bestPath = bestPath;
        }
        
        /**
         * Get the best path found so far.
         * @return The best path so far
         */
        public ArrayList<AbstractBranchable> getBestPath() {
            return bestPath;
        }
        
        /**
         * The isBetter() method is called each time the backtracking search
         * reaches a new node (after the BackTrackCallbacks have been
         * resolved).  If this method returns true, the new path will be saved
         * as the best path seen so far.  After the search is finished,
         * getBestPath() can be called to find the best path that was found in
         * the search.
         *
         * @param newPath The path to compare to the current best path
         * @return True if the new path is better, False otherwise
         */
        public abstract boolean isBetter(ArrayList newPath);
    }
    
    private BackTrackCallback callback; // the callback object
    private BestTracker bestTracker; // the bestTracker object
    private Branchable tree; // the tree to search

    /**
     * Constructor for BackTracker.  It must be assigned a tree before any
     * searching can take place.
     */
    public BackTracker() {
        this(null,null,null);
    }

    /**
     * Constructor for BackTracker.  Takes a Branchable tree it will search as
     * a parameter.
     * @param tree The tree to search
     */
    public BackTracker(AbstractBranchable tree) {
        this(tree,null,null);
    }
    
    /**
     * Constructor for BackTracker.  Takes a Branchable tree it will search and
     * a callback object as parameters.
     *
     * @param tree The tree to search
     * @param callback The callback object
     */
    public BackTracker(AbstractBranchable tree, BackTrackCallback callback) {
        this(tree,callback,null);
    }
    
    /**
     * Constructor for BackTracker.  Takes a Branchable tree it will search and
     * a bestTracker object as parameters.
     *
     * @param tree The tree to search
     * @param bestTracker The bestTracker object to keep track of the best path
     */
    public BackTracker(AbstractBranchable tree, BestTracker bestTracker) {
        this(tree, null, bestTracker);
    }
    
    /**
     * Constructor for BackTracker.  Takes a Branchable tree it will search, a
     * callback object, and a bestTracker object as parameters.
     *
     * @param tree The tree to search
     * @param callback The callback object
     * @param bestTracker The bestTracker object to keep track of the best path
     */
    public BackTracker(AbstractBranchable tree, BackTrackCallback callback,
                       BestTracker bestTracker) {
        setTree(tree);
        setCallback(callback);
        setBestTracker(bestTracker);
    }
    
    /**
     * Set the Branchable tree to search.
     * @param tree The tree to search
     */
    public void setTree(AbstractBranchable tree) {
        if (tree instanceof BinaryBranchable)
            this.tree = new BinaryBranchAdapter((BinaryBranchable)tree);
        else
            this.tree = (Branchable)tree;
    }
    
    /**
     * Set the callback object whose methods will be invoked at each node in
     * the search.
     * @param callback The callback object
     */
    public void setCallback(BackTrackCallback callback) {
        this.callback = callback;
    }
    
    /**
     * Set the bestTracker object to keep track of the best path found as the
     * search progresses.
     * @param bestTracker The bestTracker object
     */
    public void setBestTracker(BestTracker bestTracker) {
        this.bestTracker = bestTracker;
    }
    
    /**
     * Return the best path found in the last back tracking search.
     * @return The best path found, or null if bestTracker object is not set
     */
    public ArrayList<AbstractBranchable> getBestPath() {
        return (bestTracker == null) ? null : bestTracker.getBestPath();
    }
    
    /**
     * Search the tree using back-tracking.  If the callback object has been
     * set, return the branch that caused found() to evaluate to True.  If the
     * callback object has not been set, or if found() never evaluates to True,
     * return False.
     * @return The valid branch (if any) that was found in the search
     */
    public ArrayList<AbstractBranchable> search() {
        if (tree == null || (callback == null && bestTracker == null))
            return null;
        else
            return search(tree, new ArrayList<AbstractBranchable>());
    }
    
    /**
     * Main body of the searching algorithm.
     * @param tree The tree to search
     * @param path The path traveled so far
     * @return The valid branch (if any) that was found in the search
     */
    private ArrayList<AbstractBranchable> search(Branchable tree,
                                         ArrayList<AbstractBranchable> path) {
        ArrayList<AbstractBranchable> pathSoFar = new ArrayList<AbstractBranchable>(path);
        if (tree instanceof BinaryBranchAdapter)
            pathSoFar.add(((BinaryBranchAdapter)tree).getNode());
        else
            pathSoFar.add(tree);
        if (callback != null) {
            if (callback.found(pathSoFar))
                return pathSoFar;
            else if (callback.prune(pathSoFar))
                return null;
        }
        if (bestTracker != null) {
            if (bestTracker.isBetter(pathSoFar))
                bestTracker.setBestPath(pathSoFar);
        }
        if (hasChildren(tree)) {
            for (Branchable child : tree.getChildren()) {
                if (child != null) {
                    ArrayList<AbstractBranchable> branch = search(child,pathSoFar);
                    if (branch != null)
                        return branch;
                }
            }
        }
        return null;
    }
    
    /**
     * Check if this tree node has any branches.
     * @param tree The tree to check
     * @return True if at least one branch is present, False otherwise
     */
    private boolean hasChildren(AbstractBranchable tree) {
        if (tree instanceof BinaryBranchable) {
            BinaryBranchable binTree = (BinaryBranchable)tree;
            return binTree.getLeft() != null || binTree.getRight() != null;
        }
        else if (tree instanceof Branchable) {
            return ((Branchable)tree).getChildren() != null;
        }
        else
            return false;
    }
}