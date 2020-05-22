package main.crops;

import main.Asset;

/**
 * Represents a crop. Can be bought, and after a set number of days, it will
 * be ready to harvest. Harvesting automatically sells the crops at their
 * selling price.
 *
 * @author Grant
 */
public class Crop extends Asset {
	/**
	 * The number of days that must elapse before a crop can be harvested.
	 */
	private int daysToHarvest;

	/**
	 * The day the crop was grown/planted. Since crops are planted immediately
	 * after purchase, this is equivalent to the day the crop was purchased.
	 */
	private int dayPlanted;

	/**
	 * Days elapsed measures how many days has passed since purchase.
	 */
	private int daysElapsed = 0;

	/**
	 * Selling price is the amount of money awarded to the player once the
	 * crop is harvested.
	 */
	private int sellingPrice;

	/**
	 * Creates a new crop.
	 *
	 * @param name name of the crop
	 * @param purchasePrice the price the player pays to buy
	 * @param daysToHarvest the days a crop must go through before it is
	 *        ready for harvest
	 * @param sellingPrice how much money the crop will bring once harvested
	 *        and sold
	 * @param dayPlanted the day this crop was planted/purchased; used
	 * 		  exclusively for informational purposes and not in any game logic
	 */
	public Crop(String name, int purchasePrice, int daysToHarvest, int sellingPrice, int dayPlanted) {
		super(name, purchasePrice);
		this.daysToHarvest = daysToHarvest;
		this.sellingPrice = sellingPrice;
		this.dayPlanted = dayPlanted;
	}

	/**
	 * Returns the number of days until the crop is ready to be
	 * harvested and automatically sold.
	 *
	 * @return days remaining until ready for harvest
	 */
	public int getDaysToHarvest() {
		return daysToHarvest;
	}

	/**
	 * Returns the number of days it has been since purchasing the crop.
	 *
	 * @return days elapsed
	 */
	public float getDaysElapsed() {
		return daysElapsed;
	}

	/**
	 * Increases the number of elapsed days by a certain integer.
	 *
	 * This can be affected by natural passage of time or player action.
	 *
	 * @param day number of days to increase by
	 */
	public void updateDaysElapsed(int day) {
		daysElapsed += day;
	}

	/**
	 * Returns the number of days remaining until the crop is ready to be
	 * harvested and automatically sold.
	 *
	 * @return number of days left before the crop is ready
	 */
	public int getDaysLeft() {
		float days = this.getDaysToHarvest() - this.getDaysElapsed();
		if (days <= 0) {
			days = 0;
		}
		return (int) days;
	}

	/**
	 * Return how much money the crop sells for. This is automatically added
	 * to the player's bank balance when they harvest the crop.
	 *
	 * @return selling price of the crop
	 */
	public int getSellingPrice() {
		return sellingPrice;
	}

	/**
	 * Returns basic information about the crop, used in the main interface.
	 *
	 * @return a message showing how many days left till crop matures
	 */
	@Override
	public String toString() {
		return name + ": planted day " + dayPlanted + ", " +
				this.getDaysLeft() + " days left";
	}

	/**
	 * Retrieves detailed multi-line information about the crop, used as a
	 * description in the store.
	 *
	 * @return detailed message about the attributes of the crop
	 */
	@Override
	public String getInfo() {
		return getName() + " - " + getPurchasePrice() + " (" +
				getDaysToHarvest() + " days)";
	}
}