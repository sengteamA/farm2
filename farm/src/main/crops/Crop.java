package main.crops;

import main.Asset;

public class Crop extends Asset {
	private int daysToHarvest;
	private float daysElapsed = 0;
	private int sellingPrice;
	
	public Crop(String name, int purchasePrice, int daysToHarvest, int sellingPrice) {
		super(name, purchasePrice);
		this.daysToHarvest = daysToHarvest;
		this.sellingPrice = sellingPrice;
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
	
	public int getPrice() {
		return sellingPrice;
	}
	
	public String toString() {
		return name + ": days left " + daysToHarvest;
	}

}
