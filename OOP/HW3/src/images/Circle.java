package images;

// class that is a BaseImage and represents a circle shape in image
public class Circle extends BaseImage {
    private final int centerX, centerY, radius;
    private final RGB center, outside;

    public Circle(int width, int height, int centerX, int centerY, int radius, RGB center, RGB outside) {
        super(width, height);
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.center = center;
        this.outside = outside;
    }

    public Circle(int size, int radius, RGB center, RGB outside) {
        super(size, size);
        centerX = size / 2;
        centerY = size / 2;
        this.radius = radius;
        this.center = center;
        this.outside = outside;
    }
    /**
     * @param x x-axis coordinate
     * @param y y-axis coordinate
     * @return the color of the image in some (x, y) coordinate.
     */
    @Override
    public RGB get(int x, int y) {
        // calculating distance from center => [d=√((x_2-x_1)²+(y_2-y_1)²)]
        double d = Math.sqrt(Math.pow(centerX - x, 2) + Math.pow(centerY - y, 2));
        /* calculating the amount of mix between center <-> outside colors
           and the mix method should get alpha in range [0,1] */
        double dPercentage = Math.min(d / radius, 1);

        // dPercentage = 0 is center color so we send to mix the opposite dPercentage
        return RGB.mix(center, outside, 1 - dPercentage);
    }

//    public static void main(String[] args) {
//        RGB g1 = new RGB(0.2, 0.7, 0.111);
//        RGB g2 = new RGB(0.9, 0.3, 0.75);
//        RGB g3 = new RGB(0.3, 0.6, 0.21);
//        Image circle = new Circle(20, 10, 5, 8, 10, g1, g2);
//        circle.get(4, 7); // new RGB(0.29899495, 0.643431, 0.201368), "inside circle");
//        circle.get(19, 9); // , g2, "outside circle");
//    }
}
