package jmathlib.toolbox.jmathlib.system;

import jmathlib.core.tokens.*;
import jmathlib.core.functions.ExternalFunction;

/**An external function for updating JMathLib*/
public class update extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{

		String s        = "";
        String lineFile = "";;
 		    
        getInterpreter().displayText("UPDATING JMathLib");
        
       
		return null;		
	}
}

/*
@GROUP
system
@SYNTAX
update
@DOC
updates JMathLib over the web
@EXAMPLE
<programlisting>
update
</programlisting>
@NOTES
.
@SEE
checkforupdates
*/
