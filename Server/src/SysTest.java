import Testers.FunctionalityTest;
import Testers.RobustnessTest;
import Testers.ValidationTest;

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
		 * FunctionalityTest.java - new Download(~here~);
		 */
		
		//ValidationTest.main(null);
		FunctionalityTest.main(null); // minor bug: if filename contains ', SQL rejects. Solution: ' -> ''
									  // ''��� �� �� insert�ϸ� ����� ���� '��� ���� ��µȴ�.
									  // SQL �� ���� ���� '�� ''�� �ٲ㼭 ����. String.replace ���
		//RobustnessTest.main(null);
	}

}