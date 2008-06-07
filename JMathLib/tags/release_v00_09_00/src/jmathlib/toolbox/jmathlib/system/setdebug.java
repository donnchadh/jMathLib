package jmathlib.toolbox.jmathlib.system;

import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.interpreter.Errors;

/**External function for controlling the debug setting for the system*/
public class setdebug extends ExternalFunction
{
    /**Sets the debug flag
    @param operands[0] = 1, show debug info
                         0, to turn debug info off*/
    public OperandToken evaluate(Token[] operands)
    {
        if (getNArgIn(operands) !=1)
            throwMathLibException("setdebug: number of arguments !=1");
        
        if(operands[0] instanceof DoubleNumberToken)
        {
            int debug = ((DoubleNumberToken)operands[0]).getIntValue(0,0);
            
            if(debug == 0)
                setDebug(false);
            else
                setDebug(true);                                    

            return new DoubleNumberToken(1);                
        }
        else
			Errors.throwMathLibException(ERR_INVALID_PARAMETER, new Object[] {"DoubleNumberToken", operands[0].getClass().getName()});
            
        return null;
    }
}

/*
@GROUP
system
@SYNTAX
SETDEBUG(value)
@DOC
Switches debug output on or off
@NOTES
@EXAMPLES
SETDEBUG(1) turns debug output on
SETDEBUG(0) turns debug output off
@SEE
*/

