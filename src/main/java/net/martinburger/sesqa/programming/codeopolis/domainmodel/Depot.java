package net.martinburger.sesqa.programming.codeopolis.domainmodel;

import java.util.ArrayList;

/**
 * This class represents a depot for storing harvests.
 */
public class Depot {
    private ArrayList<Harvest> stock;
    private int capacity;

    /**
     * Constructor for the Depot class.
     * @param capacity the maximum capacity of the depot
     */
    public Depot(int capacity) {
        stock = new ArrayList<Harvest>();
        this.capacity = capacity;
    }

    /*Getters*/

    /**
     * This method returns the maximum capacity of the depot.
     * @return the maximum capacity of the depot
     */
    public int getCapacity() {
        return capacity;
    }

    /*Methods*/

    /**
     * This method returns the total amount of harvests in the depot.
     * @return the total amount of harvests in the depot
     */
    public int getFillLevel() {
        int res = 0;

        for(Harvest h: stock) {
            res += h.getAmount();
        }

        return res;
    }

    /**
     * This method stores a harvest in the depot.
     * @param harvest the harvest to store
     * @return true if the harvest was stored successfully, false otherwise
     */
    public boolean store(Harvest harvest) {
        if(stock.size() < capacity) {
            stock.add(harvest);
            return true;
        }

        return false;
    }

    /**
     * This method takes out a certain amount of harvests from the depot.
     * @param amount the amount of harvests to take out
     * @return the actual amount of harvests taken out
     */
    public int takeOut(int amount) {
        int takenOut = 0;

        for(Harvest h: stock) {
            if(h.getAmount() != 0) {
                takenOut += h.remove(amount - takenOut);
            }
        }

        return takenOut;
    }

    /**
     * This method expands the capacity of the depot.
     * @param capacity the amount of capacity to add
     */
    public void expand(int capacity) {
        float feePercentage = 0.05f;
        float pricePerCapacity = feePercentage * this.getFillLevel();
        int price = (int)(capacity * pricePerCapacity);

        this.takeOut(price);
        this.capacity += capacity;
    }

    /**
     * This method decays all the harvests in the depot.
     * @return the total amount of decayed harvests
     */
    public int decay() {
        int decayed = 0;

        for(Harvest h: stock) {
            h.decay();
        }

        return decayed;
    }

    /*Overrides*/

    /**
     * This method returns a string representation of the Depot object.
     * @return a string representation of the Depot object
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
