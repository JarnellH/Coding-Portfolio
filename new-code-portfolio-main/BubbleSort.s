.data
	input : .space 12
	
	out_string : .asciiz "Enter A Sequence of five numbers to sort!\n"

	edge : .asciiz "The array is empty!\n"

.text


main:
	
	li $v0 , 4
	la $a0 , out_string
	syscall

	
	li $v0 , 5
	la $a0 , input
	syscall

	
	
	addi $a1 , $a1 , 5  


sort: addi $sp, $sp, -20

	sw $ra , 16($sp) # the destination  
	sw $s3 , 12($sp)
	sw $s2 , 8($sp)
	sw $s1 , 4($sp)
	sw $s0 , 0($sp)


	move $s2 , $a0 
	move $s3 , $a1
	move $s0 , $zero


	test1: slt $t0, $s0, $s3
		beq $t0, $zero, exit1
		addi $s1, $s0, -1

	test2: slti $t0 , $s1 , 0
		bne $t0, $zero, exit2
		sll $t1, $s1, 2
		add $t2, $s2, $t1
		lw $t3 , 0($t2)
		lw $t4, 4($t2)
		slt $t0 , $t4, $t3 
		beq $t0 , $zero, exit2 
		move $a0, $s2
		move $a1, $s1
		jal swap

		addi $s1 , $s1 , -1 
		j test2


	exit2 : addi $s0, $s0, 1
		j test1


	swap: sll $t1, $a1, 2
		add $t1 , $a0 , $t1

		lw $t0, 0($t1)
		lw $t2, 4 ($t1)
		sw $t2, 0 ($t1)
		sw $t0, 4 ($t1)
		jr $ra 

	Check:
		li $v0 , 4
		la $a0 , edge
		syscall
		j main


	exit1: 
		lw $s0 ,0($sp)
		lw $s1, 4($sp)
		lw $s2, 8($sp)
		lw $s3, 12($sp)
		lw $ra, 16($sp)
		addi $sp, $sp, 20

		li $v0 , 10
		move $a0 , $t1
		syscall
		jr $ra



