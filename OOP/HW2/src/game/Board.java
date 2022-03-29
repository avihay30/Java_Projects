package game;

/**
 * Board represents a Board in some game, according to a matrix of players.
 */
public class Board {
    protected Player[][] board;
    protected int n, m;

    public Board(int n, int m) {
        board = new Player[n][m];
        this.n = n;
        this.m = m;
    }

    /**
     * @return true if setting p in board[i,j] has been successful
     */
    protected boolean set(int i, int j, Player p) {
        // if slot is taken
        if (!isEmpty(i, j)) return false;

        board[i][j] = p;
        return true;
    }

    public boolean isEmpty(int i, int j) {
        return board[i][j] == null;
    }

    /**
     * @return Player at i,j if exists, else returns null
     */
    public Player get(int i, int j) {
        // if board[i][j] is empty (=null), it will return null.
        return board[i][j];
    }

    /**
     * Iterate over all the board and checks if full
     * @return true if whole board is full.
     */
    public boolean isFull() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // if there is empty slot => board isn't full
                if (isEmpty(i, j)) return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String strToReturn = "";
        for (int i = 0; i < n; i++) {
            for (Player player : board[i]) {
                if (player == null) strToReturn += ".";
                else strToReturn += player.getMark();
            }
            strToReturn += "\n";
        }
        return strToReturn;
    }

    /**
     * calculating longest length of a line in some direction (row, column, diagonals)
     * that player board[x][y] is appear consistently
     *
     * @return longest length
     */
    protected int maxLineContaining(int i, int j) {
        int maxLength = 0;

        // calculate row length
        // direction 1 => right ; direction 2 => left
        int tempLength = lineLength(i, j, 0, 1, 0, -1);
        maxLength = max(maxLength, tempLength);

        // calculate column length
        // direction 1 => down ; direction 2 => up
        tempLength = lineLength(i, j, 1, 0, -1, 0);
        maxLength = max(maxLength, tempLength);

        // calculate main-diagonal length
        // direction 1 => down-right ; direction 2 => up-left
        tempLength = lineLength(i, j, 1, 1, -1, -1);
        maxLength = max(maxLength, tempLength);

        // calculate anti-diagonal length
        // direction 1 => down-left ; direction 2 => up-right
        tempLength = lineLength(i, j, 1, -1, -1, 1);
        maxLength = max(maxLength, tempLength);

        return maxLength;
    }

    /**
     * calculating both directions (dx,dy) of given x,y player
     *
     * @return sum lengths of both directions
     */
    private int lineLength(int x, int y, int dx1, int dy1, int dx2, int dy2) {
        int length = directedLineLength(x, y, dx1, dy1);
        length += directedLineLength(x, y, dx2, dy2);
        // in the calculations the board[x][y] is counted twice, so we subtract 1.
        return length - 1;
    }

    /**
     * calculating length of a line in direction (dx,dy) [(0,0) is the current position]
     * that player board[x][y] is appear consistently
     *
     * @return length of the directed line
     */
    private int directedLineLength(int x, int y, int dx, int dy) {
        // (dx,dy)=(0,0) stands for current position, no direction
        if (dx == 0 && dy == 0) return 1;
        Player player = board[x][y];
        int length = 0;

        while ((x >= 0 && x < n) && (y >= 0 && y < m)) {
            // if the line no longer has player in strike
            if (board[x][y] != player) return length;

            length++;
            x += dx;
            y += dy;
        }
        return length;
    }

    private static int max(int a, int b) {
        if (a > b) return a;
        return b;
    }
}
