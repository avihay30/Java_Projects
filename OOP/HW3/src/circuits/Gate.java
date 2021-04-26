package circuits;

public abstract class Gate {
    protected Gate[] inGates;

    public Gate(Gate[] inGates) {
        this.inGates = inGates;
    }

    public boolean calc() throws CircuitException {
        int i = 0;
        boolean[] gatesCalc = new boolean[inGates.length];
        // if true/false gates left in tree.
        if (inGates.length == 1 && inGates[0] == null)
            return this.func(new boolean[] {});

        for (Gate gate : inGates)
            gatesCalc[i++] = gate.calc(); // increment after assignment.
        return func(gatesCalc);
    }

    protected abstract boolean func(boolean[] inValues) throws CircuitException;

    public abstract String getName();

    public abstract Gate simplify();

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getName());

        if (inGates.length > 1 || (inGates.length != 0 && inGates[0] != null)) {
            stringBuilder.append("[");
            for (Gate gate : inGates) {
                stringBuilder.append(gate.toString()).append(", ");
            }
            stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "]");
        }
        return stringBuilder.toString();
    }
}
