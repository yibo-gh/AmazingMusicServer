package Object;

public class LinkedList implements java.io.Serializable{
	
	private static final long serialVersionUID = -2032861969176529258L; //This is required by java.io.Serializable interface, and is generalist by Eclipse.
	
	public Node head;
	public Node end;
	private int length;
	
	public LinkedList(){
		this.head = null;
		this.end = null;
		this.length = 0;
	}
	
	public void add(Object o){
		
		/**
		 * Purpose: This function is adding new input stuffs into the LinkedList.
		 * Input Requirement: This function requires a Object as parameter to run.
		 * Return: This function should returns nothing.
		 */
		
		Node add = new Node(o);
		
		if(head == null){
			head = add;
		}
		if(end == null){
			end = add;
		}
		else{
			end.next = add;
			add.prev = end;
			add.next = null;
			end = add;
		}
		length++;
	}
	
	public void delete (Object o) {
		
		/**
		 * This function is used to deleting the node in the LinkedList
		 * Input Requirement: This function requires a Object as parameter to run.
		 * Return: This function should returns nothing.
		 */
		
		if (this.head == null) return;
		Node current = this.head;
		while (current != null) {
			if (current.getInfo().equals(o)){
				Object obj = voidNode(current);
				if (obj.equals("0x1101")) {
					voidList();
					return;
				}
				this.length--;
			}
			current = current.next;
		}
	}
	
	public void delete (int index) {
		
		/**
		 * Purpose: This function is used for deleting the node in the LinkedList
		 * Input Requirement: This function requires a number as the parameter to run.
		 * Return: This function should returns nothing.
		 */
		
		if (this.head == null) return;
		
		Node current = this.head;
		int i = 0;
		while (current != null) {
			if (i == index) {
				if (voidNode(current).equals("0x1101")) {
					voidList();
					return;
				}
				this.length--;
				return;
			}
			i++;
			current = current.next;
		}
	}
	
	private Object voidNode(Node n) {
		
		/**
		 * Purpose: This function is deleting selected Node from the LinkedList
		 * Input Requirement: This function is expecting a Node to be deleted as parameter.
		 * Output: Returning of this function varies. Please see paragraph comments for more information.
		 */
		
		/*
		 * The following paragraph is the base case of this function.
		 * If the inputed Node is null or is the only element in the function, then return true or an error code depends on the situation.
		 */
		if (n == null) return true;
		if (n.prev == null && n.next == null) return "0x1101";
		
		/*
		 * The following code is the real functional part which is deleting node from a LinkedList except in above situations.
		 * When deleting finishes, function will return true.
		 */
		if (n.prev != null && n.next == null) {
			n.prev.next = null;
			this.end = n.prev;
		} else if (n.prev == null && n.next != null) {
			n.next.prev = null;
			this.head = n.next;
		} else if (n.prev != null && n.prev != null) {
			n.prev.next = n.next;
			n.next.prev = n.prev;
		} else return false;
		return true;
	}
	
	private void voidList () {
		
		/**
		 * Purpose: This function is cleaning up the LinkedList.
		 * Input Requirement: This function does not require an input.
		 * Return: This function should returns nothing.
		 */
		
		this.head = null;
		this.end = null;
		this.length = 0;
	}
	
	public int getLength() {
		return this.length;
	}
	
	public Object[] toArray(){
		Object[] obj = new Object[this.length];
		
		Node temp = this.head;
		int i = 0;
		while (temp != null) {
			obj[i] = temp.getInfo();
			i++;
			temp = temp.next;
		}
		
		return obj;
	}
}