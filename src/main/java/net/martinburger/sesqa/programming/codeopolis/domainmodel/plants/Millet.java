package net.martinburger.sesqa.programming.codeopolis.domainmodel.plants;

import net.martinburger.sesqa.programming.codeopolis.domainmodel.Conditions;
/**
 * This class represents a millet plant.
 * It contains methods to simulate pest infestation and disease outbreak.
 */
public class Millet extends Grain {
    private final float barleyGoutFlyGoodSoil = 0.1f;
    private final float barleyGoutFlyBadSoil = 0.15f;

    /**
     * Initializes a new instance of the Millet class.
     */
    public Millet() {
        super(2, false, 0.45f, 0.1f, 0.05f);
    }

    /**
     * Simulates pest infestation.
     * @param conditions The conditions that affect the millet plant.
     */
    @Override
    public void pestInfestation(Conditions conditions) {
        int newYield = this.harvest();
        float reduction = 0;

        if (conditions.isBarleyGoutFly()) {
            if (conditions.getSoilConditions() > 0.8f) {
                reduction += barleyGoutFlyGoodSoil;
            } else {
                reduction += barleyGoutFlyBadSoil;
            }
        }

        newYield -= newYield * reduction;
        this.setYield(newYield);

    }

    /**
     * Simulates disease outbreak.
     * @param conditions The conditions that affect the millet plant.
     */
    @Override
    public void diseaseOutbreak(Conditions conditions) {
        // TODO Auto-generated method stub

    }

    /**
     * Returns the barley gout fly reduction for good soil.
     * @return The barley gout fly reduction for good soil.
     */
    public float getBarleyGoutFlyGoodSoil() {
        return barleyGoutFlyGoodSoil;
    }

    /**
     * Returns the barley gout fly reduction for bad soil.
     * @return The barley gout fly reduction for bad soil.
     */
    public float getBarleyGoutFlyBadSoil() {
        return barleyGoutFlyBadSoil;
    }
}
