package temp;

public class Node<K extends Comparable<K>, T>  {
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
}
