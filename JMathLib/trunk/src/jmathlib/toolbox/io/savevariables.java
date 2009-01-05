package jmathlib.toolbox.io;

import jmathlib.core.tokens.*;
import jmathlib.core.functions.ExternalFunction;
import java.io.*;

public class savevariables extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{

        if (getNArgIn(operands) > 1)
			throwMathLibException("savevariables: number of arguments > 1");


        String file = "." + File.separator + "variables.mlf";
        
        if (getNArgIn(operands) == 1)
        {
            if (!(operands[0] instanceof CharToken)) 
                throwMathLibException("savevariables: argument must be a string");

            file = ((CharToken)operands[0]).toString();
        }

        getLocalVariables().saveVariables(file);
        
		return null;		

	} // end eval
}

/*
@GROUP
IO
@SYNTAX
savevariables()
savevariables("name.mlf")
@DOC
saves the variables from the current workspace into a serialized .mlf-file
@EXAMPLES
<programlisting>
savevariables()
</programlisting>
@NOTES
The variables are written as a serialized stream of java objects. Therefor the
format of the .mlf-file is java-specific. The file is also specific to the
versions of MathLib token and classes.
@SEE
loadvariables, csvread, csvwrite
*/

