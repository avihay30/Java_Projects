package images;

// class that is a BaseImage and represents a circle shape in image
public class Circle extends BaseImage {
    private final int centerX, centerY, radius;

    public Circle(int width, int height, int centerX, int centerY, int radius, RGB center, RGB outside) {
        // center is color1, outside is color2
        super(width, height, center, outside);
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    public Circle(int size, int radius, RGB center, RGB outside) {
        // center is color1, outside is color2
        super(size, size, center, outside);
        centerX = size / 2;
        centerY = size / 2;
        this.radius = radius;
    }

    /**
     * @param x x-axis coordinate
     * @param y y-axis coordinate
     * @return the color of the image in some (x, y) coordinate.
     */
    @Override
    public RGB get(int x, int y) {
        // calculating distance from center => [d=sqrt((x_2-x_1)^2+(y_2-y_1)^2)]
        double d = Math.sqrt(Math.pow(centerX - x, 2) + Math.pow(centerY - y, 2));
        /* calculating the amount of mix between center <-> outside colors
           and the mix method should get alpha in range [0,1] */
        double dPercentage = Math.min(d / radius, 1);

        // dPercentage = 0 is center color. so we send to mix the opposite dPercentage
        return RGB.mix(color1, color2, 1 - dPercentage);
    }
}
