package jmathlib.toolbox.general;

import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.DataToken;
import jmathlib.core.tokens.CharToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

/**An external function for getting the size of matrices*/
public class length extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{
		// at least one operand
        if (getNArgIn(operands) != 1)
			throwMathLibException("length: number of arguments != 1");
            
		int length = 0;
		// first operand must be a number
		if(operands[0] instanceof DataToken)
		{
			// get size or argument
			int y = (int)((DataToken)operands[0]).getSizeY();
			int x = (int)((DataToken)operands[0]).getSizeX();
			
			length = Math.max(y,x);
		}
		else if(operands[0] instanceof CharToken)
		{
			length = ((CharToken)operands[0]).toString().length();		
		}

		return new DoubleNumberToken(length);		
	}
}


/*
@GROUP
general
@SYNTAX
answer = length(string) 
answer = length(matrix) 
answer = number(matrix) 
@DOC
Returns the length of a matrix or a string.
@EXAMPLES
<programlisting>
length("test") = 4 
length([1, 2, 3]) = 3
</programlisting>
@NOTES
@SEE
size
*/

