package jmathlib.toolbox.jmathlib.matrix;

import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

/**An external function for computing the product of array ellements         */
public class prod extends ExternalFunction
{
	/**return a  matrix 
	* @param operands[0] = matrix 
	* @return scalar or vector                             */
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

		// one operand (e.g. prod(A) )
		if (operands == null)                         return null;
		if (operands.length != 1)                     return null;
		if (operands[0] == null)                      return null;
		if (!(operands[0] instanceof DoubleNumberToken))    return null;


		// get data from arguments
		double[][] a =      ((DoubleNumberToken)operands[0]).getReValues();

		int a_dy     = a.length;
        int a_dx     = a[0].length;	
        double[][] values = null;
        double temp = 1.0;
        
        if (a_dy==1)
        {
        	// only row vector
        	// e.g. prod([1,2,3])  -> 1*2*3=6
			values = new double[1][1];
			for (int xi=0; xi<a_dx ; xi++)
			{
				temp = temp * a[0][xi];
			}
			values[0][0] = temp;
        }
        else
        {
			// e.g. prod([1,2,3; 2,2,2]) -> [2,4,6]
        	// create matrix
			values = new double[1][a_dx];
			for (int xi=0; xi<a_dx ; xi++)
			{
				temp = 1.0;
				for (int yi=0; yi<a_dy ; yi++)
				{
					temp = temp * a[yi][xi];
				}
				values[0][xi] = temp;
			}	
					
        }
        return new DoubleNumberToken(values);
	} // end eval
}

/*
@GROUP
matrix
@SYNTAX
answer = PROD(matrix)
@DOC
Returns the products of the rows of a matrix.
@NOTES
@EXAMPLES
PROD([1,2;3,4]) = [3,8]
PROD([1,2,3;4,5,6;7,8,9]) = [28, 80, 162]
@SEE
pow2, cumprod
*/

