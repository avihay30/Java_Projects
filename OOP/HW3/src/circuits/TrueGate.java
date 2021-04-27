package circuits;

public class TrueGate extends Gate {
    private static TrueGate instance = null;

    private TrueGate() {
        super(new TrueGate[]{TrueGate.instance});
    }

    public static Gate instance() {
        if (instance==null)
            instance = new TrueGate();
        return instance;
    }

    @Override
    protected boolean func(boolean[] inValues) throws CircuitException {
        return true;
    }

    @Override
    public String getName() {
        return Constants.TRUE_GATE;
    }

    @Override
    public Gate simplify() {
        return this;
    }
}
