package theBit;
/**
 * 
 * @author Kaiyi Li
 * This class simulate a memory in a computer
 *
 */
public class memory {
	
	public static Bit[] storage = new Bit[1024*8];
	
	public static Longword read(Longword address) {
	  
		int start = (int)address.getUnsigned()*8; //Get the integer value of the address
		int end = start;// Define the end index of the longword in storage
		//Get the length of available longword from memory
		while(storage[end] !=null) {
			end++; 
		}
		if(end >(int)address.getUnsigned()*8+16) end =(int)address.getUnsigned()*8+16;
		Bit[] result = new Bit[end-start]; // Store the reading result
		// Read the desired longword
		for(int i =0; i<end-start;i++) {
			result[i] = storage[start+i];
		}
		return new Longword(result);// Return the result
		
	}
	public static void write(Longword address, Longword value) throws Exception {
		
		
		int address_l = (int)address.getUnsigned(); //Get the integer value of the address
		if(address_l>1024*8) throw new Exception("Out of memory"); // Check if address start beyond 1024*8
		int value_length = value.bit_array.length; // Get the length of the value bit array
		Bit[] value_array = value.bit_array;
		if ((address_l + value_length)>1024*8) throw new Exception("Out of memory"); //Check if the value is too long for the storage space
		// Write value in the memory
		for(int i=0;i<value_length;i++) {
			storage[address_l+i] = value_array[i];
		}
	}
}
