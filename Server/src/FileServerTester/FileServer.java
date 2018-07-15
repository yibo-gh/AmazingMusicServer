package FileServerTester;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.io.File;

class NoDirectoryException extends Exception{
	public NoDirectoryException() {
		super("There is no such user");
	}
}

public class FileServer implements Runnable {
	/**
	 * This class includes the core behavior of file servers.
	 * Input receives a socket and a select, 
	 * which determines whether the file server receives files from the server,
	 * or sends files to the server.
	 */

	private Socket socket = null;
	private int Selection = 0;

	public FileServer(Socket s, int select) {
		this.socket = s;
		this.Selection = select;
	}

	@Override
	public void run(){
		try {
			//DataInputStream dtaInStream = new DataInputStream(socket.getInputStream());
			
			/*
			 * if variable Selection ==1 -> fileServer receives file from the server.
			 * else if variable Selection == 2 -> fileServer sends file to the server. 
			 */
			
			switch(Selection) {
			case 1:
				System.out.println("client wants to send music to fileServer");
				fileServerReceive();
				break;
			case 2:
				System.out.println("client wants to receive music from fileServer");
				fileServerSend();
				break;
			}

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try { if (socket != null) socket.close(); } catch (IOException e) {};
		}
	}

	public void fileServerReceive() {
		/**
		 * This function reads the user name, file name, and then
		 *  the file from the data input stream on the socket. 
		 *  The file server stores the music in a file with the user name 
		 *  from which uploaded the file.
		 */
		try {
			
			/*
			 * Make connection to inputStream of socket.
			 * First read userName from inputStream of socket.
			 * And the the fileName.
			 */
			DataInputStream dtaInStream = new DataInputStream(socket.getInputStream());
			System.out.println("Donedfsdfsdsf");
			String userName = dtaInStream.readUTF();
			System.out.println(userName);
			String fileName = dtaInStream.readUTF();
			//long fileLength = dtaInStream.readLong();
			
			
			/*
			 * If there isn't directory that named uploader's name, make it.
			 */
			File directory = new File(userName);
			if(!directory.exists()) {
				directory.mkdir();
			}
			
			/*
			 * Make connection between music file's outputStream and socket's inputStream.
			 * And then data goes from socket's inputStream to music file until all data
			 * move.
			 */
			
			File music = new File(directory.getAbsolutePath() + File.separatorChar + fileName);
			FileOutputStream fileOutStream = new FileOutputStream(music);

			byte[] buffer = new byte[1024];
			int size = 0;

			while((size = dtaInStream.read(buffer, 0, buffer.length)) != -1) {
				fileOutStream.write(buffer, 0, size);
				fileOutStream.flush();
			}
			
			System.out.println("File "+fileName+" sent to fileServer from client.");
			
			/*
			 * closing streams.
			 */
			try { if (fileOutStream != null) fileOutStream.close(); } catch (IOException e) {};
			try { if (dtaInStream != null) dtaInStream.close(); } catch (IOException e) {};

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void fileServerSend() throws NoDirectoryException {
		/**
		 * This function reads the user name and file name from the data
		 * input stream on the socket. if the userName is not valid, then exception thrown.
		 * function goes to directory named userName and connects with music file's inputStream.
		 * music file's inputStream connects with socket's outputStream and gives data until
		 * the data over.
		 */
		try {
			/*
			 * Make connection to inputStream of socket.
			 * First read userName from inputStream of socket.
			 * And the the fileName.
			 */
			DataInputStream dtaInStream = new DataInputStream(socket.getInputStream());
			String userName = dtaInStream.readUTF();
			String fileName = dtaInStream.readUTF();
			
			/*
			 * Go to directory that named userName,
			 * if there doesn't exist directory, exception occurs
			 * Connect to music file's inputStream with socket's outputStream
			 */
			File directory = new File(userName);
			
			if(!directory.exists()) {
				NoDirectoryException ex = new NoDirectoryException();
				throw ex;
			}

			File music = new File(directory.getAbsolutePath() + File.separatorChar + fileName);

			FileInputStream fileInStream = new FileInputStream(music);
			BufferedInputStream bufferFileInStream = new BufferedInputStream(fileInStream);
			DataInputStream fileDataInStream = new DataInputStream(bufferFileInStream);

			DataOutputStream dtaOutStream = new DataOutputStream(socket.getOutputStream());

			byte[] buffer = new byte[1024];
			int size = 0;
			
			/*
			 * data goes to socket's outputStream until data over.
			 */
			while((size = fileDataInStream.read(buffer, 0, buffer.length)) != -1) {
				dtaOutStream.write(buffer, 0, size);
				dtaOutStream.flush();
			}

			System.out.println("File "+fileName+" sent to client.");
			
			/*
			 * close streams.
			 */
			try { if (dtaInStream != null) dtaInStream.close(); } catch (IOException e) {};
			try { if (fileInStream != null) fileInStream.close(); } catch (IOException e) {};
			try { if (bufferFileInStream != null) bufferFileInStream.close(); } catch (IOException e) {};
			try { if (fileDataInStream != null) fileDataInStream.close(); } catch (IOException e) {};
			try { if (dtaOutStream != null) dtaOutStream.close(); } catch (IOException e) {};

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
