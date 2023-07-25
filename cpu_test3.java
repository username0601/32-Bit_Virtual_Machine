package theBit;

public class cpu_test3 {
	public static void main(String[] args)
	{
		runTests(); 
	}
	
	public static void runTests() 
	{	try
	{  
		testPush();
		testPop();
		testCall();
		testReturn();

		
		//Grade
		System.out.println();
		System.out.println("*******CPU3 Test passed********");
	}
	catch(Exception e)
	{
		//Grade
		System.out.println("CPU3 Test failed.\nA problem occured: " + e);
	}
		
	}
	
    /**
     * Test Stack Push
     * @throws Exception
     */
	public static void testPush() throws Exception {
		System.out.println();
		System.out.println("-------Test Push-------");
		String[] test1 = new String[] {"move R3 5","Push R3"}; // Push value to stack from R3
		String[] instruction = new String[2];
		for(int i=0; i<test1.length;i++) {
			String[] assembledCode = Assembler.assemble(test1[i]);
			instruction[i] = String.join("",assembledCode); // Get bit instructions from assembler
			System.out.println(instruction[i]);
		}
		computer cpu = new computer();
		cpu.preload(instruction); // Load instructions into memory
		cpu.run(); // Run instructions

	}
	
    /**
     * Test Stack Pop
     * @throws Exception
     */
	public static void testPop() throws Exception {
		System.out.println();
		System.out.println("-------Test Pop-------");
		String[] test1 = new String[] {"move R4 10","Push R4","Pop R4"}; // Push value to stack from R3
		String[] instruction = new String[3];
		for(int i=0; i<test1.length;i++) {
			String[] assembledCode = Assembler.assemble(test1[i]);
			instruction[i] = String.join("",assembledCode); // Get bit instructions from assembler
			System.out.println(instruction[i]);
		}
		computer cpu = new computer();
		cpu.preload(instruction); // Load instructions into memory
		cpu.run(); // Run instructions

	}
	
	 /**
     * Test Stack Call
     * @throws Exception
     */
	public static void testCall() throws Exception {
		System.out.println();
		System.out.println("-------Test Call------");
		String[] test1 = new String[] {"move R5 5","call 4","Halt","interrupt 0"}; 
		String[] instruction = new String[4];
		for(int i=0; i<test1.length;i++) {
			String[] assembledCode = Assembler.assemble(test1[i]);
			instruction[i] = String.join("",assembledCode); // Get bit instructions from assembler
			System.out.println(instruction[i]);
		}
		computer cpu = new computer();
		cpu.preload(instruction); // Load instructions into memory
		cpu.run(); // Run instructions

	}
	
    /**
     * Test Stack Return
     * @throws Exception
     */
	public static void testReturn() throws Exception {
		System.out.println();
		System.out.println("-------Test Return-------");
		String[] test1 = new String[] {"Return","Halt"}; // implement call first, then return instruction
		String[] instruction = new String[2];
		for(int i=0; i<test1.length;i++) {
			String[] assembledCode = Assembler.assemble(test1[i]);
			instruction[i] = String.join("",assembledCode); // Get bit instructions from assembler
			System.out.println(instruction[i]);
		}
		computer cpu = new computer();
		cpu.preload(instruction); // Load instructions into memory
		cpu.run(); // Run instructions

	}

}
