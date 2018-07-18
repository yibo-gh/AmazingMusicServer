package FileServerTester;

import java.net.Socket;
//import java.io.IOException;
import java.io.*;

public class FileGiveClient {
	
	//@SuppressWarnings("finally")
	public static void fileGive(String filename) {
		Socket socket = null;
		DataOutputStream dtaOutStream = null;
		FileInputStream fileInStream = null;
		BufferedInputStream bufFileInStream = null;
		DataInputStream dtaInStream = null;
		File file = null;
		
		try {
			socket = new Socket("localhost", 18702); // Note: need to change later. need to decide IP address. 
			
			file = new File(filename);
			if(file.exists()) {
		
				byte[] filebyte = new byte[(int) file.length()];
				
				fileInStream = new FileInputStream(file);
				bufFileInStream = new BufferedInputStream(fileInStream);
				dtaInStream = new DataInputStream(bufFileInStream);
				
				dtaInStream.readFully(filebyte, 0, filebyte.length);
				
				dtaOutStream = new DataOutputStream(socket.getOutputStream());
				
				//dtaOutStream.writeInt(1);
				dtaOutStream.writeUTF("DongYeun");
				dtaOutStream.writeUTF(file.getName());
				dtaOutStream.write(filebyte, 0, filebyte.length);
				dtaOutStream.flush();
				
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

