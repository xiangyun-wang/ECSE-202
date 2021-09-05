package assignment2;

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
}
