package temp;

public class BST<K extends Comparable<K>, T> {

	

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
	}
	
	
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
	
	public void insert(Node<K, T> node){
		
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
				if(node.key.equals(cur.key)){
					cur.key = node.key;
				
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
			}
			node.left = null;
			node.right = null;
		}
	} // closing insert
	
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
		else
			leftDepth = depth(node.left);
			rightDepth = depth(node.right);
			if (leftDepth > rightDepth)
				return leftDepth ++;
			else
				return rightDepth ++;
	} //closing depth
	
	public int size(Node<K, T> node){
		
		if (node == null)
			return 0;
		else 
			return size(node.left) + size(node.right) + 1;
	} //closing size
	
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
		
		Node<K, T> pseudoRoot = root.right;
		
		tree_to_vine(pseudoRoot);
		vine_to_tree(pseudoRoot, size(pseudoRoot));
		root = pseudoRoot.right;
		pseudoRoot = null;
		
	} // closing balanceDSW
	
	
}//closing BST
		
		
		
		