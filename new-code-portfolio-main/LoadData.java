/*

 @author  Jarnell Hayes 
 @version 1.1 

The program below will establish a jdbc and interact with the data present in the 
table 
It will also read values from a csv file and then insert the corresponding records into 
the proper table record of the Data base

**/

import java.sql.*;
import java.io.*;

public class LoadData {
	public static void main(String[] args){

			String buyerFile = "Buyer";
			String locate = "/Users/Nelly/Desktop/MyRecords/" +buyerFile+ ".csv";
			PreparedStatement prepped = null;
			String line = "";

			String temp = " ";
			String temp2 = " ";
			String joined = " ";
			int tempi = 0;

			String[] vals = {};

			int index = 0;

		try{
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			Connection newconn = DriverManager.getConnection("jdbc:oracle:thin:@dbserver.cs.uno.edu:1521/ORCL", "[jjhayes]", "[revenge777]");

			Statement statement = newconn.createStatement();
			ResultSet result = statement.executeQuery("DELETE * FROM Buyer");

			prepped = newconn.prepareStatement("INSERT INTO Buyer VALUES (?,?,?,?,?,?)");

			while (result.next()){
				System.out.println( result.getInt(1) + " " + result.getString(2)+" "+result.getString(3)+" "+result.getInt(4)+" "+result.getInt(5)+" "+ result.getInt(6));

			}

			BufferedReader buffreader = new BufferedReader(new FileReader(locate));

			while ((line = buffreader.readLine()) != null){

				 vals = line.split(",");
				

				index = vals.length-1;
				int j = 0;


				while (j<= index){
					boolean check = false ;

					temp = vals[j];

					char[] chars = new char[temp.length()];
					for (int l = 0 ; l <= chars.length-1 ; l++){
						chars[l] = temp.charAt(l);

						check = Character.isDigit(chars[l]);

						Character omit = '-';

						if(check == true){
							temp = vals[j];
							tempi = Integer.parseInt(temp);
						}
						if(check == true && temp.charAt(l) == omit){
							temp = temp;
						}
					}
					if(check == false){
					temp = String.format("'%s'",temp);
					}
					


					System.out.println(prepped);

					int test = prepped.executeUpdate();

					//joined = String.join(",",joined,temp);
					//temp2 = joined.substring(2);
					j++;

				}

			}

			newconn.close();

		}catch(Exception e){
			System.out.println(e);
		}

	}
	
}