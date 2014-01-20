import backtracker.Branchable;
public class TicTacToeExample {
    public static void main(String[] args) {
        
    }
}

class Board implements Branchable {
    private char[][] board;
    
    public Board(int size) {
        initializeBoard(Math.min(size,3));
    }
    
    private void initializeBoard(int size) {
        this.board = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = ' ';
            }
        }
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
    
    public boolean play(int row, int col, char ch) {
        if (board[row][col] == ' ') {
            board[row][col] = ch;
            return true;
        }
        else
            return false;
    }
    
    public Board[] getChildren() {
        return null;
    }
}