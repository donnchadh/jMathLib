/* 
 * This file is part or JMathLib 
 * 
 * Check it out at http://www.jmathlib.de
 *
 * Author:  
 * (c) 2005-2009   
 */
package jmathlib.toolbox.jmathlib.system;

import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;
import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.CharToken;
import jmathlib.core.tokens.OperandToken;

/**External function to get a enviroment variable*/
public class setjmathlibproperty extends ExternalFunction
{
	/**Returns an enviroment variable
	@param operand[0] = the name of the variable
	@param operand[1] = a default value (optional)
	@return the enviroment value*/
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{
		OperandToken result = null;
		
        if (getNArgIn(operands)!=2)
            throwMathLibException("setjmathlibproperty: number of arguments != 2");
        

        if ( (!(operands[0] instanceof CharToken)) &&
             (!(operands[1] instanceof CharToken))    )
            throwMathLibException("setjmathlibproperty: arguments must be strings");

			String name = operands[0].toString();
            String prop = operands[1].toString();
		
            globals.setProperty(name, prop);
			
			
		return result;
	}
}

/*
@GROUP
system
@SYNTAX
setjmathlibproperty(property name, value)
@DOC
Returns the value of the enviromental variable variablename.
@NOTES
@EXAMPLES
GETENV("HOME")= "/home/user"
@SEE
getjmathlibproperty, getenv
*/

