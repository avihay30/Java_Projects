package images;

// abstract class that is a super class of classes
// that represents maximum sized image based on two other images.
public abstract class BinaryImageDecorator implements Image {
    protected final Image base1, base2;

    protected BinaryImageDecorator(Image base1, Image base2) {
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
}
