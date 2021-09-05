/*
 ============================================================================
 Name        : dec2base.c
 Author      : Xiangyun(Alfred) Wang
 Student ID	 : 260771591
 Version     :
 Copyright   : Your copyright notice
 Description : converting base
 ============================================================================
 */

#include <stdio.h>

//global variable
int length;			//this is used to hold the length of the output


void dec2base (int input, int base, char* str){					//this function has three inputs: base, input, and the position of array str
	char dictionary[] = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";   //this array holds all the proper characters for the output, while will be used to translate
	if (input == 0){								//if input is 0, simply put 0 in the result array
		str[0] = '0';
		str[1] = '\0';							//add '\0' at the end of the array to indicate the end of the string
		length = 1;								//length is 1 when in this case

	} else{										//for input is not 0
		int i = 0;								//int i is used to track the position of the array
		while(input > 0){						//when input is less than 0
			int r = input % base;				//r is used to hold the remainder
			str[i] = dictionary[r];				//translate r into the proper format and put it into the array
			i++;									//i increases by 1 for each time a new element is added to the array
			input = input/base;					//redefine input
		}
		str[i] = '\0';							//add '\0' at the end of the array to indicate the end of a string
		length = i;								//use global variable length to hold the length of the array
	}
}

void revStr (char *str, int length){				//this function has two inputs: position of the array str, and the length of the array
	int half = (length-1)/2;						//Since we are reversing the array, we need to know the half size of the array.
												//We can simply do a loop for the first half of the array by switch elements with the second half of the array
	for (int i = 0; i <= half; i++){				//only do the loop for the first half of the array, starting at 0, end at the half
		char temp = str[i];						//create a temporary char to hold the element in the first half of the array
		str[i] = str[length-1-i];				//switch the element from the first half of the array with the corresponding element from second half of the array
		str[length-1-i] = temp;
	}
}

int main(int argc, char *argv[]) {
	char result[32];								//since i am using signed integer, and input must be greater or equal to 0,
												//then the longest result (in this case, it has base of 2), will have 31 elements, and we need one more to hold '\0' to indicate the end of the array.
	int input, base;								//create two int, input and base
	if(argc == 2){								//if argv array has a size of 2, this means that the user only gives the number needed to be converted, but does not indicate a specific base
		sscanf(argv[1],"%d", &input);			//let the second element in the argv array be the input (first element is the name of the program)
		base = 2;								//we manually set the base to 2

	}else if (argc == 3){						//if argv array has a size of 3, this means that the user gives a complete input
		sscanf(argv[1],"%d", &input);			//let the second element in the argv array be the input number
		sscanf(argv[2],"%d",&base);				//let the third element in the argv array be the base
	}

	if (argc==1 || input < 0 || base < 2 || base > 36){			//if the size of argv array is 1, of input is less than 0, or base is less than 2 or greater than 36
		printf("Illegal input!!\n\n");							//print "Illegal input!!"
	}else{														//if the input is legal
		dec2base(input,base,result);								//use dec2base to convert the input to the specific base, and pass it to result, but it is in reverse order now
		revStr(result, length);									//use revStr to reverse the result string
		printf("The base-%d form of %d is: %s \n\n",base,input,result);	//print the result in the proper format
	}
	return 0;
}
