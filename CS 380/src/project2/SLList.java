package project2;

public class SLList {

	private SLNode head;
	private SLNode tail;
	
	SLList() {
		head = null;
		tail = null;
	
	}

	public void append(SLNode newNode){
		if (head == null){
			head = newNode;
			tail = newNode;
		}
		else {
			tail.next = newNode;
			tail = newNode;
		}
	}
	
	
	public void prepend(SLNode newNode){
		if(head == null){
			head = newNode;
			tail = newNode;
		}
		else {
			newNode.next = head;
			head = newNode;
		}
	}
	
	public void insertAfter(SLNode curNode,SLNode newNode){
		if (head == null){
			head = newNode;
			tail = newNode;
		}
		else if (curNode == tail){
			tail.next = newNode;
			tail = newNode;
		}
		else {
			newNode.next = curNode.next;
			curNode.next = newNode;
		}
	}
	public void removeAfter(SLNode curNode){
		
		SLNode sucNode = null;
		
		 if (curNode == null && head != null) {
		      sucNode = head.next;
		      head = sucNode;
		      
		      if (sucNode == null) { // Removed last item
		         tail = null;
		      }
		   }
		   else if (curNode.next != null) {
		      sucNode = curNode.next.next;
		      curNode.next = sucNode;
		      
		      if (sucNode == null) { // Removed tail
		         tail = curNode;
		      }
		   }
		}

	public SLNode search(Object key) {
		   SLNode curNode = head;
		   while (curNode != null) {
		      if (curNode.data == key) {
		         return curNode;
		      }
		      curNode = curNode.next;
		   }
		   return null;
		}
	
 public String toString(){
	 
	 	SLNode n = head; 
	 	String temp = new String();

	 	while (n != null) {
	 		temp += n + "->";
	 		n = n.next;

	 		}
	 	return temp + "X";
	 
 }
 
 public void stackPush(SLNode newItem){
	 prepend(newItem); // Insert as list head (top of stack)
 }

 public SLNode stackPop() {     
    SLNode poppedItem = head;    // Copy list head (top of stack)
    removeAfter(null);   // Remove list head  
    return poppedItem;           // Return popped item
 }
 
 public void queuePush(SLNode newItem) {
	   append(newItem); // Insert as list tail (end of queue)
	}

public SLNode queuePop() {
	   SLNode poppedItem = head;   // Copy list head (front of queue)
	   removeAfter(null);  // Remove list head
	   return poppedItem;         // Return popped item
	}

public void push(Object newData){
	stackPush(new SLNode(newData));
}

public Object pop(){
	SLNode temp = stackPop();
	if (temp != null)
		return temp.data;
	return null;	
}

public void enqueue(Object newData){
	queuePush(new SLNode(newData));
}

public SLNode dequeue(){
	SLNode poppeditem;
	poppeditem = head;
	removeAfter(null);
	return poppeditem;
}

public void insertAfterNew (SLNode curNode, SLNode newNode){
	if (curNode == null){
		prepend(newNode);
	}
	else{
		insertAfter(curNode, newNode);
	}
	
	
}

}

