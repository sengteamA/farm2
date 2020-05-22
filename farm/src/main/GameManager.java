package main;

import main.farms.*;

/**
 * Class that essentially runs the entire GUI game by handling the
 * creation and removal of GUI windows. Also stores all the player
 * data (farm, crops, animals, money etc.) required by the GUI
 * windows in question.
 *
 * This is the GUI version of GameCLI.java, which no longer exists
 * but used a command-line interface.
 *
 * @author Grant and Nick
 */
public class GameManager {
	/**
	 * An instance of the main farm GUI window, included here as it is
	 * passed around classes.
	 */
	public FarmGUI farmWindow;
	/**
	 * Farmer stores all the high-level actions that the player can run.
	 */
	public Farmer farmer;
	/**
	 * Farm stores all the player data, like what the player has on the farm.
	 */
	public Farm farm;
	/**
	 * Total duration of the game; some integer between 5 and 10 inclusive.
	 */
	private int maxDays = 0;
	/**
	 * The day number of the game. Game ends when this exceeds total duration.
	 * (Enforced by FarmGUI)
	 */
	private int dayNumber = 1;

	/**
	 * Return the total duration of the game, in days.
	 *
	 * @return total duration in days
	 */
	public int getMaxDays() {
		return maxDays;
	}

	/**
	 * Set the total duration of the game, in days.
	 *
	 * Only run once: in SetupGUI, when the player is prompted to enter
	 * the number of days they would like to play for.
	 *
	 * @param newMaxDays new total duration of game
	 */
	public void setMaxDays(int newMaxDays) {
		maxDays = newMaxDays;
	}

	/**
	 * Retrieves the number of days that have passed so far in the game.
	 *
	 * @return dayNumber
	 */
	public int getDayNumber() {
		return dayNumber;
	}

	/**
	 * Increases day number by one.
	 *
	 * Checking if the day number exceeds the game duration and ending the
	 * game is handled by FarmGUI.
	 *
	 */
	public void incrementDayNumber() {
		++dayNumber;
	}

	/**
	 * Launches the setup window.
	 */
	public void launchSetupScreen() {
		@SuppressWarnings("unused")
		SetupGUI setupWindow = new SetupGUI(this);
	}

	/**
	 * Launches the farm interaction window.
	 */
	public void launchFarmScreen() {
		farmWindow = new FarmGUI(this);
	}

	/**
	 * Launches the store window.
	 */
	public void launchStore() {
		@SuppressWarnings("unused")
		StoreGUI storeWindow = new StoreGUI(this);
	}

	/**
	 * Launches the window for feeding animals.
	 */
	public void launchFeedingScreen() {
		@SuppressWarnings("unused")
		FeedingGUI feeder = new FeedingGUI(this);
	}

	/**
	 * Launches the window for tending to crops.
	 */
	public void launchCropsScreen() {
		@SuppressWarnings("unused")
		CropCareGUI planter = new CropCareGUI(this);
	}

	/**
	 * Closes the setup screen and launches the Farm GUI window.
	 * @param setupScreen the setup window to close
	 */
	public void closeSetupScreen(SetupGUI setupScreen) {
		setupScreen.closeWindow();
		launchFarmScreen();
	}

	/**
	 * Closes the main farm window and doesn't do anything after that.
	 */
	public void closeFarmScreen() {
		farmWindow.closeWindow();
	}

	/**
	 * Closes the store window and updates the "Actions Left" label in the
	 * main farm window in case any changes to the farm have been made (i.e.
	 * player purchases).
	 *
	 * @param storeScreen the store window to close
	 */
	public void closeStoreScreen(StoreGUI storeScreen) {
		storeScreen.closeWindow();
		farmWindow.updateWindowData();
	}

	/**
	 * Closes the Feed Animals screen, and updates the main farm window for
	 * any changes made in the meantime.
	 *
	 * @param feeder the window to close
	 */
	public void closeFeedingScreen(FeedingGUI feeder) {
		feeder.closeWindow();
		farmWindow.updateWindowData();
	}

	/**
	 * Closes the Tend to Crops screen, and updates the main farm window for
	 * any changes made in the meantime.
	 *
	 * @param planter the window to close
	 */
	public void closeCropScreen(CropCareGUI planter) {
		planter.closeWindow();
		farmWindow.updateWindowData();
	}

	/**
	 * Runs the entire GUI farm simulator game, from start to finish.
	 * @param args ignored by the program
	 */
	public static void main(String[] args) {
		GameManager game = new GameManager();
		game.launchSetupScreen();
	}
}