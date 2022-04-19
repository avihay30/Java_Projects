package images;

// abstract class that is a super class of classes that represents base images (aren't based on other images).
public abstract class BaseImage implements Image {
    protected final RGB color1, color2;
    private final int width, height;

    public BaseImage(int width, int height, RGB color1, RGB color2) {
        this.width = width;
        this.height = height;
        this.color1 = color1;
        this.color2 = color2;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
