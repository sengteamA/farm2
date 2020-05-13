package main;

import java.util.InputMismatchException;
import java.util.Scanner;

import main.farms.Farm;

/**
 * Handles daily actions made to the farm by the player.
 * Also stores name and age of player.
 * @author Nick
 *
 */
public class Farmer {
	private String name;
	private int age;
	private Farm farm;

	/**
	 * Initialises Farmer using an existing farm.
	 * @param my_farm
	 */
	Farmer(Farm my_farm) {
		farm = my_farm;
	}

	// TODO: add some errors if not initialised yet?
	// or force user to initialise name and age in the constructor?
	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
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

	// TODO: add some stuff to verify farmer name,
	// using the Farmer.isValidName() method
	/**
	 * Prompt user for their name and age. Used by the command-line interface.
	 */
	public void setDetails(Scanner sc) {
		System.out.print("Type your own name here: ");
		name = sc.nextLine();
		System.out.println("Hello " + name + "!");
		System.out.print("Type your age: ");
		while (true) {
			try {
				age = sc.nextInt();
				sc.nextLine();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Please type a valid number.");
				sc.next();
			}
		}
		System.out.printf("Got it. You are %d years old.\n", age);
	}
	
	public void feedAnimals(Item item) {
		float health = 0;
		if (item.getName().equals("Stockfeed")) {
			health = 50;
			}
		else if (item.getName().contentEquals("Panda Gummy")) {
			health = 100;
		}
		if (farm instanceof AnimalFarm) {
			health += health * ((AnimalFarm) farm).animal_bonus; 
		}
		for (Animal animal: farm.showAnimals()) {
			animal.updateHealth((int)health);
		}
		farm.delItem(item);
		farm.updateAP();
	}
	
	public void playWithAnimals() {
		for (Animal animal: farm.showAnimals()) {
			float happy = 30;
			if (farm instanceof AnimalFarm) {
				happy += happy * ((AnimalFarm) farm).animal_bonus;
			}
			animal.updateHappiness((int)happy);
		}
		farm.updateAP();
	}
	
	public void harvestCrops() {
		float revenue = 0;
		for (Crop crop: farm.showCrops()) {
			if (crop.getDaysLeft() <= 0) {
				revenue += crop.getPrice();
			}
		}
		if (farm instanceof TrumpRanch) {
			revenue = revenue * ((TrumpRanch) farm).bonus;
			((TrumpRanch) farm).updateBankbalance((int)revenue);
		}
		farm.updateBankBalance((int)revenue);
		farm.showCrops().removeIf(crop -> crop.getDaysLeft() == 0);
		farm.updateAP();
	}
	
	public void tendToLand() {
		for (Animal animal: farm.showAnimals()) {
			float happy = 20;
			if (farm instanceof AnimalFarm) {
				happy += happy * ((AnimalFarm) farm).animal_bonus;
			}
			animal.updateHappiness((int)happy);
		}
		farm.addCap(2);
		farm.updateAP();
	}
	
	public static void main(String[] args) {
		PandaGummy panda = new PandaGummy();
		Cow moomoo = new Cow();
	}
}
