/**
 * This is the Crop class, a child of the Asset class. 
 * It reresents that skeleton of the crops that can be bought and planted.
 * Once its daystoHarvest reachs 0, the crops can be harvested.
 * 
 * @author Grant Wong
 */

package main.crops;

import main.Asset;

public class Crop extends Asset {
	/**
	 * The number of days that must elapse before a crop can be harvested.
	 */
	private int daysToHarvest;
	
	/**
	 * Days elapsed measures how many days has passed since purchase.
	 */
	private int daysElapsed = 0;
	
	/**
	 * Selling price is the income awarded to the player once the crop is harvested.
	 */
	private int sellingPrice;
	
	/**
	 * 
	 * @param name - name of the crop
	 * @param purchasePrice - the price the player pays to buy
	 * @param daysToHarvest - the days a crop must go through before it is ready for harvest
	 * @param sellingPrice - the worth of the crop once it matures
	 */

	public Crop(String name, int purchasePrice, int daysToHarvest, int sellingPrice) {
		super(name, purchasePrice);
		this.daysToHarvest = daysToHarvest;
		this.sellingPrice = sellingPrice;
	}
	
	/**
	 * 
	 * @return returns days to harvest
	 */
	public int getDaysToHarvest() {
		return daysToHarvest;
	}
	
	/**
	 * 
	 * @return days elapsed
	 */
	public float getDaysElapsed() {
		return daysElapsed;
	}

	/**
	 * 
	 * @param day - an integer assigned. This can be affected by natural passage of time or player action.
	 */
	public void updateDaysElapsed(int day) {
		daysElapsed += day;
	}
	
	/**
	 * 
	 * @return number of days left before the crop is ready. 
	 */
	public int getDaysLeft() {
		float days = this.getDaysToHarvest() - this.getDaysElapsed();
		if (days <= 0) {
			days = 0;
		}
		return (int) days;
	}
	
	/**
	 * 
	 * @return selling price of the crop.
	 */
	public int getPrice() {
		return sellingPrice;
	}
	
	/**
	 * @return a message showing how many days left till crop matures.
	 */
	@Override
	public String toString() {
		return name + ": days left " + this.getDaysLeft();
	}

	/**
	 * @return detailed message about the attributes of the crop.
	 */
	@Override
	public String getInfo() {
		return getName() + " - " + getPurchasePrice() + " (" +
				getDaysToHarvest() + " days)";
	}
}