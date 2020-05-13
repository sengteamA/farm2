package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;

public class StoreGUI {

	private JFrame frame;
	private JTextField txtPlayerInventory;
	private JTextField txtChemicalSpray;
	private JTextField textField;
	private JTextField txtCompost;
	private JTextField txtInstantgrolite;
	private JTextField txtInstantgrowPro;
	private JTextField txtStockfeed;
	private JTextField txtPandaGummy;
	private JButton btnLeave;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StoreGUI window = new StoreGUI();
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
	public StoreGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 608, 517);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Buy items");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(388, 31, 161, 55);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnBuyCrops = new JButton("Buy crops");
		btnBuyCrops.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBuyCrops.setBounds(388, 112, 161, 55);
		frame.getContentPane().add(btnBuyCrops);
		
		JButton btnBuyAnimals = new JButton("Buy animals");
		btnBuyAnimals.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBuyAnimals.setBounds(388, 189, 161, 55);
		frame.getContentPane().add(btnBuyAnimals);
		
		txtPlayerInventory = new JTextField();
		txtPlayerInventory.setHorizontalAlignment(SwingConstants.CENTER);
		txtPlayerInventory.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPlayerInventory.setText("Player inventory");
		txtPlayerInventory.setBounds(46, 80, 217, 42);
		frame.getContentPane().add(txtPlayerInventory);
		txtPlayerInventory.setColumns(10);
		
		txtChemicalSpray = new JTextField();
		txtChemicalSpray.setText("Chemical Spray");
		txtChemicalSpray.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtChemicalSpray.setBounds(46, 132, 161, 35);
		frame.getContentPane().add(txtChemicalSpray);
		txtChemicalSpray.setColumns(10);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setText("$");
		textField.setBounds(46, 28, 169, 42);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		txtCompost = new JTextField();
		txtCompost.setText("Compost");
		txtCompost.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCompost.setColumns(10);
		txtCompost.setBounds(46, 177, 161, 35);
		frame.getContentPane().add(txtCompost);
		
		txtInstantgrolite = new JTextField();
		txtInstantgrolite.setText("Instant-Grow Lite\u00AE");
		txtInstantgrolite.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtInstantgrolite.setColumns(10);
		txtInstantgrolite.setBounds(46, 222, 161, 35);
		frame.getContentPane().add(txtInstantgrolite);
		
		txtInstantgrowPro = new JTextField();
		txtInstantgrowPro.setText("Instant-Grow Pro\u00AE");
		txtInstantgrowPro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtInstantgrowPro.setColumns(10);
		txtInstantgrowPro.setBounds(46, 267, 161, 35);
		frame.getContentPane().add(txtInstantgrowPro);
		
		txtStockfeed = new JTextField();
		txtStockfeed.setText("Stockfeed");
		txtStockfeed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtStockfeed.setColumns(10);
		txtStockfeed.setBounds(46, 312, 161, 35);
		frame.getContentPane().add(txtStockfeed);
		
		txtPandaGummy = new JTextField();
		txtPandaGummy.setText("Panda Gummy");
		txtPandaGummy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPandaGummy.setColumns(10);
		txtPandaGummy.setBounds(46, 357, 161, 35);
		frame.getContentPane().add(txtPandaGummy);
		
		btnLeave = new JButton("Leave");
		btnLeave.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLeave.setBounds(388, 384, 161, 55);
		frame.getContentPane().add(btnLeave);
		
		textField_1 = new JTextField();
		textField_1.setBounds(206, 132, 56, 35);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(206, 177, 56, 35);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(206, 222, 56, 35);
		frame.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(206, 267, 56, 35);
		frame.getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(206, 312, 56, 35);
		frame.getContentPane().add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(206, 357, 56, 35);
		frame.getContentPane().add(textField_6);
	}
}
