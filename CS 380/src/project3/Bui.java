package project3;


import java.util.Random;
import java.util.Scanner;

public class Bui {
	final static int hashLen = 10;
	static Random rd;
	static Scanner sc;
	
	public static void main(String[] args) {
		rd = new Random(10);
		sc = new Scanner(System.in);

		System.out.println("Chaining Hash");
		ChainHash table1 = new ChainHash(hashLen);
		testHash(table1, 50, 50);
		System.out.println();

		System.out.println("Linear Probe Hash");
		LinearHash table2 = new LinearHash(hashLen);
		testHash(table2, 14, 50);
		System.out.println();
	}
	
	public static void testHash(Hash table, int items, int maxKey) {

		System.out.println("Initial Hash Table");
		System.out.println(table);	

		System.out.println("Insertion Test");
		for (int i = 0; i< items; i++) {
			Object k = rd.nextInt(maxKey);
			Object v = rd.nextInt(1000);
			System.out.println("Insert (" + k + "," + v +"):");
			table.insert(k, v); 
			System.out.println(table);	
		}
		
		System.out.println("Search and Remove Test");
		for (int i = 0; i < maxKey; i++) {
			HashPair temp = table.search(i);
			if (temp != null)
				System.out.println("\nSearch (" + i + "): " + temp);
			else
				System.out.print(i + ", ");
		
			if (temp != null) {
				table.remove(i);
				System.out.println("(" + i + ")-removed Hash Table:");
				System.out.println(table);
			}
		}
		System.out.println();

		System.out.println("String Hash Table Test");

		String str;

		while (true) {
			System.out.println("Empty line to quit");
			System.out.println("0,key,value to insert");
			System.out.println("1,key to search");
			System.out.println("2,key to remove");
			System.out.print(">");

			if ((str = sc.nextLine()).equals(""))
				break;
			
			Scanner ss = new Scanner(str);
			try {
				ss.useDelimiter(",");

				char code = ss.next().charAt(0);
				String key = ss.next();
				switch(code) {
				case '0':
					table.insert(key, ss.next());
					break;
				case '1':
					System.out.println("Found: " + table.search(key));
					break;
				case '2':
					table.remove(key);
					break;
				default:
				}
				System.out.println(table);
			} catch (RuntimeException i) {
				System.out.println("Input Error");
			} finally {
				if (ss != null)
					ss.close();
			}
			
		}
		System.out.println();
	}


	
	public static class HashPair {

		public Object key;
		public Object value;
		
		public HashPair(Object k, Object v) {
			key = k;
			value = v;
			
		}
		
		public String toString(){
			String print = "";
			print += "(" + key + "," + value +")";
			return print;
			
		}
		
		public boolean equals(Object o) {
			if (o instanceof HashPair)
				return key.equals(((HashPair)o).key); //compare HashPair.key to HashPair.key
			return key.equals(o); //otherwise comparing HashPair.key with just key
		}
		
		/*public boolean equals(Object o){
			if (o instanceof HashPair){
				return (this.key == ((HashPair) o).key);
			}
			else{
				return (this.key == o);
			}
		}*/

	}

	public static abstract class Hash {

		abstract boolean insertBook(HashPair item);
		abstract boolean insert(HashPair item);
		abstract boolean remove(Object key);
		abstract HashPair search(Object key);
		
		boolean insert(Object k, Object o){
			return insert(new HashPair(k,o));
		}
		
		static int pMod(int i, int N){
			int temp;
			temp = i%N;
			if (temp < 0)
				return N + temp;
			else
				return temp;
		}

		static int hashMult(String key, int N){
			int stringHash = 5381; 
			int hashMultiplier = 33;
				for (int i = 0; i< key.length() ; i++) {
					stringHash = (stringHash * hashMultiplier) + key.charAt(i);
				}

				return pMod(stringHash, N);
				}
		public static int hash(Object key, int N){
			
			if (key instanceof Integer)
				return pMod((int) key,N);
			if (key instanceof String)
				return hashMult((String)key,N);
			else
				return hashMult(key.toString(),N);
		}
		
		
	}
	
	public static class LinearHash extends Hash {

		HashPair[] hashTable = null;
		boolean[] emptyAfterRemoval = {true};

		public LinearHash(int capacity) {
			// TODO Auto-generated constructor stub
			hashTable = new HashPair[capacity];
			emptyAfterRemoval = new boolean[capacity];
		}

		@Override
		boolean insertBook(HashPair item) {
			// TODO Auto-generated method stub
			// Hash function determines initial bucket
			int bucket = hash(item.key, hashTable.length);     
			int bucketsProbed = 0;

			while (bucketsProbed < hashTable.length) {
				// Insert item in next empty bucket 
				if (hashTable[bucket] == null) {
					hashTable[bucket] = item;
					return true;
				}

				// Increment bucket index
				bucket = (bucket + 1) % hashTable.length;

				// Increment number of buckets probed
				++bucketsProbed;
			}

			return false;       
		}

		@Override
		boolean insert(HashPair item) {
			// TODO Auto-generated method stub
			int bucket = hash(item.key, hashTable.length);     
			int bucketsProbed = 0;

			while (bucketsProbed < hashTable.length) {
				// Insert item in next empty bucket 
				if (hashTable[bucket] == null || hashTable[bucket].key.equals(item.key)){
					hashTable[bucket] = item;
					return true;
				}

				// Increment bucket index
				bucket = (bucket + 1) % hashTable.length;

				// Increment number of buckets probed
				++bucketsProbed;
			}

			return false;
		}

		@Override
		boolean remove(Object key) {
			// TODO Auto-generated method stub

			int bucket = hash(key, hashTable.length);
			int bucketsProbed = 0;


			while ((hashTable[bucket] != null || emptyAfterRemoval[bucket]) && (bucketsProbed < hashTable.length)) {

				if ((hashTable[bucket] != null) && (hashTable[bucket].key.equals(key))) {
					emptyAfterRemoval[bucket] = true;
					return true;
				}

				// Increment bucket index
				bucket = (bucket + 1) % hashTable.length;

				// Increment number of buckets probed
				++bucketsProbed; 
			}


			return false;
		}

		@Override
		HashPair search(Object key) {
			// TODO Auto-generated method stub

			int bucket = hash(key, hashTable.length);
			int bucketsProbed = 0;
			
			while ((hashTable[bucket] != null || emptyAfterRemoval[bucket]) && (bucketsProbed < hashTable.length)) {
				
				if ((hashTable[bucket] != null) && (hashTable[bucket].key.equals(key))) {
					return hashTable[bucket];
				}

				// Increment bucket index
				bucket = (bucket + 1) % hashTable.length;

				// Increment number of buckets probed
				++bucketsProbed; 
			}

			return null;  // Item not found
		}

		public String toString(){

			String temp = "";

			//NOT SURE IF I'M CORRECT ON THIS ONE************************************

			for (int i=0;i< hashTable.length ;i++){
				if(hashTable[i] == null){
					temp += "{" + i + ":X initial} \n";
				}
				else if (emptyAfterRemoval[i]){
					temp += "{" + i + ":X removed} \n";
				}
				else{
					temp += "{" + i + ":(" + hashTable[i] + "} \n";
				}
			}
			return temp;
		}

	}
	
	public static class ChainHash extends Hash {

		DLList[] hashTable;


		public ChainHash(int capacity){
			hashTable = new DLList[capacity];
			for (int i=0;i<capacity;i++){
				hashTable[i] = new DLList();
			}
		}

		@Override
		boolean insertBook(HashPair item) {
			// TODO Auto-generated method stub
			DLList bucketList = hashTable[hash(item.key,hashTable.length)];
			bucketList.append(new DLNode(item));			   
			return true;
		}




		@Override
		boolean insert(HashPair item) {
			// TODO Auto-generated method stub

			DLList bucketList = hashTable[hash(item.key,hashTable.length)];
			DLNode temp = bucketList.search(item);
			//System.out.println(bucketList);
			
			if(temp != null)
				bucketList.remove(temp);
			
				bucketList.append(new DLNode(item));			   
				return true;
			
			
		}

		@Override
		boolean remove(Object key) {
			// TODO Auto-generated method stub
			DLList bucketList = hashTable[hash(key,hashTable.length)];
			DLNode itemNode = bucketList.search(key);
			if( itemNode != null ) {
				bucketList.remove(itemNode);
				return true;
			} 
			return false;
		}

		@Override
		HashPair search(Object key) {
			// TODO Auto-generated method stub
			DLList bucketList = hashTable[hash(key,hashTable.length)];
			DLNode result = bucketList.search(key);
			
			
			//System.out.println(hashTable[hash(key,hashTable.length)].search(key));
			
			if(result == null)
				return null;
			else
				return (HashPair)result.data;

		}
		
		public String toString(){
			String temp = "";
			
			for(int i=0;i<hashTable.length;i++){
				temp += "{"+i+":"+hashTable[i].toString()+"} \n";
			}
			
			return temp;
		}

	}
	
	public static class DLNode {

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
	
	public static class DLList {
		
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
					if (curNode.data.equals(key)) {
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
	
}// End Bui Class
