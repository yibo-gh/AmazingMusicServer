package API;

import java.io.File;
import java.sql.ResultSet;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import Object.FileInfo;
import Object.LinkedList;
import SQLpackage.Database;
import Util.MD5Class;

public class FileCoreFunctions {
	
	public static LinkedList upload (LinkedList ll) {
		/**
		 * Purpose : get linkedlist from the decoder and check whether the file has name.
		 * if there is name not null, then the server is now able to get file from the client
		 * input : linked list that head is request client give, next node is fileInfo that client give.
		 * output : linked list
		 */
		
		Object u = ll.head.getInfo();
   
		FileInfo flInfo = (FileInfo) u;
      
		/*
		 * if file name is not null, make "temporary" directory
		 * and give linked list to function 'createFileServerThread'
		 */
		
		if(flInfo.getOriName() != "") {
			LinkedList list = new LinkedList();
			list.add("OK");
			list.add(flInfo);
 
			File directory = new File("/Volumes/Common Volume/Sites/https://yg-home.site/proj/amamusic/audio/temporary");
			if(!directory.exists()) {
				directory.mkdir();
			}
			return list;
		}
		else {
			return ll;
		}
	}
   
	
	public static String validate(FileInfo flInfo) {
		/**
		 * Purpose : Compare the md 5 value of the file received from the client to 
		 * the md 5 value stored in waitingfile to determine if the value is the same.
		 * input : file information that client give
		 * output : string 
		 */
		
		/*
		 * get MD5 value of file that client give
		 */
		String path = "/Volumes/Common Volume/Sites/https://yg-home.site/proj/amamusic/audio/temporary" + File.separatorChar + flInfo.getFileSerial() +flInfo.getExension();
		File file = new File(path);
		String md5Value = MD5Class.FileMD5Generator(file);
		System.out.println(md5Value);
      
		Database db = null;
		ResultSet rs = null;
		String output = "";
		File music = new File(path);
		
		try {
			db = new Database();         
			System.out.println("select MD5 from `amazingmusicdb`.`waitingfile` where MD5='" + flInfo.getMD5() + "'");
			
			/*
			 * read MD5 value in the Database 'waiting file'
			 */
			rs = db.readDB("select MD5 from `amazingmusicdb`.`waitingfile` where MD5='" + flInfo.getMD5() + "'");
			
			
			/*
			 * if there isn't matching MD5 value, delete the music in the temporary file that client
			 * give to file server.  
			 */
			if(!rs.next()) {
				System.out.println("there is not matching MD5 value file");
				rs.close();
				
				if(music.delete()){
					System.out.println("File deleted from temp successfully");
				}
				else{
					System.out.println("Failed to delete the file from temp");
				}
				output = "VALIDATE FAIL";
				return output;

			}
			/*
			 * if there is matching MD5 value, delete the Database of matching file in the waitingfile,
			 * insert the corresponding data to DB postfile, and move the music file in the temporary file
			 * to file named client's uid.
			 */
			else {
				/*
				 * delete information in DB waitingfile
				 */
				String result = db.updateDB("delete from `amazingmusicdb`.`waitingfile` where MD5 = '" + flInfo.getMD5() + "'");
				System.out.println(result);
				
				/*
				 * insert information corresponding to file to DB postfile
				 */
				
				String oriName = flInfo.getOriName().replaceAll("'", "''");
				String inPostFile = "insert into `amazingmusicdb`.`postfile` (fileSerial, uid, oriName) "
						+ "values ('" + flInfo.getFileSerial() + "', '" + flInfo.getUID() + "', '"
						+ oriName + "')";
				System.out.println(inPostFile);
				result = db.updateDB(inPostFile);
				
				/*
				 * if there isn't directory named uid, make it.
				 */
				File uidDir = new File("/Volumes/Common Volume/Sites/https://yg-home.site/proj/amamusic/audio/" + flInfo.getUID());
				if(!uidDir.exists()) {
					uidDir.mkdir();
				}
				
				/*
				 * move music from temporary file to uid named file
				 */
				String newPath = "/Volumes/Common Volume/Sites/https://yg-home.site/proj/amamusic/audio/" + flInfo.getUID() + File.separatorChar + flInfo.getFileSerial() + flInfo.getExension();
				System.out.println(newPath);
				Files.move(Paths.get(path), Paths.get(newPath), StandardCopyOption.REPLACE_EXISTING);
				
				output = "VALIDATE SUCCESS";
				return output;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return output;
		}
	}
}