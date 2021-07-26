package connectionTCP;
/**
 * 			Universidad del Quindío
 * @author	Michelle Quintero Hernández
 *			José Manuel Rojas Tovar
 *			Brayan Tabares Hidalgo
 */
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import model.Account;
import model.Bank;

public class EchoTCPServer implements Runnable{
	
	public static final int PORT = 3400;
	
	private ServerSocket listener;
	private Socket serverSideSocket;
	private static Bank bank;
	
	public EchoTCPServer() {
		System.out.println("Server-Bank EchoTCP");
	}
	
	public void init() throws Exception {
		bank = new Bank();
		listener = new ServerSocket(PORT);
		
		while(true){
			System.out.println("El servidor TCP echo está esperando al usuario...");
			serverSideSocket = listener.accept();
			
			EchoTCPServerProtocol.protocol(serverSideSocket, bank);
		}
	}
	
	public static void main(String args[]) throws Exception {
		EchoTCPServer es = new EchoTCPServer();
		es.init();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			this.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
