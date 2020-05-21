package main.items;

/**
 * Compost - an item used on crops to speed up harvest by 2 days.
 *
 * @author Nick
 */
public class Compost extends Item {
	/**
	 * Creates some new compost.
	 */
	public Compost() {
		super("Compost", 150, "Crop",
				"Speeds up harvest by 2 days.",
				"enriches the soil, full of delicious micronutrients");
	}
}