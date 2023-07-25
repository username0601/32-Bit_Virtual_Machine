package theBit;

/**
 * 
 * This function simulate a computer cpu running process
 * @author Kaiyi Li
 *
 */
public class computer {
	
	public Bit halt = new Bit(1);//The computer is halted when halt =0000, not halted when halt =0001
	
	public memory mem = new memory(); // Build 1024 bytes memory for this computer
	
	Longword  PC = new Longword(0);// Part 1: "Tracker" in the computer to load the next part of program
	
	Longword[] registers = new Longword[16];//	Part 2: Registers to store local variables
	Longword move_register_index;
	Longword currentInstruction; // 16 bits with 4 bits each representing operating code, two variable registers , 1 result register
	//The run loop keeping running when the computer is not halted.
	
	ALU alu = new ALU(); 
	Longword op1; //Operating variable 1
	Longword op2;//Operating variable 2
	Longword result; // Store the operation result
	
	Bit[] register1_address = new Bit[4];//Store register1's address
	Bit[] register2_address = new Bit[4];//Store register2's address
	
	Longword SP = new Longword(1019); // The stack pointer
	
	public void run() throws Exception {
		
		while(halt.getBit()==1) {
			System.out.println("Fetch instructions from meomory:");
			if(this.mem.storage[(int)this.PC.getUnsigned()*8] == null) {
				System.out.println("No instructions left in the memory, now we stop");
				break;
				
			}
			fetch(); // Call fetch function
			decode(); // Call decode function
			execute(); // Call execute function
			store(); // Call store function
		}
		System.out.println("The CUP is halted");
	}
	/**
	 *  Fetch the the instructions from memory with given PC
	 */
	public void fetch() {
		//read the next longword from memory as current instructions
		 currentInstruction = mem.read(PC);
		//increase PC by 2
		this.PC = rippleAdder.add(this.PC, new Longword(2));
	}
	
	/**
	 * 1.examine currentInstruction
	 * 2.determine the location of the 2 registers
	 * 3.shifting and masking
	 * 4.put the values from the registers into op1 and op2
	 * @throws Exception 
	 */
	public void decode() throws Exception {
		Bit[] ins_array = currentInstruction.bit_array; // Transform longword instructions into a bit array
		Bit[] opCode = new Bit[4]; // To store the operation code (for move instruction exclusively)
		for(int i = 0; i<4;i++) {
			opCode[i] = ins_array[i];
		}
		//Check if the opCode is a move instruction
		if(opCode[0].getBit()==0 && opCode[1].getBit()==0 && opCode[2].getBit()==0 && opCode[3].getBit()==1) {
			Bit[] register_bit_array = new Bit[4]; // Find the register index(for move operation) from the instruction code
			for(int i = 4; i<8;i++) {
				register_bit_array[i-4] = ins_array[i];
			}
			this.move_register_index = new Longword(register_bit_array); // Get the index for register1
		//check if it is a halt 	
		}else if (opCode[0].getBit()==0 && opCode[1].getBit()==0 && opCode[2].getBit()==0 && opCode[3].getBit()==0) {
			System.out.println("Its a halt");
		//Check if it is a jump
		}else if(opCode[0].getBit()==0 && opCode[1].getBit()==0 && opCode[2].getBit()==1 && opCode[3].getBit()==1){
			System.out.println("Its an unconditional jump, we jump");
		 //Check if it is a Stack instruction
		}else if(opCode[0].getBit()==0 && opCode[1].getBit()==1 && opCode[2].getBit()==1 && opCode[3].getBit()==0) {
			Bit[] stackOp = new Bit[2];
			for(int i = 0; i<2;i++) {
				stackOp[i] = ins_array[i+4];
			}
			if(stackOp[0].getBit()==0 && stackOp[1].getBit()==0) {
				System.out.println("It is a push instruction");
			}else if(stackOp[0].getBit()==0 && stackOp[1].getBit()==1) {
				System.out.println("It is a pop instruction");
			}else if(stackOp[0].getBit()==1 && stackOp[1].getBit()==0) {
				System.out.println("It is a call instruction");
			}else if(stackOp[0].getBit()==1 && stackOp[1].getBit()==1) {
				System.out.println("It is a return instruction");
			}
		}
		//Check if it is a conditional jump instruction	
		else if(opCode[0].getBit()==0 && opCode[1].getBit()==1 ){
			System.out.println("Its a conditional jump brunch:");
		}
		else{ // if the opCode are ALU operations
		
			Bit[] register1_bit_array = new Bit[4]; // Store the bits corresponding to register 1
			for(int i = 4; i<8;i++) {
				register1_bit_array[i-4] = ins_array[i];
			}
			Longword register1_index = new Longword(register1_bit_array); // Get the index for register1
			this.op1 = registers[(int)register1_index.getUnsigned()];
			
			Bit[] register2_bit_array = new Bit[4]; // Store the bits corresponding to register 2
			for(int i = 8; i<12;i++) {
				register2_bit_array[i-8] = ins_array[i];
			}
			Longword register2_index = new Longword(register2_bit_array); // Get the index for register2
			this.op2 = registers[(int)register2_index.getUnsigned()];
		}
	}
	/**
	 * Call the ALU along with op1 and op2
	 * @throws Exception
	 */
	public void execute() throws Exception {
		Bit[] ins_array = currentInstruction.bit_array; // Transform longword instructions into a bit array
		Bit[] opCode = new Bit[4]; // To store the operation code
		for(int i = 0; i<4;i++) {
			opCode[i] = ins_array[i];
		}
		//Move value into register[move_register_index]
		if(opCode[0].getBit()==0 && opCode[1].getBit()==0 && opCode[2].getBit()==0 && opCode[3].getBit()==1) {	
			System.out.println("Execute move instruction ");

		//implement halt	
		}else if(opCode[0].getBit()==0 && opCode[1].getBit()==0 && opCode[2].getBit()==0 && opCode[3].getBit()==0){
			this.halt = new Bit(0); // Create a halt instruction to stop the CPU running
		//implement unconditional jump
		}else if(opCode[0].getBit()==0 && opCode[1].getBit()==0 && opCode[2].getBit()==1 && opCode[3].getBit()==1){
			System.out.println();
		
			
			
			 //implement Stack instructions
		}else if(opCode[0].getBit()==0 && opCode[1].getBit()==1 && opCode[2].getBit()==1 && opCode[3].getBit()==0) {
			Bit[] stackOp = new Bit[2];
			for(int i = 0; i<2;i++) {
				stackOp[i] = ins_array[i+4];
			}
			//Push instruction
			if(stackOp[0].getBit()==0 && stackOp[1].getBit()==0) {
				Bit[] rIndex = new Bit[4];
				for(int i =0; i<4; i++) {
					rIndex[i]=ins_array[12+i];
				}
				Longword idx = new Longword(rIndex); //Get the index of register
				Longword registerValue = this.registers[(int) idx.getUnsigned()];	
				this.mem.write(registerValue,this.SP); // Push value into the memory
				this.SP = rippleAdder.subtract(this.SP,new Longword(4));
           
			// Implement pop instruction	
			}else if(stackOp[0].getBit()==0 && stackOp[1].getBit()==1) {
				Bit[] rIndex = new Bit[4];
				for(int i =0; i<4; i++) {
					rIndex[i]=ins_array[12+i];
				}
				Longword idx = new Longword(rIndex); //Get the index of register
				this.SP = rippleAdder.add(this.SP,new Longword(4)); // Get the stack pointer
				Longword registerValue = this.mem.read(this.SP); // Pop value from stack
				this.registers[(int) idx.getUnsigned()] = registerValue;
				
		   // Implement call instruction	
			}else if(stackOp[0].getBit()==1 && stackOp[1].getBit()==0) {
				Bit[] addressBit = new Bit[10];
				for(int i =6;i<16;i++) {
					addressBit[i-6]=currentInstruction.bit_array[i]; //Get address
				}
				Longword address = new Longword(addressBit);
				System.out.println("Call instruction from address " + address.getUnsigned());
				int address_i = (int)(address.getUnsigned()-1)*2;
				this.mem.write(this.SP,rippleAdder.add(this.PC,new Longword(2))); // push next address into stack
				this.PC =  new Longword(address_i);// Jump to the identified address 
				
				// Implement return instruction	
			}else if(stackOp[0].getBit()==1 && stackOp[1].getBit()==1) {
				Longword instructionAddress = this.mem.read(this.SP); // Get the address that has been returned by call
				this.PC = new Longword(2);
			}
			
		
		//implement conditional jump
		}else if(opCode[0].getBit()==0 && opCode[1].getBit()==1 ){
			if(opCode[2].getBit()==0 && opCode[3].getBit()==0) {
			
				for(int i=0;i<4;i++) {
					this.register1_address[i] = currentInstruction.bit_array[8+i];
					this.register2_address[i] = currentInstruction.bit_array[12+i];
				}
			}
			
			//implement interrupt
		}else if(opCode[0].getBit()==0 && opCode[1].getBit()==0 && opCode[2].getBit()==1 && opCode[3].getBit()==0){
			
			
			if(ins_array[15].getBit() ==  0) {//print all of the registers to the screen
				System.out.println("The CPU is interrupted,now we print the content of registers:");
				   int l = this.registers.length;
				   for (int i=0;i<l;i++) {
					   if(this.registers[i]!=null) {
						   Bit[] register_array = registers[i].bit_array;
						   int m = register_array.length;
						   for(int j=0;j<m;j++) {
							   System.out.print(register_array[j].getBit());
						   }
						   System.out.println();
					   }
					
					   
				   }
				   System.out.println();

				
			}else{//Interrupt 1: print all 1024 bytes of memory to the screen.
				System.out.println("The CPU is interrupted,now we print the content of memory:");
			   int l = this.mem.storage.length;
			   for (int i=0;i<l;i++) {
				   if(this.mem.storage[i]!=null) {
					   System.out.print(this.mem.storage[i].getBit());
				   }
				   
			   }
			   System.out.println();
			   System.out.println();
				
			}
			
		}else{// ALU operations
			this.result = alu.doOp(opCode,op1,op2); // Call ALU and get the operating resulting
			System.out.print("The ");
			for(int i=0;i<opCode.length;i++) {
				System.out.print(opCode[i].getBit());
			}
			System.out.print(" has been successfully operated.");
			
			System.out.println();
			System.out.println();
		}

	}
	/**
	 * copy the value from the result longword into the register 
	 */
	public void store() {
		Bit[] ins_array = currentInstruction.bit_array; // Transform longword instructions into a bit array
		Bit[] opCode = new Bit[4]; // To store the operation code
		for(int i = 0; i<4;i++) {
			opCode[i] = ins_array[i];
		}
		//Move value into register[move_register_index]
		if(opCode[0].getBit()==0 && opCode[1].getBit()==0 && opCode[2].getBit()==0 && opCode[3].getBit()==1) {
			Bit[] move_value_array = new Bit [8];
			for(int i = 8; i<16;i++) {
				move_value_array[i-8] = ins_array[i];
			}
			registers[(int)move_register_index.getUnsigned()] = new Longword(move_value_array);//Move value to register
		    System.out.println("The bits has been sucessfully moved to register.");
		    System.out.println();
		}else if(opCode[0].getBit()==0 && opCode[1].getBit()==0 && opCode[2].getBit()==0 && opCode[3].getBit()==0) {
			this.halt = new Bit(0); // Create a halt instruction to stop the CPU running
		
		}
		 //implement Stack instructions
	     else if(opCode[0].getBit()==0 && opCode[1].getBit()==1 && opCode[2].getBit()==1 && opCode[3].getBit()==0) {
				Bit[] stackOp = new Bit[2];
				for(int i = 0; i<2;i++) {
					stackOp[i] = ins_array[i+4];
				}
				//Push instruction
				if(stackOp[0].getBit()==0 && stackOp[1].getBit()==0) {
					System.out.println("Value in register has been pushed in stack");	
					System.out.println();
					//Pop instruction
				}else if(stackOp[0].getBit()==0 && stackOp[1].getBit()==1) {
					System.out.println("Value in the Stack has been poped to register");
					System.out.println();
					// Call instruction
				}else if(stackOp[0].getBit()==1 && stackOp[1].getBit()==0) {
					System.out.println("The instruction has been called");
					System.out.println();
				}else if(stackOp[0].getBit()==1 && stackOp[1].getBit()==1) {
					System.out.print("Fectch instruction with the address from stack,");
				}
				
	     }
		//Unconditional Jump
		else if(opCode[0].getBit()==0 && opCode[1].getBit()==0 && opCode[2].getBit()==1 && opCode[3].getBit()==1){
			Bit[] address_array = new Bit[12];
			for(int i =4;i<16;i++) {
				address_array[i-4]=currentInstruction.bit_array[i];
			}
			Longword address = new Longword(address_array);
			int address_i = (int)(address.getUnsigned()-1)*2;
			this.PC =  new Longword(address_i);// Jump to the identified address 
		//Conditional Jump	
		}else if(opCode[0].getBit()==0 && opCode[1].getBit()==1 ){
			// Compare
			if(opCode[2].getBit()==0 && opCode[3].getBit()==0) {
			Longword r_address1 = new Longword(this.register1_address);
			Longword r_address2 = new Longword(this.register2_address);
			
			if(registers[(int)r_address1.getUnsigned()].getUnsigned()>=registers[(int)r_address2.getUnsigned()].getUnsigned()) {
				System.out.println("Value in register " +(int)r_address1.getUnsigned() + " is larger than or equal to value in " +  (int)r_address2.getUnsigned()+", so we jump to " +  (int)r_address1.getUnsigned());

				Longword address = registers[(int)r_address1.getUnsigned()];
				int address_i = (int)(address.getUnsigned()-1)*2;
				this.PC = new Longword(address_i);// Jump to the identified address 
				
			}else {
				System.out.println("Value in register " +(int)r_address2.getUnsigned() + " is larger than or equal to value in " +  (int)r_address1.getUnsigned()+", so we jump to " +  (int)r_address2.getUnsigned());
				Longword address = registers[(int)r_address2.getUnsigned()];
				int address_i = (int)(address.getUnsigned()-1)*2;
				this.PC = new Longword(address_i);// Jump to the identified address 
				
			}
			// Branchif	
			}else if(opCode[2].getBit()==0 && opCode[3].getBit()==1) {
				Bit[] address_value = new Bit[8];
				for(int i=0; i<8; i++) {
					address_value[i] = ins_array[8+i];
					
				}
				Longword address = new Longword(address_value);
				int address_i = (int)(address.getUnsigned()-1)*2;
				//Equal
				if(ins_array[4].getBit() == 0 && ins_array[5].getBit() == 1 ) {

					if (this.PC.getUnsigned() == new Longword(address_i).getUnsigned()) {
					System.out.println("The given value equal, so we don't jump");	
					}else {
						System.out.println("It's a conditional jump, the given value does not equal so we jump");		
						this.PC =  new Longword(address_i);// Jump to the identified address
					}
					
				//Not Equal	
				}else if(ins_array[4].getBit() ==0 && ins_array[5].getBit() == 0){
					
					if (this.PC.getUnsigned() != new Longword(address_i).getUnsigned()) {
					System.out.println("It's a conditional jump, the given value do not equal to , so  we jump");
					this.PC = new Longword(address_i);
					}else {
					  System.out.println("The given value  equal to , so we won't jump");
						
					}
					
				//Greater than	
				}else if(ins_array[4].getBit() ==1 && ins_array[5].getBit() == 0){
					if ( new Longword(address_i).getUnsigned()>this.PC.getUnsigned()) {
					System.out.println("It's a conditional jump, the given value is greater  so we jump");
					this.PC = new Longword(address_i);
					}else {
						System.out.println("The given value is smaller than  so we won't jump");
					}
					
				//Greater than or equal	
				}else if(ins_array[4].getBit() ==1 && ins_array[5].getBit() == 1){
					
					if (new Longword(address_value).getUnsigned()>=this.PC.getUnsigned()) {
					System.out.println("It's a conditional jump, the given value is greater than or equal to, so we jump");
					this.PC = new Longword(address_i);
					}else{
						System.out.println("The given value is smaller, so we won't jump");
						
					}
				}
			}
		
			//implement interrupt
		}else{
			Bit[] register3_bit_array = new Bit[4]; // Store the bits corresponding to register 3
			for(int i = 12; i<16;i++) {
				register3_bit_array[i-12] = ins_array[i];
			}
			Longword register3_index = new Longword(register3_bit_array); // Get the index for register2
			registers[(int)register3_index.getUnsigned()] = this.result;//copy the value from the result longword into the register 
			
		}
		
	}
	/**
	 * initialize the memory
	 * @param wrt
	 * @throws Exception 
	 */
	public void preload(String[] wrt) throws Exception {
		for(int i=0;i<wrt.length;i++) {
			String bit_string = wrt[i];
			String[] strings = bit_string.split("");
			Bit[] bits= new Bit[strings.length];
			for(int j =0;j<strings.length;j++) {
				bits[j] = new Bit(Integer.parseInt(strings[j]));
			}
			Longword formem = new Longword(bits);
			this.mem.write(new Longword(i*16),formem);
		}
		
	}
	public static void main(String[] args) throws Exception {
		computer c = new computer();
		String [] testString = {"123","234"};
		c.preload(testString);
	}
}
