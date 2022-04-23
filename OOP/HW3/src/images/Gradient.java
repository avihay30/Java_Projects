package images;

// class that is a BaseImage and represent combination of left and right colors in some image
public class Gradient extends BaseImage {

    public Gradient(int width, int height, RGB start, RGB end) {
        // leftRGB is color1, leftRGB is color2
        super(width, height, start, end);
    }

    /**
     * @param x x-axis coordinate
     * @param y y-axis coordinate
     * @return the color of the image in some (x, y) coordinate.
     */
    @Override
    public RGB get(int x, int y) {
        // (x / imageWidth) = 0 => x = 0, and it's the left color.
        // so we send to mix the opposite calculation
        return RGB.mix(color1, color2, 1 - (double) x / getWidth());
    }
}
