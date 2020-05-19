package main.farms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import main.animals.Animal;
import main.crops.*;
import main.items.Item;

/** Represents a Farm. Handles all the animals, crops, items owned by the
 * player, as well as money/bank balance.
 * @author Grant and Nick
 */
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
	 * @param myFarmType Type of farm (e.g. Tomacco Land)
	 * @param myFlavour Flavour text
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
	 * @param myFarmType Type of farm (e.g. Tomacco Land)
	 * @param myFlavour Flavour text
	 * @param myBank Bank balance / amount of money to start with
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
	 * @param myName new name of the farm
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
	 * @param amount the amount to increase the farm balance by.
	 */
	public void updateBankBalance(int amount) {
		bankBalance += amount;
	}

	/**
	 * Decrement number of actions left (action points) by one for the day.
	 */
	public void updateAP() {
		--actionsLeft;
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
	 * @param animal Animal to add to the farm
	 */
	public void addAnimal(Animal animal) {
		// User forgot to clone item before trying to add it
		// To clarify, two different Cow instances are fine. However, if the
		// two Cow variables point to the same place in memory, there will be
		// an error.
		if (animals.contains(animal)) {
			throw new IllegalArgumentException(
					"Cannot add two identical instances (duplicates) of an animal"
			);
		}
		animals.add(animal);
	}

	/**
	 * Adds a Crop instance to the farm.
	 * Note that the instance itself is added to the farm, not a
	 * deep copy of it.
	 *
	 * @param crop Crop to add to the farm
	 */
	public void addCrop(Crop crop) {
		if (crops.contains(crop)) {
			throw new IllegalArgumentException(
					"Cannot add two identical instances (duplicates) of a crop"
			);
		}
		crops.add(crop);
	}

	/**
	 * Adds an Item instance to the farm.
	 * @param item Item to add to the farm.
	 */
	public void addItem(Item item) {
		if (items.contains(item)) {
			throw new IllegalArgumentException(
					"Cannot add two identical instances (duplicates) of an item"
			);
		}
		items.add(item);
	}

	/**
	 * Deletes an Animal instance from the farm.
	 * @param runAway Animal to delete
	 */
	public void delAnimal(Animal runAway) {
		animals.remove(runAway);
	}

	/**
	 * Deletes a Crop instance from the farm.
	 * @param withered Crop to delete
	 */
	public void delCrop(Crop withered) {
		crops.remove(withered);
	}

	/**
	 * Deletes an Item instance from the farm.
	 * @param used Item to delete
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
	 * @param amount Amount to increase farm cap by
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
		for (Animal animal : animals) {
			money += animal.bonus();
		}
		return money;
	}

	/**
	 * Returns whether deducting an amount would result in bank balance
	 * going into debt.
	 *
	 * @param toDeduct Amount to deduct
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
	 * Returns whether the farmer has any items that can be used as food for
	 * animals, i.e. items whose type is "Animal".
	 *
	 * @return Whether farmer has any animal food.
	 */
	public boolean hasFoodItems() {
		Map<String, Long> counts = this.showItems().stream().filter(
				e -> e.getType().equals("Animal")).collect(
						Collectors.groupingBy(
								e -> e.getName(),
								Collectors.counting()
						)
				);
		if (counts.isEmpty()) {
			return false;
		}
		return true;
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
	 * Returns whether the farmer has any items that can be used on crops,
	 * i.e. items whose type is "Crop".
	 *
	 * @return Whether farmer has any items for crops.
	 */
	public boolean hasPlantItems() {
		Map<String, Long> counts = this.showItems().stream().filter(
				e -> e.getType().equals("Crop")).collect(
						Collectors.groupingBy(
								e -> e.getName(),
								Collectors.counting()
						)
				);
		if (counts.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * Checks if a particular crop is on the farm.
	 * @param subject Crop to check
	 * @return Whether subject is on the farm.
	 */
	public boolean plantInStock(Crop subject) {
		for (Crop crop : this.showCrops()) {
			if (crop.getName().equals(subject.getName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if a particular item is on the farm.
	 * @param subject Item to check
	 * @return Whether item is on the farm.
	 */
	public boolean itemInHand(Item subject) {
		for (Item item : this.showItems()) {
			if (item.getName().equals(subject.getName())) {
				return true;
			}
		}
		return false;
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

	/**
	 * Extracts items of a certain type from the list of items.
	 * A type is what kind of asset the item can be used on.
	 *
	 * @param type "Animal" or "Crop".
	 * @return A new list containing the items of the given type.
	 */
	public ArrayList<String> getItemType(String type) {
		ArrayList<String> iList = new ArrayList<String>();
		for (Item item : this.showItems()) {
			if (item.getType().equals(type)) {
				iList.add(item.getName());
			}
		}
		return iList;
	}

	/**
	 * Returns a string, which will contain all the kinds of crops the farm
	 * currently has in stock, with duplicates removed and in alphabetical
	 * order.
	 *
	 * @return A list of the kinds of crops on the farm.
	 */
	public ArrayList<String> getCropType() {
		ArrayList<String> farmCropTypes = new ArrayList<String>();
		for (Crop crop : crops) {
			if (!farmCropTypes.contains(crop.getName())) {
				farmCropTypes.add(crop.getName());
			}
		}
		Collections.sort(farmCropTypes);
		return farmCropTypes;
	}
}
