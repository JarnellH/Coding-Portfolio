
/**
*   @author Jarnell Hayes 
*	@version 1.1
*/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<T> implements Iterable<T>{

	
	public Node head;
	public Node tail;
	public int listSize;


	

	public SinglyLinkedList(){

		this.head = null;
		this.tail = null;
		this.listSize = 0;

	}


	@Override
	public SinglyLinkedListIterator iterator(){
		return new SinglyLinkedListIterator();
	}

	
	public class SinglyLinkedListIterator implements Iterator<T>{

		int index = 0;
		private Node currenten;

		public SinglyLinkedListIterator()
		{
			currenten = head;
		}

		//returns a boolean showing whether the list has a next value
		@Override
		public boolean hasNext(){

			if(listSize > 1){
				return true;
			}
			else{ return false;}
		}

		//This method will return the next value in the list
		@Override
		public T next(){

		if(hasNext()== true){

			T val = currenten.getData();
			currenten = currenten.getNext();
			return val;
		}
		else{throw new NoSuchElementException();}
	}
	public void remove(){
		throw new UnsupportedOperationException("Unsupported Opereation was attempted by this iterator");
	}
}
	

	/**
		Adds a new element to the end of the linked list

		@param T element
	*/

	public void add (T element){

		Node newN = new Node(element, null);

		if(this.head == null ){
			this.head = newN;
			
			listSize++;
		}
		else{
			Node tail = this.head;
			

			while(tail.getNext() != null){
				tail = tail.getNext();
			}
			tail.setNext(newN);
			listSize++;
		}

	}

	/**
		A method that inserts a given element at a specific index point 

		@param T element
		@param int index
	*/

	public void insertAt (T element , int index ){
		int insertion = index;
		Node newN = new Node (element,null);

		if (index == 0){
			newN.setNext(this.head);
			this.head = newN;
			listSize++;
		}
		else{
			Node current = this.head;
			while (index > 1){
				current = current.getNext();
				index--;
			}

			newN.setNext(current.getNext());
			current.setNext(newN);
			listSize++;
		}
	}


	/**
		Removes a given element in the list if found
		@param T element

	*/

	public void remove (T element){
		Node current = this.head;
		Node temp = this.head;

		if(current != null && current.getData() == element ){

			this.head = head.next;
			listSize = listSize - 1;
			

		}
		else{
			
			while(current != null && current.getData() != element){

				temp = current;
				current = current.getNext();

				
			}
			if(current != null){
				temp.setNext(current.getNext());
				
			}

			if (current == null){
				
				throw new NoSuchElementException("Element " + element + " was not found , List was size " + size() + " ,pointer was at " + temp.getData());
			}
		}
		
	}

	
	
	/**
		This method clears the entire list of all data
	*/

	public void clear(){
		Node current = this.head;
		Node prior = null;

		while (current.getNext()!= null && current != null){
			current.setNext(prior);

			if(current != null){
				current = prior;
				listSize = 0;
			}
			
		}
		
	}

	/**
		This returns a boolean value stating whether the list is empty 
	*/

	public boolean isEmpty(){
		Node current = this.head;

		if(current.getNext() == null && current == null){
			return true;
		}
		else{
			return false;
		}
	}

	/**
		this method returns the size of the list 
	*/

	public int size(){

		return listSize;
	}

	/**
		This method will search for an element given an index starting 
		from the first node within the list
		
		@param int n  
	*/

	public T getNthFromFirst (int n){
		if(n >= 0 && n < listSize){
		 	int i = 0;
		 	Node current = head;

		 	while( n>i && current.getNext() != null){
		 		current = current.getNext();
		 		i++;
		 	}
		 	
		 	return current.getData();
		}
		else{
			throw new IndexOutOfBoundsException();
		}
		
	}

	/**
		This method will search for an element given an index starting
	    from the last node within the list

		@param int n
	
	*/

	public T getNthFromLast (int n){
		
		if( n >= 0 && n < listSize){
		 	int i = 0;
		 	Node current = head;
		 	Node pointer = head;

		while (i <= n-1){
			pointer = pointer.getNext();
			i = i + 1;
		}
		while(pointer.getNext() != null){
			pointer = pointer.getNext();
			current = current.getNext();
		}
		 
		 	return current.getData();
		}
		else{
			throw new IndexOutOfBoundsException();
		}
	}

	

	/**
		The toString method prints out the list as a formatted string 

	*/
	@Override
	public String toString(){
		String list = "LinkedList: ";
		Node current = this.head;

		while(current != null){
			list = list + current.getData() + " ";
			current = current.getNext();
		}
		return list + "\n";
	}







	//The node inner class represents the nodes in the singly linked list 
	//This class also has the instructions for setting and getting the nodes
	public class Node{

		public T data;
		public Node next;

		/**
			Node constructor will take in data and the next node after it
			@param T data
			@param Node next
		*/

		public Node (T data , Node next){
			this.data = data;
			this.next = next;
		}

		//Gets the data within a node 
		public T getData(){
			return this.data;
		}

		/**
			A method that will set the contents of the data
			@param T here 
		*/
		public void setData(T here){
			this.data = here;
		}


		//This method get the next node of the current node 
		public Node getNext(){
			return this.next;
		}

		/**
			This method takes in a node as an argument and sets it as a node 
			in the linked list

			@param Node nextNode

		*/
		public void setNext(Node nextNode){
			this.next = nextNode;
		}

	}
}

