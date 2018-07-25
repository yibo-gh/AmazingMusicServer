package Object;

public class Node implements java.io.Serializable {
	
	private static final long serialVersionUID = -6137463104229125442L;
	
	/**
	 * This node is used to hold the information for server, client, read file and so on.
	 */
	
	private Object info;
	Node prev;
	Node next;

	/**
	 * Node(Object o) is a constructor function for Node above
	 */
	
	public Node() {}
	
	public Node(Object o) {
		this.info = o;
		this.prev = null;
		this.next = null;
	}
	
	public Object getInfo(){
		
		/**
		 *  Purpose: This function which used to get the information from users or requests.
		 *  Input Requirement: This function does not require an input.
		 *  Output: This function return the Object.
		 */
		
		return this.info;
	}
	
}