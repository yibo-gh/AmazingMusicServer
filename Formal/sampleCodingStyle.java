public class sampleCodingStyle {
	//A blank line here is required.
	
	public static void main (String[] args) {
		//Blank line here is optional but is recommanded for a clean code.
		/**
		 * This paragraph will print the int in our target object.
		 */
		sampleObject so = new sampleObject(1);
		int objectInt = so.getInt();
		System.out.println(objectInt);
		
		/**
		 * This paragraph will print what our object what to tell us.
		 */
		System.out.println(so.getString());
	}
	//Blank lines after funcion ends and code ends are optional.
}
