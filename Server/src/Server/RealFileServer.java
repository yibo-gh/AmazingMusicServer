package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import API.Decoder;
import Object.LinkedList;

public class RealFileServer implements Runnable {
	private Socket socket;
	
	public RealFileServer(Socket s) {
		this.socket = s;
	}
	
	public void run() {
		/**
		 * Purpose: Override run function of Thread class. Perform the server works.
		 * 			Receive the client request, process the request, and send the result of the request.
		 * Input requirement: none
		 * Output: none
		 */
		try {
			ObjectInputStream objInStream = new ObjectInputStream(this.socket.getInputStream()); // do I have to use bufferedinputstream?
			ObjectOutputStream objOutStream = new ObjectOutputStream(this.socket.getOutputStream());
	
			Object userRequest = objInStream.readObject();
			objOutStream.flush();
			
			LinkedList ll = (LinkedList) userRequest;
			
			String selection = (String) ll.head.getInfo().toString();
			System.out.println(selection);
			LinkedList ll2 = rest(ll);
			
			
			try { if (objInStream != null) objInStream.close(); } catch (IOException e) {};
			try { if (objOutStream != null) objOutStream.close(); } catch (IOException e) {};
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { 
				if (this.socket != null) this.socket.close(); 
			} catch (IOException e) {};
		}
	}
	private static LinkedList rest(LinkedList ll) {
		
		/**
		 * Purpose: obtain the rest(all but head node) of a linkedlist
		 * Input Requirement: linkedlist
		 * Output: the rest linkedlist.
		 */
		
		ll.delete(0);
		return ll;
	}
	
}