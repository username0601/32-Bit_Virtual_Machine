package theBit;
 
public class Longword_Test extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static void main(String[] args)
	{
		runTests(); 
	}
	
	public static void runTests() 
	{	
		
		
		try
		{
			testGetBit();
			testSetBit();
			testAnd();
			testOr();
			testNot();
   	    	testXor();
	 		testRightShift();
			testLeftShift();
			testCopy();
			
			//Grade
			System.out.println("Longword Test passed.");
		}
		catch(Exception e)
		{
			//Grade
			System.out.println("Longword Test failed.\nA problem occured: " + e);
		}
				
	}
	
	    //Test getBit(int value) method
		public static void testGetBit() throws Exception{
		    Longword lw = new Longword(11);
		    lw.getBit(3);
			
		}
		
	   //Test setBit(int value) method
		public static void testSetBit(){
			
			Longword lw = new Longword(11);
		    lw.setBit(3,new Bit(0));
		}
		//Test and(Longword value) method
		public static void testAnd() throws Exception {
			Longword lw = new Longword(2);
			lw.and(new Longword(3));
		}
		
		//Test or(Longword value) method
		public static void testOr() throws Exception {
			Longword lw = new Longword(2);
			lw.or(new Longword(3));
		}
		//Test not() method
		public static void testNot() throws Exception {
			Longword lw = new Longword(2);
			lw.not();
		}
		//Test xor(Longword value) method
		public static void testXor() throws Exception {
			Longword lw = new Longword(2);
			lw.xor(new Longword(3));
		}
		//Test rightShift(int value) method
		public static void testRightShift() throws Exception {
			Longword lw = new Longword(8);
			lw.rightShift(2);
		}
		//Test leftShift(int value) method
		public static void testLeftShift() throws Exception{
			Longword lw = new Longword(8);
			lw.leftShift(2);
		}
		//Test getUnsigned() method
		public static void testUnsigned() throws Exception {
			Longword lw = new Longword(8);
			lw.getUnsigned();
		}
		//Test getsigned() method
		public static void testSigned() throws Exception {
			Longword lw = new Longword(8);
			lw.getSigned();
		}
		//Test copy(Longword value) method
		public static void testCopy() {
			Longword lw = new Longword(8);
			lw.copy(new Longword(2));
		}
		
 
}