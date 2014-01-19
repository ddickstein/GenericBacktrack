public class IntNode implements Branchable {
    private int value;
    private IntNode left;
    private IntNode right;
    
    public IntNode(int value) {
        setValue(value);
        this.left = null;
        this.right = null;
    }
    
    public int getValue() {
        return value;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    
    public IntNode getLeft() {
        return left;
    }
    
    public void setLeft(IntNode left) {
        this.left = left;
    }
    
    public IntNode getRight() {
        return right;
    }
    
    public void setRight(IntNode right) {
        this.right = right;
    }
    
    public String toString() {
        return ""+value;
    }
}