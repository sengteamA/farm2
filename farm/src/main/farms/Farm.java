package main.farms;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import main.animals.Animal;
import main.crops.Crop;
import main.items.Item;

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

	public void addAnimal(Animal newAnimal) {
		animals.add(newAnimal);
	}

	public void addCrop(Crop newCrop) {
		crops.add(newCrop);
	}

	public void addItem(Item newItem) {
		items.add(newItem);
	}

	public void delAnimal(Animal runAway) {
		animals.remove(runAway);
	}

	public void delCrop(Crop withered) {
		crops.remove(withered);
	}

	public void delItem(Item used) {
		items.remove(used);
	}

	public int getFarmCap() {
		return farmCap;
	}

	public void addCap(int amount) {
		farmCap += amount;
	}
	
	public int getDailyBonusMoney() {
		int money = 0;
		for (Animal animal: animals) {
			money += animal.bonus();
		}
		return money;
	}

	/**
	 * Returns whether deducting an amount would result in bank balance
	 * going into debt.
	 * @param toDeduct
	 * @return whether bankBalance - toDeduct < 0
	 */
	public boolean hasEnoughMoney(int toDeduct) {
		return getBankBalance() - toDeduct >= 0;
	}
	
	public boolean hasAnimals() {
		boolean result = true;
		if (this.showAnimals().isEmpty()) {
			result = false;
		}
		return result;
	}
	public boolean hasFoodItems() {
		boolean result = true;
		Map<String, Long> counts = this.showItems().stream().filter(e -> e.getType().equals("Animal")).collect(Collectors.groupingBy(e -> e.getName(), Collectors.counting()));
		if (counts.isEmpty()) {
			result = false;
		}
		return result;
		//counts.keySet() this gives us a list of animals
	}
	
	public boolean hasCrops() {
		boolean result = true;
		if (this.showCrops().isEmpty()) {
			result = false;
		}
		return result;
	}
	
	public boolean hasPlantItems() {
		boolean result = true;
		Map<String, Long> counts = this.showItems().stream().filter(e -> e.getType().equals("Crop")).collect(Collectors.groupingBy(e -> e.getName(), Collectors.counting()));
		if (counts.isEmpty()) {
			result = false;
		}
		return result;
	}
}
