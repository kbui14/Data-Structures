package temp;

public class ChainHash extends Hash {

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
