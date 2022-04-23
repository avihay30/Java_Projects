package iterator;

// class that has only one static method for converting some iterator to string in array format ("[...]")
// class is final in order to not allow inheriting this class.
public final class IteratorToString {
    // empty constructor, not to allow `new` on this class. it's a utility class
    private IteratorToString() {
    }

    /**
     * @param it MyIterator to create string from
     * @return a string of the specified MyIterator in array format
     */
    public static String toString(MyIterator it) {
        StringBuilder stringBuilder = new StringBuilder("[");
        // appending the next in the iterator and space afterwards
        while (it.hasNext())
            stringBuilder.append(it.next()).append(" ");
        if (stringBuilder.length() != 1) // if the iterator wasn't empty
            stringBuilder.deleteCharAt(stringBuilder.length() - 1); // delete last empty space
        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}
