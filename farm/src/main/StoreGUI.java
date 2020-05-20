package main;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.animals.*;
import main.items.*;
import main.crops.*;
import main.farms.*;

import java.awt.GridLayout;

public class StoreGUI {
	private JFrame window;
	private GameManager manager;

	// GUI components used by StoreGUI class methods
	private JTextField txtPlayerMoney;
	private JComboBox<String> selectAssetType;
	private JList<String> assetList;

	private ArrayList<Animal> animals;
	private ArrayList<Crop> crops;
	private ArrayList<Item> items;

	private final int ANIMAL_INDEX = 0;
	private final int CROP_INDEX = 1;
	private final int ITEM_INDEX = 2;

	/**
	 * The new price of items if the farm is a Trump Ranch, where 1 is the
	 * original price. For example, 0.9 would be a 10% discount.
	 */
	private final float TRUMP_RANCH_DISCOUNT = (float)0.9;

	/**
	 * The new price of cows if the farm is a Moo Moo Farm, as a proportion
	 * of the original price.
	 */
	private final float MOOMOO_FARM_DISCOUNT = (float)0.8;

	/**
	 * Return the price of an asset, after any relevant discounts (i.e.
	 * Trump Ranch discount) have been applied.
	 *
	 * @param price - price of the asset
	 * @param name - name of the asset
	 * @return new price of asset when purchasing in store
	 */
	private int getStorePrice(int price, String name) {
		if (manager.farm instanceof TrumpRanch) {
			return (int)(price * TRUMP_RANCH_DISCOUNT);
		} else if (manager.farm instanceof MoomooFarm && name.contentEquals("Cow")) {
			return (int)(price * MOOMOO_FARM_DISCOUNT);
		}
		return price;
	}

	/**
	 * Refresh the label that states the player's current amount of money.
	 */
	private void refreshMoneyLabel() {
		txtPlayerMoney.setText("$" + manager.farm.getBankBalance());
	}

	/**
	 * Adds all the animals to the animals map, which stores all the animals
	 * available for purchase in the store.
	 */
	private void initialiseAnimals() {
		animals.add(new Cow());
		animals.add(new Fox());
		animals.add(new Sheep());
	}

	/**
	 * Adds all the crops to the crops map, which stores all the crops
	 * available for purchase in the store.
	 */
	private void initialiseCrops() {
		crops.add(new Carrot());
		crops.add(new Hipotke());
		crops.add(new Mushroom());
		crops.add(new Tomacco());
		crops.add(new Wasabi());
		crops.add(new Wheat());
	}

	/**
	 * Adds all the items to the items map, which stores all the items
	 * available for purchase in the store.
	 */
	private void initialiseItems() {
		items.add(new ChemicalSpray());
		items.add(new Compost());
		items.add(new InstantGroLite());
		items.add(new InstantGroPro());
		items.add(new PandaGummy());
		items.add(new Stockfeed());
	}

	private void refreshAssetList() {
		DefaultListModel<String> assetListModel = new DefaultListModel<>();
		if (selectAssetType.getSelectedIndex() == ANIMAL_INDEX) {
			for (Animal animal : manager.farm.showAnimals()) {
				assetListModel.addElement(animal.toString());
			}
		}
		else if (selectAssetType.getSelectedIndex() == CROP_INDEX) {
			for (Crop crop : manager.farm.showCrops()) {
				assetListModel.addElement(crop.toString());
			}
		}
		else if (selectAssetType.getSelectedIndex() == ITEM_INDEX) {
			for (Item item : manager.farm.showItems()) {
				assetListModel.addElement(item.toString());
			}
		}
		assetList.setModel(assetListModel);
	}

	/**
	 * Purchase an animal, deduct the appropriate amount of money,
	 * and update the player's list of animals.
	 * @param animal - an instance of the Animal the player wants to buy
	 */
	private void purchaseAsset(Animal animal) {
		try {
			int price = getStorePrice(animal.getPurchasePrice(), animal.getName());
			if (!manager.farm.hasEnoughMoney(price)) {
				throw new IllegalStateException("Not enough money!");
			}
			Animal newAnimal = animal.getClass().getDeclaredConstructor().newInstance();
			// Animal Farm bonus: 20% higher health and happiness
			//
			// Note other bonuses have already been taken into account in
			// getStorePrice()
			if (manager.farm instanceof AnimalFarm) {
				float health = newAnimal.getHealth() * manager.farm.getAnimalBonus();
				float happy = newAnimal.getHappiness() * manager.farm.getAnimalBonus();
				newAnimal.updateHealth((int)health);
				newAnimal.updateHappiness((int)happy);
			}
			manager.farm.updateBankBalance(-price);
			manager.farm.addAnimal(newAnimal);
			refreshMoneyLabel();
			refreshAssetList();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | SecurityException | NoSuchMethodException e) {
			// handle potential errors by just stopping the entire method
			e.printStackTrace();
			throw new IllegalArgumentException("Something's wrong with this product. " +
					"Buy something else instead?");
		}
	}

	/**
	 * Purchase a crop, deduct the appropriate amount of money,
	 * and update the player's list of crops.
	 * @param crop - an instance of the Crop the player wants to buy
	 */
	private void purchaseAsset(Crop crop) {
		try {
			int price = getStorePrice(crop.getPurchasePrice(), crop.getName());
			if (!manager.farm.hasEnoughMoney(price)) {
				throw new IllegalStateException("Not enough money!");
			}
			if (!manager.farm.hasSpace()) {
				throw new IllegalArgumentException("Not enough space on the farm to add another crop!");
			}
			Crop newCrop = crop.getClass().getDeclaredConstructor().newInstance();
			manager.farm.updateBankBalance(-price);
			manager.farm.addCrop(newCrop);
			refreshMoneyLabel();
			refreshAssetList();
		} catch (InstantiationException | IllegalAccessException |
				IllegalArgumentException | InvocationTargetException |
				SecurityException | NoSuchMethodException e) {
			// handle potential errors by just stopping the entire method
			e.printStackTrace();
			throw new IllegalArgumentException("Something's wrong with this " +
					"product. Buy something else instead?");
		}
	}

	/**
	 * Purchase an animal, deduct the appropriate amount of money,
	 * and update the player's list of animals.
	 * @param item - an instance of the Item the player wants to buy
	 */
	private void purchaseAsset(Item item) {
		try {
			int price = getStorePrice(item.getPurchasePrice(), item.getName());
			if (!manager.farm.hasEnoughMoney(price)) {
				throw new IllegalStateException("Not enough money!");
			}
			Item newItem = item.getClass().getDeclaredConstructor().newInstance();
			manager.farm.updateBankBalance(-price);
			manager.farm.addItem(newItem);
			refreshMoneyLabel();
			refreshAssetList();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | SecurityException | NoSuchMethodException e) {
			// handle potential errors by just stopping the entire method
			e.printStackTrace();
			throw new IllegalArgumentException("Something's wrong with this product. " +
					"Buy something else instead?");
		}
	}

	/**
	 * Initialises the StoreGUI instance with the given GameManager.
	 * @param myManager Game manager instance
	 */
	public StoreGUI(GameManager myManager) {
		manager = myManager;
		animals = new ArrayList<Animal>();
		crops = new ArrayList<Crop>();
		items = new ArrayList<Item>();
		initialiseAnimals();
		initialiseCrops();
		initialiseItems();

		initialise();
		window.setVisible(true);
	}

	/**
	 * Closes the window.
	 */
	public void closeWindow() {
		window.dispose();
	}

	/**
	 * Runs when the user closes the window.
	 *
	 * Passes control back to GameManager, which will call StoreGUI.closeWindow()
	 * and do whatever else it needs to do.
	 */
	public void finishedWindow() {
		manager.closeStoreScreen(this);
	}

	/**
	 * Initialise the contents of the Store window.
	 */
	private void initialise() {
		window = new JFrame();
		window.setTitle("Store");
		window.setBounds(100, 100, 800, 600);
		// Do nothing when the X button is pressed
		// perhaps a lazy solution, but it keeps things easy to manage
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		window.getContentPane().setLayout(null);

		JButton btnLeave = new JButton("Leave");
		btnLeave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		btnLeave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLeave.setBounds(670, 517, 104, 33);
		window.getContentPane().add(btnLeave);

		assetList = new JList<String>();
		assetList.setBounds(22, 121, 325, 385);
		window.getContentPane().add(assetList);

		String playerAssetCategories[] = {"My animals", "My crops", "My items"};

		selectAssetType = new JComboBox(playerAssetCategories);
		selectAssetType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		selectAssetType.setBounds(22, 77, 325, 33);
		window.getContentPane().add(selectAssetType);

		selectAssetType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refreshAssetList();
			}
		});
		selectAssetType.setSelectedIndex(0);

		txtPlayerMoney = new JTextField();
		txtPlayerMoney.setHorizontalAlignment(SwingConstants.CENTER);
		txtPlayerMoney.setText("$: 0");
		txtPlayerMoney.setEditable(false);
		txtPlayerMoney.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPlayerMoney.setBounds(22, 22, 325, 44);
		window.getContentPane().add(txtPlayerMoney);
		txtPlayerMoney.setColumns(10);
		refreshMoneyLabel();

		String storeAssetCategories[] = {
				"Browse animals in store",
				"Browse crops in store",
				"Browse items in store"
		};

		JComboBox<String> selectAisle = new JComboBox(storeAssetCategories);
		selectAisle.setBounds(380, 22, 394, 33);
		window.getContentPane().add(selectAisle);

		JList<String> aisleAssetsList = new JList<String>();
		aisleAssetsList.setBounds(380, 66, 394, 270);
		window.getContentPane().add(aisleAssetsList);

		// Update the list of animals/crops/items
		// when a different asset category is selected
		selectAisle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultListModel<String> assetModel = new DefaultListModel<String>();
				if (selectAisle.getSelectedIndex() == ANIMAL_INDEX) {
					for (Animal animal : animals) {
						float storePrice = getStorePrice(
								animal.getPurchasePrice(),
								animal.getName()
						);
						assetModel.addElement(
								animal.name + " - $" + storePrice
						);
					}
				}
				else if (selectAisle.getSelectedIndex() == CROP_INDEX) {
					for (Crop crop : crops) {
						float storePrice = getStorePrice(
								crop.getPurchasePrice(),
								crop.getName()
						);
						assetModel.addElement(
								crop.name + " - $" + storePrice
						);
					}
				}
				else if (selectAisle.getSelectedIndex() == ITEM_INDEX) {
					for (Item item : items) {
						float storePrice = getStorePrice(item.getPurchasePrice(), item.getName());
						assetModel.addElement(
								item.name + " - $" + storePrice
						);
					}
				}
				aisleAssetsList.setModel(assetModel);
			}
		});

		selectAisle.setSelectedIndex(0);

		JPanel descriptionPanel = new JPanel();
		descriptionPanel.setBorder(new TitledBorder(
				null,
				"Description",
				TitledBorder.LEADING,
				TitledBorder.TOP,
				null,
				null
		));
		descriptionPanel.setBounds(380, 347, 394, 109);
		window.getContentPane().add(descriptionPanel);
		descriptionPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane descriptionWrapper = new JScrollPane();
		descriptionPanel.add(descriptionWrapper);
		descriptionWrapper.setBorder(null);

		JTextArea descriptionBox = new JTextArea();
		descriptionWrapper.setViewportView(descriptionBox);
		descriptionBox.setWrapStyleWord(true);
		descriptionBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		descriptionBox.setEditable(false);
		descriptionBox.setBackground(descriptionPanel.getBackground());
		descriptionBox.setBorder(null);

		aisleAssetsList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int assetIndex = aisleAssetsList.getSelectedIndex();
				// nothing is selected
				if (assetIndex == -1) {
					return;
				}
				// something is selected -- find out what asset category it is in
				// (animals, crops, or items)
				if (selectAisle.getSelectedIndex() == ANIMAL_INDEX) {
					descriptionBox.setText(animals.get(assetIndex).getInfo());
				}
				else if (selectAisle.getSelectedIndex() == CROP_INDEX) {
					descriptionBox.setText(crops.get(assetIndex).getInfo());
				}
				else if (selectAisle.getSelectedIndex() == ITEM_INDEX) {
					descriptionBox.setText(items.get(assetIndex).getInfo());
				}
			}
		});

		JButton btnBuy = new JButton("Buy this!");
		btnBuy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBuy.setBounds(380, 465, 394, 41);
		window.getContentPane().add(btnBuy);

		JTextArea lblFarmBenefit = new JTextArea("Farm benefits.");
		lblFarmBenefit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFarmBenefit.setBounds(22, 517, 638, 33);
		lblFarmBenefit.setWrapStyleWord(true);
		lblFarmBenefit.setEditable(false);
		lblFarmBenefit.setBackground(window.getBackground());
		window.getContentPane().add(lblFarmBenefit);

		if (manager.farm instanceof AnimalFarm) {
			lblFarmBenefit.setText("Your farm is an " + manager.farm.getType() + ".");
		} else if (manager.farm instanceof MoomooFarm) {
			lblFarmBenefit.setText("Since your farm is a " + manager.farm.getType() +
					", cows have been set to be 20% cheaper!");
			lblFarmBenefit.setFont(new Font("Tahoma", Font.BOLD, 13));
		} else if (manager.farm instanceof TomaccoLand) {
			lblFarmBenefit.setText("Your farm is a " + manager.farm.getType() + ".");
		} else if (manager.farm instanceof TrumpRanch) {
			lblFarmBenefit.setText("Since your farm is a " + manager.farm.getType() +
					", everything has become 10% cheaper!");
			lblFarmBenefit.setFont(new Font("Tahoma", Font.BOLD, 13));
		}

		btnBuy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedAssetCategory = selectAisle.getSelectedIndex();
				int selectedAsset = aisleAssetsList.getSelectedIndex();
				try {
					if (selectedAssetCategory == ANIMAL_INDEX) {
						purchaseAsset(animals.get(selectedAsset));
					} else if (selectedAssetCategory == CROP_INDEX) {
						purchaseAsset(crops.get(selectedAsset));
					} else if (selectedAssetCategory == ITEM_INDEX) {
						purchaseAsset(items.get(selectedAsset));
					} else {
						throw new IndexOutOfBoundsException();
					}
					refreshMoneyLabel();
				} catch (IllegalStateException err) {
					// Not enough money
					JOptionPane.showMessageDialog(window, err.getMessage());
				} catch (IllegalArgumentException err) {
					// Reached farm cap for crops, or some fatal error in
					// the backend
					JOptionPane.showMessageDialog(window, err.getMessage());
				} catch (IndexOutOfBoundsException err) {
					// Nothing selected
					JOptionPane.showMessageDialog(window,
							"Oops, please select something first!"
					);
				}
			}
		});
	}
}
