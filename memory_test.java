package theBit;
 
public class memory_test {
 
	public static void main(String[] args)
	{
		runTests(); 
	}
	
	public static void runTests() 
	{	try
	{
		testWrite();
		testWrite1();
		testWrite2();
		testRead();
		
		//Grade
		System.out.println("Memory Test passed.");
	}
	catch(Exception e)
	{
		//Grade
		System.out.println("Memory Test failed.\nA problem occured: " + e);
	}
		
	}
	
	// Wrtie  1101 to  the memory with address starting at index 10  
	public static void testWrite() throws Exception {
		Longword address = new Longword(10); // Create the address longword
		Longword value = new Longword(13); //Create the value longword
		memory mem = new memory(); 
		mem.write(address,value); //write the value into memory
	}
	
	// Wrtie  10 to  the memory with address starting at index 3  
	public static void testWrite1() throws Exception {
		Longword address = new Longword(3); // Create the address longword
		Longword value = new Longword(2); //Create the value longword
		memory mem = new memory(); 
		mem.write(address,value); //write the value into memory
	}
	
	
	// Wrtie  00011110 to  the memory with address starting at index 20 
	public static void testWrite2() throws Exception {
		Longword address = new Longword(20); // Create the address longword
		Longword value = new Longword(30); //Create the value longword
		memory mem = new memory(); 
		mem.write(address,value); //write the value into memory
	}
	
	
	public static void testRead() {
		
		Longword address = new Longword(10); // Create the address longword starting at index 10
		memory mem = new memory(); 
		Longword l_result = mem.read(address); //Read the result
		Bit[] result_array = l_result.bit_array;
		//print the result
		System.out.print("We get: ");
		for(int i =0;i<result_array.length;i++) {
			System.out.print(result_array[i].getBit());
		}
		System.out.print("  And it equals: ");
		System.out.println(l_result.getUnsigned());
		
		address = new Longword(20);// set the address longword starting at index 20
		l_result = mem.read(address); //Read the second result
		result_array = l_result.bit_array;
		//print the result
		System.out.print("We get: ");
		for(int i =0;i<result_array.length;i++) {
			System.out.print(result_array[i].getBit());
		}
		System.out.print("  And it equals: ");
		System.out.println(l_result.getUnsigned());
		
		address = new Longword(3); // set the address longword starting at index 3
		l_result = mem.read(address); //Read the third result
		result_array = l_result.bit_array;
		//print the result
		System.out.print("We get: ");
		for(int i =0;i<result_array.length;i++) {
			System.out.print(result_array[i].getBit());
		}
		System.out.print("  And it equals: ");
		System.out.println(l_result.getUnsigned());
		
		
		
	}
 
}
 