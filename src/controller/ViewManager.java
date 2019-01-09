package controller;

import java.awt.CardLayout;
import java.awt.Container;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import data.Database;
import model.BankAccount;
import view.ATM;
import view.HomeView;
import view.LoginView;

public class ViewManager {
	
	private Container views;				// the collection of all views in the application
	private Database db;					// a reference to the database
	private BankAccount account;			// the user's bank account
	private BankAccount destination;		// an account to which the user can transfer funds
	
	/**
	 * Constructs an instance (or object) of the ViewManager class.
	 * 
	 * @param layout
	 * @param container
	 */
	
	public ViewManager(Container views) {
		this.views = views;
		this.db = new Database();
	}
	
	///////////////////// INSTANCE METHODS ////////////////////////////////////////////
	
	/**
	 * Routes a login request from the LoginView to the Database.
	 * 
	 * @param accountNumber
	 * @param pin
	 */
	
	public void login(String accountNumber, char[] pin) {
		// System.out.println(db.getAccount(Long.parseLong(accountNumber)).getUser().getPin()); // for when I forget
		account = db.getAccount(Long.valueOf(accountNumber), Integer.valueOf(new String(pin)));
		
		if (account == null) {
			LoginView lv = ((LoginView) views.getComponents()[ATM.LOGIN_VIEW_INDEX]);
			lv.updateErrorMessage("Invalid account number and/or PIN.");
		} else {
			LoginView lv = ((LoginView) views.getComponents()[ATM.LOGIN_VIEW_INDEX]);
			sendBankAccount(account, "Home");
			switchTo(ATM.HOME_VIEW);
			lv.updateErrorMessage("");
		}
	}
	
	public void sendBankAccount(BankAccount account, String view) {
		switch (view) {
		case "Home":
			view.HomeView hv = ((view.HomeView) views.getComponents()[ATM.HOME_VIEW_INDEX]);
			hv.setBankAccount(account);
			break;
		case "Deposit":
			view.DepositView dv = ((view.DepositView) views.getComponents()[ATM.DEPOSIT_VIEW_INDEX]);
			dv.setBankAccount(account);
		case "Withdraw":
			view.WithdrawView wv = ((view.WithdrawView) views.getComponents()[ATM.WITHDRAW_VIEW_INDEX]);
			wv.setBankAccount(account);
			break;
		case "Transfer":
			view.TransferView tv = ((view.TransferView) views.getComponents()[ATM.TRANSFER_VIEW_INDEX]);
			tv.setBankAccount(account);
			break;
		case "Information":
			view.InformationView iv = ((view.InformationView) views.getComponents()[ATM.INFORMATION_VIEW_INDEX]);
			iv.setBankAccount(account);
			break;
		}
	}
	
	public boolean updateAccount(BankAccount account) {
		boolean result = db.updateAccount(account);
		return result;
	}
	
	public boolean closeAccount(BankAccount account) {
		return db.closeAccount(account);
	}
	
	public boolean reopenAccount(BankAccount account) {
		return db.reopenAccount(account);
	}
	
	public BankAccount getAccount(long accountNumber) {
		return db.getAccount(accountNumber);
	}
	
	
	/**
	 * Switches the active (or visible) view upon request.
	 * 
	 * @param view
	 */
	
	public void switchTo(String view) {
		((CardLayout) views.getLayout()).show(views, view);
		
		if (view.equals("LOGIN_VIEW")) {
			account = null;
		}
	}
	
	/**
	 * Routes a shutdown request to the database before exiting the application. This
	 * allows the database to clean up any open resources it used.
	 */
	
	public void shutdown() {
		try {			
			int choice = JOptionPane.showConfirmDialog(
				views,
				"Are you sure?",
				"Shutdown ATM",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE
			);
			
			if (choice == 0) {
				db.shutdown();
				System.exit(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
