package main;

import java.util.ArrayList;

public class TrumpRanch extends Farm {
	public float discount = (float)0.9; //10% discount on all purchases
	public float bonus = (float)1.1; //10% bonus on selling crops
	public int bankBalance = 1200; // 20% bonus starting cash
	TrumpRanch() {
		super();
	}
	
	public String getName() {
		return super.getName();
	}
	
	public int getBankBalance() {
		return bankBalance;
	}
	
	public int getActionsLeft() {
		return super.getActionsLeft();
	}
	
	/*
	 * animal, crops, items not yet implemented
	public ArrayList showCrops() {
		return super.showCrops();
	}
	
	public Arraylist showAnimals() {
		return super.showAnimals();
	}
	
	public Arraylist showItems() {
		return super.showItems();
	}*/
	
	public void setName() {
		super.setName();
	}
	
	public void updateAP() {
		super.updateAP();
	}
	
	public void updateBankbalance(int amount) {
		bankBalance += amount;
	}
	
	public String Flavour() {
		return "I always get the best deals!!\n"
				+ "All purchases are 10% cheaper\n"
				+ "10% bonus on all sales\n"
				+ "Starts game with 20% more cash";
	}
}
