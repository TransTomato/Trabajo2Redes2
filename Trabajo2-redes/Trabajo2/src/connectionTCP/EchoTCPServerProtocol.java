package connectionTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import exceptions.ColonException;
import exceptions.DepositException;
import exceptions.FunarAccountException;
import exceptions.FunarPocketException;
import exceptions.NameException;
import exceptions.OptionException;
import exceptions.PocketException;
import exceptions.WithdrawException;
import model.Account;
import model.Bank;
import model.BankOptions;

public class EchoTCPServerProtocol {
	private static PrintWriter toNetwork;
	private static BufferedReader fromNetwork;
	
	public static void protocol(Socket socket, Bank bank) throws IOException{
		// TODO Auto-generated method stub
		 System.out.println("Conexión entrante...");
		 createStreams(socket);
		 
		 String message = fromNetwork.readLine();
		 System.out.println("From client: "+message);
		 String answer = "";
		
		 try {
			
			invalidOption(message.split(",")[0]);
			 switch(BankOptions.valueOf(message.split(",")[0])) {
			 	case ABRIR_CUENTA:
			 		try {
			 			invalidColon(message,1);
			 			invalidName(message.split(",")[1],bank);
			 			bank.createAccount(message.split(",")[1]);
			 			bank.addTransaction(message.split(",")[0]);
						answer = "Cuenta creada correctamente. Usted es la cuenta # "+(bank.accounts.size()-1);
						
			 		}
					catch(Exception ne) {
						System.out.println(ne.getMessage());
						answer= ne.getMessage();
					}
			 		break;
			 	case ABRIR_BOLSILLO:
			 		try {
			 			invalidColon(message,1);
			 			int value = Integer.parseInt(message.split(",")[1]);
			 			invalidPocket(message.split(",")[1],bank);
			 		
						bank.createPocket(message.split(",")[1]);
						bank.addTransaction(message.split(",")[0]);
						answer = "Bolsillo creado con éxito. Bolsillo # b"+message.split(",")[1];
			 		}
			  
			 		catch (NumberFormatException e) {
			 			System.out.println("Por favor ingrese un numero.");
			 			answer="Por favor ingrese un numero.";
			 		}
			 		catch(Exception bo) {
			 			System.out.println(bo.getMessage());
			 			answer=bo.getMessage();
			 		}
			 	break;
			 	case CANCELAR_BOLSILLO:
			 		try {
			 			invalidColon(message,1);
			 			int value = Integer.parseInt(message.split(",")[1]);
			 			funarPocket(message.split(",")[1],bank);
						bank.terminatePocket(message.split(",")[1]);
						bank.addTransaction(message.split(",")[0]);
						answer = "Bolsillo cancelado con éxito";
			 		}
			 		catch (NumberFormatException e) {
			 			System.out.println("Por favor ingrese un numero.");
			 			answer="Por favor ingrese un numero.";
			 		}
			 		catch(Exception bo) {
			 			System.out.println(bo.getMessage());
			 			answer=bo.getMessage();
			 		}
			 	break;
			 	case CANCELAR_CUENTA:
			 		try {
			 			invalidColon(message,1);
			 			int value = Integer.parseInt(message.split(",")[1]);
			 			funarAccount(message.split(",")[1],bank);
				 		bank.terminateAccount(message.split(",")[1]);
				 		bank.addTransaction(message.split(",")[0]);
				 		answer = "Cuenta # "+message.split(",")[1]+" cancelada exitosamente";
			 		}
			 		catch (NumberFormatException e) {
			 			System.out.println("Por favor ingrese un numero.");
			 			answer="Por favor ingrese un numero.";
			 		}
			 		catch(Exception bo) {
			 			System.out.println(bo.getMessage());
			 			answer=bo.getMessage();
			 		}
			 	break;
			 	case DEPOSITAR:
			 		try {
			 			invalidColon(message,2);
			 			int value = Integer.parseInt(message.split(",")[1]);
			 			int depo = Integer.parseInt(message.split(",")[2]);
			 			depositAccount(message.split(",")[1],message.split(",")[2],bank);
				 		bank.deposit(message.split(",")[1], Integer.parseInt(message.split(",")[2]));
				 		bank.addTransaction(message.split(",")[0]);
				 		answer = "Se depositó "+message.split(",")[2]+" en la cuenta # "+message.split(",")[1]+" correctamente";
			 		}
			 		catch (NumberFormatException e) {
			 			System.out.println("Por favor ingrese un numero.");
			 			answer="Por favor ingrese un numero.";
			 		}
			 		catch(Exception bo) {
			 			System.out.println(bo.getMessage());
			 			answer=bo.getMessage();
			 		}
			 		
			 	break;
			 	case RETIRAR:
			 		try {
			 			invalidColon(message,2);
			 			int value = Integer.parseInt(message.split(",")[1]);
			 			int depo = Integer.parseInt(message.split(",")[2]);
			 			withAccount(message.split(",")[1],message.split(",")[2],bank);
					 	bank.withdraw(message.split(",")[1], Integer.parseInt(message.split(",")[2]));
					 	answer = "Se retiró "+message.split(",")[2]+" de la cuenta # "+message.split(",")[1]+" correctamente";
			 		}
			 		catch (NumberFormatException e) {
			 			System.out.println("Por favor ingrese un numero.");
			 			answer="Por favor ingrese un numero.";
			 		}
			 		catch(Exception bo) {
			 			System.out.println(bo.getMessage());
			 			answer=bo.getMessage();
			 		}
				break;
			 	case TRASLADAR:
				 	bank.transfer(message.split(",")[1], Integer.parseInt(message.split(",")[2]));
				 	bank.addTransaction(message.split(",")[0]);
				 	answer = "Se trasladó "+message.split(",")[2]+" al bolsillo # "+message.split(",")[1]+" correctamente";
				break;
			 	case CONSULTAR:
				 	int balance = bank.checkAccount(message.split(",")[1]);
				 	bank.addTransaction(message.split(",")[0]);
				 	answer = "Su saldo en la cuenta #"+message.split(",")[1]+" es de "+balance;
				break;
			 	case CARGAR:
				 	answer = "Se ha cargado los datos del archivo: "+message.split(",")[1];
				break;
			 	case LISTAR_TRANSACCIONES:
			 		String transactions = "";
			 		for (Map.Entry<String, String> transaction : bank.transactions.entrySet()) {
					transactions += "Fecha-hora:"+transaction.getKey()+" Transaccion: "+transaction.getValue()+";";
			 		}
			 		answer = "Lista de transacciones: ;"+transactions;
				break;
			 }
		 }
		 catch(OptionException oe) {
			 System.out.println(oe.getMessage());
			 answer=oe.getMessage();
		 }
		 
		 
		 toNetwork.println(answer);
		 System.out.println("Sent to client: "+answer);
	}
	
	private static void createStreams(Socket socket) throws IOException{
		toNetwork = new PrintWriter(socket.getOutputStream(), true);
		fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	public static void invalidName(String name,Bank bank) throws NameException {
		char[] chars = name.toCharArray();   
	      for(char c : chars){
	         if(Character.isDigit(c)){
	            throw new NameException("El nombre no puede contener numeros");
	         }
	      }
	  
	      HashMap<String,Account> selects=bank.accounts;
	      for(Account account: selects.values()) {
	    	  String clone=account.getName();
	    	  if(clone.replaceAll(" ", "").toLowerCase().equals(name.replaceAll(" ", "").toLowerCase())) {   		  
	    		  throw new NameException("El nombre ingresado ya esta en el sistema");
	    	  }
	      }
	}
	
	public static boolean invalidOption(String option) throws OptionException {
		for (BankOptions bankOp : BankOptions.values()) {
			if(option.contentEquals(bankOp+"")) {
				return false;
			}
			
		}
		throw new OptionException("Por favor introduzca una opcion valida");
	}
	
	public static void invalidColon(String message,int colon) throws ColonException {
		if(!(message.split(",").length==colon+1)) {
			throw new ColonException("Por favor ingresar el numero de comas necesarias: "+colon);
		}
	}
	
	public static void invalidPocket(String message,Bank bank) throws PocketException{
		if(!bank.accounts.containsKey(message)) {
			throw new PocketException("La cuenta a la que quiere abrir un bolsillo no existe");
		}
		if(!(bank.accounts.get(message).getAccountPocket()==null)) {
			throw new PocketException("La cuenta ya posee un bolsillo");
		}
	}
	
	public static void funarPocket(String message,Bank bank) throws FunarPocketException {
		if(!bank.accounts.containsKey(message)) {
			throw new FunarPocketException("La cuenta a la que quiere cerrar un bolsillo no existe");
		}
		if((bank.accounts.get(message).getAccountPocket()==null)) {
			throw new FunarPocketException("La cuenta no posee un bolsillo");
		}
	}
	
	public static void funarAccount(String message,Bank bank) throws FunarAccountException {
		if(!bank.accounts.containsKey(message)) {
			throw new FunarAccountException("La cuenta que quiere cerrar no existe");
		}
		if(!(bank.accounts.get(message).getAccountPocket()==null)) {
			throw new FunarAccountException("No se puede cerrar la cuenta porque posee un bolsillo");
		}
		if(bank.accounts.get(message).getBalance()>0) {
			throw new FunarAccountException("No se puede cerrar la cuenta porque no tiene un saldo de 0");
		}
	}
	
	public static void depositAccount(String message,String valor,Bank bank) throws DepositException {
		if(!bank.accounts.containsKey(message)) {
			throw new DepositException("No se puede depositar a una cuenta que no existe");
		}
		if(Integer.parseInt(valor)<=0) {
			throw new DepositException("No se puede depositar un valor nulo o negativo");
		}
	}
	
	public static void withAccount(String message,String valor,Bank bank) throws WithdrawException {
		if(!bank.accounts.containsKey(message)) {
			throw new WithdrawException("No se puede retirar de una cuenta que no existe");
		}
		if(Integer.parseInt(valor)<=0) {
			throw new WithdrawException("No se puede retirar un valor nulo o negativo");
		}
		if(Integer.parseInt(valor)>bank.accounts.get(message).getBalance()) {
			throw new WithdrawException("No se puede retirar un valor mayor al de su saldo");
		}
	}
}
