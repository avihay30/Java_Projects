package circuits;

// Abstract class that is a super class of classes that represents some logic gates (not, or, etc...)
public abstract class Gate {
    // all inner gates
    protected Gate[] inGates;

    public Gate(Gate[] inGates) {
        this.inGates = new Gate[inGates.length];
        for (int i = 0; i < inGates.length; i++) {
            this.inGates[i] = inGates[i];
        }
    }

    /**
     * @return calculate the boolean value of the gate
     * @throws CircuitException if not all gates in inGates has been set with values
     */
    public boolean calc() throws CircuitException {
        boolean[] inValues = new boolean[inGates.length];
        for (int i = 0; i < inGates.length; i++) {
            inValues[i] = inGates[i].calc();
        }
        return func(inValues);
    }

    // calculate all boolean values according to gate rules.
    protected abstract boolean func(boolean[] inValues) throws CircuitException;

    public abstract String getName();

    /**
     * @return simplified logic gate
     */
    public abstract Gate simplify();

    public String toString() {
        // if gate has no inGates
        if (inGates.length == 0) return getName();

        // if gate has inGates, adding main gate name
        StringBuilder stringBuilder = new StringBuilder(getName());

        // iterating over all inner gates
        stringBuilder.append("[");
        for (Gate inGate : inGates) {
            stringBuilder.append(inGate.toString()).append(", ");
        }
        // replacing last comma and space with "]"
        stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "]");

        return stringBuilder.toString();
    }
}
