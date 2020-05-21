package main;

import main.farms.*;

/**
 * Class that essentially runs the entire GUI game by handling the
 * creation and removal of GUI windows. Also stores all the player
 * data (farm, crops, animals, money etc.) required by the GUI
 * windows in question.
 *
 * This is the GUI version of GameCLI.java, which uses a command-line
 * interface.
 *
 * @author Grant and Nick
 */
public class GameManager {
	public FarmGUI farmWindow;
	/**
	 * Farmer stores all the high-level actions that the player can run.
	 * Farm stores all the player data, like what the player has on the farm.
	 */
	public Farmer farmer;
	public Farm farm;
	public int dayNumber = 1;
	public int maxDays = 0;
	public int score = 0;

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
		farmWindow.updateActionLabel();
	}

	/**
	 * Closes the Feed Animals screen, and updates the main farm window for
	 * any changes made in the meantime.
	 *
	 * @param feeder the window to close
	 */
	public void closeFeedingScreen(FeedingGUI feeder) {
		feeder.closeWindow();
		farmWindow.updateActionLabel();
	}

	/**
	 * Closes the Tend to Crops screen, and updates the main farm window for
	 * any changes made in the meantime.
	 *
	 * @param planter the window to close
	 */
	public void closeCropScreen(CropCareGUI planter) {
		planter.closeWindow();
		farmWindow.updateActionLabel();
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