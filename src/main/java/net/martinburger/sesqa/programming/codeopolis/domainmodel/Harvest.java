package net.martinburger.sesqa.programming.codeopolis.domainmodel;

import net.martinburger.sesqa.programming.codeopolis.domainmodel.plants.Rottable;

/**
 * The `Harvest` class represents a harvest of crops.
 * It contains information about the amount of crops harvested and the year of the harvest.
 */
public class Harvest {
    private int amount;
    private int year;       //Lagerjahr
    private Rottable grainType;

    /**
     * Constructs a new `Harvest` object with the given amount and year.
     *
     * @param amount: the amount of crops harvested
     * @param year: the year of the harvest
     * @param grainType: The type of grain you store
     */
    public Harvest(int amount, int year, Rottable grainType) {
    	this.amount = amount;
    	this.year = year;
    	this.grainType = grainType;
    }
    
    /**
     * Constructs a new `Harvest` object with the given amount and year.
     *
     * @param amount the amount of crops harvested
     * @param year the year of the harvest
     */
    public Harvest(int amount, int year) {
        this.amount = amount;
        this.year = year;
        this.grainType = null;
    }
    
    /*Getters*/
    public int getAmount() {
        return amount;
    }

    public int getYear() {
        return year;
    }
    
    public Rottable getGrainType() {
    	return this.grainType;
    }

    /*Functionality methods*/

    /**
     * Removes the given amount of crops from the harvest.
     *
     * @param amount the amount of crops to remove
     * @return the actual amount of crops removed
     */
    public int remove(int amount) {
        int removed = amount;

        if(this.amount < amount) {
            removed = this.amount;
        }

        this.amount -= removed;
        return removed;
    }

    /**
     * Decays the harvest based on the year of the harvest.
     *
     * @return the amount of crops that decayed
     */
    public int decay() {
        int longevity = this.grainType.getLongevity();
        float rotAfterFirstYear = this.grainType.getRotAfterFirstYear();
        float rotIncrease = this.grainType.getRotIncrease();
        int toRot = 0;

        if(this.year > longevity) {
            toRot -= (int)(rotAfterFirstYear * (rotIncrease * (this.year - longevity)) * this.amount);
            this.amount -= toRot;
        }

        this.year++;
        return toRot;
    }

    /*Overrides*/

    /**
     * Returns a string representation of the `Harvest` object.
     *
     * @return a string representation of the `Harvest` object
     */
    @Override
    public String toString() {
        return super.toString();
    }
}

