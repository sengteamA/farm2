package main;

import java.util.Scanner;

import org.junit.jupiter.api.extension.ExtensionContext.Store;

/**
 * The class that runs the game loop, and handles every object in the game.
 * @author Grant
 *
 */
public class Game {
	private Farmer farmer;
	private Farm farm;
	private int money; // TODO move this somewhere else
	private Store store;
	private int dayNumber = 0;
	private int maxDays = 0;
	private int score = 0; // TODO figure out how to calculate score
	private int actionsLeft = 2;

	private void setGameDuration(Scanner sc) {
		System.out.print("For how many days (from 5 to 10) would you like to play? ");
		while (true) {
			if (sc.hasNextInt()) {
				maxDays = sc.nextInt();
				if (5 <= maxDays && maxDays <= 10) {
					System.out.printf("Number of days set to %d\n", maxDays);
					break;
				} else {
					System.out.printf("%d is not between 5 and 10 inclusive!", maxDays);
					continue;
				}
			} else {
				System.out.printf("Please type a valid number");
			}
		}
	}

	/**
	 * Initializes farmer attribute, prompts the user for and sets the name of
	 * the farmer.
	 */
	private void setFarmerDetails(Scanner sc) {
		farmer = new Farmer();
		farmer.setDetails(sc);
	}

	/**
	 * Prompts the user about what farm to create, and assigns Game.farm to
	 * this value.
	 */
	private void selectFarm(Scanner sc) {
		System.out.println("What farm would you like to create?");
		farmSelectLoop: while (true) {
			// TODO: Print flavour text as well here?
			System.out.println("Input 1 for Animal Farm.");
			System.out.println("Input 2 for Trump Ranch.");
			System.out.println("Input 3 for Tomacco Land.");
			System.out.println("Input 4 for Moo Moo Farm.");
			if (!sc.hasNextInt()) {
				System.out.println("Please type a positive number.");
				continue;
			}
			switch (sc.nextInt()) {
			case 1:
				farm = new AnimalFarm();
				break farmSelectLoop; // breaks out of the while loop
			case 2:
				farm = new TrumpRanch();
				break farmSelectLoop;
			case 3:
				farm = new TomaccoLand();
				break farmSelectLoop;
			case 4:
				farm = new MoomooFarm();
				break farmSelectLoop;
			default:
				System.out.println("Number must be from 1 to 4.");
			}
		}
		// we include this because nextInt only "consumed" the number
		// and didn't consume the new line afterwards
		sc.nextLine();
		// printing the type of farm is optional
		System.out.printf("Farm %s successfully created!\n", farm.getType());
	}

	/**
	 * Starts and sets up game, and prompts required information from user.
	 */
	private void startGame(Scanner sc) {
		System.out.println("Welcome to the game.");
		setGameDuration(sc);
		setFarmerDetails(sc);
		selectFarm(sc);
		farm.setName(sc);
	}

	/**
	 * Prompt user for a game loop option that doesn't cost any actions.
	 */
	private void promptNonAction(Scanner sc) {
		System.out.println("1. View status of farm's crops and animals.");
		System.out.println("2. View status of farm, and money available.");
		System.out.println("3. View general store.");
		System.out.println("4. Go back.");
		optionLoop: while (true) {
			switch (sc.nextInt()) {
			case 1:
				break optionLoop;
			case 2:
				break optionLoop;
			case 3:
				break optionLoop;
			case 4:
				return;
			default:
				System.out.println("Please type a valid number.");
			}
		}
	}

	private void promptAction(Scanner sc) {
		System.out.printf("You have %d actions left.\n", farm.getActionsLeft());
		System.out.println("1. Tend to crops.");
		System.out.println("2. Feed animals.");
		System.out.println("3. Play with animals.");
		System.out.println("4. Harvest crops.");
		System.out.println("5. Tend to farm land.");
		System.out.println("6. Go back.");
		optionLoop: while (true) {
			switch (sc.nextInt()) {
			case 1:
				break optionLoop;
			case 2:
				break optionLoop;
			case 3:
				break optionLoop;
			case 4:
				break optionLoop;
			case 5:
				break optionLoop;
			case 6:
				sc.close();
				return;
			default:
				System.out.println("Please type a valid number.");
			}
		}
	}

	/**
	 * Prints status of farm (including amount of money), and
	 * prompts if the user wants to see status of crops, animals,
	 * and items.
	 */
	private void viewStatus() {

	}

	/**
	 * Main game loop, loops over and over and prompts user for the
	 * next action.
	 */
	private void mainGameLoop(Scanner sc) {
		outerLoop: while (dayNumber <= maxDays) {
			++dayNumber;
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
		System.out.println("Game duration: " + dayNumber);
		System.out.println("Profit: " + money);
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
