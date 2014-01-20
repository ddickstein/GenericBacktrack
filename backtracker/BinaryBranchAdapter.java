package backtracker;

class BinaryBranchAdapter implements Branchable {
    private BinaryBranchable node;
    private Branchable[] children;
    public BinaryBranchAdapter(BinaryBranchable node) {
        this.node = node;
        children = new Branchable[2];
        if (node.getLeft() == null)
            children[0] = null;
        else
            children[0] = new BinaryBranchAdapter(node.getLeft());
        if (node.getRight() == null)
            children[1] = null;
        else
            children[1] = new BinaryBranchAdapter(node.getRight());
    }
    
    public BinaryBranchable getNode() {
        return node;
    }
    
    public String toString() {
        return node.toString();
    }
    
    public Branchable[] getChildren() {
        return children;
    }
}