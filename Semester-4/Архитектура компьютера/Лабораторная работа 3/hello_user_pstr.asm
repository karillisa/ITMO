/* acc32 */

	.data
	
buffer:			   	.byte 7, 'Hello, ________________________'
message_to_user:    .byte 19, 'What is your name?\n'
tmp:		   		.word 0
one:	       		.word 1
end_of_line: 	    .word 0xA
loop_cntr: 	   		.word 0
cursor: 	   	   	.word 0
read_input_flag:  	.word 0
limit: 				.word 30
print_count: 		.word 2
exclamation_mark:	.word 0x21
byte_mask:	   		.word 0x0000_00FF
clear_low_byte:    	.word 0xFFFF_FF00
in_port:    		.word 0x80
out_port:   		.word 0x84

	.text
    .org 0x88

_start:
	load_imm		message_to_user
	store 			cursor
	load_ind 		cursor
	and 			byte_mask
    store			loop_cntr
	load			cursor
	add				one
	store			cursor
	
output_pstring:	
	load_ind		cursor
	and 			byte_mask
	store_ind		out_port
	load			cursor
	add				one
	store			cursor
	load			loop_cntr
	sub				one
	store			loop_cntr
	bnez 			output_pstring
	
output_pstring_return:
	load			print_count
	sub 			one
	store			print_count
	beqz 			hello_user_pstr_end

	
get_input_steam:
	load_imm		buffer
	store			tmp
	load		    buffer
	add				tmp
	add     		one
	and				byte_mask
	store 			cursor	
	
while_input_stream:	
	load_ind		in_port
	and				byte_mask
	store			tmp
	xor				end_of_line
	beqz			get_input_stream_end
	load_ind		cursor
	and             clear_low_byte
	add				tmp
	store_ind		cursor
	load			buffer
	add				one
	store			buffer
	and 			byte_mask
	xor				limit
	beqz			handle_overflow
	load			cursor
	add				one
	store			cursor
	jmp				while_input_stream

get_input_stream_end:
	load_ind		cursor
	and				clear_low_byte
	add				exclamation_mark
	store_ind       cursor
	load			buffer
	add				one
	store			buffer
	
print_buffer:
	load_imm		buffer
	store 			cursor
	load_ind 		cursor
	and 			byte_mask
    store			loop_cntr
	load			cursor
	add				one
	store			cursor
	jmp				output_pstring

handle_overflow:
    load_imm		0xCCCC_CCCC 		; overflow return value
    store_ind       out_port

hello_user_pstr_end:
    halt


/*
simulation_config = 
name: assert hello_user_pstr('Alice\n') == ('What is your name?\nHello, Alice!', '')
# and mem[0..31]: 0d 48 65 6c 6c 6f 2c 20 41 6c 69 63 65 21 5f 5f 5f 5f 5f 5f 5f 5f 5f 5f 5f 5f 5f 5f 5f 5f 5f 5f
limit: 2000
memory_size: 0x1000
input_streams:
  0x80: [65, 108, 105, 99, 101, 10]
  0x84: []
reports:
  - name: Check results
    slice: last
    filter:
      - state
    view: |
      numio[0x80]: {io:0x80:dec}
      numio[0x84]: {io:0x84:dec}
      symio[0x80]: {io:0x80:sym}
      symio[0x84]: {io:0x84:sym}
      {memory:0:31}
    assert: |
      numio[0x80]: [] >>> []
      numio[0x84]: [] >>> [87,104,97,116,32,105,115,32,121,111,117,114,32,110,97,109,101,63,10,72,101,108,108,111,44,32,65,108,105,99,101,33]
      symio[0x80]: "" >>> ""
      symio[0x84]: "" >>> "What is your name?\nHello, Alice!"
      mem[0..31]: 	0d 48 65 6c 6c 6f 2c 20 41 6c 69 63 65 21 5f 5f 5f 5f 5f 5f 5f 5f 5f 5f 5f 5f 5f 5f 5f 5f 5f 5f
*/