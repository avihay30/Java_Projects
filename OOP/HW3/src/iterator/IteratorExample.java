package iterator;

public class IteratorExample implements MyIterator {
    private int i = 0;

    @Override
    public boolean hasNext() {
        return i < 10;
    }
    @Override
    public int next() {
        if (!hasNext())
            return i;
        return i++;
    }
}
