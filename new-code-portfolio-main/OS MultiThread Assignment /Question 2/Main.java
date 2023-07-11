
import java.io.*;
import java.util.concurrent.*;

//Question 2 Algorithm Based on TextBook Solution

class Philosopher implements Runnable{

	FileWriter writer ; 
	PrintWriter print = null;
	String path = "/Users/nelly/OS/A3/Question 2/Out2.txt";

	//File file = new File ("Output2.txt");
	
	final int n = 5;
	int i = 0;
	int left = (i + n -1)% n;
	int right = (i+1)% n;

	int thinking = 0;
	int hungry = 1;
	int eating = 2;

	long avH = 0;
	long avT = 0;
	long avE =0;

	Semaphore mutex = new Semaphore(1);
	private static Semaphore[] s = new Semaphore[]{new Semaphore(1),new Semaphore(1),new Semaphore(1),new Semaphore(1),new Semaphore(1)};
	int[] state = new int[n];

	long sleeper = (long) (Math.random() * 100) + 50;
  	long eater =  (long) (Math.random() * 40) + 10;

  	long hungryT;


	public void philosopher(int i){
		this.i = i;
	}


	@Override
	public void run(){
		try{
			int b =0;
			writer = new FileWriter(path,false);
			print = new PrintWriter(writer);
			
			while(b<61){
				think();
				takeForks(i);
				eat();
				putForks(i);
				Thread.sleep(1000);

				i++;
				if(i == 5){
					i = 0;
				}
				b++;
			}

			print.println((avH/5) + "ms is the average time being Hungry");
			print.println((avT /5) + "ms is the average time spent Thinking");
			print.println((avE/5) + "ms is the average time spent Eating");

			System.out.println("I added the data");
			print.close();

		}catch(Exception e){e.printStackTrace();}
	}


	  public static void main(String[] args) {
	  
	  Philosopher philosophers = new Philosopher();
					
		new Thread(philosophers).start();
	}


	public void takeForks(int i){

		try{	
		mutex.acquire();

		state[i] = hungry;
		hungryT = System.nanoTime();

		test(i);
		mutex.release();
		s[i].acquire();
	}catch(InterruptedException e){e.printStackTrace();}

	}


	public void putForks(int i){
		try{
		mutex.acquire();

		state[i] = thinking;
		long think = System.nanoTime();
		test(left);
		test(right);
		mutex.release();
		}catch(InterruptedException e){e.printStackTrace();}
		

	}

	public void test(int i){
		try{	
			writer = new FileWriter(path,true);
			print = new PrintWriter(writer);
			if(state[i] == hungry && state[left] != eating && state[right] != eating){
				long tempTime = System.nanoTime();
				state[i] = eating;
				
				print.println("Philosopher "+ (i+1) + " was Hungry for " + (tempTime - hungryT)/1000000 +"ms\n") ;

				
				avH = avH + (tempTime - hungryT)/1000000;

				

				s[i].release();
				print.close();
			}
		}catch(Exception e){e.printStackTrace();}
	}

	private void think() {
    try {

     writer = new FileWriter(path,true);
      print = new PrintWriter(writer);
      long track = System.nanoTime();
      Thread.sleep(10);
      long track2 = System.nanoTime();
     

      //write this to a file 
      print.println("Philosopher "+ (i+1) + " is Thinking for " + (track2 - track) /1000000 +"ms\n ") ;
      avT = avT + (track2 - track)/1000000;
      print.close();
    } catch (Exception e) {
    }
  }

    private void eat() {
    try {

      writer = new FileWriter(path,true);
      print = new PrintWriter(writer);
      long follow = System.nanoTime();
      Thread.sleep(eater);
      long temp = System.nanoTime();

      //write to a file
      print.println("Philosopher "+(i+1)+" is Eating for "+ (temp - follow)/ 1000000 +"ms\n ");
      avE = avE + (temp - follow)/1000000;
      print.close();
    } catch(Exception e) {
    }
  }


}