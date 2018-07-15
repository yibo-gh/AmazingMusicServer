package FileServerTester;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class FileClientOperater {
	
	@SuppressWarnings("finally")
	public static void request(int selection) {
		try {
			switch(selection) {
			case 1:
				System.out.println("adf");
				System.out.println("Client wants to send file");
	
				String FileName1 = "/Users/user/Desktop/fileReceiveServer.txt.txt";
				System.out.println(FileName1);
				FileGiveClient2.fileGive(FileName1);
				break;
			case 2:
				System.out.println("Client wants to get file from server");
	
			    String FileName2 = "fileReceiveServer.txt.txt";
				System.out.println(FileName2);
			    
			    FileReceiveClient.fileReceive(FileName2);
			    break;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	public static void main(String[] args) {
		request(1);
	}
}

