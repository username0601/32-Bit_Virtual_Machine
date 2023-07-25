package theBit;
 
public class rippleAdder_Test {
	
	public static void main(String[] args)
	{
		runTests(); 
	}
	
	public static void runTests() 
	{	try
	{
		testAdd();
		testSubtract();
		
		//Grade
		System.out.println("Longword Test passed.");
	}
	catch(Exception e)
	{
		//Grade
		System.out.println("Longword Test failed.\nA problem occured: " + e);
	}
		
	}
	public static void testAdd() {
		rippleAdder ra = new rippleAdder();
		@SuppressWarnings("static-access")
		Longword test = ra.add(new Longword(2),new Longword(3));
	    Bit[] result_array =  test.bit_array;
	    for (int i = 0;i<result_array.length;i++) {
	    	System.out.print(result_array[i].getBit());
	    }
	    System.out.println();
	}
	
	public static void testSubtract() {
		rippleAdder ra = new rippleAdder();
		@SuppressWarnings("static-access")
		Longword test = ra.subtract(new Longword(7),new Longword(3));
	    Bit[] result_array =  test.bit_array;
	    for (int i = 0;i<result_array.length;i++) {
	    	System.out.print(result_array[i].getBit());
	    }
	    System.out.println();
	}
}