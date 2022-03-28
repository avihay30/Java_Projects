package game;

public class FourInARow extends Game {

    public FourInARow(String player1, String player2) {
        // 6x7 is the boardSize according to requirements.
        super(6, 7, new Player(player1, 'W'), new Player(player2, 'B'));
    }

    @Override
    protected boolean doesWin(int i, int j) {
        return maxLineContaining(i, j) >= 4;
    }

    @Override
    protected boolean onePlay(Player p) {
        int column, row;
        boolean isColumnFull = false;

        do {
            if (isColumnFull) System.out.println("The column is full...");
            System.out.format("%s, please enter column: ", p);
            // can assume that input is valid
            column = s.nextInt();
            row = getNextEmptyRowInColumn(column);
            set(row, column, p);
            // if return value is -1 so column is full
            isColumnFull = row == -1;
            // printing the board if play is valid
            if (!isColumnFull) System.out.println(super.toString());
        } while (isColumnFull);

        return doesWin(row, column);
    }

    /**
     * @param column to search on
     * @return if column is full -1,
     * else the next empty row index in the specified column
     */
    private int getNextEmptyRowInColumn(int column) {
        // iterating backwards on the board's rows
        for (int i = n - 1; i > 0; i--) {
            if (isEmpty(i, column)) return i;
        }
        return -1;
    }
}
