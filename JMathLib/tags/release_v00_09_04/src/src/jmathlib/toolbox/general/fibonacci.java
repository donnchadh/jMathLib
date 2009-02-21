package jmathlib.toolbox.general;

import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

/**External function to calculate the nth fibonacci number*/
public class fibonacci extends ExternalFunction
{
	/**calculate the Fibonacci number
	@param operand[0] = The index of the Fibonacci number
	@return the Fibonacci number
	*/
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{
		if (getNArgIn(operands) != 1)
			throwMathLibException("fibonacci: number of arguments != 1");
            
	    if (!(operands[0] instanceof DoubleNumberToken)) 
        	throwMathLibException("fibonacci: first argument must be a number");

		//harmonic(n) = 1 + 1/2 + 1/3 + ... + 1/n
		double index = ((DoubleNumberToken)operands[0]).getValueRe();
			
		double total1 = 0;
		double total2 = 1;
			
		for(int count = 1; count <= index; count++)
		{
			double temp = total2 + total1;
			total1 = total2;
			total2 = temp;
		}
		OperandToken result = new DoubleNumberToken(total1);
		
        return result;
	}
}


/*
@GROUP
general
@SYNTAX
answer = fibonacci(value)
@DOC
Returns the fibonacci number with an index of value.
@EXAMPLES
<programlisting>
fibonacci(5) = 5 
fibonacci(10) = 55
</programlisting>
@NOTES
@SEE
*/

