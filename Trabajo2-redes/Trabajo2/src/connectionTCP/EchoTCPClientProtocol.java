package connectionTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import model.BankOptions;

public class EchoTCPClientProtocol {
	private static final Scanner SCANNER = new Scanner(System.in);
	
	private static PrintWriter toNetwork;
	private static BufferedReader fromNetwork;
	
	public static void protocol(Socket socket) throws Exception{
		// TODO Auto-generated method stub
		createStreams(socket);
		
		System.out.println("Elija su opción:"
				+ "\n 1, Abrir Cuenta 2. Crear un bolsillo 3. Cancelar un bolsillo"
				+ "\n 4. Cancelar cuenta 5. Depositar a una cuenta 6.Retirar de una cuenta"
				+ "\n 7. Trasladar al bolsillo 8. Consultar Saldo");
		
		String message = SCANNER.nextLine();
		toNetwork.println(BankOptions.values()[Integer.parseInt(message)-1]);
		
		String fromServer = fromNetwork.readLine();
		System.out.println("FROM SERVER: "+fromServer);
		
		message = SCANNER.nextLine();
		toNetwork.println(message);
		
		fromServer = fromNetwork.readLine();
		System.out.println("FROM SERVER: "+fromServer);
		
		
	}
	
	private static void createStreams(Socket socket) throws IOException{
		toNetwork = new PrintWriter(socket.getOutputStream(), true);
		fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

}
