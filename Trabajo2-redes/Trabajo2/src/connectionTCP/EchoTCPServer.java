package connectionTCP;

import java.net.ServerSocket;
import java.net.Socket;

public class EchoTCPServer {
	
	public static final int PORT = 3400;
	
	private ServerSocket listener;
	private Socket serverSideSocket;
	
	public EchoTCPServer() {
		System.out.println("Ecgo TCP server...");
	}
	
	public void init() throws Exception {
		listener = new ServerSocket(PORT);
		
		while(true){
			System.out.println("The echo TCP client is waiting for the client...");
			serverSideSocket = listener.accept();
			
			EchoTCPServerProtocol.protocol(serverSideSocket);
		}
	}
	
	public static void main(String args[]) throws Exception {
		EchoTCPServer es = new EchoTCPServer();
		es.init();
	}

}
