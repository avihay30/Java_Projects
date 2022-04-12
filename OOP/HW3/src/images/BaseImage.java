package images;

// abstract class that is a super class of classes that represents base images (aren't based on other images).
public abstract class BaseImage implements Image {
    private final int width, height;

    protected BaseImage(int width, int height) {
        this.width = width;
        this.height = height;
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
