package assignment2;

import java.io.BufferedReader; 
import java.io.FileReader; 
import java.io.IOException; 
import java.util.Scanner;

public class A2 {
	
	@SuppressWarnings("resource")
	
	public static void main(String[] args) {
		
		bTree nametree = new bTree();  //create a new tree
		Stack reverseorder = new Stack(); //create a new stack
		
	// Prompt user for a file name. If no name is entered, terminate 
	// the program, otherwise attempt to open the file. If file open
	// is not successful, prompt again for a new name. Keep doing this // until successful open, or a blank line is entered.
		//System.out.println("Simple File Listing Program"); 
		Scanner sc = new Scanner(System.in); 
		BufferedReader rd = null;
		
		System.out.println("Assignment 2 - File Sorting Program: "); 
		while(rd == null) {
			System.out.print("Enter name of file to sort: "); 
			String filename = sc.nextLine();
			if (filename.equals("")) {
					System.out.println("Program terminated");
					System.exit(0);							 // Exit 
			}
	// Try to open the specified file
			try {
				rd = new BufferedReader(new FileReader(filename));
			}
			catch (IOException ex) {
					System.out.println("Unable to open file, try again.");
			} 
		}
	// Read the file a line at a time into a string. Print as read 
	// to the output display. Modify the code below as necessary.
		System.out.println(""); 
		try {
			while (true) {
				String line = rd.readLine(); // Read a line of text
				if (line == null) break;		// Exit if at end of file
				nametree.addNode(line);		//add the name to the nametree
			}								// to current line
		}
		catch (IOException ex) {
			System.out.println("I/O Error - program terminated");
			System.exit(-1); 
		}

		
		System.out.println("\nFile in sort order: \n");
		
		nametree.inorder();		//output the nametree in order
		
		System.out.println("\n\nFile in reverse sort order: \n");

		reverseorder = nametree.push_to_stack();   //push the data of the nametree to a stack called reverseorder
		
		while (true) {
			String out = reverseorder.pop(); //pop out the stack until it meets null
			if (out == null) break;
			System.out.println(out);
		}	
		
		System.out.println("\n\nProgram terminated");
	}
}
