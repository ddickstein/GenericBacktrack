import backtracker.BackTracker;
import backtracker.BackTracker.BackTrackCallback;
import backtracker.BackTracker.BestTracker;
import backtracker.BranchBehavior;
import backtracker.Branchable;

public class TicTacToeExample {
    public static void main(String[] args) {
        
    }
}

class TicTacToeAI {
    public static Board[] nextPossibleMoves(Board board) {
        if (board == null)
            return null;
        if (board.isFull())
            return new Board[0];
        char player = board.getPlayer();
        return null;
    }
}

abstract class BoardTraverser {
    private Board board;
    private Object result;
    private boolean halted;
    
    BoardTraverser(Board board) {
        this.board = board;
        setResult(null);
        halted = false;
    }
    
    void traverse() {
        for (int i = 0; i < board.size() && !halted; i++) {
            for (int j = 0; j < board.size() && !halted; j++) {
                action(i,j,board);
            }
        }
        halted = false;
    }
    
    void halt() {
        halted = true;
    }
    
    void setResult(Object result) {
        this.result = result;
    }
    
    Object getResult() {
        return result;
    }
    
    abstract void action(int row, int col, Board board);
}

class Board implements Branchable {
    private char[][] board;
    private BranchBehavior behavior;
    
    public Board(int size) {
        initializeBoard(Math.max(size,3));
        this.behavior = new BranchBehavior() {
            public Branchable[] getChildren() {
                return Board.this.getChildren();
            }
        };
    }
    
    private void initializeBoard(int size) {
        this.board = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = ' ';
            }
        }
    }
    
    public char get(int r, int c) {
        return board[r][c];
    }
    
    public boolean set(int row, int col, char ch) {
        if (board[row][col] == ' ') {
            board[row][col] = ch;
            return true;
        }
        else
            return false;
    }
    
    public int size() {
        return board.length;
    }
    
    public String toString() {
        StringBuilder boardBuilder = new StringBuilder();
        for (int r = 0; r <= board.length * 2; r++) {
            if (r % 2 == 0) {
                for (int c = 0; c < board[0].length; c++) {
                    boardBuilder.append("+---");
                }
                boardBuilder.append("+\n");
            }
            else {
                for (int c = 0; c < board[0].length; c++) {
                    boardBuilder.append("| "+board[r/2][c]+" ");
                }
                boardBuilder.append("|\n");
            }
        }
        return boardBuilder.toString();
    }
    
    public Board[] getChildren() {
        return TicTacToeAI.nextPossibleMoves(this);
    }
    
    public BranchBehavior getBranchBehavior() {
        return behavior;
    }
    
    public char getPlayer() {
        BoardTraverser traverser = new BoardTraverser(this) {
            public void action(int row, int col, Board board) {
                
            }
        };
        int numX = 0;
        int numO = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 'X')
                    numX++;
                else if (board[i][j] == 'O')
                    numO++;
            }
        }
        return numX > numO ? 'O' : 'X';
    }
    
    public boolean isFull() {
        BoardTraverser traverser = new BoardTraverser(this) {
            public void action(int row, int col, Board board) {
                if (board.get(row,col) == ' ') {
                    setResult(false);
                    halt();
                }
            }
        };
        traverser.setResult(true);
        traverser.traverse();
        return (Boolean)traverser.getResult();
    }
}