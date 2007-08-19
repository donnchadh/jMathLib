package jmathlib.toolbox.jmathlib.matrix;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;

/**An external function for computing XOr of two matrices        */
public class xor extends ExternalFunction
{
	/**return a  matrix 
	* @param operands[0] = matrix 
	* @param operands[1] = matrix 
	* @return matrix  XOr of each element of the first and second argument  */
	public OperandToken evaluate(Token[] operands)
	{

		// two operands (e.g. xor(A,B) )
		if (operands == null)                         return null;
		if (operands.length != 2)                     return null;
		if (operands[0] == null)                      return null;
		if (operands[1] == null)                      return null;
		if (!(operands[0] instanceof DoubleNumberToken))    return null;
		if (!(operands[1] instanceof DoubleNumberToken))    return null;


		// get data from arguments
		double[][] a =      ((DoubleNumberToken)operands[0]).getReValues();
		int a_dy     = a.length;
        int a_dx     = a[0].length;	

		double[][] b =      ((DoubleNumberToken)operands[1]).getReValues();
		int b_dy     = b.length;
        int b_dx     = b[0].length;	
	
		// both matrices must have the same size
		if ( (a_dy != b_dy) || (a_dx != b_dx) ) return null; 


		// create matrix
		double[][] values = new double[a_dy][a_dx];
		for (int xi=0; xi<a_dx ; xi++)
		{
			for (int yi=0; yi<a_dy ; yi++)
			{
				if (   ((a[yi][xi] != 0.0) && (b[yi][xi] != 0.0))
				    || ((a[yi][xi] == 0.0) && (b[yi][xi] == 0.0)) )
				{
					values[yi][xi] = 0.0;
				}
				else
				{
					values[yi][xi] = 1.0;
				}
			}

		}	
		return new DoubleNumberToken(values);		

	} // end eval
}

/*
@GROUP
matrix
@SYNTAX
answer = xor(matrix1, matrix2)
@DOC
Returns the boolean xor of the elements of two matrices.
@NOTES
@EXAMPLES
xor([1,0;0,1], [1,1;0,0]) = [0,1;0,1]
@SEE
and, or
*/

