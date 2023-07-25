package theBit;
 
/**
 * 
 * @author Kaiyi Li
 *
 */
public class Longword implements ILongword {
	
	Bit[] bit_array;
	/**
	 * Constructor
	 * @param bit_set
	 */
	Longword(Bit[] bit_set){
		this.bit_array = new Bit[bit_set.length];
		this.bit_array = bit_set;
		
	}
	/**
	 * Constructor
	 * @param value
	 */
	Longword(int value){
		this.set(value);
		
	}
	 /**
	    * get value of i's bit in the longword
	 * @throws Exception 
	    */
	public Bit getBit(int i) throws Exception {	
		 return this.bit_array[i-1];
	}
	 /**
	    * set value to i's bit
	    */
	public void setBit(int i, Bit value) {
		this.bit_array[i-1] = value;
		
	}
	 /**
	    * Perform and operator on our longword with other longword
	 * @throws Exception 
	    */
	public Longword and(Longword other) throws Exception {
		
	    int l1 = this.bit_array.length;
        int l2 = other.bit_array.length;
        int l3 = l1>l2?l1:l2;
        Bit[] temp = new Bit[l3];
        if(l1 >=l2) {
        	temp = new Bit[l1];
        	for(int i = 0;i<l1-l2;i++) {
        		temp[i] = new Bit(0);
        		
        	}
        	for (int i=0; i<l2;i++) {
        		temp[i+l1-l2] = other.bit_array[i];
        	}
        }else if(l2>l1) {
        	temp = new Bit[l2];
        	for(int i = 0;i<l2-l1;i++) {
        		temp[i] = new Bit(0);
        		
        	}
        	for (int i=0; i<l1;i++) {
        		temp[i+l2-l1] = other.bit_array[i];
        	}
        }
 
		Bit[] bit = new Bit[l3];
		for(int n = 0; n < bit.length; n++)
		{  
			bit[n] = this.getBit(n+1).and(temp[n]);
			
		}
		return new Longword(bit);
	}
	 
 
	/**
	    * Perform or operator on our longword with other longword
	 * @throws Exception 
	    */
	public Longword or(Longword other) throws Exception {
 
		
	    int l1 = this.bit_array.length;
        int l2 = other.bit_array.length;
        int l3 = l1>l2?l1:l2;
        Bit[] temp = new Bit[l3];
        if(l1 >=l2) {
        	temp = new Bit[l1];
        	for(int i = 0;i<l1-l2;i++) {
        		temp[i] = new Bit(0);
        		
        	}
        	for (int i=0; i<l2;i++) {
        		temp[i+l1-l2] = other.bit_array[i];
        	}
        }else if(l2>l1) {
        	temp = new Bit[l2];
        	for(int i = 0;i<l2-l1;i++) {
        		temp[i] = new Bit(0);
        		
        	}
        	for (int i=0; i<l1;i++) {
        		temp[i+l2-l1] = other.bit_array[i];
        	}
        }
 
		Bit[] bit = new Bit[l3];
		for(int n = 0; n < bit.length; n++)
		{  
			bit[n] = this.getBit(n+1).or(temp[n]);
			
		}
		return new Longword(bit);
	
		
	}
	 /**
	    * Perform xor operator on our longword with other longword
	 * @throws Exception 
	    */
	public Longword xor(Longword other) throws Exception {
 
		
	    int l1 = this.bit_array.length;
        int l2 = other.bit_array.length;
        int l3 = l1>l2?l1:l2;
        Bit[] temp = new Bit[l3];
        if(l1 >=l2) {
        	temp = new Bit[l1];
        	for(int i = 0;i<l1-l2;i++) {
        		temp[i] = new Bit(0);
        		
        	}
        	for (int i=0; i<l2;i++) {
        		temp[i+l1-l2] = other.bit_array[i];
        	}
        }else if(l2>l1) {
        	temp = new Bit[l2];
        	for(int i = 0;i<l2-l1;i++) {
        		temp[i] = new Bit(0);
        		
        	}
        	for (int i=0; i<l1;i++) {
        		temp[i+l2-l1] = other.bit_array[i];
        	}
        }
 
		Bit[] bit = new Bit[l3];
		for(int n = 0; n < bit.length; n++)
		{  
			bit[n] = this.getBit(n+1).xor(temp[n]);
			
		}
		return new Longword(bit);
	
		
	}
   /**
    * Perform not operator on our longword
    */
	public Longword not() {
		Bit[] bit = new Bit[this.bit_array.length];
		for(int n = 1; n < bit.length; n++)
		{   
			bit[n] = new Bit(this.bit_array[n].not().getBit());
			
		}
		return new Longword(bit);
	}
   /**
    * Right shift our long word by amount bits
    */
	public Longword rightShift(int amount) throws Exception {
		
		int l = this.bit_array.length;
		Bit[] rs = new Bit[l];
		if(l<amount)throw new Exception("Right shift amount is too big");
		Bit[] temp = new Bit[l-amount];
		for(int i =0;i<l-amount;i++) {
			temp[i] = this.bit_array[i];
		}
		for (int i = 0; i<amount;i++) {
			rs[i] = new Bit(0);
		}
		for (int i = amount;i<l;i++) {
			rs[i] = temp[i-amount];
		}
		
		return new Longword(rs);
	}
    /**
     * Left shift our longword by amount bits
     */
	public Longword leftShift(int amount) throws Exception {
		int l = this.bit_array.length;
		Bit[] temp = new Bit[l+amount];
		for (int i = 0; i<l; i++) {
			temp[i] = this.bit_array[i];
		}
		for (int i = l; i<l+amount;i++) {
			temp[i] = new Bit(0);
		}
		int j = l+amount;
		int m = 0;
		while(temp[m].getBit() == 0) {
				j--;
				m++;
		}
		if(j>64)throw new Exception("Bits size out of boundary");
		Bit[] ls = new Bit[j];
		for(int i =j-1; i>=0;i--) {
			ls[i] = temp[i+m];
		}
 
		return new Longword(ls);
	}
   /**
    * Return unsigned long
    */
	public long getUnsigned() {
		  long unsigned = 0;
		  int l = this.bit_array.length;
		  for (int i =0; i<l;i++){
		       unsigned = Long.sum(unsigned,(int) (this.bit_array[i].getBit()*Math.pow(2,l-i-1)));
		  }
		
		  return unsigned;
 
	}
   /**
    * Return signed integer
    */
	public int getSigned() {
		  int signed = 0;
		  int l = this.bit_array.length;
		  for (int i =1; i<l-1;i++){
		       signed = Integer.sum(signed,(int) (this.bit_array[i].getBit()*Math.pow(2,l-i-1)));
		  }
		  if(this.bit_array[0].getBit() == 1){
		       signed = - signed;
		   }
		  return signed;
	}
    /**
     * Copy a new longword to this class
     */
	public void copy(Longword other) {
		int len_a = this.bit_array.length; 
		int len_b = other.bit_array.length;
		
		Bit[] temp = this.bit_array;
		this.bit_array = new Bit[len_a+len_b];
		
		for (int i = 0; i<len_a; i++){
			this.bit_array[i] = temp[i];
		}
		for(int i = len_a; i<len_a + len_b; i++) {
			this.bit_array[i]= other.bit_array[i-len_a];
		}		
	}
   /**
    * Set value for Longword
    */
	public void set(int value) {
		
		String initial = Integer.toBinaryString(value);
		char[] char_array = initial.toCharArray();
		this.bit_array = new Bit[char_array.length];
		for(int i = 0;i<char_array.length;i++) {
			bit_array[i] = new Bit(Character.getNumericValue(char_array[i]));  
		}
		
		
	}
	
 
}