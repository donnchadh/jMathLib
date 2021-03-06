package jmathlib.toolbox.general;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.CharToken;
import jmathlib.core.tokens.FunctionHandleToken;
import jmathlib.core.functions.ExternalFunction;

/**External function */
public class func2str extends ExternalFunction
{
	/**@param operands[0] = a matrix of numbers
	@return a matrix the same size with 1 if the number is a prime*/
	public OperandToken evaluate(Token[] operands)
	{
        if (getNArgIn(operands) != 1)
			throwMathLibException("func2str: number of arguments != 1");
            
	    if (!(operands[0] instanceof FunctionHandleToken)) 
            throwMathLibException("func2str: argument no function handle");

	    String s = ((FunctionHandleToken)operands[0]).getName();
	    
	    return new CharToken(s);
	    

	}
	
}

/*
@GROUP
general
@SYNTAX
answer=isfunctionhandle(value)
@DOC
Checks if value is a function handle. Returning 1 if it is.
@EXAMPLES
<programlisting>
isfunctionhandle( @sin ) -> 1
</programlisting>
@NOTES
@SEE
isa
*/

