package iterator;

/* interface that whom implements it, generate int series
   that each call to next() will return the next int in order
   and hasNext() returns if there is any next. */
public interface MyIterator {
    boolean hasNext();
    int next();
}
