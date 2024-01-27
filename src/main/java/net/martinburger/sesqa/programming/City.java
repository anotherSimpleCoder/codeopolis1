package net.martinburger.sesqa.programming;

public class City {
   private String name;
   private int bushels;
   private int acres;
   private int population;
   private int bushelsPerPeople;
   private int plantedAcres;
   private int pastYears;

    public City(String name, int bushelsPerPeople) {
        this.name = name;
        this.bushels = 2800;
        this.acres = 1000;
        this.population = 100;
        this.bushelsPerPeople = bushelsPerPeople;
        this.plantedAcres = 0;
        this.pastYears = 0;
    }

    public void buyLand(int landOffer, int wantedAcres) throws Exception {
    	if(landOffer < 0 || wantedAcres < 0) {
    		throw new Exception("Invalid input");
    	}
    	
        int price = wantedAcres*landOffer;

        if (price > bushels ){
            throw new Exception("not enough bushels");
        }

        this.bushels = this.bushels -price;
        this.acres = this.acres + wantedAcres;
    }
    
    
    public void sellLand(int acresToBeSold) throws Exception {
    	if(acresToBeSold < 0) {
    		throw new Exception("Invalid input");
    	}
    	
    	int pricePerAcres = 10;
    	
        if ( acresToBeSold > acres ){
                throw new Exception("Not enough acres");
        }
        int income = pricePerAcres * acresToBeSold ;

        this.bushels= this.bushels + income;
        this.acres = this.acres-acresToBeSold;
    }

    public void feed(int bushelsToFeed) throws Exception {
    	if(bushelsToFeed < 0) {
    		throw new Exception("Invalid input");
    	}
    	
    	if(this.bushels < bushelsToFeed) {
    		throw new Exception("Not enough bushels to feed.");
    	}
    	
    	this.bushels = this.bushels - bushelsToFeed;
    }

    public void plant(int acresToPlant) throws Exception {
    	if(acresToPlant < 0) {
    		throw new Exception("Invalid input.");
    	}
    	
    	int freeAcres = this.acres - this.plantedAcres;
    	
    	if(freeAcres <= 0) {
    		throw new Exception("Not enough land to plant seeds!");
    	}
    	
    	if(this.bushels == 0) {
    		throw new Exception("Not enough bushels available for planting seeds!");
    	}
    	
    	if((acres / 10) > this.population) {
    		throw new Exception("Not enough population to plant!");
    	}
    	
    	//Do the planting
    	this.bushels -= 1;
    	this.plantedAcres += 1;
    }
    
    public String toString() {
    	return String.format("In the year %d after its foundation, %d inhabitants live in the town of %s. The town owns %d bushels of grain and %d acres of land.",
    			this.pastYears,
    			this.population,
    			this.name,
    			this.bushels,
    			this.acres
    	);
    }

    public int getAcres() {
        return acres;
    }

    public int getBushels() {
        return bushels;
    }
    
    public int getPlantedAcres() {
    	return plantedAcres;
    }
    
    public int getPopulation() {
		return population;
	}
    
    public void setBushels(int newBushels) {
    	this.bushels = newBushels;
    }
}