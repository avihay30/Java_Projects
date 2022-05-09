package cities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

// class that represent some country and it's cities
public class Country implements Comparable<Country> {

    private Set<City> cities;
    private String name;

    public Country(String name) {
        cities = new TreeSet<>();
        this.name = name;
    }

    /**
     * @param city to add to Country's Set if it's the same Country
     *             else throw IllegalArgumentException
     */
    public void addCity(City city) {
        if (!equals(city.getCountry()))
            throw new IllegalArgumentException(
                    "Invalid Country doesn't equal to " + name);
        // it's add of Set, so no need to check for duplicates of city
        cities.add(city);
    }

    /**
     * @return country's population - sum of all cities population
     */
    public int population() {
        int sum = 0;
        for (City city: cities) {
            sum += city.getPopulation();
        }
        return sum;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * @param other the object to check equality against
     * @return if this and Object have the same country name
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Country)) return false;

        Country obj = (Country) other;
        return name.equals(obj.name);
    }


    // comparing both names
    @Override
    public int compareTo(Country o) {
        return name.compareTo(o.name);
    }

    /**
     * @param under lower-bound of population
     * @return sorted list of all cities in the country
     * that are under the specified bound
     */
    public List<City> smallCities(int under) {
        List<City> cityList = new ArrayList<>();
        for (City city: cities) { // iterating over cities in sorted order (by TreeSet)
            if (city.getPopulation() < under) cityList.add(city);
        }

        return cityList; // cityList is already sorted because of TreeSet
    }

    /**
     * @return sorted string representation of Cities with their populations
     * under this country with total population
     */
    public String report() {
        StringBuilder reportStr = new StringBuilder();
        reportStr.append(name + "(" + population() + ") : ");

        for (City city: cities) { // cities are already sorted because of TreeSet
            reportStr.append(city.getName() + "(" + city.getPopulation() + "), ");
        }
        reportStr.delete(reportStr.length() - 2, reportStr.length());
        return reportStr.toString();
    }
}
