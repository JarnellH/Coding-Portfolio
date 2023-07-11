public class MyArrayList<T> {

	private int size;
	private Object[] myData;

	public MyArrayList(){
		this.size = 0;
		myData = new Object[5];

	}

	public int size(){
		return this.size;
	}

	public void add(T element){
		if(myData.size() == myData.length){
			expandStorage();
		
		myData [ size ]  = element;
		size++;
		}

	}

	public T get(int index){
		return (T)(myData[index]);
	}

	private void expandStorage(){
		int newSize = (myData.length/ 2) + 1;
		Object[] temp = new Object[newSize];
		for (int i = 0; i<myData.length;i++){
			    temp[i] = myData[i];
		}
		this.myData = temp;
	}


	
} //end class