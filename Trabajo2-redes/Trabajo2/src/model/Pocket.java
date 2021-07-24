package model;
/**
 * 			Universidad del Quindío
 * @author	Michelle Quintero Hernández
 *			José Manuel Rojas Tovar
 *			Brayan Tabares Hidalgo
 */
public class Pocket {

	/**
	 * @return the balance
	 */
	public int getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(int balance) {
		this.balance = balance;
	}

	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	private String accountNumber;
	private int balance;
	
	/**
	 * @param accountNumber
	 * @param balance
	 */
	public Pocket(short accountNumber) {
		this.accountNumber = "b" + accountNumber;
		this.balance = 0;
	}
	
}
