package images;

// class that is a ImageDecorator and represents filter color on some base image.
public class Filter extends ImageDecorator {
    private final RGB filterColor;

    public Filter(Image base, RGB filter) {
        super(base);
        this.filterColor = filter;
    }

    /**
     * @param x x-axis coordinate
     * @param y y-axis coordinate
     * @return the color of the filtered image in some (x, y) coordinate.
     */
    @Override
    public RGB get(int x, int y) {
        // returning filtered color
        return super.baseImage.get(x, y).filter(filterColor);
    }
}
