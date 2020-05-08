package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class GameSetupWindow {

	private JFrame frmSetUpGame;
	private JTextField txtJohn;
	private JTextField textField_1;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					GameSetupWindow window = new GameSetupWindow();
					window.frmSetUpGame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameSetupWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSetUpGame = new JFrame();
		frmSetUpGame.setTitle("Set up game");
		frmSetUpGame.setBounds(100, 100, 450, 348);
		frmSetUpGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblGameDuration = new JLabel("Game duration:");

		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(8, 5, 10, 1));

		JLabel lblWhatIsYour = new JLabel("Your name:");

		JLabel lblYourAge = new JLabel("Your age:");

		txtJohn = new JTextField();
		txtJohn.setText("John");
		txtJohn.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setText("47");
		textField_1.setColumns(10);

		JLabel lblSelectAFarm = new JLabel("Select a farm:");

		JComboBox comboBox = new JComboBox();

		JPanel panel = new JPanel();
		panel.setFont(new Font("Dialog", Font.PLAIN, 10));
		panel.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)), "Farm information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel.setToolTipText("");

		JLabel lblSetAName = new JLabel("Name your farm:");

		textField = new JTextField();
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Start!");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("blep");
			}
		});

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GroupLayout groupLayout = new GroupLayout(frmSetUpGame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblGameDuration)
							.addPreferredGap(ComponentPlacement.RELATED, 262, Short.MAX_VALUE)
							.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblWhatIsYour)
							.addPreferredGap(ComponentPlacement.RELATED, 222, Short.MAX_VALUE)
							.addComponent(txtJohn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblYourAge)
								.addComponent(lblSelectAFarm))
							.addPreferredGap(ComponentPlacement.RELATED, 204, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(textField_1)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSetAName)
							.addPreferredGap(ComponentPlacement.RELATED, 185, Short.MAX_VALUE)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNewButton))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGameDuration)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWhatIsYour)
						.addComponent(txtJohn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblYourAge)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelectAFarm)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSetAName)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblAnyErrorsWill = new JLabel("Any errors will go here");
		lblAnyErrorsWill.setForeground(Color.RED);
		lblAnyErrorsWill.setFont(new Font("Dialog", Font.PLAIN, 12));
		panel_1.add(lblAnyErrorsWill);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("250px"),
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.DEFAULT_ROWSPEC,
				RowSpec.decode("default:grow"),}));

		JLabel lblSelectedFarm = new JLabel("Something Farm");
		lblSelectedFarm.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblSelectedFarm, "2, 1, center, center");

		JLabel lblFlavourText = new JLabel("Flavour text here Moo mooo Moooo");
		lblFlavourText.setVerticalAlignment(SwingConstants.TOP);
		lblFlavourText.setFont(new Font("Dialog", Font.PLAIN, 12));
		panel.add(lblFlavourText, "1, 2, 3, 1, left, center");
		frmSetUpGame.getContentPane().setLayout(groupLayout);
	}
}
