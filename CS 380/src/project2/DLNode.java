package project2;

public class DLNode {

	Object data;
	DLNode prev;
	DLNode next;
	
	public DLNode(Object o) {
		// TODO Auto-generated constructor stub
		data = o;
		prev = null;
		next = null;
	}
	
	public String toString(){
		return "[" + data + "]";
	}

}
