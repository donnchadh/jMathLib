package jmathlib.toolbox.jmathlib.graphics;

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

/**An external function for closing figures*/
public class close extends ExternalFunction
{

	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

		//ErrorLogger.debugLine("figure evaluate");

        if (operands==null) 
		{
			
			int i = globals.getGraphicsManager().getCurrentFigureNumber();
			globals.getGraphicsManager().removeFigure(i);

		}
        else
        {
	        if (getNArgIn(operands) != 1)
				throwMathLibException("close: number of arguments != 1");
            
	    	if (!(operands[0] instanceof DoubleNumberToken)) 
        		throwMathLibException("close: argument must be a number");

			int i  = (int)(((DoubleNumberToken)operands[0]).getReValues()[0][0]); 

			globals.getGraphicsManager().removeFigure(i);
        }
		
        globals.getGraphicsManager().getCurrentFigure().repaint();
		

		return null;
	}
}

/*
@GROUP
graphics
@SYNTAX
close(figure number)
close()
@DOC
Creates a graphics window (figure)
@EXAMPLES
<programlisting>
close(1)
</programlisting>
@SEE
figure, plot
*/

