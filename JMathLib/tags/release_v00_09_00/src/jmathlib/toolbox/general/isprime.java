package jmathlib.toolbox.general;

import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.functions.ExternalFunction;

/**External function to check if a number is a prime*/
public class isprime extends ExternalFunction
{
	/**@param operands[0] = a matrix of numbers
	@return a matrix the same size with 1 if the number is a prime*/
	public OperandToken evaluate(Token[] operands)
	{
        if (getNArgIn(operands) != 1)
			throwMathLibException("isPrime: number of arguments != 1");
            
	    if (!(operands[0] instanceof DoubleNumberToken)) 
        	throwMathLibException("isPrime: argument must be a number");
		
		DoubleNumberToken matrix = ((DoubleNumberToken)operands[0]);
		int sizeX = matrix.getSizeX();
		int sizeY = matrix.getSizeY();
		double[][] values = matrix.getReValues();
		double[][] results = new double[sizeY][sizeX];
			
		for(int yy = 0; yy < sizeY; yy++)
		{
			for(int xx = 0; xx < sizeX; xx++)
			{
				results[yy][xx] = isPrime(values[yy][xx]);
			}
		}
		OperandToken result = new DoubleNumberToken(results);
		
        return result;
	}
	
	private double isPrime(double value)
	{
		double result = 0;
		
		if(value == 2)
			result = 1;
		else if((java.lang.Math.ceil(value/2) != (value/2)) && (value > 2))
		{
			result = 1;
			for(int index = 3; index < value && result != 0; index += 2)
			{
				double temp = value/index;
				if(java.lang.Math.ceil(temp) == temp)
					result = 0;
			}
		}
		
		return result;
	}
	//12243431
}

/*
@GROUP
general
@SYNTAX
answer=isprime(value)
@DOC
Checks if value is a prime. returning 1 if it is.
@EXAMPLES
<programlisting>
isprime(3)=1
isprime(4)=0
</programlisting>
@NOTES
@SEE
primes
*/

