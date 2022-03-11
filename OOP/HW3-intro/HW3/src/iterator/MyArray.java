package iterator;

import java.util.NoSuchElementException;

public class MyArray implements MyIterator {
    private final int[] array;
    private int i = 0;

    public MyArray(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        try {
            this.array[i] = this.array[i];
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
    @Override
    public int next() {
        if (!hasNext())
            throw new NoSuchElementException();
        return this.array[i++]; // incrementing after return
    }
}
