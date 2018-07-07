package Object;

public class LinkedList {
	
	private Node head;
	private Node end;
	private int length;
	
	public void linkedList(){
		this.head = null;
		this.end = null;
		this.length = 0;
	}
	
	public void add(Object o){
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
		if (this.head == null) return;
		
		Node current = this.head;
		while (current != null) {
			if (current.getInfo().equals(o)){
				Object obj = voidNode(current);
				if (obj.equals("0x1101")) {
					voidList();
					return;
				}
			}
			current = current.next;
		}
	}
	
	public void delete (int index) {
		if (this.head == null) return;
		
		Node current = this.head;
		int i = 0;
		while (current != null) {
			if (i == index) {
				if (voidNode(current).equals("0x1101")) {
					voidList();
					return;
				}
				return;
			}
			i++;
		}
	}
	
	private Object voidNode(Node n) {
		if (n == null) return true;
		if (n.prev == null && n.next == null) return "0x1101";
		
		if (n.prev != null) n.prev.next = n.next;
		else n.next.prev = n.prev;
		if (n.next != null) n.next.prev = n.prev;
		else n.prev.next = null;
		return true;
	}
	
	private void voidList () {
		this.head = null;
		this.end = null;
	}
}