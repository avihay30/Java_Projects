package circuits;

public class Utils {
    static Gate[] clearNullFromArray(Gate[] gates, int sizeOfNonNull) {
        Gate[] nonNullGates = new Gate[sizeOfNonNull];
        int i = 0;
        for (Gate gate : gates)
            if (gate != null)
                nonNullGates[i++] = gate;
        return nonNullGates;
    }
}
