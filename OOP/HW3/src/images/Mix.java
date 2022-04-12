package images;

// class that is a BinaryImageDecorator and represents two base images
// that combined with RGB.mix (where possible)
public class Mix extends BinaryImageDecorator {
    private final double alpha;

    public Mix(Image base1, Image base2, double alpha) {
        super(base1, base2);
        this.alpha = alpha;
    }

    @Override
    protected RGB getCombinedColor(int x, int y) {
        return RGB.mix(base1.get(x, y), base2.get(x, y), alpha);
    }
}
