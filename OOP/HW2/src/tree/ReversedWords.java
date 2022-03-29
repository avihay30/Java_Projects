package tree;

import java.util.Scanner;

/**
 * ReversedWords has a function that making use of Node Class,
 * the function returns the amount of reversed words that was inputted by the user.
 */
public class ReversedWords {

    public static int checkReversed() {
        Scanner scanner = new Scanner(System.in);
        Node root = new Node();
        int numOfReversed = 0;

        // checks if there is left to scan and it's not "X" (=ending letter)
        while (scanner.hasNext() && !scanner.hasNext("X")) {
            String nextWord = scanner.next();
            // if reversed word was inserted already
            if (root.num(getReversed(nextWord)) > 0) numOfReversed++;
            // inserting the current word
            root.add(nextWord);
        }

        scanner.close();
        return numOfReversed;
    }

    /**
     * reverse given string
     *
     * @param str given string to reverse
     * @return reversed string
     */
    private static String getReversed(String str) {
        String reversedStr = "";
        for (int i = str.length() - 1; i >= 0; i--) reversedStr += str.charAt(i);
        return reversedStr;
    }
}
