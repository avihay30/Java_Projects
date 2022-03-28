package bank;

public class ProAccount extends Account {
    // according to requirements account history is up to 100
    private int[] accountHistory = new int[100];
    private int currentHistory;

    public ProAccount(String name) {
        super(name);
    }

    @Override
    public void add(int amount) {
        super.add(amount);

        accountHistory[currentHistory++] = super.getShekels();
    }

    @Override
    public String toString() {
        return String.format("%s %s", super.toString(), arrToString(accountHistory, currentHistory));
    }

    public static void transfer(ProAccount from, ProAccount to, int amount) {
        from.add(-amount);
        to.add(amount);
    }

    private static String arrToString(int[] arr, int amount) {
        if (amount == 0) return "[]";

        String str = "[";
        for (int i = 0; i < amount - 1; i++) str += arr[i] + ",";
        str += arr[amount - 1] + "]";

        return str;
    }
}
