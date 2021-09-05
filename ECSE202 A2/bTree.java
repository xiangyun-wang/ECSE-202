package assignment2;


public class bTree {
	bNode tree = null;									//create a bNode and name it "tree"
	Stack reverse = new Stack();							//create a new Stack Object, name it "reverse"
	
	//the following method add a new node to the tree
	public void addNode(String data) {
		tree = rNode(tree, data);
	}

	/**
	 * This method compare two strings
	 * @param data is the string to compare to 
	 * @param name is the string that need to be compared
	 * @return is the result of the comparison
	 */
	private int compare(String data, String name) {
		return data.compareToIgnoreCase(name);          //if data > name, return positive number; if data < name, return negative number; 
	}												   //if data = name, return 0		
	
	/**
	 * This method create and add a new node to the tree
	 * @param root is an existing node which the data of it will be used to compare to the input, and a new node might attach to the left or right of it
	 * @param data is the input string
	 * @return is the new tree
	 */
	private bNode rNode(bNode root, String data) {		
		if (root == null) {								//if there is nothing, create a new node and add it to the tree
			bNode node = new bNode();
			node.data = data;
			node.left = null;
			node.right = null;
			root = node;
		} else if (compare(root.data, data) > 0) {		//compare the data of the node to the input data
			root.left = rNode(root.left, data);			//if input is smaller than the data of the node, put it to the left, and call this method again
		} else {
			root.right = rNode(root.right, data);		//if input is larger that the data of the node, put it to the right side, and call this metho again
		}
		return root; 									
	}
	
	/**
	 * this is a wrapper for inorder_traversal
	 */
	public void inorder() {
		inorder_traversal (tree);
	}
	
	/**
	 * This method follows the order of the tree and output the data in order
	 * @param root contains data which will be printed
	 */
	private void inorder_traversal (bNode root) {
		if (root.left != null) inorder_traversal(root.left);		//follow the tree, always check the left side of the node first
		System.out.println(root.data);							//keep checking, until the left side of the node is null, print the data
		if (root.right != null) inorder_traversal(root.right);	//then check the right side of the node
	}

	/**
	 * This is a wrapper for to_stack method
	 * @return a stack which contains all the names
	 */
	public Stack push_to_stack() {
		to_stack(tree);
		return reverse;
	}
	
	/**
	 * The method will push all the names in the tree to a stack, named reverse
	 * @param root is the tree that contains all the name
	 */
	private void  to_stack (bNode root) {
		if (root.left != null) to_stack(root.left);			//follow the tree, always check the left side of the first
		reverse.push(root.data);								//keep checking, until the left side of the node is null, push it to the stack
		if (root.right != null) to_stack(root.right);		//then check the left side of the node
	}
}
