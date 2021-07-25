package model;
/**
 * 			Universidad del Quindío
 * @author	Michelle Quintero Hernández
 *			José Manuel Rojas Tovar
 *			Brayan Tabares Hidalgo
 */
import java.util.HashMap;

public class Bank {

	public HashMap<String, Account> accounts = new HashMap<String, Account>();
	
	/**
	 * 
	 * @param name
	 */
	public void createAccount(String name) {
		Account account = new Account(name);
		accounts.put(""+(accounts.size()+1), account);
	}
	/**
	 * 
	 * @param accountNumber
	 */
	public void createPocket(String accountNumber) {
		Pocket pocket = new Pocket(accountNumber);
		accounts.get(accountNumber).setPocket(pocket);
	}
	/**
	 * 
	 * @param accountNumber
	 */
	public void terminatePocket(String pocketNumber) {
		accounts.get(pocketNumber.substring(1)).setPocket(null);
	}
	/**
	 * 
	 * @param accountNumber
	 */
	public void terminateAccount(String accountNumber) {
		accounts.remove(accountNumber);
	}
	/**
	 * 
	 * @param accountNumber
	 * @param money
	 */
	public void deposit(String accountNumber, int amount) {
		Account account = accounts.get(accountNumber);
		account.setBalance(account.getBalance()+amount);
	}
	/**
	 * 
	 * @param accountNumber
	 * @param amount
	 */
	public void withdraw(String accountNumber, int amount) {
		Account account = accounts.get(accountNumber);
		account.setBalance(account.getBalance()-amount);
	}
	/**
	 * 
	 * @param accountNumber
	 * @param amount
	 */
	public void transfer(String accountNumber, int amount) {
		Account account = accounts.get(accountNumber);
		Pocket pocket = account.getAccountPocket();
		account.setBalance(account.getBalance()-amount);
		pocket.setBalance(pocket.getBalance()+amount);
	}
	/**
	 * 
	 * @param accountNumber
	 * @return
	 */
	public int checkAccount(String accountNumber) {
		Account account = accounts.get(accountNumber);
		if(accountNumber.startsWith("b")) {
			return account.getAccountPocket().getBalance();
		}
		return account.getBalance();
	}
}
