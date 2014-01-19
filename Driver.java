import java.util.ArrayList;
public class Driver {
    public static void main(String[] args) {
        IntNode tree = IntTreeGenerator.generate(4,8);
        BackTracker btracker = new BackTracker(tree, new BackTracker.BackTrackCallback() {
            public boolean found(ArrayList<Branchable> path) {
                return false;
            }
            public boolean prune(ArrayList<Branchable> path) {
                if (path.size() < 2)
                    return false;
                IntNode lastNode = (IntNode)path.get(path.size()-1);
                IntNode lastParent = (IntNode)path.get(path.size()-2);
                return lastNode.getValue() >= lastParent.getValue();
            }
        }, new BackTracker.BestTracker() {
            public boolean isBetter(ArrayList<Branchable> path) {
                IntNode mostRecentNode = (IntNode)path.get(path.size()-1);
                ArrayList<Branchable> currentBestPath = getBestPath();
                if (currentBestPath == null)
                    return true;
                IntNode lastNodeOfBestBranch = (IntNode)currentBestPath.get(currentBestPath.size()-1);
                return mostRecentNode.getValue() < lastNodeOfBestBranch.getValue();
            }
        });
        
        ArrayList<Branchable> branch = btracker.search();
        assert(branch == null);
        System.out.print("Best path: ");
        TreePrinter.printPath(btracker.getBestPath());
        System.out.println("\nTree:");
        TreePrinter.printTree(tree);
    }
}