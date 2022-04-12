package images;

// class that is a ImageDecorator and represents filter color on some base image.
public class Filter extends ImageDecorator {
//    private final Image baseImage;
    private final RGB filterColor;

    public Filter(Image base, RGB filter) {
        super(base);
        this.filterColor = filter;
    }

    /**
     * @param x x-axis coordinate
     * @param y y-axis coordinate
     * @return the color of the filtered image in some (x, y) coordinate.
     * @throws IllegalArgumentException if given x or y is outside the scope of baseImage dimensions
     */
    @Override
    public RGB get(int x, int y) {
        // note: no need to check for valid x,y it will be already thrown by the get of baseImage.
        // returning filtered color
        return super.baseImage.get(x, y).filter(filterColor);
    }
}
