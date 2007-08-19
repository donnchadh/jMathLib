package jmathlib.toolbox.general;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.VariableToken;
import jmathlib.core.tokens.CharToken;
import jmathlib.core.interpreter.*;
import jmathlib.core.functions.ExternalFunction;

/**An external function for clearing stored variables*/
public class clear extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{

		Variable var;
		String s=" ";

        if (getNArgIn(operands) != 1)
			throwMathLibException("clear: number of arguments < 1");
            
		if (operands[0] instanceof VariableToken)
		{
			s = ((VariableToken)operands[0]).getName();
			ErrorLogger.debugLine("clear "+s);
		}
		else if(operands[0] instanceof CharToken)
		{
			s = ((CharToken)operands[0]).getValue();
		}

		if (!s.equals(" "))
		{
			// remove one variable
			getVariables().remove(s);
		}
		else
		{
		    getVariables().clear();
            getGlobalVariables().clear();
            getFunctionManager().clear();
		}

		return null;		
	}
}

/*
@GROUP
general
@SYNTAX
clear(variable)
@DOC
Clears the specified variable or, if blank, clears all variables.
@EXAMPLE
<programlisting>
clear('x'); 
clear();
</programlisting>
@NOTES
The variable should be given as a string containing the variable name.
@SEE
who, whos
*/
