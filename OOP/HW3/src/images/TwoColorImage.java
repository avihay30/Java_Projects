package images;

// class that is a BaseImage and represents an image
// that is based on two colors according to some given TwoDFunc
public class TwoColorImage extends BaseImage {
    private final TwoDFunc func;

    public TwoColorImage(int width, int height, RGB zero, RGB one, TwoDFunc func) {
        // zeroRGB is color1, oneRGB is color2
        super(width, height, zero, one);
        this.func = func;
    }

    /**
     * @param x x-axis coordinate
     * @param y y-axis coordinate
     * @return the color of the image in some (x, y) coordinate.
     */
    @Override
    public RGB get(int x, int y) {
        // normalizing x,y
        double xNorm = (double) x / getWidth();
        double yNorm = (double) y / getHeight();
        double alpha = func.f(xNorm, yNorm);
        // limiting alpha to upper (=1) and lower (=0) bounds
        alpha = Math.min(alpha, 1);
        alpha = Math.max(alpha, 0);
        // alpha = 0 is zeroRGB color so we send to mix the opposite alpha
        return RGB.mix(color1, color2, 1 - alpha);
    }
}
