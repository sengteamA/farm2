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
	 * Returns any relevant general information about the asset,
	 * to be used in the description of the asset in the Store.
	 * @return information about the asset, as a single-line string.
	 */
	public String getInfo() {
		return getName() + " - " + getPurchasePrice();
	}
}
