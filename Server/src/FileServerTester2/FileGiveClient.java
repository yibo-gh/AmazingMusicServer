package FileServerTester2;

import java.net.Socket;
import java.io.*;

import Object.LinkedList;
import Object.FileInfo;

public class FileGiveClient {
	public static void fileGiveRequest(LinkedList list, String Filename) {
		Socket socket = null;
		ObjectOutputStream objOutStream = null;
		ObjectInputStream objInStream = null;
		FileInputStream fileInStream = null;
		BufferedInputStream bufFileInStream = null;
		DataInputStream dtaInStream = null;
		DataOutputStream dtaOutStream = null;
		Object  result = null;
		File file = null;
		
		try {
			socket = new Socket("127.0.0.1", 18702);
			
			objOutStream = new ObjectOutputStream(socket.getOutputStream());
			objOutStream.writeObject(list);
			objOutStream.flush();
			
			file = new File(Filename);
			if(file.exists()) {
				
				byte[] filebyte = new byte[(int) file.length()];
				
				fileInStream = new FileInputStream(file);
				bufFileInStream = new BufferedInputStream(fileInStream);
				dtaInStream = new DataInputStream(bufFileInStream);
				
				dtaInStream.readFully(filebyte, 0, filebyte.length);
				
				dtaOutStream = new DataOutputStream(socket.getOutputStream());
				
				dtaOutStream.write(filebyte, 0, filebyte.length);
				dtaOutStream.flush();
				
				System.out.println("File "+Filename+" sent to Server.");
			}
			else {
				System.out.println("There isn't a file");
			}
			
			
			
			objInStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			result = objInStream.readObject();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try { if (objOutStream != null) objOutStream.close(); } catch (IOException e) {};
			try { if (objInStream != null) objInStream.close(); } catch (IOException e) {};
			try { if (socket != null) socket.close(); } catch (IOException e) {};
		}
	}
}