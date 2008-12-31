package jmathlib.toolbox.jmathlib.system;

import jmathlib.core.tokens.*;
import jmathlib.core.functions.ExternalFunction;

public class quit extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{

	    if ((getNArgIn(operands) == 1)         &&
	        (operands[0] instanceof CharToken)    )
	    {
	            String value = operands[0].toString();

	            // if user calls quit("force") JMathLib will be terminated
	            // immediately without saving anything
	            if (value.equals("force"))
	                System.exit(0);
	    }
	    
	    // run finish script and save local properties
	    getInterpreter().save();
    
	    // exit JMathLib
	    System.exit(0);
	    
		return null;		
	}
}

/*
@GROUP
system
@SYNTAX
quit
@DOC
exits JMathLib
@EXAMPLE
<programlisting>
quit
</programlisting>
@NOTES
quit("force") will terminate JMathLib 
without saving any variables of saving any changed settings.
@SEE
exit
*/
