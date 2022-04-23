package images;

// abstract class that is a super class of classes
// that represents maximum sized image based on two other images.
public abstract class BinaryImageDecorator implements Image {
    protected final Image base1, base2;

    public BinaryImageDecorator(Image base1, Image base2) {
        this.base1 = base1;
        this.base2 = base2;
    }

    @Override
    public int getWidth() {
        return Math.max(base1.getWidth(), base2.getWidth());
    }

    @Override
    public int getHeight() {
        return Math.max(base1.getHeight(), base2.getHeight());
    }

    /**
     * @param x x-axis coordinate
     * @param y y-axis coordinate
     * @return the color of the combined base images using getCombinedColor in (x, y) coordinate.
     */
    @Override
    public RGB get(int x, int y) {
        boolean isBase1In = x <= base1.getWidth() && y <= base1.getHeight();
        boolean isBase2In = x <= base2.getWidth() && y <= base2.getHeight();

        // if both images defined in x,y
        if (isBase1In && isBase2In) {
            return getCombinedColor(x, y);
        } else if (isBase1In) return base1.get(x, y);
        else if (isBase2In) return base2.get(x, y);
        else return RGB.BLACK;
    }

    protected abstract RGB getCombinedColor(int x, int y);
}
