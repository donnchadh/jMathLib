package jmathlib.toolbox.general;

import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.functions.ExternalFunction;

/**An external function for creating equaly spaced vectors*/
public class linspace extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{

        if (getNArgIn(operands) != 3)
   			throwMathLibException("linspace: number of arguments != 3");

		if (   (!(operands[0] instanceof DoubleNumberToken))
			|| (!(operands[1] instanceof DoubleNumberToken))
		    || (!(operands[2] instanceof DoubleNumberToken)) ) 
   			throwMathLibException("linspace: wrong type of arguments");


		// get data from arguments
		double x1 =      ((DoubleNumberToken)operands[0]).getReValues()[0][0];
		double x2 =      ((DoubleNumberToken)operands[1]).getReValues()[0][0];		
		int    n  = (int)((DoubleNumberToken)operands[2]).getReValues()[0][0];

		double[][] values = new double[1][n];
		double     dx     = (x2-x1) / (double)(n-1);
		for (int i=0; i<n ; i++)
		{
			values[0][i] = x1 +  ((double)i) * dx;
		}
		
		return new DoubleNumberToken(values);		
	}
}

/*
@GROUP
general
@SYNTAX
matrix = linspace(start, end, no of values)
@DOC
Returns a matrix going from start to end with count elements.
@EXAMPLES
<programlisting>
linspace(0, 1, 11) = [0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1] 
linspace(0, 10 , 3) = [0, 5, 10]
</programlisting>
@NOTES
A similar result can be obtained by using the colon operator.
@SEE
*/
