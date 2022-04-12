package images;

// class that is a BinaryImageDecorator and represents two base images
// that combined with RGB.mix (where possible)
public class Mix extends BinaryImageDecorator {
    private final double alpha;

    public Mix(Image base1, Image base2, double alpha) {
        super(base1, base2);
        this.alpha = alpha;
    }

    /**
     * @param x x-axis coordinate
     * @param y y-axis coordinate
     * @return the color of the combined base images using mix in some (x, y) coordinate.
     * @throws IllegalArgumentException if given x or y is outside the scope of some baseImage dimensions
     */
    @Override
    public RGB get(int x, int y) {
        boolean isBase1In = x <= base1.getWidth() && y <= base1.getHeight();
        boolean isBase2In = x <= base2.getWidth() && y <= base2.getHeight();

        // if both images defined in x,y
        if (isBase1In && isBase2In) {
            return RGB.mix(base1.get(x, y), base2.get(x, y), alpha);
        }
        else if (isBase1In) return base1.get(x, y);
        else if (isBase2In) return base2.get(x, y);
        else return RGB.BLACK;
    }
}