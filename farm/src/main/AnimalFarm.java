package main;

import java.util.ArrayList;

public class AnimalFarm extends Farm {
	//20% happiness and health bonus for all animals - Nick 16/04/2020
	public double happinessMultiplier = 1.2;
	public double healthMultiplier = 1.2;
	
	AnimalFarm() {
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
		super.updateBankBalance(amount);
	}
	
	public String Flavour() {
	return "All Animals are equal, but some are more equal than others.\n"
		+ "All animals start 20% happier and healthier on purchase\n"
		+ "Feeding and playing with animals is 20% more effective\n";
	}
	
	
}
