package Testers;

public class GeneralPurposeTester {

	public static void main (String args[]) {
		int randomInt = (int) (Math.random() * 100);
		for (;randomInt <= 0x1F || randomInt >= 0x7F;) randomInt = (int) (Math.random() * 100);
		char c = (char) randomInt;
		System.out.println(randomInt + ": " + c);
	}
}
