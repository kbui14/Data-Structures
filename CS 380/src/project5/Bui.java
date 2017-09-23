package project5;

import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

import project4.Bui.Node;

public class Bui {
	static Random rd;
	static Scanner sc;

	static final int N = 10;

	public static void main(String[] args) {
		rd = new Random(20);

		testStack();
		testQueue();

		//Max heap 
		System.out.println("Max Heap Test");
		Heap<Integer, Double> heap1 = new Heap<Integer, Double>(100);
		testHeap(heap1);
		System.out.println();

		//Min heap 
		System.out.println("Min Heap Test");
		Heap<Integer, Double> heap2 = new Heap<Integer, Double>(100);
		heap2.setComparator(heap2.minComp);
		testHeap(heap2);
		System.out.println();

		//Max PQ 
		System.out.println("Max Priority Queue Test");
		PriorityQueue<PQKey<Integer>, Double> heap3 = 
				new PriorityQueue<PQKey<Integer>, Double>(100);
		//testPQ(heap1); //this should result in compilation error!!!
		//testHeap(heap3);  // this should result in compilation error!!!
		testPQ(heap3);
		System.out.println();
	}

	static void testStack() {
		Stack stack = new Stack(N);

		for (int i = 0; i < N; i++) {
			stack.push(i);
			System.out.println(stack);
		}

		final int M = 12;
		for (int i = 0; i < M; i++) {
			try {
				System.out.print(stack.pop() + " pop: ");
			} catch (RuntimeException re) {
				System.out.println(re);
			}
			System.out.println(stack);
		}
		for (int i = 0; i < M; i++) {
			System.out.print("push " + i + ": ");
			try {
				stack.push(i);
			} catch (RuntimeException re) {
				System.out.println(re);
			}
			System.out.println(stack);
		}
		/*
		 * for (int i = 0; i < M; i++) { System.out.print("enqueue " + i +
		 * ": "); list3.enqueue(i); System.out.println(list3); }
		 */
		for (int i = 0; i < M; i++) {
			try {
				System.out.print(stack.pop() + " pop: ");
			} catch (RuntimeException re) {
				System.out.println(re);
			}
			System.out.println(stack);
		}
		/*
		 * for (int i = 0; i < M; i++) { System.out.print(list3.dequeue() +
		 * " deque: "); System.out.println(list3); }
		 */

	}

	static void testQueue() {
		Queue Queue = new Queue(N);

		for (int i = 0; i < N; i++) {
			Queue.enqueue(i*1.0);
			System.out.println(Queue);
		}

		final int M = 12;
		for (int i = 0; i < M; i++) {
			try {
				System.out.print(Queue.dequeue() + " dequeue: ");
			} catch (RuntimeException re) {
				System.out.println(re);
			}
			System.out.println(Queue);
		}
		for (int i = 0; i < M; i++) {
			System.out.print("enqueue " + i + ": ");
			try {
				Queue.enqueue(i);
			} catch (RuntimeException re) {
				System.out.println(re);
			}
			System.out.println(Queue);
		}
		/*
		 * for (int i = 0; i < M; i++) { System.out.print("enqueue " + i +
		 * ": "); list3.enqueue(i); System.out.println(list3); }
		 */
		for (int i = 0; i < M; i++) {
			try {
				System.out.print(Queue.dequeue() + " dequeue: ");
			} catch (RuntimeException re) {
				System.out.println(re);
			}
			System.out.println(Queue);
		}
		/*
		 * for (int i = 0; i < M; i++) { System.out.print(list3.dequeue() +
		 * " deque: "); System.out.println(list3); }
		 */
		int items = 100;
		for (int i = 0; i < items; ) {
			if (rd.nextInt(10) %2 == 0) {
				try {
					System.out.print("dequeue ");
					System.out.print(Queue.dequeue());
					System.out.print(": ");
				} catch (RuntimeException re) {
					System.out.println(re);
				}	
			} else {
				System.out.print(i + " enqueue: ");
				try {
					Queue.enqueue(i);
				} catch (RuntimeException re) {
					System.out.println(re);
				}
				i++;
			}
			System.out.println(Queue);
		}
	}



	public static void testHeap(Heap<Integer, Double> heap) {
		int items = 100;

		for(int i  = 0; i < items; i++) {
			Integer k = new Integer(rd.nextInt(100));
			Double v = rd.nextInt(1000)/10.0;
			System.out.println("Insert (" + k + "," + v +"):");
			heap.insert(k, v);
		}
		System.out.println(heap);			

		for(int i  = 0; i < items; i++) {
			System.out.println("Remove: " + heap.remove());
		}
	}

	public static void testPQ(PriorityQueue<? extends PQKey<Integer>, Double> heap) {	
		//	public static <A extends Comparable<A>, B> void testPQ(PriorityQueue<? extends PQKey<A>, B> heap) {
		int items = 100;
		int priorities = 5;

		for(int i  = 0; i < items; i++) {
			//			PQKey<A> p = new PQKey<A>(rd.nextInt(priorities));
			PQKey<Integer> p = new PQKey<Integer>(rd.nextInt(priorities));
			Double v = rd.nextInt(1000)/10.0;
			System.out.println("Insert (" + p + "," + v +"):");
			heap.insert(p, v);
		}
		System.out.println(heap);			

		for(int i  = 0; i < items; i++) {
			System.out.println("Remove: " + heap.remove());
		}
	}

	public static class Node<K extends Comparable<K>, T>  {
		K key;
		T data;
		Node<K, T> left;
		Node<K, T> right;

		Node(K k, T o) {
			key = k;
			data = o;
		}

		public boolean equals(Object o) {
			if (o instanceof Node<?, ?>)
				return key.equals(((Node<?, ?>)o).key); //only check the keys
			return false;
		}

		public String toString() {
			return "(" + key +  "," + data +  ")";
		}
	} // closing Node class

	public static class Stack {

		public int size;
		private Object[] storage;

		Stack(int capacity){
			if (capacity <= 0)
				throw new IllegalArgumentException("Stack's capacity must be positive");
			storage = new Object[capacity];
			size = 0;
		} //closing Stack constructor

		void push (Object value) {
			if (size >= storage.length) {
				throw new RuntimeException("Stack overflow");
			}
			storage[size] = value;
			size++;
		} // closing push method

		Object pop() {
			if(size <= 0){
				throw new RuntimeException("Stack empty");
			}
			size--;
			return storage[size];
		} // closing pop method

		Object peek(){
			if (size <= 0){
				throw new RuntimeException("Stack empty");
			}
			return storage[size-1];
		} // closing peek method

		boolean isEmpty(){
			if(size <= 0)
				return true;
			else
				return false;
		} // closing isEmpty method

		boolean isFull(){
			if(size == storage.length)
				return true;
			else
				return false;
		} // closing isFull method

		public String toString(){

			String temp = "";
			for(int i=0;i<storage.length;i++){
				if(storage[i] == null)
					break;
				if(i == size-1)
					temp += storage[i];
				else
					temp += storage[i] + " / ";
			}
			return temp;
		} // closing toString method

	} // closing Stack class

	public static class Queue{

		int queueFront;
		int queueLength;
		private Object storage[];

		Queue(int capacity){
			if(capacity < 1)
				throw new IllegalArgumentException("Stack's capacity must be positive");
			storage = new Object[capacity];
			queueFront = 0;
			queueLength = 0;
		} // closing Queue constructor

		void enqueue(Object value){
			int back;

			if (queueLength >= storage.length) {
				queueLength = storage.length;
				throw new RuntimeException("Stack overflow");
			}
			back = (queueFront + queueLength) % storage.length;
			storage[back] = value;
			queueLength++;
		} // closing enqueue method

		Object dequeue(){

			Object temp;

			if(queueLength <= 0){
				throw new RuntimeException("Stack empty");
			}
			temp = storage[queueFront];
			queueFront++;
			queueFront = queueFront % storage.length;
			queueLength--;
			return temp;
		} // closing dequeue method

		Object peek(){
			if (queueLength <= 0){
				throw new RuntimeException("Stack empty");
			}
			return storage[queueFront];
		} // closing peek method

		boolean isEmpty(){
			if(queueLength <= 0)
				return true;
			else
				return false;
		} // closing isEmpty method

		boolean isFull(){
			if(queueLength == storage.length)
				return true;
			else
				return false;
		} // closing isFull method

		public String toString(){

			String temp = "(" + queueFront+ "," + queueLength + ")";
			for(int i=0;i<queueLength;i++){
				if(storage[i] == null)
					break;
				if (i == queueLength-1)
					temp += storage[(queueFront + i) % storage.length].toString();
				else
					temp += storage[(queueFront + i) % storage.length].toString() + " / ";
			}
			return temp;
		} // closing toString method

	} // closing Queue class

	public static class Heap <K extends Comparable <K>, T>{

		Node<K, T> storage[];
		int bottom;
		Comparator<K> minComp;

		Heap(int capacity){

			if(capacity < 1)
				throw new IllegalArgumentException("Stack's capacity must be positive");
			storage = new Node[capacity];
			bottom = 0;
			minComp = new Comparator<K> (){
				public int compare(K k1, K k2){
					return k1.compareTo(k2);
				}
			}; // closing comp allocation
		} // Closing Heap Constructor

		void insertNode(Node<K, T> value){
			int child;
			int parent;
			Node<K, T> temp;

			if (bottom >= storage.length) {
				bottom = storage.length;
				throw new RuntimeException("Stack overflow");
			}
			storage[bottom] = value;
			child = bottom;
			bottom++;

			while(child != 0){
				parent = (child - 1)/2;
				if (minComp.compare(storage[parent].key,storage[child].key) < 0){
					temp = storage[parent];
					storage[parent] = storage[child];
					storage[child] = temp;
					child = parent;
				}
				else
					break;
			}
		}// closing insertNode method

		void insert(K key, T value){
			insertNode(new Node<K, T>(key, value));
		} // closing insert method

		public Node<K,T> remove(){

			Node rootNode;
			Node bottomNode;
			int parentIndex;

			if(bottom <= 0){
				return null;
			}
			rootNode = storage[0];
			bottomNode = storage[bottom-1];
			bottom--;
			if(bottom == 0){
				return rootNode;
			}
			parentIndex = 0;
			storage[parentIndex] = bottomNode;

			while(2*parentIndex+1 < bottom){
				int chosenChild;
				int child1;
				int child2;
				K chosenKey;
				K key1;
				K key2;

				child1 = 2*parentIndex+1;
				key1 = storage[child1].key; 
				chosenKey = key1;
				chosenChild = child1;

				child2 = child1 + 1;
				if (child2 < bottom){
					key2 = storage[child2].key;
					if (minComp.compare(key1, key2) < 0){
						chosenKey = key2;
						chosenChild = child2;
					}
				}
				if (chosenKey.compareTo(storage[parentIndex].key) > 0){
					Node<K, T> temp = storage[parentIndex];
					storage[parentIndex] = storage[chosenChild];
					storage[chosenChild] = temp;
					parentIndex = chosenChild;
				}
				else
					break;				
			}
			return rootNode;

		} // closing remove method

		Object peek(){
			if (bottom <= 0){
				return null;
			}
			return storage[0];
		} // closing peek method

		boolean isEmpty(){
			if(bottom <= 0)
				return true;
			else
				return false;
		} // closing isEmpty method

		boolean isFull(){
			if(bottom == storage.length)
				return true;
			else
				return false;
		} // closing isFull method

		public String toString() {
			String temp = "";
			int nextLevel = 2;
			for (int i = 0; i < bottom; i++) {
				if (i == nextLevel-1) {
					temp += "\n";
					nextLevel *= 2;
				}
				temp += storage[i];

			}
			temp += "";

			return temp;
		} // closing toString method

		public void setComparator(Comparator<K> c){
			minComp = c;
		} // closing setComparator

	} // closing Heap class

	public static class PQKey<K extends Comparable<K>> implements Comparable<PQKey<K>>{

		K level;
		int keyOrder;
		static int keyCount;

		PQKey(K k){
			level = k;
			keyOrder = keyCount;
			keyCount++;
		}

		public int compareTo(PQKey<K> o) {
			int temp = this.level.compareTo(o.level);
			if(temp != 0)
				return temp;
			else
				return o.keyOrder - keyOrder;
		} // closing compareTo method

		public String toString(){
			String temp = "";
			temp = level + "[" + keyOrder + "]";
			return temp;

		} // closing toString method

	} // closing PQKey class

	public static class PriorityQueue <K extends PQKey<?>, T> extends Heap<PQKey<?>, T>{



		PriorityQueue(int capacity) {
			super(capacity);

		}

	} // closing PriorityQueue class



} // closing Bui class

