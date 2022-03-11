package circuits;

public class FalseGate extends Gate {
    private static FalseGate instance = null;

    private FalseGate() {
        super(new FalseGate[]{FalseGate.instance});
    }

    public static Gate instance() {
        if (instance==null)
            instance = new FalseGate();
        return instance;
    }

    @Override
    protected boolean func(boolean[] inValues) throws CircuitException {
        return false;
    }

    @Override
    public String getName() {
        return Constants.FALSE_GATE;
    }

    @Override
    public Gate simplify() {
        return this;
    }
}
