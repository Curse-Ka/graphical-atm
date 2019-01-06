package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.ViewManager;

@SuppressWarnings("serial")
public class HomeView extends JPanel implements ActionListener {
	
	private ViewManager manager;		// manages interactions between the views, model, and database
	private JLabel Welcome;
	private JLabel CurrentBalance;
	private JButton Deposit;
	private JButton Withdraw;
	private JButton Transfer;
	private JButton Information;
	private JButton Close;
	private JButton LogOut;
	private model.BankAccount account;
	private JLabel errorMessageLabel;		// label for potential error messages
	
	private data.Database db = new data.Database();

		/**
	 * Constructs an instance (or objects) of the HomeView class.
	 * 
	 * @param manager
	 */
	
	public HomeView(ViewManager manager) {
		super();
		
		this.manager = manager;
		this.errorMessageLabel = new JLabel("", SwingConstants.CENTER);
		initialize();
	}
		
	///////////////////// INSTANCE METHODS ////////////////////////////////////////////
	
	/**
	* Updates the error message label.
	* 
	* @param errorMessage
	*/
	
	public void updateErrorMessage(String errorMessage) {
		errorMessageLabel.setText(errorMessage);
	}
	
	///////////////////// PRIVATE METHODS /////////////////////////////////////////////
	
	/*
	 * Initializes the HomeView components.
	 */
	
	private void initialize() {
		this.setLayout(null);
		
		initWelcome();
		initCurrentBalance();
		initDepositButton();
		initWithdrawalButton();
		initTransferButton();
		initInformationButton();
		initCloseAccountButton();
		initLogOutButton();
		initErrorMessageLabel();
	}
	
	private void initWelcome() {
		Welcome =  new JLabel("Welcome to Inva Sterr's Bank, ", SwingConstants.CENTER);
		Welcome.setBounds(50, 40, 400, 35);		
		Welcome.setFont(new Font("DialogInput", Font.PLAIN, 18));
		Welcome.setForeground(Color.BLACK);
		//Welcome.setText("Welcome to the Inva Sterr's Bank, NAME NAME");
		
		this.add(Welcome);
	}

	private void initCurrentBalance() {
		CurrentBalance =  new JLabel("Your Current Balance is $XX,XXX", SwingConstants.CENTER);
		CurrentBalance.setBounds(60, 100, 400, 35);
		CurrentBalance.setFont(new Font("DialogInput", Font.BOLD, 14));
		CurrentBalance.setForeground(Color.BLACK);
		//CurrentBalance.setText("");
		
		this.add(CurrentBalance);
		
	}

	private void initDepositButton() {
		Deposit = new JButton("Deposit Funds");
		Deposit.setBounds(160, 160, 180, 35);
		Deposit.addActionListener(this);
		
		this.add(Deposit);		
	}

	private void initWithdrawalButton() {
		Withdraw = new JButton("Withdraw Funds");
		Withdraw.setBounds(160, 200, 180, 35);
		Withdraw.addActionListener(this);
		
		this.add(Withdraw);		
	}

	private void initTransferButton() {
		Transfer = new JButton("Transfer Funds");
		Transfer.setBounds(160, 240, 180, 35);
		Transfer.addActionListener(this);
		
		this.add(Transfer);		
	}

	private void initInformationButton() {
		Information = new JButton("View/Edit Informaition");
		Information.setBounds(160, 280, 180, 35);
		Information.addActionListener(this);
		
		this.add(Information);		
	}

	private void initCloseAccountButton() {
		Close = new JButton("Close Account");
		Close.setBounds(160, 320, 180, 35);
		Close.addActionListener(this);
		
		this.add(Close);		
	}

	private void initLogOutButton() {
		LogOut = new JButton("Log Out");
		LogOut.setBounds(205, 380, 90, 35);
		LogOut.addActionListener(this);
		
		this.add(LogOut);
	}

	private void initErrorMessageLabel() {
		errorMessageLabel.setBounds(90, 440, 400, 35);
		errorMessageLabel.setFont(new Font("DialogInput", Font.ITALIC, 14));
		errorMessageLabel.setForeground(Color.RED);
		
		this.add(errorMessageLabel);
	}
	
	/*
	 * HomeView is not designed to be serialized, and attempts to serialize will throw an IOException.
	 * 
	 * @param oos
	 * @throws IOException
	 */
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR: The HomeView class is not serializable.");
	}
	
	///////////////////// OVERRIDDEN METHODS //////////////////////////////////////////
	
	/*
	 * Responds to button clicks and other actions performed in the HomeView.
	 * 
	 * @param e
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source.equals(Deposit)) {
			manager.switchTo(ATM.DEPOSIT_VIEW);
		} else if (source.equals(Withdraw)) {
			manager.switchTo(ATM.WITHDRAW_VIEW);
		} else if (source.equals(Transfer)) {
			manager.switchTo(ATM.TRANSFER_VIEW);
		} else if (source.equals(Information)) {
			manager.switchTo(ATM.INFORMATION_VIEW);
		} else if (source.equals(Close)) {
			try {			
				int choice = JOptionPane.showConfirmDialog(
					null,
					"Are you sure you want to close your account?",
					"Close Account",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE
				);
				if (choice == 0) {
					if (db.closeAccount(account)) {
						manager.switchTo(ATM.LOGIN_VIEW);
					} else {
						updateErrorMessage("ERROR: Could not close account.");
					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (source.equals(LogOut)) {
			try {			
				int choice = JOptionPane.showConfirmDialog(
					null,
					"Are you sure you want to log out?",
					"Log Out",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE
				);
				if (choice == 0) {
					manager.switchTo(ATM.LOGIN_VIEW);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}