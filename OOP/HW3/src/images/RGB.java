package images;

// class that represents color in RGB standard
public class RGB {
    // some useful RGB color constants
    public static final RGB BLACK = new RGB(0);
    public static final RGB WHITE = new RGB(1);
    public static final RGB RED = new RGB(1, 0, 0);
    public static final RGB GREEN = new RGB(0, 1, 0);
    public static final RGB BLUE = new RGB(0, 0, 1);

    private final double red, green, blue;

    public RGB(double red, double green, double blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public RGB(double grey) {
        this.red = grey;
        this.green = grey;
        this.blue = grey;
    }

    public double getRed() {
        return red;
    }

    public double getGreen() {
        return green;
    }

    public double getBlue() {
        return blue;
    }

    /**
     * @return RGB color that is inverted to `this` color
     */
    public RGB invert() {
        return new RGB(1 - red, 1 - green, 1 - blue);
    }

    /**
     * @param filter the color of filter
     * @return new RGB color that is multiplied by `this` base color and filter color
     */
    public RGB filter(RGB filter) {
        return new RGB(filter.red * red, filter.green * green, filter.blue * blue);
    }

    /**
     * @return new RGB color that is the sum of given two given RGB colors, up to 1 on each base color
     */
    public static RGB superpose(RGB rgb1, RGB rgb2) {
        return new RGB(
                Math.min(rgb1.red + rgb2.red, 1),
                Math.min(rgb1.green + rgb2.green, 1),
                Math.min(rgb1.blue + rgb2.blue, 1));
    }

    /**
     * @return new RGB color that is a calculated average of two given RGB colors
     * according to the formula => alpha * color1 + (1-alpha) * color2
     */
    public static RGB mix(RGB rgb1, RGB rgb2, double alpha) {
        return new RGB(
                alpha * rgb1.red + (1 - alpha) * rgb2.red,
                alpha * rgb1.green + (1 - alpha) * rgb2.green,
                alpha * rgb1.blue + (1 - alpha) * rgb2.blue);
    }

    public String toString() {
        return String.format("<%.4f, %.4f, %.4f>", red, green, blue);
    }
}
