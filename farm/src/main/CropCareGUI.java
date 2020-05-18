package main;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class CropCareGUI {

	private JFrame frame;
	private GameManager manager;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public CropCareGUI(GameManager myManager) {
		manager = myManager;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
