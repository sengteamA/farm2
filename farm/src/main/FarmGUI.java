package main;

import java.awt.EventQueue;
import main.farms.*;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class FarmGUI {

	private JFrame frame;
	private JTextField txtDays;
	private JTextField textField;
	private JTextField txtMoney;
	private JTextField txtFarmerName;
	private JTextField txtCapacity;
	private Farm farm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FarmGUI window = new FarmGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FarmGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		
		txtDays = new JTextField();
		txtDays.setHorizontalAlignment(SwingConstants.CENTER);
		txtDays.setText("Farm name");
		txtDays.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtDays.setBounds(58, 27, 159, 52);
		frame.getContentPane().add(txtDays);
		txtDays.setColumns(10);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("Days");
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setColumns(10);
		textField.setBounds(58, 89, 159, 52);
		frame.getContentPane().add(textField);
		
		JButton btnNewButton = new JButton("View animals");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(56, 312, 160, 61);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnDailyAction = new JButton("Daily Action");
		btnDailyAction.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDailyAction.setBounds(57, 230, 160, 61);
		frame.getContentPane().add(btnDailyAction);
		
		JButton btnViewCrops = new JButton("View crops");
		btnViewCrops.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnViewCrops.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnViewCrops.setBounds(56, 393, 160, 61);
		frame.getContentPane().add(btnViewCrops);
		
		JButton btnViewItems = new JButton("View items");
		btnViewItems.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnViewItems.setBounds(57, 475, 160, 61);
		frame.getContentPane().add(btnViewItems);
		
		txtMoney = new JTextField();
		txtMoney.setText("$ Money");
		txtMoney.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtMoney.setHorizontalAlignment(SwingConstants.CENTER);
		txtMoney.setBounds(542, 43, 169, 64);
		frame.getContentPane().add(txtMoney);
		txtMoney.setColumns(10);
		
		txtFarmerName = new JTextField();
		txtFarmerName.setText("Farmer name");
		txtFarmerName.setHorizontalAlignment(SwingConstants.CENTER);
		txtFarmerName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtFarmerName.setColumns(10);
		txtFarmerName.setBounds(552, 134, 159, 52);
		frame.getContentPane().add(txtFarmerName);
		
		txtCapacity = new JTextField();
		txtCapacity.setText("Capacity");
		txtCapacity.setHorizontalAlignment(SwingConstants.CENTER);
		txtCapacity.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtCapacity.setColumns(10);
		txtCapacity.setBounds(58, 151, 159, 52);
		frame.getContentPane().add(txtCapacity);
	}
}
