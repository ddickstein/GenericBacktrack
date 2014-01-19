import java.util.ArrayList;
public class TreePrinter {
    public static void printTree(Branchable tree) {
        if (tree == null)
            System.out.println("[None]");
        else
            printTree(tree,"","",true);
    }
    
    private static void printTree(Branchable tree, String currentPrefix, String nextPrefix, boolean rootPrint) {
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