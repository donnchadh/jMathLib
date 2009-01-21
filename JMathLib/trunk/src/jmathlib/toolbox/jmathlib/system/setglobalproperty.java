package jmathlib.toolbox.jmathlib.system;

import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;
import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.CharToken;
import jmathlib.core.tokens.OperandToken;

/**External function to get a enviroment variable*/
public class setglobalproperty extends ExternalFunction
{
	/**Returns an enviroment variable
	@param operand[0] = the name of the variable
	@param operand[1] = a default value (optional)
	@return the enviroment value*/
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{
		OperandToken result = null;
		
        if (getNArgIn(operands)!=2)
            throwMathLibException("setglobalproperty: number of arguments != 2");
        

        if ( (!(operands[0] instanceof CharToken)) &&
             (!(operands[1] instanceof CharToken))    )
            throwMathLibException("setglobalproperty: arguments must be strings");

			String name = operands[0].toString();
            String prop = operands[1].toString();
		
            globals.getInterpreter().prefs.setGlobalProperty(name, prop);
			
			
		return result;
	}
}

/*
@GROUP
system
@SYNTAX
setglobalproperty(property name, value)
@DOC
Returns the value of the enviromental variable variablename.
@NOTES
@EXAMPLES
GETENV("HOME")= "/home/user"
@SEE
setlocalproperty, getlocalproperty, getglobalproperty
*/

