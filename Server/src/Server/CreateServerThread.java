package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Object.LinkedList;

public class CreateServerThread extends Thread {
	private Socket clientSocket;
	
	public CreateServerThread (Socket s) {
		this.clientSocket = s;
		System.out.println("New server thread: " + getName());
		
		start();
	}
	
	public void run() {
		try {
			ObjectInputStream objInStream = new ObjectInputStream(this.clientSocket.getInputStream()); // do I have to use bufferedinputstream?
			LinkedList list = (LinkedList) objInStream.readObject();
			System.out.println((String) list.head.getInfo()); // for ClientRequestTester
			
			ObjectOutputStream objOutStream = new ObjectOutputStream(clientSocket.getOutputStream());
			objOutStream.writeObject("done!"); // for ClientRequestTester
			objOutStream.flush();
			
			try { if (objInStream != null) objInStream.close(); } catch (IOException e) {};
			try { if (objOutStream != null) objOutStream.close(); } catch (IOException e) {};
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { 
				if (this.clientSocket != null) this.clientSocket.close(); 
			} catch (IOException e) {};
		}
	}
}