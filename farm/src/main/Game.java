package main;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.extension.ExtensionContext.Store;

/**
 * The class that runs the game loop, and handles every object in the game.
 * @author Grant
 *
 */
public class Game {
	private farmer Farmer;
	private Farm farm;
	private int money; // TODO move this somewhere else
	private Store store;
	private ArrayList<RandomEvent> randomEvents;
	private int dayNumber = 0;
	private int score; // TODO figure out how to calculate score

	/**
	 * Initializes farmer attribute, prompts the user for and sets the name of
	 * the farmer.
	 */
	private void setFarmerName() {
		farmer = new Farmer();
		farmer.setName();
	}

	/**
	 * Prompts the user about what farm to create, and assigns Game.farm to
	 * this value.
	 */
	private void selectFarm() {
		Scanner sc = new Scanner(System.in);
		System.out.println("What farm would you like to create?");
		farmSelectLoop: while (true) {
			// TODO: Print flavour text as well here?
			System.out.println("Input '1' for Animal Farm.");
			System.out.println("Input '2' for Trump Ranch.");
			System.out.println("Input '3' for Tomacco Land.");
			System.out.println("Input '4' for Moo Moo Farm.");
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
				System.out.println("Please type an integer from 1 to 4.");
			}
		}
		sc.close();
		// printing the type of farm is optional
		System.out.printf("Farm %s successfully created!\n", farm.getType());
	}

	/**
	 * Starts and sets up game, and prompts required information from user.
	 */
	private void startGame() {
		System.out.println("Welcome to the game.");
		setFarmerName();
		selectFarm();
		farm.setName();
	}

	/**
	 * Main game loop, loops over and over and prompts user for the
	 * next action.
	 */
	private void mainGameLoop() {
		// TODO
	}

	/**
	 * Wraps up the game by printing game results and credits.
	 */
	private void finishGame() {
		System.out.println("You have completed the farm simulator!");
		System.out.println("Thank you for playing!");
		System.out.println("==========");
		System.out.println("Farm name: " + farm.getName());
		System.out.println("Game duration: " + dayNumber);
		System.out.println("Profit: " + money);
		System.out.println("Final score:" + score);
	}

	/**
	 * Runs the game from start to finish.
	 */
	public void runGame() {
		startGame();
		mainGameLoop();
		finishGame();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.runGame();
	}
}
