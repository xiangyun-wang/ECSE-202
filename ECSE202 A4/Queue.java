package assignment4;

public class Queue {
	
	listNode front = null;
	listNode rear = null;
	/**
	 * this method add an element to the queue
	 * @param input is the user input that will be added to the queue
	 */
	void enqueue (String input) {
		listNode node = new listNode();
		node.data = input;
		node.next = null;
		if (rear != null) {
			rear.next = node;
		}else {
			front = node;
		}
		rear = node;
	}
	
	/**
	 * this method will pop out the first element in the queue
	 * @return is the first element of the queue
	 */
	String dequeue() {
		if (front != null) {
			if (front == rear) {
				rear = null;
			}
			String out = front.data;
			front = front.next;
			return out;
		}else {
			return null;
		}
	}
	
	/**
	 * This method checks if the queue is empty or not
	 * if it is empty, return true, if not, return false
	 * @return indicates if the queue is empty or not
	 */
	Boolean isEmpty() {
		if (front == null){
			return true;
		}else {
			return false;
		}
	}
}
