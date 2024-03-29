package net.martinburger.sesqa.programming.codeopolis.domainmodel;

import net.martinburger.sesqa.programming.codeopolis.domainmodel.plants.Rottable;

import java.util.ArrayList;

/**
 * This class represents a depot for storing harvests.
 */
public class Depot {
    private ArrayList<Silo> stock;
    private int capacity;

    /**
     * Constructor for the Depot class.
     * @param capacity the maximum capacity of the depot
     */
    public Depot(int capacity) {
        stock = new ArrayList<Silo>();
        stock.add(new Silo(capacity));
        stock.add(new Silo(capacity));
        stock.add(new Silo(capacity));
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

        for(Silo s: stock) {
            res += s.getFillLevel();
        }

        return res;
    }

    /**
     * This method stores a harvest in the depot.
     * @param harvest the harvest to store
     * @return true if the harvest was stored successfully, false otherwise
     */
    public boolean store(Harvest harvest) {
        for(Silo s: stock) {
            if(s.getFillLevel() < this.capacity) {
                s.store(harvest);
                return true;
            }
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

        for(Silo s: stock) {
            if(s.getFillLevel() != 0) {
                takenOut += s.takeOut(amount - takenOut);
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

        for(Silo s: stock) {
            s.decay();
        }

        return decayed;
    }

    //TODO: Man fuck jay z
    public void defragement() {
        Rottable grainType = stock.get(0).getGrainType();
        for(int i = 1; i < stock.size(); i++) {
            if(stock.get(i).getGrainType().equals(grainType)) {
                if(stock.get(i-1).getFillLevel() < stock.get(i-1).getCapacity()) {
                    int toTakeOut = stock.get(i-1).getCapacity() - stock.get(i).getFillLevel();
                    stock.get(i).takeOut(toTakeOut);

                    //TODO: Hmm we got a year problem....
                }

            }
        }
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
