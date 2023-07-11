
/**
	@author Jarnell Hayes
	@version 1.1
*/



import java.util.Iterator;
import java.util.NoSuchElementException;


public class MyStack<T> extends SinglyLinkedList<T>{

	private SinglyLinkedList<T> myStack;
	private Node head;

	private int listSize; 

	//Constructor object
	public MyStack(){
		myStack = new SinglyLinkedList<T>();
		this.head = null;
		
		listSize = 0;
	}

	/**
		A method that will push given items onto the stack
		@param T element 
	*/

	public void push(T element){

		Node temp = new Node (element , null);

		temp.next = head;
		head = temp;
		listSize++;
		

	}

	//This method pops items off of the stack 
	public T pop(){

		if(isEmpty()){
			return null;
		}else{
			T temp = head.data;
			head = head.next;
			listSize--;
			
		return temp;

		}
		
	}


	//A method that returns a boolean value stating whether the stack is empty or not
	public boolean isEmpty(){
		return listSize == 0;
	}

	//Returns a string output of the stack 
	@Override
	public String toString(){
		String retS = "";
		for (int i = 0; i < myStack.size();i++){
				retS = retS + this.myStack.getNthFromFirst(i)+ "\n";
				if(myStack.getNthFromFirst(i) == null){
					break;
					
				}
		}
		return retS;
	}

	
}