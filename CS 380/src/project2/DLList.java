package project2;

public class DLList {
	
	public DLNode head;
	public DLNode tail;

	public DLList() {
		// TODO Auto-generated constructor stub
		head = null;
		tail = null;
	}
	
	public void append(DLNode newNode){
		if (head == null) { // List empty
		      head = newNode;
		      tail = newNode;
		   }
		   else {
		      tail.next = newNode;
		      newNode.prev = tail;
		      tail = newNode;
		   }
	}
	
	public void Prepend(DLNode newNode) {
		   if (head == null) { // List empty
			      head = newNode;
			      tail = newNode;
			   }
			   else {
			      newNode.next = head;
			      head.prev = newNode;
			      head = newNode;
			   }
			}
	
	public void insertAfter(DLNode curNode, DLNode newNode) {
		DLNode sucNode = null;
		
		   if (head == null) { // List empty
		      head = newNode;
		      tail = newNode;
		   }
		   else if (curNode == tail) { // Insert after tail
		      tail.next = newNode;
		      newNode.prev =tail;
		      tail = newNode;
		   }
		   else { 
		      sucNode = curNode.next;
		      newNode.next = sucNode;
		      newNode.prev = curNode;
		      curNode.next = newNode;
		      sucNode.prev = newNode;
		   }
		}
	
	public void remove(DLNode curNode) {
		DLNode sucNode;
		DLNode predNode;
		
		sucNode = curNode.next;
		predNode = curNode.prev;
				   
		if (sucNode != null) {
			sucNode.prev = predNode;   
				  }
				   
		if (predNode != null) {
			predNode.next = sucNode;
				  }
				   
		if (curNode == head) { // Removed head
		 	head = sucNode;
				  }
				   
	 	if (curNode == tail) { // Removed tail
			tail = predNode;
				  }
		}
	
	public DLNode search(Object key) {
		
		DLNode curNode = head;
		
			while (curNode != null) {
				if (curNode.data == key) {
					return curNode;
				}
			curNode = curNode.next;
			}
			
		return null;
	}
	
	public String toString(){
		/*
		 * TODO: 
		 * Make sure you make it so the
		 * first node is prints "X<-"
		 * and the last node prints "->X"
		 * 
		 */
	 	DLNode n = head; 
	 	String temp = new String();

	 	
	 	temp += "X<-";
	 	while (n != null) {
	 		if (n.next != null){
	 		temp += n.toString() + "<=>";
	 		}
	 		else{
	 			temp += n.toString();
	 		}
	 		n = n.next;
	 		if (n == null){
	 			temp += "->"; 
	 			}
	 		}
	 	return temp + "X";
	 
	}

	public String reverseString(){
		DLNode n = tail;
		String temp = new String();
		
		temp = "->";
		while (n != null) {
	 		temp += n + "<=>";
	 		n = n.next;
	 		
	 		if (head ==null){
	 			temp = "<-"; 
	 			}
	 		}
		return temp + "X";
	}
	
	public void insertAfterNew(DLNode curNode, DLNode newNode){
		if (curNode == null){
			Prepend(newNode);
		}
		else{
			insertAfter(curNode, newNode);
		}
	}
	
	
	
	
}
