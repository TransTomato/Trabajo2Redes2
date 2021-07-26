package connectionTCPTextMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import model.BankOptions;
import persistence.ObjectReader;

public class EchoTCPClientProtocol {
	private static final Scanner SCANNER = new Scanner(System.in);
	
	private static PrintWriter toNetwork;
	private static BufferedReader fromNetwork;
	
	public static void protocol(Socket socket) throws Exception{
		// TODO Auto-generated method stub
		createStreams(socket);
		
		System.out.println("Elija su opcion (Coloque opcion como aparece seguido"
				+ " de una coma y la info necesaria, EJ: ABRIR_CUENTA,Michelle Quintero:"
				+ "\n -, ABRIR_CUENTA,<nombre completo>"
				+ "\n -. ABRIR_BOLSILLO,<numero cuenta>"
				+ "\n -. CANCELAR_BOLSILLO,<numero bolsillo>"
				+ "\n -. CANCELAR_CUENTA,<numero cuenta>"
				+ "\n -. DEPOSITAR,<numero cuenta>,<valor>"
				+ "\n -. RETIRAR,<numero cuenta>,<valor>"
				+ "\n -. TRASLADAR,<numero cuenta>,<valor>"
				+ "\n -. CONSULTAR,<numero cuenta>"
				+ "\n -. CARGAR,<nombre del archivo>"
				+ "\n -. LISTAR_TRANSACCIONES");
		
		String message = SCANNER.nextLine();
		toNetwork.println(message);
		System.out.println("SENT TO SERVER: "+message);
		String fromServer = fromNetwork.readLine();
		System.out.println("FROM SERVER: "+fromServer);
		
		if(message.contains("CARGAR")) {
			ArrayList<String> transactions = ObjectReader.readTransactions(message.split(",")[1]);
			for (String transaction : transactions) {
				
				createStreams(new Socket(socket.getInetAddress(), socket.getPort()));
				toNetwork.println(transaction);
				fromServer = fromNetwork.readLine();
				System.out.println("FROM SERVER: "+fromServer);
			}
		}
		
	}
	
	private static void createStreams(Socket socket) throws IOException{
		toNetwork = new PrintWriter(socket.getOutputStream(), true);
		fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

}
