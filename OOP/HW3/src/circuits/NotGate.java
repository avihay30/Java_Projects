package circuits;

// class that is a Gate that represent a Not logic gate
public class NotGate extends Gate {

    public NotGate(Gate in) {
        super(new Gate[]{in});
    }

    // inverting child gate boolean value according to NotGate rules.
    @Override
    protected boolean func(boolean[] inValues) throws CircuitException {
        // inverting the boolean value that was set to the child
        return !inValues[0];
    }

    @Override
    public String getName() {
        return "NOT";
    }

    /**
     * @return simplified logic gate according to not logic
     */
    @Override
    public Gate simplify() {
        Gate simplifiedGate = inGates[0].simplify();

        // if simplifiedGate is a True gate returning False gate
        if (simplifiedGate instanceof TrueGate)
            return FalseGate.instance();
            // if simplifiedGate is a False gate returning True gate
        else if (simplifiedGate instanceof FalseGate)
            return TrueGate.instance();
            // if simplifiedGate is a Not gate returning the grandson
        else if (inGates[0] instanceof NotGate)
            return inGates[0].inGates[0];
        // else simplifiedGate has some Var gate
        // that has not been set => can't simplify.
        return this;
    }

//    public static void main(String[] args) {
////        Gate gate = new NotGate(new NotGate(TrueGate.instance()));
////        Gate gate1 = new NotGate(new NotGate(new NotGate(new NotGate(new VarGate("aaa")))));
////        Gate gate2 = new NotGate(new NotGate(new NotGate(new NotGate(FalseGate.instance()))));
////        System.out.println(gate.simplify());
////        System.out.println(gate1.simplify());
////        System.out.println(gate2.simplify());
////
////        // not ->
//
//        VarGate v1 = new VarGate("1");
//        VarGate v2 = new VarGate("2");
//
//        //Gate g1 = new Or2Gate(FalseGate.instance(), TrueGate.instance());
//        //Gate g2 = new Or2Gate(v1, new NotGate(v2));
//        //Gate out = new AndGate(new Gate[] { g1, g2, TrueGate.instance() });
//
//
//        Gate g1 = new Or2Gate(TrueGate.instance(), FalseGate.instance());
//        Gate g2 = new Or2Gate(v1, v1);
//        Gate out = new OrGate(new Gate[]{g2, FalseGate.instance()});
//
//
//        //v1.setVal(false);
//        //	v2.setVal(true);
//        //System.out.println(out + " = " + out.calc());
//        System.out.println(out + " = " + out.simplify());
//    }
}
