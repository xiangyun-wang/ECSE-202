package assignment4;

public class Stack {
	
	listNode top = null;   //create a listNode called top
	
	/**
	 * This method pushes the input into a stack
	 * @param data is the user input
	 */
	public void push(String data) {
		listNode node = new listNode(); 				//create a new node to hold one data and another node
		node.data = data;							//let node.data = user input
		node.next = top;								
		top = node;									//redefine top
	}
	
	/**
	 * This method pops out the data of a stack
	 * @return is the popped data
	 */
	String pop() {
		if (top == null) {							
			return null;
		}
		String data = top.data;						//create a string called data, which will be used as the return
		top = top.next;								//redefine top
		return data;
	}
	
	/**
	 * This method checks if the stack is empty or not, 
	 * if it is empty, return true, if not, return false
	 * @return indicates if the stack is empty or not
	 */
	Boolean isEmpty() {
		if (top == null) {
			return true;
		}else {
			return false;
		}
	}
}
