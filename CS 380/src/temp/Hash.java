package temp;

public abstract class Hash {

	abstract boolean insertBook(HashPair item);
	abstract boolean insert(HashPair item);
	abstract boolean remove(Object key);
	abstract HashPair search(Object key);
	
	boolean insert(Object k, Object o){
		return insert(new HashPair(k,o));
	}
	
	public Hash() {
		// TODO Auto-generated constructor stub
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
