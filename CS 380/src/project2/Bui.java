package project2;

public class Bui {

	static final int N = 4;

	public static void main(String[] args) {
		testSLList();
		testDLList();
		testStackQueue();
	}
	
	public static SLNode head;
	public static SLNode tail;
	
	public void SLList() {
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

	public SLNode search1(Object key) {
		   SLNode curNode = head;
		  while (curNode != null) {
		      if (curNode.data == key) {
		         return curNode;
		      }
		      curNode = curNode.next;
		   }
		   return null;
		}
	
 public String toString1(){
	 
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
    SLNode poppedItem = head;   // Copy list head (top of stack)
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
	
		public static DLNode head1;
		public static DLNode tail1;

	public static void DLList() {
		// TODO Auto-generated constructor stub
		head1 = null;
		tail1 = null;
	}
	
	public void append(DLNode newNode){
		if (head1 == null) { // List empty
		      head1 = newNode;
		      tail1 = newNode;
		   }
		   else {
		      tail1.next = newNode;
		      newNode.prev = tail1;
		      tail1 = newNode;
		   }
	}
	
	public void Prepend(DLNode newNode) {
		   if (head1 == null) { // List empty
			      head1 = newNode;
			      tail1 = newNode;
			   }
			   else {
			      newNode.next = head1;
		      head1.prev = newNode;
			      head1 = newNode;
			   }
			}
	
	public void insertAfter(DLNode curNode, DLNode newNode) {
		DLNode sucNode = null;
		
		   if (head1 == null) { // List empty
		      head1 = newNode;
		      tail1 = newNode;
		   }
		   else if (curNode == tail1) { // Insert after tail
		      tail1.next = newNode;
		      newNode.prev =tail1;
		      tail1 = newNode;
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
				   
		if (curNode == head1) { // Removed head
		 	head1 = sucNode;
				  }
				   
	 	if (curNode == tail1) { // Removed tail
			tail1 = predNode;
				  }
		}
	
	public DLNode search(Object key) {
		
		DLNode curNode = head1;
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
	 	DLNode n = head1; 
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
		DLNode n = tail1;
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
	
	
	
	
	
	
	

	static void testSLList() {
		
		System.out.println("Singly-Linked List");

		SLList list1 = new SLList();

		for (int i = 0; i < N; i++)
			list1.append(new SLNode(i));
		for (double d = N; d < 2 * N; d++)
			list1.append(new SLNode(d));

		System.out.println(list1);


		SLNode temp = list1.search(0);
		System.out.println(temp);

		list1.insertAfter(temp, new SLNode(1000));
		System.out.println(list1);
		
		list1.removeAfter(temp);
		System.out.println(list1);
		
		System.out.println();
	}
	
	static void testDLList() {
		
		System.out.println("Doubly-Linked List");

		DLList list2 = new DLList();

		for (int i = 0; i < N; i++) 
			list2.append(new DLNode(i));
		for (double d = N; d < 2 * N; d++)
			list2.append(new DLNode(d));

		System.out.println(list2);

		DLNode temp = list2.search(0);
		System.out.println(temp);

		list2.insertAfter(temp, new DLNode(2000));
		System.out.println(list2);
		
		list2.remove(temp);
		System.out.println(list2);
			
		System.out.println();
	}


	public static void testStackQueue() {
		
		System.out.println("Stack");
	
		SLList stack1 = new SLList();
		final int M = 10;
		for (int i = 1; i < M; i++) {
			System.out.print("push " + i + ": ");
			stack1.push(i);
			System.out.println(stack1);
		}
		for (int i = 1; i < M; i++) {
			System.out.print(stack1.stackPop() + " pop: ");
			System.out.println(stack1);
		}
		System.out.println();
		
		System.out.println("Quene");

		SLList queue1 = new SLList();

		for (int i = 1; i < M; i++) {
			System.out.print("enqueue " + i + ": ");
			queue1.enqueue(i);
			System.out.println(queue1);
		}

		for (int i = 1; i < M; i++) {
			System.out.print(queue1.dequeue() + " dequeue: ");
			System.out.println(queue1);
		}
		System.out.println();
	}
}
