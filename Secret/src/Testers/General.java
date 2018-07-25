package Testers;

import java.io.File;
import Object.LinkedList;
import Object.User;
import Object.Conn;
import Object.Downloader;
import Object.FileTrans;

public class General {

	public static void main (String args[]) {
		testDownload();
	}
	
	private static void createUser() {
		User u = new User("yoona@snsd.or.kr", "loveMyYoona!");
		LinkedList ll = new LinkedList();
		ll.add(u);
		System.out.println(Util.ReturnCodeChecker.getCodeType((int)CoreServer.Core.register(ll)));
	}
	
	private static void userLogin() {
		User u = new User("yoona@snsd.or.kr", "loveMyYoona!");
		LinkedList ll = new LinkedList();
		ll.add(u);
		System.out.println(Util.ReturnCodeChecker.getCodeType((int)CoreServer.Core.login(ll)));
	}
	
	private static void autoUpload () {
		String add = "/Users/yiboguo/Desktop/Yoona.jpg";
		File f = new File(add);
		String uid = "snokxyoona";
		String m = Util.FileMD5.getFileMD5(f);
		String n = "Yoona";
		String e = "jpg";
		FileTrans ft = new FileTrans(uid, m, n, e);
		
		LinkedList ll = new LinkedList();
		ll.add(ft);
		
		String[] str = {add};
		if (CoreServer.Core.uploadFile(ll).equals(0x01)) Client.ImageTester.main(str);
	}
	
	private static void testLastCol() {
		Conn c = new Conn();
		String sql;
		sql = "SELECT * FROM `user`.`info` WHERE `emailDomain` = 'snsd.or.kr';";
		Object o = c.getLastCol(sql);
		System.out.println(o);
	}
	
	private static void testIniBase() {
		Conn c = new Conn();
		Object o = c.iniDatabase();
		System.out.println(Util.ReturnCodeChecker.getCodeType((int)o));
	}
	
	private static void testDownload() {
		new Downloader ("http://anybuy.app/img/KR10000001.jpg",
				"Yoona.jpg", "/Users/yiboguo/Desktop").start();
	}
}
