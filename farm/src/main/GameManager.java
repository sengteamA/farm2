package main;

import main.farms.*;

/**
 * Game manager: the class that essentially runs the entire game by
 * handling the creation and removal of GUI windows. Also stores
 * all the player data (farm, crops, animals, money etc.) required
 * by the GUI windows in question.
 *
 * This is the GUI version of Game.java, which uses a command-line
 * interface.
 *
 * @author Grant and Nick
 *
 */
public class GameManager {
	public FarmGUI farmWindow;
	public Farmer farmer;
	public Farm farm;
	public Store store;
	public int dayNumber = 1;
	public int maxDays = 0;
	public int score = 0;

	public void launchSetupScreen() {
		SetupGUI setupWindow = new SetupGUI(this);
	}

	public void launchFarmScreen() {
		farmWindow = new FarmGUI(this);
	}

	public void launchStore() {
		StoreGUI storeWindow = new StoreGUI(this);
	}

	//window for feeding animals
	public void launchFeedingScreen() {
		FeedingGUI feeder = new FeedingGUI(this);
	}

	//window for tending to crops
	public void launchCropsScreen() {
		CropCareGUI planter = new CropCareGUI(this);
	}

	/**
	 * Closes the setup screen and launches the Farm GUI window.
	 * @param setupScreen - the SetupGUI instance.
	 */
	public void closeSetupScreen(SetupGUI setupScreen) {
		setupScreen.closeWindow();
		launchFarmScreen();
	}

	public void closeFarmScreen() {
		farmWindow.closeWindow();
		// TODO: create a window to output player score?
	}

	public void closeStoreScreen(StoreGUI storeScreen) {
		storeScreen.closeWindow();
		farmWindow.updateActionLabel();
	}

	public void closeFeedingScreen(FeedingGUI feeder) {
		feeder.closeWindow();
		farmWindow.updateActionLabel();
	}

	public void closeCropScreen(CropCareGUI planter) {
		planter.closeWindow();
		farmWindow.updateActionLabel();
	}

	public static void main(String[] args) {
		GameManager game = new GameManager();
		game.launchSetupScreen();
	}
}