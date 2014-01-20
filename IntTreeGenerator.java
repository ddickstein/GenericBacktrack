/*
 * 1/19/14
 * IntTreeGenerator.java
 * by Dani Dickstein
 */

/*
 * A program used to generate a random tree of IntNodes.  It is only used in
 * the example programs for demonstration purposes.
 */
public class IntTreeGenerator
{
    // Generate a tree of IntNodes that has a depth between mindepth and
    // maxdepth.
    public static IntNode generate(int mindepth, int maxdepth) {
        if (maxdepth < mindepth || maxdepth <= 0)
            return null;
        IntNode root = null;
        int possibilities;
        if (mindepth > 0)
            possibilities = randInt(2) + 1;
        else if (maxdepth == 0)
            possibilities = 1;
        else
            possibilities = randInt(3);
        switch(possibilities) {
            case 1:
                root = new IntNode(randInt(100));
                break;
            case 2:
                root = new IntNode(randInt(100));
                switch(randInt(2)) {
                    case 1: root.setLeft(generate(mindepth-1,maxdepth-1));
                    case 2: root.setRight(generate(mindepth-1,maxdepth-1));
                }
                break;
            case 3:
                root = new IntNode(randInt(100));
                root.setLeft(generate(mindepth-1,maxdepth-1));
                root.setRight(generate(mindepth-1,maxdepth-1));
                break;
        }
        return root;
    }
    
    private static int randInt(int cap) {
        return (int)(Math.random() * cap + 1);
    }
}
