package images;

// class that is a BinaryImageDecorator and represents two base images
// that combined with RGB.superpose (where possible)
public class Superpose extends BinaryImageDecorator {

    public Superpose(Image base1, Image base2) {
        super(base1, base2);
    }

    @Override
    protected RGB getCombinedColor(int x, int y) {
        return RGB.superpose(base1.get(x, y), base2.get(x, y));
    }
}
