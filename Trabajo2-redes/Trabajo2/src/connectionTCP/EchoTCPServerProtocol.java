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
		
		 
		 switch(BankOptions.valueOf(message)) {
		 	case ABRIR_CUENTA:
		 		answer = "Indique su primer nombre y primer apellido:";
		 		toNetwork.println(answer);
				System.out.println("Sent to client: "+message);
				message = fromNetwork.readLine();
				System.out.println("From client: "+message);
				bank.createAccount(message);
				answer = "Cuenta creada correctamente. Usted es la cuenta # "+bank.accounts.size();
		 	break;
		 	case ABRIR_BOLSILLO:
		 		answer = "Indique el número de cuenta:";
		 		toNetwork.println(answer);
				System.out.println("Sent to client: "+message);
				message = fromNetwork.readLine();
				System.out.println("From client: "+message);
				bank.createPocket(Short.parseShort(message));
				answer = "Bolsillo creado con éxito";
		 	break;
		 	case CANCELAR_BOLSILLO:
		 		answer = "Indique el número de cuenta:";
		 		toNetwork.println(answer);
				System.out.println("Sent to client: "+message);
				message = fromNetwork.readLine();
				System.out.println("From client: "+message);
				bank.terminatePocket(Short.parseShort(message));
				answer = "Bolsillo cancelado con éxito";
		 	break;
		 	case CANCELAR_CUENTA:
		 		
		 	break;
		 	case DEPOSITAR:
		 	
		 	break;
		 	case RETIRAR:
			 	
			break;
		 	case TRASLADAR:
			 	
			break;
		 	case CONSULTAR:
			 	
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
