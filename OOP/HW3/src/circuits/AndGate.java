package circuits;

// class that is a Gate that represent a And logic gate
public class AndGate extends Gate {

    public AndGate(Gate[] inGates) {
        super(inGates);
    }

    // calculate all boolean values according to AndGate rules.
    @Override
    protected boolean func(boolean[] inValues) throws CircuitException {
        // doing "And" between all inValues,
        // if some one of them = false => returns false.
        for (boolean value : inValues) {
            if (!value) return false; // if some value is false
        }
        return true;
    }

    @Override
    public String getName() {
        return "AND";
    }

    /**
     * @return simplified logic gate according to And logic
     */
    @Override
    public Gate simplify() {
        int count = 0, simplifiedIndex = 0;
        Gate[] inSimplified = new Gate[inGates.length];

        /* simplifying all children into inSimplified.
           and if one of the children is False, returns it.
           and count how many non true (unknown) children
         */
        for (int i = 0; i < inGates.length; i++) {
            inSimplified[i] = inGates[i].simplify();
            if (inSimplified[i] instanceof FalseGate) return inSimplified[i];
            if (!(inSimplified[i] instanceof TrueGate)) count++;
        }

        //// Ignoring all true children. ////
        // if only True gates are left after simplify,
        // under the assumption that the gate has at least one gate
        if (count == 0) return TrueGate.instance();

        Gate[] simplifiedArr = new Gate[count];
        for (Gate inGate : inSimplified) {
            if (!(inGate instanceof TrueGate)) simplifiedArr[simplifiedIndex++] = inGate;
        }

        // if only one non True child left
        if (count == 1) return simplifiedArr[0];

        // if more than one unknown combining them to one OrGate
        return new AndGate(simplifiedArr);
    }
}
