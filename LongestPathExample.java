/*
 * 1/19/14
 * MinValueExample.java
 * by Dani Dickstein
 */
import java.util.ArrayList;
import backtracker.BackTracker;
import backtracker.BackTracker.BackTrackCallback;
import backtracker.BackTracker.BestTracker;
import backtracker.TreePrinter;
import backtracker.Branchable;

/*
 * A sample usage of the BackTracker algorithm.  This program traverses a
 * tree to find the longest path.
 */
public class LongestPathExample {
    public static void main(String[] args) {
        // Generate a new IntNode tree
        IntNode tree = IntTreeGenerator.generate(3,10);
        // Create a new BackTracker with a BestTracker.  We do not need to use
        // any callback object, because we will search the whole tree without
        // doing any pruning.
        BackTracker backTracker = new BackTracker(tree,new BestTracker() {
            // We want to check if the smallest value on this path is smaller
            // than the smallest value we have seen so far.
            @Override
            public boolean isBetter(ArrayList<Branchable> path) {
                if (path == null || path.size() == 0)
                    return false;
                IntNode lastNode = (IntNode)path.get(path.size()-1);
                if (lastNode.getRight() != null || lastNode.getLeft() != null)
                    return false;
                else if (getBestPath() == null)
                    return true;
                else
                    return path.size() > getBestPath().size();
            }
        });        
        // Run the search algorithm
        backTracker.search();
        // Print out the best path found (the path in descending order that has
        // the smallest element)
        System.out.print("Longest path: ");
        TreePrinter.printPath(backTracker.getBestPath());
        System.out.println("\nTree:");
        TreePrinter.printTree(tree);
    }
}