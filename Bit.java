package theBit;
 
public class Bit {
	private int bit;
	private boolean value;
	
	//Constructor 
	Bit (int bit)
	{
		this.bit = bit;
		if (bit == 0)
			value = false; 
		else
			value = true;			
	}
	
	//Set the boolean value to object
	public void set(boolean value)
	{
		this.value = value;
		if (this.value == true)
			bit = 1;
		else
			bit = 0;
	}
	
	//Toggle the value and bit 
	public void toggle()
	{
		this.value = !value;
		if (this.value == true)
			bit = 1;
		else
			bit = 0;
	}
	
	//Set value to true, bit to 1
	public void set()
	{
		this.value = true;
		this.bit = 1;
	}
	
	//Clear value to false, bit to 0
	public void clear()
	{
		this.value = false;
		this.bit = 0;
	}
	
	//Return the current value
	public boolean getValue() 
	{
		return this.value;
	}	
	
	//Return the current bit
	public int getBit() 
	{
		return this.bit;
	}	
	
	//Performs logical AND on two objects and returns a new object set to the result
	public Bit and(Bit other)
	{
		if (this.value == false)
			return new Bit(0);
		else  if (other.value == false)
			return new Bit(0);
		else
			return new Bit(1);
	}
	
	//Performs logical OR on two objects and returns a new object set to the result
	public Bit or(Bit other)
	{
		if (this.value == true)
			return new Bit(1);
		else
		{
			if(other.value == true)
				return new Bit(1);
			else
				return new Bit(0);
		}
	}
	
	//Perform logical XOR on two objects and return a new object set to the result
	public Bit xor(Bit other)
	{
		if (this.value == other.value)
			return new Bit(0);
		else
			return new Bit(1);
	}
	
	//Perform logical NOT on one object and return a new object set to the result
	public Bit not()
	{
		toggle();
		return new Bit(this.bit);
	}
	
	//Return the value to String
	@Override
	public String toString()
	{
		if (value == true)
			return "t";
		else
			return "f";
	}
}