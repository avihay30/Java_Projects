package images;

// class that is a ImageDecorator and represents the invert color on some base image.
public class Invert extends ImageDecorator {

    public Invert(Image base) {
        super(base);
    }

    /**
     * @param x x-axis coordinate
     * @param y y-axis coordinate
     * @return the color of the inverted image in some (x, y) coordinate.
     * @throws IllegalArgumentException if given x or y is outside the scope of baseImage dimensions
     */
    @Override
    public RGB get(int x, int y) {
        // note: no need to check for valid x,y it will be already thrown by the get of baseImage.
        return super.baseImage.get(x, y).invert();
    }
}
