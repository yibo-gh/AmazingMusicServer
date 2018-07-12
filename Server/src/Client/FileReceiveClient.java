package Client;

import java.net.Socket;
//import java.io.IOException;
import java.io.*;

public class FileReceiveClient {
	
	public static void fileReceive(String filename) {
		Socket socket = null;
		DataInputStream dtaInStream = null;
		OutputStream outStream = null;
		
		try {
			socket = new Socket("127.0.0.1", 18702); // Note: need to change later. need to decide IP address. 
			dtaInStream = new DataInputStream(socket.getInputStream());
			
			filename = dtaInStream.readUTF();
			long filesize = dtaInStream.readLong();
			byte[] buffer = new byte[1024];
			
			outStream = new FileOutputStream(("received from server" + filename));
			int bytesRead = dtaInStream.read(buffer, 0, (int) Math.min(buffer.length, filesize));
			
			while (filesize > 0 && bytesRead != -1) {
                outStream.write(buffer, 0, bytesRead);
                filesize -= bytesRead;
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
