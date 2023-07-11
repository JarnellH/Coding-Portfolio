

import java.io.*;

class Question1 implements Runnable{

String file = "/home/jarnell/Desktop/output.txt";
BufferedReader buffread;
long startTime;
long endTime;
long elapsedTime;

String[] words ={};
String lines = "";
String fix;
String temp;

int index = 0;
int lettercount = 0;
int totalwords = 0;

char[] letters;
int[] frequency = new int[8];

long percentage;

@Override
public void run(){

try{
	buffread = new BufferedReader(new FileReader(file));
	
	while (buffread.readLine() != null){
		line = buffread.readLine();
		words = line.split(" ");
		
		index = words.length-1;
		
		int i = 0;
		
		while(i <= index){
			fix = words[i];
			fix = fix.replaceAll("[^a-zA-Z0-9]","");
			words[i] = fix;
			
			temp = words[i];
			letters = new char[temp.length()];
			
			for(int track = 0; track <= letters.length-1;track++)			{
				lettercount = lettercount + 1;
				
				if (letters[track] == ' ' && lettercount != 0){
				lettercount = lettercount -1;
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
		totalwords = totalwords + frequency[occur];
	}
	
	for(int l = 0; l <= frequency.length-1; l++){
		int real = l+1;
		
		if(l < 7){
		percentage = (float) (frequency[l] * 100) / total;
			System.out.printf("%s letter - %s words, %s%", real,frequency[l],percent);
		}else{
			percentage = (float) (frequency[l] * 100) / total;
			System.out.printf("%s or more letter - %s words, %s%", real,frequency[l],percent);	
	
		}
		
	}

}catch(Exception e){e.printStackTrace();}



}

}

public class Question1{

	public static void main(String[] args){
		Question1 threaded = new Question1();

		//This is the source code for question one and the single threaded version of question 2
		//The number of implemented threads were changed in this main method
		Thread thread1 = new Thread(threaded);
		
		thread1.start();
	}
}

