package net.martinburger.sesqa.programming.codeopolis.domainmodel;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * This class represents the conditions for growing plants.
 * It contains the following fields:
 * - soilConditions: the quality of the soil
 * - averageTemperatureSummer: the average temperature during summer
 * - averageTemperatureWinter: the average temperature during winter
 * - drought: whether there is a drought
 * - fusarium: whether there is fusarium
 * - leafDrought: whether there is leaf drought
 * - powdryMildrew: whether there is powdry mildrew
 * - barleyGoutFly: whether there is barley gout fly
 * - delioFly: whether there is delio fly
 * - fritFly: whether there is frit fly
 */
public class Conditions {
	public static final float SUMMER_TEMPERATURE_LOWER = 0.0f;
	public static final float SUMMER_TEMPERATURE_HIGHER = 30.0f;
	public static final float WINTER_TEMPERATURE_LOWER = -10.0f;
	public static final float WINTER_TEMPERATURE_HIGHER = 10.0f;
	
	private float soilConditions;
	private float averageTemperatureSummer;
	private float averageTemperatureWinter;
	private boolean drought;
	private boolean fusarium;
	private boolean leafDrought;
	private boolean powdryMildrew;
	private boolean barleyGoutFly;
	private boolean delioFly;
	private boolean fritFly;
	
	/**
     * Constructor for the Conditions class.
     * @param soilConditions the quality of the soil
     * @param averageTemperatureSummer the average temperature during summer
     * @param averageTemperatureWinter the average temperature during winter
     * @param drought whether there is a drought
     * @param fusarium whether there is fusarium
     * @param leafDrought whether there is leaf drought
     * @param powdryMildrew whether there is powdry mildrew
     * @param barleyGoutFly whether there is barley gout fly
     * @param delioFly whether there is delio fly
     * @param fritFly whether there is frit fly
     */
	private Conditions(float soilConditions, float averageTemperatureSummer, float averageTemperatureWinter, boolean drought, boolean fusarium, boolean leafDrought, boolean powdryMildrew, boolean barleyGoutFly, boolean delioFly, boolean fritFly) {
		this.soilConditions = soilConditions;
		this.averageTemperatureSummer = averageTemperatureSummer;
		this.averageTemperatureWinter = averageTemperatureWinter;
		this.drought = drought;
		this.fusarium = fusarium;
		this.leafDrought = leafDrought;
		this.powdryMildrew = powdryMildrew;
		this.barleyGoutFly = barleyGoutFly;
		this.delioFly = delioFly;
		this.fritFly = fritFly;
	}

	/**
	 * This method generates random conditions for growing barley.
	 * @return a Conditions object with random values
	 */
	public static Conditions generateRandomConditions() {
		Random random = new Random();
		HashMap<String, Boolean> settings = new HashMap<String, Boolean>(
			Map.of(
					"drought", false,
					"fusarium", false,
					"leadDrought", false,
					"powdryMildrew", false,
					"barleyGoutFly", false,
					"delioFly", false,
					"fritFly", false	
			)
		);
		
		//Generate seed upon which the probabilities will be set
		long lowerBoundary = 0x10000000000000L;
		long upperBoundary = 0x64646464646464L;

		long seed = random.nextLong(lowerBoundary, upperBoundary);

		//Some bit magic
		for(String key: settings.keySet()) {
			long setting = seed & 0x64;
			seed >>= 8;
			
			if(setting >= 80) {
				settings.put(key, true);
			}
		}
		
		return new Conditions(
				random.nextFloat(0.0f, 1.0f),
				random.nextFloat(Conditions.SUMMER_TEMPERATURE_LOWER, Conditions.SUMMER_TEMPERATURE_HIGHER),
				random.nextFloat(Conditions.WINTER_TEMPERATURE_LOWER, Conditions.WINTER_TEMPERATURE_HIGHER),
				settings.get("drought"),
				settings.get("fusarium"),
				settings.get("leadDrought"),
				settings.get("powdryMildrew"),
				settings.get("barleyGoutFly"),
				settings.get("delioFly"),
				settings.get("fritFly")
		);
	}
	
	/** Getters **/
	/**
	 * This method returns the soil conditions.
	 * @return the quality of the soil
	 */
	public float getSoilConditions() {
	    return soilConditions;
	}

	/**
	 * This method returns the average temperature during summer.
	 * @return the average temperature during summer
	 */
	public float getAverageTemperatureSummer() {
	    return averageTemperatureSummer;
	}

	/**
	 * This method returns the average temperature during winter.
	 * @return the average temperature during winter
	 */
	public float getAverageTemperatureWinter() {
	    return averageTemperatureWinter;
	}

	/**
	 * This method returns whether there is barley gout fly.
	 * @return whether there is barley gout fly
	 */
	public boolean isBarleyGoutFly() {
	    return barleyGoutFly;
	}

	/**
	 * This method returns whether there is delio fly.
	 * @return whether there is delio fly
	 */
	public boolean isDelioFly() {
	    return delioFly;
	}

	/**
	 * This method returns whether there is drought.
	 * @return whether there is drought
	 */
	public boolean isDrought() {
	    return drought;
	}

	/**
	 * This method returns whether there is frit fly.
	 * @return whether there is frit fly
	 */
	public boolean isFritFly() {
	    return fritFly;
	}

	/**
	 * This method returns whether there is fusarium.
	 * @return whether there is fusarium
	 */
	public boolean isFusarium() {
	    return fusarium;
	}

	/**
	 * This method returns whether there is leaf drought.
	 * @return whether there is leaf drought
	 */
	public boolean isLeafDrought() {
	    return leafDrought;
	}

	/**
	 * This method returns whether there is powdry mildrew.
	 * @return whether there is powdry mildrew
	 */
	public boolean isPowdryMildrew() {
	    return powdryMildrew;
	}

	/**
	 * This method returns the upper limit of summer temperature.
	 * @return the upper limit of summer temperature
	 */
	public static float getSummerTemperatureHigher() {
	    return SUMMER_TEMPERATURE_HIGHER;
	}

	/**
	 * This method returns the lower limit of summer temperature.
	 * @return the lower limit of summer temperature
	 */
	public static float getSummerTemperatureLower() {
	    return SUMMER_TEMPERATURE_LOWER;
	}

	/**
	 * This method returns the upper limit of winter temperature.
	 * @return the upper limit of winter temperature
	 */
	public static float getWinterTemperatureHigher() {
	    return WINTER_TEMPERATURE_HIGHER;
	}

	/**
	 * This method returns the lower limit of winter temperature.
	 * @return the lower limit of winter temperature
	 */
	public static float getWinterTemperatureLower() {
	    return WINTER_TEMPERATURE_LOWER;
	}

	/** Setters **/

	/**
	 * This method sets the average temperature during summer.
	 * @param averageTemperatureSummer the average temperature during summer
	 */
	public void setAverageTemperatureSummer(float averageTemperatureSummer) {
	    this.averageTemperatureSummer = averageTemperatureSummer;
	}

	/**
	 * This method sets the average temperature during winter.
	 * @param averageTemperatureWinter the average temperature during winter
	 */
	public void setAverageTemperatureWinter(float averageTemperatureWinter) {
	    this.averageTemperatureWinter = averageTemperatureWinter;
	}

	/**
	 * This method sets whether there is barley gout fly.
	 * @param barleyGoutFly whether there is barley gout fly
	 */
	public void setBarleyGoutFly(boolean barleyGoutFly) {
	    this.barleyGoutFly = barleyGoutFly;
	}

	/**
	 * This method sets whether there is delio fly.
	 * @param delioFly whether there is delio fly
	 */
	public void setDelioFly(boolean delioFly) {
	    this.delioFly = delioFly;
	}

	/**
	 * This method sets whether there is drought.
	 * @param drought whether there is drought
	 */
	public void setDrought(boolean drought) {
	    this.drought = drought;
	}

	/**
	 * This method sets whether there is frit fly.
	 * @param fritFly whether there is frit fly
	 */
	public void setFritFly(boolean fritFly) {
	    this.fritFly = fritFly;
	}

	/**
	 * This method sets whether there is fusarium.
	 * @param fusarium whether there is fusarium
	 */
	public void setFusarium(boolean fusarium) {
	    this.fusarium = fusarium;
	}

	/**
	 * This method sets whether there is leaf drought.
	 * @param leafDrought whether there is leaf drought
	 */
	public void setLeafDrought(boolean leafDrought) {
	    this.leafDrought = leafDrought;
	}

	/**
	 * This method sets whether there is powdry mildrew.
	 * @param powdryMildrew whether there is powdry mildrew
	 */
	public void setPowdryMildrew(boolean powdryMildrew) {
	    this.powdryMildrew = powdryMildrew;
	}
	
	/**
	 * This method sets the quality of the soil.
	 * @param soilConditions the quality of the soil
	 */
	public void setSoilConditions(float soilConditions) {
	    this.soilConditions = soilConditions;
	}
}
