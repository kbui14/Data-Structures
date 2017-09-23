package project4;

import java.util.Random;
import java.util.Scanner;

public class Bui {

	static Random rd;
	static Scanner sc;

	public static void main(String[] args) {
		rd = new Random(20);
		sc = new Scanner(System.in);

		testBST();
	}

	public static void testBST() {

		BST<String,String> tree0 = new BST<String,String>();
		System.out.println("Rotation Test");
		tree0.insert("D","D");
		tree0.insert("B","B");
		tree0.insert("C","C");
		tree0.insert("A","T1");
		tree0.insert("BB","T2");
		tree0.insert("CC","T3");
		tree0.insert("E","T4");
		System.out.println("Initial Tree");
		System.out.println(tree0);
		tree0.root = tree0.rightRotate(tree0.root);
		System.out.println("Right Rotate Tree");
		System.out.println(tree0);
		tree0.root = tree0.leftRotate(tree0.root);
		System.out.println("Left Rotate Tree");
		System.out.println(tree0);
		System.out.println();

		System.out.println("Insertion Test");
		BST<Integer,Double> tree1 = new BST<Integer,Double>();
		BST<Integer,Double> tree2 = new BST<Integer,Double>();
		System.out.println("Initial Tree");
		System.out.println(tree1);
		System.out.println("depth = " + tree1.depth() + ", size = " + tree1.size());

		int items = 15;
		int searches = 100;

		while (tree1.size() < items) {
			Integer k = rd.nextInt(100);
			Double v = (rd.nextInt(1000)/10.0);
			//			System.out.println("Insert (" + k + "," + v +"):");
			tree1.insert(k, v);
			tree2.insert(k, v);
			//			System.out.println(tree1);			
		}

		System.out.println("Populated Tree");
		System.out.println(tree1);
		System.out.println();

		System.out.println("Search and Remove Test");
		for (int i = 0; i< searches; i++) {
			//			Integer key = rd.nextInt(100);
			Integer key = i;

			Node<Integer, Double> temp = tree2.search(key);
			if (temp != null)
				System.out.println("\nSearch (" + key + "): " + temp);
			else
				System.out.print(key + ", ");

			if (temp != null) {
				tree2.remove(key);
				System.out.println("(" + key + ")-removed Tree:");
				System.out.println(tree2);
				System.out.println("depth = " + tree2.depth() + ", size = " + tree2.size());
			}
		}
		System.out.println("");

		System.out.println("Traversal Test");
		System.out.println("Inorder Traversal");
		System.out.println(tree1.inorderString());
		System.out.println("Preorder Traversal");
		System.out.println(tree1.preorderString());
		System.out.println("Postorder Traversal");
		System.out.println(tree1.postorderString());
		System.out.println("depth = " + tree1.depth() + ", size = " + tree1.size());
		System.out.println();

		System.out.println("Balance Test");
		tree1.balanceDSW();
		System.out.println("DSW Balanced Tree");
		System.out.println(tree1);
		System.out.println("depth = " + tree1.depth() + ", size = " + tree1.size());
		System.out.println();


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

	public static class BST<K extends Comparable<K>, T> {

		protected Node<K, T> root;

		BST() {
			root = null;
		}

		public Node<K, T> search (K key){
			Node<K, T> cur = this.root;
			while (cur != null){
				int comp = key.compareTo(cur.key);
				if (comp == 0)
					return cur; //Found
				else if (comp < 0)
					cur = cur.left;
				else
					cur = cur.right;
			}
			return cur;
		} // closing search


		public void insertBook(Node<K, T> node){

			Node<K, T> cur = root;


			if (root == null){
				root = node;
				node.left = null;
				node.right = null;
			}
			else{
				cur = root;
				while(cur != null){
					int comp = node.key.compareTo(cur.key);
					if (comp == 0){
						if (cur.left == null){
							cur.left = node;
							cur = null;
						}
						else
							cur = cur.left;
					}
					else {
						if (cur.right == null){
							cur.right = node;
							cur = null;
						}
						else{
							cur = cur.right;
						}
					}
				}
				node.left = null;
				node.right = null;
			}


		} // closing insertBook

		public void insert (Node<K, T> node){

			Node<K,T> cur = this.root; 

			if(root == null){
				root = node; 
				node.left = null;
				node.right = null;
			}
			else{
				cur = root; 
			}

			while (cur != null){
				int comp = node.key.compareTo(cur.key);
				if (comp < 0){ //node < cur
					if (cur.left == null){
						cur.left = node;
						cur = null; 
					}
					else {
						cur = cur.left; 
					}
				}
				else if (comp > 0) { //node > 0
					if (cur.right == null){
						cur.right = node; 
						cur = null; 
					}
					else {
						cur = cur.right; 
						node.left = null; 
						node.right = null; 
					}
				}
				else if (comp == 0){ //node == cur
					cur.key = node.key; 
					cur.data = node.data; 
					cur = null; // gets it to get out of the loop
				}
			}
		} // closing insert

		public void insert(K key, T value) {
			insert(new Node<K, T>(key,value));
		} // closing insert override 

		private void remove(Node<K, T> par, Node<K, T> cur, K key){

			par = null;
			cur = root;
			Node<K, T> suc;
			int comp = key.compareTo(cur.key);

			while(cur != null){
				if(cur.key.equals(key)){
					if(cur.left == null && cur.right == null){
						if (par == null){
							root = null;
						}
						else if (par.left == cur){
							par.left = null;
						}
						else{
							par.right = null;
						}
					}
					else if (cur.left != null && cur.right==null){
						if (par == null){
							root = cur.left;
						}
						else if (par.left == cur){
							par.left = cur.left;
						}
						else{
							par.right = cur.left;
						}
					}
					else if (cur.left == null && cur.right != null){
						if (par == null){
							root = cur.right;
						}
						else if (par.left == cur){
							par.left = cur.right;
						}
						else{
							par.right = cur.right;
						}
					}
					else {
						suc = cur.right;

						while (suc.left != null){
							suc = suc.left;
						}
						cur = suc;
						remove(cur, cur.right, suc.key);
					}

				}
				else if (comp < 0){
					par = cur;
					cur = cur.right;
				}
				else{
					par = cur;
					cur = cur.left;
				}

			}
		}//close remove

		public void remove(K key){
			remove(null, root, key);
		} //closing remove

		public int depth(Node<K, T> node){

			int leftDepth;
			int rightDepth;

			if (node == null){
				return 0;
			}
			else{
				leftDepth = depth(node.left);
				rightDepth = depth(node.right);

				if (leftDepth > rightDepth)
					return leftDepth ++;
				else
					return rightDepth ++;
			}
		} // closing depth

		public int depth(){

			return depth(root);

		} // closing depth

		public int size(Node<K, T> node){

			if (node == null)
				return 0;
			else 
				return size(node.left) + size(node.right) + 1;
		} // closing size

		public int size(){

			return size(root);

		} // closing size

		private String inorderString(Node<K, T> node){

			if (node == null)
				return "";
			return inorderString(node.left) + node.toString() + inorderString(node.right);

		} // closing inorderString

		public String inorderString(){

			return inorderString(root);

		} // closing inorderString 


		private String preorderString(Node<K, T> node){

			if (node == null)
				return null;
			else
				return node.toString() + preorderString(node.left) + node + preorderString(node.right);
		} // closing preorderString
		public String preorderString(){

			return preorderString(root);

		} // closing preorderString


		public String postorderString(Node<K, T> node){

			if (node == null)
				return "";
			return postorderString(node.left) + postorderString(node.right) + node.toString();
		} // closing postorderString
		public String postorderString(){

			return postorderString(root);

		} // closing postorderString

		private String toString(Node<K, T> node, int depth){

			String temp = "";

			if (node == null)
				return "x";

			temp = "[";

			if(node.left != null)
				temp = temp + toString(node.left, depth + 1) + "<";

			temp = temp + depth + ":" + node;

			if(node.right != null)
				temp = temp + ">" + toString(node.right, depth + 1);

			temp = temp + "]";

			return temp;

		} // closing toString

		public String toString(){

			return toString(root, 1);

		} // closing toString

		protected Node<K, T> rightRotate(Node<K, T> DNode){

			Node<K, T> BNode;
			Node<K, T> CNode;

			BNode = DNode.left;
			CNode = DNode.right;

			BNode.right = DNode;
			DNode.left = CNode;

			return BNode;
		} // closing rightRotate

		protected Node<K, T> leftRotate(Node<K, T> DNode){

			Node<K, T> BNode;
			Node<K, T> CNode;

			BNode = DNode.right;
			CNode = DNode.left;

			BNode.left = DNode;
			DNode.right = CNode;

			return BNode;
		} // closing leftRotate

		private int tree_to_vine(Node<K, T> root){

			Node<K, T> tail = root;
			Node<K, T> rest = tail.right;
			Node<K, T> temp;

			int size = size(root);

			while (rest != null){
				if (rest.left == null){
					tail = rest;
					rest = rest.right;
					return size++;
				}
				else{
					temp = rest.left;
					rest.left = temp.right;
					temp.right = rest;
					rest = temp;
					tail.right = temp;
					return size--;
				}
			}
			return size;

		} // closing tree_to_vine

		private int fullSize(int size){
			int val = 1;

			while (val -1 <= size){
				val = val*2;
			}
			return val/2-1;
		} // closing fullSize

		private void vine_to_tree(Node<K, T> root, int size){

			int leaves = size - fullSize(size);
			compress(root, leaves);
			size = size - leaves;
			while(size > 1){
				compress(root, size/2);
				size = size/2;
			}
		} // closing' vine_to_tree

		private void compress(Node<K, T> root, int count){

			for(int i=1; i<count;i++)
				leftRotate(root);
		} // closing compress

		public void balanceDSW(){

			Node <K,T> pseudoRoot = new Node<K, T>(null,null);
			root = pseudoRoot.right;
			tree_to_vine(pseudoRoot);
			vine_to_tree(pseudoRoot, size(pseudoRoot));
			root = pseudoRoot.right;
			pseudoRoot = null;

		} // closing balanceDSW


	}//closing BST





} //closing Bui class


