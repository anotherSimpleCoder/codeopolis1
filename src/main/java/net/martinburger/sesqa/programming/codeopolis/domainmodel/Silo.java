package net.martinburger.sesqa.programming.codeopolis.domainmodel;

import net.martinburger.sesqa.programming.codeopolis.domainmodel.plants.Rottable;

/**
 * Represents a silo for storing harvested grains.
 */
public class Silo {
    private Harvest[] stock;
    private int capacity;
    private int fillLevel;
    private Rottable grainType;

    /**
     * Constructs a silo with the specified capacity.
     *
     * @param capacity the maximum capacity of the silo
     */
    public Silo(int capacity) {
        this.stock = new Harvest[capacity];
        this.capacity = capacity;
        this.fillLevel = 0;
    }

    /**
     * Retrieves the capacity of the silo.
     *
     * @return the capacity of the silo
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Retrieves the current fill level of the silo.
     *
     * @return the current fill level of the silo
     */
    public int getFillLevel() {
        return fillLevel;
    }

    /**
     * Retrieves the type of grain stored in the silo.
     *
     * @return the type of grain stored in the silo
     */
    public Rottable getGrainType() {
        return this.grainType;
    }

    /**
     * Stores a harvest in the silo.
     *
     * @param harvest the harvest to be stored
     * @return the amount of harvest that couldn't be stored due to capacity limitations,
     *         or 0 if the entire harvest was stored successfully
     */
    public int store(Harvest harvest) {
        int harvestLeft = 0;
        
        if(fillLevel == 0) {
            this.grainType = harvest.getGrainType();
        }
        
        if(!harvest.getGrainType().equals(this.getGrainType())) {
            return -1;
        }
        
        if(harvest.getAmount() > this.capacity) {
            harvestLeft = harvest.getAmount() - this.capacity;
            harvest = new Harvest(harvestLeft, harvest.getYear(), harvest.getGrainType());
        }
        
        int i = 0;
        while(i < stock.length) {
            if(stock[i] == null) {
                stock[i] = harvest;
                fillLevel += harvest.getAmount();
                break;
            }
            
            i++;
        }
        
        return harvestLeft;
    }

    /**
     * Takes out a specified amount of grain from the silo.
     *
     * @param amount the amount of grain to take out
     * @return the actual amount of grain taken out
     */
    public int takeOut(int amount) {
        int toTakeOut = amount;

        if(amount > this.fillLevel) {
            toTakeOut = this.fillLevel;
        }
        
        for(Harvest h: this.stock) {
            if(h != null) {
                if(h.getAmount() != 0) {
                    h.remove(toTakeOut);
                    this.fillLevel -= toTakeOut;
                }
            }
        }
        
        return toTakeOut;
    }

    /**
     * Simulates decay of stored grains in the silo.
     *
     * @return the total amount of decayed grains
     */
    public int decay() {
        int decayed = 0;
        
        for(Harvest h: this.stock) {
            decayed += h.decay();
        }
        
        return decayed;
    }

    /**
     * Returns a string representation of the silo.
     *
     * @return a string representation of the silo
     */
    @Override
    public String toString() {
        return "";
    }
}
