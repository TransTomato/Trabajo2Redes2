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
		 
		 if(message.length()==1) {
		
		 
			 switch(Short.parseShort(message)) {
			 	case 1:
			 		answer = "Indique su primer nombre y primer apellido:";
			 		toNetwork.println(answer);
					System.out.println("Sent to client: "+message);
					message = fromNetwork.readLine();
					System.out.println("From client: "+message);
					bank.createAccount(message);
					answer = "Cuenta creada correctamente. Usted es la cuenta # "+bank.accounts.size();
			 	break;
			 	case 2:
			 			
			 	break;
			 	case 3:
			 		
			 	break;
			 	case 4:
			 		
			 	break;
			 	case 5:
			 	
			 	break;
			 	case 6:
				 	
				break;
			 	case 7:
				 	
				break;
		 }
			 
		} 
		 
		 
		 toNetwork.println(answer);
		 System.out.println("Sent to client: "+answer);
	}
	
	private static void createStreams(Socket socket) throws IOException{
		toNetwork = new PrintWriter(socket.getOutputStream(), true);
		fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

}
