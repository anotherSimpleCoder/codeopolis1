package net.martinburger.sesqa.programming.codeopolis.domainmodel.plants;

import net.martinburger.sesqa.programming.codeopolis.domainmodel.Conditions;
/**
 * The `Wheat` class represents a type of plant that is used for harvesting wheat.
 * It extends the `Plant` class and overrides the `pestInfestation` and `diseaseOutbreak` methods.
 */
public class Wheat extends Grain {
    private final float fritFlyReduction = 0.25f;
    private final float barleyGoutFlyReduction = 0.3f;

    private final float powderyMildrewReduction = 0.3f;
    private final float leafDroughtWinter = 0.3f;
    private final float leafDroughtSummer = 0.4f;

    /**
     * Creates a new instance of the `Wheat` class with default values.
     */
    public Wheat() {
        super(6, true, 0.1f, 0.3f, 0.5f);
    }

    /**
     * Overrides the `pestInfestation` method of the `Plant` class.
     * @param conditions The conditions under which the pest infestation occurs.
     */
    @Override
    public void pestInfestation(Conditions conditions) {
        int newYield = this.harvest();
        float reduction = 0;

        if(conditions.isFritFly()) {
            reduction += this.fritFlyReduction;
        }

        if(conditions.isBarleyGoutFly()) {
            reduction += this.barleyGoutFlyReduction;
        }

        newYield -= Math.round(newYield * reduction);
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


        if(conditions.isPowdryMildrew()) {
            reduction += this.powderyMildrewReduction;
        }

        if(conditions.isLeafDrought()){
            if((conditions.getAverageTemperatureWinter() <= (this.getOptimalTemperatureWinter() + 2))) {
                reduction += this.leafDroughtWinter;
            } else {
                reduction += this.leafDroughtSummer;
            }
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
     * Returns the reduction in yield due to frit fly infestation.
     * @return The reduction in yield due to frit fly infestation.
     */
    public float getFritFlyReduction() {
        return fritFlyReduction;
    }

    /**
     * Returns the reduction in yield due to leaf drought in summer.
     * @return The reduction in yield due to leaf drought in summer.
     */
    public float getLeafDroughtSummer() {
        return leafDroughtSummer;
    }

    /**
     * Returns the reduction in yield due to leaf drought in winter.
     * @return The reduction in yield due to leaf drought in winter.
     */
    public float getLeafDroughtWinter() {
        return leafDroughtWinter;
    }

    /**
     * Returns the reduction in yield due to powdery mildrew infestation.
     * @return The reduction in yield due to powdery mildrew infestation.
     */
    public float getPowderyMildrewReduction() {
        return powderyMildrewReduction;
    }
}
