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
		SetupGUI setupWindow = new SetupGUI(this);
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
	 * @param setupScreen - the SetupGUI instance.
	 */
	public void closeSetupScreen(SetupGUI setupScreen) {
		setupScreen.closeWindow();
		launchFarmScreen();
	}

	public static void main(String[] args) {
		GameManager game = new GameManager();
		game.launchSetupScreen();
	}
}