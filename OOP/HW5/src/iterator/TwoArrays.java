package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Iterable Class that iterate over 2 given arrays alternately.
public class TwoArrays implements Iterable<Integer> {
    private int[] arr1, arr2;

    public TwoArrays(int[] a1, int[] a2) {
        arr1 = a1;
        arr2 = a2;
    }

    @Override
    public Iterator<Integer> iterator() {
        // anonymous Iterator class for TwoArrays
        return new Iterator<Integer>() {
            private int arr1Idx, arr2Idx;
            private boolean isArr2;

            @Override
            public boolean hasNext() {
                // returns true while there is left in some array
                return arr1Idx < arr1.length || arr2Idx < arr2.length;
            }

            @Override
            public Integer next() {
                if(!hasNext())
                    throw new NoSuchElementException();
                // assign appropriate value and inc counter
                Integer value = isArr2 ? arr2[arr2Idx++] : arr1[arr1Idx++];
                // in case iterating on one of the arrays has been finished
                // assigning isArr2 to constant boolean value
                if (arr2Idx == arr2.length) isArr2 = false;
                else if (arr1Idx == arr1.length) isArr2 = true;
                else isArr2 = !isArr2;

                return value;
            }
        };
    }

//    public static void main(String[] args) {
//        int[] a1 = { 1, 2, 3, 4 };
//        int[] a2 = { 100, 101, 102, 103, 104, 105, 106 };
//
//        TwoArrays aa = new TwoArrays(a1, a2);
//        for (int i : aa)
//            System.out.print(i + " ");
//    }
}
