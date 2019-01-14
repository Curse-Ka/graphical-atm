package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ViewManager;

@SuppressWarnings("serial")
public class InformationView extends JPanel implements ActionListener {
	
	private ViewManager manager;		// manages interactions between the views, model, and database
	private JTextField AccountNumber;
	private JTextField FirstName;
	private JTextField LastName;
	private JTextField DOB;
	private JTextField PhoneNumber_A;
	private JTextField PhoneNumber_B;
	private JTextField PhoneNumber_C;
	private JTextField Address;
	private JTextField City;
	private JTextField StateUneditable;
	private JComboBox<String> StateEditable;
	private JTextField PostalCode;
	private JPasswordField Pin; // have to ask about this
	private JButton Edit;
	private JButton Save;
	private JButton Cancel;
	private JLabel errorMessageLabel;
	private model.BankAccount account;
	private boolean editable;
	private JButton backButton;
	
	public InformationView(ViewManager manager) {
		super();
		
		this.manager = manager;
		this.errorMessageLabel = new JLabel("", SwingConstants.CENTER);
		
		editable = false;
		
		initialize();
		setEditable();
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
		setFieldInformation();
	}
	
	///////////////////// PRIVATE METHODS /////////////////////////////////////////////
	
	/*
	* Initializes the InformationView components.
	*/
	
	private void initialize() {
		this.setLayout(null);
		
		initBackButton();
		initAccountNumberField();
		initFirstNameField();
		initLastNameField();
		initAddressField();
		initCityField();
		initStateMenu();
		initPostalCodeField();
		initDateOfBirth();
		initPhoneNumField();
		initPinField();
		initEditButton();
		initSaveButton();
		initCancelButton();
		initErrorMessageLabel();
	}
	
	private void setFieldInformation() {
		model.User user = account.getUser();
		
		AccountNumber.setText(Long.toString(account.getAccountNumber()));
		FirstName.setText(user.getFirstName());
		LastName.setText(user.getLastName());
		Address.setText(user.getStreetAddress());
		City.setText(user.getCity());
		StateUneditable.setText(user.getState());
		int i = 0;
		String[] states = {"State", "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};
		do { i++; } while (!states[i].equals(user.getState()));
		StateEditable.setSelectedIndex(i);
		PostalCode.setText(user.getZip());
		DOB.setText(user.getFormattedDob());
		PhoneNumber_A.setText(Long.toString(user.getPhone()).substring(0, 3));
		PhoneNumber_B.setText(Long.toString(user.getPhone()).substring(3, 6));
		PhoneNumber_C.setText(Long.toString(user.getPhone()).substring(6));
	}
	
	private void setEditable() {
		Address.setEditable(editable);
		City.setEditable(editable);
		StateEditable.setVisible(editable);
		StateUneditable.setEditable(editable);
		StateUneditable.setVisible(!editable);
		PostalCode.setEditable(editable);
		PhoneNumber_A.setEditable(editable);
		PhoneNumber_B.setEditable(editable);
		PhoneNumber_C.setEditable(editable);
		Pin.setEditable(editable);
		Edit.setVisible(!editable);
		Save.setVisible(editable);
		Cancel.setVisible(editable);
	}
	
	private void initAccountNumberField() {
		JLabel label = new JLabel("Account Number", SwingConstants.RIGHT);
		label.setBounds(60, 20, 135, 35);
		label.setLabelFor(AccountNumber);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		AccountNumber = new JTextField(20);
		AccountNumber.setBounds(205, 20, 200, 35);
		AccountNumber.setEditable(false);
		
		this.add(label);
		this.add(AccountNumber);		
	}
	
	private void initFirstNameField() {
		JLabel label = new JLabel("First Name", SwingConstants.RIGHT);
		label.setBounds(100, 60, 95, 35);
		label.setLabelFor(FirstName);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		FirstName = new JTextField(20);
		FirstName.setBounds(205, 60, 200, 35);
		FirstName.setEditable(false);
		
		this.add(label);
		this.add(FirstName);
		
	}

	private void initLastNameField() {
		JLabel label = new JLabel("Last Name", SwingConstants.RIGHT);
		label.setBounds(100, 100, 95, 35);
		label.setLabelFor(LastName);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		LastName = new JTextField(20);
		LastName.setBounds(205, 100, 200, 35);
		LastName.setEditable(false);
		
		this.add(label);
		this.add(LastName);
	}

	private void initAddressField() {
		JLabel label = new JLabel("Address", SwingConstants.RIGHT);
		label.setBounds(100, 140, 95, 35);
		label.setLabelFor(Address);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		Address = new JTextField(20);
		Address.setBounds(205, 140, 200, 35);
		
		this.add(label);
		this.add(Address);		
	}

	private void initCityField() {
		JLabel label = new JLabel("City", SwingConstants.RIGHT);
		label.setBounds(100, 180, 95, 35);
		label.setLabelFor(City);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		City = new JTextField(20);
		City.setBounds(205, 180, 200, 35);
		
		this.add(label);
		this.add(City);		
	}

	private void initStateMenu() {
		JLabel label = new JLabel("State", SwingConstants.RIGHT);
		label.setBounds(100, 220, 95, 35);
		label.setLabelFor(StateEditable);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		String[] states = {"State", "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};
		
	    StateEditable = new JComboBox<>(states);
	    StateEditable.setEditable(false);
	    StateEditable.setBounds(205, 220, 200, 35);
	    
	    StateUneditable = new JTextField(20);
	    StateUneditable.setBounds(205, 220, 200, 35);
	    		
		this.add(label);
		this.add(StateEditable);
		this.add(StateUneditable);
	}

	private void initPostalCodeField() {
		JLabel label = new JLabel("Postal Code", SwingConstants.RIGHT);
		label.setBounds(100, 260, 95, 35);
		label.setLabelFor(PostalCode);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		PostalCode = new JTextField(20);
		PostalCode.setBounds(205, 260, 200, 35);
		
		this.add(label);
		this.add(PostalCode);		
	}
	
	private void initDateOfBirth() {
		JLabel label = new JLabel("Date of Birth", SwingConstants.RIGHT);
		label.setBounds(65, 300, 130, 35);
		label.setLabelFor(DOB);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		DOB = new JTextField(20);
		DOB.setBounds(205, 300, 200, 35);
		DOB.setEditable(false);
		
		this.add(label);
		this.add(DOB);
	}
	
	private void initPhoneNumField() {
		JLabel label = new JLabel("Phone Number", SwingConstants.RIGHT);
		label.setBounds(65, 340, 130, 35);
		label.setLabelFor(PhoneNumber_A);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		PhoneNumber_A = new JTextField(20);
		PhoneNumber_A.setBounds(205, 340, 60, 35);
		
		PhoneNumber_B = new JTextField(20);
		PhoneNumber_B.setBounds(265, 340, 60, 35);
		
		PhoneNumber_C = new JTextField(20);
		PhoneNumber_C.setBounds(325, 340, 80, 35);
		
		this.add(label);
		this.add(PhoneNumber_A);
		this.add(PhoneNumber_B);
		this.add(PhoneNumber_C);		
	}
	
	private void initPinField() {
		JLabel label = new JLabel("Pin", SwingConstants.RIGHT);
		label.setBounds(100, 380, 95, 35);
		label.setLabelFor(Pin);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		Pin = new JPasswordField(20);
		Pin.setBounds(205, 380, 200, 35);
		
		this.add(label);
		this.add(Pin);
	}
	
	private void initEditButton() {
		Edit = new JButton("Edit");
		Edit.setBounds(205, 420, 90, 35);
		Edit.addActionListener(this);
		
		this.add(Edit);	
	}
	
	private void initSaveButton() {
		Save = new JButton("Save");
		Save.setBounds(205, 420, 90, 35);
		Save.addActionListener(this);
		
		this.add(Save);	
	}
	
	private void initCancelButton() {
		Cancel = new JButton("Cancel");
		Cancel.setBounds(300, 420, 90, 35);
		Cancel.addActionListener(this);
		
		this.add(Cancel);
	}
	
	private void initBackButton() {
		backButton = new JButton("Back");
		backButton.setBounds(5, 5, 50, 50);
		backButton.addActionListener(this);		
		
		this.add(backButton);		
	}

	private void initErrorMessageLabel() {
		errorMessageLabel.setBounds(90, 450, 400, 35);
		errorMessageLabel.setFont(new Font("DialogInput", Font.ITALIC, 14));
		errorMessageLabel.setForeground(Color.RED);
		
		this.add(errorMessageLabel);
	}
	
	/*
	 * InformationView is not designed to be serialized, and attempts to serialize will throw an IOException.
	 * 
	 * @param oos
	 * @throws IOException
	 */
	

	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR: The CreateView class is not serializable.");
	}
	
	///////////////////// OVERRIDDEN METHODS //////////////////////////////////////////
	
	/*
	 * Responds to button clicks and other actions performed in the InformationView.
	 * 
	 * @param e
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source.equals(Edit)) {
			editable = true;
			setEditable();
		} else if (source.equals(Cancel)) {
			try {			
				int choice = JOptionPane.showConfirmDialog(
					null,
					"Are you sure you want to cancel edits?",
					"Cancel Edits",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE
				);
				if (choice == 0) {
					editable = false;
					setEditable();
					setFieldInformation();
					updateErrorMessage("");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (source.equals(Save)) {
			String errorMsg = "";
			errorMsg = (new String(Pin.getPassword()).length() != 4 && new String(Pin.getPassword()).length() != 0 || !checkContents(new String(Pin.getPassword()), false)) ? "Please enter pin in correct format" : errorMsg;
			errorMsg = (PhoneNumber_A.getText().length() != 3 || PhoneNumber_B.getText().length() != 3 || PhoneNumber_C.getText().length() != 4
					|| !checkContents(PhoneNumber_A.getText(), false) || !checkContents(PhoneNumber_B.getText(), false) || !checkContents(PhoneNumber_C.getText(), false)) ? "Please enter phone number in correct format" : errorMsg;
			errorMsg = (PostalCode.getText().length() != 5 || !checkContents(PostalCode.getText(), false)) ? "Please enter postal code in correct format" : errorMsg;
			errorMsg = (StateEditable.getSelectedIndex() == 0) ? "Please enter state in correct format" : errorMsg;
			errorMsg = (City.getText().equals("") || !checkContents(City.getText(), true)) ? "Please enter city in correct format" : errorMsg;
			errorMsg = (Address.getText().equals("")) ? "Please enter address in correct format" : errorMsg;
			errorMsg = (LastName.getText().equals("") || !checkContents(LastName.getText(), true)) ? "Please enter last name in correct format" : errorMsg;
			errorMsg = (FirstName.getText().equals("") || !checkContents(FirstName.getText(), true)) ? "Please enter first name in correct format" : errorMsg;
			
			// Update error message
			updateErrorMessage(errorMsg);
			
			// If the entered data is valid
			if (errorMsg.equals("")) {
				try {			
					int choice = JOptionPane.showConfirmDialog(
						null,
						"Are you sure you want to save edits?",
						"Save Edits",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE
					);
					if (choice == 0) {
						account.getUser().setStreetAddress(Address.getText());
						account.getUser().setCity(City.getText());
						account.getUser().setState(StateEditable.getSelectedItem().toString());
						account.getUser().setPhone(Long.valueOf(PhoneNumber_A.getText() + PhoneNumber_B.getText() + PhoneNumber_C.getText()));
						if (new String(Pin.getPassword()).length() != 0) {
							account.getUser().setPin(account.getUser().getPin(), Integer.valueOf(new String(Pin.getPassword())));
						}
						
						if (manager.updateAccount(account)) {
							editable = false;
							setEditable();
							setFieldInformation();
						} else {
							errorMsg = "ERROR: Unable to update account information";
							updateErrorMessage(errorMsg);
						}
						
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} else if (source.equals(backButton)) {
			if (editable == true) {
				try {			
					int choice = JOptionPane.showConfirmDialog(
						null,
						"Are you sure you want to cancel edits and exit to home?",
						"Exit",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE
					);
					if (choice == 0) {
						manager.sendBankAccount(account, "Home");
						manager.switchTo(ATM.HOME_VIEW);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else {
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
	
	private boolean checkContents(String string, boolean characters) {
		boolean format = true;
		for (int i = 0; i < string.length(); i++) {
			char tempChar = string.charAt(i);
			if (characters) {
				if (!(tempChar >= 'a' && tempChar <= 'z' || tempChar >= 'A' && tempChar <= 'Z' || tempChar == ' ' || tempChar == '.')) {
					format = false;
				}
			} else {
				if (!(tempChar >= '0' && tempChar <= '9')) {
					format = false;
				}
			}
		}
		return format;
	}
}