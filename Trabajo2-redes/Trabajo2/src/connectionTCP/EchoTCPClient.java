package connectionTCP;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoTCPClient {
	
	public static final int PORT = 3400;
	public static final String SERVER = "localhost";
	public static boolean close = false;
	
	public Socket clientSideSocket;

	public EchoTCPClient() {
		System.out.println("File TCP Client...");
	}
	
	public void init() throws Exception{
		while(!close)
		{
		createSocket();
		System.out.println("Connection approved from Server Side");
		
		
		EchoTCPClientProtocol.protocol(clientSideSocket);
		}
		
		clientSideSocket.close();
	}
	
	public void createSocket() {
		try {
			clientSideSocket = new Socket(SERVER,PORT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) throws Exception{
		EchoTCPClient ec = new EchoTCPClient();
		ec.init();
	}
	
	
}
