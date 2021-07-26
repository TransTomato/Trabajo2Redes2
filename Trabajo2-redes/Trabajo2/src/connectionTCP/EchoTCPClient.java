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
		try {
			clientSideSocket = new Socket(SERVER,PORT);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void init() throws Exception{
		while(!close)
		{
		
		System.out.println("Connection approved from Server Side");
		
		
		EchoTCPClientProtocol.protocol(clientSideSocket);
		}
		
		clientSideSocket.close();
	}
	
	public static void main(String args[]) throws Exception{
		EchoTCPClient ec = new EchoTCPClient();
		ec.init();
	}
	
	
}
