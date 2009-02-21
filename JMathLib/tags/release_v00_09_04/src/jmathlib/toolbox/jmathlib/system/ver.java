/* 
 * This file is part or JMathLib 
 * 
 * Check it out at http://www.jmathlib.de
 *
 * Author:  
 * (c) 2005-2009   
 */
package jmathlib.toolbox.jmathlib.system;

import jmathlib.core.tokens.*;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

/**An external function for returning versionn information*/
public class ver extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

		String s        = "";
 		    
		globals.getInterpreter().displayText("Version information for JMathLib");

        s = globals.getProperty("jmathlib.version");
        globals.getInterpreter().displayText("version: "+s);

        s = globals.getProperty("jmathlib.release.date");
        globals.getInterpreter().displayText("release date: "+s);

        s = globals.getProperty("jmathlib.release.name");
        globals.getInterpreter().displayText("release name: "+s);

        s = globals.getProperty("jmathlib.release.description");
        globals.getInterpreter().displayText("release description: "+s);

        s = globals.getProperty("jmathlib.copyright");
        globals.getInterpreter().displayText(s);

		return null;		
	}
}

/*
@GROUP
system
@SYNTAX
ver
@DOC
detailed version information about toolboxes
@EXAMPLE
<programlisting>
ver
</programlisting>
@NOTES
.
@SEE
version
*/
