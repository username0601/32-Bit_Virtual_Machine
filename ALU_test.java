package theBit;
 
public class ALU_test {
	
	
 
	public static void main(String[] args)
	{
		runTests(); 
	}
	
	public static void runTests() 
	{	try
	{
		testdoOp();
		
		//Grade
		System.out.println("ALU Test passed.");
	}
	catch(Exception e)
	{
		//Grade
		System.out.println("ALU Test failed.\nA problem occured: " + e);
	}
		
	}
	public static void testdoOp() throws Exception {
		Longword a = new Longword(8);
		Longword b = new Longword(3);
		ALU alu = new ALU();
		Bit[] op = new Bit[4];
		op[0] = new Bit(1);
		op[1] = new Bit(1);
		op[2] = new Bit(1);
		op[3] = new Bit(0);	
		Longword c = alu.doOp(op,a,b);
		Bit[] bt = c.bit_array;
		for(int i = 0;i<bt.length;i++) {
			System.out.print(bt[i].getBit());
		}
		System.out.println();
	}
 
}