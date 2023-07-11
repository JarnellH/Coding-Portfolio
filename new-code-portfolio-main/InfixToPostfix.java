
/**
	@author Jarnell Hayes
	@version 1.1

*/

public class InfixToPostfix extends MyStack{


	/**
		This method will take a string input as an infix of a stack and determine the posfix output
		@param String s 

	*/


	public String infixToPostfix(String s){

		MyStack <Character> stack = new MyStack<Character>();


		//ch is the character array of the infix string 
		//postfix will become the postix output

		String postfix = "";
		char[] ch = s.toCharArray();


		/**
			Char array checks for the operators making sure to push the operands first
			And continues to check for parenthesis as well

			A second char variable is used to compare and add onto the stack

			the else if condition then checks for the operators and compares them so that they are added onto the stack 
			correctly
		*/

		for(char c: ch){

			if(c != '+' && c != '-' && c != '*' && c != '/' && c != '(' && c != ')'){
				postfix = postfix + c;

			}else if(c == '('){
				stack.push(c);

			}else if (c == ')'){
				while(!stack.isEmpty()){
					char t = stack.pop();

					if(t != '('){
						postfix = postfix + t;

					}else{

					break;

					}
				}
			} else if(c == '+' || c == '-' || c == '*' || c == '/' ){
				if(stack.isEmpty()){
					stack.push(c);
				}else{
					while(!stack.isEmpty()){
						char t = stack.pop();
						if(t == '('){
							stack.push(t);
							break;
						}else if (t == '+' || t == '-' || t == '*' || t == '/' ){
							if(getPriority(t) < getPriority(c)){
								stack.push(t);
								break;
							}else{
								postfix = postfix + t;
							}
						}
						
					}
					stack.push(c);
				}
			}
		}
	
	while(!stack.isEmpty()){
			postfix = postfix + stack.pop();
		}

		return postfix;
		
	}

	/**
		This will compare the operators so that the precedence is determined
		@param char c 

	*/

	public int getPriority(char c){
		if (c == '+' || c == '-'){
			return 1;
		} else {
			return 2;
		}
	}


	
}