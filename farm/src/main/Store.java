package main;

import java.lang.reflect.InvocationTargetException;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import main.animals.*;
import main.crops.*;
import main.farms.Farm;
import main.items.*;

/**
 * Handles the purchase of animals, crops and items to a Farm (which
 * is provided as an argument for Store's methods).
 *
 * Also stores all the animals, crops and items available for purchase.
 * @author Grant
 *
 */
public class Store {
	private TreeMap<String,Animal> animals;
	private TreeMap<String,Crop> crops;
	private TreeMap<String,Item> items;

	public Store() {
		animals = new TreeMap<String,Animal>();
		crops = new TreeMap<String,Crop>();
		items = new TreeMap<String,Item>();
		initialiseAnimals();
		initialiseCrops();
		initialiseItems();
	}

	private void addAnimal(String name, Animal animal) {
		animals.put(name, animal);
	}

	private void addCrop(String name, Crop crop) {
		crops.put(name, crop);
	}

	private void addItem(String name, Item item) {
		items.put(name, item);
	}

	/**
	 * Adds all the animals to the animals map, which stores all the animals
	 * available for purchase in the store.
	 */
	private void initialiseAnimals() {
		Cow cow = new Cow();
		Fox fox = new Fox();
		Sheep sheep = new Sheep();
		addAnimal(cow.getName(), cow);
		addAnimal(fox.getName(), fox);
		addAnimal(sheep.getName(), sheep);
	}

	/**
	 * Adds all the crops to the crops map, which stores all the crops
	 * available for purchase in the store.
	 */
	private void initialiseCrops() {
		Carrot carrot = new Carrot();
		Hipotke hipotke = new Hipotke();
		Mushroom mushroom = new Mushroom();
		Tomacco tomacco = new Tomacco();
		Wasabi wasabi = new Wasabi();
		Wheat wheat = new Wheat();
		addCrop(carrot.getName(), carrot);
		addCrop(hipotke.getName(), hipotke);
		addCrop(mushroom.getName(), mushroom);
		addCrop(tomacco.getName(), tomacco);
		addCrop(wasabi.getName(), wasabi);
		addCrop(wheat.getName(), wheat);
	}

	/**
	 * Adds all the items to the items map, which stores all the items
	 * available for purchase in the store.
	 */
	private void initialiseItems() {
		ChemicalSpray chemicalSpray = new ChemicalSpray();
		Compost compost = new Compost();
		InstantGroLite instantGroLite = new InstantGroLite();
		InstantGroPro instantGroPro = new InstantGroPro();
		PandaGummy pandaGummy = new PandaGummy();
		Stockfeed stockfeed = new Stockfeed();
		addItem(chemicalSpray.getName(), chemicalSpray);
		addItem(compost.getName(), compost);
		addItem(instantGroLite.getName(), instantGroLite);
		addItem(instantGroPro.getName(), instantGroPro);
		addItem(pandaGummy.getName(), pandaGummy);
		addItem(stockfeed.getName(), stockfeed);
	}

	/**
	 * Prints assets the player has to the system console, in a very very
	 * rudimentary fashion.
	 */
	public void printFarmerAssets(Farm farm) {
		System.out.println("Your animals:");
		for (Animal animal : farm.showAnimals()) {
			System.out.println(animal.getName());
		}
		System.out.println("Your crops:");
		for (Crop crop : farm.showCrops()) {
			System.out.println(crop.getName());
		}
		System.out.println("Your items:");
		for (Item item : farm.showItems()) {
			System.out.println(item.getName());
		}
	}

	/**
	 * Display all the items in an aisle, i.e. a collection of Asset objects.
	 * @param aisle A tree map of animals, crops or items -- the assets in the "aisle".
	 */
	public void printAisleAssets(TreeMap<String,? extends Asset> aisle) {
		// https://stackoverflow.com/a/2723538
		// for what ? extends Asset means
		for (Map.Entry<String,? extends Asset> entry : aisle.entrySet()) {
			// TODO: change getInfo() of Animal, Crop and Item
			// to be, well, more expressive
			System.out.printf("%s: %s\n", entry.getKey(), entry.getValue().getInfo());
		}
	}


	/**
	 * Purchases an animal: checks if player has enough money, then deducts money and adds animal to farm.
	 * @param farm - The farm to apply the purchase to.
	 * @param animalName - the name of the animal being purchased.
	 * @return true if animal purchase was successful, false if it wasn't.
	 */
	public boolean purchaseAnimal(Farm farm, String animalName) {
		Animal mapAnimal = animals.get(animalName);
		if (mapAnimal == null) {
			System.out.println(animalName + " is not the name of a valid animal.");
			return false;
		}
		try {
			Animal newAnimal = mapAnimal.getClass().getDeclaredConstructor().newInstance();
			if (!farm.hasEnoughMoney(newAnimal.getPurchasePrice())) {
				System.out.println("Not enough money!");
				return false;
			}
			farm.updateBankBalance(-newAnimal.getPurchasePrice());
			farm.addAnimal(newAnimal);
			System.out.printf("Purchased %s successfully.\n", newAnimal.getName());
			return true;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | SecurityException | NoSuchMethodException e) {
			// handle potential errors by just stopping the entire method
			System.out.println("Something went wrong with the program.");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Enters the animal-purchasing command line interface. Also displays
	 * the amount of money the user currently has.
	 * @param farm - the farm to perform the purchase on
	 * @param sc - where to get user input from (usually System.in)
	 */
	private void visitAnimalAisle(Farm farm, Scanner sc) {
		outerLoop: while (true) {
			printAisleAssets(animals);
			System.out.printf("Your money: %d\n", farm.getBankBalance());
			System.out.printf("Type the animal you want to buy, or type 'exit':\n");
			String inputName = sc.nextLine();
			if (inputName.equals("exit")) {
				System.out.println("Exiting aisle...");
				break outerLoop;
			} else {
				if (purchaseAnimal(farm, inputName)) {
					System.out.println("Purchase successful!");
				} else {
					System.out.println("Purchase was not successful...");
				}
			}
		}
	}

	/**
	 * Purchases a crop: checks if player has enough money, then deducts money and adds crop to farm.
	 * @param farm - The farm to apply the purchase to.
	 * @param cropName - the name of the crop being purchased.
	 * @return true if crop purchase was successful, false if it wasn't.
	 */
	public boolean purchaseCrop(Farm farm, String cropName) {
		Crop mapCrop = crops.get(cropName);
		if (mapCrop == null) {
			System.out.println(cropName + " is not the name of a valid crop.");
			return false;
		}
		try {
			Crop newCrop = mapCrop.getClass().getDeclaredConstructor().newInstance();
			if (!farm.hasEnoughMoney(newCrop.getPurchasePrice())) {
				System.out.println("Not enough money!");
				return false;
			}
			farm.updateBankBalance(-newCrop.getPurchasePrice());
			farm.addCrop(newCrop);
			System.out.printf("Purchased %s successfully.\n", newCrop.getName());
			return true;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | SecurityException | NoSuchMethodException e) {
			// handle potential errors by just stopping the entire method
			System.out.println("Something went wrong with the program.");
			e.printStackTrace();
			return false;
		}
	}

	private void visitCropAisle(Farm farm, Scanner sc) {
		outerLoop: while (true) {
			printAisleAssets(crops);
			System.out.printf("Your money: %d\n", farm.getBankBalance());
			System.out.printf("Type the crop you want to buy, or type 'exit':\n");
			String inputName = sc.nextLine();
			if (inputName.equals("exit")) {
				System.out.println("Exiting aisle...");
				break outerLoop;
			} else {
				if (purchaseCrop(farm, inputName)) {
					System.out.println("Purchase successful!");
				} else {
					System.out.println("Purchase was not successful...");
				}
			}
		}
	}

	public boolean purchaseItem(Farm farm, String itemName) {
		Item mapItem = items.get(itemName);
		if (mapItem == null) {
			System.out.println(itemName + " is not the name of a valid item.");
			return false;
		}
		try {
			Item newItem = mapItem.getClass().getDeclaredConstructor().newInstance();
			if (!farm.hasEnoughMoney(newItem.getPurchasePrice())) {
				System.out.println("Not enough money!");
				return false;
			}
			farm.updateBankBalance(-newItem.getPurchasePrice());
			farm.addItem(newItem);
			System.out.printf("Purchased %s successfully.\n", newItem.getName());
			return true;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | SecurityException | NoSuchMethodException e) {
			// handle potential errors by just stopping the entire method
			System.out.println("Something went wrong with the program.");
			e.printStackTrace();
			return false;
		}
	}

	private void visitItemAisle(Farm farm, Scanner sc) {
		outerLoop: while (true) {
			printAisleAssets(items);
			System.out.printf("Your money: %d\n", farm.getBankBalance());
			System.out.printf("Type the item you want to buy, or type 'exit':\n");
			String inputName = sc.nextLine();
			if (inputName.equals("exit")) {
				System.out.println("Exiting aisle...");
				break outerLoop;
			} else {
				if (purchaseItem(farm, inputName)) {
					System.out.println("Purchase successful!");
				} else {
					System.out.println("Purchase was not successful...");
				}
			}
		}
	}

	/**
	 * Runs game loop for interacting with store.
	 */
	public void visitStore(Farm farm, Scanner sc) {
		outerLoop: while (true) {
			System.out.println("Welcome to the county general store.");
			System.out.printf("You have %d money.\n", farm.getBankBalance());
			System.out.println("Type 1 to display what you already own.");
			System.out.println("Type 2 to buy crops.");
			System.out.println("Type 3 to buy animals");
			System.out.println("Type 4 to buy items/farming supplies.");
			System.out.println("Type 5 to go back");
			optionLoop: while (true) {
				try {
					switch (sc.nextInt()) {
					case 1:
						System.out.println("Displaying what you currently own...");
						sc.nextLine();
						printFarmerAssets(farm);
						break optionLoop;
					case 2:
						System.out.println("Entering the crop aisle...");
						sc.nextLine();
						visitCropAisle(farm, sc);
						break optionLoop;
					case 3:
						System.out.println("Entering the animal section...");
						sc.nextLine();
						visitAnimalAisle(farm, sc);
						break optionLoop;
					case 4:
						System.out.println("Entering the item aisle...");
						sc.nextLine();
						visitItemAisle(farm, sc);
						break optionLoop;
					case 5:
						System.out.println("Returning...");
						sc.nextLine();
						break outerLoop;
					}
				} catch (InputMismatchException e) {
					System.out.println("Please type a number.");
					sc.next();
				}
			}
		}
	}
}
