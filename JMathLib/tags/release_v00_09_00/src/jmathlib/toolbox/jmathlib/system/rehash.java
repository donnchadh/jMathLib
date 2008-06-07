package jmathlib.toolbox.jmathlib.system;

import jmathlib.core.tokens.*;
import jmathlib.core.functions.ExternalFunction;

public class rehash extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{

        getFunctionManager().checkAndRehashTimeStamps();

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

