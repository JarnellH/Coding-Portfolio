
import java.io.*;
import java.util.concurrent.*;


public class Q2V2{

public static void main(String[] args) throws InterruptedException{
	
	BlockingQueue<String> blockQ = new ArrayBlockingQueue<String>(5024);
	Stream threaded1 = new Stream(blockQ);
	Manipulate threaded2 = new Manipulate(blockQ);
	
	Thread thread1 = new Thread(threaded1);
	Thread thread2 = new Thread(threaded2);
	
	thread1.start();
	thread2.start();
	
	thread1.join();
	thread2.join();
	
	
	
	double elapsedTime =  (threaded2.endTime - threaded1.startTime) / 100000000.0;
	
	System.out.printf("This took %.2f seconds" , elapsedTime);
	
	
	
}

}


 class Stream implements Runnable {
	protected BlockingQueue queue = null;
	
	String file = "/home/jarnell/Desktop/output.txt";
	BufferedReader buffread;
	String line = "";
	String signal = "THIS IS WHERE YOU STOP!!";
	
 	long startTime;
	
	
	public Stream (BlockingQueue queue){
		this.queue = queue;
	}
	
	@Override
	public void run(){
	
	startTime = System.nanoTime();
	
	try{
		buffread = new BufferedReader(new FileReader(file));
		while(buffread.readLine() != null){
			line = buffread.readLine();
			queue.put(line);
						
		}
		
		queue.put(signal);
	
	}catch(Exception e){e.printStackTrace();}
	
	}

}

class Manipulate implements Runnable{
	protected BlockingQueue queue = null;
	
	long endTime;
	
	String[] words ={};
	String fix;
	String temp = "";
	String hold = "";
	String signal = "THIS IS WHERE YOU STOP!!";

	int index = 0;
	int lettercount = 0;

	int total = 0;
	int i = 0;

	int[] frequency = new int[8];

	float percentage;
	
	public Manipulate (BlockingQueue queue){
		this.queue = queue;
	}
	
	@Override
	public void run(){
	
	try{
	
		while(queue.take() != signal){
			hold = (String) queue.take();
			
			words = hold.split(" ");
			
			index = words.length-1;
			
			while(i <= index){
			fix = words[i];
			fix = fix.replaceAll("[^a-zA-Z0-9]"," ");
			words[i] = fix;
			
			temp = words[i];
			char[] letters = new char[temp.length()];
			
			for(int track = 0; track <= letters.length-1;track++)			{
				letters[track] = temp.charAt(track);
				lettercount = lettercount + 1;
				
	
				if(letters[track] == ' '){

				frequency[lettercount-1] = frequency[lettercount-1] + 1;
				lettercount = 0;
				}
				
				if(lettercount >= 8){
				 frequency[7] = frequency[7] + 1;
				 lettercount = 0;
				}			
			}
			i++;
		}
				
	}
	
		for(int occur = 0 ; occur <= frequency.length-1; occur++){
			total = total + frequency[occur];	
		}
	
		System.out.println(total +" is total amount of words");
	
		for(int l = 0; l <= frequency.length-1; l++){
			int real = l+1;
		
			if(real < 8){
		percentage = (float) (frequency[l] * 100) / total;
			System.out.println(real +" letter - "+frequency[l]+ " words, " + percentage);
			
			}else{
			
			percentage = (float) (frequency[l] * 100) / total;
			System.out.println(real +" or more letters - "+frequency[l]+ " words, " + percentage);	
	
			}		
		}
		
		
		endTime = System.nanoTime();
		
	
	}catch(Exception e){e.printStackTrace();}
	
	}

}


