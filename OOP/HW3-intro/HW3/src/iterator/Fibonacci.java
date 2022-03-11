package iterator;

public class Fibonacci implements MyIterator {
    private final int upperBound;
    private boolean isFirstElement = true; // todo: maybe redundant...
    private int preElement = 1;
    private int secondPreElement = 0;

    public Fibonacci(int upperBound) {
        this.upperBound = upperBound;
    }

    @Override
    public boolean hasNext() {
        return preElement + secondPreElement <= upperBound;
    }

    @Override
    public int next() {
        if (hasNext()) {
            if (isFirstElement) {
                isFirstElement = false;
                return preElement;
            }
            int temp = preElement;
            preElement += secondPreElement;
            secondPreElement = temp;
        }
        return preElement;
    }
}
