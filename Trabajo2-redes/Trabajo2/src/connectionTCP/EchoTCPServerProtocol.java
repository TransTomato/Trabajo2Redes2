package connectionTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

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
		
		 
		 switch(BankOptions.valueOf(message.split(",")[0])) {
		 	case ABRIR_CUENTA:
				bank.createAccount(message.split(",")[1]);
				answer = "Cuenta creada correctamente. Usted es la cuenta # "+bank.accounts.size();
		 	break;
		 	case ABRIR_BOLSILLO:
				bank.createPocket(message.split(",")[1]);
				answer = "Bolsillo creado con éxito. Bolsillo # b"+message.split(",")[1];
		 	break;
		 	case CANCELAR_BOLSILLO:
				bank.terminatePocket(message.split(",")[1]);
				answer = "Bolsillo cancelado con éxito";
		 	break;
		 	case CANCELAR_CUENTA:
		 		bank.terminateAccount(message.split(",")[1]);
		 		answer = "Cuenta # "+message.split(",")[1]+" cancelada exitosamente";
		 	break;
		 	case DEPOSITAR:
		 		bank.deposit(message.split(",")[1], Integer.parseInt(message.split(",")[2]));
		 		answer = "Se depositó "+message.split(",")[2]+" en la cuenta # "+message.split(",")[1]+" correctamente";
		 	break;
		 	case RETIRAR:
			 	bank.withdraw(message.split(",")[1], Integer.parseInt(message.split(",")[2]));
			 	answer = "Se retiró "+message.split(",")[2]+" de la cuenta # "+message.split(",")[1]+" correctamente";
			break;
		 	case TRASLADAR:
			 	bank.transfer(message.split(",")[1], Integer.parseInt(message.split(",")[2]));
			 	answer = "Se trasladó "+message.split(",")[2]+" al bolsillo # "+message.split(",")[1]+" correctamente";
			break;
		 	case CONSULTAR:
			 	int balance = bank.checkAccount(message.split(",")[1]);
			 	answer = "Su saldo en la cuenta #"+message.split(",")[1]+" es de "+balance;
			break;
		 	case CARGAR:
			 	ArrayList<String> transactions = ObjectReader.readTransactions(message.split(",")[1]);
			 	answer = "Se ha cargado los datos del archivo: "+message.split(",")[1];
			break;
	 }
		 
		 
		 
		 toNetwork.println(answer);
		 System.out.println("Sent to client: "+answer);
	}
	
	private static void createStreams(Socket socket) throws IOException{
		toNetwork = new PrintWriter(socket.getOutputStream(), true);
		fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

}
