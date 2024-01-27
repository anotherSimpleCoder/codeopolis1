package net.martinburger.sesqa.programming.codeopolis.domainmodel.plants;

import net.martinburger.sesqa.programming.codeopolis.domainmodel.Conditions;
/**
 * The `Rice` class represents a type of plant that is used for harvesting rice.
 * It extends the `Plant` class and overrides the `pestInfestation` and `diseaseOutbreak` methods.
 */
public class Rice extends Grain {
    private final float deliaFlyReduction = 0.3f;
    private final float deliaFlyOutsideThresholdReduction = 0.4f;
    private final float barleyGoutFlyReduction = 0.3f;
    private final float leafDroughtRedcution = 0.3f;
    private final float fusariumReduction = 0.25f;

    /**
     * Creates a new instance of the `Rice` class with default values.
     */
    public Rice() {
        super(6, false, 0.1f, 0.3f, 0.5f);
    }

    /**
     * Overrides the `pestInfestation` method of the `Plant` class.
     * @param conditions The conditions under which the pest infestation occurs.
     */
    @Override
    public void pestInfestation(Conditions conditions) {
        int newYield = this.harvest();
        float temperatureThreshold = getOptimalTemperatureSummer() + (getOptimalTemperatureSummer() * 0.1f);
        float reduction = 0;

        if(conditions.isDelioFly()) {
            if(conditions.getAverageTemperatureSummer() < temperatureThreshold) {
                reduction += deliaFlyReduction;
            } else {
                reduction += deliaFlyOutsideThresholdReduction;
            }
        }

        if(conditions.isBarleyGoutFly()) {
            reduction += barleyGoutFlyReduction;
        }

        newYield -= newYield * reduction;
        this.setYield(newYield);
    }

    /**
     * Overrides the `diseaseOutbreak` method of the `Plant` class.
     * @param conditions The conditions under which the disease outbreak occurs.
     */
    @Override
    public void diseaseOutbreak(Conditions conditions) {
        int newYield = this.harvest();
        float reduction = 0;

        if(conditions.isLeafDrought()) {
            reduction += leafDroughtRedcution;
        }

        if(conditions.isFusarium()) {
            reduction += fusariumReduction;
        }

        newYield -= newYield * reduction;
        this.setYield(newYield);
    }

    /**
     * Returns the reduction in yield due to barley gout fly infestation.
     * @return The reduction in yield due to barley gout fly infestation.
     */
    public float getBarleyGoutFlyReduction() {
        return barleyGoutFlyReduction;
    }

    /**
     * Returns the reduction in yield due to delia fly infestation outside the temperature threshold.
     * @return The reduction in yield due to delia fly infestation outside the temperature threshold.
     */
    public float getDeliaFlyOutsideThresholdReduction() {
        return deliaFlyOutsideThresholdReduction;
    }

    /**
     * Returns the reduction in yield due to delia fly infestation.
     * @return The reduction in yield due to delia fly infestation.
     */
    public float getDeliaFlyReduction() {
        return deliaFlyReduction;
    }

    /**
     * Returns the reduction in yield due to fusarium disease outbreak.
     * @return The reduction in yield due to fusarium disease outbreak.
     */
    public float getFusariumReduction() {
        return fusariumReduction;
    }

    /**
     * Returns the reduction in yield due to leaf drought.
     * @return The reduction in yield due to leaf drought.
     */
    public float getLeafDroughtRedcution() {
        return leafDroughtRedcution;
    }
}
