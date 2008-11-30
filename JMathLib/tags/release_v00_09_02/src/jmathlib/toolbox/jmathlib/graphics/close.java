package jmathlib.toolbox.jmathlib.graphics;

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;

/**An external function for closing figures*/
public class close extends ExternalFunction
{

	public OperandToken evaluate(Token[] operands)
	{

		//ErrorLogger.debugLine("figure evaluate");

        if (operands==null) 
		{
			
			int i = getGraphicsManager().getCurrentFigureNumber();
            getGraphicsManager().removeFigure(i);

		}
        else
        {
	        if (getNArgIn(operands) != 1)
				throwMathLibException("close: number of arguments != 1");
            
	    	if (!(operands[0] instanceof DoubleNumberToken)) 
        		throwMathLibException("close: argument must be a number");

			int i  = (int)(((DoubleNumberToken)operands[0]).getReValues()[0][0]); 

            getGraphicsManager().removeFigure(i);
        }
		
		getGraphicsManager().getCurrentFigure().repaint();
		

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

