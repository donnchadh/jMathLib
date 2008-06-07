package jmathlib.toolbox.jmathlib.graphics;

import jmathlib.core.tokens.*;
import jmathlib.core.functions.ExternalFunction;

/**Add text to the x-axis of the current axes*/
public class xlabel extends ExternalFunction
{

	public OperandToken evaluate(Token[] operands)
	{

		//ErrorLogger.debugLine("xlabel evaluate");

		if (getNArgIn(operands) != 1)
			throwMathLibException("xlabel: number of arguments != 1");
            
	    if (!(operands[0] instanceof CharToken)) 
        	throwMathLibException("xlabel: argument must be a string");

        String xLabel = ((CharToken)operands[0]).toString();

		getGraphicsManager().getCurrentFigure().getCurrentAxes().setXLabel(xLabel);
		getGraphicsManager().getCurrentFigure().repaint();

		return null;
	}
}

/*
@GROUP
graphics
@SYNTAX
xlabel(label)
@DOC
Sets the label on the x axis of a graph
@EXAMPLES
<programlisting>
xlabel("x axis")
</programlisting>
@NOTES
.
@SEE
ylabel, zlabel, title
*/

