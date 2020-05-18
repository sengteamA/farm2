package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JComboBox;
import main.animals.*;
import main.items.*;
import main.crops.*;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class StoreGUI {

	private JFrame frmStore;
	private GameManager manager;
	private JButton btnLeave;
	private JTextField txtPlayerCash;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public StoreGUI(GameManager myManager) {
		manager = myManager;
		initialize();
		frmStore.setVisible(true);
	}
	
	public void closeWindow() {
		frmStore.dispose();
	}
	
	public void closeFinishedScreen() {
		manager.closeStoreScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmStore = new JFrame();
		frmStore.setTitle("Store");
		frmStore.setBounds(100, 100, 703, 410);
		frmStore.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStore.getContentPane().setLayout(null);
		
		btnLeave = new JButton("Leave");
		btnLeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeFinishedScreen();
			}
		});
		btnLeave.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLeave.setBounds(426, 294, 161, 55);
		frmStore.getContentPane().add(btnLeave);
		
		JList assetList = new JList();
		assetList.setBounds(22, 167, 200, 182);
		frmStore.getContentPane().add(assetList);
		
		String assets[] = {"", "Animals", "Crops", "Items"};
		
		JComboBox assetComboBox = new JComboBox(assets);
		assetComboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		assetComboBox.setBounds(22, 124, 200, 33);
		frmStore.getContentPane().add(assetComboBox);
		
		JButton viewAssets = new JButton("View Assets");
		viewAssets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (assetComboBox.getSelectedItem().equals("Animals")) {
					DefaultListModel<Animal> animalListModel = new DefaultListModel<>();
					animalListModel.addAll(manager.farm.showAnimals());
					assetList.setModel(animalListModel);
				}
				else if (assetComboBox.getSelectedItem().equals("Crops")) {
					DefaultListModel<Crop> cropListModel = new DefaultListModel<>();
					cropListModel.addAll(manager.farm.showCrops());
					assetList.setModel(cropListModel);
				}
				else if (assetComboBox.getSelectedItem().equals("Items")) {
					DefaultListModel<Item> itemListModel = new DefaultListModel<>();
					itemListModel.addAll(manager.farm.showItems());
					assetList.setModel(itemListModel);
				}
				else {
					DefaultListModel<String> placeHolder = new DefaultListModel<String>();
					assetList.setModel(placeHolder);
				}
			}
		});
		viewAssets.setFont(new Font("Tahoma", Font.BOLD, 15));
		viewAssets.setBounds(22, 76, 200, 42);
		frmStore.getContentPane().add(viewAssets);
		
		String items[] = {"", "Chemical Spray", "Compost", "Instant-Grow Lite(R)", "Instant-Grow Pro(R)", "Panda Gummy", "Stockfeed"};
		String animals[] = {"", "Cow", "Fox", "Sheep"};
		String crops[] = {"", "Carrot", "Hipotke", "Mushroom", "Tomacco", "Wasabi", "Wheat"};
		
		
		txtPlayerCash = new JTextField();
		txtPlayerCash.setHorizontalAlignment(SwingConstants.CENTER);
		txtPlayerCash.setText("$: 0");
		txtPlayerCash.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPlayerCash.setBounds(22, 22, 200, 44);
		frmStore.getContentPane().add(txtPlayerCash);
		txtPlayerCash.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Item Description", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(251, 155, 336, 117);
		frmStore.getContentPane().add(panel);
		
		
	}
}
