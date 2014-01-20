package backtracker;

public abstract class BinaryBranchBehavior extends BranchBehavior {
    public abstract Branchable getLeft();
    public abstract Branchable getRight();
    
    public final Branchable[] getChildren() {
        return new Branchable[]{getLeft(),getRight()};
    }
}