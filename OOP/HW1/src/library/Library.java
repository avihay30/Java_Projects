package library;

/***
 * Represents a Library of books (`Book`s)
 */
public class Library {

    private Book[] books;

    /***
     * Generates a Library with `size` slots for Books
     * @param size of the library
     */
    public Library(int size) {
        this.books = new Book[size];
    }

    /***
     * Adds a new Book to `this` library
     * @param bookNum appropriate slot in the Library (books array)
     * @param title of the Book
     * @param auth Author of the Book
     */
    public void setBook(int bookNum, String title, Author auth) {
        books[bookNum] = new Book(title, auth);
    }

    public Book getBook(int bookNum) {
        return books[bookNum];
    }
}
