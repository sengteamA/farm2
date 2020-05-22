package main.crops;

/**
 * Represents mushroom, a type of crop. A slight upgrade from carrot.
 *
 * @author Nick
 */
public class Mushroom extends Crop {
	/**
	 * Creates some mushroom.
	 * @param dayPlanted the day this crop was purchased or planted
	 */
	public Mushroom(int dayPlanted) {
		super("Mushroom", 30, 7, 39, dayPlanted);
	}
}