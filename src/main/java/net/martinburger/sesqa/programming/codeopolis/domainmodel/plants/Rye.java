package net.martinburger.sesqa.programming.codeopolis.domainmodel.plants;

import net.martinburger.sesqa.programming.codeopolis.domainmodel.Conditions;
/**
 * The `Rye` class represents a type of plant that is used for harvesting rye.
 * It extends the `Plant` class and overrides the `pestInfestation` and `diseaseOutbreak` methods.
 */
public class Rye extends AbstractWinterGrain {
    private final float powderyMildrewWinter = 0.1f;
    private final float powderyMildrewSummer = 0.15f;

    /**
     * Creates a new instance of the `Rye` class with default values.
     */
    public Rye() {
        super(2, 0.45f, 0.1f, 0.05f);
    }

    /**
     * Overrides the `pestInfestation` method of the `Plant` class.
     * @param conditions The conditions under which the pest infestation occurs.
     */
    @Override
    public void pestInfestation(Conditions conditions) {
        // Nothing to do here!
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
            if(conditions.getAverageTemperatureWinter() <= this.getOptimalTemperature() + 3) {
                reduction += this.powderyMildrewWinter;
            } else {
                reduction += this.powderyMildrewSummer;
            }
        }

        newYield -= newYield * reduction;
        this.setYield(newYield);
    }

    /**
     * Returns the reduction in yield due to powdery mildrew infestation in summer.
     * @return The reduction in yield due to powdery mildrew infestation in summer.
     */
    public float getPowderyMildrewSummer() {
        return powderyMildrewSummer;
    }

    /**
     * Returns the reduction in yield due to powdery mildrew infestation in winter.
     * @return The reduction in yield due to powdery mildrew infestation in winter.
     */
    public float getPowderyMildrewWinter() {
        return powderyMildrewWinter;
    }
}
