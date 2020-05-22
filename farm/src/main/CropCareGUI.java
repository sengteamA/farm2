package main;

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
import java.util.ListIterator;
import java.awt.event.ActionEvent;

/**
 * GUI window for tending to crops. Has a list of items that can be
 * applied to crops, and a list of crops. Multiple instances of the same
 * crop counts as one crop when it comes to applying an item, i.e.
 * applying an item on Carrot applies it to all of the player's carrots
 * simultaneously.
 *
 * @author Nick
 */
public class CropCareGUI {
	private JFrame window;
	private GameManager manager;
	private JTextField playerPlants;
	private JTextField playerItems;
	private JComboBox<?> actBox;
	private JButton actionButton;
	private JButton quitButton;

	/**
	 * Create the application.
	 *
	 * @param myManager the game instance to use
	 */
	public CropCareGUI(GameManager myManager) {
		manager = myManager;
		initialise();
		window.setVisible(true);
	}

	/**
	 * Closes the CropCareGUI window. Run from GameManager.
	 */
	public void closeWindow() {
		window.dispose();
	}

	/**
	 * Tells GameManager to close this window and handle what happens
	 * after that.
	 */
	public void finishedWindow() {
		manager.closeCropScreen(this);
	}

	/**
	 * Initialise the contents of the crop care GUI window.
	 */
	private void initialise() {
		window = new JFrame();
		window.setTitle("Crop Management");
		window.setBounds(100, 100, 717, 349);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);

		JList<String> plantList = new JList<String>();
		plantList.setBounds(255, 57, 167, 201);
		window.getContentPane().add(plantList);
		DefaultListModel<String> cropListModel = new DefaultListModel<>();
		cropListModel.addAll(manager.farm.getCropType());
		plantList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		plantList.setModel(cropListModel);

		JList<Item> ItemList = new JList<Item>();
		ItemList.setBounds(453, 57, 218, 201);
		ItemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		window.getContentPane().add(ItemList);
		DefaultListModel<Item> itemListModel = new DefaultListModel<>();
		itemListModel.addAll(manager.farm.getItemType("Crop"));
		ItemList.setModel(itemListModel);

		playerPlants = new JTextField();
		playerPlants.setText("Player plants");
		playerPlants.setHorizontalAlignment(SwingConstants.CENTER);
		playerPlants.setFont(new Font("Tahoma", Font.BOLD, 15));
		playerPlants.setBounds(255, 12, 167, 35);
		playerPlants.setEditable(false);
		window.getContentPane().add(playerPlants);
		playerPlants.setColumns(10);

		playerItems = new JTextField();
		playerItems.setHorizontalAlignment(SwingConstants.CENTER);
		playerItems.setText("Player items");
		playerItems.setFont(new Font("Tahoma", Font.BOLD, 15));
		playerItems.setColumns(10);
		playerItems.setBounds(453, 12, 218, 35);
		playerItems.setEditable(false);
		window.getContentPane().add(playerItems);

		String acts[] = {"water plants", "use item"};
		actBox = new JComboBox<Object>(acts);
		actBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		actBox.setBounds(54, 102, 140, 45);
		window.getContentPane().add(actBox);

		actionButton = new JButton("Confirm Action");
		actionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Carrot carrot = new Carrot();
				Hipotke hip = new Hipotke();
				Mushroom mush = new Mushroom();
				Tomacco tomacco = new Tomacco();
				Wasabi wasabi = new Wasabi();
				Wheat wheat = new Wheat();

				if (actBox.getSelectedItem() == "water plants") {
					if (plantList.getSelectedValue() == null) {
						JOptionPane.showMessageDialog(window,
								"Please select a crop first.",
								"WARNING", JOptionPane.ERROR_MESSAGE);
					}
					else if(plantList.getSelectedValue().contentEquals(carrot.getName())) {
						manager.farmer.tendToCrops("watering plants", carrot,
								null);
						JOptionPane.showMessageDialog(window,
								"Carrots watered.");
						finishedWindow();
					}
					else if(plantList.getSelectedValue().contentEquals(hip.getName())) {
						manager.farmer.tendToCrops("watering plants", hip,
								null);
						JOptionPane.showMessageDialog(window,
								"Hipotke Grass watered.");
						finishedWindow();
					}
					else if (plantList.getSelectedValue().contentEquals(mush.getName())) {
						manager.farmer.tendToCrops("watering plants", mush,
								null);
						JOptionPane.showMessageDialog(window,
								"Mushrooms watered.");
						finishedWindow();
					}
					else if (plantList.getSelectedValue().contentEquals(tomacco.getName())) {
						manager.farmer.tendToCrops("watering plants", tomacco,
								null);
						JOptionPane.showMessageDialog(window,
								"Tomaccos watered.");
						finishedWindow();
					}
					else if (plantList.getSelectedValue().contentEquals(wasabi.getName())) {
						manager.farmer.tendToCrops("watering plants", wasabi,
								null);
						JOptionPane.showMessageDialog(window,
								"Wasabi watered");
						finishedWindow();
					}
					else if (plantList.getSelectedValue().contentEquals(wheat.getName())) {
						manager.farmer.tendToCrops("watering plants", wheat,
								null);
						JOptionPane.showMessageDialog(window,
								"Wheat watered");
						finishedWindow();
					}
				}

				else if (actBox.getSelectedItem() == "use item") {
					if (plantList.getSelectedValue() == null ||
							ItemList.getSelectedValue() == null) {
						JOptionPane.showMessageDialog(window,
								"Please select a crop and an item.",
								"WARNING", JOptionPane.ERROR_MESSAGE);
					}
					else if (plantList.getSelectedValue().contentEquals(carrot.getName())) {
						ListIterator<Item> iterator =
								manager.farm.showItems().listIterator();
						Item item = null;
						while (iterator.hasNext()) {
							item = iterator.next();
							if (item == ItemList.getSelectedValue()) {
								break;
							}
						}
						if (item != null) {
							manager.farmer.tendToCrops("use item",
									carrot, item);
							JOptionPane.showMessageDialog(window,
									item.getName() + " used on carrots.");
							DefaultListModel<Item> itemListModel =
									new DefaultListModel<>();
							itemListModel.addAll(
									manager.farm.getItemType("Crop"));
							ItemList.setModel(itemListModel);
							finishedWindow();
						}
					}
					else if (plantList.getSelectedValue().contentEquals(hip.getName())) {
						ListIterator<Item> iterator =
								manager.farm.showItems().listIterator();
						Item item = null;
						while (iterator.hasNext()) {
							item = iterator.next();
							if (item == ItemList.getSelectedValue()) {
								break;
							}
						}
						if (item != null) {
							manager.farmer.tendToCrops("use item", hip, item);
							JOptionPane.showMessageDialog(window,
									item.getName() + " used on Hipotke Grass.");
							DefaultListModel<Item> itemListModel =
									new DefaultListModel<>();
							itemListModel.addAll(manager.farm.getItemType("Crop"));
							ItemList.setModel(itemListModel);
							finishedWindow();
						}
					}
					else if (plantList.getSelectedValue().contentEquals(mush.getName())) {
						ListIterator<Item> iterator =
								manager.farm.showItems().listIterator();
						Item item = null;
						while (iterator.hasNext()) {
							item = iterator.next();
							if (item == ItemList.getSelectedValue()) {
								break;
							}
						}
						if (item != null) {
							manager.farmer.tendToCrops("use item", mush, item);
							JOptionPane.showMessageDialog(window,
									item.getName() + " used on mushrooms.");
							DefaultListModel<Item> itemListModel =
									new DefaultListModel<>();
							itemListModel.addAll(manager.farm.getItemType("Crop"));
							ItemList.setModel(itemListModel);
							finishedWindow();
						}
					}
					else if (plantList.getSelectedValue().contentEquals(tomacco.getName())) {
						ListIterator<Item> iterator =
								manager.farm.showItems().listIterator();
						Item item = null;
						while (iterator.hasNext()) {
							item = iterator.next();
							if (item == ItemList.getSelectedValue()) {
								break;
							}
						}
						if (item != null) {
							manager.farmer.tendToCrops("use item", tomacco, item);
							JOptionPane.showMessageDialog(window,
									item.getName() + " used on Tomaccos.");
							DefaultListModel<Item> itemListModel =
									new DefaultListModel<>();
							itemListModel.addAll(manager.farm.getItemType("Crop"));
							ItemList.setModel(itemListModel);
							finishedWindow();
						}
					}
					else if (plantList.getSelectedValue().contentEquals(wasabi.getName())) {
						ListIterator<Item> iterator =
								manager.farm.showItems().listIterator();
						Item item = null;
						while (iterator.hasNext()) {
							item = iterator.next();
							if (item == ItemList.getSelectedValue()) {
								break;
							}
						}
						if (item != null) {
							manager.farmer.tendToCrops("use item", wasabi, item);
							JOptionPane.showMessageDialog(window,
									item.getName() + " used on wasabi.");
							DefaultListModel<Item> itemListModel = new DefaultListModel<>();
							itemListModel.addAll(manager.farm.getItemType("Crop"));
							ItemList.setModel(itemListModel);
							finishedWindow();
						}
					}
					else if (plantList.getSelectedValue().contentEquals(wheat.getName())) {
						ListIterator<Item> iterator =
								manager.farm.showItems().listIterator();
						Item item = null;
						while (iterator.hasNext()) {
							item = iterator.next();
							if (item == ItemList.getSelectedValue()) {
								break;
							}
						}
						if (item != null) {
							manager.farmer.tendToCrops("use item", wheat, item);
							JOptionPane.showMessageDialog(window,
									item.getName() + " used on wheat.");
							DefaultListModel<Item> itemListModel = new DefaultListModel<>();
							itemListModel.addAll(manager.farm.getItemType("Crop"));
							ItemList.setModel(itemListModel);
							finishedWindow();
						}
					}
				}
			}
		});
		actionButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		actionButton.setBounds(54, 53, 140, 45);
		window.getContentPane().add(actionButton);

		quitButton = new JButton("Exit");
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		quitButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		quitButton.setBounds(54, 212, 140, 45);
		window.getContentPane().add(quitButton);
	}
}