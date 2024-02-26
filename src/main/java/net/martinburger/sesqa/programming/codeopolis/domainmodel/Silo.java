package net.martinburger.sesqa.programming.codeopolis.domainmodel;

public class Silo {
	private Harvest[] stock;
	private int capacity;
	private int fillLevel;
	private String grainType;
	

	
	public Silo(int capacity) {
		this.stock = new Harvest[capacity];
		this.capacity = capacity;
		this.fillLevel = 0;
	}
	
	/*Getters*/
	public int getCapacity() {
		return capacity;
	}
	
	public int getFillLevel() {
		return fillLevel;
	}
	
	public String getGrainType() {
		return grainType;
	}
	
	/* Functionality methods */
	public int store(Harvest harvest) {
		int harvestLeft = 0;
		
		if(fillLevel == 0) {
			this.grainType = harvest.getGrainType();
		}
		
		if(!(harvest.getGrainType().equals(this.getGrainType()))) {
			return -1;
		}
		
		if(harvest.getAmount() > this.capacity) {
			harvestLeft = harvest.getAmount() - this.capacity;
			harvest = new Harvest(harvestLeft, harvest.getYear(), harvest.getGrainType());
		}
		
		int i = 0;
		while(true && i < stock.length) {
			if(stock[i] == null) {
				stock[i] = harvest;
				fillLevel += harvest.getAmount();
				break;
			}
			
			i++;
		}
		
		return harvestLeft;
	}
	
	public int takeOut(int amount) {
		int toTakeOut = amount;
		
		if(amount > this.fillLevel) {
			toTakeOut = this.fillLevel;
		}
		
		for(Harvest h: this.stock) {
			if(h != null) {
				if(h.getAmount() != 0) {
					h.remove(toTakeOut);
					this.fillLevel -= toTakeOut;
				}
			}
		}
		
		return toTakeOut;
	}
	
	public String toString() {
		return "";
	}
}
