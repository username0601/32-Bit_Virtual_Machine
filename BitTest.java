package theBit;
 
@SuppressWarnings("serial")
public class BitTest extends Exception{
	public static void main(String[] args)
	{
		runTests(); 
	}
	
	public static void runTests()
	{	
		try
		{
			testSet1();
			testToggle();
			testSet2();
			testClear();
			testGetValue();
			testGetBit();
			testAnd();
			testOr();
		    testXor();
			testNot();
			testToString();
			//Grade
			System.out.println("Bit Test passed.");
		}
		catch(Exception e)
		{
			//Grade
			System.out.println("Bit Test failed.\nA problem occured: " + e);
		}
				
	}
	
	//Test set(boolean value) method
	public static void testSet1() throws TestException{
		Bit x = new Bit(0);
		x.set(true);
		if (x.getValue() != true) throw new TestException("SET true failed");	
		Bit y = new Bit(1);	
		y.set(false);
		if (y.getValue() != false) throw new TestException("SET false failed");	
		
	}
	
	//Test toggle() method
	public static void testToggle() throws TestException{
		Bit x = new Bit(0);
		x.toggle();
		if (x.getValue() != true) throw new TestException("0 TOGGLE 1 failed");	
		Bit y = new Bit(1);	
		y.toggle();
		if (y.getValue() != false) throw new TestException("1 TOGGLE 0 failed");	
	}
	
	//Test set() method
	public static void testSet2() throws TestException{
		Bit x = new Bit(0);
		x.set();
		if (x.getValue() != true) throw new TestException("0 SET 1 failed");	
		Bit y = new Bit(1);	
		y.set();
		if (y.getValue() != true) throw new TestException("1 SET 1 failed");	
	}
	
	//Test clear() method
	public static void testClear() throws TestException{
		Bit x = new Bit(1);
		x.clear();
		if (x.getValue() != false) throw new TestException("1 CLEAR 0 failed");	
		Bit y = new Bit(0);	
		y.clear();
		if (y.getValue() != false) throw new TestException("0 CLEAR 0 failed");	
	}
	
	//Test getValue() method
	public static void testGetValue() throws TestException{
		if(new Bit(0).getValue() != false) throw new TestException("0 GETVALUE failed");
		if(new Bit(1).getValue() != true) throw new TestException("1 GETVALUE failed");
	}
	
	//Test getBit() method
	public static void testGetBit() throws TestException{
		if(new Bit(0).getBit() != 0) throw new TestException("0 GETBIT failed");
		if(new Bit(1).getBit() != 1) throw new TestException("1 GETBIT failed");
	}
	
	//Test and() method
	public static void testAnd() throws TestException{
		if (new Bit(0).and(new Bit(0)).getBit() != 0) throw new TestException("0 AND 0 failed");		
		if (new Bit(0).and(new Bit(1)).getBit() != 0) throw new TestException("0 AND 1 failed");
		if (new Bit(1).and(new Bit(0)).getBit() != 0) throw new TestException("1 AND 0 failed");
		if (new Bit(1).and(new Bit(1)).getBit() != 1) throw new TestException("1 AND 1 failed");
	}
	
	//Test or() method
	public static void testOr() throws TestException{
		if (new Bit(0).or(new Bit(0)).getBit() != 0) throw new TestException("0 OR 0 failed");
		if (new Bit(0).or(new Bit(1)).getBit() != 1) throw new TestException("0 OR 1 failed");
		if (new Bit(1).or(new Bit(0)).getBit() != 1) throw new TestException("1 OR 0 failed");
		if (new Bit(1).or(new Bit(1)).getBit() != 1) throw new TestException("1 OR 1 failed");
	}
	
	//Test xor() method
	public static void testXor() throws TestException{
		if (new Bit(0).xor(new Bit(0)).getBit() != 0) throw new TestException("0 XOR 0 failed");
		if (new Bit(0).xor(new Bit(1)).getBit() != 1) throw new TestException("0 XOR 1 failed");
		if (new Bit(1).xor(new Bit(0)).getBit() != 1) throw new TestException("1 XOR 0 failed");
		if (new Bit(1).xor(new Bit(1)).getBit() != 0) throw new TestException("1 XOR 1 failed");
	}
 
	//Test not() method
	public static void testNot() throws TestException{
		if (new Bit(0).not().getBit() != 1) throw new TestException("0 NOT failed");
		if (new Bit(1).not().getBit() != 0) throw new TestException("1 NOT failed");
	}
	
	//Test toString() method
	public static void testToString() throws TestException{
		if (new Bit(0).toString() != "f" ) throw new TestException("0 TOSTRING f failed");
		if (new Bit(1).toString() != "t" ) throw new TestException("1 TOSTRING t failed");
	}
	
	
}