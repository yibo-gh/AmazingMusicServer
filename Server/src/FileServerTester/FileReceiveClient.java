package FileServerTester;

import java.net.Socket;
//import java.io.IOException;
import java.io.*;

public class FileReceiveClient {
	
	public static void fileReceive(String filename) {
		Socket socket = null;
		DataInputStream dtaInStream = null;
		DataOutputStream dtaOutStream = null;
		OutputStream outStream = null;
		
		try {
			socket = new Socket("localhost", 18702); // Note: need to change later. need to decide IP address. 
			dtaInStream = new DataInputStream(socket.getInputStream());
			
			dtaOutStream = new DataOutputStream(socket.getOutputStream());
			
			//dtaOutStream.writeInt(2);
			dtaOutStream.writeUTF("DongYeun");
			dtaOutStream.writeUTF(filename);
			
			//long filesize = dtaInStream.readLong();
			byte[] buffer = new byte[1024];
			int size = 0;
			
			File music = new File("/Users/user/Desktop/cmpm80/"+ filename);
			
			outStream = new FileOutputStream(music);
			//int bytesRead = dtaInStream.read(buffer, 0, (int) Math.min(buffer.length, filesize));
			
			while ((size = dtaInStream.read(buffer, 0, buffer.length)) != -1) {
              outStream.write(buffer, 0, size);
              outStream.flush();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try { if (outStream != null) outStream.close(); } catch (IOException e) {};
			try { if (dtaInStream != null) dtaInStream.close(); } catch (IOException e) {};
			try { if (socket != null) socket.close(); } catch (IOException e) {};
		}
	}

}
