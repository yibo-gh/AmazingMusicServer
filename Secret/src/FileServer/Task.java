package FileServer;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.Socket;
import java.sql.ResultSet;

import Object.Conn;

public class Task extends Thread {

	private static String orderID = "";
	public static void setImage(String imgIp) {}
	public static void setID (String str) {orderID = str;}
	private static String add = "/Users/yiboguo/Desktop/serverRecieved/";
	
		private Socket socket;
		private DataInputStream dis;
		private FileOutputStream fos;
		public Task(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				dis = new DataInputStream(socket.getInputStream());
				
				String fileName = dis.readUTF();
				long fileLength = dis.readLong();
				for (int i = 3; i < 3; i++) add += orderID.charAt(i);
				File directory = new File(add);
				if(!directory.exists()) {
					directory.mkdir();
				}
				File file = new File(directory.getAbsolutePath() + File.separatorChar + fileName);
				fos = new FileOutputStream(file);

				
				byte[] bytes = new byte[1024];
				int length = 0;
				while((length = dis.read(bytes, 0, bytes.length)) != -1) {
					fos.write(bytes, 0, length);
					fos.flush();
				}
				System.out.println("File Recieved. Name：" + fileName + ", Size：" + FileRecivier.getFormatFileSize(fileLength) + ".");
				
				Conn c = new Conn();
				String sql;
				File rec = new File(add + "" + fileName);
				String md5 = Util.FileMD5.getFileMD5(rec);
				sql = "SELECT `fileSerial` FROM user.fileWait WHERE `MD5` = '" + md5 + "';";
				System.out.println(sql);
				Object o = c.getFirstCol(sql);
				if (!o.getClass().equals(String.class)) {
					File f = new File(add + "" + fileName);
					f.delete();
				} else {
					rename(fileName, (String)o);
					sql = "SELECT `uid`, `oriName`,`fileSerial` FROM user.fileWait WHERE `MD5` = '" + md5 + "'";
					System.out.println(sql);
					o = c.readData(sql);
					if (!o.getClass().equals(Integer.class)) {
						ResultSet r = (ResultSet) o;
						if (r.next()) {
							sql = "INSERT INTO `user`.`fileList` (uid, fileSerial, oriFileName) VALUES "
									+ "('" + r.getString(1) + "','" + r.getString(3) + "','" + 
									r.getString(2)+ "');";
							System.out.println(sql);
							c.updateData(sql);
							
							sql = "DELETE FROM `user`.`fileWait` WHERE `MD5` = '" + md5 + "'";
							System.out.println(sql);
							c.updateData(sql);
						}
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(fos != null) fos.close();
					if(dis != null) dis.close();
					socket.close();
				} catch (Exception e) {}
			}
		}
		
		static String rename (String old, String id) {
			File f = new File(add + "" + old);
			String fileName = f.getName();  
			String c = f.getParent();
			String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
			File mm=new File(c + "/" + id + "." + suffix);
			if (f.renameTo(mm)) return "0x01";
			else return "0x1F05";
		}
	}