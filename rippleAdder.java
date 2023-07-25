package theBit;
 
/**
 * 
 * Conduct binary add and subtract operation
 * @author Kaiyi Li
 *
 */
public class rippleAdder {
     
	/**
	 * Conduct binary add of two longwords
	 * @param a
	 * @param b
	 * @return e
	 */
	public static Longword add(Longword a, Longword b) {
 
		int m = a.bit_array.length;  // Get the length of first adder
		int j = 0;// Count 
		// eliminate the 00s at the left side of the first adder and put the new adder in bits_a
		while(a.bit_array[j].getBit() == 0 && m>1) {
			m--;
			j++;
	    }
		Bit[] bits_a = new Bit[m];
		for(int i =m-1; i>=0;i--) {
			bits_a[i] = a.bit_array[i+j];
		}	
		int n = b.bit_array.length; // Get the length of second adder
		int q = 0; // Count 
		// eliminate the 00s at the left side of the second adder and put the new adder in bits_b
		while(b.bit_array[q].getBit() == 0 && n>1) {
			n--;
			q++;
	    }
		Bit[] bits_b = new Bit[n];
		for(int i =n-1; i>=0;i--) {
			bits_b[i] = b.bit_array[i+q];
		}
		 
		// Make the length of both adder the same by adding 00s on the left of the bits array of the shorter adder
		int l1 = bits_a.length;
		int l2 = bits_b.length;
		int l3 = l1>=l2?l1:l2;
		Bit[] bits_c = new Bit[l3];
		Bit[] bits_d = new Bit[l3];
		if(l1>=l2) {
			bits_c = bits_a;
        	for(int i = 0;i<l1-l2;i++) {
        		bits_d[i] = new Bit(0);
        		
        	}
        	for (int i=0; i<l2;i++) {
        		bits_d[i+l1-l2] = bits_b[i];
        	}
		}else {
			bits_d = bits_b;
			for(int i = 0;i<l2-l1;i++) {
        		bits_c[i] = new Bit(0);
        		
        	}
        	for (int i=0; i<l1;i++) {
        		bits_c[i+l2-l1] = bits_a[i];
        	}
		}
		
		int carry = 0;
		Bit[] bits_e = new Bit[l3+1];
		
		for(int i = l3-1;i>=0;i--) {
			Bit add =  bits_c[i].xor(bits_d[i]);
			if(bits_c[i].getBit()==1 && bits_d[i].getBit()==1 && carry ==0 ) {
				carry +=1; // if 1+1 and carry =0,then carry  should be 1
			}else if(bits_c[i].getBit()==1 && bits_d[i].getBit()==1 && carry ==1) {
				add = new Bit(1); // if 1+1 and carry =1, then  1+1+1 = 11 
			}else if(bits_c[i].getBit()==1 && bits_d[i].getBit()==0 && carry ==1) {
				add = new Bit(0); // if 1+0 and carry =1, then 1+1 = 10,
			}else if(bits_c[i].getBit()==0 && bits_d[i].getBit()==1 && carry ==1) {
				add = new Bit(0); // if 0+1  and carry = 1, then 0+1+1 = 10
			}else if(bits_c[i].getBit()==0 && bits_d[i].getBit()==0 && carry ==1) {
				carry =0; // if 0+0 and carry =1, then 0+0+1 = 01
				add = new Bit(1);
						
			}
			bits_e[i+1] = add;
		}
		bits_e[0]=new Bit(carry);
		return new Longword(bits_e);
	}
	
	   /**
	    * conduct binary subtract of two longwords
	    * @param a
	    * @param b
	    * @return e
	    */
	
	public static Longword subtract(Longword a, Longword b) {
         
		int m = a.bit_array.length;  // Get the length of minuend
		int j = 0;  // Count 
		
		// eliminate the 00s at the left side of the minuend and put the new minuend in bits_a
		while(a.bit_array[j].getBit() == 0 && m>1) {
			m--;
			j++;
	    }
		Bit[] bits_a = new Bit[m]; //store new minuend bits array
		for(int i =m-1; i>=0;i--) {
			bits_a[i] = a.bit_array[i+j];
		}	
		int n = b.bit_array.length; //Get the length of c
		int q = 0;
		
		// eliminate the 00s at the left side of the subtrahend and put the new subtrahend in bits_b
		while(b.bit_array[q].getBit() == 0 && n>1) {
			n--;
			q++;
	    }
		Bit[] bits_b = new Bit[n]; // store new subtrahend bits array
		for(int i =n-1; i>=0;i--) {
			bits_b[i] = b.bit_array[i+q];
		}
		 
		// Make the length of minuend and subtrahend array the same by adding 00s on the left of the bits array
		int l1 = bits_a.length;
		int l2 = bits_b.length;
		int l3 = l1>l2?l1:l2; // If the minuend is bigger than c, then get its length, otherwise l3=13
		Bit[] bits_c = new Bit[l3]; // Store the new subtrahend
		Bit[] bits_d = new Bit[l3]; // Store the new c
		
		if(l1>l2) {
			bits_c = new Bit[l3]; //  new subtrahend's length equals original minuend's length
			bits_d = bits_a;  // minuend  doesn't change 
        	for(int i = 0;i<l1-l2;i++) {
        		bits_c[i] = new Bit(0);  //  complete the left side of new subtrahend with 00s
        		
        	}
        	for (int i=0; i<l2;i++) {
        		bits_c[i+l1-l2] = bits_b[i]; // the right side of the new subtrahend equal to the original subtrahend
        	}
		}else {
		//  new subtrahend and new minuend's length equals 16. complete both's left side with 00s
			for (int i =0; i<l2;i++) {
				bits_c[i] = new Bit(0); 
				bits_d[i] = new Bit(0); 
			}
			for (int i = 0; i<l2;i++) {
				bits_c[16-i-1] = bits_b[l2-i-1]; // the right side of the new subtrahend equal to the original subtrahen
			}
			
			for(int i =0; i<l1; i++) {
				bits_d[16-i-1] = bits_a[l1-i-1]; // the right side of the new minuend equal to the original minuend
			}
        	
		}
		
		int borrow = 0;
		Bit[] bits_e = new Bit[l3];
		
		for(int i = l3-1;i>=0;i--) {
			Bit substrct =  bits_c[i].xor(bits_d[i]);
			if(bits_d[i].getBit()==0 && bits_c[i].getBit()==1 && borrow ==0 ) {
				borrow +=1; //if 0-1, need to borrow 1 from higher end
			}else if(bits_d[i].getBit()==1 && bits_c[i].getBit()==1 && borrow ==1) {
				substrct = new Bit(1); //if borrow =1 & 1-1, then the minuend should be 0, use  0-1 logic
			}else if(bits_d[i].getBit()==0 && bits_c[i].getBit()==1 && borrow ==1) {
				substrct = new Bit(0);  //if borrow =1 and minuend is 0, then  borrow 1 from higher end,the minuend should be 1, use  1-1 logic
			}else if(bits_d[i].getBit()==0 && bits_c[i].getBit()==0 && borrow ==1) {
				substrct = new Bit(1); //if borrow =1 and minuend is 0,  then borrow 1 from higher end, the minuend should be 1, use  1-0 logic
			}else if(bits_d[i].getBit()==1 && bits_c[i].getBit()==0 && borrow ==1) {
				borrow =0; //if borrow =1 and minuend is 1, , then the minuend should be 0, use  0-0 logic
				substrct = new Bit(0);
						
			}
			bits_e[i] = substrct;
		}
		return new Longword(bits_e);
	}
	
	public static void main(String[] args) {
		rippleAdder ra = new rippleAdder();
		@SuppressWarnings("static-access")
		Longword test = ra.subtract(new Longword(0),new Longword(2));
	    Bit[] result_array =  test.bit_array;
	    for (int i = 0;i<result_array.length;i++) {
	    	System.out.print(result_array[i].getBit());
	    }
	    System.out.println();
	}
 
}