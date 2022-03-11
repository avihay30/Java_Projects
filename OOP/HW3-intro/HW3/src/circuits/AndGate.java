package circuits;

public class AndGate extends Gate {
    protected Gate[] inGates;

    public AndGate(Gate[] inGates) {
        super(inGates);
        this.inGates = inGates;
    }

    @Override
    protected boolean func(boolean[] inValues) throws CircuitException {
        for (boolean bool : inValues)
            if (!bool)
                return false;
        return true;
    }

    @Override
    public String getName() {
        return Constants.AND_GATE;
    }

    @Override
    public Gate simplify() {
        Gate[] gates = new Gate[inGates.length];
        int i = 0;
        for (Gate gate : inGates) {
            Gate simplifiedGate = gate.simplify();
            if (simplifiedGate instanceof FalseGate)
                return simplifiedGate;
            else if (!(simplifiedGate instanceof TrueGate))
                gates[i++] = simplifiedGate; // if unknown gate
        }
        Gate[] nonNullGates = Utils.clearNullFromArray(gates, i);
        if (nonNullGates.length > 1)
            // if more then one unknowns combining them to one AndGate
            return new AndGate(nonNullGates);
        else if (nonNullGates.length == 1)
            return nonNullGates[0]; // if only one gate left.
        return TrueGate.instance(); // if all gates are true.
    }
}
