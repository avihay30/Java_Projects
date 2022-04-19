package circuits;

// class that is a Gate that represent a Or logic gate
public class OrGate extends Gate {

    public OrGate(Gate[] inGates) {
        super(inGates);
    }

    // calculate all boolean values according to OrGate rules.
    @Override
    protected boolean func(boolean[] inValues) throws CircuitException {
        // doing "Or" between all inValues,
        // if some one of them = true => returns true.
        for (boolean value : inValues) {
            if (value) return true; // if some value is true
        }
        return false;
    }

    @Override
    public String getName() {
        return "OR";
    }

    /**
     * @return simplified logic gate according to Or logic
     */
    @Override
    public Gate simplify() {
        int count = 0, simplifiedIndex = 0;
        Gate[] inSimplified = new Gate[inGates.length];

        /* simplifying all children into inSimplified.
           and if one of the children is true, returns it.
           and count how many non false (unknown) children
         */
        for (int i = 0; i < inGates.length; i++) {
            inSimplified[i] = inGates[i].simplify();
            if (inSimplified[i] instanceof TrueGate) return inSimplified[i];
            if (!(inSimplified[i] instanceof FalseGate)) count++;
        }

        //// Ignoring all false children. ////
        // if only False gates are left after simplify,
        // under the assumption that the gate has at least one gate
        if (count == 0) return FalseGate.instance();

        Gate[] simplifiedArr = new Gate[count];
        for (Gate inGate : inSimplified) {
            if (!(inGate instanceof FalseGate)) simplifiedArr[simplifiedIndex++] = inGate;
        }

        // if only one non false child left
        if (count == 1) return simplifiedArr[0];

        // if more than one unknown combining them to one OrGate
        return new OrGate(simplifiedArr);
    }
}
