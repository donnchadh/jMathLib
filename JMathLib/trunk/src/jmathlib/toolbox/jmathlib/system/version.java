package jmathlib.toolbox.jmathlib.system;

import jmathlib.core.tokens.*;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

/**An external function for returning versionn information*/
public class version extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

		String s        = "";
 		    
		globals.getInterpreter().displayText("Version information for JMathLib");

        s = globals.getInterpreter().prefs.getLocalProperty("jmathlib.version");
        globals.getInterpreter().displayText("version: "+s);

        s = globals.getInterpreter().prefs.getLocalProperty("jmathlib.release.date");
        globals.getInterpreter().displayText("release date: "+s);

        s = globals.getInterpreter().prefs.getLocalProperty("jmathlib.release.name");
        globals.getInterpreter().displayText("release name: "+s);

        s = globals.getInterpreter().prefs.getLocalProperty("jmathlib.release.description");
        globals.getInterpreter().displayText("release description: "+s);

        s = globals.getInterpreter().prefs.getLocalProperty("jmathlib.copyright");
        globals.getInterpreter().displayText(s);

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
