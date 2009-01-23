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
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

		Variable var;
		String s=" ";

        if (getNArgIn(operands) != 1)
			throwMathLibException("clear: number of arguments < 1");
            
        // get subcommand or variable name
		if (operands[0] instanceof VariableToken)
		{
			s = ((VariableToken)operands[0]).getName();
			ErrorLogger.debugLine("clear "+s);
		}
		else if(operands[0] instanceof CharToken)
		{
			s = ((CharToken)operands[0]).getValue();
		}

		// check what the user wans to clear
		if (s.equals("variables"))
		{
		    // only clear local variables
            globals.getLocalVariables().clear();
		}
		else if (s.equals("globals"))
		{
		    // clear global variables
		    globals.getGlobalVariables().clear();
		    globals.getLocalVariables().clear();
            //TODO: when removing global variables also remove 
            //     pointers from local to global varaibles in "getLocalVariables"
		}
		else if (s.equals("functions"))
		{
		    // clear cache for m-files, class-files, script-files
		    globals.getFunctionManager().clear();
		}
		else if (s.equals("all"))
		{
		    // clear everything
		    globals.getLocalVariables().clear();
		    globals.getGlobalVariables().clear();
		    globals.getFunctionManager().clear();

		}
		else if (!s.equals(" "))
		{
			// remove one variable from local workspace
		    globals.getLocalVariables().remove(s);
		}
		else
		{
		    // clear without any arguments only clears the local workspace
		    globals.getLocalVariables().clear();
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
clear()clears local variables
clear("variables") clears local variables
clear("globals") clears global variables
clear("functions"" clear function cache
clear("all") clear local variables, global variables and function cache
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
