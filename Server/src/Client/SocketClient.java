package Client;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import Object.LinkedList;

public class SocketClient {
	/*
	 * Client machine send a request by a linkedlist
	 * which contains several information.
	 * For example, the first node is about the type
	 * of the request. (register, login, logout, ..)
	 * 1. Receive a linkedlist as an input
	 * 2. Open a socket and get connection
	 * 3. Send the linkedlist through ObjectOutputStream
	 * 4. Receive an object from server through and return it
	 *    ObjectInputStream
	 */
	@SuppressWarnings("finally")
	public static Object request(LinkedList list) {
		Socket socket = null;
		ObjectOutputStream objOutStream = null;
		ObjectInputStream objInStream = null;
		Object obj = null;
		
		try {
			socket = new Socket("127.0.0.1", 18701); // need to decide IP address.
			
			objOutStream = new ObjectOutputStream(socket.getOutputStream());
			objOutStream.writeObject(list);
			objOutStream.flush();
			
			objInStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			obj = objInStream.readObject();
			
			System.out.println((String) obj); // for ClientRequestTester
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try { if (objOutStream != null) objOutStream.close(); } catch (IOException e) {};
			try { if (objInStream != null) objInStream.close(); } catch (IOException e) {};
			try { if (socket != null) socket.close(); } catch (IOException e) {};
			return obj;
		}
	}
}
