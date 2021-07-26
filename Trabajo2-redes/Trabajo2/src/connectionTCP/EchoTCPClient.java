package connectionTCP;

import java.net.Socket;

public class EchoTCPClient implements Runnable{
	
	public static final int PORT = 3400;
	public static final String SERVER = "localhost";
	public static boolean close = false;
	
	private Socket clientSideSocket;

	public EchoTCPClient() {
		System.out.println("File TCP Client...");
	}
	
	public void init() throws Exception{
		while(!close)
		{
		
		clientSideSocket = new Socket(SERVER, PORT);
		System.out.println("Connection approved from Server Side");
		
		EchoTCPClientProtocol.protocol(clientSideSocket);
		}
		
		clientSideSocket.close();
	}
	
	public static void main(String args[]) throws Exception{
		EchoTCPClient ec = new EchoTCPClient();
		ec.init();
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
