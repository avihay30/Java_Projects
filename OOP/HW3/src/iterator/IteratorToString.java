package iterator;

public class IteratorToString {
    public static String toString(MyIterator it) {
        StringBuilder stringBuilder = new StringBuilder("[");
        while(it.hasNext()) {
            stringBuilder.append(it.next()).append(" ");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("]");

        return stringBuilder.length() > 2 ? stringBuilder.toString() : "";
    }
}
