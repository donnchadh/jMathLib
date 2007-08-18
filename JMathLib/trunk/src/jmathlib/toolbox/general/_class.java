package jmathlib.toolbox.general;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.DataToken;
import jmathlib.core.tokens.CharToken;
import jmathlib.core.functions.ExternalFunction;

public class _class extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{

        if (getNArgIn(operands) != 1)
			throwMathLibException("_class: number of arguments != 1");

        if (!(operands[0] instanceof DataToken))  
            throwMathLibException("_class: first operand must be a data token");
        
        String opDataType = ((DataToken)operands[0]).getDataType();
        
        return new CharToken(opDataType);
    }
}

/*
@GROUP
general
@SYNTAX
class(value)
@DOC
returns the class name of some array.
@EXAMPLES
<programlisting>
a=55
class(a)  -> double

b='asdf'
class(b) -> char
</programlisting>
@NOTES
.
@SEE
isa, ismatrix, isnumeric, isscalar, issquare, islogical
*/

