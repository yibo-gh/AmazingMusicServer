package Experiment;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileReader {

	private static FileOutputStream fos;
	private static DataInputStream dis;

	public static void main (String args[]) throws IOException {
		FileOutputStream f = 
				decodeFile(new File("/Users/yiboguo/Desktop/Yamaha-V50-Synbass-1-C2 2.wav"));
		System.out.println(f.toString());
	}
	
	private static FileOutputStream decodeFile (File f) throws IOException {
		
		fos = new FileOutputStream(f);
		dis = new DataInputStream(new FileInputStream(f));
		
		byte[] bytes = new byte[1024];
		int length = 0;
		System.out.println((length = dis.read(bytes, 0, bytes.length)) != -1);
		while((length = dis.read(bytes, 0, bytes.length)) != -1) {
			fos.write(bytes, 0, length);
			fos.flush();
			
			for (int i = 0; i < bytes.length; i++) System.out.print(bytes[i] + " ");
			System.out.println();
			
		}
		return fos;
	}
}
