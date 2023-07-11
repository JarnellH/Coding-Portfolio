.data
	array :.space 12


	empty : .asciiz "There is no array present please enter values..."
	promptu : .asciiz "Input 3 numbers for the array \n"
	return_message : .asciiz "The sorted sequence is  "
	white_space : .asciiz " "
.text

main:

	li $v0 ,4
	la $a0 , promptu
	syscall 


	la $s1 ,array

LoopInput :

	#this fills the array from user input
	li $v0 , 5
	syscall 

	beq $v0 , 0 , Check

	#the user input data is stored while the pointer and count is incremented
	sw $v0 , 0 ($s1)
	addi $s1 ,$s1, 4
	addi $t1 , $t1, 1
	bne $t1 , 3 ,LoopInput


	#array is updated and count initialized 
	la $s1 , array
	move $t1 , $zero


#the sort sequence for the array
Sort: 

	#compares two array vals
	beq $t2 , 2, cont
	lw $t3 , 0($s1)
	lw $t4 , 4($s1)

	addi $s1, $s1 ,4
	addi $t2 , $t2 , 1

	ble $t3 , $t4, Sort

	#swaps the values based on comparison
	sw $t3 , 0 ($s1)
	sw $t4 , -4($s1)
	bne $t2 , 2, Sort


cont: 
	#inner loop that keeps the sorting going until finished
	la $s1 , array
	addi $t1, $t1,1
	add $t2 ,$zero, $t1

	bne $t1, 2 , Sort


	la $s1 , array
	move $t1 , $zero


	li $v0 , 4
	la $a0 , return_message
	syscall



print:
#prints out the sorted array sequence and restores registers 

	li $v0 , 1
	lw $a0 , 0($s1)
	syscall

	li $v0 , 4
	la $a0 , white_space
	syscall

	addi $s1, $s1, 4
	addi $t1, $t1, 1
	bne $t1 ,3 , print

	move $t1, $zero
	move $t2, $zero
	move $t3, $zero
	move $t4, $zero
	
	j $ra


Check:
#throws an empty array exception and prompts user to submit data
	li $v0 , 4
	la $a0 , empty
	syscall
	j main

	
