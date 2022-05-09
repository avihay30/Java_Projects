package shop;

// enum class that holds all guitar types
public enum Type {
    ACOUSTIC, ELECTRIC, CLASSICAL;

    /**
     * @return Camelcase String of this type
     */
    @Override
    public String toString() {
        switch (this) {
            case ACOUSTIC:
                return "Acoustic";
            case ELECTRIC:
                return "Electric";
            case CLASSICAL:
                return "Classical";
            default:
                return null;
        }
    }
}
