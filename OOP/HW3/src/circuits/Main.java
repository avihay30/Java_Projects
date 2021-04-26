package circuits;

public class Main {
    public static void main(String[] args) throws CircuitException {
//        Gate[] gates =  { TrueGate.instance(), new NotGate(FalseGate.instance()), TrueGate.instance() };
//        Gate[] gates =  { TrueGate.instance() , new NotGate(TrueGate.instance())};
//        Gate a = new AndGate(gates);
//        System.out.println(a.calc());

//        VarGate v1 = new VarGate("1");
//        VarGate v2 = new VarGate("2");
//        Gate out = new AndGate(new Gate[]{v1, v2, TrueGate.instance()});
//        v1.setVal(false);
//        v2.setVal(true);
//        System.out.println(out + " = " + out.calc());

        VarGate v1 = new VarGate("1");
        VarGate v2 = new VarGate("2");

        Gate g1 = new Or2Gate(FalseGate.instance(), TrueGate.instance());
        Gate g2 = new Or2Gate(v1, new NotGate(v2));
        Gate out = new AndGate(new Gate[] { g1, g2, TrueGate.instance() });

        v1.setVal(false);
        v2.setVal(true);
        System.out.println(out + " = " + out.calc());

    }
}
