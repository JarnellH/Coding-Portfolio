/**

	@version 1.1
	@author Jarnell Hayes 

	The following program will manipulate data within a sql database 
	using a jdbc  and queries created within the program 

*/

import java.io.*;
import java.sql.*;

public class ManipData{

	public static void main(String[] args) {


		

		try{

			//Establish a Connection to the database
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection newconn = DriverManager.getConnection("jdbc:oracle:thin:@dbserver.cs.uno.edu:1521/ORCL", "jjhayes", "revenge777");

			Statement state = newconn.createStatement();

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

				ResultSet result3 = state.executeQuery("Select * FROM Agent");

				if(result3.next()){

				
				String office = result3.getString("Office");
				String param = "New Orleans";
				int aid = result3.getInt("Agent_Id");

					if (aid == houseAgent && office.equals(param)){
						System.out.println(houseid+" "+ address+" "+street+" "+sqft+" " +houseAgent+" "+sellerssn);
					}else{
						System.out.println("No rows were selected");
					}

				 }

			}

			newconn.close();

		}
		catch(Exception e ){
			System.out.println(e);
		}
	
	}

}