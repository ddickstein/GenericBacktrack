import java.util.ArrayList;
public class BackTracker {
    public interface BackTrackCallback {
        public boolean found(ArrayList<Branchable> path);
        public boolean prune(ArrayList<Branchable> path);
    }
    public static abstract class BestTracker {
        private ArrayList<Branchable> bestPath;
        
        public void setBestPath(ArrayList<Branchable> newPath) {
            bestPath = newPath;
        }
        
        public ArrayList<Branchable> getBestPath() {
            return bestPath;
        }
        
        public abstract boolean isBetter(ArrayList<Branchable> newPath);
    }
    
    private BackTrackCallback callback;
    private BestTracker bestTracker;
    private Branchable tree;

    public BackTracker(Branchable tree) {
        this(tree,null,null);
    }
    
    public BackTracker(Branchable tree, BackTrackCallback callback) {
        this(tree,callback,null);
    }
    
    public BackTracker(Branchable tree, BestTracker bestTracker) {
        this(tree, null, bestTracker);
    }
    
    public BackTracker(Branchable tree, BackTrackCallback callback, BestTracker bestTracker) {
        setTree(tree);
        setCallback(callback);
        setBestTracker(bestTracker);
    }
    
    public void setTree(Branchable tree) {
        this.tree = tree;
    }
    
    public void setCallback(BackTrackCallback callback) {
        this.callback = callback;
    }
    
    public void setBestTracker(BestTracker bestTracker) {
        this.bestTracker = bestTracker;
    }
    
    public ArrayList<Branchable> getBestPath() {
        return (bestTracker == null) ? null : bestTracker.getBestPath();
    }
    
    public ArrayList<Branchable> search() {
        return search(tree, new ArrayList<Branchable>());
    }
    
    private ArrayList<Branchable> search(Branchable tree, ArrayList<Branchable> path) {
        if (callback == null)
            return null;
        ArrayList<Branchable> pathSoFar = new ArrayList<Branchable>(path);
        pathSoFar.add(tree);
        if (callback.found(pathSoFar))
            return pathSoFar;
        else if (callback.prune(pathSoFar))
            return null;
        if (bestTracker != null) {
            if (bestTracker.isBetter(pathSoFar))
                bestTracker.setBestPath(pathSoFar);
        }
        if (hasLeft(tree)) {
            ArrayList<Branchable> leftBranch = search(tree.getLeft(),pathSoFar);
            if (leftBranch != null)
                return leftBranch;
        }
        if (hasRight(tree)) {
            ArrayList<Branchable> rightBranch = search(tree.getRight(),pathSoFar);
            if (rightBranch != null)
                return rightBranch;
        }
        return null;
    }
    
    private boolean hasLeft(Branchable tree) {
        return tree.getLeft() != null;
    }
    
    private boolean hasRight(Branchable tree) {
        return tree.getRight() != null;
    }
}