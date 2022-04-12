package circuits;

// Singleton class that is a Gate that represent a False logic gate
public class FalseGate extends Gate {
    private static FalseGate instance;

    private FalseGate() {
        super(new Gate[]{});
    }

    public static Gate instance() {
        if (instance == null)
            instance = new FalseGate();
        return instance;
    }

    // returns false according to FalseGate rules.
    @Override
    protected boolean func(boolean[] inValues) throws CircuitException {
        return false;
    }

    @Override
    public String getName() {
        return "F";
    }

    @Override
    public Gate simplify() {
        // can't bee simplified so returning this gate
        return this;
    }
}
