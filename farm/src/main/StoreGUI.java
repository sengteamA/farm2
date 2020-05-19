package main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.animals.*;
import main.items.*;
import main.crops.*;
import main.farms.AnimalFarm;
import main.farms.MoomooFarm;
import main.farms.TomaccoLand;
import main.farms.TrumpRanch;

import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;

public class StoreGUI {
	private JFrame window;
	private GameManager manager;

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
	 * The new price of cows if the farm is a Moo Moo Farm, as a proportion of
	 * the original price.
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
	private float getStorePrice(int price, String name) {
		if (manager.farm instanceof TrumpRanch) {
			return price * TRUMP_RANCH_DISCOUNT;
		} else if (manager.farm instanceof MoomooFarm && name.contentEquals("Cow")) {
			return price * MOOMOO_FARM_DISCOUNT;
		}
		return price;
	}

	/**
	 * Adds all the animals to the animals map, which stores all the animals
	 * available for purchase in the store.
	 */
	private void initialiseAnimals() {
		Cow cow = new Cow();
		Fox fox = new Fox();
		Sheep sheep = new Sheep();
		animals.add(cow);
		animals.add(fox);
		animals.add(sheep);
	}

	/**
	 * Adds all the crops to the crops map, which stores all the crops
	 * available for purchase in the store.
	 */
	private void initialiseCrops() {
		Carrot carrot = new Carrot();
		Hipotke hipotke = new Hipotke();
		Mushroom mushroom = new Mushroom();
		Tomacco tomacco = new Tomacco();
		Wasabi wasabi = new Wasabi();
		Wheat wheat = new Wheat();
		crops.add(carrot);
		crops.add(hipotke);
		crops.add(mushroom);
		crops.add(tomacco);
		crops.add(wasabi);
		crops.add(wheat);
	}

	/**
	 * Adds all the items to the items map, which stores all the items
	 * available for purchase in the store.
	 */
	private void initialiseItems() {
		ChemicalSpray chemicalSpray = new ChemicalSpray();
		Compost compost = new Compost();
		InstantGroLite instantGroLite = new InstantGroLite();
		InstantGroPro instantGroPro = new InstantGroPro();
		PandaGummy pandaGummy = new PandaGummy();
		Stockfeed stockfeed = new Stockfeed();
		items.add(chemicalSpray);
		items.add(compost);
		items.add(instantGroLite);
		items.add(instantGroPro);
		items.add(pandaGummy);
		items.add(stockfeed);
	}

	private void purchaseAsset(Animal animal) {

	}

	private void purchaseAsset(Crop crop) {

	}

	private void purchaseAsset(Item item) {

	}

	/**
	 * Initialises the StoreGUI instance with the given GameManager.
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

	public void closeWindow() {
		window.dispose();
	}

	public void closeFinishedScreen() {
		manager.closeStoreScreen(this);
	}

	/**
	 * Initialise the contents of the Store window.
	 */
	private void initialise() {
		window = new JFrame();
		window.setTitle("Store");
		window.setBounds(100, 100, 800, 600);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.getContentPane().setLayout(null);

		JButton btnLeave = new JButton("Leave");
		btnLeave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeFinishedScreen();
			}
		});
		btnLeave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLeave.setBounds(670, 517, 104, 33);
		window.getContentPane().add(btnLeave);

		JList assetList = new JList();
		assetList.setBounds(22, 121, 325, 385);
		window.getContentPane().add(assetList);

		String playerAssetCategories[] = {"My animals", "My crops", "My items"};

		JComboBox selectAssetType = new JComboBox(playerAssetCategories);
		selectAssetType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		selectAssetType.setBounds(22, 77, 325, 33);
		window.getContentPane().add(selectAssetType);

		selectAssetType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectAssetType.getSelectedIndex() == ANIMAL_INDEX) {
					DefaultListModel<Animal> animalListModel = new DefaultListModel<>();
					animalListModel.addAll(manager.farm.showAnimals());
					assetList.setModel(animalListModel);
				}
				else if (selectAssetType.getSelectedIndex() == CROP_INDEX) {
					DefaultListModel<Crop> cropListModel = new DefaultListModel<>();
					cropListModel.addAll(manager.farm.showCrops());
					assetList.setModel(cropListModel);
				}
				else if (selectAssetType.getSelectedIndex() == ITEM_INDEX) {
					DefaultListModel<Item> itemListModel = new DefaultListModel<>();
					itemListModel.addAll(manager.farm.showItems());
					assetList.setModel(itemListModel);
				}
			}
		});
		selectAssetType.setSelectedIndex(0);

		JTextField txtPlayerMoney = new JTextField();
		txtPlayerMoney.setHorizontalAlignment(SwingConstants.CENTER);
		txtPlayerMoney.setText("$: 0");
		txtPlayerMoney.setEditable(false);
		txtPlayerMoney.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPlayerMoney.setBounds(22, 22, 325, 44);
		window.getContentPane().add(txtPlayerMoney);
		txtPlayerMoney.setColumns(10);

		String storeAssetCategories[] = {
				"Browse animals in store",
				"Browse crops in store",
				"Browse items in store"};

		JComboBox selectAisle = new JComboBox(storeAssetCategories);
		selectAisle.setBounds(380, 22, 394, 33);
		window.getContentPane().add(selectAisle);

		JList aisleAssetsList = new JList();
		aisleAssetsList.setBounds(380, 66, 394, 270);
		window.getContentPane().add(aisleAssetsList);

		// Update the list of animals/crops/items
		// when a different asset category is selected
		selectAisle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultListModel<String> assetModel = new DefaultListModel();
				if (selectAisle.getSelectedIndex() == ANIMAL_INDEX) {
					for (Animal animal : animals) {
						float storePrice = getStorePrice(animal.getPurchasePrice(), animal.getName());
						assetModel.addElement(
								animal.name + " - $" + storePrice
						);
					}
				}
				else if (selectAisle.getSelectedIndex() == CROP_INDEX) {
					for (Crop crop : crops) {
						float storePrice = getStorePrice(crop.getPurchasePrice(), crop.getName());
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
		lblFarmBenefit.setBounds(22, 517, 638, 33);
		lblFarmBenefit.setWrapStyleWord(true);
		lblFarmBenefit.setEditable(false);
		lblFarmBenefit.setBackground(window.getBackground());
		window.getContentPane().add(lblFarmBenefit);

		if (manager.farm instanceof AnimalFarm) {
			lblFarmBenefit.setText("Your farm is an " + manager.farm.getType() + "!");
		} else if (manager.farm instanceof MoomooFarm) {
			lblFarmBenefit.setText("Since your farm is a " + manager.farm.getType() +
					", cows have been set to be 20% cheaper!");
		} else if (manager.farm instanceof TomaccoLand) {
			lblFarmBenefit.setText("Your farm is a " + manager.farm.getType() + "!");
		} else if (manager.farm instanceof TrumpRanch) {
			lblFarmBenefit.setText("Since your farm is a " + manager.farm.getType() +
					", everything has become 10% cheaper!");
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
					}
				} catch (IllegalArgumentException err) {
					// not enough money
					JOptionPane.showMessageDialog(window,
							"Cannot complete purchase: not enough money!"
					);
				} catch (IndexOutOfBoundsException err) {
					JOptionPane.showMessageDialog(window,
							"Oops, please select something first!"
					);
				}
			}
		});
	}
}
