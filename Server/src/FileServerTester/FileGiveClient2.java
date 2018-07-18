package FileServerTester;

import java.net.Socket;
import java.io.*;

import Object.LinkedList;
import Object.FileInfo;

//import java.io.IOException;

public class FileGiveClient2 {
	
	//@SuppressWarnings("finally")
	public static void fileGive(String filename) {
		Socket socket = null;
		DataOutputStream dtaOutStream = null;
		FileInputStream fileInStream = null;
		BufferedInputStream bufFileInStream = null;
		DataInputStream dtaInStream = null;
		ObjectOutputStream objOutStream = null;
		File file = null;
		
		try {
			socket = new Socket("localhost", 18702); // Note: need to change later. need to decide IP address. 
			
			file = new File(filename);
			if(file.exists()) {
				
				LinkedList list = new LinkedList();
				FileInfo fileinfo = new FileInfo("DongYeun", filename, "111", "txt");
				list.add("upload");
				list.add(fileinfo);
				
				objOutStream = new ObjectOutputStream(socket.getOutputStream());
				objOutStream.writeObject(list);
				objOutStream.flush();
				
				System.out.println("File "+filename+" sent to Server.");
			}
			else {
				System.out.println("There isn't a file");
			}			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try { if (dtaOutStream != null) dtaOutStream.close(); } catch (IOException e) {};
			try { if (dtaInStream != null) dtaInStream.close(); } catch (IOException e) {};
			try { if (fileInStream != null) fileInStream.close(); } catch (IOException e) {};
			try { if (bufFileInStream != null) bufFileInStream.close(); } catch (IOException e) {};
			try { if (socket != null) socket.close(); } catch (IOException e) {};
		}
		
	}

}

