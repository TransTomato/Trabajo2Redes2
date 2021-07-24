package connectionTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoTCPClientProtocol {
	private static final Scanner SCANNER = new Scanner(System.in);
	
	private static PrintWriter toNetwork;
	private static BufferedReader fromNetwork;
	
	public static void protocol(Socket socket) throws Exception{
		// TODO Auto-generated method stub
		createStreams(socket);
		
		System.out.println("Indique su primer nombre y primer apellido para abrir una cuenta:");
		String name = SCANNER.nextLine();
		
		toNetwork.println(name);
		
		String fromServer = fromNetwork.readLine();
		System.out.println("FROM SERVER: "+fromServer);
		
		while(true) {
			System.out.println("Elija su opción:"
					+ "\n 1. Crear un bolsillo 2. Cancelar un bolsillo"
					+ " 3. Depositar a una cuenta 4.Retirar de una cuenta"
					+ " 5. Trasladar al bolsillo 6. Consultar Saldo");
			
			String option = SCANNER.nextLine();
			toNetwork.println(option);
			
			fromServer = fromNetwork.readLine();
			System.out.println("FROM SERVER: "+fromServer);
		}
		
		
	}
	
	private static void createStreams(Socket socket) throws IOException{
		toNetwork = new PrintWriter(socket.getOutputStream(), true);
		fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

}
