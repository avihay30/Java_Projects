package game;

/**
 * TicTacToe is a Game that represents the famous game TicTacToe.
 */
public class TicTacToe extends Game {

    public TicTacToe(String player1, String player2) {
        // 3x3 is the boardSize according to requirements.
        super(3, 3, new Player(player1, 'X'), new Player(player2, 'O'));
    }

    /**
     * winner is the player with 3 strikes in a row
     * @return if the player at x,y on the board has won
     */
    @Override
    protected boolean doesWin(int x, int y) {
        return maxLineContaining(x, y) >= 3;
    }
}
