package temp;

public class HashPair {

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
	
	public boolean equals(Object o){
		if (o instanceof HashPair){
			return (this.key == ((HashPair) o).key);
		}
		else{
			return (this.key == o);
		}
	}

}
