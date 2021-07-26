package model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * 			Universidad del Quindío
 * @author	Michelle Quintero Hernández
 *			José Manuel Rojas Tovar
 *			Brayan Tabares Hidalgo
 */
import java.util.HashMap;

public class Bank {

	public HashMap<String, Account> accounts = new HashMap<String, Account>();
	public HashMap<String, String> transactions = new HashMap<String, String>();
	
	/**
	 * 
	 * @param name
	 */
	public void createAccount(String name) {
		Account account = new Account(name);
		accounts.put(""+(accounts.size()), account);
	}
	/**
	 * 
	 * @param accountNumber
	 */
	public void createPocket(String accountNumber) {
		accountNumber = accountNumber.replaceAll(" ","");
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
		accountNumber = accountNumber.replaceAll(" ","");
		accounts.remove(accountNumber);
	}
	/**
	 * 
	 * @param accountNumber
	 * @param money
	 */
	public void deposit(String accountNumber, int amount) {
		accountNumber = accountNumber.replaceAll(" ","");
		Account account = accounts.get(accountNumber);
		account.setBalance(account.getBalance()+amount);
	}
	/**
	 * 
	 * @param accountNumber
	 * @param amount
	 */
	public void withdraw(String accountNumber, int amount) {
		accountNumber = accountNumber.replaceAll(" ","");
		Account account = accounts.get(accountNumber);
		account.setBalance(account.getBalance()-amount);
	}
	/**
	 * 
	 * @param accountNumber
	 * @param amount
	 */
	public void transfer(String accountNumber, int amount) {
		accountNumber = accountNumber.replaceAll(" ","");
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
		accountNumber = accountNumber.replaceAll(" ","");
		Account account;
		if(accountNumber.startsWith("b")) {
			account = accounts.get(accountNumber.replace("b", ""));
			return account.getAccountPocket().getBalance();
		}
		 account = accounts.get(accountNumber);
		return account.getBalance();
	}
	public void addTransaction(String transaction) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		transactions.put(dtf.format(now), transaction);
	}
}
