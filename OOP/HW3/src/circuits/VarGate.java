package circuits;

// class that is a Gate with custom name
// that represent some unknown true/false gate by a boolean variable
public class VarGate extends Gate {
    private final String name;
    private Boolean value;

    public VarGate(String name) {
        super(new Gate[]{});
        this.name = name;
    }

    @Override
    public String getName() {
        return "V" + name;
    }

    /**
     * @return the boolean variable value if set, else throw CircuitException
     * @throws CircuitException if boolean variable has not been set.
     */
    @Override
    protected boolean func(boolean[] inValues) throws CircuitException {
        if (value == null) throw new CircuitException();
        return value;
    }

    public void setVal(boolean value) {
        this.value = value;
    }

    /**
     * @return simplified logic gate,
     * if boolean variable has been set returns the appropriate True/False Gate,
     * else returns `this`.
     */
    @Override
    public Gate simplify() {
        // if not been set yet
        if (value == null) return this;
        // returning True gate if was set to true, else False gate.
        return value ? TrueGate.instance() : FalseGate.instance();
    }
}
