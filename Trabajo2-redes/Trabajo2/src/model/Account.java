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
	private Pocket pocket;
	
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
	 * @param pocket the pocket to set
	 */
	public void setPocket(Pocket pocket) {
		this.pocket = pocket;
	}

	/**
	 * @return the pocket
	 */
	public Pocket getAccountPocket() {
		return pocket;
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
