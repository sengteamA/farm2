package main;

/**
 * Basic "asset" that can be purchased or sold in the store.
 * @author Grant
 *
 */
public class Asset {
	protected String name;
	protected int purchasePrice;

	public Asset(String name, int purchasePrice) {
		this.name = name;
		this.purchasePrice = purchasePrice;
	}

	public String getName() {
		return name;
	}

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
