package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import main.farms.*;

import java.util.List;

/**
 * The window for setting up the player's farm, farmer and game duration.
 * Run at the start of the game.
 *
 * @author Grant
 */
public class SetupGUI {
	private GameManager manager;
	private JFrame window;

	// private TreeMap<String,Farm> farms = new TreeMap<String,Farm>();
	// note: List.of only works in Java 9 or above
	// if this doesn't work, replace with Arrays.asList
	private List<Farm> farms = List.of(
			new AnimalFarm(),
			new MoomooFarm(),
			new TomaccoLand(),
			new TrumpRanch()
	);

	/**
	 * Create the application.
	 * @param myManager game instance to use
	 */
	public SetupGUI(GameManager myManager) {
		manager = myManager;
		initialise();
		window.setVisible(true);
	}

	/**
	 * Closes this window (GameSetupWindow instance).
	 */
	public void closeWindow() {
		window.dispose();
	}

	/**
	 * Calls the parent game manager, which closes this window and creates
	 * the next one that's needed.
	 */
	public void finishedWindow() {
		manager.closeSetupScreen(this);
	}

	/**
	 * Initialises all the variables used by the game with their values in the
	 * setup screen.
	 *
	 * @param gameDuration duration of game in days (5-10 days inclusive)
	 * @param farmerName farmer name (validated by the validate method)
	 * @param farmerAge farmer age (no restrictions)
	 * @param farmName farm name (validated by the validate method)
	 * @param selectedFarm an instance of one of the four Farm child classes
	 */
	public void initialiseGame(int gameDuration, String farmerName,
			int farmerAge, String farmName, Farm selectedFarm) {
		manager.maxDays = gameDuration;
		try {
			// Create a new farm instance from the existing instance
			// https://stackoverflow.com/q/53257073
			manager.farm = selectedFarm.getClass()
					.getDeclaredConstructor().newInstance();
			manager.farm.setName(farmName);
			manager.farmer = new Farmer(manager.farm, farmerName, farmerAge);

			// finished initialising, hand things over to GameManager
			finishedWindow();
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| SecurityException | NoSuchMethodException e) {
			// handle potential errors by just giving up
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	/**
	 * Checks a text box to see if it follows the naming requirements of
	 * farmers and farms. If it does not, set the contents of errorBar to an
	 * appropriate error message for the user.
	 *
	 * @param textBox the text box to check the input of
	 * @param errorBar the JLabel component to set any errors in
	 * @param textBoxName the name of the text box, used in error messages
	 * @return whether the input in the text box is valid
	 */
	public boolean validate(JTextField textBox, JLabel errorBar,
			String textBoxName) {
		String name = textBox.getText();
		Color validBackground = Color.WHITE;
		Color errorBackground = Color.decode("#ffaaaa"); // a lighter red
		if (name.length() < 3 || name.length() > 15) {
			errorBar.setText(textBoxName +
					" name needs to be between 3 and 15 characters, " +
					"inclusive.");
			textBox.setBackground(errorBackground);
			return false;
		} else if (!name.matches("^[A-Za-z]+( [A-Za-z]+)*$")) {
			errorBar.setText(textBoxName + " name cannot contain numbers " +
					"or symbols, or have extra spaces.");
			textBox.setBackground(errorBackground);
			return false;
		} else {
			// valid input
			errorBar.setText("");
			textBox.setBackground(validBackground);
			return true;
		}
	}

	/**
	 * Initialise the contents of the window.
	 */
	private void initialise() {
		window = new JFrame();
		window.setTitle("Set up game");
		window.setBounds(100, 100, 475, 425);
		// Exit the entire program when the X button is pressed
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Dialog", Font.PLAIN, 12));

		JLabel lblGameDuration = new JLabel("Game duration:");

		JSpinner selectGameDuration = new JSpinner();
		selectGameDuration.setModel(new SpinnerNumberModel(8, 5, 10, 1));

		JLabel lblFarmerName = new JLabel(
				"Your name (3-15 chars, A-Z or spaces only):");

		JTextField txtFarmerName = new JTextField();
		txtFarmerName.setText("Farmer name");
		txtFarmerName.setColumns(10);

		txtFarmerName.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				validate(txtFarmerName, lblError, "Farmer");
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				validate(txtFarmerName, lblError, "Farmer");
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				validate(txtFarmerName, lblError, "Farmer");
			}
		});

		JLabel lblFarmerAge = new JLabel("Your age:");

		JSpinner selectFarmerAge = new JSpinner();
		selectFarmerAge.setModel(new SpinnerNumberModel(20, null, null, 1));

		JLabel lblSelectFarm = new JLabel("Select a farm:");

		JComboBox<String> selectFarm = new JComboBox<String>();
		for (Farm farm : farms) {
			selectFarm.addItem(farm.getType());
		}

		JLabel lblSelectedFarm = new JLabel("Something Farm.");
		lblSelectedFarm.setHorizontalAlignment(SwingConstants.CENTER);

		JTextArea lblFlavourText = new JTextArea("Flavour text.");
		lblFlavourText.setWrapStyleWord(true);
		lblFlavourText.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblFlavourText.setEditable(false);
		lblFlavourText.setBackground(lblSelectedFarm.getBackground());
		lblFlavourText.setBorder(null);

		selectFarm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = selectFarm.getSelectedIndex();
				Farm selectedFarm = farms.get(selectedIndex);
				lblSelectedFarm.setText(selectedFarm.getType());
				lblFlavourText.setText(selectedFarm.getFlavour());
			}
		});
		// selects first one automatically
		selectFarm.setSelectedIndex(0);

		JPanel panelFarms = new JPanel();
		panelFarms.setFont(new Font("Dialog", Font.PLAIN, 10));

		panelFarms.setBorder(new TitledBorder(
				new LineBorder(new Color(192, 192, 192)),
				"Farm information",
				TitledBorder.LEADING,
				TitledBorder.TOP,
				null,
				new Color(51, 51, 51)
		));
		panelFarms.setToolTipText("");

		JLabel lblSetFarmName = new JLabel(
				"Name your farm: (3-15 chars, A-Z or spaces only)");

		JTextField txtFarmName = new JTextField();
		txtFarmName.setText("My Farm");
		txtFarmName.setColumns(10);
		txtFarmName.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				validate(txtFarmName, lblError, "Farm");
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				validate(txtFarmName, lblError, "Farm");
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				validate(txtFarmName, lblError, "Farm");
			}
		});

		JPanel panelError = new JPanel();
		panelError.setBorder(new BevelBorder(
				BevelBorder.LOWERED, null, null, null, null)
		);

		panelError.add(lblError);

		JButton btnStart = new JButton("Start!");

		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// There are only two text boxes that need to be validated --
				// the others are designed such that the user cannot feasibly input
				// something invalid
				boolean farmerNameValidation =
						validate(txtFarmerName, lblError, "Farmer");
				boolean farmNameValidation =
						validate(txtFarmName, lblError, "Farm");
				if (!farmerNameValidation || !farmNameValidation) {
					JOptionPane.showMessageDialog(window,
							"Cannot create a new game because the name of " +
							"your farm and/or farmer is invalid.\n" +
							"Please fix them, then try again.");
					return;
				} else {
					int selectedIndex = selectFarm.getSelectedIndex();
					initialiseGame(
							(int)selectGameDuration.getValue(),
							txtFarmerName.getText(),
							(int)selectFarmerAge.getValue(),
							txtFarmName.getText(),
							farms.get(selectedIndex)
					);
				}
			}
		});

		GroupLayout groupLayout = new GroupLayout(window.getContentPane());

		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelError, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addComponent(panelFarms, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblGameDuration)
							.addPreferredGap(
									ComponentPlacement.RELATED,
									301,
									Short.MAX_VALUE)
							.addComponent(
									selectGameDuration,
									GroupLayout.PREFERRED_SIZE,
									GroupLayout.DEFAULT_SIZE,
									GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(lblFarmerName)
							.addPreferredGap(ComponentPlacement.RELATED, 284, Short.MAX_VALUE)
							.addComponent(
									txtFarmerName,
									GroupLayout.PREFERRED_SIZE,
									75,
									GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblFarmerAge)
								.addComponent(lblSelectFarm))
							.addPreferredGap(ComponentPlacement.RELATED, 311, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(
										selectFarm,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(selectFarmerAge,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSetFarmName)
							.addPreferredGap(
									ComponentPlacement.RELATED,
									247,
									Short.MAX_VALUE)
							.addComponent(
									txtFarmName,
									GroupLayout.PREFERRED_SIZE,
									GroupLayout.DEFAULT_SIZE,
									GroupLayout.PREFERRED_SIZE))
						.addComponent(btnStart))
					.addContainerGap())
		);

		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGameDuration)
						.addComponent(
								selectGameDuration,
								GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFarmerName)
						.addComponent(
								txtFarmerName,
								GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFarmerAge)
						.addComponent(
								selectFarmerAge,
								GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelectFarm)
						.addComponent(
								selectFarm,
								GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(
							panelFarms,
							GroupLayout.DEFAULT_SIZE,
							90,
							Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSetFarmName)
						.addComponent(
								txtFarmName,
								GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnStart)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(
							panelError,
							GroupLayout.PREFERRED_SIZE,
							20,
							GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);


		panelError.setLayout(new GridLayout(0, 1, 0, 0));

		panelFarms.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("250px"),
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("max( 20dlu;default)"),
				RowSpec.decode("default:grow"),
				RowSpec.decode("default:grow"),}));

		panelFarms.add(lblSelectedFarm, "2, 1, center, center");

		// Putting the flavour text box (JTextArea) into JScrollPane,
		// which in turn is in a GroupLayout, magically fixes my problems
		// with JTextArea not behaving like a CSS flexbox element would
		// (expanding to maximum width possible, then taking up only as much
		// height as necessary)
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		panelFarms.add(scrollPane, "1, 2, 3, 1, fill, fill");

		scrollPane.setViewportView(lblFlavourText);

		window.getContentPane().setLayout(groupLayout);

		// Validate the name text boxes before the user types anything,
		// so they know they need to fill it in.
		validate(txtFarmerName, lblError, "Farmer");
		validate(txtFarmName, lblError, "Farm");
	}
}