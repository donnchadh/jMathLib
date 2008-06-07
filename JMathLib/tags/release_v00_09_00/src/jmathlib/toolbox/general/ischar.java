package jmathlib.toolbox.general;

import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.tokens.*;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.functions.ExternalFunction;

/**An external function which checks if the argument is a char*/
public class ischar extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{

        if (getNArgIn(operands) != 1)
			throwMathLibException("ischar: number of arguments != 1");
        
		if (operands[0] instanceof CharToken)   return DoubleNumberToken.one;
		else                                    return DoubleNumberToken.zero;
	}
}

/*
@GROUP
general
@SYNTAX
answer = ischar(value)
@DOC
Returns 1 if the first operand is a struct, else it returns 0.
@EXAMPLES
<programlisting>
ischar("hello") returns 1 
ischar(55)  returns 0
</programlisting>
@NOTES
.
@SEE
ismatrix, isnumeric, isscalar, issquare
*/

