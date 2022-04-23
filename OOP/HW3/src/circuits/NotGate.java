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
}
