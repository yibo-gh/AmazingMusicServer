package Server;

import java.net.ServerSocket;
import java.net.Socket;

public class FileServerOperater{
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		try {
			serverSocket = new ServerSocket(18702);
			System.out.println("File Server started");
			
			clientSocket = serverSocket.accept();
			
			int select = 1;
			
			new Thread(new FileServer(clientSocket, select)).start();
			
			System.out.println("FilserverOperaer over");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
