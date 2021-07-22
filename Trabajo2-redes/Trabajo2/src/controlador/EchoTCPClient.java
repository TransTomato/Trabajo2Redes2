package controlador;

import java.net.Socket;

public class EchoTCPClient {
	public static final int PORT = 3400;
	public static final String SERVER = "localhost";
	
	private Socket clientSideSocket;

	public EchoTCPClient() {
		System.out.println("File TCP Client...");
	}
	
	public void init() throws Exception{
		
		clientSideSocket = new Socket(SERVER, PORT);
		System.out.println("Connection approved from Server Side");
		
		EchoTCPClientProtocol.protocol(clientSideSocket);
		
		clientSideSocket.close();
	}
	
	public static void main(String args[]) throws Exception{
		EchoTCPClient ec = new EchoTCPClient();
		ec.init();
	}
	
}
