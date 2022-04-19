package images;

// class that represents color in RGB standard
public class RGB {
    // some useful RGB color constants
    public static final RGB BLACK = new RGB(0);
    public static final RGB WHITE = new RGB(1);
    public static final RGB RED = new RGB(1, 0, 0);
    public static final RGB GREEN = new RGB(0, 1, 0);
    public static final RGB BLUE = new RGB(0, 0, 1);

    private final double red, green, blue;

    public RGB(double red, double green, double blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public RGB(double grey) {
        this.red = grey;
        this.green = grey;
        this.blue = grey;
    }

    public double getRed() {
        return red;
    }

    public double getGreen() {
        return green;
    }

    public double getBlue() {
        return blue;
    }

    /**
     * @return RGB color that is inverted to `this` color
     */
    public RGB invert() {
        return new RGB(1 - red, 1 - green, 1 - blue);
    }

    /**
     * @param filter the color of filter
     * @return new RGB color that is multiplied by `this` base color and filter color
     */
    public RGB filter(RGB filter) {
        return new RGB(filter.red * red, filter.green * green, filter.blue * blue);
    }

    /**
     * @return new RGB color that is the sum of given two given RGB colors, up to 1 on each base color
     */
    public static RGB superpose(RGB rgb1, RGB rgb2) {
        return new RGB(
                Math.min(rgb1.red + rgb2.red, 1),
                Math.min(rgb1.green + rgb2.green, 1),
                Math.min(rgb1.blue + rgb2.blue, 1));
    }

    /**
     * @return new RGB color that is a calculated average of two given RGB colors
     * according to the formula => alpha * color1 + (1-alpha) * color2
     */
    public static RGB mix(RGB rgb1, RGB rgb2, double alpha) {
        return new RGB(
                alpha * rgb1.red + (1 - alpha) * rgb2.red,
                alpha * rgb1.green + (1 - alpha) * rgb2.green,
                alpha * rgb1.blue + (1 - alpha) * rgb2.blue);
    }

    public String toString() {
        return String.format("<%.4f, %.4f, %.4f>", red, green, blue);
    }

//    public static void main(String[] args) {
//        RGB rgb = new RGB(1,1,1);
//        System.out.println(rgb.invert());
//        System.out.println(rgb.filter(new RGB(0.5,0.5,0.3)));
//        System.out.println(RGB.superpose(new RGB(0.5,0.5,0.3), new RGB(0.3,0.7,0.3)));
//        System.out.println(rgb);
//        
        
//        Image i = new Gradient(200, 100, RGB.RED, 
//        		new RGB(1, 1, 0));
//        		Displayer.display(i);
//        
//        Image i = new Circle(200, 100, 70, 70, 90, RGB.BLUE, 
//        		new RGB(0.5, 0, 0.5));
//        Displayer.display(i);
//        
//        Image i = new Circle(120, 60, RGB.WHITE, RGB.BLACK);
//        Image i2 = new Filter(i, new RGB(0.5, 0, 1));
//        Displayer.display(i2);
//        
//        Image i = new Invert(new Circle(120, 60, RGB.RED, RGB.BLACK));
//        Displayer.display(i);
        
//        Image i = new Transpose(new Gradient(100, 200, RGB.BLUE,
//        		 RGB.GREEN));	
//        Displayer.display(i);
        
//        Image i1 = new Gradient(100, 150, RGB.RED,
//        		RGB.RED);
//        		Image i2 = new Gradient(200, 100, RGB.BLUE,
//        		RGB.BLUE);
//        		Image i = new Superpose(i1,  i2);
//        		Displayer.display(i);
        
//        Image i1 = new Gradient(100, 150, RGB.RED, 
//        		RGB.WHITE);
//        		Image i2 = new Gradient(200, 100, RGB.BLUE, 
//        		RGB.GREEN);
//        		Image i = new Mix(i1,  i2, 0.7);
//        		Displayer.display(i);
        
//		Image i = new TwoColorImage(200, 100, RGB.BLACK, 
//				RGB.RED, new Func1());
//				Displayer.display(i);

//        Image i1 = new Gradient(500, 500, RGB.BLUE, RGB.BLACK);
//        Image i2 = new Transpose(new Gradient(500, 500, RGB.RED, RGB.BLACK));
//        Image i3 = new Mix(i1, i2, 0.5);
//        Image i4 = new Circle(350, 150, new RGB(1, 1, 0), RGB.BLACK);
//        Image i5 = new Circle(200, 100, new RGB(0, 0.5, 1), RGB.BLACK);
//        Image i6 = new Circle(500, 200, RGB.WHITE, RGB.BLACK);
//
//        Image i7 = new Superpose(i3, i4);
//        Image i8 = new Superpose(i5, i6);
//        Image i9 = new Superpose(i7, i8);
//
//        Displayer.display(i9);

        
//    }
}
