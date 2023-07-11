#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <string.h>

/**

  This program will write the  recursively forked data into a text file 

*/

int main(){

	int a , b = 1 ,pid, ppid ,pid2;

	//The file to be written to 
	FILE *file_point;
	file_point = fopen("ProcessRecords.txt" , "w");

	//Recieves user input 
	printf("Provide a number: ");
	scanf("%d" , &a);

	//for loop which recursively invokes fork()
	for(b = 1 ;b < a; b++){
		pid = fork();

		if(pid == 0){
			sleep(1);
		}	

		pid2 = wait(NULL);
		
		//condition statement to get the process IDs
		if(pid2 != -1){
		ppid = getppid();
		printf("PID: %d Finished exec , Parent PPID : %d \n" , pid2 , ppid );	

		}
	}
	fprintf(file_point,"PID: %d, ParentID: %d\n", getpid() , getppid());

	fclose(file_point);
	return 0;
}