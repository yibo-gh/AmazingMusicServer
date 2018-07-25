package CoreServer;

import Object.LinkedList;
import Object.Node;
import Object.Conn;
import Object.User;
import Object.FileTrans;

public class Core {

	public static Object register(LinkedList ll) {
		Node n = ll.head;
		if (n.getInfo() == null) return 0x3000;
		Object o = n.getInfo();
		if (!o.getClass().equals(User.class)) return 0x3000;
		User u = (User) o;
		if (u.getStatusCode() != 0x2F03) return 0x3105;
		String sql;
		sql = "INSERT INTO user.info (`emailUser`, `emailDomain`, `uid`, `password`) VALUES ('"+ 
				u.getEmailUser().toLowerCase() + "','" + u.getEmailDomain().toLowerCase() + "','" +
				u.getID().toLowerCase() + "','" + Util.MD5Util.crypt(u.getPasscode()) + "');";
		Conn c = new Conn();
		o = c.updateData(sql);
		if (o.equals(0x2F01)) return 0x2F03;
		else if (o.equals(0x3105)) return u.getStatusCode();
		else return o;
	}
	
	public static Object login (LinkedList ll) {
		Node n = ll.head;
		if (n.getInfo() == null) return 0x3000;
		Object o = n.getInfo();
		if (!o.getClass().equals(User.class)) return 0x3000;
		User u = (User) o;
		if (u.getStatusCode() != 0x3101) return 0x3202;
		String sql;
		sql = "SELECT `password` FROM `user`.`info` WHERE `emailUser` = "
				+ "'" + u.getEmailUser() + "' AND `emailDomain` = '" + u.getEmailDomain() + "';";
		Conn c = new Conn();
		if (c.getFirstCol(sql).equals(Util.MD5Util.crypt(u.getPasscode()))) return 0x2F04;
		else return 0x3201;
	}
	
	public static Object uploadFile(LinkedList ll) {
		/** 
		 * ll: File Serial, MD5, name, extention, user
		 * Please DO NOT start upload until received 0x01
		 */
		Node n = ll.head;
		Object o = n.getInfo();
		if (!o.getClass().equals(FileTrans.class)) return 0x3000;
		
		FileTrans ft = (FileTrans) o;
		String sql = "INSERT INTO user.fileWait (`MD5`,`fileSerial`,`uid`,`oriName`) VALUE "
				+ "('" + ft.getMD5() + "','" + ft.getSer()+ "','" + ft.getUid() + "','"
				+ ft.getFileName() + "');";
		
		Conn c = new Conn();
		o = c.updateData(sql);
		if (o.equals(0x2F01)) return 0x01;
		else return o;
	}
}
