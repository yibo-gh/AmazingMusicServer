package Request;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class RequestTest2 {

	public static void main(String[] args) throws IOException {
		/*
		System.out.println(Request.login("ciy405x@kaist.ac.kr", "cofls8680*")); // NOTREGISTERED
		System.out.println(Request.register("ciy405x@kaist.ac.kr", "cofls8680*"));
		System.out.println(Request.login("icho4@ucsc.edu", "Wlgn0504"));
		System.out.println(Request.register("icho4@ucsc.edu", "cofls8680*"));
		System.out.println(Request.login("icho4@ucsc.edu", "Wlgn0504"));
		System.out.println(Request.login("icho4@ucsc.edu", "cofls8680*"));
		System.out.println(Request.upload(Request.login("icho4@ucsc.edu", "cofls8680*"), "C:\\Users\\ï¿½Î¿ï¿½\\Desktop\\DailyScrum&Plan\\Plan15.7.18.txt"));
		System.out.println(Request.upload(Request.login("icho4@ucsc.edu", "cofls8680*"), "C:\\Users\\ï¿½Î¿ï¿½\\Desktop\\DailyScrum&Plan\\Plan15.7.txt"));
		*/
		
/*		String[] testCases = {"icho@ucsc.edu", "ciy405x@kaist.ac.kr", "me@gmail.com", 
				"me@company.co.uk", "inyoungh@naver.com", "Iam@g.com", 
				"inyoung@navrr.cor", "inyoun@kai.ac.kr", "inyoun@k.ac.kr"};
		for (int i=0; i < testCases.length; i++) {
			System.out.println(Request.login(testCases[i], "c1235"));
		}*/
		
		
/*		String url = "http://pds.joins.com/news/component/ilgan_isplus/201702/17/2017021711003995900.jpeg";
		URL website = new URL(url);
		ReadableByteChannel rbc = Channels.newChannel(website.openStream());
		FileOutputStream fos = new FileOutputStream("C:\\Users\\ÀÎ¿µ\\Desktop\\eunseo2.jpeg");
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);*/
	}

}
