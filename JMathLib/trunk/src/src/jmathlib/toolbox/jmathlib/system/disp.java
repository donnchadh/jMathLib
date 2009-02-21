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

/**An external function for writing to the main display*/
public class disp extends ExternalFunction
{
	/**write operand to main display
	@param operand[n] = items to display*/
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{
		for(int index = 0; index < operands.length; index++)
		{
		    globals.getInterpreter().displayText(operands[index].toString());
		}
		
		return null; 
	}
}

/*
@GROUP
system
@SYNTAX
DISP(string1, string2, string3, ...)
@DOC
This displays a set of lines of text.
@NOTES
@EXAMPLES
DISP("Hello", "world")
Hello
world
@SEE
*/
