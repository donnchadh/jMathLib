package jmathlib.toolbox.jmathlib.graphics;

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;

/**Holds the current axes. All subsequent uses of plot will accumulate in
   this axes */
public class hold extends ExternalFunction
{

	public OperandToken evaluate(Token[] operands)
	{

		//ErrorLogger.debugLine("hold evaluate");

		boolean value = true; // default is hold on

        if (getNArgIn(operands) != 1)
			throwMathLibException("hold: number of arguments != 1");

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
        	throwMathLibException("hold: wrong argument");
        }

		// switch hold on/off 
	    if (value)
	        getGraphicsManager().getCurrentFigure().getCurrentAxes().set("NextPlot","add");
        else
            getGraphicsManager().getCurrentFigure().getCurrentAxes().set("NextPlot","replace");
            
        return null;
	}
}

/*
@GROUP
graphics
@SYNTAX
HOLD(figure)
@DOC
Holds a graph so that more graphs can be drawn over the top of it.
@EXAMPLES
<programlisting>
hold(1)
</programlisting>
@NOTES
@SEE
*/

