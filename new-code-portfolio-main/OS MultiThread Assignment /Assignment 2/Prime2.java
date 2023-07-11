//Multithreaded 2 thread cpu task

class Prime2 implements Runnable{

long startT;
long endT;

@Override
public void run(){

	
	long count =  69881631850817231L;
	float sqrt = (float) Math.ceil(Math.sqrt(count));




	try{

		startT = System.nanoTime();

		for(int i = 2; i < count; i++){

			if (count % i == 0){
				System.out.printf("%s is not a prime :( \n", count);
				break;
			}else{
				System.out.printf("%s is a prime!!\n " , count);

				System.out.println(i);
			}
		}

		endT = System.nanoTime();

		double printed = (double)(endT - startT) / 1000000000;

		System.out.printf("This took %.2f seconds\n",printed);





	}catch(Exception e){e.printStackTrace();}
}

public static void main(String[] args){
	Prime thready = new Prime();
	Prime number2 = new Prime();

	Thread output = new Thread(thready);
	Thread output2 = new Thread(number2);

	output.start();
	output2.start();





}




}