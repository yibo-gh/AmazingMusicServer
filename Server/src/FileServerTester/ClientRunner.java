package FileServerTester;

import java.io.InputStream;

import FileServerTester2.BasicClient;
import Object.FileInfo;
import Object.LinkedList;




public class ClientRunner {
	public static void main(String args[]) throws Exception {
	
		System.out.println("1. basic client");
		
        int selection = 1;
        
        switch(selection) {
        case 1:
        	System.out.println("basic client selected");
        	basic();
        	break;
        }
	}
	
	public static void basic() {
		LinkedList list = new LinkedList();
		FileInfo fileinfo = new FileInfo("DongYeun", "filename", "111");
		list.add("upload");
		list.add(fileinfo);
		
		BasicClient.basicRequest(list, "filename");
	}

}
