package cities;

// class that represent some city in a country
public class City implements Comparable<City> {
    private String name;
    private Country country;
    private int population;

    public City(String name, Country country, int population) {
        this.name = name;
        this.country = country;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    public int getPopulation() {
        return population;
    }

    public String toString() {
        return String.format("%s (of %s)", name, country);
    }

    /**
     * @param other the object to check equality against
     * @return if this and Object have the same country and city name
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof City)) return false;

        City obj = (City) other;
        // returns true if it's same country and city name
        return country.equals(obj.getCountry())
                && name.equals(obj.getName());
    }

    /**
     * @param o the object to be compared.
     * @return if same countries - comparing city names
     * else comparing countries
     */
    @Override
    public int compareTo(City o) {
        if (equals(o)) return 0; // if both are equal

        // if both countries are equal - comparing by city name
        if (country.equals(o.getCountry()))
            return name.compareTo(o.getName());
        // countries are different - comparing by country (by country name)
        return country.compareTo(o.country);
    }
}
