package main.crops;

import main.Asset;

public class Crop extends Asset {
	private int daysToHarvest;
	private int daysElapsed = 0;
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
	
	public void updateDaysElapsed(int day) {
		daysElapsed += day;
	}
	
	public int getDaysLeft() {
		float days = this.getDaysToHarvest() - this.getDaysElapsed();
		if (days <= 0) {
			days = 0;
		}
		return (int) days;
	}
	
	public int getPrice() {
		return sellingPrice;
	}
	
	public String toString() {
		return name + ": days left " + this.getDaysLeft();
	}
}