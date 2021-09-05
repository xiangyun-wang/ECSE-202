package assignment_1_final_version;

import java.util.Scanner;

public class dec2base {
	// the method below translate numbers into proper characters
	private static char translate (int remainder) {						// input is the remainder, which will be translated and add to the final output
		char translated;													// represent translated elements
		String dictionary = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";		// act as a "dictionary",has all proper characters for the final output
		translated = dictionary.charAt(remainder);						//translate remainder into proper characters for the final output
		return translated;
	}
	// the method below convert the input number(has base of 10) into the number of target base
	private static String dec2B (int number, int base) {					
		String converted = "";											//this is used to store converted numbers
		int r, converting = number;										//r represents remainder, converting represents quotient
		if (number == 0) {												//this is the case that the input is 0
			converted = "0";
		} else if (number < 0) {											//to see if the input is a negative number or not
			converting = -number;										//if it is negative, make it positive
		}
		while (converting > 0) {											//continue this loop until "converting" is no longer greater than 0
			r = converting % base;										//get the remainder
			converted = converted + translate(r);						//translate method is used here, and the translated element is added to the converted string
			converting = converting / base;								//redefine "converting", and continue the loop
		}
		if (number < 0) 	converted = converted + "-";						//if the original input is negative, give it back the "-" sign
			
		return converted;
	}
	// the method below reverse the input string
	private static String reverse (String before_reversed) {				//input a string which needs to be reversed
		String reversed = "";											//"reversed" is used to store reversed string
		for (int i = before_reversed.length()-1; i >= 0;i--) {			//start from the last element of the original string, put it into a new string called reversed
			reversed = reversed + before_reversed.charAt(i);
		}
		return reversed;
	}
	
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);							
		System.out.println("Java base conversion demo: ");
		while (true) {
			System.out.print("Enter number to be converted: ");
			int input = sc.nextInt();									//get the number from user, which will be converted
			System.out.print("Enter base to convert to: ");
			int base = sc.nextInt();									//get the target base from the user
			if (base > 35 || base < 2) {									//check if the user input a valid number for base, if not, let the user input again, but for only one more time
				System.out.println("This is not a proper base, it should be any integer between 2(inclusive) and 35 (inclusive). ");
				System.out.print("Enter base to convert to (if the base is still invalid, this program will stop): ");
				base = sc.nextInt();
			}
			if (base > 35 || base < 2) {									//if the base entered is still invalid, break the program
				System.out.println("Program ended.....");
				break;
			}
			System.out.println(input + " is represented in base-" + base + " as " + reverse(dec2B(input,base)) ); //print the result
		}
		sc.close();
	}
}
