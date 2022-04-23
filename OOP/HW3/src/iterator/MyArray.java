package iterator;

import java.util.NoSuchElementException;

// class that simply implements MyIterator for array of ints
public class MyArray implements MyIterator {
    private final int[] arr;
    private int index; // holds the index of the `next` element in arr

    public MyArray(int[] arr) {
        this.arr = new int[arr.length];
        // copying given arr to class arr
        for (int i = 0; i < arr.length; i++) {
            this.arr[i] = arr[i];
        }
    }

    /**
     * @return if there are element left in the instance array
     */
    @Override
    public boolean hasNext() {
        return index < arr.length;
    }

    /**
     * @return the next element in array if possible,
     * else throws `NoSuchElementException`
     */
    @Override
    public int next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return arr[index++];
    }
}
