package theBit;
/**
 * This class conduct binary multiplication
 * @author Kaiyi Li
 *
 */
public class multiplier {
 
	public static Longword multiply (Longword a, Longword b) throws Exception {
		int l2 = b.bit_array.length; // Get the length of the factor 
		Bit[] bits_b = b.bit_array;  // Get the Bits array of the factors
		rippleAdder ra = new rippleAdder();
		Longword result = new Longword(0); // Store multiplication result
		
		//Left shift the multiplier by i amount, and sum the result if the Bit of the factor(i) is 1
		for (int i = l2-1;i>=0; i--) {
			if (bits_b[i].getBit() == 1) {
				result =ra.add(result, a.leftShift(l2-i-1));
			}
		}
        int l3 = result.bit_array.length; // Get the length of the result bits array
        Bit[] result_array = result.bit_array; // Result array
        //if the length of the result array is bigger than 32 bits, We keep only the lower 32-bits.
        if (l3>32){
        	 Bit[] bits_c = new Bit[32];
        	for (int i = 0; i<32;i++) {
        			 bits_c[32-i-1] = result_array[l3-i-1];
        	}
        	// Keep the sign of the result bits while trimminig it to 32 bits
        	if((a.bit_array[0].getBit()==0 && b.bit_array[0].getBit()==1)||(a.bit_array[0].getBit()==1 && b.bit_array[0].getBit()==0)) {
            		 bits_c[0] = new Bit(1);
            }
        	 return new Longword(bits_c);
        }
		return result;
		
	}
}