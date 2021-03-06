package jmathlib.toolbox.jmathlib.system;

import jmathlib.core.tokens.*;
import jmathlib.core.functions.ExternalFunction;

/**An external function for returning versionn information*/
public class version extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{

		String s        = "";
 		    
        getInterpreter().displayText("Version information for JMathLib");

        s = getInterpreter().prefs.getGlobalProperty("jmathlib.version");
        getInterpreter().displayText("version: "+s);

        s = getInterpreter().prefs.getGlobalProperty("jmathlib.release.date");
        getInterpreter().displayText("release date: "+s);

        s = getInterpreter().prefs.getGlobalProperty("jmathlib.release.name");
        getInterpreter().displayText("release name: "+s);

        s = getInterpreter().prefs.getGlobalProperty("jmathlib.release.description");
        getInterpreter().displayText("release description: "+s);

        s = getInterpreter().prefs.getGlobalProperty("jmathlib.copyright");
        getInterpreter().displayText(s);

		return null;		
	}
}

/*
@GROUP
system
@SYNTAX
version
@DOC
displays current version of JMathLib
@EXAMPLE
<programlisting>
version
</programlisting>
@NOTES
.
@SEE
ver
*/
