package Testers;

import Object.LinkedList;

public class LinkedListTest {
	
	/**
	 * Purpose: This is a test for the LinkedList
	 * Input Requirement:nothing
	 * Return: True or False
	 */
	
	public static void main (String[] args) {
		LinkedListTester();
	}
	
	static void LinkedListTester() {
		
		LinkedList ll = new LinkedList();
		int length = (int) (Math.random() * 10 + 1);
		int[] iniArr = new int[length];
		
		for (int i = 0; i < length; i++) {
			int j = (int) (Math.random() * 1000);
			ll.add(j);
			iniArr[i] = j;
		}
		
		if (length != ll.getLength() || iniArr.equals(ll.toArray())) {
			System.out.println("LinkedList initial failed.");
			return;
		} else System.out.println("LinkedList initial success.");
		
		int deleteItem = (int) (Math.random() * 10);
		for (;deleteItem >= ll.getLength();) deleteItem = (int) (Math.random() * 10);
		
		int task = -0xFF;
		Object[] obj = ll.toArray();
		
		task = 0x01;
		
		// comObj is the object array operated by local array, not transformed from LinkedList
		
		Object[] comObj = deleteItemFromObjArray(obj, deleteItem);
		obj = deleteItemFromLinkedList(obj, deleteItem, ll, task);
		if (!isObjArrSame(comObj, obj) || ll.getLength() != comObj.length) {
			System.out.println("LinkedList deleting with index error.");
			return;
		} System.out.println("LinkedList deleting with index success!");
		
		deleteItem = (int) (Math.random() * 10);
		for (;deleteItem >= ll.getLength();) deleteItem = (int) (Math.random() * 10);
		obj = ll.toArray();
		
		task = 0x02;
		comObj = deleteItemFromObjArray(obj, 0);
		obj = deleteItemFromLinkedList(obj, 0, ll, task);
		if (!isObjArrSame(comObj, obj) || ll.getLength() != comObj.length) {
			System.out.println("LinkedList deleting with element error");
			return;
		} System.out.println("LinkedList deleting with element success!");
		System.out.println("LinkedList object pass!");
	}
	
	private static Object[] deleteItemFromLinkedList(Object[] obj, int index, LinkedList ll, int t) {
		if (t < 0x01 || t > 0x02) return null;
		if (t == 0x01) {
			ll.delete(index);
			return ll.toArray();
		}
		else if (t == 0x02) {
			ll.delete(obj[index]);
			return ll.toArray();
		}
		else return null;
	}
	
	private static Object[] deleteItemFromObjArray(Object[] obj, int index) {
		Object[] o = new Object[obj.length - 1];
		for (int i = 0; i < obj.length; i++) {
			if (i < index) {
				o[i] = obj[i];
			} else if (i > index) {
				o[i-1] = obj[i];
			}
		}
		return o;
	}
	
	private static boolean isObjArrSame(Object[] o1, Object[] o2) {
		if (o1.length != o2.length) return false;
		for (int i = 1; i < o1.length; i++) {
			if (!o1[i].equals(o2[i])) return false;
		}
		return true;
	}
}
