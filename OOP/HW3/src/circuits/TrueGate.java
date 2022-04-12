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
    public Gate si:mplify() {
        // can't bee simplified so returning this gate
        return this;
    }

//    public static void main(String[] args) throws CircuitException {
//        VarGate v1 = new VarGate("1");
//        VarGate v2 = new VarGate("2");
//
//        Gate g1 = new Or2Gate(FalseGate.instance(), TrueGate.instance());
//        Gate g2 = new Or2Gate(v1, new NotGate(v2));
//        Gate out = new AndGate(new Gate[]{g1, g2, TrueGate.instance()});
//
////        v1.setVal(false);
////        v2.setVal(true);
////        System.out.println(out + " = " + out.calc());
//
//        System.out.println(out + " = " + out.simplify());
//
//        Gate xx = new NotGate(new NotGate(new OrGate(new Gate[]{v1, FalseGate.instance()})));
//        System.out.println(xx + " = " + xx.simplify());
//
//        Gate g3 = FalseGate.instance();
//        System.out.println(g3 + " = " + g3.calc());
//    }
}
