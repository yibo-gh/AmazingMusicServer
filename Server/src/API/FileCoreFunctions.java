package API;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.DigestInputStream;
import java.io.InputStream ;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import Object.FileInfo;
import Object.LinkedList;
import Object.User;
import SQLpackage.Database;


public class FileCoreFunctions {
   
   public static LinkedList upload (LinkedList ll) {
      
      Object u = ll.head.getInfo();
      
      FileInfo flInfo = (FileInfo) u;
      
      System.out.println("asdf");
      
      System.out.println(flInfo.getUID());
      System.out.println(flInfo.getOriName());
      System.out.println(flInfo.getMD5());
      
      if(flInfo.getOriName() != "") {
         LinkedList list = new LinkedList();
         list.add("OK");
         list.add(flInfo);
         
         System.out.println(list.getClass());
         System.out.println(list.end.getInfo());
         
         File directory = new File("temporary");
         if(!directory.exists()) {
            directory.mkdir();
         }
         
         return list;
      }
      else {
         ll.add("NO");
         return ll;
      }
   }
   
   public static String download (LinkedList ll) {
      
      Object u = ll.head.getInfo();
      
      FileInfo flInfo = (FileInfo) u;
      
      System.out.println(flInfo.getUID());
      System.out.println(flInfo.getOriName());
      System.out.println(flInfo.getMD5());
      
      return "downloaded";
   }
   
   public static String validate(FileInfo flInfo) {
      String path = "temporary" + File.separatorChar + flInfo.getFileSerial();
      System.out.println(path);
      File file = new File(path);
      String md5Value = MD5Class.FileMD5Generator(file);
      System.out.println(md5Value);
      
      Database db = null;
      ResultSet rs = null;
      String output = "";
      File music = new File(path);
      
      try {
         db = new Database();
         
         System.out.println("select MD5 from waitingfile where MD5='" + flInfo.getMD5() + "'");
         rs = db.readDB("select MD5 from waitingfile where MD5='" + flInfo.getMD5() + "'");
         //System.out.println(rs);
         if(!rs.next()) {
            System.out.println("there is not matching MD5 value file");
            rs.close();
            
            if(music.delete())
              {
                  System.out.println("File deleted from temp successfully");
              }
              else
              {
                  System.out.println("Failed to delete the file from temp");
              }
            output = "VALIDATE FAIL";
            return output;
         }
         else {
            String result = db.updateDB("delete from waitingfile where MD5 = '" + flInfo.getMD5() + "'");
            System.out.println(result);
            String inPostFile = "insert into postfile (fileSerial, uid, oriName) "
                  + "values ('" + flInfo.getFileSerial() + "', '" + flInfo.getUID() + "', '"
                  + flInfo.getOriName() + "')";
            System.out.println(inPostFile);
            result = db.updateDB(inPostFile);
            
            File uidDir = new File(flInfo.getUID());
            if(!uidDir.exists()) {
               uidDir.mkdir();
            }
            
            String newPath = flInfo.getUID() + File.separatorChar + flInfo.getFileSerial();
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