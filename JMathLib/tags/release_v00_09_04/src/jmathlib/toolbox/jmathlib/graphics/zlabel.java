package jmathlib.toolbox.jmathlib.graphics;

import jmathlib.core.tokens.*;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

/**Add text to the z-axis of the current axes*/
public class zlabel extends ExternalFunction
{

	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

		//ErrorLogger.debugLine("zlabel evaluate");

		if (getNArgIn(operands) != 1)
			throwMathLibException("zlabel: number of arguments != 1");
            
	    if (!(operands[0] instanceof CharToken)) 
        	throwMathLibException("zlabel: argument must be a string");

		String zLabel = ((CharToken)operands[0]).toString();

		globals.getGraphicsManager().getCurrentFigure().getCurrentAxes().setZLabel(zLabel);
		globals.getGraphicsManager().getCurrentFigure().repaint();

		return null;
	}
}

/*
@GROUP
graphics
@SYNTAX
zlabel(label)
@DOC
Sets the label on the z axis of a graph
@EXAMPLES
<programlisting>
zlabel("z axis")
</programlisting>
@NOTES
@SEE
xlabel, ylabel, title
*/

