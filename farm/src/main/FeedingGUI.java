package main;

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
import java.awt.event.ActionEvent;
import java.util.ListIterator;

public class FeedingGUI {
	private JFrame frame;
	private GameManager manager;

	/**
	 * Create the application.
	 * @param myManager game instance to use
	 */
	public FeedingGUI(GameManager myManager) {
		manager = myManager;
		initialise();
		frame.setVisible(true);
	}

	/**
	 * Closes the Feed Animals window. This is used by the game.
	 */
	public void closeWindow() {
		frame.dispose();
	}

	/**
	 * Indicates that FeedingGUI is done with the window, and hands over
	 * control to the game (which in turn runs closeWindow() to close this
	 * window).
	 */
	public void finishedWindow() {
		manager.closeFeedingScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialise() {
		frame = new JFrame();
		frame.setBounds(100, 100, 343, 355);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblSelectItem = new JLabel("Select Item");
		lblSelectItem.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSelectItem.setBounds(105, 10, 152, 35);
		frame.getContentPane().add(lblSelectItem);

		JList<Item> foodList = new JList<Item>();
		foodList.setBounds(30, 45, 261, 186);
		foodList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		frame.getContentPane().add(foodList);
		DefaultListModel<Item> itemListModel = new DefaultListModel<>();
		itemListModel.addAll(manager.farm.getItemType("Animal"));
		foodList.setModel(itemListModel);

		JButton btnUseItem = new JButton("Use Item");
		btnUseItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
				JOptionPane.showMessageDialog(frame, "Your animals grow healthier.");
				// close window after animal is fed
				finishedWindow();
			}
		});
		btnUseItem.setBounds(30, 241, 125, 52);
		frame.getContentPane().add(btnUseItem);

		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		btnQuit.setBounds(166, 241, 125, 52);
		frame.getContentPane().add(btnQuit);
	}
}