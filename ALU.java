package theBit;
 
public class ALU {
 
	public static multiplier m; 
	public static ILongword l;
	public static rippleAdder r;
	public static Longword result;
	
	// Package of all bit operations 
	public static Longword doOp(Bit[] operation, Longword a, Longword b) throws Exception {
		m = new multiplier();
		l = new Longword(a.bit_array);
		r = new rippleAdder();
		
		if(operation[0].getBit() == 0) {
			return m.multiply(a,b);
		}else if(operation[0].getBit() == 1) {
			if (operation[1].getBit() == 0) {
				if(operation[2].getBit() == 0 ) {
					//1000- and
					if(operation[3].getBit() == 0 ) {
						return l.and(b);
					//1001- or
					}else if(operation[3].getBit() == 1) {
						return l.or(b);
					}
					
				}else if(operation[2].getBit() == 1) {
					//1010- xor
					if(operation[3].getBit() == 0 ) {
						return l.xor(b);
					//1011- not
					}else if(operation[3].getBit() == 1) {
						return l.not();
					}
				}
			}else if(operation[1].getBit() == 1) {
				if(operation[2].getBit() == 0 ) {
					int m = b.bit_array.length;
			        Bit[] bits_b = b.bit_array;
			        Bit[] temp = new Bit[5];
			        int amount = 0;
			        //ignore all but the lowest 5 bits
			        if (m>5) {
			        	for (int i =0; i<5; i++) {
			        		temp[0] = bits_b[m-5-i-1];
			        		
			        	}
			        	for (int i =0; i<5;i++){
			        		// Calculate the 10 base amount from 2 base bits
			   		       amount = Integer.sum(amount,(int)(temp[i].getBit()*Math.pow(2,5-i-1)));
			   		  	} 	
			        	
			        }else {
			  		    
					  for (int i =0; i<m-1;i++){
					       amount = Integer.sum(amount,(int) (bits_b[i].getBit()*Math.pow(2,m-i-1)));
					  }
			        }
			        
			        
					//1100- left shift
					if(operation[3].getBit() == 0 ) {
						return l.leftShift(amount);
					//1101- right shift
					}else if(operation[3].getBit() == 1) {
						return l.rightShift(amount);
					}
					
				}else if(operation[2].getBit() == 1) {
					//1110- add
					if(operation[3].getBit() == 0 ) {
						return r.add(a,b);
					//1111- subtract
					}else if(operation[3].getBit() == 1) {
						return r.subtract(a,b);
					}
				}
			
				
			}
		}else {
			// Other operations
			throw new Exception("Invalid option");
		}
		
		return result;
		
	}
	
	public static void main(String[] args) throws Exception {
 
		Longword a = new Longword(2);
		Longword b = new Longword(3);
		ALU alu = new ALU();
		Bit[] op = new Bit[4];
		op[0] = new Bit(1);
		op[1] = new Bit(1);
		op[2] = new Bit(0);
		op[3] = new Bit(0);	
		Longword c = alu.doOp(op,a,b);
		Bit[] bt = c.bit_array;
		for(int i = 0;i<bt.length;i++) {
			System.out.print(bt[i].getBit());
		}
		System.out.println();
	
	}
	
	
	
}
 