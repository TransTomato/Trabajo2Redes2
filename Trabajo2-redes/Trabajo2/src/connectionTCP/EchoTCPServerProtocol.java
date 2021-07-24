package connectionTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import model.Account;
import model.Pocket;

public class EchoTCPServerProtocol {
	private static Account account;

	private static PrintWriter toNetwork;
	private static BufferedReader fromNetwork;
	
	public static void protocol(Socket socket) throws IOException{
		// TODO Auto-generated method stub
		 System.out.println("Conexión entrante...");
		 createStreams(socket);
		 
		 String option = fromNetwork.readLine();
		 System.out.println("From client: "+option);
		 String answer = "";
		 
			 switch(Short.parseShort(option)) {
			 	case 1:
			 		
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
		 
		 
		 
		 toNetwork.println(answer);
		 System.out.println("Sent to client: "+answer);
	}
	
	private static void createStreams(Socket socket) throws IOException{
		toNetwork = new PrintWriter(socket.getOutputStream(), true);
		fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

}
