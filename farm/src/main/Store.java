package main;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import main.animal.Animal;
import main.animal.Cow;
import main.animal.Fox;
import main.animal.Sheep;
import main.crops.Carrot;
import main.crops.Crop;
import main.crops.Hipotke;
import main.crops.Mushroom;
import main.crops.Tomacco;
import main.crops.Wasabi;
import main.crops.Wheat;
import main.items.ChemicalSpray;
import main.items.Compost;
import main.items.InstantGroLite;
import main.items.InstantGroPro;
import main.items.Item;
import main.items.PandaGummy;
import main.items.Stockfeed;

public class Store {
	private TreeMap<String,Asset> animals;
	private TreeMap<String,Asset> crops;
	private TreeMap<String,Asset> items;

	private void addAnimal(String name, Animal animal) {
		animals.put(name, animal);
	}

	private void addCrop(String name, Crop crop) {
		crops.put(name, crop);
	}

	private void addItem(String name, Item item) {
		items.put(name, item);
	}

	private void initialiseAnimals() {
		Cow cow = new Cow();
		Fox fox = new Fox();
		Sheep sheep = new Sheep();
		addAnimal(cow.getName(), cow);
		addAnimal(fox.getName(), fox);
		addAnimal(sheep.getName(), sheep);
	}

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
	 * Displays assets the player has.
	 */
	public void displayFarmerAssets() {

	}

	public void displayAisleAssets(TreeMap<String,Asset> aisle) {
		for (Map.Entry<String,Asset> entry : aisle.entrySet()) {
			// TODO: change getInfo() of Animal, Crop and Item
			// to be, well, more expressive
			System.out.printf("%s: %s\n", entry.getKey(), entry.getValue().getInfo());
		}
	}

	private void purchaseItem(TreeMap<String,Asset> aisle, String assetName) {
		// TODO:
		// check if goes under user's balance, then
		// deduct price from user's balance
		// create new item with entry.getValue().getClass().newInstance()
		//  \------> check if methods of Crop, Animal, Item will be preserved
		// https://stackoverflow.com/questions/29333960/create-new-instance-from-existing-instance
		// then put in appropriate ArrayList in farm???
	}

	private void visitAisle(TreeMap<String,Asset> aisle, String aisleName, Scanner sc) {
		outerLoop: while (true) {
			displayItems(aisle);
			System.out.printf("Select %s to buy:\n", aisleName);
			String inputName = sc.nextLine();

			// TODO: if user buys something, call purchaseItem()

			if (inputName.equals("exit")) {
				System.out.println("Exiting aisle...");
				break outerLoop;
			}
		}
	}

	Store() {
		initialiseAnimals();
		initialiseCrops();
		initialiseItems();
	}

	/**
	 * Runs game loop for interacting with store.
	 */
	public void visitStore(Farm farm, Scanner sc) {
		outerLoop: while (true) {
			System.out.println("Welcome to the county general store.");
			System.out.printf("You have %d money.\n", farm.getBankBalance());
			// TODO
			// Show what items the player currently owns, their amounts, and theamount of money the player has.
			System.out.println("Type 1 to buy crops.");
			System.out.println("Type 2 to buy animals");
			System.out.println("Type 3 to buy items/farming supplies.");
			System.out.println("Type 4 to go back");
			optionLoop: while (true) {
				try {
					switch (sc.nextInt()) {
					case 1:
						System.out.println("Entering the crop aisle...");
						visitAisle(crops, "crop", sc);
						break optionLoop;
					case 2:
						System.out.println("Entering the animal section...");
						visitAisle(animals, "animal", sc);
						break optionLoop;
					case 3:
						System.out.println("Entering the item aisle...");
						visitAisle(items, "item", sc);
						break optionLoop;
					case 4:
						System.out.println("Returning...");
						break outerLoop;
					}
				} catch (InputMismatchException e) {
					System.out.println("Please type a number.");
					sc.next();
				}
			}
		}
		sc.nextLine();
	}
}
