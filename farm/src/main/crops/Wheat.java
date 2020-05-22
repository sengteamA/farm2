package main.crops;

/**
 * The wheat crop, which is the cheapest crop available in-game.
 *
 * @author Nick
 */
public class Wheat extends Crop {
	/**
	 * Creates some wheat.
	 * @param dayPlanted the day this crop was purchased or planted
	 */
	public Wheat(int dayPlanted) {
		super("Wheat", 10, 4, 12, dayPlanted);
	}
}
