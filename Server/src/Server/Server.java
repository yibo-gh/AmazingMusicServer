package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public Server() {
		ServerSocket serverSocket = null;
		Socket clientSocket;
		
		try {
			serverSocket = new ServerSocket(18701);
			
			while (true) {
				clientSocket = serverSocket.accept();
				
				new CreateServerThread(clientSocket);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { 
				if (serverSocket != null) serverSocket.close(); 
			} catch (IOException e) {};
		}
	}
}
