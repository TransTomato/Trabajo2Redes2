package model;

import java.util.HashMap;

public class Bank {

	public HashMap<Account, String> accounts = new HashMap<Account, String>();
	
	/**
	 * 
	 * @param name
	 */
	public void createAccount(String name) {
		Account account = new Account(name);
		accounts.put(account, ""+accounts.size()+1);
	}
	
	public void createPocket() {
		
	}
	
}
