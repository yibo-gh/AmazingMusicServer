package Client;

import java.net.Socket;
import java.net.UnknownHostException;
import java.io.*;

import Object.LinkedList;

public class FileGiveClient {
	
	public static Object fileGiveRequest(LinkedList list, String Filename) throws UnknownHostException, IOException {
		
		/**
		 * Purpose : give file server the music file.
		 * first, connect the file server with socket, and write linked list containing the 
		 * request and fileInfo in outputstream.
		 * second, give file to server.
		 * last, get some message whether the file giving work done well.
		 * Input : linked list containing request in first node, and fileInfo in second node.
		 * Output : String that file server give.
		 * 
		 */
		Socket socket = new Socket("yg-home.site", 18702);
		ObjectOutputStream objOutStream = null;
		ObjectInputStream objInStream = null;
		FileInputStream fileInStream = null;
		BufferedInputStream bufFileInStream = null;
		DataInputStream dtaInStream = null;
		DataOutputStream dtaOutStream = null;
		Object  result = null;
		File file = null;
		
		try {
			/*
			 * Open a socket and get connection. Then Send the input linkedlist to server.
			 */
			//socket = new Socket("yg-home.site", 18702);
			
			objOutStream = new ObjectOutputStream(socket.getOutputStream());
			objOutStream.writeObject(list);
			objOutStream.flush();
			
			file = new File(Filename);
			/*
			 * if there is file named filename that client give, start sending file to file server
			 */
			if(file.exists()) {
				
				byte[] filebyte = new byte[(int) file.length()];
				
				fileInStream = new FileInputStream(file);
				bufFileInStream = new BufferedInputStream(fileInStream);
				dtaInStream = new DataInputStream(bufFileInStream);
				
				dtaInStream.readFully(filebyte, 0, filebyte.length);
				
				dtaOutStream = new DataOutputStream(socket.getOutputStream());
				dtaOutStream.write(filebyte, 0, filebyte.length);
				dtaOutStream.flush();
								
				/*
				 * get some string from the file server.
				 */
				objInStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
				result = objInStream.readObject();
			}
			else {
				System.out.println("There isn't a file");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			try { if (objInStream != null) objInStream.close(); } catch (IOException e) {}
			try { if (dtaOutStream != null) dtaOutStream.close(); } catch (IOException e) {}
			try { if (dtaInStream != null) dtaInStream.close(); } catch (IOException e) {}
			try { if (bufFileInStream != null) bufFileInStream.close(); } catch (IOException e) {}
			try { if (fileInStream != null) fileInStream.close(); } catch (IOException e) {}
			try { if (objOutStream != null) objOutStream.close(); } catch (IOException e) {}
			try { if (socket != null) socket.close(); } catch (IOException e) {}
		}
		return result;
	}
}
