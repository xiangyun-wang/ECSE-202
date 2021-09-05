/*
 ============================================================================
 Name        : sortFile.c
 Author      : Xiangyun(Alfred) Wang
 McGill ID   : 260771591
 Version     : 2.0
 Copyright   : Your copyright notice
 Description : Name sorting program
 ============================================================================
 */

 #include <stdio.h>
 #include <stdlib.h>
 #include <string.h>
 #define MAXBUF 100
 #define MAXNAME 100               //maximum length of each name

//global variable
 struct listNode* reverse = NULL; //create a stack to hold names
                //this stack will be used to reverse the order of the name list

//------------------------------------------------------------------------------

/*
  The following four lines of code creates a structure called bNode(binary node).
  Each bNode contains three variables: data, left and right
*/
 struct bNode{
 	char data[MAXNAME];     //"data" has type char array, which will be used to hold names
 	struct bNode* left;     //"left" and "right" have type pointer to struct bNode,
 	struct bNode* right;    //which will be used to hold addresses of adjacent bNodes
 };

/*
  The following lines of code create a function which adds a new node to a tree.
  It will take a pointer of bNode(tree) and a pointer to a string as inputs,
  and it will create a new node which contains the string and then add it to
  the existing tree. Finally, return the a pointer to the new tree.
*/
 struct bNode* addNode(struct bNode* root, char* name){
 	if (root == NULL){                                        //if root points to nothing
 		root = (struct bNode*) malloc (sizeof(struct bNode));   //allocate memory to pointer(size of the space equals to the size of a bNode)
 		strncpy(root->data, name, MAXNAME);    //copy the string from pointer name to the address of root->data up to MAXNAME characters
 		root->data[MAXNAME-1] = '\0';          //set the last elements of root->data to '\0' to indicate the end of the string
 		root->left = NULL;           //set pointer root->left to NULL
 		root->right = NULL;          //set pointer root->right to NULL
 	}else{               //if the root is not a NULL pointer
 		if (strcasecmp(name,root->data)>0){         //compare the string at the address of root->data and the string pointed by name
 			root->right = addNode(root->right, name); //if name is larger than root->data, turn to the right branch of the tree and run the addNode function again
 		}else{                                      //if name is smaller than root->data,
 			root->left = addNode(root->left, name);   //turn to the left branch of the tree and run the addNode function again
 		}
 	}
 	return root;        //return the address of the binary tree
 }

/*
  The following lines of code build a function which can print data
  of a binary tree in order. This function takes a pointer to a bTree
  as the input and print it in order.
*/
 void inorder_traversal(struct bNode* root){
 	if(root->left != NULL){               //if it is not NULL, call the function again,
 		inorder_traversal(root->left);      //and take the left of the original bNode as the input
 	}
 	printf("%s", root->data);             //print the data of the root
 	if (root->right != NULL){             //if the right side of the root if not NULL
 		inorder_traversal(root->right);     //call the function again, and take the right
                                        //of the original bNode as the input
 	}
 }
//------------------------------------------------------------------------------
/*
  The following four lines of code create a structure called listNode.
  A listNode contains a string which will be used to hold names,
  and it also contains a pointer to the next listNode
*/
 struct listNode{
 	char data[MAXNAME];               //data has array type with size of MAXNAME
 	struct listNode* next;            //next has type listNode*, which will be used to
                                    //hold the address of the next listNode
 };

/*
  The following lines of code create a method called push,
  which will add a new listNode to the stack. It takes the pointer to the
  original stack and the name as inputs, and return the address of the mew stack
*/
 struct listNode* push(struct listNode* stack, char* name){
 		struct listNode* update;           //updata has type pointer to a listNode,
                                       //which is the new address of the stack
 		update = (struct listNode*) malloc (sizeof(struct listNode));
    //allocate memory space to the pointer
 		strncpy(update->data,name,MAXNAME); //pass the string from name to updata->data
 		update->next = stack;      //the address of the orignal stack is passed to update->next
 	return update;               //return the new address of the stack
 }

/*
  The following lines of code create a function called pop, which will take
  a pinter to a stack as an input, and print every element in the stack
*/
 void pop(struct listNode* stack){
 	printf("%s",stack->data);       //print the data of the stack
 	if (stack->next != NULL){       //if stack->next is not null
 		pop(stack->next);     //call the function again, but this time using stack->next as the input
 	}
 }
//------------------------------------------------------------------------------
/*
  The following lines of code push the data of a bTree to a stack. It takes a
  pointer to a bTree and push the data of it to a global stack
*/

 void push_to_stack(struct bNode* root){
 	if(root->left != NULL){           //if root->left is not NULL
 		push_to_stack(root->left);      //call the function again and take the left
                                    //of the orginal input as the new input
 	}
 	reverse = push(reverse,root->data); //reverse is the name of the global stack
                                      //push the data of the bNode to the stack
 	if (root->right != NULL){           //if the right of the bNode is not NULL,
 		push_to_stack(root->right);      //call the function again and take the right
                                      //of the orginal input as the new input
 	}
 }

//------------------------------------------------------------------------------

 int main(int argc, char *argv[]) {
 // Internal declarations
 	FILE * FileD;			// File descriptor (an object)!
 	char *line;				// Pointer to buffer used by getline function
 	int bufsize = MAXBUF;	// Size of buffer to allocate
 	int linelen;				// Length of string returned (getline)

 // Argument check
 	if (argc != 2) {
 		printf("Usage: fileReader [text file name]\n");
 		return -1;
 	}

 // Attempt to open the user-specified file.  If no file with
 // the supplied name is found, exit the program with an error
 // message.

 	if ((FileD=fopen(argv[1],"r"))==NULL) {
 		printf("Can't read from file %s\n",argv[1]);
 		return -2;
 	}

 // Allocate a buffer for the getline function to return data to.

 	line=(char *)malloc(bufsize+1);
 	if (line==NULL) {
 		printf("Unable to allocate a buffer for reading.\n");
 		return -3;
 	}

 // If the file exists and a buffer was successfully allocated,
 // use the getline function to read the file libe by line.  This
 // is directly analogous to the readLine method in Java.
 //
 // Notice that the first argument to getline is a pointer to
 // a pointer.  This type of argument passing is used when
 // the function modifies that actual value of the pointer itself
 // as well as the data that the pointer references.  This detail
 // is beyond the scope of this course.

 // Another detail about the behavior of the getline function -
 // it returns the \n (newline) delimiter at the end of the
 // current line, so you need to remember to strip this off
 // depending on how you use the string.  Since this function
 // simply prints the file, the newline is left in and does not
 // have to be added in the printf call.
  struct bNode* tree;         //create a pointer of bTree
  tree = NULL;                //set the pointer ot NULL
  char filename[100];         //string filename is used to hold the name of
                              //the file which will be sorted

  printf("\n\nAssignment 6 - File Sorting Program\nEnter name of file to sort: ");
  scanf("%s", filename);      //get the filename
  if(strcmp(filename,argv[1])==0){    //if the name of the file entered later is
            //the same as the name of the file entered while calling the program

    while ((linelen=getline(&line,(size_t *)&bufsize,FileD))>0){
      tree = addNode(tree,line);    //add each line of the file to tree
    }

    push_to_stack(tree);            //push the data of the tree to the reverse stack

    printf("\n\nFile in sort order: \n\n");
    inorder_traversal(tree);        //print the data of the tree in order

    printf("\n\n\nFile in reverse sort order: \n\n");
    pop(reverse);                   //print the data of the reverse stack
                                    //(this will be in reverse order)

  }else{                          //if the file names entered are not compatible
    printf("\n\nThis name is not right!!!");
  }

  printf("\n\nProgram terminated......\n\n");


 	return EXIT_SUCCESS;
 }
