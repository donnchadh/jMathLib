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
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

/**An external function for writing to the main display*/
public class newline extends ExternalFunction
{
	/**write operand to main display
	@param operand[n] = items to display*/
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{
		double count = 1;
		
		if((operands.length > 0) && (operands[0] instanceof DoubleNumberToken))
		{
			count = ((DoubleNumberToken)operands[0]).getValueRe();
		}
		
		for(int index = 0; index < count; index++)
		{
		    globals.getInterpreter().displayText("");
		}
		
		return new DoubleNumberToken(1);
	}
}

/*
@GROUP
system
@SYNTAX
newline(lines)
@DOC
Displays number of blank lines equal to the first parameter.
@NOTES
@EXAMPLES
NEWLINE(1)

NEWLINE(2)

@SEE
*/

