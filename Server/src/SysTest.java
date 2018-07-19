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
									  // ''라고 쓴 뒤 insert하면 출력할 때는 '라고 정상 출력된다.
									  // SQL 에 넣을 때만 '를 ''로 바꿔서 넣자. String.replace 사용
		//RobustnessTest.main(null);
	}

}