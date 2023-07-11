.data

	out_string : .asciiz "Enter A Number to find its Factorial!\n"

	out_bounds : .asciiz "The number entered is out of bounds!!Please Enter one in range \n"

.text  

main:

 	#Prompt user to provide a number
	li $v0 , 4
	la $a0 , out_string 
	syscall


	#the code for receiving user input 
INPUT:
	li $v0 , 5
	syscall

	#Takes the input and stores it and prepares edge case handle

	move $t0 , $v0
	move $t1 , $t0
	addi $t3,$t3, 1
	addi $t4, $t4, 12
	

Factorial:

	blt $t1, $zero, CHECK
	beq $t1 , $zero, Default
	bge $t1, $t4 , CHECK
	
    ble $t1 , $t3, EXIT
   
	addi $t1, $t1, -1
	mul $t0 , $t0, $t1
	j Factorial

#error handle for negatives
CHECK:
	li $v0 , 4
	la $a0 , out_bounds
	syscall
	move $t4, $zero 
	j INPUT

Default:

	addi $t0, $zero , 1
	j EXIT


EXIT:
	li $v0, 1
	move $a0 , $t0
	syscall 

	jr $ra

