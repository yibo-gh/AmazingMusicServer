import Testers.FunctionalityTest;
import Testers.RobustnessTest;
import Testers.ValidationTest;
import Testers.SearchTest1;

public class SysTest {

	public static void main (String args[]) {
		
		/**
		 * Purpose: Test code for the combination of gps and file servers
		 * Input Requirement: nothing
		 * Output: nothing
		 */

		/*
		 * Things to change
		 * Download.java - localCache
		 * FunctionalityTest.java - new Download(~here~); (url part)
		 */
		ValidationTest.main(null);
		//FunctionalityTest.main(null);
		//RobustnessTest.main(null);
	}

}