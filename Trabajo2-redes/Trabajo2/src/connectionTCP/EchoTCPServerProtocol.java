package connectionTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import exceptions.ColonException;
import exceptions.NameException;
import exceptions.OptionException;
import exceptions.PocketException;
import model.Account;
import model.Bank;
import model.BankOptions;
import model.Pocket;
import persistence.ObjectReader;

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
						answer = "Cuenta creada correctamente. Usted es la cuenta # "+bank.accounts.size();
						
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
			 		   
			 		
						bank.createPocket(message.split(",")[1]);
						bank.addTransaction(message.split(",")[0]);
						answer = "Bolsillo creado con éxito. Bolsillo # b"+message.split(",")[1];
			 		}
			  
			 		catch (NumberFormatException e) {
			 			System.out.println("Input String cannot be parsed to Integer.");
			 		}
			 		catch(Exception bo) {
			 			System.out.println(bo.getMessage());
			 			answer=bo.getMessage();
			 		}
			 	break;
			 	case CANCELAR_BOLSILLO:
					bank.terminatePocket(message.split(",")[1]);
					bank.addTransaction(message.split(",")[0]);
					answer = "Bolsillo cancelado con éxito";
			 	break;
			 	case CANCELAR_CUENTA:
			 		bank.terminateAccount(message.split(",")[1]);
			 		bank.addTransaction(message.split(",")[0]);
			 		answer = "Cuenta # "+message.split(",")[1]+" cancelada exitosamente";
			 	break;
			 	case DEPOSITAR:
			 		bank.deposit(message.split(",")[1], Integer.parseInt(message.split(",")[2]));
			 		bank.addTransaction(message.split(",")[0]);
			 		answer = "Se depositó "+message.split(",")[2]+" en la cuenta # "+message.split(",")[1]+" correctamente";
			 	break;
			 	case RETIRAR:
				 	bank.withdraw(message.split(",")[1], Integer.parseInt(message.split(",")[2]));
				 	answer = "Se retiró "+message.split(",")[2]+" de la cuenta # "+message.split(",")[1]+" correctamente";
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
			 		String transactions="";
			 		for (Map.Entry<String, String> transaction : bank.transactions.entrySet()) {
						transactions+="Fecha-hora:"+transaction.getKey()+" Transaccion: "+transaction.getValue()+"\n";
					}
				 	answer = "Lista de transacciones: "+transactions;
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
	
}
