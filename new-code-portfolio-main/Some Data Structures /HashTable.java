/**
 * 
 * @author Jarnell Hayes
 * @version 1.1
 * 
 * 
 * The hash table uses a hash function to map data values to a key within the table
 * string -> hash code -> index
 * 
 * Key -> Value  {Keys map to values}
 * 
 * Hash function evaluates a key value and returns an index 
 * Hashfunction(Key) -> index **same key results in the same index**
 * 
 * two strings with different hash codes can collide(map to same key)
 * 
 * chaining involves using a linked list to avoid collisions in data mapping
 * the linked list uses the nodes to reference values mapped to same index 
 * 
 * open addressing (linear probing or double hashing)
 * 
 * the function will be key mod array.length -> index 
 * 
 * length should be prime 
 * 
 * 
 * Hash Tables run at constant time for a good hash table (assumption)
 * O(n) for a terrible table (lots of collisions)
 * */


public class HashTable<T>{

	//pointer will reference the chained value
	LinkedList[] data;

	Entry[] table;
	int size;
	
	public HashTable(int size){
		this.size = size;

		table = new Entry[size];

		for(int i = 0; i < size; i++){
			table[i] = new Entry();
		}


	}

	//Take the key and turn it into an index 
	//avoid collisions and fast computation
	public int hash(Object key){

		return Integer.parseInt(key) % table.length - 1;
	}


	public boolean equals(Object o){}

	//public void put(Object key , Object value){}


	public void get(Object key){
		int arrayIndex = Integer.parseInt(key) % table.length - 1;


		if(table[arrayIndex] == key){
			System.out.println(key + " was found  at index " + arrayIndex );
		}


	}


	//public void remove(Object key){}

	//public boolean remove(Object key , Object value){}


	//Replaces value if it is mapped to the specified key
	//public void replace(Object key , Object value){}

	//public void clear(){}

	//public int hashCode(){  }

}



//This class enables the hash table to gain a new entry 
public class Entry(){

	int key;
	Object value;

	Entry next;

	//Constructor takes a key and a value as an entry 
	public Entry(int key , Object value){
		this.key = key;
		this.value = value;

		next = null;

	}

	//Takes no params so the data is null
	public Entry(){

		next = null;
	}

	//This method returns the key
	public int getkey(){
		return key;
	}
	//this method returns the value of the data
	public Object getValue(){
		return value;
	}
}