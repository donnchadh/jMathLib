package jmathlib.toolbox.io;

import jmathlib.core.tokens.*;
import jmathlib.core.functions.ExternalFunction;
import java.io.*;

public class loadvariables extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{

        if (getNArgIn(operands) > 1)
			throwMathLibException("loadvariables: number of arguments > 1");


        String file = "." + File.separator + "variables.mlf";
        
        if (getNArgIn(operands) == 1)
        {
            if (!(operands[0] instanceof CharToken)) 
                throwMathLibException("loadvariables: argument must be a string");

            file = ((CharToken)operands[0]).toString();
        }

        getVariables().loadVariables(file);
        
		return null;		

	} // end eval
}

/*
@GROUP
IO
@SYNTAX
loadvariables()
loadvariables("name.mlf")
@DOC
loads the variables from a serialized .mlf-file
@EXAMPLES
<programlisting>
loadvariables()
</programlisting>
@NOTES
The variables are written as a serialized stream of java objects. Therefor the
format of the .mlf-file is java-specific. The file is also specific to the
versions of MathLib token and classes.
@SEE
savevariables
*/

