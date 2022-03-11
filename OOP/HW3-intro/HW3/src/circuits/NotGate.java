package circuits;

public class NotGate extends Gate {
    protected Gate inGate;

    public NotGate(Gate in) {
        super(new Gate[]{in});
        this.inGate = in;
    }

    @Override
    protected boolean func(boolean[] inValues) throws CircuitException {
        return !inValues[0];
    }

    @Override
    public String getName() {
        return Constants.NOT_GATE;
    }

    @Override
    public Gate simplify() {
        if (inGate.simplify() instanceof VarGate) // VarGate not initialized.
            return this;
        return (inGate.simplify() instanceof TrueGate) ? FalseGate.instance() : TrueGate.instance();
    }
}
