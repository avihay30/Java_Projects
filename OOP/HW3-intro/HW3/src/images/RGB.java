package images;

public class RGB {
    public static final RGB BLACK = new RGB(0);
    public static final RGB WHITE = new RGB(1);
    public static final RGB RED = new RGB(1, 0, 0);
    public static final RGB GREEN = new RGB(0, 1, 0);
    public static final RGB BLUE = new RGB(0, 0, 1);
    private double red = 0.0;
    private double green = 0.0;
    private double blue = 0.0;

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
        return this.red;
    }

    public double getBlue() {
        return this.blue;
    }

    public double getGreen() {
        return this.green;
    }

    public RGB invert() {
        return new RGB(1 - this.red, 1 - this.green, 1 - this.blue);
    }

    public RGB filter(RGB filter) {
        return new RGB(
                this.red * filter.getRed(),
                this.green * filter.getGreen(),
                this.blue * filter.getBlue());
    }

    public static RGB superpose(RGB rgb1, RGB rgb2) {
        return new RGB(
                Math.min(1, rgb1.getRed() + rgb2.getRed()),
                Math.min(1, rgb1.getBlue() + rgb2.getGreen()),
                Math.min(1, rgb1.getBlue() + rgb2.getBlue()));
    }

    public static RGB mix(RGB rgb1, RGB rgb2, double alpha) {
        return new RGB(
                alpha * rgb1.getRed() + (1 - alpha) * rgb2.getRed(),
                alpha * rgb1.getGreen() + (1 - alpha) * rgb2.getGreen(),
                alpha * rgb1.getBlue() + (1 - alpha) * rgb2.getBlue());
    }


    public String toString() {
        return String.format(
                "<%.4f, %.4f, %.4f>",  this.red, this.green, this.blue);
    }
}
