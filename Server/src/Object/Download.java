package Object;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Download {

	private String url, filename, sysUsername;
	
	public Download(String u, String f) {
		this.url = u;
		this.filename = f;
		this.sysUsername = System.getProperty("user.name");
	}
	
	public void start() {
		try {
			URL website = new URL(this.url);
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			
			String localCache = "C:\\Users\\" + this.sysUsername + "\\AppData\\AmaMusic\\";
			File dir = new File(localCache);
			if (!dir.exists())
				dir.mkdirs();
			FileOutputStream fos = new FileOutputStream(localCache + this.filename + this.url.substring(this.url.lastIndexOf(".")));
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
