package main.crops;

import main.Asset;

public class Crop extends Asset {
	private int daysToHarvest;
	private float daysElapsed = 0;
	
	// protected so only subclasses can initialize a crop
	public Crop(String name, int purchasePrice, int daysToHarvest) {
		super(name, purchasePrice);
		this.daysToHarvest = daysToHarvest;
	}

	public String getName() {
		return name;
	}
	
	public int getDaysToHarvest() {
		return daysToHarvest;
	}
	
	public float getDaysElapsed() {
		return daysElapsed;
	}
	
	public String toString() {
		return name + ": days left " + daysToHarvest;
	}

}
