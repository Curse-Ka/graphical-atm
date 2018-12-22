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
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ViewManager;

@SuppressWarnings("serial")
public class CreateView extends JPanel implements ActionListener {
	
	private ViewManager manager;		// manages interactions between the views, model, and database
	private JTextField FirstName;
	private JTextField LastName;
	private JComboBox<String> DOB_Month;
	private JComboBox<String> DOB_Day;
	private JComboBox<String> DOB_Year;
	private JTextField PhoneNumber_A;
	private JTextField PhoneNumber_B;
	private JTextField PhoneNumber_C;
	private JTextField Address;
	private JTextField City;
	private JComboBox<String> State;
	private JTextField PostalCode;
	private JPasswordField Pin;
	private JButton Create;
	private JButton Cancel;
	private JLabel errorMessageLabel;
	/**
	 * Constructs an instance (or object) of the CreateView class.
	 * 
	 * @param manager
	 */
	
	public CreateView(ViewManager manager) {
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
	 * Initializes the CreateView components.
	 */
	
	private void initialize() {
		this.setLayout(null);
		
		initFirstNameField();
		initLastNameField();
		initDateOfBirth();
		initPhoneNumField(); // FIXXXXXXXX
		initAddressField();
		initCityField();
		initStateMenu();
		initPostalCodeField();
		initPinField();
		initCreateButton();
		initCancelButton();
		initErrorMessageLabel();		
	}

	private void initFirstNameField() {
		JLabel label = new JLabel("First Name", SwingConstants.RIGHT);
		label.setBounds(100, 40, 95, 35);
		label.setLabelFor(FirstName);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		FirstName = new JTextField(20);
		FirstName.setBounds(205, 40, 200, 35);
		
		this.add(label);
		this.add(FirstName);
		
	}

	private void initLastNameField() {
		JLabel label = new JLabel("Last Name", SwingConstants.RIGHT);
		label.setBounds(100, 80, 95, 35);
		label.setLabelFor(LastName);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		LastName = new JTextField(20);
		LastName.setBounds(205, 80, 200, 35);
		
		this.add(label);
		this.add(LastName);
		
	}

	private void initDateOfBirth() {
		JLabel label = new JLabel("Date of Birth", SwingConstants.RIGHT);
		label.setBounds(65, 120, 130, 35);
		label.setLabelFor(DOB_Month);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		String[] months = { "MM", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};
		String[] days = { "DD", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
		String[] years = { "YY", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940"};
		
	    DOB_Month = new JComboBox<>(months);
	    DOB_Month.setBounds(205, 120, 75, 35);
	    DOB_Month.setVisible(true);
	    DOB_Month.setEditable(false);
	    
	    DOB_Day = new JComboBox<>(days);
	    DOB_Day.setBounds(275, 120, 70, 35);
	    DOB_Day.setVisible(true);
	    DOB_Day.setEditable(false);
	    
	    DOB_Year = new JComboBox<>(years);
	    DOB_Year.setBounds(340, 120, 70, 35);
	    DOB_Year.setVisible(true);
	    DOB_Year.setEditable(false);
		
		this.add(label);
		this.add(DOB_Month);
		this.add(DOB_Day);
		this.add(DOB_Year);
		
	}

	private void initPhoneNumField() {
		JLabel label = new JLabel("Phone Number", SwingConstants.RIGHT);
		label.setBounds(65, 160, 130, 35);
		label.setLabelFor(PhoneNumber_A);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		PhoneNumber_A = new JTextField(20);
		PhoneNumber_A.setBounds(205, 160, 60, 35);
	
		PhoneNumber_B = new JTextField(20);
		PhoneNumber_B.setBounds(265, 160, 60, 35);
		
		PhoneNumber_C = new JTextField(20);
		PhoneNumber_C.setBounds(325, 160, 80, 35);
		
		this.add(label);
		this.add(PhoneNumber_A);
		this.add(PhoneNumber_B);
		this.add(PhoneNumber_C);		
	}

	private void initAddressField() {
		JLabel label = new JLabel("Address", SwingConstants.RIGHT);
		label.setBounds(100, 200, 95, 35);
		label.setLabelFor(Address);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		Address = new JTextField(20);
		Address.setBounds(205, 200, 200, 35);
		
		this.add(label);
		this.add(Address);		
	}

	private void initCityField() {
		JLabel label = new JLabel("City", SwingConstants.RIGHT);
		label.setBounds(100, 240, 95, 35);
		label.setLabelFor(City);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		City = new JTextField(20);
		City.setBounds(205, 240, 200, 35);
		
		this.add(label);
		this.add(City);		
	}

	private void initStateMenu() {
		JLabel label = new JLabel("State", SwingConstants.RIGHT);
		label.setBounds(100, 280, 95, 35);
		label.setLabelFor(State);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		String[] states = {"State", "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};
		
	    State = new JComboBox<>(states);
	    State.setVisible(true);
	    State.setEditable(false);
	    State.setBounds(205, 280, 200, 35);
	    		
		this.add(label);
		this.add(State);
	}

	private void initPostalCodeField() {
		JLabel label = new JLabel("Postal Code", SwingConstants.RIGHT);
		label.setBounds(100, 320, 95, 35);
		label.setLabelFor(PostalCode);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		PostalCode = new JTextField(20);
		PostalCode.setBounds(205, 320, 200, 35);
		
		this.add(label);
		this.add(PostalCode);		
	}

	private void initPinField() {
		JLabel label = new JLabel("Pin", SwingConstants.RIGHT);
		label.setBounds(100, 360, 95, 35);
		label.setLabelFor(Pin);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		City = new JPasswordField(20);
		City.setBounds(205, 360, 200, 35);
		
		this.add(label);
		this.add(City);
	}

	private void initCreateButton() {
		Create = new JButton("Create");
		Create.setBounds(205, 400, 90, 35);
		Create.addActionListener(this);
		
		this.add(Create);
	}

	private void initCancelButton() {
		Cancel = new JButton("Cancel");
		Cancel.setBounds(300, 400, 90, 35);
		Cancel.addActionListener(this);
		
		this.add(Cancel);
	}
	
	private void initErrorMessageLabel() {
		errorMessageLabel.setBounds(90, 440, 400, 35);
		errorMessageLabel.setFont(new Font("DialogInput", Font.ITALIC, 14));
		errorMessageLabel.setForeground(Color.RED);
		
		this.add(errorMessageLabel);
	}

	/*
	 * CreateView is not designed to be serialized, and attempts to serialize will throw an IOException.
	 * 
	 * @param oos
	 * @throws IOException
	 */
	

	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR: The CreateView class is not serializable.");
	}
	
	///////////////////// OVERRIDDEN METHODS //////////////////////////////////////////
	
	/*
	 * Responds to button clicks and other actions performed in the CreateView.
	 * 
	 * @param e
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source.equals(Create)) {
			// Check for empty values or incorrect formats NEED TO CHECK NUMS VS CHARS LATER TOO
			String errorMsg = "";
			errorMsg = (FirstName.getText().equals("") /* || length*/) ? "Please enter first name in correct format" : errorMsg;
			errorMsg = (LastName.getText().equals("") /* || length*/) ? "Please enter last name in correct format" : errorMsg;
			errorMsg = (DOB_Month.getSelectedIndex() == 0 || DOB_Day.getSelectedIndex() == 0 || DOB_Year.getSelectedIndex() == 0) ? "Please enter date of birth in correct format" : errorMsg;
			errorMsg = (PhoneNumber_A.getText().length() != 3 || PhoneNumber_B.getText().length() != 3 || PhoneNumber_C.getText().length() != 4) ? "Please enter phone number in correct format" : errorMsg;
			errorMsg = (Address.getText().equals("") /* || length*/) ? "Please enter address in correct format" : errorMsg;
			// errorMsg = (City.getText().equals("") /* || length*/) ? "Please enter city in correct format" : errorMsg;
			// THIS DOESN'T WORK FOR SOME REASON
			System.out.println(City.getText());
			errorMsg = (State.getSelectedIndex() == 0 /* || length*/) ? "Please enter state in correct format" : errorMsg;
			errorMsg = (PostalCode.getText().length() != 5) ? "Please enter postal code in correct format" : errorMsg;
			// errorMsg = (Pin.getPassword().equals(null) || Pin.getText().length() != 5) ? "Please enter pin in correct format" : errorMsg; 
			// THIS DOESN"T WORK EITHER ^^
			
			if (errorMsg.equals("")) {
				manager.switchTo(ATM.HOME_VIEW);
			}
			
			updateErrorMessage(errorMsg);
		} else if (source.equals(Cancel)) {
			FirstName.setText("");
			LastName.setText("");
			DOB_Month.setSelectedIndex(0);
			DOB_Day.setSelectedIndex(0);
			DOB_Year.setSelectedIndex(0);
			PhoneNumber_A.setText("");
			PhoneNumber_B.setText("");
			PhoneNumber_C.setText("");
			Address.setText("");
			City.setText(""); //Why doesn't this one work?
			State.setSelectedIndex(0);
			PostalCode.setText("");
			//Pin.setText(""); // This one doesn't work either
			
			manager.switchTo(ATM.LOGIN_VIEW);
		} else {
			System.err.println("ERROR: Action command not found (" + e.getActionCommand() + ")");
		}
		
		// TODO
		//
		// this is where you'll setup your action listener, which is responsible for
		// responding to actions the user might take in this view (an action can be a
		// user clicking a button, typing in a textfield, etc.).
		//
		// feel free to use my action listener in LoginView.java as an example.
	}
}