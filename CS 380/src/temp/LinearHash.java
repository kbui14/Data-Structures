package temp;

public class LinearHash extends Hash {

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
			if (hashTable[bucket].key.equals(item.key)) {
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
		Object EmptySinceStart;

		if (hashTable[bucket] != null || emptyAfterRemoval[bucket])
			EmptySinceStart = false;
		else
			EmptySinceStart = true;


		while ((hashTable[bucket] != EmptySinceStart) && (bucketsProbed < hashTable.length)) {

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
			if(hashTable[i] != null || emptyAfterRemoval[i]){
				temp += "{" + i + "X initial} \n";
			}
			else if (emptyAfterRemoval[i]){
				temp += "{" + i + "X removed} \n";
			}
			else{
				temp += "{" + i + ":(" + hashTable[i] + "} \n";
			}
		}
		return temp;
	}

}
