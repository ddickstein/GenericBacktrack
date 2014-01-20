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

/*
 * A sample usage of the BackTracker algorithm.  This program traverses a
 * tree to find the smallest element, but only considers paths on which the
 * nodes appear in descending order.
 */
public class MinValueExample {
    // Create a new callback object
    private static BackTrackCallback callback = new BackTrackCallback() {
        // We want to search the whole tree, so we make found() return false.
        public boolean found(ArrayList path) {
            return false;
        }
        // We want to prune results where the path is no longer in decreasing
        // order.
        public boolean prune(ArrayList path) {
            if (path.size() < 2)
                return false;
            IntNode lastNode = (IntNode)path.get(path.size()-1);
            IntNode lastParent = (IntNode)path.get(path.size()-2);
            return lastNode.getValue() >= lastParent.getValue();
        }
    };

    // Create a new bestTracker object
    private static BestTracker bestTracker = new BestTracker() {
        // We want to check if the smallest value on this path is smaller than
        // the smallest value we have seen so far.
        public boolean isBetter(ArrayList path) {
            int value = ((IntNode)path.get(path.size()-1)).getValue();
            ArrayList bestPath = getBestPath();
            if (bestPath == null)
                return true;
            IntNode bestNode = (IntNode)bestPath.get(bestPath.size()-1);
            return value < bestNode.getValue();
        }
    };

    public static void main(String[] args) {
        // Generate a new IntNode tree
        IntNode tree = IntTreeGenerator.generate(3,10);
        // Create a new BackTracker
        BackTracker btracker = new BackTracker(tree, callback, bestTracker);        
        // Run the search algorithm
        btracker.search();
        // Print out the best path found (the path in descending order that has
        // the smallest element)
        System.out.print("Best path: ");
        TreePrinter.printPath(btracker.getBestPath());
        System.out.println("\nTree:");
        TreePrinter.printTree(tree);
    }
}