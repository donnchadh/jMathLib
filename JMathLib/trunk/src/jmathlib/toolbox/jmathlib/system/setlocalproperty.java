package jmathlib.toolbox.jmathlib.system;

import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.CharToken;
import jmathlib.core.tokens.OperandToken;

/**External function to get a enviroment variable*/
public class setlocalproperty extends ExternalFunction
{
	/**Returns an enviroment variable
	@param operand[0] = the name of the variable
	@param operand[1] = a default value (optional)
	@return the enviroment value*/
	public OperandToken evaluate(Token[] operands)
	{
		OperandToken result = null;
		
        if (getNArgIn(operands)!=2)
            throwMathLibException("setlocalproperty: number of arguments != 2");
        

        if ( (!(operands[0] instanceof CharToken)) &&
             (!(operands[1] instanceof CharToken))    )
            throwMathLibException("setlocalproperty: arguments must be strings");

			String name = operands[0].toString();
            String prop = operands[1].toString();
		
			getInterpreter().prefs.setLocalProperty(name, prop);
			
			
		return result;
	}
}

/*
@GROUP
system
@SYNTAX
GETENV(variablename)
@DOC
Returns the value of the enviromental variable variablename.
@NOTES
@EXAMPLES
GETENV("HOME")= "/home/user"
@SEE
*/

