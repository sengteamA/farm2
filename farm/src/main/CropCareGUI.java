package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;

import javax.swing.SwingConstants;

import main.crops.*;
import main.items.*;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
		plantList.setBounds(230, 57, 192, 201);
		frmCropManagement.getContentPane().add(plantList);
		DefaultListModel<String> cropListModel = new DefaultListModel<>();
		cropListModel.addAll(manager.farm.getCropType());
		plantList.setModel(cropListModel);
		
		JList ItemList = new JList();
		ItemList.setBounds(453, 57, 218, 201);
		frmCropManagement.getContentPane().add(ItemList);
		DefaultListModel<Item> itemListModel = new DefaultListModel<>();
		itemListModel.addAll(manager.farm.getItemType("Crop"));
		ItemList.setModel(itemListModel);
		
		playerPlants = new JTextField();
		playerPlants.setText("Player plants");
		playerPlants.setHorizontalAlignment(SwingConstants.CENTER);
		playerPlants.setFont(new Font("Tahoma", Font.BOLD, 15));
		playerPlants.setBounds(230, 12, 192, 35);
		frmCropManagement.getContentPane().add(playerPlants);
		playerPlants.setColumns(10);
		
		playerItems = new JTextField();
		playerItems.setHorizontalAlignment(SwingConstants.CENTER);
		playerItems.setText("Player Items");
		playerItems.setFont(new Font("Tahoma", Font.BOLD, 15));
		playerItems.setColumns(10);
		playerItems.setBounds(453, 12, 218, 35);
		frmCropManagement.getContentPane().add(playerItems);
		
		String acts[] = {"water plants", "use item"};
		actBox = new JComboBox();
		actBox.setBounds(54, 57, 140, 45);
		frmCropManagement.getContentPane().add(actBox);
		
		btnAction = new JButton("Confirm Action");
		btnAction.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAction.setBounds(54, 112, 140, 45);
		frmCropManagement.getContentPane().add(btnAction);
		
		quitButton = new JButton("Exit");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		quitButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		quitButton.setBounds(54, 212, 140, 45);
		frmCropManagement.getContentPane().add(quitButton);
	}
}
