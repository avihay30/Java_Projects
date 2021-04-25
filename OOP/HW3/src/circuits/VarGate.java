package circuits;

public class VarGate extends Gate {
    protected Gate inGate;
    protected String name;

    public VarGate(String name) {
        super(new Gate[]{});
        this.name = name;
    }

    @Override
    protected boolean func(boolean[] inValues) throws CircuitException {
        if (inGate == null)
            throw new CircuitException();
        return inGate.func(new boolean[]{}); //todo: check all places if good
    }

    @Override
    public String getName() {
        return Constants.VAR_GATE + name;
    }

    @Override
    public Gate simplify() {
        return null;
    }

    public void setVal(boolean value) {
        if (value)
            inGate = TrueGate.instance();
        else
            inGate = FalseGate.instance();
    }
}
