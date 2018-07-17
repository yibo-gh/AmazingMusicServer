package FileServerTester;

import java.io.File;
import java.io.InputStream;

import FileServerTester2.BasicClient;
import FileServerTester2.FileGiveClient;
import Object.FileInfo;
import Object.LinkedList;




public class ClientRunner {
	public static void main(String args[]) throws Exception {
	
		System.out.println("1. basic client");
		
        int selection = 2;
        
        switch(selection) {
        case 1:
        	System.out.println("basic client selected");
        	basic();
        	break;
        case 2:
        	System.out.println("file give client selected");
        	fileGive();
        }
	}
	
	public static void basic() {
		LinkedList list = new LinkedList();
		FileInfo fileinfo = new FileInfo("DongYeun", "", "111");
		list.add("upload");
		list.add(fileinfo);
		
		BasicClient.basicRequest(list, "");
	}
	
	public static void fileGive() {
		LinkedList list = new LinkedList();
		File f = new File("/Users/yiboguo/Desktop/Yoona.jpg");
		FileInfo fileinfo = new FileInfo("11111", f.getAbsolutePath(), API.MD5Class.FileMD5Generator(f));
		list.add("upload");
		list.add(fileinfo);
		
		FileGiveClient.fileGiveRequest(list, "/Users/user/Desktop/fileReceiveServer.txt.txt");
	}

}
