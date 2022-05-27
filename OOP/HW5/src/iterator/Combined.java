package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Iterable Class that iterate over 2 given Iterables alternately.
public class Combined<E> implements Iterable<E> {
    private Iterable<E> first, second;
    public Combined(Iterable<E> first, Iterable<E> second) {
        this.first = first;
        this.second = second;
    }

    // private inner Iterator class for Combined class
    private class CombinedIterator implements Iterator<E> {
        private boolean isSecondIterator;
        private Iterator<E> iterator1 = first.iterator();
        private Iterator<E> iterator2 = second.iterator();

        @Override
        public boolean hasNext() { // while hasNext on some iterator
            return iterator1.hasNext() || iterator2.hasNext();
        }

        @Override
        public E next() {
            if(!hasNext())
                throw new NoSuchElementException();
            // assign appropriate value according to which iterator we are looking for
            E value = isSecondIterator ? iterator2.next() : iterator1.next();
            // in case iterating on one of the Iterables has been finished
            // assigning isSecondIterator to constant boolean value
            if (!iterator2.hasNext()) isSecondIterator = false;
            else if (!iterator1.hasNext()) isSecondIterator = true;
            else isSecondIterator = !isSecondIterator;

            return value;
        }
    }

    @Override
    public Iterator<E> iterator() { // returning the private CombinedIterator
        return new CombinedIterator();
    }
}
