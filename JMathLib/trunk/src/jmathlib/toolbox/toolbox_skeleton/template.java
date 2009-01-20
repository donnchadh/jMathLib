package jmathlib.toolbox.toolbox_skeleton;

/* This file is part or JMathLib 
 * author:  stefan (stefan@held-mueller.de) 2009   
 * */

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;


/**An external function for computing a mesh of a matrix  */
public class template extends ExternalFunction
{
	/**returns two  matrices 
	* @param operands[0] = x values (e.g. [-2:0.2:2]) 
	* @param operands[1] = y values (e.g. [-2:0.2:2])
	* @return [X,Y] as matrices                                 */
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

		// one operand (e.g. [x,y]=template([-2:0.2:2],[-2:0.2:2]) )
		if (getNArgIn(operands)!=2)
			throwMathLibException("template: number of input arguments != 2");

		// Check number of return arguments
		if (getNoOfLeftHandArguments()!=2)
		    throwMathLibException("template: number of output arguments != 2");

		if ( !(operands[0] instanceof DoubleNumberToken) ||
             !(operands[0] instanceof DoubleNumberToken)   )
			throwMathLibException("template: works only on numbers");

		// get data from arguments
		double[][] x =  ((DoubleNumberToken)operands[0]).getReValues();
		double[][] y =  ((DoubleNumberToken)operands[1]).getReValues();

		if ((x.length != 1) ||
            (y.length != 1)    )
            throwMathLibException("template: works only row vectors");

		int sizeX = x[0].length;
        int sizeY = y[0].length;
        
        double[][] X = new double[sizeY][sizeX];
        double[][] Y = new double[sizeY][sizeX];
        
        for (int i=0; i<sizeY; i++)
        {
        	for (int j=0; j<sizeX; j++)
            {
            	X[i][j] = x[0][j];
                Y[i][j] = y[0][i];
            }
        } 

  		OperandToken values[][] = new OperandToken[1][2];
		values[0][0] = new DoubleNumberToken(X);
		values[0][1] = new DoubleNumberToken(Y);
		return new MatrixToken( values );

	} // end eval
}


/*
@GROUP
General
@SYNTAX
answer = template (value)
@DOC
Returns the sign of value.
@EXAMPLES
<programlisting>
sign(-10)=-1
sign(10)=1
</programlisting>
@NOTES
This functions is used as a template for developing toolbox functions.
@SEE
template
*/
