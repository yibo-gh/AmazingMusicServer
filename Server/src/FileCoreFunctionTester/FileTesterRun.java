package FileCoreFunctionTester;

import Server.RealFileServer;

public class FileTesterRun {
	public static void main(String args[]) {
		
		/**
		 * Purpose: Run a general purpose server (before run the ClientRequestTester.java)
		 * Input requirement: None
		 * Output: None
		 */
			
		new RealFileServer();
	}
}