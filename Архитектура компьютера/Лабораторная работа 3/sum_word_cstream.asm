/* f32a */

	.data
input_addr:    		.word 0x80
output_addr:   		.word 0x84
high_part:      	.word 0x0
low_part: 	   		.word 0x0
current_num: 		.word 0x0

	.text
	.org	   0x88
_start:
	@p input_addr b!
	lit 1 eam
	
while_cstream:
	@b
	dup
	!p 	current_num
	dup
	if	sum_word_end
	-if	continue
	no_check_carry	;
continue:
	@p	high_part
	upd_low_part_with_carry_return
	+ 
	!p 	high_part
	while_cstream ;
	
no_check_carry:
	@p 	current_num
	@p	low_part
	+
	!p 	low_part
	while_cstream ; 
	
upd_low_part_with_carry_return:
	lit 0
	@p 	current_num
	@p 	low_part
	+
	dup
	!p 	low_part
	+
	@p 	low_part
	xor
	lit 1 and
	; 
	
sum_word_end:
	@p output_addr a!
	@p high_part !
	@p low_part !
	
	halt


/*
simulation_config =
name: assert sum_word_cstream([48, 18, 0]) == [0, 66]
limit: 2000
memory_size: 0x1000
input_streams:
  0x80: [48, 18, 0]
  0x84: []
reports:
  - name: Check results
    slice: last
    filter:
      - state
    view: |
      numio[0x80]: {io:0x80:dec}
      numio[0x84]: {io:0x84:dec}
    assert: |
      numio[0x80]: [] >>> []
      numio[0x84]: [] >>> [0,66]

*/