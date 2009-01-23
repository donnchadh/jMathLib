package jmathlib.toolbox.general;

import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

/**External function to calculate the nth harmonic number*/
public class harmonic extends ExternalFunction
{
	/**calculate the harmonic number
	@param operand[0] = The index of the harmonic number
	@return the harmonic number
	*/
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{
		OperandToken result = new DoubleNumberToken(0);
		if(operands.length >= 1 && operands[0] instanceof DoubleNumberToken)
		{
			//harmonic(n) = 1 + 1/2 + 1/3 + ... + 1/n
			double index = ((DoubleNumberToken)operands[0]).getValueRe();
			
			double total = 0;
			for(int count = 1; count <= index; count++)
			{
				total += (1.0/ count);
			}
			result = new DoubleNumberToken(total);
		}
		return result;
	}
}


/*
@GROUP
general
@SYNTAX
answer = harmonic(value)
@DOC
Returns the harmonic number with an index of value.
@EXAMPLES
<programlisting>
harmonic(5) = 2.283333333333333
harmonic(10) = 2.9289682539682538
</programlisting>
@NOTES
This calculates sum(1..value){1/n}
@SEE
*/

