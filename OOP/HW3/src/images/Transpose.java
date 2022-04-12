package images;

// class that represents the transpose of some base image.
public class Transpose implements Image {
    private final Image baseImage;

    public Transpose(Image baseImage) {
        this.baseImage = baseImage;
    }

    @Override
    public int getWidth() {
        return baseImage.getHeight();
    }

    @Override
    public int getHeight() {
        return baseImage.getWidth();
    }

    /**
     * @param x x-axis coordinate
     * @param y y-axis coordinate
     * @return the color of the transposed image in some (x, y) coordinate.
     */
    @Override
    public RGB get(int x, int y) {
        return baseImage.get(y, x);
    }

//    public static void main(String[] args) {
//        Image image = new Transpose(new Gradient(100, 200, RGB.BLUE, RGB.GREEN));
////        Displayer.display(image);
//        image.get(1, 0);
//    }
}
