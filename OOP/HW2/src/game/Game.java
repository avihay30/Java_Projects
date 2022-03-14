package game;

import java.util.Scanner;

public class Game extends Board {
    protected Player[] players;
    protected Scanner s;

    public Game(int n, int m, Player p1, Player p2) {
        super(n, m);
        players = new Player[2];
        players[0] = p1;
        players[1] = p2;
        s = new Scanner(System.in);
    }

    protected boolean doesWin(int i, int j) {
        return i == 0 && j == 0;
    }

    protected boolean onePlay(Player p) {
        int i, j;
        boolean isAlreadySet = false;
        do {
            if (isAlreadySet) System.out.println("There is a piece there already...");
            System.out.format("%s, please enter x and y: ", p);
            // can assume that input is valid
            i = s.nextInt();
            j = s.nextInt();
            isAlreadySet = !set(i, j, p);
            // printing the board if play is valid
            if (!isAlreadySet) System.out.println(super.toString());
        } while (isAlreadySet);

        return doesWin(i, j);
    }

    public Player play() {
        boolean isPlayerOne = true;

        while (!isFull()) {
            Player nextPlayer = isPlayerOne ? players[0] : players[1];
            // if nextPlayer is won => print congratulations and return that player.
            if (onePlay(nextPlayer)) {
                System.out.format("%s Won!", nextPlayer);
                return nextPlayer;
            }

            isPlayerOne = !isPlayerOne;
        }
        return null;
    }

//    public static void main(String[] args) {
//        Game g = new Game(3, 4, new Player("Red", 'R'), new Player("Black", 'B'));
//        g.play();
//    }
}
