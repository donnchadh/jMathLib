package jmathlib.toolbox.jmathlib.graphics;

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

/**An external function for 2 dimensional plots*/
public class figure extends ExternalFunction
{

	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

		//ErrorLogger.debugLine("figure evaluate");

        if (operands==null) 
		{
			// figure called with no arguments
            globals.getGraphicsManager().createNewFigure();

		}
        else
        {
            
            if (getNArgIn(operands) == 0)
                globals.getGraphicsManager().createNewFigure();

            if (getNArgIn(operands) != 1)
			throwMathLibException("figure: number of arguments != 1");
            
	    	if (!(operands[0] instanceof DoubleNumberToken)) 
        		throwMathLibException("print: argument must be a number");

			int figureNumber  = (int)(((DoubleNumberToken)operands[0]).getReValues()[0][0]); 

			globals.getGraphicsManager().createNewFigure(figureNumber);
        }
		
        globals.getGraphicsManager().getCurrentFigure().repaint();
		

		return null;
	}
}

/*
@GROUP
graphics
@SYNTAX
figure(figure number)
figure()
@DOC
Creates a graphics window (figure)
@EXAMPLES
<programlisting>
figure(1)
</programlisting>
@SEE
plot, close
*/

