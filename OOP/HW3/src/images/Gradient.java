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
     */
    @Override
    public RGB get(int x, int y) {
        // (x / imageWidth) = 0 => x = 0, and it's the left color so we send to mix the opposite calculation
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
