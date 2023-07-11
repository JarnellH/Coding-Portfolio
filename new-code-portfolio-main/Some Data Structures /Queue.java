import java.util.ArrayList;
import java.util.List;

/**
	@author Jarnell Hayes
	@version 1.1
*/

public class Queue<T>{
	
	private ArrayList<T> elements;

	private int s; 

	Queue(){
		this.elements = new ArrayList<T>();

		this.s = 0;
	}


	public T peek(){
		if(this.elements.isEmpty()){

			return null;
		}
		else{
			return this.elements.get(0);
		}

	}

	/**
		This method will add an item to the front of the queue
		@param element the value to be inserted into the queue
	*/

	public void enqueue(T element){
		this.elements.add(element);
		s++;
	}

	public T dequeue(){
		if(this.elements.isEmpty()){
			return null;
		}else{
			T temp = this.elements.get(0);
			T temp1 = this.elements.remove(0);
			s--;
			return temp;
		}
	}

	public int size(){
		return s;
	}

	public boolean isEmpty(){
		return s == 0;
	}
}