public class IntTreeGenerator
{
    public static IntNode generate(int mindepth, int maxdepth) {
        IntNode root = null;
        int possibilities;
        if (mindepth > 0)
            possibilities = randInt(2) + 1;
        else if (maxdepth == 1)
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
