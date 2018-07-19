package Testers;

public class DecoderTest {
	
		/*
		 * This test code may be no longer working.
		 */
		public static void main (String[] args) {
			DecoderTest();
		}

		static void DecoderTest() {
			Object oT = null;
			Object obj = randomElement(oT);
		}
		
		public static Object randomElement(Object o){
			int randomInt = (int) (Math.random() * 100);
			for (;randomInt <= 0x1F || randomInt >= 0x7F;) randomInt = (int) (Math.random() * 100);
			o = (char) randomInt;
			return o;
		}
	}
