package net.martinburger.sesqa.programming.codeopolis.domainmodel.plants;

import java.util.Random;

import net.martinburger.sesqa.programming.codeopolis.domainmodel.Conditions;

/**
 * The `Plant` class represents a plant that can be grown and harvested.
 * It contains information about the base yield, whether it is a winter plant, and various factors that affect its growth.
 */
public abstract class AbstractGrain {
    private final int baseYield;
    private final float allowedTemperatureOffset;
    private final float conditionsReduction;
    private final float droughtReduction;
    private final float optimalTemperature;
    private int yield;
    protected final int longevity;
    protected final float rotAfterFirstYear;
    protected final float rotIncrease;

    /**
     * Constructs a new `Plant` object with the given parameters.
     *
     * @param baseYield the base yield of the plant
     * @param allowedTemperatureOffset the allowed temperature offset for the plant
     * @param conditionsReduction the reduction factor for the plant based on soil conditions
     * @param droughtReduction the reduction factor for the plant based on drought conditions
     */
    protected AbstractGrain(int baseYield, float allowedTemperatureOffset, float conditionsReduction, float droughtReduction, float optimalTemperature, int longevity, float rotAfterFirstYear, float rotIncrease) {
        this.baseYield = baseYield;
        this.allowedTemperatureOffset = allowedTemperatureOffset;
        this.conditionsReduction = conditionsReduction;
        this.droughtReduction = droughtReduction;
        this.optimalTemperature = optimalTemperature;
        this.longevity = longevity;
        this.rotAfterFirstYear = rotAfterFirstYear;
        this.rotIncrease = rotIncrease;
    }

    public static int[] getDistributedGrainSet(int bushels) {
    	Random random = new Random();
    	
    	int[] set = {0,0,0,0,0,0};
    	
    	if(bushels > 0) {
    		for(int i = 0; i < set.length; i++) {
        		int share = random.nextInt(0, bushels);
        		
        		if(i == set.length -1) {
        			share = bushels;
        		} 
        		
        		set[i] = share;
        		bushels -= share;
        	}
    	}
    	
    	return set;
    }
    
    /*Functionality methods*/

    /**
     * Plants the given number of acres of the plant.
     *
     * @param acres the number of acres to plant
     * @return true if the planting was successful, false otherwise
     */
    public final boolean plant(int acres) {
        if(acres < 0) {
            return false;
        }

        this.yield = baseYield * acres;
        return true;
    }

    /**
     * Grows the plant based on the given conditions.
     *
     * @param conditions the conditions to grow the plant in
     */
    public abstract void grow(Conditions conditions);

    /**
     * Applies drought conditions to the plant.
     *
     * @param conditions the conditions to apply drought to
     */
    public final void drought(Conditions conditions) {
        float reduction = 0;

        if(conditions.isDrought()) {
            reduction = this.droughtReduction;
        }

        this.yield -= Math.round(this.yield * reduction);
    }

    /**
     * Harvests the plant.
     *
     * @return the yield of the plant
     */
    public final int harvest() {
        return this.yield;
    }
	
    /**
     * Simulates pest infestation.
     * @param conditions The conditions that affect the plant.
     */
    public abstract void pestInfestation(Conditions conditions);

    /**
     * Simulates disease outbreak.
     * @param conditions The conditions that affect the plant.
     */
    public abstract void diseaseOutbreak(Conditions conditions);

    /**
     * Returns the base yield of the plant.
     * @return The base yield of the plant.
     */
    public int getBaseYield() {
        return baseYield;
    }

    public float getOptimalTemperature() {
		return optimalTemperature;
	}
    
    /**
     * Returns the allowed temperature offset of the plant.
     * @return The allowed temperature offset of the plant.
     */
    public float getAllowedTemperatureOffset() {
        return allowedTemperatureOffset;
    }

    /**
     * Returns the conditions reduction of the plant.
     * @return The conditions reduction of the plant.
     */
    public float getConditionsReduction() {
        return conditionsReduction;
    }

    /**
     * Returns the drought reduction of the plant.
     * @return The drought reduction of the plant.
     */
    public float getDroughtReduction() {
        return droughtReduction;
    }

    public float getOptimalTempearature() {
    	return optimalTemperature;
    }

    /**
     * Sets the yield of the plant.
     * @param yield The yield of the plant.
     */
    protected void setYield(int yield) {
        this.yield = yield;
    }

    public int getYield() {
        return yield;
    }
}
