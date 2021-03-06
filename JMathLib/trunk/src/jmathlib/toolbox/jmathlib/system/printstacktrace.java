/* 
 * This file is part or JMathLib 
 * 
 * Check it out at http://www.jmathlib.de
 *
 * Author:  
 * (c) 2005-2009   
 */
package jmathlib.toolbox.jmathlib.system;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.CharToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

/**An external function for displaying error messages
aborts the current function being processed*/
public class printstacktrace extends ExternalFunction
{
	/**write operand to main display then abort processing
	@param operand[n] = error messages to display*/
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{	
        String val = "";
		if(operands[0] instanceof CharToken)
		{
			val = ((CharToken)operands[0]).toString() + "\n";
		}	
        
        //val += getContextList().getStackTrace();
		return  new CharToken(val);
	}
}

/*
@GROUP
system
@SYNTAX
printstacktrace(message)
@DOC
Displays message and the current execution stack trace.
@NOTES
@EXAMPLES
Error("There has been an error")
There has been an error
@SEE
warning, error
*/

