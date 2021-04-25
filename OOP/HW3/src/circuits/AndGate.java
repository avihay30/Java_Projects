package circuits;

public class AndGate extends Gate {
    protected Gate[] inGates;

    public AndGate(Gate[] inGates) {
        super(inGates);
        this.inGates = inGates;
    }

    @Override
    protected boolean func(boolean[] inValues) throws CircuitException {
        for (boolean bool : inValues) {
            if (!bool)
                return false;
        }
        return true;
    }

    @Override
    public String getName() {
        return Constants.AND_GATE;
    }

    @Override
    public Gate simplify() {
        return null;
    }
}
