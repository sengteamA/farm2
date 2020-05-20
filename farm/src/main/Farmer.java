package main;

import java.util.InputMismatchException;
import java.util.ListIterator;
import java.util.Scanner;

import main.animals.*;
import main.crops.*;
import main.farms.*;
import main.items.*;

/**
 * Handles daily actions made to the farm by the player.
 * Also stores name and age of player.
 *
 * @author Nick
 *
 */
public class Farmer {
	private String name;
	private int age;
	private Farm farm;

	/**
	 * Initialises Farmer using an existing farm.
	 * @param myFarm - the farm to use
	 */
	public Farmer(Farm myFarm) {
		farm = myFarm;
	}

	/**
	 * Initialises Farmer using an existing farm, in addition to a
	 * name and an age for the farmer.
	 *
	 * This constructor assumes that all validation (checking if name
	 * is valid, for instance) has already been done.
	 *
	 * @param myFarm the farm to use
	 * @param myName name of farmer
	 * @param myAge age of farmer
	 */
	public Farmer(Farm myFarm, String myName, int myAge) {
		farm = myFarm;
		name = myName;
		age = myAge;
	}

	/**
	 * Returns the name of the farmer. Note that the name will have been set
	 * by the player.
	 *
	 * @return name of the farmer
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the age of the farmer. This could be any integer, as there are
	 * no restrictions on what value the age can be.
	 *
	 * @return age of the farmer
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Set a name for the farmer. Used in command-line interface only -- the
	 * GUI uses the Farmer constructor instead.
	 *
	 * @param newName the string to set the farmer's name to
	 */
	public void setName(String newName) {
		name = newName;
	}

	/**
	 * Set age of farmer. Used in command-line interface only -- the GUI uses
	 * the Farmer constructor instead.
	 *
	 * @param newAge the integer to set the farmer's age to
	 */
	public void setAge(int newAge) {
		age = newAge;
	}

	/**
	 * Will be used by Farmer.setDetails() to check whether a name is valid.
	 * (This is not implemented yet.)
	 *
	 * Note that for the GUI, the validation will be done in the GUI, as opposed to through
	 * the isValidName method.
	 *
	 * @param name - the name to check
	 * @return whether name is a valid name
	 */
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

	/**
	 * Prompt user for their name and age. Used by the command-line interface.
	 * @param sc where to get user input from (usually System.in)
	 */
	public void setDetails(Scanner sc) {
		System.out.print("Type your own name here: ");
		String name;
		while (true) {
			name = sc.nextLine();
			if (isValidName(name)) {
				setName(name);
				break;
			} else {
				name = null;
			}
		}
		System.out.println("Hello " + getName() + "!");
		System.out.print("Type your age: ");
		while (true) {
			try {
				setAge(sc.nextInt());
				sc.nextLine();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Please type a valid number.");
				sc.next();
			}
		}
		System.out.printf("Got it. You are %d years old.\n", getAge());
	}

	/**
	 * All benefits for each item hard coded. Item affects all items of specific type.
	 * If watering plants is selected, then choice will be null.
	 * Tomacco Land crops should grow one day faster per action.
	 * Moo Moo Farm crops should grow 2 days faster per action if cow is in play.
	 *
	 * @param action The action to perform
	 * @param type TODO
	 * @param choice TODO
	 */
	public void tendToCrops(String action, Crop type, Item choice) {
		float days = 0;
		if (action.equals("watering plants")) {
			days = 1;
		}

		else if (action.equals("use item")) {
			if (choice.getName().equals("Chemical Spray")) {
				days = 4;
			}
			else if (choice.getName().equals("Compost")) {
				days = 2;
			}
			else if (choice.getName().equals("Instant-Grow Lite(R)")) {
				days = type.getDaysLeft() / 2;
			}
			else if (choice.getName().equals("Instant-Grow Pro(R)")) {
				days = type.getDaysToHarvest();
			}
		}

		if (farm instanceof TomaccoLand) {
			++days;
		}
		else if (farm instanceof MoomooFarm && ((MoomooFarm) farm).hasCow()) {
			days += 2;
		}

		for (Crop crop : farm.showCrops()) {
			if (crop.getName().equals(type.getName())) {
				crop.updateDaysElapsed((int)days);
			}
		}

		//corrected item deletion
		if (choice != null) {
			ListIterator<Item> iterator = farm.showItems().listIterator();
			while (iterator.hasNext()) {
				Item item = iterator.next();
				if (item.getName().equals(choice.getName())) {
					iterator.remove();
					break;
				}
			}
		}
		farm.updateAP();
	}

	public void feedAnimals(Item choice) {
		float health = 0;
		if (choice.getName().equals("Stockfeed")) {
			health = 50;
			}
		else if (choice.getName().contentEquals("Panda Gummy")) {
			health = 100;
		}
		if (farm instanceof AnimalFarm) {
			health += health * farm.getAnimalBonus();
		}
		for (Animal animal : farm.showAnimals()) {
			animal.updateHealth((int)health);
		}
		//corrected item deletion process
		ListIterator<Item> iterator = farm.showItems().listIterator();

		while (iterator.hasNext()) {
			Item item = iterator.next();
			if (item.getName().equals(choice.getName())) {
				iterator.remove();
				break;
			}
		}
		farm.updateAP();
	}

	public void playWithAnimals() {
		for (Animal animal : farm.showAnimals()) {
			float happy = 30;
			if (farm instanceof AnimalFarm) {
				happy += happy * farm.getAnimalBonus();
			}
			animal.updateHappiness((int)happy);
		}
		farm.updateAP();
	}

	public void harvestCrops() {
		float revenue = 0;
		for (Crop crop : farm.showCrops()) {
			if (crop.getDaysLeft() <= 0) {
				revenue += crop.getPrice();
			}
		}
		if (farm instanceof TrumpRanch) {
			revenue = revenue * (float)1.1;
		}

		farm.updateBankBalance((int)revenue);
		farm.showCrops().removeIf(crop -> crop.getDaysLeft() == 0);
		farm.updateAP();
	}

	public void tendToLand() {
		for (Animal animal : farm.showAnimals()) {
			float happy = 20;
			if (farm instanceof AnimalFarm) {
				happy += happy * farm.getAnimalBonus();
			}
			animal.updateHappiness((int)happy);
		}
		farm.addCap(2);
		farm.updateAP();
	}
}
