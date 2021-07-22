package controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoTCPServerProtocol {

	private static PrintWriter toNetwork;
	private static BufferedReader fromNetwork;
	
	public static void protocol(Socket socket) throws IOException{
		// TODO Auto-generated method stub
		 System.out.println("Connection incoming...");
		 createStreams(socket);
		 
		 String message = fromNetwork.readLine();
		 System.out.println("From client: "+message);
		 
		 String answer = message;
		 
		 toNetwork.println(answer);
		 System.out.println("Sent to client: "+answer);
	}
	
	private static void createStreams(Socket socket) throws IOException{
		toNetwork = new PrintWriter(socket.getOutputStream(), true);
		fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

}
