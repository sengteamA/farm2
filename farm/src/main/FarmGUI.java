package main;

import java.awt.EventQueue;

import main.animals.*;
import main.crops.*;
import main.farms.*;
import main.items.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class FarmGUI {

	private JFrame frmFarmOverview;
	private GameManager manager;
	private JTextField txtDays;
	private JTextField textField;
	private JTextField txtMoney;
	private JTextField txtFarmerName;
	private JTextField txtCapacity;
	private JTextField landCapacity;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;



	/**
	 * Create the application.
	 */
	public FarmGUI(GameManager myManager) {
		manager = myManager;
		initialize();
		frmFarmOverview.setVisible(true);
	}
	
	/**
	 * Closes this window (GameSetupWindow instance).
	 */
	public void closeWindow() {
		frmFarmOverview.dispose();
	}
	
	/**
	 * Calls the parent game manager, which closes this window and creates
	 * the next one that's needed.
	 */
	public void finishedWindow() {
		manager.closeFarmScreen(this);
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frmFarmOverview = new JFrame();
		frmFarmOverview.setTitle("Farm Overview");
		frmFarmOverview.getContentPane().setLayout(null);
		frmFarmOverview.setBounds(100, 100, 600, 450);
		
		JLabel lblLandCap = new JLabel("Land Capacity: " + manager.farm.getFarmCap());
		lblLandCap.setHorizontalAlignment(SwingConstants.LEFT);
		lblLandCap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLandCap.setBounds(417, 84, 137, 34);
		frmFarmOverview.getContentPane().add(lblLandCap);
		
		JLabel lblDay = new JLabel("Day: " + manager.dayNumber + "/" + manager.maxDays);
		lblDay.setHorizontalAlignment(SwingConstants.LEFT);
		lblDay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDay.setBounds(417, 119, 137, 34);
		frmFarmOverview.getContentPane().add(lblDay);
		
		JLabel lblFarmMoney = new JLabel("$ " + manager.farm.getBankBalance());
		lblFarmMoney.setHorizontalAlignment(SwingConstants.LEFT);
		lblFarmMoney.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFarmMoney.setBounds(417, 149, 137, 34);
		frmFarmOverview.getContentPane().add(lblFarmMoney);
		
		JLabel lblActions = new JLabel("Actions Left: " + manager.farm.getActionsLeft());
		lblActions.setHorizontalAlignment(SwingConstants.LEFT);
		lblActions.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblActions.setBounds(417, 179, 137, 42);
		frmFarmOverview.getContentPane().add(lblActions);
		
		JButton btnCrops = new JButton("Tend to Crops");
		btnCrops.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCrops.setBounds(31, 28, 155, 48);
		frmFarmOverview.getContentPane().add(btnCrops);
		
		JButton btnPlayWithAnimals = new JButton("Play with Animals");
		btnPlayWithAnimals.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnPlayWithAnimals.setBounds(31, 84, 155, 48);
		frmFarmOverview.getContentPane().add(btnPlayWithAnimals);
		
		JButton btnFeedAnimals = new JButton("Feed Animals");
		btnFeedAnimals.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnFeedAnimals.setBounds(31, 142, 155, 48);
		frmFarmOverview.getContentPane().add(btnFeedAnimals);
		
		JButton button = new JButton("Tend to Crops");
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.setBounds(196, 28, 155, 48);
		frmFarmOverview.getContentPane().add(button);
		
		JButton btnHarvestCrops = new JButton("Harvest Crops");
		btnHarvestCrops.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnHarvestCrops.setBounds(196, 84, 155, 48);
		frmFarmOverview.getContentPane().add(btnHarvestCrops);
		
		JButton btnVisitStore = new JButton("Visit Store");
		btnVisitStore.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnVisitStore.setBounds(196, 142, 155, 48);
		frmFarmOverview.getContentPane().add(btnVisitStore);

		String assets[] = {"", "Animals", "Crops", "Items"};
		JComboBox comboBox = new JComboBox(assets);
		comboBox.setBounds(31, 267, 111, 34);
		frmFarmOverview.getContentPane().add(comboBox);
		
		JList listsDisplay = new JList();
		listsDisplay.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		listsDisplay.setFont(new Font("Tahoma", Font.PLAIN, 12));
		listsDisplay.setBounds(152, 226, 199, 157);
		frmFarmOverview.getContentPane().add(listsDisplay);
		
		JButton btnViewAssets = new JButton("View Assets");
		btnViewAssets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().equals("Animals")) {
					DefaultListModel<Animal> animalListModel = new DefaultListModel<>();
					animalListModel.addAll(manager.farm.showAnimals());
					listsDisplay.setModel(animalListModel);
				}
				else if (comboBox.getSelectedItem().equals("Crops")) {
					DefaultListModel<Crop> cropListModel = new DefaultListModel<>();
					cropListModel.addAll(manager.farm.showCrops());
					listsDisplay.setModel(cropListModel);
				}
				else if (comboBox.getSelectedItem().equals("Items")) {
					DefaultListModel<Item> itemListModel = new DefaultListModel<>();
					itemListModel.addAll(manager.farm.showItems());
					listsDisplay.setModel(itemListModel);
				}
				else {
					DefaultListModel<String> placeHolder = new DefaultListModel<String>();
					listsDisplay.setModel(placeHolder);
				}
			}
		});
		
		btnViewAssets.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnViewAssets.setBounds(31, 226, 111, 42);
		frmFarmOverview.getContentPane().add(btnViewAssets);
		
		
		JLabel FarmStatus = new JLabel("Farm Status");
		FarmStatus.setHorizontalAlignment(SwingConstants.LEFT);
		FarmStatus.setFont(new Font("Tahoma", Font.BOLD, 18));
		FarmStatus.setBounds(417, 40, 137, 34);
		frmFarmOverview.getContentPane().add(FarmStatus);
		
		JButton btnDayEnd = new JButton("End Day");
		btnDayEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				++manager.dayNumber;
				if (manager.dayNumber > (manager.maxDays)) {
					finishedWindow();
					JOptionPane.showMessageDialog(frmFarmOverview, "==========\nYou have completed the farm simulator!\nThank you for playing.\n==========\n"
							+ "Farm name: " + manager.farm.getName() + "\n" + "Game duration: " + (manager.dayNumber-1) + "\n" + "Profit: $" + manager.farm.getBankBalance());
				}
				manager.farm.refreshAP();
				int income = manager.farm.getDailyBonusMoney();
				manager.farm.updateBankBalance(income);
				for (Crop crop: manager.farm.showCrops()) {
					crop.updateDaysElapsed(1);
				}
				lblDay.setText("Day: " + manager.dayNumber + "/" + manager.maxDays);
				lblFarmMoney.setText("$ " + manager.farm.getBankBalance());
				lblActions.setText("Actions Left: " + manager.farm.getActionsLeft());
				comboBox.setSelectedIndex(0);
				DefaultListModel<String> placeHolder = new DefaultListModel<String>();
				listsDisplay.setModel(placeHolder);
			}
		});
		btnDayEnd.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDayEnd.setBounds(399, 335, 155, 48);
		frmFarmOverview.getContentPane().add(btnDayEnd);
		
		
		
	}
}
