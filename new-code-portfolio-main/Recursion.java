/**
	
	@author Jarnell Hayes 
	@version 1.1



*/



public class Recursion{
	
	public static void main(String[] args){
		System.out.println("Fibonacci: " + fibonacci(10));
		System.out.println("Factorial: " + factorial(5));
		System.out.println(stringIterator("Beware M. Bison's psycho power!"));

	}

		/**

			@param nthTerm an int value that would represent a number in the fibonacci sequence
		*/
		public static int fibonacci(int nthTerm){
			if (nthTerm == 0 || nthTerm == 1){
				return nthTerm;
			}else{
				return fibonacci(nthTerm-1) + fibonacci(nthTerm -2);
			}
		}







		public static int factorial(int n){
			System.out.println("n: " + n);

			if(n==0 || n == 1){
				return 1;
			}else{

				return n*factorial(n-1);
				/**
				int currentNumber = n;
				int priorFactorial = factorial(n-1);
				int currentFactorial = currentNumber * priorFactorial;
				return currentFactorial;
				*/

			}
		}

		public static char stringIterator(String string){
			if(string.charAt(string.length()-1) == 'M'){

				return string.charAt(string.length()-1);

			}else{

				System.out.println(string);
				String substring = string.substring(0,(string.length()-1));
				return stringIterator(substring);
			}
		}
	






}