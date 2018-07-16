package FileServerTester;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServerOperater{
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		try {
			
			serverSocket = new ServerSocket(18702);
			System.out.println("File Server started");
			
			while(true) {
			clientSocket = serverSocket.accept();
			System.out.println("server successfully connected to client");
			
			int select = 1;
			System.out.println(select);
			
			new Thread(new FileServer(clientSocket, select)).start();
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