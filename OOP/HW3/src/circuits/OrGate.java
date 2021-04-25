package circuits;

public class OrGate extends Gate {
    protected Gate[] inGates;

    public OrGate(Gate[] inGates) {
        super(inGates);
        this.inGates = inGates;
    }

    @Override
    protected boolean func(boolean[] inValues) throws CircuitException {
        for (boolean bool : inValues) {
            if (bool)
                return true;
        }
        return false;
    }

    @Override
    public String getName() {
        return Constants.OR_GATE;
    }

    @Override
    public Gate simplify() {
        return null;
    }
}
