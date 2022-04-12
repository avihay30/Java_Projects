package images;

// abstract class that is a super class of classes,
// that represents decorated image based on other image with the same dimensions.
public abstract class ImageDecorator implements Image {
    protected final Image baseImage;

    public ImageDecorator(Image baseImage) {
        this.baseImage = baseImage;
    }

    @Override
    public int getWidth() {
        return baseImage.getWidth();
    }

    @Override
    public int getHeight() {
        return baseImage.getHeight();
    }
}
