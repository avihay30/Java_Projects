package cards;

/***
 * Represents a Deck of game cards.
 */
public class Deck {

    private Card[] cards;
    // represents num of actual cards in cards array.
    private int numOfCards;

    /***
     * Generates a Deck with 0..num cards with all possible suits.
     * @param num the max Card.num of Deck to be created
     */
    public Deck(int num) {
        int totalNumOfSuits = 4;
        // init cards array
        cards = new Card[totalNumOfSuits * num];

        for (int cardNum = 0; cardNum < num; cardNum++) {
            for (int suitNum = 0; suitNum < totalNumOfSuits; suitNum++) {
                cards[numOfCards++] = new Card(cardNum, suitNum);
            }
        }
    }

    /***
     * Generates num or `from.numOfCards` sized Deck by taking cards from `from` Deck
     * @param from Deck to take from
     * @param num num of Cards to take, if possible
     */
    public Deck(Deck from, int num) {
        // set to the number of cards possible to pick from "Deck from".
        numOfCards = (num < from.numOfCards) ? num : from.numOfCards;
        // init cards array
        cards = new Card[numOfCards];

        for (int i = 0; i < numOfCards; i++) {
            cards[i] = from.takeOne();
        }
    }

    /***
     * Generates a Deck by combining first and seconds Decks
     * @param first Deck to take from
     * @param second Deck to take from
     */
    public Deck(Deck first, Deck second) {
        int numOfCardsToBe = first.getNumCards() + second.getNumCards();
        // init cards array
        cards = new Card[numOfCardsToBe];

        // loop until both decks are empty.
        while (first.getNumCards() != 0 || second.getNumCards() != 0) {
            if (first.getNumCards() > 0)
                cards[numOfCards++] = first.takeOne();
            if (second.getNumCards() > 0)
                cards[numOfCards++] = second.takeOne();
        }
    }

    public int getNumCards() {
        return numOfCards;
    }

    public Card takeOne() {
        if (numOfCards == 0) return null;

        return cards[--numOfCards];
    }

    public String toString() {
        String output = "[";
        for (int i = 0; i < numOfCards; i++) {
            output += cards[i];
            if (i != numOfCards - 1) output += ", ";
        }
        return output + "]";
    }

    /***
     * Performing insertion sort on this Deck
     */
    public void sort() {
        for (int i = 1; i < numOfCards; i++)
            for (int j = i; j > 0 && cards[j].compareTo(cards[j - 1]) < 0; j--)
                swap(j, j - 1);
    }

    private void swap(int i, int j) {
        Card temp = cards[i];
        cards[i] = cards[j];
        cards[j] = temp;
    }
}
