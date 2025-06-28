/* risc-iv-32 */

	.data
input_addr:    			.word 0x80
output_addr:   			.word 0x84

	.text
_start:
	lui     sp, %hi(0x200)
	addi    sp, sp, %lo(0x200)
	
    lui     t0, %hi(input_addr)
    addi    t0, t0, %lo(input_addr)
	lw		t0, 0(t0)
	lw		a0, 0(t0)
	
	jal 	ra, reverse_bits
	
	lui     t0, %hi(output_addr)
    addi    t0, t0, %lo(output_addr)
	lw		t0, 0(t0)
	sw		a1, 0(t0)	
    halt
	
reverse_bits:
	addi 	a2, zero, 1
	addi	t1, zero, 32
reverse_bits_loop:
	and 	a3, a0, a2
	addi	sp, sp, -4
	sw		ra, 0(sp)
	jal		ra, add_bit_to_result
	lw		ra, 0(sp)
	addi	sp, sp, 4
	
	srl		a0, a0, a2
	addi	t1, t1, -1
	beqz 	t1, reverse_bits_return
	j		reverse_bits_loop
	
reverse_bits_return: 
	jr 		ra
	
add_bit_to_result:
	sll 	a1, a1, a2
	add     a1, a1, a3
	
	jr 		ra


/*
simulation_config =
name: assert reverse_bits(1) == -2147483648
limit: 2000
memory_size: 0x1000
input_streams:
  0x80: [1]
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
      numio[0x84]: [] >>> [-2147483648]
*/