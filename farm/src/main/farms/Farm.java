package main.farms;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import main.animals.Animal;
import main.crops.*;
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

	/**
	 * Creates a new farm.
	 * @param myFarmType - Type of farm (e.g. Tomacco Land)
	 * @param myFlavour - Flavour text
	 */
	public Farm(String myFarmType, String myFlavour) {
		animals = new ArrayList<Animal>();
		crops = new ArrayList<Crop>();
		items = new ArrayList<Item>();
		farmType = myFarmType;
		flavour = myFlavour;
	}

	/**
	 * Creates a new farm.
	 * @param myFarmType - Type of farm (e.g. Tomacco Land)
	 * @param myFlavour - Flavour text
	 * @param myBank - Bank balance / amount of money to start with
	 */
	public Farm(String myFarmType, String myFlavour, int myBank) {
		animals = new ArrayList<Animal>();
		crops = new ArrayList<Crop>();
		items = new ArrayList<Item>();
		farmType = myFarmType;
		flavour = myFlavour;
		bankBalance = myBank;
	}

	/**
	 * Returns the user-set name of the farm.
	 * This is different to the type of the farm, e.g. "Tomacco Land".
	 * @return name of farm
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the farm to myName.
	 * Used by SetupGUI.
	 *
	 * @param myName - new name of the farm
	 */
	public void setName(String myName) {
		name = myName;
	}

	/**
	 * Returns the bank balance / amount of money in the farm.
	 * @return Bank balance
	 */
	public int getBankBalance() {
		return bankBalance;
	}

	/**
	 * Returns number of actions left for the day.
	 * @return Number of daily actions left
	 */
	public int getActionsLeft() {
		return actionsLeft;
	}

	/**
	 * Returns the bonus animals get for health and happiness upon purchasing.
	 * This will be 0 unless the farm type is an Animal Farm.
	 * @return Bonus amount (zero by default)
	 */
	public float getAnimalBonus() {
		return 0;
	}

	/**
	 * Returns the animals the player has on the farm.
	 * @return An array list of animals.
	 */
	public ArrayList<Animal> showAnimals() {
		return animals;
	}

	/**
	 * Returns the crops the player has on the farm.
	 * @return An array list of crops.
	 */
	public ArrayList<Crop> showCrops() {
		return crops;
	}

	/**
	 * Returns the items the player has on the farm.
	 * @return An array list of items.
	 */
	public ArrayList<Item> showItems() {
		return items;
	}

	/**
	 * Increase the farm's money / bank balance by the specified amount.
	 * @param amount - the amount to increase the farm balance by.
	 */
	public void updateBankBalance(int amount) {
		bankBalance += amount;
	}

	/**
	 * Decrement number of actions left (action points) by one for the day.
	 */
	public void updateAP() {
		actionsLeft -= 1;
	}

	/**
	 * Returns the flavour text of the farm
	 * @return Flavour text of the farm
	 */
	public String getFlavour() {
		return flavour;
	}

	/**
	 * Returns the farm type, e.g. "Trump Ranch"
	 * @return Type of farm
	 */
	public String getType() {
		return farmType;
	}

	/**
	 * Adds an Animal instance to the farm.
	 * Note that the instance itself is added to the farm, not a
	 * deep copy of it.
	 *
	 * @param animal - Animal to add to the farm
	 */
	public void addAnimal(Animal animal) {
		animals.add(animal);
	}

	/**
	 * Adds a Crop instance to the farm.
	 * Note that the instance itself is added to the farm, not a
	 * deep copy of it.
	 *
	 * @param crop - Crop to add to the farm
	 */
	public void addCrop(Crop crop) {
		crops.add(crop);
	}

	/**
	 * Adds an Item instance to the farm.
	 * @param item - Item to add to the farm.
	 */
	public void addItem(Item item) {
		items.add(item);
	}

	/**
	 * Deletes an Animal instance from the farm.
	 * @param runAway - Animal to delete
	 */
	public void delAnimal(Animal runAway) {
		animals.remove(runAway);
	}

	/**
	 * Deletes a Crop instance from the farm.
	 * @param withered - Crop to delete
	 */
	public void delCrop(Crop withered) {
		crops.remove(withered);
	}

	/**
	 * Deletes an Item instance from the farm.
	 * @param used - Item to delete
	 */
	public void delItem(Item used) {
		items.remove(used);
	}

	/**
	 * Returns the farm cap, i.e. the maximum number of crops
	 * allowed on the farm.
	 *
	 * @return Cap on number of crops
	 */
	public int getFarmCap() {
		return farmCap;
	}

	/**
	 * Increase farm cap by amount.
	 * @param amount - Amount to increase farm cap by
	 */
	public void addCap(int amount) {
		farmCap += amount;
	}

	/**
	 * Retrives the farm's daily bonus money.
	 * @return Daily bonus money
	 */
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
	 *
	 * @param toDeduct - Amount to deduct
	 * @return Whether there will be enough money after deducting
	 */
	public boolean hasEnoughMoney(int toDeduct) {
		return getBankBalance() - toDeduct >= 0;
	}

	/**
	 * Returns whether there are any animals in the farm.
	 * @return Number of animals != 0
	 */
	public boolean hasAnimals() {
		return !this.showAnimals().isEmpty();
	}

	/**
	 * TODO:
	 * @return
	 */
	public boolean hasFoodItems() {
		boolean result = true;
		Map<String, Long> counts = this.showItems().stream().filter(
				e -> e.getType().equals("Animal")).collect(
						Collectors.groupingBy(
								e -> e.getName(),
								Collectors.counting()
						)
				);
		if (counts.isEmpty()) {
			result = false;
		}
		return result;
		//counts.keySet() this gives us a list of animals
	}

	/**
	 * Returns whether there are any crops in the farm.
	 * @return Number of crops != 0
	 */
	public boolean hasCrops() {
		return !this.showCrops().isEmpty();
	}

	/**
	 * TODO:
	 * @return
	 */
	public boolean hasPlantItems() {
		boolean result = true;
		Map<String, Long> counts = this.showItems().stream().filter(e -> e.getType().equals("Crop")).collect(Collectors.groupingBy(e -> e.getName(), Collectors.counting()));
		if (counts.isEmpty()) {
			result = false;
		}
		return result;
	}

	/**
	 * Checks if a particular crop is on the farm.
	 * @param subject - Crop to check
	 * @return Whether subject is on the farm.
	 */
	public boolean plantInStock(Crop subject) {
		boolean outcome = false;
		for (Crop crop : this.showCrops()) {
			if (crop.getName().equals(subject.getName())) {
				outcome = true;
			}
		}
		return outcome;
	}

	/**
	 * Checks if a particular item is on the farm.
	 * @param subject - Item to check
	 * @return Whether item is on the farm.
	 */
	public boolean itemInHand(Item subject) {
		boolean outcome = false;
		for (Item item : this.showItems()) {
			if (item.getName().equals(subject.getName())) {
				outcome = true;
			}
		}
		return outcome;
	}

	/**
	 * Refresh AP (daily actions) to its original number of two.
	 * Used at the end of the day.
	 */
	public void refreshAP() {
		this.actionsLeft = 2;
	}

	/**
	 * Returns whether there is enough space on the farm to add
	 * another crop.
	 *
	 * A necessary check before each crop is purchased.
	 * @return Whether there is enough space on farm.
	 */
	public boolean hasSpace() {
		return crops.size() < this.getFarmCap();
	}

	///extracts types of items from the item list
	public ArrayList<String> getItemType(String Type) {
		ArrayList<String> iList = new ArrayList<String>();
		for (Item item: this.showItems()) {
			if (item.getType().equals(Type)) {
				iList.add(item.getName());
			}
		}
		return iList;
	}

	// returns a string, which will contain crops farm currently has in stock
	// manually tested as unable to reconfigure JUNIT pathing
	public ArrayList<String> getCropType() {
		ArrayList<String> cList = new ArrayList<String>();
		Carrot carrot = new Carrot();
		Hipotke hip = new Hipotke();
		Mushroom mush = new Mushroom();
		Tomacco tomacco = new Tomacco();
		Wasabi wasabi = new Wasabi();
		Wheat wheat = new Wheat();

		//test carrot membership
		if (this.plantInStock(carrot) == true && !cList.contains(carrot.getName())) {
			cList.add(carrot.getName());
		}
		else if (!this.plantInStock(carrot) && cList.contains(carrot.getName())) {
			cList.remove(carrot.getName());

		}
		//test hipotke membership
		if (this.plantInStock(hip) == true && !cList.contains(hip.getName())) {
			cList.add(hip.getName());
		}
		else if (!this.plantInStock(hip) && cList.contains(hip.getName())) {
			cList.remove(hip.getName());
		}

		//test mushroom membership
		if (this.plantInStock(mush) == true && !cList.contains(mush.getName())) {
			cList.add(mush.getName());
		}
		else if (!this.plantInStock(mush) && cList.contains(mush.getName())) {
			cList.remove(mush.getName());
		}

		//test tomacco membership
		if (this.plantInStock(tomacco) == true && !cList.contains(tomacco.getName())) {
			cList.add(tomacco.getName());
		}
		else if (!this.plantInStock(tomacco) && cList.contains(tomacco.getName())) {
			cList.remove(tomacco.getName());
		}

		//test wasabi membership
		if (this.plantInStock(wasabi) == true && !cList.contains(wasabi.getName())) {
			cList.add(wasabi.getName());
		}
		else if (!this.plantInStock(wasabi) && cList.contains(wasabi.getName())) {
			cList.remove(wasabi.getName());
		}

		//test wheat membership
		if (this.plantInStock(wheat) == true && !cList.contains(wheat.getName())) {
			cList.add(wheat.getName());
		}
		else if (!this.plantInStock(wheat) && cList.contains(wheat.getName())) {
			cList.remove(wheat.getName());
		}

		return cList;
	}
}
