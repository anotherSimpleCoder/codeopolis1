package net.martinburger.sesqa.programming.codeopolis.domainmodel;

public interface CityState {
	 /**
     * Gets the number of acres owned by the city.
     * 
     * @return The number of acres owned by the city.
     */
    public int getAcres();

    /**
     * Gets the number of bushels owned by the city.
     * 
     * @return The number of bushels owned by the city.
     */
    public int getBushels();

    /**
     * Gets the number of planted acres in the city.
     * 
     * @return The number of planted acres in the city.
     */
    public int getPlantedAcres();

    /**
     * Gets the population of the city.
     * 
     * @return The population of the city.
     */
    public int getPopulation();
}
