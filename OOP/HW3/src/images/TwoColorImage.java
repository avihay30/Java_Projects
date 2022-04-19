package images;

// class that is a BaseImage and represents an image
// that is based on two colors according to some given TwoDFunc
public class TwoColorImage extends BaseImage {
    private final TwoDFunc func;

    public TwoColorImage(int width, int height, RGB zero, RGB one, TwoDFunc func) {
        // zeroRGB is color1, oneRGB is color2
        super(width, height, zero, one);
        this.func = func;
    }

    /**
     * @param x x-axis coordinate
     * @param y y-axis coordinate
     * @return the color of the image in some (x, y) coordinate.
     */
    @Override
    public RGB get(int x, int y) {
        // normalizing x,y
        double xNorm = (double) x / getWidth();
        double yNorm = (double) y / getHeight();
        double alpha = func.f(xNorm, yNorm);
        // limiting alpha to upper (=1) and lower (=0) bounds
        alpha = Math.min(alpha, 1);
        alpha = Math.max(alpha, 0);
        // alpha = 0 is zeroRGB color so we send to mix the opposite alpha
        return RGB.mix(color1, color2, 1 - alpha);
    }
    
//    public static void main(String[] args) {
////    	Image i = new TwoColorImage(200, 100, RGB.BLACK, 
////    			RGB.RED, new Func1());
////    			Displayer.display(i);
//
//		Image i1 = new Gradient(500, 500, RGB.BLUE, RGB.BLACK);
//		Image i2 = new Transpose(new Gradient(500, 500, RGB.RED, RGB.BLACK));
//		Image i3 = new Mix(i1, i2, 0.5);
//		Image i4 = new Circle(350, 150, new RGB(1, 1, 0), RGB.BLACK);
//		Image i5 = new Circle(200, 100, new RGB(0, 0.5, 1), RGB.BLACK);
//		Image i6 = new Circle(500, 200, RGB.WHITE, RGB.BLACK);
//	
//		Image i7 = new Superpose(i3, i4);
//		Image i8 = new Superpose(i5, i6);
//		Image i9 = new Superpose(i7, i8);
//	
//		Displayer.display(i9);
//
//	}
}
