package net.martinburger.sesqa.programming.codeopolis.utils;

/**
 * This class represents the configuration of the game.
 * */
public class GameConfig {
	private int initialResidents; // Initial number of residents
	private int initialAcres; // Initial number of acres
	private int initialBushels; // Initial number of bushels
	private int initialCapacity; // Initial capacity of the city depot
	private int maxAcrePrice; // Maximum price per acre of land
	private int minArcrPrice; // Minimum price per acre of land
	private int bushelsPerResident; // Number of bushels to be fed per resident per year
	private int bushelsPerAcre; // Number of bushels to plant per acre
	private int acrePerResident; // Number of acres a resident can farm
	private int numberOfYears; // Number of game turns ( years )
	private float harvestFactor; // The harvest factor indicates the maximum harvest rate
	private int rateInfestation; // The maximum percentage of bushels that can be eaten by rats in a year
	private DifficultyLevel level; // Difficulty level of the game
	
	public GameConfig(DifficultyLevel level) {
		switch(level) {
			case EASY: {
				this.initialResidents = 100;
				this.initialAcres = 1000;
				this.initialBushels = 2800;
				this.initialCapacity = 10;
				this.maxAcrePrice = 30;
				this.minArcrPrice = 10;
				this.bushelsPerResident = 20;
				this.bushelsPerAcre = 1;
				this.acrePerResident = 10;
				this.numberOfYears = 10;
				this.harvestFactor = 6.0f;
				this.rateInfestation = 10;
				this.level = level;
				break;
			}
			
			case MEDIUM: {
				this.initialResidents = 100;
				this.initialAcres = 900;
				this.initialBushels = 2800;
				this.initialCapacity = 5;
				this.maxAcrePrice = 35;
				this.minArcrPrice = 15;
				this.bushelsPerResident = 22;
				this.bushelsPerAcre = 1;
				this.acrePerResident = 10;
				this.numberOfYears = 10;
				this.harvestFactor = 5.0f;
				this.rateInfestation = 20;
				this.level = level;
				break;
			}
			
			case HARD: {
				this.initialResidents = 100;
				this.initialAcres = 800;
				this.initialBushels = 2800;
				this.initialCapacity = 2;
				this.maxAcrePrice = 35;
				this.minArcrPrice = 15;
				this.bushelsPerResident = 24;
				this.bushelsPerAcre = 1;
				this.acrePerResident = 10;
				this.numberOfYears = 10;
				this.harvestFactor = 4.0f;
				this.rateInfestation = 25;
				this.level = level;
				break;
			}
		}
	}

	public int getInitialResidents() {
		return initialResidents;
	}
	
	public int getInitialAcres() {
		return initialAcres;
	}
	
	public int getInitialBushels() {
		return initialBushels;
	}
	
	public int getInitialCapacity() {
		return initialCapacity;
	}
	
	public int getMaxAcrePrice() {
		return maxAcrePrice;
	}
	
	public int getMinArcrPrice() {
		return minArcrPrice;
	}
	
	public int getBushelsPerResident() {
		return bushelsPerResident;
	}
	
	public int getBushelsPerAcre() {
		return bushelsPerAcre;
	}
	
	public int getAcrePerResident() {
		return acrePerResident;
	}

	public int getNumberOfYears() {
		return numberOfYears;
	}
	
	public float getHarvestFactor() {
		return harvestFactor;
	}
	
	public int getRateInfestation() {
		return rateInfestation;
	}
	
	public DifficultyLevel getLevel() {
		return level;
	}
}
