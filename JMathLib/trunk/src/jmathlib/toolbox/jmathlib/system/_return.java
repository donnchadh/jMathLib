package jmathlib.toolbox.jmathlib.system;

import jmathlib.core.interpreter.*;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.tokens.*;

/**return from a user function*/
public class _return extends ExternalFunction
{
    /**return from a function*/
    public OperandToken evaluate(Token[] operands)
    {
    	//if (operands == null)
    		throw new ControlException();
    	
    	//throw new ControlException(ControlException.Return, operands[0]);
    }
}

/*
@GROUP
system
@SYNTAX
RETURN(value)
@DOC
Returns from user function.
@NOTES
@EXAMPLES
RETURN(3)
@SEE
*/

