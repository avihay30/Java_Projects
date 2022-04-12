package iterator;

// class that implements MyIterator that each call to next()
// returns the next number in Fibonacci series.
public class Fibonacci implements MyIterator {
    private int upperBound;
    // holds the last (idx = 1) and before last (idx = 0) numbers of the Fibonacci series
    private int[] prevNumbers = new int[2];

    public Fibonacci(int upperBound) {
        this.upperBound = upperBound;
    }

    /**
     * @return true if the next element in the series is less/equal to the upperBound
     */
    @Override
    public boolean hasNext() {
        return (prevNumbers[0] + prevNumbers[1]) <= upperBound;
    }

    @Override
    public int next() {
        // if the next is bigger than upperBound, return last number in series.
        if (!hasNext()) return prevNumbers[1];

        // if it's the first call to next(), returning 1
        if (prevNumbers[0] == 0 && prevNumbers[1] == 0) {
            prevNumbers[1] = 1;
            return prevNumbers[1];
        }
        // calculating next number in series, and update accordingly the prevNumbers array
        int nextNum = prevNumbers[0] + prevNumbers[1];
        prevNumbers[0] = prevNumbers[1];
        prevNumbers[1] = nextNum;
        return nextNum;
    }

//    public static void main(String[] args) {
//        MyIterator it = new Fibonacci(10);
//        while(it.hasNext())
//            System.out.print(it.next() + " ");
//        System.out.print(it.next() + " ");
//    }
}
