package jmathlib.toolbox.jmathlib.matrix;

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;
import jmathlib.toolbox.jmathlib.matrix._private.Jama.EigenvalueDecomposition;

/**An external function for computing eigenvalues and eigenvectors of an array  */
public class eig extends ExternalFunction
{
	/**return a  matrix 
	* @param operands[0] = matrix 
	* @return matrix                                 */
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

		// one operand (e.g. eig(A) )
		if (getNArgIn(operands) != 1)
			throwMathLibException("eig: number of arguments != 1");

		if (!(operands[0] instanceof DoubleNumberToken))
			  throwMathLibException("eig: works only on numbers");

		// get data from arguments
		double[][] A =      ((DoubleNumberToken)operands[0]).getReValues();

		EigenvalueDecomposition eigDecomp = new EigenvalueDecomposition( A );


		if (getNArgOut()==2)
		{
			// 2 arguments on the left side

			// get eigen vectors
			double[][] eigenVectors = eigDecomp.getV();

			// get eigen values block matrix
			double[][] eigenValues = eigDecomp.getD();

			//DoubleNumberToken	 values = {new DoubleNumberToken(eigenVectors),
			//                          new DoubleNumberToken(eigenValues)  };

			OperandToken values[][] = new OperandToken[1][2];
			values[0][0] = new DoubleNumberToken(eigenVectors);
			values[0][1] = new DoubleNumberToken(eigenValues);
			return new MatrixToken( values );

		}
		else
		{
			// 0 or 1 arguments on the left side 

			// get eigenvalues
			double[] eigRealValues = eigDecomp.getRealEigenvalues();
			double[] eigImagValues = eigDecomp.getImagEigenvalues();

			int n = eigRealValues.length;
			double[][] realValues = new double[n][1];
			double[][] imagValues = new double[n][1];

			for (int yi=0; yi<n ; yi++)
			{
				realValues[yi][0] = eigRealValues[yi];
				imagValues[yi][0] = eigImagValues[yi];
			}

			return new DoubleNumberToken(realValues, imagValues);
		}

	} // end eval
}

/*
@GROUP
matrix
@SYNTAX
eig(matrix)
@DOC
Caculates the eigenvalues of the matrix.
@NOTES
@EXAMPLES
<programlisting>
eig([1,2;3,4])=[-0.372;5.372]
</programlisting>
@SEE
*/

