package jmathlib.toolbox.jmathlib.system;

import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;
import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.CharToken;
import jmathlib.core.tokens.OperandToken;

/**External function to get a enviroment variable*/
public class getjmathlibproperty extends ExternalFunction
{
	/**Returns an enviroment variable
	@param operand[0] = the name of the variable
	@param operand[1] = a default value (optional)
	@return the enviroment value*/
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{
		OperandToken result = null;
		
        if (getNArgIn(operands)!=1)
            throwMathLibException("getjmathlibproperty: number of arguments != 1");
        

        if (!(operands[0] instanceof CharToken))
            throwMathLibException("getjmathlibproperty: number of arguments != 1");

			String name = operands[0].toString();
			String defaultVal = "";
			
		
			String property = globals.getProperty(name);
			
			result = new CharToken(property);
			
		return result;
	}
}

/*
@GROUP
system
@SYNTAX
getjmathlibproperty(variablename)
@DOC
Returns the value of the enviromental variable variablename.
@NOTES
@EXAMPLES
getlocal("HOME")= "/home/user"
@SEE
getenv, setjmathlibproperty
*/

