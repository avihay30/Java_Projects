package cities;

import java.util.*;

// class that represent the world that maps all the countries with their cities
public class World {
    // countries mapped by their name
    private Map<String, Country> countries = new HashMap<>();

    /**
     * @param name of Country to be created and inserted to countries map
     */
    public void addCountry(String name) {
        Country country = new Country(name);
        countries.put(name, country);
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

        City city = new City(name, country, population);
        country.addCity(city);
    }

    /**
     * @return world's population - sum of all countries population
     */
    public int population() {
        int sum = 0;
        for (Country country: countries.values()) {
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
        for (Country country: countries.values()) {
            cityList.addAll(country.smallCities(under));
        }
        Collections.sort(cityList);
        return cityList;
    }

    /**
     * @return sorted string representation of the countries with their populations
     * under this world with total population
     */
    public String report() {
        StringBuilder reportStr = new StringBuilder();
        for (Country country: countries.values()) {
            reportStr.append(country.report() + "\n");
        }

        reportStr.append("Total population is " + population() + "\n");

        return reportStr.toString();
    }

//    public static void main(String[] args) {
//        World w = new World();
//        w.addCountry("Spain");
//        w.addCity("Granada", "Spain", 233764);
//        w.addCountry("Brazil");
//        w.addCity("Salvador",  "Brazil", 2677000);
//        w.addCity("Barcelona", "Spain", 1615000);
//        w.addCity("Rio de Janeiro",  "Brazil", 6320000);
//
//        System.out.println(w.report());
//        int bound = 2000000;
//        System.out.println("Cities with population under " + bound + ":");
//        System.out.println(w.smallCities(bound));
//    }
}
