import Testers.FunctionalityTest;
import Testers.RobustnessTest;
import Testers.VulnerabilityTest;

public class SysTest {

	public static void main (String args[]) {
		
		/**
		 * Purpose: Test code for the combination of gps and file servers
		 * Input Requirement: nothing
		 * Output: nothing
		 */

		VulnerabilityTest.main(null);
		FunctionalityTest.main(null);
		RobustnessTest.main(null);
		
	}

}