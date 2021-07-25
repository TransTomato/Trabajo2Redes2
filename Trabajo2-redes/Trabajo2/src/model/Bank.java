package model;
/**
 * 			Universidad del Quindío
 * @author	Michelle Quintero Hernández
 *			José Manuel Rojas Tovar
 *			Brayan Tabares Hidalgo
 */
import java.util.HashMap;

public class Bank {

	public HashMap<Short, Account> accounts = new HashMap<Short, Account>();
	
	/**
	 * 
	 * @param name
	 */
	public void createAccount(String name) {
		Account account = new Account(name);
		accounts.put((short) (accounts.size()+1), account);
	}
	/**
	 * 
	 * @param accountNumber
	 */
	public void createPocket(short accountNumber) {
		Pocket pocket = new Pocket(accountNumber);
		accounts.get(accountNumber).setPocket(pocket);
	}
	
}
