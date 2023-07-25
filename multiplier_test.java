package theBit;

public class multiplier_test {
	
	public static void main(String[] args)
	{
		runTests(); 
	}
	
	public static void runTests() 
	{	try
	{
		testMultiple();
		
		//Grade
		System.out.println("Multiplier Test passed.");
	}
	catch(Exception e)
	{
		//Grade
		System.out.println("Multiplier Test failed.\nA problem occured: " + e);
	}
		
	}
	// Test 1*3 (1*11)
	public static void testMultiple() throws Exception {
		
		Longword a = new Longword(2);
		Longword b = new Longword(3);
		multiplier m = new multiplier();
		@SuppressWarnings("static-access")
		Longword c = m.multiply(a,b);
		Bit[] bt = c.bit_array;
		for(int i = 0;i<bt.length;i++) {
			System.out.print(bt[i].getBit());
		}
		System.out.println();
	}

}