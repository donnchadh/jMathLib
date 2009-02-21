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
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.tokens.CharToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;
import jmathlib.core.interpreter.Variable;

/**An external function for writing to the main display
sets the last warning variable to the message displayed*/
public class warning extends ExternalFunction
{
	/**write operand to main display
	@param operand[n] = items to display*/
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{
		String message = "";
		for(int index = 0; index < operands.length; index++)
		{
			message = operands[index].toString();
			globals.getInterpreter().displayText(message);
		}
		
		Variable var = globals.createVariable("lastwarning");
		var.assign(new CharToken(message));	

		return new DoubleNumberToken(1);
	}
}

/*
@GROUP
system
@SYNTAX
warning(message)
@DOC
Displays a warning message
@NOTES
This function sets the lastwarning variable
@EXAMPLES
warning("Danger, danger")
Danger, danger
@SEE
error, exit
*/

