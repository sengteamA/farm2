package main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import main.animals.Animal;
import main.crops.Crop;
import main.farms.*;
import main.items.Item;

/**
 * The class that runs the game loop, and handles every object in the game.
 * @author Grant
 *
 */
public class Game {
	private Farmer farmer;
	private Farm farm;
	private Store store;
	private int dayNumber = 1;
	private int maxDays = 0;
	private int score = 0; // TODO figure out how to calculate score
	private int actionsLeft = 2; // TODO is this even used?

	private void setGameDuration(Scanner sc) {
		System.out.print("For how many days (from 5 to 10) would you like to play? ");
		while (true) {
			try {
				maxDays = sc.nextInt();
				sc.nextLine(); // discard rest of line
				if (5 <= maxDays && maxDays <= 10) {
					System.out.printf("Number of days set to %d\n", maxDays);
					break;
				} else {
					System.out.printf("%d is not between 5 and 10 inclusive!", maxDays);
					continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("Please type a valid number.");
				sc.next(); // discard the input
			}
		}
	}

	/**
	 * Initializes farmer attribute, prompts the user for and sets the name of
	 * the farmer.
	This method creats another instance of farm, which prevents attribute of objects from being updated correctly. Removed - Nick
	private void setFarmerDetails(Scanner sc) {
		Farm farm = new Farm("type", "text");
		farmer = new Farmer(farm);
		farmer.setDetails(sc);
	}
	 */

	/**
	 * Creates a new Store instance for the game.
	 */
	private void createStore() {
		store = new Store();
	}

	/**
	 * Prompts the user about what farm to create, and assigns Game.farm to
	 * this value.
	 */
	private void selectFarm(Scanner sc) {
		System.out.println("What farm would you like to create?");
		farmSelectLoop: while (true) {
			System.out.println("Input 1 for Animal Farm.");
			System.out.println("Input 2 for Trump Ranch.");
			System.out.println("Input 3 for Tomacco Land.");
			System.out.println("Input 4 for Moo Moo Farm.");
			try {
				switch (sc.nextInt()) {
				case 1:
					farm = new AnimalFarm();
					farmer = new Farmer(farm);
					break farmSelectLoop; // breaks out of the while loop
				case 2:
					farm = new TrumpRanch();
					farmer = new Farmer(farm);
					break farmSelectLoop;
				case 3:
					farm = new TomaccoLand();
					farmer = new Farmer(farm);
					break farmSelectLoop;
				case 4:
					farm = new MoomooFarm();
					farmer = new Farmer(farm);
					break farmSelectLoop;
				default:
					System.out.println("Number must be from 1 to 4.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Please type a number from 1 to 4.");
				sc.next();
			}
		}
		// we include this because nextInt only "consumed" the number
		// and didn't consume the new line afterwards
		sc.nextLine();
		// printing the type of farm is optional
		System.out.printf("Farm %s successfully created!\n", farm.getType());
		System.out.printf("Farm info: %s\n", farm.getFlavour());
	}

	/**
	 * Starts and sets up game, and prompts required information from user.
	 */
	private void startGame(Scanner sc) {
		System.out.println("Welcome to the game.");
		setGameDuration(sc);
		selectFarm(sc);
		farm.setName(sc);
		farmer.setDetails(sc);
		// set up store, now that we have a farm
		// (this could go anywhere to be honest)
		createStore();
	}

	private void viewCrops() {
		ArrayList<Crop> crops = farm.showCrops();
		for (Crop crop : crops) {
			// it ain't pretty, but it's a proof of concept
			System.out.printf("%s\n", crop);
		}
	}

	private void viewAnimals() {
		ArrayList<Animal> animals = farm.showAnimals();
		for (Animal animal : animals) {
			System.out.printf("%s\n", animal);
		}
	}

	private void viewItems() {
		ArrayList<Item> items = farm.showItems();
		for (Item item : items) {
			System.out.printf("%s\n", item);
		}
	}

	/**
	 * Prints status of farm (including amount of money), and
	 * prompts if the user wants to see status of crops, animals,
	 * and items.
	 */
	private void viewStatus(Scanner sc) {
		System.out.printf("%s, owned by %s (%d y.o.)\n", farm.getName(), farmer.getName(), farmer.getAge());
		System.out.printf("Money: %d\n", farm.getBankBalance());
		System.out.println("===");
		outerLoop: while (true) {
			System.out.println("Type 1 to view crops");
			System.out.println("Type 2 to view animals");
			System.out.println("Type 3 to view items");
			System.out.println("Type 4 to go back");
			optionLoop: while (true) {
				try {
					switch (sc.nextInt()) {
					case 1:
						System.out.println("Viewing crops...");
						viewCrops();
						sc.nextLine();
						break optionLoop;
					case 2:
						System.out.println("Viewing animals...");
						viewAnimals();
						sc.nextLine();
						break optionLoop;
					case 3:
						System.out.println("Viewing items...");
						viewItems();
						sc.nextLine();
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
	}

	/**
	 * Prompt user for a game loop option that doesn't cost any actions.
	 */
	private void promptNonAction(Scanner sc) {
		System.out.println("1. View status of farm OR farm's crops/animals/items.");
		System.out.println("2. View general store.");
		System.out.println("3. Go back.");
		optionLoop: while (true) {
			try {
				switch (sc.nextInt()) {
				case 1:
					System.out.println("Viewing status...");
					viewStatus(sc);
					break optionLoop;
				case 2:
					store.visitStore(farm, sc);
					break optionLoop;
				case 3:
					break optionLoop;
				default:
					System.out.println("Please type a valid number.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Please type a valid number.");
				sc.next();
			}
		}
		sc.nextLine();
	}

	private void promptAction(Scanner sc) {
		// TODO: test this
		System.out.printf("You have %d actions left.\n", farm.getActionsLeft());
		System.out.println("1. Tend to crops.");
		System.out.println("2. Feed animals.");
		System.out.println("3. Play with animals.");
		System.out.println("4. Harvest crops.");
		System.out.println("5. Tend to farm land.");
		System.out.println("6. Go back.");
		optionLoop: while (true) {
			try {
				switch (sc.nextInt()) {
				case 1:
					System.out.println("Tending to the crops...");
					// Farmer.tendToCrops()
					break optionLoop;
				case 2:
					System.out.println("Feeding the animals...");
					// Farmer.feedAnimals()
					break optionLoop;
				case 3:
					System.out.println("Playing with the animals...");
					// farmer.playWithAnimals()
					break optionLoop;
				case 4:
					System.out.println("Harvesting the crops...");
					// farmer.harvestCrops()
					break optionLoop;
				case 5:
					System.out.println("Tending to the farm land...");
					// farmer.tendToLand()
					break optionLoop;
				case 6:
					System.out.println("Returning...");
					break optionLoop;
				default:
					System.out.println("Please type a valid number.");
					sc.next();
				}
			} catch (InputMismatchException e) {
				System.out.println("Please type a valid number.");
				sc.next();
			}
		}
		sc.nextLine();
	}

	/**
	 * Main game loop, loops over and over and prompts user for the
	 * next action.
	 */
	private void mainGameLoop(Scanner sc) {
		outerLoop: while (dayNumber <= maxDays) {
			System.out.printf("Day number: %d\n", dayNumber);
			System.out.println("Please type a number from 1-9 below.");
			System.out.println("1. View status or visit store.");
			System.out.printf("2. Take an action (%d left).\n", farm.getActionsLeft());
			System.out.println("3. Move on to next day.");
			optionLoop: while (true) {
				switch (sc.nextInt()) {
				case 1:
					promptNonAction(sc);
					break optionLoop;
				case 2:
					promptAction(sc);
					break optionLoop;
				case 3:
					//my apologies, get dailybonus() actually calculates the bonus but doesn't add it to bank account! - Nick
					System.out.println("Receiving bonus money...");
					int income = farm.getDailyBonusMoney();
					farm.updateBankBalance(income);
					++dayNumber;
					continue outerLoop; // should skip to next day
				default:
					System.out.println("Please type a valid number.");
				}
			}
		}
	}

	/**
	 * Wraps up the game by printing game results and credits.
	 */
	private void finishGame() {
		System.out.println("==========");
		System.out.println("You have completed the farm simulator!");
		System.out.println("Thank you for playing.");
		System.out.println("==========");
		System.out.println("Farm name: " + farm.getName());
		System.out.println("Game duration: " + (dayNumber-1));
		System.out.println("Profit: " + farm.getBankBalance());
		System.out.println("Final score:" + score);
	}

	/**
	 * Runs the game from start to finish.
	 */
	public void runGame(Scanner sc) {
		// good practice to have only one Scanner open at a time
		// and to use the same Scanner throughout the program
		// (because when one Scanner is closed, System.in is closed too)
		startGame(sc);
		mainGameLoop(sc);
		finishGame();
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Game game = new Game();
		game.runGame(sc);
		sc.close(); // wrap up the Scanner, no longer accept input
	}
}
