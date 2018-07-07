package Object;

public class Node {
	
	/**
	 * This node is used to hold the information for server, client, read file and so on.
	 */
	
	private Object info;
	Node prev;
	Node next;

	/**
	 * Node(Object o) is a constructor function for Node above
	 */
	
	Node(Object o) {
		this.info = o;
		this.prev = null;
		this.next = null;
	}
	
	/**
	 *  getinfo() is a function which used to get the information from users or requests.
	 */
	
	public Object getInfo(){
		return this.info;
	}
	
}