import java.util.ArrayList;

public class Stack<T>{

	private ArrayList<T> container;

	public Stack(){
		this.container = new ArrayList<T>;
	}

	public int getSize(){
		return this.container.size();
	}

	 public boolean isEmpty(){

		return this.container.isEmpty();
	}

	public void push(T item){
		this.container.add(item);

	}

	public void peek(){

		if(this.isEmpty()){
			return null;
		}else {
			return this.container.get(this.getSize()-1);
		}

	}

	public T pop(){
		if(this.isEmpty()){
			return null;
		}else{
			T temp = container.get(this.getSize()-1);
			container.remove(container.getSize()-1);
			return temp;
		}
	}
}