package bank;

/**
 * ProAccount is a Account, that represents an account that
 * stores all transactions, and able to transfer money from one ProAccount to another.
 */
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

    /**
     * @param from which account to withdraw amount
     * @param to which account add amount
     * @param amount how much to transfer
     */
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
