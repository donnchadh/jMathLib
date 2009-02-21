package jmathlib.toolbox.general;

import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

/** */
public class isstudent extends ExternalFunction
{
	/**@param operands[0] = a matrix of numbers
	@return a matrix the same size with 1 if the number is a prime*/
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{
        if (getNArgIn(operands) != 0)
			throwMathLibException("isstudent: number of arguments != 0");
            
		
        return new DoubleNumberToken(0.0);
	}
	
}

/*
@GROUP
general
@SYNTAX
isstudent()
@DOC
checks if a student version or a commercial version is used.
Since JMathLib is open source this function always returns 0
@EXAMPLES
<programlisting>
isstudent() -> 0
</programlisting>
@NOTES
@SEE

*/

