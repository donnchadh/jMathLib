package jmathlib.toolbox.jmathlib.system;

import jmathlib.core.tokens.*;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

public class rehash extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

	    globals.getFunctionManager().checkAndRehashTimeStamps();

        return null;
        
	} // end eval
}

/*
@GROUP
system
@SYNTAX
rehash
@DOC
rehases all m-files in the cache for user functions
@EXAMPLES
<programlisting>
rehash
</programlisting>
@SEE
path, rmpath, addpath 
*/

