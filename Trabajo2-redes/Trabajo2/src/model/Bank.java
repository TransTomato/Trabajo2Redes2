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
}
