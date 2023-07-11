
/**
	@author Jarnell Hayes
	@version 1.1
*/


public class Operations{

	/**
		@param x an integer value
		@param y an integer value 
		@return the sum of the two integer values
	*/
	public static int add(int x , int y){

		return x + y;

	}

	/**
		@param x an integer value 
		@param y an integer value 
		@return the result of the subtraction of one integer value from the other 
	*/
	public static int subtract(int x , int y){

		return x - y;
		
	}

	/**
		@param x an integer value 
		@param y an integer value 
		@return the product of the multiplication of one integer value times the other 
	*/
	public static int multiply(int x , int y){

		return x * y;
		
	}

	/**
		@param x an integer value 
		@param y an integer value 
		@return the quotient result of the division of one integer value from the other 
	*/
	public static int divide(int x , int y){


		if(y == 0){
			throw new ArithmeticException("Divide by 0!");
		}
		
		return x/y;
	}

}