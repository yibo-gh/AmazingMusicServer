package Testers;

import Object.LinkedList;
import Object.Node;

public class DecoderTest {
		
		public static void main (String[] args) {
			DecoderTest();
		}

		static void DecoderTest() {
		Object oT;
		Object obj = randomElement(oT);
		}
		
		public Object randomElement(Object o){
			int randomInt = (int) (Math.random() * 100);
			for (;randomInt <= 0x1F || randomInt >= 0x7F;) randomInt = (int) (Math.random() * 100);
			o = (char) randomInt;
			return o;
		}
	}