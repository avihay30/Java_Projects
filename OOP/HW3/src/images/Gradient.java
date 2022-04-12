package images;

// class that is a BaseImage and represent combination of left and right colors in some image
public class Gradient extends BaseImage {
    private final RGB leftRGB, rightRGB;

    public Gradient(int width, int height, RGB start, RGB end) {
        super(width, height);
        leftRGB = start;
        rightRGB = end;
    }

    /**
     * @param x x-axis coordinate
     * @param y y-axis coordinate
     * @return the color of the image in some (x, y) coordinate.
     * @throws IllegalArgumentException if given x is outside the scope of image-width dimensions
     */
    @Override
    public RGB get(int x, int y) {
        // check if is valid x, throw argument exception if not
        if (x > getWidth() || x < 0) throw new IllegalArgumentException("x should be in image-width dimensions");

        // x/imageWidth = 0 => x = 0, and it's the left color so we send to mix the opposite calculation
        return RGB.mix(leftRGB, rightRGB, 1 - (double) x/getWidth());
    }

//    public static void main(String[] args) {
//        RGB g1 = new RGB(0.2, 0.7, 0.111);
//        RGB g2 = new RGB(0.9, 0.3, 0.75);
//
//        Image i = new Gradient(20, 10, g1, g2);
//        i.get(3, 2);
//    }
}
