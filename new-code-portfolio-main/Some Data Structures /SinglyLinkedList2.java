/**
* A class that models a SinglyLinkedList with add(), addByIndex(), removeByElement(),
* removeByIndex(), and toString() methods. A SinglyLinkedList "knows" only its head node.
* It contains an inner Node class. A Node object "knows" only its data (e.g., an int in this case) 
* and the next node.
*
* @author	Jarnell Hayes
* @version	October 19, 2020
*/
public class SinglyLinkedList2 {

	private String listName;
	private Node head;
	
	public SinglyLinkedList(String listName) {
		this.listName = listName;
		this.head = null;
	}
	
	/**
	* Returns an element specified by index from the list.
	*
	* @param	int 	index
	* @return	int		value
	*/
	public int getByIndex(int index) {
		Node current = this.head;
		if(index < 0){
			//If the index is less than zero we return -999.
			return -999;
		} else if(index == 0 && current != null) {
			//If the index is the first index we will return whatever value is the head node.
			return current.getData();
		} else {
			//In all other cases iterate through the nodes until the correctly indexed node is found.
			//Then we would return the data inside the node.
			while(index>0){

				current = current.getNext();
				index = index -1;

			}
			return current.getData();
		}
		
	}
	
	/**
	* Adds an element to the end of the list.
	*
	* @param	int 	element
	*/
	public void add(int element) {
		Node newNode = new Node(element);
		
		if (this.head == null) {
			//Provide implementation where element is added at the head node.
			this.head = newNode;
		} else {
			Node tail = this.head;
			//Provide implementation where element is added to a list that is not empty.
			//One way is to make the head node the tail node and "iterate" through by 
			//making the tail node's next node the new tail and then assigning the new 
			//node as the tail node's next node.
			while(tail.next != null){
				tail = tail.getNext();
			}
			tail.setNext(newNode);
		}
	}
	
	/**
	* Adds an element to the list at a specified index.
	*
	* @param 	int 	index The index at which the value given element will be
	* @param	int 	element The element provided to the index
	*/
	public void addByIndex(int index, int element) {
		int printIndex = index;
		Node newNode = new Node(element);
		
		if (index == 0) {
			
			//Provide implementation where the index at which to add an element is 0.
			newNode.setNext(this.head);
			this.head = newNode;
			//KEEP THIS LINE TO PRINT RESULT!
			System.out.println("Element " + element + " was added at index " + index + ".");
		} else {
			Node current = this.head; 
			//Provide implementation where the index at which to add an element is greater than 0.
			while (index >1){
				current = current.getNext();
				index--;
			}
			
			newNode.setNext(current.getNext());
			current.setNext(newNode);
			//KEEP THIS LINE TO PRINT RESULT!
			System.out.println("Element " + element + " was added at index " + printIndex + ".");
		}
	}
	
	/**
	* Removes an element from the list.
	*
	* @param	int 	element
	*/
	public void removeByElement(int element) {
		Node current = this.head;
		Node previous = null;
		
		if (current != null && current.data == element) {
			
			//Provide implementation when removing the element from the head.
			this.head = current.getNext();

			//KEEP THIS LINE TO PRINT RESULT!
			System.out.println("Element " + element + " was found and removed.");
		} else {
			while(current != null && current.data != element) {
				
				//Provide implementation to "iterate" through your linked list.
				previous = current;
				current = current.getNext();
			}
			//If element was found, then it is at the current node.  Remove it.
			if (current != null) {
				
				//Provide implementation to remove the desired element--e.g., the current node.
				previous.setNext(current.getNext());//previous.next = current.next;
				//KEEP THIS LINE TO PRINT RESULT!
				System.out.println("Element " + element + " was found and removed.");
			}
			//If element was not found, then the current node should be null.
			if (current == null) {
				//KEEP THIS LINE TO PRINT RESULT!
				System.out.println("Element " + element + " was not found.");
			}
		} 		
	}
	
	/**
	* Removes an element specified by index from the list.
	*
	* @param	int 	index
	*/
	public void removeByIndex(int index) {
		Node current = this.head;
		Node previous = null;
		
		if (index == 0 && current != null) {
			
			//Provide implementation where the index from which to remove is 0.
			this.head = current.getNext();
			//KEEP THIS LINE TO PRINT RESULT!
			System.out.println("Element at index " + index + " removed.");
		} else {
			int counter = 0;
		
			while(current != null) {
				if (counter == index) {
					
					//Provide implementation to remove the element from the specified index.
					previous.setNext(current.getNext());
					//KEEP THIS LINE TO PRINT RESULT!
					System.out.println("Element at index " + index + " removed.");
					break;
				} else {
					
					//Continue "iterating" through your linked list and increment the counter.
					previous = current;
					current = current.getNext();
					counter++;
				}
			}	
			//If the element at the specified index was found, then it should be at the current node.
			if (current == null) {
				//KEEP THIS LINE TO PRINT RESULT!
				System.out.println("The element at index " + index + " was not found.");
			}
		}
	}
	
	@Override
	public String toString() {
		String list = "LinkedList: ";
		Node current = this.head;  
   
        while (current != null) {  
            list = list + current.data + " "; 
            current = current.next; 
        }
		return list + "\n";
	}
	
	public class Node {
		private int data;
		private Node next;
				
		public Node(int data) {
			this.data = data;
			this.next = null;
		}

		public void setNext(Node nextNode){
			this.next = nextNode;
		}

		public Node getNext(){
			return this.next;
		}

		public int getData(){
			return this.data;
		}
	}
}