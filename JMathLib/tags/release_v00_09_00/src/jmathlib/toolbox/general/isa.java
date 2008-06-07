package jmathlib.toolbox.general;

import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.tokens.*;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.functions.ExternalFunction;

public class isa extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{

        if (getNArgIn(operands) != 2)
			throwMathLibException("isa: number of arguments != 2");

        if (!(operands[0] instanceof DataToken))  
            throwMathLibException("isa: first operand must be a data token");
        
        if (!(operands[1] instanceof CharToken))  
            throwMathLibException("isa: second operand must be a char token");
        
        String opDataType = ((DataToken)operands[0]).getDataType();
        
        String reqDataType = ((CharToken)operands[1]).getValue();

        
		if (opDataType.compareTo(reqDataType)==0)  
            return DoubleNumberToken.one;
		else
            return DoubleNumberToken.zero;
	}
}

/*
@GROUP
general
@SYNTAX
isa(value,'class')
@DOC
.
@EXAMPLES
<programlisting>
a=55
isa(a,'double')  -> 1
isa(a,'char')    -> 0
</programlisting>
@NOTES
.
@SEE
ismatrix, isnumeric, isscalar, issquare, islogical
*/

