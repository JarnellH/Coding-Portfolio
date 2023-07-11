/**
 * A class that will take in a file name from the user and read the data 
 * to create SQL INSERT statements to populate a SQL Database
 *
 *@author Jarnell Hayes
 *@version 1.1  October 11,2021
 *
 * */



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;




public class HayesInserts{

	public static void main (String[] args ){
	
			String line = "";
			String[] files = {"Agent","Seller","Buyer","PhoneNumber","House", "Posting"};

			String location = "/Users/Nelly/Desktop/MyRecords/.csv";

			int index = 0;

			
 			
		//The following try catch block creates a reader to read the csv file 
		//it also creates an array of the csv values to store and input them into SQL INSERT Statements
		try{
			File file = new File ("/Users/Nelly/Desktop/MyRecords/Hayes_Inserts.sql");
 			file.createNewFile();
 			FileOutputStream out = new FileOutputStream("Hayes_Inserts.sql",true);

			String stream = "";
			//iterates through the files so that each table is populated
			for (int k = 0; k <= files.length-1;k++){
				location = "/Users/Nelly/Desktop/MyRecords/" + files[k] + ".csv";

				
		BufferedReader buffreader = new BufferedReader(new FileReader(location));
			
			//This while loop reads the file until the next line is null
			//it also tracks the values separated by commas in order to keep track of them
			while ((line = buffreader.readLine()) != null){
				

				String joined = " ";
				String temp = " ";
				String temp2 = " ";

				
				String[] values = line.split(",");
				index = values.length - 1;
				int j = 0;
				

					//This loop prints the INSERT statement while iterating through the lines in the csv
					//checking to see if there are integer strings
					while(j <= index){
					boolean intcheck = false;
					temp = values[j];
					char[] chars = new char[temp.length()];
					for(int l = 0 ; l <= chars.length-1; l++){
						chars[l] = temp.charAt(l);
					
					 intcheck = Character.isDigit(chars[l]);
					if(intcheck == true){
						temp =  values[j] ;

					}

				}
				if(intcheck == false){
					temp = String.format("'%s'",temp);
					}
					joined = String.join(",",joined,temp);
					temp2 = joined.substring(2);
						
					j++;
				}	
				
				//outputs the data into the created sql file Hayes_Inserts
				 stream = String.format("INSERT INTO %s VALUES (%s);\n",files[k],temp2); 
				 byte[] content = stream.getBytes();
				 out.write(content);

			}
			//produces commit statements after each table insert statements
			String commit = String.format("COMMIT;\n\n");
			byte[] push = commit.getBytes();
			out.write(push);
		}

			//closes the byte stream to the file
		//	out.close();

		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}

	}

}