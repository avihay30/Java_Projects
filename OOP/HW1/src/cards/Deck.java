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
                cards[numOfCards] = new Card(cardNum, suitNum);
                numOfCards++;
            }
        }
    }

    /***
     * Generates num or `from.numOfCards` sized Deck by taking cards from `from` Deck
     * @param from Deck to take from
     * @param num num of Crads to take, if possible
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

        // todo: check if need to set to null in the cards array slot...
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
     * Performing a bubble sort on Deck
     */
    public void sort() {
        int unorderedLength = numOfCards;
        // init default biggest card
        int indexOfBiggest = 0;
        Card biggestCard = cards[indexOfBiggest];

        for (int i = 0; i < numOfCards; i++) {
            for (int j = 1; j < unorderedLength; j++) {
                // check if cards[j] is bigger than biggestCard
                if (biggestCard.compareTo(cards[j]) < 0) {
                    biggestCard = cards[j];
                    indexOfBiggest = j;
                }
            }
            // switching last unsorted card with the biggest card
            cards[indexOfBiggest] = cards[unorderedLength - 1];
            cards[unorderedLength - 1] = biggestCard;
            // reset biggestCard
            biggestCard = cards[0];
            indexOfBiggest = 0;
            unorderedLength--;
        }
    }
}
