package main;

import java.util.ArrayList;

public class MoomooFarm extends Farm {
	
	public float cow_discount = (float)0.8;
	public float cow_factor = (float)1;
	
	public MoomooFarm() {
		super();
	}
	
	public String getName() {
		return super.getName();
	}
	
	public int getBankBalance() {
		return super.getBankBalance();
	}
	public int getActionsLeft() {
		return super.getActionsLeft();
	}
	
	/*
	 * animal, crops, items not yet implemented. 
	public ArrayList showCrops() {
		return super.showCrops();
	}
	
	public Arraylist showAnimals() {
		return super.showAnimals();
	}
	
	public Arraylist showItems() {
		return super.showItems();
	}
	
	public void hasCow() {
		if (animals.contains("cow")) {
			cow_factor = 1.2;
		}
	}*/
	
	public void setName() {
		super.setName();
	}
	
	public void updateAP() {
		super.updateAP();
	}
	
	public void updateBankbalance(int amount) {
		super.updateBankBalance(amount);
	}
	public String Flavour() {
		return "Cows be thy god\n"
			+ "cows can be purchased with 20% discount\n"
			+ "tend crops 20% more effective when farm has cows";
	
}
