package cities;

/***
 * Represents a road that connects between two cities
 */
public class Road {

    private City city1;
    private City city2;
    private int length;

    /***
     * Generates a Road and connects it to the given cities
     * @param city1 to be connected to
     * @param city2 to be connected to
     * @param length of the road
     */
    public Road(City city1, City city2, int length) {
        this.city1 = city1;
        this.city2 = city2;
        this.length = length;

        this.city1.connect(this);
        this.city2.connect(this);
    }

    public City getCity1() {
        return city1;
    }

    public City getCity2() {
        return city2;
    }

    public int getLength() {
        return length;
    }
}
