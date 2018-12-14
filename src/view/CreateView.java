package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CreateView extends JPanel implements ActionListener {
	
	private ViewManager manager;
	
	/**
	 * Constructs an instance (or object) of the CreateView class.
	 * 
	 * @param manager
	 */
	
	public CreateView(ViewManager manager) {
		super();
		
		this.manager = manager;
		initialize();
	}
	
	///////////////////// PRIVATE METHODS /////////////////////////////////////////////
	
	/*
	 * Initialies the CreateView components.
	 */
	
	private void initialize() {
		
		// you'll need to remove this when you start coding. for now, it's here only to
		// demonstrate that the button to create an account in LoginView.java correctly
		// navigates the user to CreateView.
		
		this.add(new javax.swing.JLabel("CreateView", javax.swing.SwingConstants.CENTER));
		
		// TODO
		//
		// this is where you should build your create account form (i.e., all the
		// components that will allow the user to enter his or her information and
		// create a new account).
		//
		// feel free to use my layout in LoginView.java as an example for laying out
		// and positioning your components.
	}

	///////////////////// OVERRIDDEN METHODS //////////////////////////////////////////
	
	/**
	 * Responds to button clicks and other actions performed in the CreateView.
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// TODO
		//
		// this is where you'll setup your action listener, which is reponsible for
		// responding to actions the user might take in this view (an action can be a
		// user clicking a button, typing in a textfield, etc.).
		//
		// feel free to use my action listener in LoginView.java as an example.
	}
	
}