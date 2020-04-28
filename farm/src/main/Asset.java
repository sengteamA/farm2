package main;

/**
 * Basic "asset" that can be purchased or sold in the store.
 * @author Grant
 *
 */
public class Asset {
	protected String name;
	protected int purchasePrice;

	// protected so only subclasses can initialize an asset
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
}
