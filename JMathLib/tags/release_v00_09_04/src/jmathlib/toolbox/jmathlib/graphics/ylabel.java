package jmathlib.toolbox.jmathlib.graphics;

import jmathlib.core.tokens.*;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

/**Add text to the y-axis of the current axes*/
public class ylabel extends ExternalFunction
{

	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

		//ErrorLogger.debugLine("ylabel evaluate");

        if (getNArgIn(operands) != 1)
			throwMathLibException("ylabel: number of arguments != 1");
            
	    if (!(operands[0] instanceof CharToken)) 
        	throwMathLibException("ylabel: argument must be a string");

		String yLabel = ((CharToken)operands[0]).toString();

		globals.getGraphicsManager().getCurrentFigure().getCurrentAxes().setYLabel(yLabel);
		globals.getGraphicsManager().getCurrentFigure().repaint();

		return null;
	}
}

/*
@GROUP
graphics
@SYNTAX
ylabel(label)
@DOC
Sets the label on the y axis of a graph
@EXAMPLES
<programlisting>
ylabel("y axis")
</programlisting>
@NOTES
@SEE
xlabel, zlabel, title
*/

