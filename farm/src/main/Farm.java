package main;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;
import main.animal.*;
import main.crops.*;
import main.items.*;

public class Farm {
	private String name;
	private int bankBalance = 1000;
	private ArrayList<Animal> animals;
	private ArrayList<Crop> crops;
	private ArrayList<Item> items;
	private float cropGrowthSpeed = 1;
	private int initialCashBonus;
	private float happinessMultiplier = 1;
	private float healthMultiplier = 1;
	private int actionsLeft = 2;
	private int farmCap = 10;
	private String flavour;
	private String farmType;

	public Farm() {
		animals = new ArrayList<Animal>();
		crops = new ArrayList<Crop>();
		items = new ArrayList<Item>();
	}

	public String getName() {
		return name;
	}

	public int getBankBalance() {
		return bankBalance;
	}

	public int getActionsLeft() {
		return actionsLeft;
	}

	public ArrayList<Crop> showCrops() {
		return crops;
	}

	public ArrayList<Animal> showAnimals() {
		return animals;
	}

	public ArrayList<Item> showItems() {
		return items;
	}

	public void updateBankBalance(int amount) {
		bankBalance += amount;
	}

	public void setName(Scanner sc) {
		while (true) {
			String s;
			System.out.println("Please enter a name between 3 to 15 characters long without numbers of symbols");
			s = sc.nextLine();
			name = s;
			if (isValidName(name)) {
				break;
			} else {
				name = null;
			}
		}
	}

	// deducts actions left by 1 - Nick 15/04/2020
	public void updateAP() {
		actionsLeft -= 1;
	}

	// this method will continue to demand for entries until a valid name is provided - Nick 15/04/2020
	//
	// this checks if the string consists of words separated by at most one space
	// https://regexr.com/ is a good place to test if regex works correctly
	public boolean isValidName(String name) {
		if (name.length() < 3 || name.length() > 15) {
			System.out.println("Name is outside required length");
			return false;
		} else if (name.matches("^[A-Za-z]+( [A-Za-z]+)*$") == false) {
			System.out.println("Name cannot contain numbers or symbols, or extra spaces");
			return false;
		}
		return true;
	}

	public String getFlavour() {
		return flavour;
	}

	public String getType() {
		return farmType;
	}

	public void setType(String chosenFarm) {
		farmType = chosenFarm;
	}

	public void addAnimals(Animal newAnimal) {
		animals.add(newAnimal);
	}

	public void addCrops(Crop newCrop) {
		crops.add(newCrop);
	}

	public void addItems(Item newItem) {
		items.add(newItem);
	}

	public void delAnimals(Animal runAway) {
		animals.remove(runAway);
	}

	public void delCrops(Crop withered) {
		crops.remove(withered);
	}

	public void delItems(Item used) {
		items.remove(used);
	}

	public int getFarmCap() {
		return farmCap;
	}

	public void addCap(int amount) {
		farmCap += amount;
	}
}
}
