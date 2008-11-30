package jmathlib.toolbox.jmathlib.matrix;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;

/**An external function for reshaping matrices                   *
*  (e.g. reshape([1,2;3,4;5,6],2,3) return [1,5,4;3,2,6])        *
*  The original matrix is read column for column and rearranged  *
*  to a new dimension                                            */
public class reshape extends ExternalFunction
{
	/**return a  matrix 
	@param operands[0] = matrix to reshape
	@param operands[1] = number of rows
	@param operands[2] = number of columns */
	public OperandToken evaluate(Token[] operands)
	{

		// three operands (e.g. reshape(A,n,m) )
		if (operands == null)                             return null;

		if (operands.length != 3)                         return null;

		if (   (operands[0] == null)       
			|| (operands[1] == null)
			|| (operands[2] == null) )                    return null;

		if (   (!(operands[0] instanceof DoubleNumberToken))
			|| (!(operands[1] instanceof DoubleNumberToken))
			|| (!(operands[2] instanceof DoubleNumberToken))  ) return null;


		// get data from arguments
		double[][] x =      ((DoubleNumberToken)operands[0]).getReValues();
		int        n = (int)((DoubleNumberToken)operands[1]).getReValues()[0][0];		
		int        m = (int)((DoubleNumberToken)operands[2]).getReValues()[0][0];

		int x_dy     = x.length;
        int x_dx     = x[0].length;	
	
		// size(x) == n*m
		if ((x_dy * x_dx) != (n*m))
		{
			jmathlib.core.interpreter.ErrorLogger.debugLine("reshape: eval: dimension don't fit");
			return null;
		}
	

		// create matrix
		double[][] values = new double[n][m];
		int yii=0;
		int xii=0;
		for (int xi=0; xi<m ; xi++)
		{
			for (int yi=0; yi<n ; yi++)
			{
				// reshape
				values[yi][xi] = x[yii][xii];
	
				// read original matrix columnwise
				yii++;
				if (yii >= x_dy) 
				{
					yii=0;
					xii++;
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
reshape(matrix to reshape, no of rows, no of columns)
@DOC
Reshapes a matrix.
@NOTES
@EXAMPLES
reshape([1,2,3;4,5,6;7,8,9], 2, 2,) = [1,2;4,5]
@SEE
repmat

*/

