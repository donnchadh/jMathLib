package jmathlib.toolbox.jmathlib.system;

import jmathlib.core.tokens.*;
import jmathlib.core.functions.ExternalFunction;

/**An external function for returning versionn information*/
public class ver extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{

		String s        = "";
 		    
        getInterpreter().displayText("Version information for JMathLib");

        s = getInterpreter().prefs.getLocalProperty("jmathlib.version");
        getInterpreter().displayText("version: "+s);

        s = getInterpreter().prefs.getLocalProperty("jmathlib.release.date");
        getInterpreter().displayText("release date: "+s);

        s = getInterpreter().prefs.getLocalProperty("jmathlib.release.name");
        getInterpreter().displayText("release name: "+s);

        s = getInterpreter().prefs.getLocalProperty("jmathlib.release.description");
        getInterpreter().displayText("release description: "+s);

        s = getInterpreter().prefs.getLocalProperty("jmathlib.copyright");
        getInterpreter().displayText(s);

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
