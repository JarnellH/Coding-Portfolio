/**
*
*  @author  Jarnell Hayes 
*  @version 1.1 
*
* The program below will establish a jdbc and interact with the data present in the 
* table 
* It will also read values from a csv file and then insert the corresponding records into 
* the proper table record of the Data base
*
**/

import java.sql.*;
import java.io.*;

public class LoadData {
	public static void main(String[] args){

			String buyerFile = "Buyer";
			String locate = "/Users/Nelly/Desktop/MyRecords/Buyer.csv";
			PreparedStatement prepped = null;
			String line = "";

			String temp = " ";
			String[] vals = {};
			int index = 0;

	try{

			//The class name and the connection driver 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection newconn = DriverManager.getConnection("jdbc:oracle:thin:@dbserver.cs.uno.edu:1521/ORCL", "jjhayes", "revenge777");

			//statement to establish connection
			Statement statement = newconn.createStatement();

			//Deletes the values from the table 
			ResultSet resu = statement.executeQuery("DELETE FROM Buyer");


			//Prepared statment which inserts Buyer Values with appropriate schema 
			prepped = newconn.prepareStatement("INSERT INTO Buyer VALUES (?,?,?,?,?,?)");

/*
		
			--Buyer(SSN, Name, Telephone,MinPrice,MaxPrice,AgentId)
*/
			//Reads the csv file to iterate through the values 
			BufferedReader buffreader = new BufferedReader(new FileReader(locate));


			//while loop which ensures all lines are read
			while ((line = buffreader.readLine()) != null){
				vals = line.split(",");
				index = vals.length-1;

				int j = 0;
				int i = 0;
				char omit = '-';


			//this while loop helps keep track of the lines 
			while(j <= index){
					boolean check = false;
					temp = vals[j];

					char[] chars = new char[temp.length()];

					//the for loop compares each char in the string sequences
					for ( i = 0 ; i <= chars.length-1 ; i++){
						chars[i] = temp.charAt(i);

						//a check with if statements that ensures proper SQL syntax
						check = Character.isDigit(chars[i]);

						if(check == true ){
							temp = vals[j];
						
						}
						if (check == false || temp.charAt(i) == omit){

							temp = String.format("'%s'" , temp);
							break;
						}			
						
					}
						
						j++;

					//System.out.println(j+" " + temp);
					prepped.setString(j,temp);
					String commit = String.format("COMMIT:\n\n");

			
				}
				
			}

			//closes the data base connection
			newconn.close();

		}catch(Exception e){
				System.out.println(e);
			}


	try{

			//Establish a Connection to the database
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@dbserver.cs.uno.edu:1521/ORCL", "jjhayes", "revenge777");

			Statement state = conn.createStatement();

			ResultSet result = state.executeQuery("SELECT * FROM House");


			//House(Id , Address , StreetName , SquareFt, AgentId , SellerSSN)
			// The while loop collects the records from the database and provides condtions while looping 
			//to then print the correct output
			while (result.next()){
				int houseid = result.getInt("HouseId");
				int address = result.getInt("Address");
				String street = result.getString("StreetName");
				int sqft = result.getInt("SquareFt");
				int houseAgent = result.getInt("HouseAgentId");
				int sellerssn = result.getInt("HouseSellerSSN");

				ResultSet result2 = state.executeQuery("Select * FROM Buyer");

				if(result2.next()){
				int buyerAgent = result2.getInt("BuyerAgentId");
				int mini = result2.getInt("MinPrice");
				System.out.println(houseid+" "+ address+" "+street+" "+sqft+" " +houseAgent+" "+sellerssn);

				if(buyerAgent == houseAgent && mini >= 100000 && mini <= 200000){

					System.out.println(houseid+" "+ address+" "+street+" "+sqft+" " +houseAgent+" "+sellerssn);
				}

				}

				//queries all agent records 
				ResultSet result3 = state.executeQuery("Select * FROM Agent");

				if(result3.next()){

				
				String office = result3.getString("Office");
				String param = "New Orleans";
				int aid = result3.getInt("Agent_Id");

					//iterates through and selects records according to condtions(Located in New Orleans )
					if (aid == houseAgent && office.equals(param)){
						System.out.println(houseid+" "+ address+" "+street+" "+sqft+" " +houseAgent+" "+sellerssn);
					}else{
						System.out.println("No rows were selected");
					}

				 }

			}
			//closes database connection
			conn.close();

		}
		catch(Exception e ){
			System.out.println(e);
		}

	}

}



	
