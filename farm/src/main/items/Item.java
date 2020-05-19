/**
 * a child class of asset which can be bought from stores and consumed for player benefit
 * Divided into animal and crop items, they benefit these groups differently
 */
package main.items;

import main.Asset;

public class Item extends Asset {

	private String type;
	private String benefit;
	private String flavour;
	/**
	 * 
	 * @param name - of the item
	 * @param purchasePrice - price to buy
	 * @param type - used on animals or crops
	 * @param benefit - the function of the item
	 * @param flavour - a description of the item
	 */
	public Item(String name, int purchasePrice, String type,
			String benefit, String flavour) {
		super(name, purchasePrice);
		this.type = type;
		this.benefit = benefit;
		this.flavour = flavour;
	}
	
	/**
	 * 
	 * @return type of item in string
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * 
	 * @return benefit description in a string
	 */
	public String getBenefit() {
		return benefit;
	}
	
	/**
	 * 
	 * @return return flavour text of item
	 */
	public String getFlavour() {
		return flavour;
	}

	/**
	 * @return return a string representation of the item
	 */
	@Override
	public String toString() {
		return name + ": " + benefit;
	}
	
	/**
	 * @return return a detailed string representation of the item
	 */
	@Override
	public String getInfo() {
		return name + ": (for " + type + ")\n" + benefit + "\n" + flavour;
	}
}
