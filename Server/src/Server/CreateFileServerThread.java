package Server;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Object.LinkedList;
import Object.FileInfo;
import API.FileServerDecoder;
import API.FileCoreFunctions;

public class CreateFileServerThread extends Thread {
	private Socket clientSocket;

	public CreateFileServerThread(Socket s) {
		
		/*
		 * Constructor for CreateFileServerThread. Start thread execution. 
		 * Two threads are running concurrently. 
		 * Input Requirement: a socket which is connected with a client
		 */
		
		this.clientSocket = s;
		System.out.println("New server thread: " + getName()); // print the thread name.

		start();
	}

	public void run() {
		
		/**
		 * Purpose: Override run function of Thread class. Perform the server works.
		 * 			Receive the client request, process the request, and send the result of the request.
		 * Input requirement: none
		 * Output: none
		 */
		
		try {
			ObjectInputStream objInStream = new ObjectInputStream(this.clientSocket.getInputStream());
			ObjectOutputStream objOutStream = new ObjectOutputStream(this.clientSocket.getOutputStream());
			String signal = "";
			
			/*
			 *  receive client request from the stream, 
			 *  give the information to the decoder,
			 *  get processed result from API part,
			 *  and sent the result to client
			 */
			Object userRequest = objInStream.readObject();
			LinkedList sign = (LinkedList) FileServerDecoder.firewall(userRequest); // check if userRequest is linkedlist in firewall
			System.out.println(sign.getClass());
			System.out.println(sign.head.getInfo());
			
			/*
			 * if sign.head is "OK" that means oriName of file is not null. then start getting file from client.
			 * first keep music file into temporary file. then validate the file if the temporary file's MD5 is correct with 
			 * DB waitingfile's MD5 value.
			 */
			if (sign.head.getInfo() == "OK") {
				System.out.println("Now client sends files");
				FileInfo flInfo = (FileInfo) sign.end.getInfo();
				File music = new File("temporary" + File.separatorChar + flInfo.getFileSerial() + flInfo.getExension());
				FileOutputStream fileOutStream = new FileOutputStream(music);

				DataInputStream dtaInStream = new DataInputStream(this.clientSocket.getInputStream());
				byte[] buffer = new byte[1024];
				int size = 0;
				
				/*
				 * file data is getting in to the file.
				 */
				while (true) {
					size = dtaInStream.read(buffer, 0, buffer.length);
					fileOutStream.write(buffer, 0, size);
					if (size < 1024) {
						System.out.println("break");
						fileOutStream.close();
						break;
					}
				}
				System.out.println("File sent to fileServer from client.");
				
				/*
				 * compare the file's MD5 value that client give with DB waiting file.
				 */
				signal = FileCoreFunctions.validate(flInfo);
			} 
			else {
				System.out.println("Client can't send file because there is no file name");
				signal = " ";
			}
			/*
			 * give some string to client
			 */
			objOutStream.writeObject(signal);
			objOutStream.flush();

			try {
				if (objInStream != null)
					objInStream.close();
			} catch (IOException e) {}
			try {
				if (objOutStream != null)
					objOutStream.close();
			} catch (IOException e) {}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (this.clientSocket != null)
					this.clientSocket.close();
			} catch (IOException e) {}
		}
	}
}