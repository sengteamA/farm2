package main.items;

/**
 * Chemical Spray - an item used on crops to speed up harvest by 4 days.
 * Like compost but more effective!
 *
 * @author Nick
 */
public class ChemicalSpray extends Item {
	/**
	 * Creates some chemical spray.
	 */
	public ChemicalSpray() {
		super("Chemical Spray", 250, "Crop",
				"Speeds harvest by 4 days",
				"kills bugs, and humans if you're not careful");
	}
}