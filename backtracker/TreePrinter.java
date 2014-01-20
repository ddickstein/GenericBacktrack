/*
 * 1/19/14
 * TreePrinter.java
 * by Dani Dickstein
 */
package backtracker;
import java.util.ArrayList;

/**
 * The TreePrinter class is used to print out trees in a nicely formatted way.
 * Each node must override the toString() method to determine how they should
 * be printed.  For clean tree printing, keep toString() to a single line, and
 * preferably short.
 *
 * @author Dani Dickstein
 * @version 0.1
 */
public class TreePrinter {

    /**
     * This method is used to print an entire Branchable tree, starting at the
     * given root.
     * @param tree The tree to print
     */
    public static void printTree(Branchable tree) {
        if (tree == null)
            System.out.println("[None]");
        else
            printTree(tree,"","",true);
    }
    
    /**
     * Main body of the tree printing algorithm.
     * @param tree The tree to print
     * @param currentPrefix The prefix to print before this node's value
     * @param nextPrefix The prefix to print before this node's children's
     *                   values
     * @param rootPrint True if printing the root of the tree, False otherwise
     */
    private static void printTree(Branchable tree, String currentPrefix,
                                  String nextPrefix, boolean rootPrint) {
        if (rootPrint)
            System.out.println(tree);
        else
            System.out.println(currentPrefix+"|___"+tree);
        if (tree.getLeft() != null && tree.getRight() != null)
            printTree(tree.getLeft(),nextPrefix,nextPrefix+"| ",false);
        else if (tree.getLeft() != null)
            printTree(tree.getLeft(),nextPrefix,nextPrefix+"  ",false);
        if (tree.getRight() != null)
            printTree(tree.getRight(),nextPrefix,nextPrefix+"  ",false);
    }
    
    /**
     * This method is used to print a single path, represented by an ArrayList
     * of Branchable nodes.
     * @param branch A list of nodes that make up the given branch
     */
    public static void printPath(ArrayList<Branchable> branch) {
        if (branch == null)
            System.out.println("[None]");
        else {
            for (int i = 0; i < branch.size()-1; i++) {
                System.out.print("("+branch.get(i)+") --> "); 
            }
            System.out.println("("+branch.get(branch.size()-1)+")");
        }
    }
}