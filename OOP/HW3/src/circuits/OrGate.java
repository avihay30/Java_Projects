package circuits;

import java.util.ArrayList;

public class OrGate extends Gate {
    protected Gate[] inGates;

    public OrGate(Gate[] inGates) {
        super(inGates);
        this.inGates = inGates;
    }

    @Override
    protected boolean func(boolean[] inValues) throws CircuitException {
        for (boolean bool : inValues)
            if (bool)
                return true;
        return false;
    }

    @Override
    public String getName() {
        return Constants.OR_GATE;
    }

    @Override
    public Gate simplify() {
        Gate[] gates = new Gate[inGates.length];
        int i = 0;
        for (Gate gate : inGates) {
            Gate simplifiedGate = gate.simplify();
            if (simplifiedGate instanceof TrueGate)
                return simplifiedGate;
            else if (!(simplifiedGate instanceof FalseGate))
                gates[i++] = simplifiedGate; // if unknown gate
        }
        Gate[] nonNullGates = Utils.clearNullFromArray(gates, i);
        if (nonNullGates.length > 1)
            return new OrGate(nonNullGates);
        else if (nonNullGates.length == 1)
            return nonNullGates[0];
        return FalseGate.instance();
    }
}
