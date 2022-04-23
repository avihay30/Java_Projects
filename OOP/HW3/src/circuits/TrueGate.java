package circuits;

// Singleton class that is a Gate that represent a True logic gate
public class TrueGate extends Gate {
    private static TrueGate instance;

    private TrueGate() {
        super(new Gate[]{});
    }

    public static Gate instance() {
        if (instance == null)
            instance = new TrueGate();
        return instance;
    }

    // returns true according to TrueGate rules.
    @Override
    protected boolean func(boolean[] inValues) throws CircuitException {
        return true;
    }

    @Override
    public String getName() {
        return "T";
    }

    @Override
    public Gate simplify() {
        // can't bee simplified so returning this gate
        return this;
    }
}
