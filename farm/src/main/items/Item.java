package main.items;

import main.Asset;

public class Item extends Asset {

	private String type;
	private String benefit;
	private String flavour;

	public Item(String name, int purchasePrice, String type,
			String benefit, String flavour) {
		super(name, purchasePrice);
		this.type = type;
		this.benefit = benefit;
		this.flavour = flavour;
	}

	public String getType() {
		return type;
	}

	public String getBenefit() {
		return benefit;
	}

	public String getFlavour() {
		return flavour;
	}

	@Override
	public String toString() {
		return name + ": " + benefit;
	}

	@Override
	public String getInfo() {
		return name + ": (for " + type + ")\n" + benefit + "\n" + flavour;
	}
}
