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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ViewManager;

@SuppressWarnings("serial")
public class WithdrawView extends JPanel implements ActionListener {
	
	private ViewManager manager;		// manages interactions between the views, model, and database
	private JTextField WithdrawAmount;
	private JButton Confirm;
	private model.BankAccount account;
	private JLabel errorMessageLabel;
	private JButton backButton;
	
	public WithdrawView(ViewManager manager) {
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
	
	public void setBankAccount(model.BankAccount account) {
		this.account = account;
	}
	
	///////////////////// PRIVATE METHODS /////////////////////////////////////////////
	
	/*
	* Initializes the WithdrawView components.
	*/
	
	private void initialize() {
		this.setLayout(null);
		
		initWithdrawAmountField();
		initConfirmButton();
		initBackButton();
		initErrorMessageLabel();
	}
	
	private void initWithdrawAmountField() {
		JLabel label = new JLabel("Withdraw Amount: $", SwingConstants.RIGHT);
		label.setBounds(40, 100, 155, 35);
		label.setLabelFor(WithdrawAmount);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		WithdrawAmount = new JTextField(20);
		WithdrawAmount.setBounds(205, 100, 200, 35);
		
		this.add(label);
		this.add(WithdrawAmount);
		
	}

	private void initConfirmButton() {
		Confirm = new JButton("Confirm");
		Confirm.setBounds(205, 140, 90, 35);
		Confirm.addActionListener(this);
		
		this.add(Confirm);	
	}
	
	private void initBackButton() {
		backButton = new JButton("Back");
		backButton.setBounds(5, 5, 50, 50);
		backButton.addActionListener(this);		
		
		this.add(backButton);
	}
	
	private void initErrorMessageLabel() {
		errorMessageLabel.setBounds(25, 200, 450, 35);
		errorMessageLabel.setFont(new Font("DialogInput", Font.ITALIC, 14));
		errorMessageLabel.setForeground(Color.RED);
		
		this.add(errorMessageLabel);
	}
	
	/*
	 * WithdrawView is not designed to be serialized, and attempts to serialize will throw an IOException.
	 * 
	 * @param oos
	 * @throws IOException
	 */
	

	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR: The CreateView class is not serializable.");
	}
	
	///////////////////// OVERRIDDEN METHODS //////////////////////////////////////////
	
	/*
	 * Responds to button clicks and other actions performed in the WithdrawView.
	 * 
	 * @param e
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source.equals(Confirm)) {
			if (!WithdrawAmount.getText().equals("") && checkContents(WithdrawAmount.getText()) 
					&& Double.parseDouble(WithdrawAmount.getText()) >= 0.01 
					&& Double.parseDouble(WithdrawAmount.getText()) <= account.getBalance()) {
				try {			
					int choice = JOptionPane.showConfirmDialog(
						null,
						"Are you sure you want to withdraw $" + WithdrawAmount.getText() + "?",
						"Exit",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE
					);
					if (choice == 0) {
						account.withdraw(Double.parseDouble(WithdrawAmount.getText()));
						if (manager.updateAccount(account)) {
							WithdrawAmount.setText("");
							manager.sendBankAccount(account, "Home");
							manager.switchTo(ATM.HOME_VIEW);
							updateErrorMessage("");
						} else {
							updateErrorMessage("ERROR: Could not withdraw");
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}				
			} else if (Double.parseDouble(WithdrawAmount.getText()) >= account.getBalance()) {
				updateErrorMessage("Be careful of overdrawing. Account has $" + Math.round(account.getBalance() * 100) / 100); 
			} else {
				updateErrorMessage("Please withdraw at least $0.01 in the correct format (##.##)"); 
			}
		} else if (source.equals(backButton)) {
			if (!WithdrawAmount.getText().equals("")) {
				try {			
					int choice = JOptionPane.showConfirmDialog(
						null,
						"Are you sure you want to cancel withdraw and exit to home?",
						"Exit",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE
					);
					if (choice == 0) {
						WithdrawAmount.setText("");
						updateErrorMessage("");
						manager.switchTo(ATM.HOME_VIEW);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else {
				updateErrorMessage("");
				manager.switchTo(ATM.HOME_VIEW);
			}
		} else {
			System.err.println("ERROR: Action command not found (" + e.getActionCommand() + ")");
		}
	}
	
	/**
	 * Checks for certain format
	 * 
	 * @param string, checking for characters?
	 * @return true if format is correct; false otherwise.
	 */
	
	private boolean checkContents(String string) {
		boolean format = true;
		for (int i = 0; i < string.length(); i++) {
			char tempChar = string.charAt(i);
			if (!(tempChar >= '0' && tempChar <= '9' || tempChar == '.')) {
					format = false;
			}
			if (tempChar == '.' && (string.length() - i) > 3) {
				format = false;
			}
		}
		return format;
	}
}