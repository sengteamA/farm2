package main.crops;

/**
 * Represents a carrot. Cheap and yum!
 *
 * @author Nick
 */
public class Carrot extends Crop {
	/**
	 * Creates some carrot.
	 * @param dayPlanted the day this crop was purchased or planted
	 */
	public Carrot(int dayPlanted) {
		super("Carrot", 20, 6, 24, dayPlanted);
	}
}
