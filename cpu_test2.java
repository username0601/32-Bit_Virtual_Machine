package theBit;

import java.util.Arrays;

public class cpu_test2 {

	
	public static void main(String[] args)
	{
		runTests(); 
	}
	
	public static void runTests() 
	{	try
	{
		testJump();
		testCompare();
		testBranchIfEqual();
		testBranchIfNotEqual();
		testBranchIfGreater();
		testBranchIfGreaterOrEqual();
		
		//Grade
		System.out.println();
		System.out.println("*******CPU2 Test passed********");
	}
	catch(Exception e)
	{
		//Grade
		System.out.println("CPU2 Test failed.\nA problem occured: " + e);
	}
		
	}
    /**
     * Test unconditional jump
     * @throws Exception
     */
	public static void testJump() throws Exception {
		System.out.println();
		System.out.println("Test Jump");
		String[] test1 = new String[] {"jump 4","move R1 5","interrupt 0","halt"};
		String[] instruction = new String[4];
		for(int i=0; i<test1.length;i++) {
			String[] assembledCode = Assembler.assemble(test1[i]);
			instruction[i] = String.join("",assembledCode);
			System.out.println(instruction[i]);
		}
		computer cpu = new computer();
		cpu.preload(instruction);
		cpu.run();

	}
	
    /**
     * Test compare two registers' value, jump to the one with bigger value
     * @throws Exception
     */
	public static void testCompare() throws Exception {
		System.out.println();
		System.out.println("Test Compare");
		String[] test1 = new String[] {"Move R2 1","Move R4 4","Compare R2 R4","halt"};
		String[] instruction = new String[4];
		for(int i=0; i<test1.length;i++) {
			String[] assembledCode = Assembler.assemble(test1[i]);
			instruction[i] = String.join("",assembledCode);
			System.out.println(instruction[i]);
		}
		computer cpu = new computer();
		cpu.preload(instruction);
		cpu.run();
		
		
	}
	
    /**
     * Test Branch if its equal to
     * @throws Exception
     */
	public static void testBranchIfEqual() throws Exception {
		System.out.println();
		System.out.println("Test Equal");
		String[] test1 = new String[] {"Move R4 2","BrunchIfEqual 3","halt"};
		String[] instruction = new String[3];
		for(int i=0; i<test1.length;i++) {
			String[] assembledCode = Assembler.assemble(test1[i]);
			instruction[i] = String.join("",assembledCode);
			System.out.println(instruction[i]);
		}
		computer cpu = new computer();
		cpu.preload(instruction);
		cpu.run();
		
		
	}
	

	public static void testBranchIfNotEqual() throws Exception {
		System.out.println();
		System.out.println("Test Not Equal");
		String[] test1 = new String[] {"Move R2 1","BrunchIfNotEqual 5","halt"};
		String[] instruction = new String[3];
		for(int i=0; i<test1.length;i++) {
			String[] assembledCode = Assembler.assemble(test1[i]);
			instruction[i] = String.join("",assembledCode);
			System.out.println(instruction[i]);
		}
		computer cpu = new computer();
		cpu.preload(instruction);
		cpu.run();
	}
	


	public static void testBranchIfGreater() throws Exception {
		System.out.println();
		System.out.println("Test If Greater");
		String[] test1 = new String[] {"Move R2 1","BrunchIfGreater 1","halt"};
		String[] instruction = new String[3];
		for(int i=0; i<test1.length;i++) {
			String[] assembledCode = Assembler.assemble(test1[i]);
			instruction[i] = String.join("",assembledCode);
			System.out.println(instruction[i]);
		}
		computer cpu = new computer();
		cpu.preload(instruction);
		cpu.run();
	}
	
	public static void testBranchIfGreaterOrEqual() throws Exception {
		System.out.println();
		System.out.println("Test If Greater Or Equal");
		String[] test1 = new String[] {"Move R4 5","BrunchIfGreaterOrEqual 4","interrupt 0"};
		String[] instruction = new String[3];
		for(int i=0; i<test1.length;i++) {
			String[] assembledCode = Assembler.assemble(test1[i]);
			instruction[i] = String.join("",assembledCode);
			System.out.println(instruction[i]);
		}
		computer cpu = new computer();
		cpu.preload(instruction);
		cpu.run();
	}
	
		


	



}
