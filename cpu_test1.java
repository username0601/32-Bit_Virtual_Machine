//package theBit;
//
//public class cpu_test1 {
//	
//	public static void main(String[] args)
//	{
//		runTests(); 
//	}
//	
//	public static void runTests() 
//	{	try
//	{
//		testPreLoad();
//		testHalt();
//		testInterrupt1();
//		testMove1();
//		testMove2();
//		testALUOP();
//		testInterrupt0();
//		
//		
//		//Grade
//		System.out.println();
//		System.out.println("*******CPU Test passed********");
//	}
//	catch(Exception e)
//	{
//		//Grade
//		System.out.println("CPU Test failed.\nA problem occured: " + e);
//	}
//		
//	}
//	
//	public static void testPreLoad() throws Exception {
//		System.out.println("Step 1: Test preload: ");
//		String [] string_array= {"0000101001010000"};
//		computer c = new computer();
//		c.preload(string_array);
//		memory m = c.mem;
//		Bit[] storage = m.storage;
//		System.out.println("The content in the memory is: ");
//		for(int i=0; i<storage.length;i++) {
//			if(storage[i]!=null) {
//				System.out.print(storage[i].getBit());
//			}
//			
//		}
//		System.out.println();
//		
//	}
//	
//	public static void testHalt() throws Exception {
//		System.out.println();
//		System.out.println("Step 2: Test Halt:");
//		String [] string_array= {"0000000000000000"};
//		computer c = new computer();
//		c.preload(string_array);
//		c.run();
//		
//	}
//	
//	public static void testInterrupt1() throws Exception {
//		System.out.println();
//		System.out.println("Step 3: Test interrupt 1:");
//		String [] string_array= {"0010000000000001"};
//		computer c = new computer();
//		c.preload(string_array);
//		c.run();
//		
//	}
//	
//	public static void testMove1() throws Exception {
//		System.out.println();
//		System.out.println("Step 4: Test Move1:");
//		String [] string_array= {"0001001000001010"};
//		computer c = new computer();
//		c.preload(string_array);
//		c.run();
//		
//	}
//	
//	public static void testMove2() throws Exception {
//		System.out.println();
//		System.out.println("Step 5: Test Move2:");
//		String [] string_array= {"0001000100001010"};
//		computer c = new computer();
//		c.preload(string_array);
//		c.run();
//		
//	}
//	
//	public static void testALUOP() throws Exception {
//		System.out.println();
//		System.out.println("Step 6: Test ALUOP:");
//		String [] string_array= {"0001001000001010","0001000100001010","1110000100100011"};
//		computer c = new computer();
//		c.preload(string_array);
//		c.run();
//	}
//	
//	public static void testInterrupt0() throws Exception {
//		System.out.println();
//		System.out.println("Step 7: Test Interrupt0:");
//		String [] string_array= {"0001001000001010","0001000100001010","1110000100100011","0010000000000000"};
//		computer c = new computer();
//		c.preload(string_array);
//		c.run();
//		
//	}
//	
//	
//
//}
