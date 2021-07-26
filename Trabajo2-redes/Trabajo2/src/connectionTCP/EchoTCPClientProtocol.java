package connectionTCP;

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
	/**
	 * 
	 * @param socket
	 * @param name
	 * @return
	 * @throws IOException
	 */
	public static String abrirCuenta(Socket socket, String name)  {
		String console = "",
				message = BankOptions.ABRIR_CUENTA+","+name;
		try {
			createStreams(socket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		toNetwork.println(message);
		console+="SENT TO SERVER:"+message+"\n";
		String fromServer = "";
		try {
			fromServer = fromNetwork.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		console+="FROM SERVER: "+fromServer+"\n";
		
		return console;
	}
	/**
	 * 
	 * @param socket
	 * @param accountNum
	 * @return
	 * @throws IOException
	 */
	public static String abrirBolsillo(Socket socket, String accountNum)  {
		String console = "",
				message = BankOptions.ABRIR_BOLSILLO+","+accountNum;
		try {
			createStreams(socket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		toNetwork.println(message);
		console+="SENT TO SERVER:"+message+"\n";
		String fromServer = "";
		try {
			fromServer = fromNetwork.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		console+="FROM SERVER: "+fromServer;
		
		return console;
	}
	/**
	 * 
	 * @param socket
	 * @param accountNum
	 * @return
	 * @throws IOException
	 */
	public static String deposit(Socket socket, String accountNum, String value)  {
		String console = "",
				message = BankOptions.DEPOSITAR+","+accountNum+","+value;
		try {
			createStreams(socket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		toNetwork.println(message);
		console+="SENT TO SERVER:"+message+"\n";
		String fromServer = "";
		try {
			fromServer = fromNetwork.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		console+="FROM SERVER: "+fromServer;
		
		return console;
	}
	/**
	 * 
	 * @param socket
	 * @param accountNum
	 * @param value
	 * @return
	 * @throws IOException
	 */
	public static String transfer(Socket socket, String accountNum, String value)  {
		String console = "",
				message = BankOptions.TRASLADAR+","+accountNum+","+value;
		try {
			createStreams(socket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		toNetwork.println(message);
		console+="SENT TO SERVER:"+message+"\n";
		String fromServer = "";
		try {
			fromServer = fromNetwork.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		console+="FROM SERVER: "+fromServer;
		
		return console;
	}
	/**
	 * 
	 * @param socket
	 * @param accountNum
	 * @param value
	 * @return
	 * @throws IOException
	 */
	public static String withdraw(Socket socket, String accountNum, String value)  {
		String console = "",
				message = BankOptions.CANCELAR_CUENTA+","+accountNum+","+value;
		try {
			createStreams(socket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		toNetwork.println(message);
		console+="SENT TO SERVER:"+message+"\n";
		String fromServer = "";
		try {
			fromServer = fromNetwork.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		console+="FROM SERVER: "+fromServer;
		
		return console;
	}
	/**
	 * 
	 * @param socket
	 * @param pocketNum
	 * @return
	 * @throws IOException
	 */
	public static String cancelarBolsillo(Socket socket, String pocketNum) {
		String console = "",
				message = BankOptions.CANCELAR_BOLSILLO+","+pocketNum;
		try {
			createStreams(socket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		toNetwork.println(message);
		console+="SENT TO SERVER:"+message+"\n";
		String fromServer = "";
		try {
			fromServer = fromNetwork.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		console+="FROM SERVER: "+fromServer;
		
		return console;
	}
	/**
	 * 
	 * @param socket
	 * @param accountNum
	 * @return
	 * @throws IOException
	 */
	public static String cancelarCuenta(Socket socket, String accountNum) {
		String console = "",
				message = BankOptions.CANCELAR_CUENTA+","+accountNum;
		try {
			createStreams(socket);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		toNetwork.println(message);
		console+="SENT TO SERVER:"+message+"\n";
		String fromServer = "";
		try {
			fromServer = fromNetwork.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		console+="FROM SERVER: "+fromServer;
		
		return console;
	}
	/**
	 * 
	 * @param socket
	 * @param accountNum
	 * @return
	 * @throws IOException
	 */
	public static String check(Socket socket, String accountNum) {
		String console = "",
				message = BankOptions.CONSULTAR+","+accountNum;
		try {
			createStreams(socket);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		toNetwork.println(message);
		console+="SENT TO SERVER:"+message+"\n";
		String fromServer = "";
		try {
			fromServer = fromNetwork.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		console+="FROM SERVER: "+fromServer;
		
		return console;
	}
	/**
	 * 
	 * @param socket
	 * @return
	 * @throws IOException
	 */
	public static String listTransfers(Socket socket) {
		String console = "",
				message = BankOptions.LISTAR_TRANSACCIONES+"";
		try {
			createStreams(socket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		toNetwork.println(message);
		console+="SENT TO SERVER:"+message+"\n";
		String fromServer = "";
		try {
			fromServer = fromNetwork.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		console+="FROM SERVER: "+fromServer;
		
		return console;
	}
	/**
	 * 
	 * @param socket
	 * @return
	 * @throws IOException
	 */
	public static String cargarArchivos(Socket socket) {
		String console = "";
		try {
			createStreams(socket);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ArrayList<String> transactions = ObjectReader.readTransactions("transactions_example.txt");
		for (String transaction : transactions) {
			try {
				createStreams(new Socket(socket.getInetAddress(), socket.getPort()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			toNetwork.println(transaction);
			console+="SENT TO SERVER:"+transaction+"\n";
			String fromServer = "";
			try {
				fromServer = fromNetwork.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			console+="FROM SERVER: "+fromServer;
		}
		return console;
	}

}
