package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RealFileServerOperater{
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		try {
			
			serverSocket = new ServerSocket(8080);
			System.out.println("File Server started");
			
			while(true) {
			clientSocket = serverSocket.accept();
			System.out.println("server successfully connected to client");
			
			new Thread(new RealFileServer(clientSocket)).start();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (serverSocket != null) serverSocket.close();
			}catch (IOException e) {};
		}
	}
}