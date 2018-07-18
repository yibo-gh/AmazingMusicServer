package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import API.Decoder;

public class CreateServerThread extends Thread {
	private Socket clientSocket;
	
	public CreateServerThread (Socket s) {
		
		/*
		 * Constructor for CreateServerThread. Start thread execution. Two threads are running concurrently.
		 * Input Requirement: a socket which is connected with a client
		 */
		
		this.clientSocket = s;
		System.out.println("New server thread: " + getName()); // print the thread name.
		
		start();
	}
	
	public void run() {
		
		/**
		 * Purpose: Override run function of Thread class. Perform the server works.
		 * 			Receive the client request, process the request, and send the result of the request.
		 * Input requirement: none
		 * Output: none
		 */
		
		try {
			ObjectInputStream objInStream = new ObjectInputStream(this.clientSocket.getInputStream()); // do I have to use bufferedinputstream?
			ObjectOutputStream objOutStream = new ObjectOutputStream(this.clientSocket.getOutputStream());
			
			/*
			 *  receive client request from the stream, 
			 *  give the information to the decoder,
			 *  get processed result from API part,
			 *  and sent the result to client
			 */
			
			Object userRequest = objInStream.readObject();
			Object ob = Decoder.firewall(userRequest); // check if userRequest is linkedlist in firewall
			objOutStream.writeObject(ob);
			objOutStream.flush();
			
			objOutStream.writeObject("done!"); // Note: need to change later
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