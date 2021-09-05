package assignment3;

import java.util.*;
import acm.program.*;
/**
 * 
 * @author xiangyunwang
 *
 */
public class In2pJ extends ConsoleProgram{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * the method below compare the precedence of two operators (no precedence for parenthesis)
	 */
	private int comprece (String operatorinstack, String operatortemp) {
		if ((operatorinstack.equals("+") ||operatorinstack.equals("-")) && (operatortemp.equals("*") ||operatortemp.equals("/"))) {
			return -1;				//return -1 if top operator in stack has a lower precedence than the input
		}else if ((operatorinstack.equals("/") ||operatorinstack.equals("*")) && (operatortemp.equals("+") ||operatortemp.equals("-"))){
			return 1;               //return 1 if top operator in stack has higher precedence than the input
		}else {
			return 0;				//return 0 if top operator in stack has the same precedence than the input
		}
	}
	
	/**
	 * the method below checks if the input is an operator or not
	 * (in this program, parentheses are not considered as operators, but they will be stored in the operator stack )
	 */
	private Boolean isOperator (String input) {
		if (input.equals("+")||input.equals("-")||input.equals("*")||input.equals("/")) { 
			return true;				//if the input is one of + - * /, then return true
		}else {
			return false;
		}
	}
	
	/**
	 * The method below checks if the input is a number or not
	 * if it is a number, return true, if not, return false
	 */
	private Boolean isNumber (String input) {
		int decimal = 0;										//"decimal" is used to count the number of ".", to make sure that the input does not have two or more decimal points
		int i = 0;											//"i" is used to go through each index of the input string
		if (input.length()==0) return false;					//if the length of the input is 0, return false
		for (i= 0; i<input.length(); i++) {					//go through each index of the string
			char test = input.charAt(i);
			if (Character.isDigit(test)) {					//if the index is a number, keep the loop
				
			}else if (test == '.') {							//if the index is ".", check the length of the string and the number of "." detected
				if (decimal == 1||input.length()==1) return false;		//if the length of the string is one (only contains "."), or there is an existing decimal point, return false
				decimal += 1;								//otherwise, record the number of the decimal point
			}else {											
				return false;								//if it is neither "." or a number, return false
			}
		}
		return true;											//if the entire string is checked and it does not return false, return true (which means the input is a proper number)
	}
	
	public void run() {
		
		Queue input = new Queue();						//this queue is used to hold input
		Queue output = new Queue();						//this queue is used to hold output
		Stack operator = new Stack();					//this stack is used to hold operators
		String previous = "none";						//this string is used to hold the previous element that is pushed into the output queue
		Boolean negative = false;						//this boolean is used to indicate whether the input number is positive or negative; if the number is negative, it is true; if the number is positive, it is false
		Boolean error = false;							//this boolean is used to indicate if there is an error or not
		int open = 0;									//this int is used to count the number of the open parenthesis
		int close = 0;									//this int is used to count the number of the close parenthesis
		
		
		String formula = readLine("Enter String: ");									//ask for the user input
		formula = formula.replace(" ", "");											//delete all the space in the input
		StringTokenizer split = new StringTokenizer(formula, "+-*/()",true);			//separate the input by "+-*/()", and also save these separators
		
		while(split.hasMoreTokens()) {
			String a = split.nextToken();							//use "a" to hold the next token
			if (a.equals("(")) open += 1;							//if the string equals "(", record it
			else if (a.equals(")")) close += 1;						//else if the string equals ")", record it
			input.enqueue(a);										//push the separated inputs into the input queue
		}

		if (open != close) error = true;
		
		while(input.front != null && error == false) {													//keep the loop until there is no more elements in the input queue or there is an error
			
			String temp = input.dequeue();											//create a string called "temp", to temporarily hold an element from the input queue
			
			if(((isOperator(temp)||temp.equals(")"))&&isOperator(previous))||(isOperator(temp)&&input.front == null)) {		//if there are two consecutive operators from the input, return error and break the while loop
				error = true;
				break;
			}else if (isOperator(temp)||temp.equals("(")||temp.equals(")")) {			//if the input is an operator or parentheses

				if ((output.isEmpty()||previous.equals("(")||previous.equals("-(")) && isOperator(temp)) {					//This if-statement checks unary operator (also contains two error cases)
					/*
					 * There are three cases for unary operator: 
					 * 1. The formula starts directly with "+" or "-". Example: -a + b
					 * 2. The negative or positive number is in a pair of parentheses. Example: a + (-b)
					 * 3. "+" or "-" is in front of a pair of parentheses. Example: -(a+b) (this is a very special case, so "-(" is created to handle this case)
					 * 
					 * Therefore, if one of the following conditions is true: 
					 * 1. the output queue is empty
					 * 2. the previous element dealt with is "(" 
					 * 3. the previous element dealt with is "-("
					 * And at the same time, one of the following conditions is also true: 
					 * 1. The element from the input queue is "-"
					 * 2. The element from the input queue is "+"
					 * Then it can be told that this is a unary operator
					 */
					if (temp.equals("-")&&input.front.data.equals("(")) {  
						/*
						 * This is for the case -(a+b)
						 * If the element from the input queue is "-", and the next element in the input queue is "(", case -(a+b) is satisfied
						 * While pushing to the output, if it meets "-(", "-1" and "*" will be pushed to the output queue
						 * Example: input: case -(a+b) output: a b + -1 *
						 * ("+" is simply ignored)
						 */
						temp = "-(";
						operator.push(temp);			
						input.dequeue();		//Since we used the next element in the input queue, we have to dequeue it 
						
					}else if (temp.equals("-")||temp.equals("+")){	//Standard cases for unary operators. Cases: -a + b and a + (-b)
						if (temp.equals("-")){						//if number is negative, let the program know, and add a negative sign for the next number
							negative = true;							//"negative" is used to let the system know if the next number is negative or positive
						}											//cases for unary operator "+" are simply ignored
					
					}else {											//This else statement contains two error cases:
						error = true;								//1. the formula starts with "*" or "/"
						break;										//2. the formula in the parentheses starts with "*" or "/"
					}
				// unary operator ends
					
				} else if (operator.isEmpty()||temp.equals("(")) {	// if there is no operator in the stack or the input element is "(", 
					operator.push(temp);								//just simply push the input element into the operator stack
					
				}else if(temp.equals(")")) {							//if the input is a close parenthesis, 
					while (true) {									//keep popping until it meets the open parenthesis or "-("
						String out = operator.pop();
						if (out.equals("(")) {
							break;									//break if meets "("
						} else if(out.equals("-(")) {				//if it meets "-(", enqueue "-1", and enqueue"*", then break;
							output.enqueue("-1");
							output.enqueue("*");
							break;
						}
						output.enqueue(out); 
					}
				}
				
				else {																	//this is the general case of pushing and popping operators
					
					if(comprece(operator.top.data,temp) == 1) {							//if the operator from the input queue has a lower precedence than the top element in the operator stack
						while (true) {													//pop until it meets an open parenthesis or there is nothing left in the operator stack
							String out = operator.pop();
							if (out == null) break;
							else if (out.equals("(")) {operator.push("("); break;}
							else if (out.equals("-(")) {operator.push("-("); break;}
							output.enqueue(out);
						}
						operator.push(temp);												//then push the operator from the input queue to the stack
						
					}else if (comprece(operator.top.data,temp) == -1){					//if the operator from the input queue has a higher precedence than the top element in the operator stack
						operator.push(temp);												//simply push the operator into the operator stack
						
					}else if (operator.top.data.equals("(")||operator.top.data.equals("-(")){		//if the previous operator pushed is "(" or "-("
						operator.push(temp);}													//simply push the operator into the stack
					
					else {																//if the operator from the input queue has the same precedence as the top element in the operator stack
						output.enqueue(operator.pop());									//pop the top operator from the stack
						operator.push(temp);												//push the input operator into the operator stack
					}
				}
				
			}else if (isNumber(temp)){									//if the input is a number
				if(negative == true) { 									//to see if the number is negative or not
					temp = "-" + temp; 									//if negative, add a plus sign at the front
					negative = false;									//reset negative to false
				}
				output.enqueue(temp);
			}else {									//if the input is neither a number nor an operator nor a parenthesis
				error = true;						//it is an illegal input and set "error" to true then break the loop
				break;
			}
			
			previous = temp;							//use the input to redefine "previous"
		}
		
		while (true) {								//pop all the operator left in the stack, and enqueue them to the output queue
			String out = operator.pop();
			if (out == null) break;
			output.enqueue(out);
		}
		
		if (error == true) {							//if there is an error, print "Error! " and clear the output queue
			print("Error! ");
			while (true) {
				String out = output.dequeue(); 
				if (out == null) break;
			}
		}else {										//if there is no error, dequeue the output queue and print the output
			print("Postfix: ");
			while (true) {
				String out = output.dequeue(); 
				if (out == null) break;
				print(out+" ");
			}	
		}
	}
}
