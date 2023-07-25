package theBit;

import java.util.Iterator;


/**
 * 
 * @author Kaiyi Li
 * This function simulate an assembler
 *
 */
public class Assembler {
	
	/**
	 * This function is the simplest implementation of an assembler
	 * @param inputs
	 * @return outputs
	 * @throws Exception 
	 */
	public static String[] assemble (String inputs) throws Exception {
		String[] instruction = inputs.split(" "); // Decode instruction into a string array
		String[] outputs = null; // Store the result bits string
		String op = instruction[0].toLowerCase();
		if(instruction.length ==1) {//Halt
			if(op.equals("halt")) {
				outputs = new String[1];
				outputs[0] = "0000000000000000";
			}else if(op.equals("return")) { //Return instruction
				outputs = new String[1];
				outputs[0] = opHelper(op);

			}
			
		}else if(instruction.length ==2) { // Jump 4, BrunchIfEqual Interupt
			
			
			if(op.equals("interrupt")) {
				outputs = new String[2];
				outputs[0] = opHelper(op);
				outputs[1] = valueHelper2(instruction[1],12);
				
			}else if(op.equals("jump")) { // For jump operation
				outputs = new String[2];
				outputs[0] = opHelper(op);
				outputs[1] = valueHelper2(instruction[1],12);
			}else if(op.equals("push")) { //push instruction
				outputs = new String[2];
				outputs[0] = opHelper(op);
				outputs[1] = registerHelper(instruction[1]);
			}else if(op.equals("pop")) { //Pop instruction
				outputs = new String[2];
				outputs[0] = opHelper(op);
				outputs[1] = registerHelper(instruction[1]);
			}else if(op.equals("call")) { //Call instruction
				outputs = new String[2];
				outputs[0] = opHelper(op);
				outputs[1] = valueHelper2(instruction[1],10);
			}
			else if(op.substring(0,6).equals("brunch")) { //For brunchif operation
				outputs = new String[3];
				outputs[0] = opHelper("brunchif");
				
				if(op.equals("brunchifequal")) { //equal
					outputs[1] = "0100";
					outputs[2] = valueHelper2(instruction[1],8);
				}else if(op.equals("brunchifnotequal")) {//notequal
					outputs[1] = "0000";
					outputs[2] = valueHelper2(instruction[1],8);
				}else if(op.equals("brunchifgreater")) {// greater
					outputs[1] = "1000";
					outputs[2] = valueHelper2(instruction[1],8);
				}else if(op.equals("brunchifgreaterorequal")) {// greater or equal
					outputs[1] = "1100";
					outputs[2] = valueHelper2(instruction[1],8);
				}
			}
		}else if(instruction.length ==3) { // Check instruction length (For example "Move R1 1" equals 3, but "Add R3 1 2" equals 4)
			
			if(op.equals("compare")) {
				outputs = new String[4];
				outputs[0] = opHelper(instruction[0]);
				outputs[1] = "0000";
				outputs[2] = registerHelper(instruction[1]); //Register 1
				outputs[3] = registerHelper(instruction[2]); //Register 2
			}else {
				outputs = new String[3];
				outputs[0] = opHelper(instruction[0]);
				outputs[1] = registerHelper(instruction[1]); 
				outputs[2] = valueHelper1(instruction[2]); // Call value1 helper
			}
		
		}else if(instruction.length ==4) {
			outputs = new String[4];
			outputs[2]= valueHelper(instruction[2]);
			outputs[3]= valueHelper(instruction[3]);
			outputs[0] = opHelper(instruction[0]);
			outputs[1] = registerHelper(instruction[1]); 
		}else {
			throw new Exception("invalid instruciton");
		}

		return outputs;
		
	}
	/**
	 * This function loop over each character. When a space is found, it identify an token
	 * It will decide if the token is operation, register, or number
	 * @param inputs
	 * @return
	 * @throws Exception
	 */
	public static String[] lexical(String inputs) throws Exception {
		String[] characters = inputs.split(""); 
		int total_length = characters.length;
		StringBuilder builder = new StringBuilder() ; // to store identified token
		String token = "";
		for (int i=0;i<total_length;i++) {
			if(!characters[i].trim().isEmpty() ) { // Identify a space
				token = token + characters[i];
			}else {
				builder.append(token); // add the identified token to a string container
				builder.append(" ");
				token = "";
			}
		}
		builder.append(token);
		String [] instruction = builder.toString().split(" ");
		String[] outputs;
		if(instruction.length ==3) { // // Check instruction length (For example "Move R1 1" equals 3, but "Add R3 1 2" equals 4)
			outputs = new String[3];
			outputs[2] = valueHelper1(instruction[2]);
		}else if(instruction.length ==4) {
			outputs = new String[4];
			outputs[2] =valueHelper(instruction[2]);//  Convert the first value into binary string bits
			outputs[3] =valueHelper(instruction[3]); // Convert the second value into binary string bits
		}else {
			throw new Exception("invalid instruciton");
		}
		outputs[0] = opHelper(instruction[0]); // Convert operation into binary string bits
		outputs[1] = registerHelper(instruction[1]); // Convert register index into binary  string bits
		return outputs;
		
	}
	
	/**
	 * recursive descent parser to get the elements of instruction
	 * @param inputs
	 * @return
	 * @throws Exception
	 */
	public static String[] parser(String inputs) throws Exception {
		String[] instruction = inputs.split(" ");
		String[] characters = inputs.split("");
		String[] outputs;
		if(instruction.length ==3) { // Process instructions with 3 tokens
			outputs = new String[3];
			//Get operator
			int index =0;
			String op ="";
	        op = recursive(characters,index,op);
	        outputs[0] = opHelper(op);
	        
			//Get register
	        index = index +op.split("").length +1;
	        String re = "";
	        re = recursive(characters,index,re);
	        outputs[1] = registerHelper(re);
	        
	       //Get value
	        index = index +re.split("").length +1;
	        String value = "";
	        value = recursive(characters,index,value);
	        outputs[2] = valueHelper1(value);
		}else if(instruction.length ==4) { // Process instructions with 4 tokens 
			
			outputs = new String[4];
			//Get operator
			int index =0;
	        String op = "";
	        op = recursive(characters,index,op);
	        outputs[0] = opHelper(op);
	        
			//Get register
	        index = index +op.split("").length +1;
	        String re = "";
	        re = recursive(characters,index,re);
	        outputs[1] = registerHelper(re);
	        
	       //Get value 1
	        index = index +re.split("").length +1;
	        String value1 = "";
	        value1 = recursive(characters,index, value1);
	        outputs[2] = valueHelper(value1);
	        
	        //Get value 2
	        index = index +value1.split("").length +1;
	        String value2 ="";
	        value2 = recursive(characters,index,value2);
	        outputs[3] = valueHelper(value2);
			
		}else {
			throw new Exception("invalid instruciton");
		}
		return outputs;
	}
	/**
	 * This function use recursive to get each element(token) of the instruction
	 * @param characters
	 * @param index
	 * @param token
	 * @return
	 */
	public static String recursive (String[] characters,int index, String token) {
		if(index <characters.length&& !characters[index].trim().isEmpty()) { // stop recursive when the whole characters are processed or an space is indentified
			token = token + characters[index];
			index++; // move to next characters
			return recursive(characters,index,token); // recursive 
		}
		return token;
		
	}
	
	/**
	 * This funciton convert each operation into its corresponding binary bits value
	 * @param op
	 * @return
	 */
	public static String opHelper(String op) {
		String op_bits = null;
		switch(op.toLowerCase()) {
		case "move":
			op_bits = "0001";
			break;
		case "halt":
			op_bits = "0000";
			break;
		case "and":
			op_bits = "1000";
			break;
		case "or":
			op_bits = "1001";
			break;
		case "xor":
			op_bits = "1010";
			break;
		case "not":
			op_bits = "1011";
			break;
		case "left":
			op_bits = "1100";
			break;
		case "right":
			op_bits = "1101";
			break;
		case "add":
			op_bits = "1110";
			break;
		case "subtract":
			op_bits = "1111";
			break;
		case "jump":
			op_bits = "0011";
			break;
		case "compare":
			op_bits = "0100";
			break;
		case "brunchif":
			op_bits = "0101";
			break;
		case "interrupt":
			op_bits = "0010";
			break;
		case "push":
			op_bits = "011000000000";
			break;
		case "pop":
			op_bits = "011001000000";
			break;
		case "call":
			op_bits = "011010";
			break;
		case "return":
			op_bits = "0110110000000000";
		}
		return op_bits;
		
	}
	/**
	 * Register starts with letter "R", followed by a decimal number which denote the index of the register
	 * This function convert the decimal number into a binary string value
	 * @param register
	 * @return
	 */
	public static String registerHelper(String register) {
		int string_length = register.split("").length;
		String register_index = register.substring(1, string_length); // Trim to get the decimal number
		int int_index = Integer.parseInt(register_index); // Convert the string value of index to integer value
	    String string_index = Integer.toBinaryString(int_index); // Convert index to string bits
	    char zero = '0';
	    while(string_index.split("").length<4) {
	    	string_index = zero+ string_index; // Make the binary index 4 bits by add 00s at the beginning
	    }
	    return string_index;
		
		
	}
	/**
	 * This function help to convert decimal number string to a 4 bits binary number string
	 * @param value
	 * @return
	 */
	public static String valueHelper(String value) {
	    String string_value = Integer.toBinaryString(Integer.parseInt(value));// Convert the value string into a binary string value
		if(Integer.parseInt(value)>0) { // Check if value is positive
			char zero = '0';
		    while(string_value.split("").length<4) { // less than 4 bits
		    	string_value = zero+ string_value;// Make it an 4 bits by adding 00s before the binary string bits
		    }
		}else {
			int length = string_value.length();
			string_value = string_value.substring(length-4,length); //trim the last 8 bits of a negative binary string bits 
		}
		

	    return string_value;
	}
	
	/**
	 * This function help to convert decimal number string to a 8 bits binary number string
	 * @param value
	 * @return
	 */
	public static String valueHelper1(String value) {
		 String string_value = Integer.toBinaryString(Integer.parseInt(value)); // Convert the value string into a binary string value
		if(Integer.parseInt(value)>0) { //Check if the value is negative
			char zero = '0';
		    while(string_value.split("").length<8) { // less than 8 bits
		    	string_value = zero+ string_value; // Make it an 8 bits by adding 00s before the binary string bits
		    }
		}else {
			int length = string_value.length(); 
			string_value = string_value.substring(length-8,length); // trim the last 8 bits of a negative binary string bits 
		}
	   

	    return string_value;
	}
	
	/**
	 * This function help to convert decimal number string to a [bitnumber] bits binary number string
	 * @param value
	 * @return
	 */
	public static String valueHelper2(String value, int bitnumber) {
		 String string_value = Integer.toBinaryString(Integer.parseInt(value)); // Convert the value string into a binary string value
		if(Integer.parseInt(value)>=0) { //Check if the value is negative
			char zero = '0';
		    while(string_value.split("").length<bitnumber) { // less than 8 bits
		    	string_value = zero+ string_value; // Make it an 8 bits by adding 00s before the binary string bits
		    }
		}else {
			int length = string_value.length(); 
			string_value = string_value.substring(length-bitnumber,length); // trim the last 8 bits of a negative binary string bits 
		}
	   

	    return string_value;
	}
	public static void main(String[] args) throws Exception {
		
		String[] outputs1 = assemble("Add R1 1 2");
		
		for(int i =0; i<outputs1.length;i++) {
			System.out.print(outputs1[i]);
			
		}
        System.out.println();
		String[] outputs = lexical("Add R1 1 2");
		for(int i =0; i<outputs.length;i++) {
			System.out.print(outputs[i]);
		}
		System.out.println();
		
		String[] outputs2 = parser("Add R1 1 2");
		
		for(int i =0; i<outputs2.length;i++) {
			System.out.print(outputs2[i]);
		}
		
	}
	

	
	

}
