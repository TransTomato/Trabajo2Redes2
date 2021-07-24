package connectionTCP;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import model.Account;

public class EchoTCPServer {
	
	public static final int PORT = 3400;
	
	private ServerSocket listener;
	private Socket serverSideSocket;
	
	public EchoTCPServer() {
		System.out.println("Server-Bank EchoTCP");
	}
	
	public void init() throws Exception {
		listener = new ServerSocket(PORT);
		
		while(true){
			System.out.println("El servidor TCP echo está esperando al usuario...");
			serverSideSocket = listener.accept();
			
			EchoTCPServerProtocol.protocol(serverSideSocket);
		}
	}
	
	public static void main(String args[]) throws Exception {
		EchoTCPServer es = new EchoTCPServer();
		es.init();
	}

}
