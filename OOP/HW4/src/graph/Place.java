package graph;

// Class that represent a 2 dimensional place in a qube
public class Place {
    private int x, y;

    public Place(int x, int y, int bound) {
        if (x < 0 || x >= bound || y < 0 || y >= bound)
            throw new IllegalArgumentException("x or y are out of bounds");
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * @return true if the specified Object and this have the same coordinates
     */
    @Override
    public boolean equals(Object o) {
        // if given object is not a place object, or it's null
        if (!(o instanceof Place)) return false;

        Place obj = (Place) o;
        // returns true if it's same x and y
        return x == obj.x && y == obj.y;
    }

    // overriding the original hash code from object
    // in consider of x,y places
    @Override
    public int hashCode() {
        return x * 31 + y;
    }

//    public static void main(String[] args) {
//        Place place = new Place(1, 2, 4);
//        Place place1 = new Place(2, 1, 5);
//        System.out.println(place.hashCode());
//        System.out.println(place1.hashCode());
//    }
}
