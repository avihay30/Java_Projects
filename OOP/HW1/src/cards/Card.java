package cards;

/***
 * Represents a game Card, (numSuit, i.g: '5 heart' = 5H)
 */
public class Card {

    private int num;
    private int suit;

    public Card(int num, int suit) {
        this.num = num;
        this.suit = suit;
    }

    public int getNum() {
        return num;
    }

    public int getSuit() {
        return suit;
    }

    public String toString() {
        if (suit == 0) return num + "C";
        if (suit == 1) return num + "D";
        if (suit == 2) return num + "H";
        return num + "S";
    }

    /***
     * Comparing `this` to `other` Card
     * @param other Card to compare with
     * @return positive number if `this` is bigger, negative otherwise.
     */
    public int compareTo(Card other) {
        if (num == other.num) {
            return suit - other.suit;
        }
        // returns positive number if num > other.num, else negative number;
        return num - other.num;
    }
}
