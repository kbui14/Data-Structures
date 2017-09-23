package project2;

public class SLNode {

	public Object data;
	public SLNode next;
	
	public SLNode(Object o) {
		// TODO Auto-generated constructor stub
		data = o;
		next = null;
	}
	
	
public String toString(){
	return "[" + data.toString() + "]";
}

}
