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
		int days_left = daysToHarvest - daysElapsed;
		if (days_left < 0) {
			days_left = 0;
		}
		return days_left;
	}
	
	public float getDaysElapsed() {
		return daysElapsed;
	}
	
	public void updateDaysElapsed(int day) {
		daysElapsed += day;
	}
	
	public int getPrice() {
		return sellingPrice;
	}
	
	public String toString() {
		return name + ": days left " + this.getDaysToHarvest();
	}

}
