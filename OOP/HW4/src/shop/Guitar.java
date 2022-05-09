package shop;

// class that is an Instrument and represent a guitar with some Type
public class Guitar extends Instrument {
    private final Type type;

    public Guitar(String company, int price, Type type) {
        super(company, price);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("Guitar(%s)", type) + CompanyPriceString();
    }
}
