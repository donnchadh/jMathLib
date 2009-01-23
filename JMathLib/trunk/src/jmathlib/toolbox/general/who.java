package jmathlib.toolbox.general;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.interpreter.*;
import jmathlib.core.functions.ExternalFunction;
import java.util.*;

/**An external function for getting the stored variables*/
public class who extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

		//if (operands != null)
   		//    throwMathLibException("who requires no arguments");

		Variable var;

		globals.getInterpreter().displayText("\nYour variables are:\n");

		Iterator iter = globals.getLocalVariables().getIterator();
		while(iter.hasNext())
		{
		    Map.Entry next = ((Map.Entry)iter.next());
		    var = ((Variable)next.getValue());
		    globals.getInterpreter().displayText(var.getName());
		}

		return null;		
	}
}

/*
@GROUP
general
@SYNTAX
who
@DOC
Returns a list of all the variables in the system.
@EXAMPLES
<programlisting>
who 
ans 
x 
y
</programlisting>
@NOTES
@SEE
whos
*/

