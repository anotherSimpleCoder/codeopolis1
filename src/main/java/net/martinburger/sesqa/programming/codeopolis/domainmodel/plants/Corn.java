package net.martinburger.sesqa.programming.codeopolis.domainmodel.plants;

import net.martinburger.sesqa.programming.codeopolis.domainmodel.Conditions;

/**
 * This class represents a corn plant.
 * It contains methods to simulate pest infestation and disease outbreak.
 */
public class Corn extends AbstractSummerGrain implements Rottable  {
    private final float fritFlyReduction = 0.4f;
    private final float powderyMildrewReduction = 0.25f;

    /**
     * Initializes a new instance of the Corn class.
     */
    public Corn() {
        super(4, 0.25f, 0.2f, 0.3f, 2, 0.02f, 0.03f);
    }

    /**
     * Simulates pest infestation.
     * @param conditions The conditions that affect the corn plant.
     */
    @Override
    public void pestInfestation(Conditions conditions) {
        int newYield = this.harvest();
        float reduction = 0;

        if (conditions.isFritFly()) {
            reduction += fritFlyReduction;
        }

        newYield -= newYield * reduction;
        this.setYield(newYield);
    }

    /**
     * Simulates disease outbreak.
     * @param conditions The conditions that affect the corn plant.
     */
    @Override
    public void diseaseOutbreak(Conditions conditions) {
        int newYield = this.harvest();
        float reduction = 0;

        if (conditions.isPowdryMildrew()) {
            reduction += powderyMildrewReduction;
        }

        newYield -= newYield * reduction;
        this.setYield(newYield);
    }

    /**
     * Returns the frit fly reduction.
     * @return The frit fly reduction.
     */
    public float getFritFlyReduction() {
        return fritFlyReduction;
    }

    /**
     * Returns the powdery mildrew reduction.
     * @return The powdery mildrew reduction.
     */
    public float getPowderyMildrewReduction() {
        return powderyMildrewReduction;
    }

    @Override
    public int getLongevity() {
        return 0;
    }

    @Override
    public float getRotIncrease() {
        return 0;
    }

    @Override
    public float getRotAfterFirstYear() {
        return 0;
    }
}