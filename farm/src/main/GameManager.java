package main;

import main.animals.Animal;
import main.crops.Crop;
import main.farms.*;
import main.items.Item;

/**
 * Game manager: the class that essentially runs the entire game by
 * handling the creation and removal of GUI windows. Also stores
 * all the player data (farm, crops, animals, money etc.) required
 * by the GUI windows in question.
 *
 * This is the GUI version of Game.java, which uses a command-line
 * interface.
 *
 * @author Grant
 *
 */
public class GameManager {
	public void launchSetupScreen() {
		// TODO: rename GameSetupWindow to SetupGUI or something sensible
		GameSetupWindow setupWindow = new GameSetupWindow(this);
	}

	public void launchFarmScreen() {
		FarmGUI farmWindow = new FarmGUI(this);
	}

	public void closeFarmScreen(FarmGUI farmWindow) {
		farmWindow.closeWindow();
		// TODO: create a window to output player score?
	}

	/**
	 * Closes the setup screen and launches the Farm GUI window.
	 * @param setupScreen - the GameSetupWindow instance.
	 */
	public void closeSetupScreen(GameSetupWindow setupScreen) {
		setupScreen.closeWindow();
		launchFarmScreen();
	}

	public static void main(String[] args) {
		GameManager game = new GameManager();
		game.launchSetupScreen();
	}
}