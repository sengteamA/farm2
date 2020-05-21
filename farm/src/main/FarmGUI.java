package main;

import main.animals.*;
import main.crops.*;
import main.items.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

/**
 * The main farm simulator window, with buttons that lead to daily actions or
 * other actions a player can perform.
 *
 * @author Nick
 */
public class FarmGUI {
	private GameManager manager;

	// Various GUI components used by the window and are required in
	// FarmGUI's methods
	private JFrame window;
	private JLabel lblActions;
	private JLabel lblFarmMoney;
	private JComboBox<String> selectAssetType;
	private JList<String> listsDisplay;
	private JTextField viewAssets;

	/**
	 * Create the application.
	 * @param myManager the game instance to use
	 */
	public FarmGUI(GameManager myManager) {
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
	 * Updates the "Actions Left" label in the main farm window.
	 */
	public void updateActionLabel() {
		lblActions.setText("Actions Left: " + manager.farm.getActionsLeft());
	}

	/**
	 * Calls the parent game manager, which closes this window and creates
	 * the next one that's needed.
	 */
	public void finishedWindow() {
		manager.closeFarmScreen();
	}

	/**
	 * Launches the store window and interface.
	 */
	public void launchStore() {
		manager.launchStore();
	}

	/**
	 * Refreshes the list of player's assets, whether that be animals, crops
	 * or items. Typically run after an action that modifies any one of these.
	 */
	private void refreshAssetList() {
		DefaultListModel<String> assetListModel = new DefaultListModel<>();
		if (selectAssetType.getSelectedItem().equals("Animals")) {
			for (Animal animal : manager.farm.showAnimals()) {
				assetListModel.addElement(animal.toString());
			}
		}
		else if (selectAssetType.getSelectedItem().equals("Crops")) {
			for (Crop crop : manager.farm.showCrops()) {
				assetListModel.addElement(crop.toString());
			}
		}
		else if (selectAssetType.getSelectedItem().equals("Items")) {
			for (Item item : manager.farm.showItems()) {
				assetListModel.addElement(item.toString());
			}
		}
		listsDisplay.setModel(assetListModel);
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialise() {
		window = new JFrame();
		window.setTitle("Farm Overview");
		window.getContentPane().setLayout(null);
		window.setBounds(100, 100, 600, 477);

		JLabel lblLandCap = new JLabel("Land Capacity: " + manager.farm.getFarmCap());
		lblLandCap.setHorizontalAlignment(SwingConstants.LEFT);
		lblLandCap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLandCap.setBounds(417, 84, 137, 34);
		window.getContentPane().add(lblLandCap);

		JLabel lblDay = new JLabel("Day: " + manager.dayNumber + "/" + manager.maxDays);
		lblDay.setHorizontalAlignment(SwingConstants.LEFT);
		lblDay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDay.setBounds(417, 119, 137, 34);
		window.getContentPane().add(lblDay);

		lblFarmMoney = new JLabel("$ " + manager.farm.getBankBalance());
		lblFarmMoney.setHorizontalAlignment(SwingConstants.LEFT);
		lblFarmMoney.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFarmMoney.setBounds(417, 149, 137, 34);
		window.getContentPane().add(lblFarmMoney);

		lblActions = new JLabel("Actions Left: " + manager.farm.getActionsLeft());
		lblActions.setHorizontalAlignment(SwingConstants.LEFT);
		lblActions.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblActions.setBounds(417, 179, 137, 42);
		window.getContentPane().add(lblActions);

		JButton tendLand = new JButton("Tend to Land");
		tendLand.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (manager.farm.getActionsLeft() == 0) {
					JOptionPane.showMessageDialog(
							window,
							"You have no actions left. Please end day to replenish.",
							"WARNING",
							JOptionPane.ERROR_MESSAGE
					);
				}
				else {
					manager.farmer.tendToLand();
					lblActions.setText(
							"Actions Left: " +
							manager.farm.getActionsLeft());
					lblLandCap.setText(
							"Land Capacity: " +
							manager.farm.getFarmCap());
					JOptionPane.showMessageDialog(
							window,
							"Your land grows fertile and animals are happier.");
				}
			}
		});
		tendLand.setFont(new Font("Tahoma", Font.BOLD, 12));
		tendLand.setBounds(196, 28, 155, 48);
		window.getContentPane().add(tendLand);

		JButton btnVisitStore = new JButton("Visit Store");
		btnVisitStore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				launchStore();
			}
		});
		btnVisitStore.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnVisitStore.setBounds(196, 142, 155, 48);
		window.getContentPane().add(btnVisitStore);

		//will not automatically update unless "pressed" again
		JTextField ViewAssets = new JTextField("View Assets");
		ViewAssets.setHorizontalAlignment(SwingConstants.CENTER);

		listsDisplay = new JList<String>();
		listsDisplay.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		listsDisplay.setFont(new Font("Tahoma", Font.PLAIN, 12));
		listsDisplay.setBounds(152, 256, 199, 173);
		window.getContentPane().add(listsDisplay);

		String assets[] = {"", "Animals", "Crops", "Items"};
		selectAssetType = new JComboBox<String>(assets);
		selectAssetType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refreshAssetList();
			}
		});
		selectAssetType.setSelectedIndex(0);
		selectAssetType.setBounds(31, 298, 111, 34);
		window.getContentPane().add(selectAssetType);

		JButton btnHarvestCrops = new JButton("Harvest Crops");
		btnHarvestCrops.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (manager.farm.getActionsLeft() == 0) {
					JOptionPane.showMessageDialog(
							window,
							"You have no actions left. Please end day to replenish.",
							"WARNING",
							JOptionPane.ERROR_MESSAGE
					);
				}
				else if (!manager.farm.hasCrops()) {
					JOptionPane.showMessageDialog(
							window,
							"You have no crops. Please see your local supplier.",
							"WARNING",
							JOptionPane.ERROR_MESSAGE
					);
				}
				else if (manager.farm.showCrops().stream().allMatch(c -> c.getDaysLeft() > 0)) {
					JOptionPane.showMessageDialog(
							window,
							"No crops available for harvest.",
							"WARNING",
							JOptionPane.ERROR_MESSAGE
					);
				}
				else {
					manager.farmer.harvestCrops();
					JOptionPane.showMessageDialog(window, "Crop(s) were harvested and sold.");
					refreshAssetList();
				}
				lblFarmMoney.setText("$ " + manager.farm.getBankBalance());
				lblActions.setText("Actions Left: " + manager.farm.getActionsLeft());
			}
		});
		btnHarvestCrops.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnHarvestCrops.setBounds(196, 84, 155, 48);
		window.getContentPane().add(btnHarvestCrops);


		JLabel FarmStatus = new JLabel("Farm Status");
		FarmStatus.setHorizontalAlignment(SwingConstants.LEFT);
		FarmStatus.setFont(new Font("Tahoma", Font.BOLD, 18));
		FarmStatus.setBounds(417, 40, 137, 34);
		window.getContentPane().add(FarmStatus);


		JButton btnPlayWithAnimals = new JButton("Play with Animals");
		btnPlayWithAnimals.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (manager.farm.getActionsLeft() == 0) {
					JOptionPane.showMessageDialog(
							window,
							"You have no actions left. Please end day to replenish.",
							"WARNING",
							JOptionPane.ERROR_MESSAGE
					);
				}
				else if (!manager.farm.hasAnimals()) {
					JOptionPane.showMessageDialog(window,
							"You have no animals. Please see your local supplier.",
							"WARNING",
							JOptionPane.ERROR_MESSAGE
					);
				}
				else {
					manager.farmer.playWithAnimals();
					lblActions.setText("Actions Left: " + manager.farm.getActionsLeft());
					JOptionPane.showMessageDialog(
							window,
							"Your animals are happier for your attention."
					);
					refreshAssetList();
				}
			}
		});

		btnPlayWithAnimals.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnPlayWithAnimals.setBounds(31, 84, 155, 48);
		window.getContentPane().add(btnPlayWithAnimals);


		JButton btnDayEnd = new JButton("End Day");
		btnDayEnd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				++manager.dayNumber;
				if (manager.dayNumber > (manager.maxDays)) {
					finishedWindow();
					JOptionPane.showMessageDialog(
							window,
							"==========\n" +
							"You have completed the farm simulator!\n" +
							"Thank you for playing.\n==========\n"
							+ "Farm name: " + manager.farm.getName() + "\n" +
							"Game duration: " + (manager.dayNumber-1) + "\n" +
							"Profit: $" + manager.farm.getBankBalance() + "\n" +
							"Final score:" + manager.farm.getScore()
					);
				}
				manager.farm.refreshAP();
				int income = manager.farm.getDailyBonusMoney();
				manager.farm.updateBankBalance(income);
				for (Crop crop : manager.farm.showCrops()) {
					crop.updateDaysElapsed(1);
				}
				for (Crop crop : manager.farm.showCrops()) {
					if (crop.getDaysLeft() == 0) {
						JOptionPane.showMessageDialog(
								window,
								"You have crops ready for harvest."
						);
						break;
					}
				}
				lblDay.setText("Day: " + manager.dayNumber + "/" + manager.maxDays);
				lblFarmMoney.setText("$ " + manager.farm.getBankBalance());
				lblActions.setText("Actions Left: " + manager.farm.getActionsLeft());
				selectAssetType.setSelectedIndex(0);
				refreshAssetList();
			}
		});
		btnDayEnd.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDayEnd.setBounds(399, 359, 155, 48);
		window.getContentPane().add(btnDayEnd);

		JLabel lblCentralDisplay = new JLabel("Asset Display");
		lblCentralDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		lblCentralDisplay.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCentralDisplay.setBounds(152, 214, 199, 32);
		window.getContentPane().add(lblCentralDisplay);

		JButton btnfeedAnimals = new JButton("Feed Animals");
		btnfeedAnimals.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (manager.farm.getActionsLeft() == 0) {
					JOptionPane.showMessageDialog(
							window,
							"You have no actions left. Please end day to replenish.",
							"WARNING",
							JOptionPane.ERROR_MESSAGE
					);
				}

				else if (!manager.farm.hasAnimals()) {
					JOptionPane.showMessageDialog(
							window,
							"You have no animals. Please see your local supplier.",
							"WARNING",
							JOptionPane.ERROR_MESSAGE
					);
				}

				else if(!manager.farm.hasFoodItems()) {
					JOptionPane.showMessageDialog(
							window,
							"You have no food. Please see your local supplier.",
							"WARNING",
							JOptionPane.ERROR_MESSAGE
					);
				}
				else {
					manager.launchFeedingScreen();
				}
			}
		});

		btnfeedAnimals.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnfeedAnimals.setBounds(31, 142, 155, 48);
		window.getContentPane().add(btnfeedAnimals);

		JButton tendtoCrops = new JButton("Tend to Crops");
		tendtoCrops.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (manager.farm.getActionsLeft() == 0) {
					JOptionPane.showMessageDialog(
							window,
							"You have no actions left. Please end day to replenish.",
							"WARNING",
							JOptionPane.ERROR_MESSAGE
					);
				}
				else if (manager.farm.hasCrops() == false) {
					JOptionPane.showMessageDialog(
							window,
							"You have no crops. Please see your local supplier.",
							"WARNING",
							JOptionPane.ERROR_MESSAGE
					);
				}
				else {
					manager.launchCropsScreen();
				}
			}
		});
		tendtoCrops.setFont(new Font("Tahoma", Font.BOLD, 12));
		tendtoCrops.setBounds(31, 28, 155, 48);
		window.getContentPane().add(tendtoCrops);

		viewAssets = new JTextField();
		viewAssets.setText("View Assets");
		viewAssets.setFont(new Font("Tahoma", Font.BOLD, 15));
		viewAssets.setHorizontalAlignment(SwingConstants.CENTER);
		viewAssets.setBounds(31, 256, 111, 34);
		window.getContentPane().add(viewAssets);
		viewAssets.setColumns(10);
	}
}