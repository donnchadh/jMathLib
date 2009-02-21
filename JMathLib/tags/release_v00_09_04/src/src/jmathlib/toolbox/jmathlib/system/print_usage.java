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
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

public class print_usage extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{	

        throwMathLibException("print_usage");
		
        return  null;
	}
}

/*
@GROUP
system
@SYNTAX
print_usage
@DOC
print out the valid usage of the calling function
@NOTES
@EXAMPLES
@SEE
warning, error
*/

