package jmathlib.toolbox.jmathlib.graphics;

import jmathlib.core.tokens.*;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

public class clf extends ExternalFunction
{

	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{
        
        if (getNArgIn(operands) >1)
			throwMathLibException("clf: number of arguments <=1");

        // get figure number
        double n = globals.getGraphicsManager().getCurrentFigureNumber();
        
        // in case n>1 there is no current figure, -> create one
        if (n<1)
        {
            // create figure
            globals.getGraphicsManager().createNewFigure();

            // get figure number again (should be at least "1")
            n = globals.getGraphicsManager().getCurrentFigureNumber();
        }
        
        globals.getGraphicsManager().getCurrentFigure().clearFigure();
        
		return null;
	}
}

/*
@GROUP
graphics
@SYNTAX
clf
@DOC
clear current figure
@EXAMPLES
.
@NOTES
@SEE
gca, gco, gcbo, gcbf, cla
*/

