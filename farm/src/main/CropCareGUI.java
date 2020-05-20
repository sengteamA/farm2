package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.Font;

import javax.swing.SwingConstants;

import main.crops.*;
import main.items.*;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ListIterator;
import java.awt.event.ActionEvent;

public class CropCareGUI {

	private JFrame frmCropManagement;
	private GameManager manager;
	private JTextField playerPlants;
	private JTextField playerItems;
	private JComboBox actBox;
	private JButton btnAction;
	private JButton quitButton;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public CropCareGUI(GameManager myManager) {
		manager = myManager;
		initialize();
		frmCropManagement.setVisible(true);
	}

	public void closeWindow() {
		frmCropManagement.dispose();
	}

	public void finishedWindow() {
		manager.closeCropScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCropManagement = new JFrame();
		frmCropManagement.setTitle("Crop Management");
		frmCropManagement.setBounds(100, 100, 717, 349);
		frmCropManagement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCropManagement.getContentPane().setLayout(null);

		JList plantList = new JList();
		plantList.setBounds(255, 57, 167, 201);
		frmCropManagement.getContentPane().add(plantList);
		DefaultListModel<String> cropListModel = new DefaultListModel<>();
		cropListModel.addAll(manager.farm.getCropType());
		plantList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		plantList.setModel(cropListModel);

		JList ItemList = new JList();
		ItemList.setBounds(453, 57, 218, 201);
		ItemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		frmCropManagement.getContentPane().add(ItemList);
		DefaultListModel<Item> itemListModel = new DefaultListModel<>();
		itemListModel.addAll(manager.farm.getItemType("Crop"));
		ItemList.setModel(itemListModel);

		playerPlants = new JTextField();
		playerPlants.setText("Player plants");
		playerPlants.setHorizontalAlignment(SwingConstants.CENTER);
		playerPlants.setFont(new Font("Tahoma", Font.BOLD, 15));
		playerPlants.setBounds(255, 12, 167, 35);
		playerPlants.setEditable(false);
		frmCropManagement.getContentPane().add(playerPlants);
		playerPlants.setColumns(10);

		playerItems = new JTextField();
		playerItems.setHorizontalAlignment(SwingConstants.CENTER);
		playerItems.setText("Player Items");
		playerItems.setFont(new Font("Tahoma", Font.BOLD, 15));
		playerItems.setColumns(10);
		playerItems.setBounds(453, 12, 218, 35);
		playerPlants.setEditable(false);
		frmCropManagement.getContentPane().add(playerItems);

		String acts[] = {"water plants", "use item"};
		actBox = new JComboBox(acts);
		actBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		actBox.setBounds(54, 102, 140, 45);
		frmCropManagement.getContentPane().add(actBox);

		btnAction = new JButton("Confirm Action");
		btnAction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Carrot carrot = new Carrot();
				Hipotke hip = new Hipotke();
				Mushroom mush = new Mushroom();
				Tomacco tomacco = new Tomacco();
				Wasabi wasabi = new Wasabi();
				Wheat wheat = new Wheat();

				ChemicalSpray spray = new ChemicalSpray();
				Compost compost = new Compost();
				InstantGroLite igl = new InstantGroLite();
				InstantGroPro igp = new InstantGroPro();

				if (actBox.getSelectedItem() == "water plants") {
					if (plantList.getSelectedValue() == null) {
						JOptionPane.showMessageDialog(frmCropManagement, "Please select a crop first.", "WARNING", JOptionPane.ERROR_MESSAGE);
					}
					else if(plantList.getSelectedValue() == "Carrot") {
						manager.farmer.tendToCrops("watering plants", carrot, null);
						JOptionPane.showMessageDialog(frmCropManagement, "Carrots watered.");
						finishedWindow();
					}
					else if(plantList.getSelectedValue() == "Hipotke Grass") {
						manager.farmer.tendToCrops("watering plants", hip, null);
						JOptionPane.showMessageDialog(frmCropManagement, "Hipotke Grass watered.");
						finishedWindow();
					}
					else if (plantList.getSelectedValue() == "Mushroom") {
						manager.farmer.tendToCrops("watering plants", mush, null);
						JOptionPane.showMessageDialog(frmCropManagement, "Mushrooms watered.");
						finishedWindow();
					}
					else if (plantList.getSelectedValue() == "Tomacco") {
						manager.farmer.tendToCrops("watering plants", tomacco, null);
						JOptionPane.showMessageDialog(frmCropManagement, "Tomaccos watered.");
						finishedWindow();
					}
					else if (plantList.getSelectedValue() == "Wasabi") {
						manager.farmer.tendToCrops("watering plants", wasabi, null);
						JOptionPane.showMessageDialog(frmCropManagement, "Wasabi watered");
						finishedWindow();
					}
					else if (plantList.getSelectedValue() == "Wheat") {
						manager.farmer.tendToCrops("watering plants", wheat, null);
						JOptionPane.showMessageDialog(frmCropManagement, "Wheat watered");
						finishedWindow();
					}
				}
				else if (actBox.getSelectedItem() == "use item"){
					if (plantList.getSelectedValue() == null || ItemList.getSelectedValue() == null) {
						JOptionPane.showMessageDialog(frmCropManagement, "Please select a crop and an item.", "WARNING", JOptionPane.ERROR_MESSAGE);
					}
					else if (plantList.getSelectedValue().equals("Carrot")) {
						ListIterator<Item> iterator = manager.farm.showItems().listIterator();
						Item item = null;
						while (iterator.hasNext()) {
							item = iterator.next();
							if (item == ItemList.getSelectedValue()) {
								break;
							}
						}
						if (item != null) {
							manager.farmer.tendToCrops("use item", carrot, item);
							JOptionPane.showMessageDialog(frmCropManagement, item.getName() + " used on carrots.");
							DefaultListModel<Item> itemListModel = new DefaultListModel<>();
							itemListModel.addAll(manager.farm.getItemType("Crop"));
							ItemList.setModel(itemListModel);
							finishedWindow();
						}
					}
					else if (plantList.getSelectedValue().equals("Hipotke Grass")) {
						ListIterator<Item> iterator = manager.farm.showItems().listIterator();
						Item item = null;
						while (iterator.hasNext()) {
							item = iterator.next();
							if (item == ItemList.getSelectedValue()) {
								break;
							}
						}
						if (item != null) {
							manager.farmer.tendToCrops("use item", hip, item);
							JOptionPane.showMessageDialog(frmCropManagement, item.getName() + " used on Hipotke Grass.");
							DefaultListModel<Item> itemListModel = new DefaultListModel<>();
							itemListModel.addAll(manager.farm.getItemType("Crop"));
							ItemList.setModel(itemListModel);
							finishedWindow();
						}
					}
					else if (plantList.getSelectedValue().equals("Mushroom")) {
						ListIterator<Item> iterator = manager.farm.showItems().listIterator();
						Item item = null;
						while (iterator.hasNext()) {
							item = iterator.next();
							if (item == ItemList.getSelectedValue()) {
								break;
							}
						}
						if (item != null) {
							manager.farmer.tendToCrops("use item", mush, item);
							JOptionPane.showMessageDialog(frmCropManagement, item.getName() + " used on mushrooms.");
							DefaultListModel<Item> itemListModel = new DefaultListModel<>();
							itemListModel.addAll(manager.farm.getItemType("Crop"));
							ItemList.setModel(itemListModel);
							finishedWindow();
						}
					}
					else if (plantList.getSelectedValue().equals("Tomacco")) {
						ListIterator<Item> iterator = manager.farm.showItems().listIterator();
						Item item = null;
						while (iterator.hasNext()) {
							item = iterator.next();
							if (item == ItemList.getSelectedValue()) {
								break;
							}
						}
						if (item != null) {
							manager.farmer.tendToCrops("use item", tomacco, item);
							JOptionPane.showMessageDialog(frmCropManagement, item.getName() + " used on Tomaccos.");
							DefaultListModel<Item> itemListModel = new DefaultListModel<>();
							itemListModel.addAll(manager.farm.getItemType("Crop"));
							ItemList.setModel(itemListModel);
							finishedWindow();
						}
					}
					else if (plantList.getSelectedValue().equals("Wasabi")) {
						ListIterator<Item> iterator = manager.farm.showItems().listIterator();
						Item item = null;
						while (iterator.hasNext()) {
							item = iterator.next();
							if (item == ItemList.getSelectedValue()) {
								break;
							}
						}
						if (item != null) {
							manager.farmer.tendToCrops("use item", wasabi, item);
							JOptionPane.showMessageDialog(frmCropManagement, item.getName() + " used on wasabi.");
							DefaultListModel<Item> itemListModel = new DefaultListModel<>();
							itemListModel.addAll(manager.farm.getItemType("Crop"));
							ItemList.setModel(itemListModel);
							finishedWindow();
						}
					}
					else if (plantList.getSelectedValue().equals("Wheat")) {
						ListIterator<Item> iterator = manager.farm.showItems().listIterator();
						Item item = null;
						while (iterator.hasNext()) {
							item = iterator.next();
							if (item == ItemList.getSelectedValue()) {
								break;
							}
						}
						if (item != null) {
							manager.farmer.tendToCrops("use item", wheat, item);
							JOptionPane.showMessageDialog(frmCropManagement, item.getName() + " used on wheat.");
							DefaultListModel<Item> itemListModel = new DefaultListModel<>();
							itemListModel.addAll(manager.farm.getItemType("Crop"));
							ItemList.setModel(itemListModel);
							finishedWindow();
						}
					}
				}
			}
		});
		btnAction.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAction.setBounds(54, 53, 140, 45);
		frmCropManagement.getContentPane().add(btnAction);

		quitButton = new JButton("Exit");
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		quitButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		quitButton.setBounds(54, 212, 140, 45);
		frmCropManagement.getContentPane().add(quitButton);
	}
}
