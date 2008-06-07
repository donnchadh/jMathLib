package jmathlib.toolbox.jmathlib.graphics;

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;

/**Add a grid to the current axes*/
public class grid extends ExternalFunction
{

	public OperandToken evaluate(Token[] operands)
	{

		//ErrorLogger.debugLine("grid evaluate");

		boolean value = true; // default is grid on

        if (getNArgIn(operands) != 1)
			throwMathLibException("grid: number of arguments != 1");
            

	    if (operands[0] instanceof CharToken) 
		{
			String s = ((CharToken)operands[0]).toString();
			if (s.equals("on")) 
				value = true;
			if (s.equals("off")) 
				value = false;
		}
		else if (operands[0] instanceof DoubleNumberToken)
		{
			int v = (int)(((DoubleNumberToken)operands[0]).getReValues()[0][0]);
			if (v == 1.0)
				value = true;
			if (v == 0.0)
				value = false;
		}
		else
        {
   			throwMathLibException("grid: wrong argument");
        }
        
		// switch grid on/off 
		getGraphicsManager().getCurrentFigure().getCurrentAxes().set("XGrid",new Boolean(value));
        getGraphicsManager().getCurrentFigure().getCurrentAxes().set("YGrid",new Boolean(value));
        getGraphicsManager().getCurrentFigure().getCurrentAxes().set("ZGrid",new Boolean(value));

		getGraphicsManager().getCurrentFigure().repaint();

		return null;
	}
}

/*
@GROUP
graphics
@SYNTAX
grid(value)
@DOC
Turns the grid on or off on a graph.
@EXAMPLES
<programlisting>
grid("on")      turns the grid on
grid("off")     turns the grid off
</programlisting>
@NOTES
@SEE
*/

