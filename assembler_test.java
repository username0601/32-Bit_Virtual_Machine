//package theBit;
//
//public class assembler_test {
//	public static void main(String[] args)
//	{
//		runTests(); 
//	}
//	
//	public static void runTests() 
//	{	
//		try
//		{
//			testassemble1();
//			testlexical1();
//			testparser1();
//			testassemble2();
//			testlexical2();
//			testparser2();
//			testassemble3();
//			testlexical3();
//			testparser3();
//			System.out.println("Assembler Test passed.");
//		}
//		catch(Exception e)
//		{
//			//Grade
//			System.out.println("Assembler Test failed.\nA problem occured: " + e);
//		}
//				
//	}
//	/**
//	 * Test assemble with 3 tokens
//	 * @throws Exception
//	 */
//	public static void testassemble1() throws Exception {
//		
//		String[] outputs = Assembler.assemble("move R2 -1");
//		System.out.println("Assemble test with instruction: move R2 -1");
//		for(int i =0; i<outputs.length;i++) {
//			System.out.print(outputs[i]);
//			
//		}
//		System.out.println();
//		
//	}
//	
//	/**
//	 * Test lexical with 3 tokens
//	 * @throws Exception
//	 */
//	public static void testlexical1() throws Exception {
//		
//		String[] outputs = Assembler.lexical("move R2 -1");
//		System.out.println("Lexical test with instruction: move R2 -1");
//		for(int i =0; i<outputs.length;i++) {
//			System.out.print(outputs[i]);
//			
//		}
//		System.out.println();
//		
//	}
//	
//	/**
//	 * Test parser with 3 tokens
//	 * @throws Exception
//	 */
//	public static void testparser1() throws Exception {
//		
//		String[] outputs = Assembler.parser("move R2 -1");
//		System.out.println("Parser test with instruction: move R2 -1");
//		for(int i =0; i<outputs.length;i++) {
//			System.out.print(outputs[i]);
//			
//		}
//		System.out.println();
//		
//	}
//	
//	/**
//	 * Test assemble with 4 tokens
//	 * @throws Exception
//	 */
//	public static void testassemble2() throws Exception {
//		
//		String[] outputs = Assembler.assemble("add R1 1 2");
//		System.out.println("Assemble test with instruction: Add R1 1 2");
//		
//		for(int i =0; i<outputs.length;i++) {
//			System.out.print(outputs[i]);
//			
//		}
//		System.out.println();
//		
//	}
//	
//	/**
//	 * Test lexical with 4 tokens
//	 * @throws Exception
//	 */
//	public static void testlexical2() throws Exception {
//		
//		String[] outputs = Assembler.lexical("add R1 1 2");
//		System.out.println("Lexical test with instruction: Add R1 1 2");
//		for(int i =0; i<outputs.length;i++) {
//			System.out.print(outputs[i]);
//			
//		}
//		System.out.println();
//		
//	}
//	
//	/**
//	 * Test parser with 4 tokens
//	 * @throws Exception
//	 */
//	public static void testparser2() throws Exception {
//		
//		String[] outputs = Assembler.parser("add R1 1 2");
//		System.out.println("Parser test with instruction: Add R1 1 2");
//		for(int i =0; i<outputs.length;i++) {
//			System.out.print(outputs[i]);
//			
//		}
//		System.out.println();
//		
//	}
//	
//	/**
//	 * Test assemble with 4 tokens
//	 * @throws Exception
//	 */
//	public static void testassemble3() throws Exception {
//		
//		String[] outputs = Assembler.assemble("Subtract R10 1 2");
//		System.out.println("Assemble test with instruction: Subtract R10 1 2");
//		
//		for(int i =0; i<outputs.length;i++) {
//			System.out.print(outputs[i]);
//			
//		}
//		System.out.println();
//		
//	}
//	
//	/**
//	 * Test lexical with 4 tokens
//	 * @throws Exception
//	 */
//	public static void testlexical3() throws Exception {
//		
//		String[] outputs = Assembler.lexical("Subtract R10 1 2");
//		System.out.println("Lexical test with instruction: Subtract R10 1 2");
//		for(int i =0; i<outputs.length;i++) {
//			System.out.print(outputs[i]);
//			
//		}
//		System.out.println();
//		
//	}
//	
//	/**
//	 * Test parser with 4 tokens
//	 * @throws Exception
//	 */
//	public static void testparser3() throws Exception {
//		
//		String[] outputs = Assembler.parser("Subtract R10 1 2");
//		System.out.println("Parser test with instruction: Subtract R10 1 2");
//		for(int i =0; i<outputs.length;i++) {
//			System.out.print(outputs[i]);
//			
//		}
//		System.out.println();
//		
//	}
//	
//
//}
