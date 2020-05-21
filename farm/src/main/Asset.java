package main;

/**
 * Basic "asset" that can be purchased or sold in the store, or used by the
 * player on the farm.
 *
 * @author Grant
 *
 */
public class Asset {
	protected String name;
	protected int purchasePrice;

	/**
	 * Creates a new asset. This could be a crop, an animal, or an item.
	 *
	 * @param name name of the asset, used as a unique identifier for the item
	 * @param purchasePrice the cost to purchase this in the store
	 */
	public Asset(String name, int purchasePrice) {
		this.name = name;
		this.purchasePrice = purchasePrice;
	}

	/**
	 * Returns the name of the asset.
	 * @return name of asset
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns how much the asset costs to purchase in the store.
	 * @return price of asset
	 */
	public int getPurchasePrice() {
		return purchasePrice;
	}

	/**
	 * Returns any relevant general information about the asset, with
	 * more detail than toString().
	 *
	 * @return information about the asset, as a multi-line string.
	 */
	public String getInfo() {
		return getName() + " - " + getPurchasePrice();
	}

	/**
	 * Returns brief information about the asset.
	 * This will be overridden by Animal, Crop and Item.
	 *
	 * @return Brief information about the asset
	 */
	@Override
	public String toString() {
		return name + ": " + purchasePrice;
	}
}
