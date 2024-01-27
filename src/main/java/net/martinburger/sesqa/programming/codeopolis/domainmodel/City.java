/**
 * The `City` class represents a town in the Codeopolis game. It tracks various
 * attributes such as the town's name, population, bushels of grain, acres of land,
 * and implements methods for in-game actions like buying land, selling land, feeding
 * people, planting acres, and processing turns.
 * 
 * @author net.martinburger.sesqa.programming.codeopolis
 * @version 1.0
 */
package net.martinburger.sesqa.programming.codeopolis.domainmodel;

import java.util.Arrays;
import java.util.Random;

import net.martinburger.sesqa.programming.codeopolis.domainmodel.plants.Grain;
import net.martinburger.sesqa.programming.codeopolis.utils.TurnResult;

public class City implements CityState {
    private String name;
    private int bushels;
    private int acres;
    private int population;
    private int bushelsFed;
    private int plantedAcres;
    private int pastYears;
    private Depot depot;
    private Harvest harvest;
    
    /**
     * Constructs a new `City` object with the specified initial values.
     * 
     * @param name         The name of the city.
     * @param population   The initial population of the city.
     * @param acres        The initial acres of land owned by the city.
     * @param bushels      The initial bushels of grain owned by the city.
     */
    public City(String name, int population, int acres, int bushels, int capacity) {
        this.name = name;
        this.acres = acres;
        this.bushels = 0;
        this.population = population;
        this.bushelsFed = 0;
        this.plantedAcres = 0;
        this.pastYears = 0;
        this.harvest = new Harvest(0, 0);
        this.depot = new Depot(capacity) {{
        	this.store(new Harvest(bushels, -1));
        }};
    }

    /**
     * Buys land in the city at the specified price per acre.
     * 
     * @param pricePerAcres The price per acre of land.
     * @param wantedAcres   The number of acres the player wants to buy.
     * @throws Exception    If the input is invalid or there are not enough bushels.
     */
    public void buyLand(int pricePerAcres, int wantedAcres) throws Exception {
        if (wantedAcres < 0) {
            throw new Exception("Invalid input!");
        }

        int price = wantedAcres * pricePerAcres;

        if (price > this.depot.getFillLevel()) {
            throw new Exception("Not enough bushels");
        }

        //this.bushels = this.bushels - price;
        this.depot.takeOut(price);
        this.acres = this.acres + wantedAcres;
    }

    /**
     * Sells land in the city at the specified price per acre.
     * 
     * @param pricePerAcres   The price per acre of land.
     * @param acresToBeSold   The number of acres the player wants to sell.
     * @throws Exception      If the input is invalid or there are not enough acres.
     */
    public void sellLand(int pricePerAcres, int acresToBeSold) throws Exception {
        if (acresToBeSold < 0) {
            throw new Exception("Invalid input!");
        }

        if (acresToBeSold > acres) {
            throw new Exception("Not enough acres");
        }
        int income = pricePerAcres * acresToBeSold;

        this.bushels = this.bushels + income;	//How can we do this with depot?
        this.acres = this.acres - acresToBeSold;
    }

    /**
     * Feeds the city's residents with the specified amount of bushels.
     * 
     * @param bushelsToFeed The amount of bushels to feed the residents.
     * @throws Exception    If the input is invalid or there are not enough bushels.
     */
    public void feed(int bushelsToFeed) throws Exception {
        // Check if input is correct
        if (bushelsToFeed < 0) {
            throw new Exception("Invalid input!");
        }

        // Check for enough bushels
        if (this.depot.getFillLevel() < bushelsToFeed) {
            throw new Exception("Not enough bushels to feed.");
        }

        this.depot.takeOut(bushelsToFeed);
        this.bushelsFed = bushelsToFeed;
    }
    
    public void plant(int acresPerResident, int bushelsPerAcre, int[] acresToPlant) throws Exception {
    	int totalAcresToPlant = Arrays.stream(acresToPlant).reduce(0, (subtotal, element)-> subtotal += element);
    	
    	 int freeAcres = this.acres - this.plantedAcres;

         if (freeAcres <= 0) {
             throw new Exception("Not enough land to plant seeds!");
         }

         if (this.depot.getFillLevel() * bushelsPerAcre == 0 || this.depot.getFillLevel() * bushelsPerAcre < totalAcresToPlant) {
             throw new Exception("Not enough bushels available for planting seeds!");
         }

         if ((acres / acresPerResident) > this.population) {
             throw new Exception("Not enough population to plant!");
         }

         // Do the planting
         //this.bushels -= bushelsPerAcre * acresToPlant;
         this.depot.takeOut(bushelsPerAcre * totalAcresToPlant);
         this.plantedAcres += totalAcresToPlant;
    }

    /**
     * Plants acres of land in the city, consuming one bushel per acre.
     * 
     * @param acresPerResident The number of acres required per resident.
     * @param bushelsPerAcre   The number of bushels required per acre.
     * @param acresToPlant     The number of acres to plant.
     * @throws Exception       If there's not enough land, bushels, or population.
     
    public void plant(int acresPerResident, int bushelsPerAcre, int acresToPlant) throws Exception {
        int freeAcres = this.acres - this.plantedAcres;

        if (freeAcres <= 0) {
            throw new Exception("Not enough land to plant seeds!");
        }

        if (this.depot.getFillLevel() * bushelsPerAcre == 0 || this.depot.getFillLevel() * bushelsPerAcre < acresToPlant) {
            throw new Exception("Not enough bushels available for planting seeds!");
        }

        if ((acres / acresPerResident) > this.population) {
            throw new Exception("Not enough population to plant!");
        }

        // Do the planting
        //this.bushels -= bushelsPerAcre * acresToPlant;
        this.depot.takeOut(bushelsPerAcre * acresToPlant);
        this.plantedAcres += acresToPlant;
    }*/

    /**
     * Returns a string representation of the city's attributes.
     * 
     * @return A formatted string describing the city's state.
     */
    public String toString() {
        return String.format(
                "In the year %d after its foundation, %d inhabitants live in the town of %s. The town owns %d bushels of grain and %d acres of land.",
                this.pastYears, this.population, this.name, this.bushels, this.acres);
    }

    /**
     * Runs a turn in the game, processing various game mechanics and updating the city's state.
     * 
     * @param bushelsPerResident The number of bushels required per resident.
     * @param harvestFactor      The harvest factor affecting crop yield.
     * @param rateInfestation     The rate of infestation affecting stored bushels.
     * @return A `TurnResult` object containing the results of the turn.
     */
    public TurnResult runTurn(int bushelsPerResident, float harvestFactor, int rateInfestation) {
        Random random = new Random();
    	
    	// Calculate turn variables
        int starvation = this.calculateStarvation(bushelsPerResident);
        int starvedPercentage = this.calculateStarvedPercentage(starvation);
        int newResidents = this.calculateNewResidents(random.nextFloat(0.0f, 0.04f), starvedPercentage);
        int newHarvest = this.calculateNewHarvest(random.nextFloat(0.1f, 1.0f) ,harvestFactor);
        int eatenByRats = this.calculateEatenByRats(random.nextFloat(0, rateInfestation));

        // Apply them to city
        this.depot.decay();
        this.pastYears += 1;
        this.population += (newResidents - starvation);
        
        //Change this to depot
        this.bushels = (this.bushels + newHarvest) - eatenByRats;
        this.harvest = new Harvest(newHarvest, 0);
        this.depot.store(harvest);

        // Return turn result
        return new TurnResult(this.name, this.pastYears, newResidents, newHarvest, this.population, this.bushels,
                starvation, this.acres, eatenByRats, starvedPercentage);
    }

    /**
     * Calculates the number of people who starved based on the given bushels per resident.
     * 
     * @param bushelsPerResident The number of bushels required per resident.
     * @return The number of people who starved.
     */
    public int calculateStarvation(int bushelsPerResident) {
        int peopleFed = (int) (this.bushelsFed / bushelsPerResident);
        return this.population - peopleFed;
    }

    /**
     * Calculates the percentage of people who starved based on the given starvation count.
     * 
     * @param starvation The number of people who starved.
     * @return The percentage of people who starved.
     */
    public int calculateStarvedPercentage(int starvation) {
    	float percentage = (float) starvation / (float) this.population;
        return Math.round(percentage * 100);
    }

    /**
     * Calculates the number of new residents based on the given starved percentage.
     * 
     * @param starvedPercentage The percentage of people who starved.
     * @return The number of new residents.
     */
    public int calculateNewResidents(float growth, int starvedPercentage) {
        int newResidents = 0;

        if (starvedPercentage < 40) {
            newResidents = (int) (this.population * growth);
        }

        return newResidents;
    }

    /**
     * Calculates the new harvest based on the given harvest factor.
     * 
     * @param harvestFactor The harvest factor affecting crop yield.
     * @return The new harvest amount.
     */
    public int calculateNewHarvest(float z, float harvestFactor) {
        float harvestRate = z * harvestFactor;
        float newHarvest = (float) (this.plantedAcres) * harvestRate;
        
        
        
        return Math.round(newHarvest);
    }

    /**
     * Calculates the amount of grain eaten by rats based on the given infestation rate.
     * 
     * @param rateInfestation The rate of infestation affecting stored bushels.
     * @return The amount of grain eaten by rats.
     */
    public int calculateEatenByRats(float ratPercentage) {
        return (int) (this.bushels * (ratPercentage / 100));
    }

    /**
     * Gets the number of acres owned by the city.
     * 
     * @return The number of acres owned by the city.
     */
    public int getAcres() {
        return acres;
    }

    /**
     * Gets the number of bushels owned by the city.
     * 
     * @return The number of bushels owned by the city.
     */
    public int getBushels() {
        return this.depot.getFillLevel();
    }

    /**
     * Gets the number of planted acres in the city.
     * 
     * @return The number of planted acres in the city.
     */
    public int getPlantedAcres() {
        return plantedAcres;
    }

    /**
     * Gets the population of the city.
     * 
     * @return The population of the city.
     */
    public int getPopulation() {
        return population;
    }

    /**
     * Gets the depot of the city.
     * 
     * @return The depot of the city.
     * */
    public Depot getDepot() {
		return depot;
	}
    
    /**
     * Sets the number of bushels owned by the city.
     * 
     * @param newBushels The new number of bushels owned by the city.
     */
    public void setBushels(int newBushels) {
        this.bushels = newBushels;
    }
    
    public void setBushelsFed(int bushelsFed) {
		this.bushelsFed = bushelsFed;
	}
    
    public void setPlantedAcres(int plantedAcres) {
		this.plantedAcres = plantedAcres;
	}
}
