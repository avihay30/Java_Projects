package cities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// class that represent the world that maps all the countries with their cities
public class World {
    // countries mapped by their name
    private Map<String, Country> countries = new TreeMap<>();

    /**
     * @param name of Country to be created and inserted to countries map
     */
    public void addCountry(String name) {
        countries.put(name, new Country(name));
    }

    /**
     * Creating new city and adds it to the corresponding country in map
     * if not such country - throws IllegalArgumentException
     */
    public void addCity(String name, String countryName, int population) {
        Country country = countries.get(countryName);
        // if no mapping has been found for countryName in countries
        if (country == null)
            throw new IllegalArgumentException("No such country has been found\n");

        country.addCity(new City(name, country, population));
    }

    /**
     * @return world's population - sum of all countries population
     */
    public int population() {
        int sum = 0;
        for (Country country : countries.values()) {
            sum += country.population();
        }
        return sum;
    }

    /**
     * @param under lower-bound of population
     * @return sorted list of all cities in the world
     * that are under the specified bound
     */
    public List<City> smallCities(int under) {
        List<City> cityList = new ArrayList<>();
        for (Country country : countries.values()) {
            cityList.addAll(country.smallCities(under));
        }
        return cityList;
    }

    /**
     * @return sorted string representation of the countries with their populations
     * under this world with total population
     */
    public String report() {
        StringBuilder reportStr = new StringBuilder();
        for (Country country : countries.values()) {
            reportStr.append(country.report() + "\n");
        }

        reportStr.append("Total population is " + population() + "\n");
        return reportStr.toString();
    }
}
