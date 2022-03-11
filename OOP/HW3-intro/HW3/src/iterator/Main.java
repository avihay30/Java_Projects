package iterator;

public class Main {
    public static void main(String[] args) {
        // My array
//        int[] x = {2, 4, 6, 1};
//        MyIterator it = new MyArray(x);
//        while(it.hasNext())
//            System.out.print(it.next() + " ");

        // Fibonacci
        Fibonacci fibo = new Fibonacci(10);
        while(fibo.hasNext())
            System.out.print(fibo.next() + " ");

        // IteratorToString
        System.out.println(IteratorToString.toString(new Fibonacci(5)));
    }
}
