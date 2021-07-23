package model;
/**
 * 			Universidad del Quindío
 * @author	Michelle Quintero Hernández
 *			José Manuel Rojas Tovar
 *			Brayan Tabares Hidalgo
 */
public class Account {

	private short accountNumber;
	private String name;
	private int balance;
	
	/**
	 * Construct
	 */
	public Account(short accountNumber, String name) {
		this.accountNumber = accountNumber;
		this.name = name;
		balance = 0;
	}

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
	public short getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	
}
