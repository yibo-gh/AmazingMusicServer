package Client;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class FileClient {
	
	@SuppressWarnings("finally")
	public static void request(int selection) {
		try {
			switch(selection) {
			case 1:
				System.out.println("adf");
				System.out.println("Client wants to send file");
				//Scanner sc1 = new Scanner(System.in);

			    //System.out.print("Enter File name : ");
			    //String FileName1 = sc1.nextLine();
				String FileName1 = "/Users/user/Desktop/fileReceiveServer.txt.txt";
				System.out.println(FileName1);
				FileGiveClient.fileGive(FileName1);
				break;
			case 2:
				System.out.println("Client wants to get file from server");
				//Scanner sc2 = new Scanner(System.in);

			    //System.out.print("Enter File name : ");
			    //String FileName2 = sc2.nextLine();
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
		request(2);
	}
}
