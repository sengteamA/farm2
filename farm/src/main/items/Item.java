package main.items;

import main.Asset;

/**
 * A child class of asset which can be bought from stores and consumed for
 * player benefit. Divided into animal and crop items; they benefit these
 * groups differently.
 *
 * @author Nick
 */
public class Item extends Asset {
	private String type;
	private String benefit;
	private String flavour;

	/**
	 * Creates a new Item with different qualities.
	 *
	 * @param name of the item
	 * @param purchasePrice price to buy, used in StoreGUI
	 * @param type either "Animal" or "Crop", representing which of the two it
	 * 		       is used for
	 * @param benefit the function of the item
	 * @param flavour a description of the item, used in StoreGUI
	 */
	public Item(String name, int purchasePrice, String type,
			String benefit, String flavour) {
		super(name, purchasePrice);
		this.type = type;
		this.benefit = benefit;
		this.flavour = flavour;
	}

	/**
	 * Returns either "Animal" or "Crop".
	 *
	 * @return type of item in string
	 */
	public String getType() {
		return type;
	}

	/**
	 * Returns what benefit the item has to the player.
	 *
	 * @return benefit description in a string
	 */
	public String getBenefit() {
		return benefit;
	}

	/**
	 * Return the item's silly flavour text.
	 *
	 * @return flavour text of item
	 */
	public String getFlavour() {
		return flavour;
	}

	/**
	 * Return a single-line description of the item.
	 *
	 * @return a string representation of the item
	 */
	@Override
	public String toString() {
		return name + ": " + benefit;
	}

	/**
	 * Returns a multi-line detailed string representation of the item. Used in
	 * StoreGUI, among other places.
	 *
	 * @return a detailed string representation of the item
	 */
	@Override
	public String getInfo() {
		return name + ": (for " + type + ")\n" + benefit + "\n" + flavour;
	}
}
