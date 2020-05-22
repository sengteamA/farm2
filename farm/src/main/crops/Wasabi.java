package main.crops;

/**
 * Wasabi, a type of crop. Expensive but selling price is pretty high.
 *
 * @author Nick
 */
public class Wasabi extends Crop {
	/**
	 * Creates some wasabi.
	 * @param dayPlanted the day this crop was purchased or planted
	 */
	public Wasabi(int dayPlanted) {
		super("Wasabi", 100, 15, 150, dayPlanted);
	}
}
