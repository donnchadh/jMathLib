package jmathlib.toolbox.jmathlib.matrix;

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;
import jmathlib.toolbox.jmathlib.matrix._private.Jama.CholeskyDecomposition;


/**An external function for computing the cholewsky decomposition of an array  */
public class chol extends ExternalFunction
{
	/**return a  matrix 
	* @param operands[0] = matrix 
	* @return matrix                                 */
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

		// one operand (e.g. chol(A) )
		if (getNArgIn(operands) != 1)
			throwMathLibException("chol: number of arguments != 1");
        
        if (!(operands[0] instanceof DoubleNumberToken))    return null;


		// get data from arguments
		double[][] A =      ((DoubleNumberToken)operands[0]).getReValues();

		CholeskyDecomposition cholDecomp = new CholeskyDecomposition( A );

		double[][] L = cholDecomp.getL();

		return new DoubleNumberToken( L );		

	} // end eval
}

/*
@GROUP
matrix
@SYNTAX
answer=CHOL(matrix)
@DOC
Calculates the Cholewsky decomposition of matrix
@NOTES
@EXAMPLES
CHOL([2,3;3,5])=[1.414,0;2.121,0.707]
@SEE
*/

