package FileServerTester2;

import java.net.Socket;
import java.io.*;

import Object.LinkedList;
import Object.FileInfo;

public class BasicClient {
	
	public static void basicRequest(LinkedList list, String Filename) {
		Socket socket = null;
		ObjectOutputStream objOutStream = null;
		ObjectInputStream objInStream = null;
		Object  result = null;
		
		try {
			socket = new Socket("127.0.0.1", 18702);
			
			objOutStream = new ObjectOutputStream(socket.getOutputStream());
			objOutStream.writeObject(list);
			objOutStream.flush();
			
			objInStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			result = objInStream.readObject();
			//FileInfo flInfo = (FileInfo) result;
			
			//System.out.println(flInfo.getUID());
			//System.out.println(flInfo.getOriName());
			//System.out.println(flInfo.getMD5());
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try { if (objOutStream != null) objOutStream.close(); } catch (IOException e) {};
			try { if (objInStream != null) objInStream.close(); } catch (IOException e) {};
			try { if (socket != null) socket.close(); } catch (IOException e) {};
		}
	}

}
