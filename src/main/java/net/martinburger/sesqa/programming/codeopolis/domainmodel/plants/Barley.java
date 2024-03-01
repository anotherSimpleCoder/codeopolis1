package net.martinburger.sesqa.programming.codeopolis.domainmodel.plants;

import net.martinburger.sesqa.programming.codeopolis.domainmodel.Conditions;

/**
 * This class represents a barley plant.
 * It contains methods to simulate pest infestation and disease outbreak.
 */
public class Barley extends AbstractWinterGrain implements Rottable {
    private final float barleyGoutFlyReduction = 0.4f;
    private final float fusariumReduction = 0.25f;

    /**
     * Initializes a new instance of the Barley class.
     */
    public Barley() {
        super(4, 0.25f, 0.2f, 0.2f, 2, 0.02f, 0.03f);
    }

    /**
     * Simulates pest infestation.
     * @param conditions The conditions that affect the barley plant.
     */
    @Override
    public void pestInfestation(Conditions conditions) {
        int newYield = this.harvest();
        float reduction = 0;

        if (conditions.isBarleyGoutFly()) {
            reduction += this.barleyGoutFlyReduction;
        }

        newYield -= newYield * reduction;
        this.setYield(newYield);
    }

    /**
     * Simulates disease outbreak.
     * @param conditions The conditions that affect the barley plant.
     */
    @Override
    public void diseaseOutbreak(Conditions conditions) {
        int newYield = this.harvest();
        float reduction = 0;

        if (conditions.isFusarium()) {
            reduction += this.fusariumReduction;
        }

        newYield -= newYield * reduction;
        this.setYield(newYield);
    }

    /**
     * Returns the barley gout fly reduction.
     * @return The barley gout fly reduction.
     */
    public float getBarleyGoutFlyReduction() {
        return barleyGoutFlyReduction;
    }

    /**
     * Returns the fusarium reduction.
     * @return The fusarium reduction.
     */
    public float getFusariumReduction() {
        return fusariumReduction;
    }

    @Override
    public int getLongevity() {
        return this.longevity;
    }

    @Override
    public float getRotAfterFirstYear() {
        return this.rotAfterFirstYear;
    }

    @Override
    public float getRotIncrease() {
        return this.rotIncrease;
    }
}
