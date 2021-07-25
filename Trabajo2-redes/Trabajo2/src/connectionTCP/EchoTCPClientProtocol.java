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
		
		System.out.println("Elija su opción:"
				+ "\n 1, Abrir Cuenta 2. Crear un bolsillo 3. Cancelar un bolsillo"
				+ "\n 4. Depositar a una cuenta 5.Retirar de una cuenta"
				+ "\n 6. Trasladar al bolsillo 7. Consultar Saldo");
		
		String option = SCANNER.nextLine();
		toNetwork.println(option);
		
		String fromServer = fromNetwork.readLine();
		System.out.println("FROM SERVER: "+fromServer);
		
		String answer = SCANNER.nextLine();
		toNetwork.println(answer);
		
		fromServer = fromNetwork.readLine();
		System.out.println("FROM SERVER: "+fromServer);
		
		
	}
	
	private static void createStreams(Socket socket) throws IOException{
		toNetwork = new PrintWriter(socket.getOutputStream(), true);
		fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

}
