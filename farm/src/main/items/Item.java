package main.items;

import main.Asset;

public class Item extends Asset {
	
	private String type;
	private int units;
	private String benefit;
	private String flavour;
	
	public Item(String name, int purchasePrice, String type, String benefit, String flavour) {
		super(name, purchasePrice);
		this.type = type;
		this.units = units;
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
	
	public int getUnits() {
		return units;
	}
	
	public void updateUnits(int amount) {
		units += amount;
	}
	
	public String toString() {
		return name + ": " + benefit;
	}
}
