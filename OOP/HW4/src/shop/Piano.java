package shop;

// class that is an Instrument and represent a Piano with some octaves
public class Piano extends Instrument {
    private final int octaves;

    public Piano(String company, int price, int octaves) {
        super(company, price);
        this.octaves = octaves;
    }

    public int getOctaves() {
        return octaves;
    }

    @Override
    public String toString() {
        return String.format("Piano(%d octaves)", octaves) + CompanyPriceString();
    }
}
