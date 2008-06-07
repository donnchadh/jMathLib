package jmathlib.toolbox.jmathlib.graphics;

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;

/**An external function for 2 dimensional plots*/
public class subplot extends ExternalFunction
{

	public OperandToken evaluate(Token[] operands)
	{

		//ErrorLogger.debugLine("subplot evaluate");

        if (getNArgIn(operands) != 3)
			throwMathLibException("subplot: number of arguments != 3");

	    if ((!(operands[0] instanceof DoubleNumberToken)) || 
	        (!(operands[1] instanceof DoubleNumberToken)) || 
	        (!(operands[2] instanceof DoubleNumberToken))    )
        	throwMathLibException("subplot: wrong type of argument");

		int gridY       = (int)(((DoubleNumberToken)operands[0]).getReValues()[0][0]); 
		int gridX       = (int)(((DoubleNumberToken)operands[1]).getReValues()[0][0]); 
		int currentAxes = (int)(((DoubleNumberToken)operands[2]).getReValues()[0][0]); 

		getGraphicsManager().getCurrentFigure().setSubPlot(gridY, gridX, currentAxes);
		getGraphicsManager().getCurrentFigure().repaint();
		

		return null;
	}
}

/*
@GROUP
graphics
@SYNTAX
SUBPLOT(y, x, axes)
@DOC
divides a graph into cells.
@EXAMPLES
subplot(2,4, 1)
@NOTES
@SEE
plot, plot3, surf
*/

