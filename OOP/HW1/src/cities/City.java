package cities;

/***
 * Represents a city with a connected roads (if any) to/from it
 */
public class City {

    private String name;
    private Road[] roads;
    private int numRoads;

    /***
     * Generates a city named `name`
     * @param name of the City to be
     */
    public City(String name) {
        this.name = name;
    }

    /***
     * Connects the city to new road r
     * @param r Road to connect
     */
    public void connect(Road r) {
        // init roads, under the assumption that a city is connected to 10 roads maximum.
        if (roads == null) {
            roads = new Road[10];
        }
        roads[numRoads++] = r;
    }

    /***
     * Calculates the closest city to `this` City
     * @return nearest city if any, null otherwise
     */
    public City nearestCity() {
        if (roads == null) return null;

        int minDistance = roads[0].getLength();
        City closestCity = getOtherCityInRoad(roads[0]);
        for (int i = 1; i < numRoads; i++) {
            if (roads[i].getLength() < minDistance) {
                minDistance = roads[i].getLength();
                closestCity = getOtherCityInRoad(roads[i]);
            }
        }

        return closestCity;
    }

    public String toString() {
        return name;
    }

    /***
     * A utility getter for other City in a road
     * @param road to get from the other city
     * @return the other City, not `this` City
     */
    private City getOtherCityInRoad(Road road) {
        // if city1 is me, return other city
        if (road.getCity1() == this)
            return road.getCity2();
        return road.getCity1();
    }
}
