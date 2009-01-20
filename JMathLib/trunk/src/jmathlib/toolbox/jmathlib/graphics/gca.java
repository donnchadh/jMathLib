package jmathlib.toolbox.jmathlib.graphics;

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

public class gca extends ExternalFunction
{

	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{
        
        if (getNArgIn(operands) != 0)
			throwMathLibException("gca: number of arguments != 0");

        // get handle to axes
        double n = globals.getGraphicsManager().getCurrentFigure().getCurrentAxes().getHandle();
        
        
		return new DoubleNumberToken(n);
	}
}

/*
@GROUP
graphics
@SYNTAX
gca
@DOC
get handle to current axis
@EXAMPLES
.
@NOTES
@SEE
gcf, gco, gcbo, gcbf
*/

