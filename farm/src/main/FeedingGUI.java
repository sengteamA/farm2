package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import main.items.*;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.util.ListIterator;

public class FeedingGUI {

	private JFrame frame;
	private GameManager manager;
	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the application.
	 */
	public FeedingGUI(GameManager myManager) {
		manager = myManager;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		manager.closeFeedingScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 343, 355);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSelectItem = new JLabel("Select Item");
		lblSelectItem.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSelectItem.setBounds(105, 10, 152, 35);
		frame.getContentPane().add(lblSelectItem);
		
		JList foodList = new JList();
		foodList.setBounds(30, 45, 261, 186);
		foodList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		frame.getContentPane().add(foodList);
		DefaultListModel<Item> itemListModel = new DefaultListModel<>();
		itemListModel.addAll(manager.farm.getItemType("Animal"));
		foodList.setModel(itemListModel);
		
		JButton btnUseItem = new JButton("Use Item");
		btnUseItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//duplicate variable error??
				ListIterator<Item> iterator = manager.farm.showItems().listIterator();
				Item item = null;
				while (iterator.hasNext()) {
					item = iterator.next();
					if (item == foodList.getSelectedValue()) {
						break;
					}
				}
				if (item != null) {
					manager.farmer.feedAnimals(item);
				}
				DefaultListModel<Item> itemListModel = new DefaultListModel<>();
				itemListModel.addAll(manager.farm.getItemType("Animal"));
				foodList.setModel(itemListModel);
				JOptionPane.showMessageDialog(frame, "You're animals grow healthier.");
				//close window after animal is fed
				finishedWindow();
			}
		});
		btnUseItem.setBounds(30, 241, 125, 52);
		frame.getContentPane().add(btnUseItem);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		btnQuit.setBounds(166, 241, 125, 52);
		frame.getContentPane().add(btnQuit);
	}

}
