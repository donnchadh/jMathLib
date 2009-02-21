package jmathlib.toolbox.jmathlib.matrix;

import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.MatrixToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;
import jmathlib.toolbox.jmathlib.matrix._private.Jama.LUDecomposition;


   /** LU Decomposition.
   <P>
   For an m-by-n matrix A with m >= n, the LU decomposition is an m-by-n
   unit lower triangular matrix L, an n-by-n upper triangular matrix U,
   and a permutation vector piv of length m so that A(piv,:) = L*U.
   If m < n, then L is m-by-m and U is m-by-n.
   <P>
   The LU decompostion with pivoting always exists, even if the matrix is
   singular, so the constructor will never fail.  The primary use of the
   LU decomposition is in the solution of square systems of simultaneous
   linear equations.  This will fail if isNonsingular() returns false.
   <p>
   usage: [L,U]   = lu (A) <br>
          [L,U,P] = lu (A) <br>
          [L]     = lu (A) <br>
          x       = lu (A,B) as a solution to A*X = B;
   */

/**An external function for computing a LU decomposition of a matrix  */
public class lu extends ExternalFunction
{
	/**return a  matrix 
	* @param operands[0] = matrix 
	* @return matrix                                 */
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

		// one operand (e.g. eig(A) )
		if (operands == null)                         return null;
		if ((operands.length < 1) || 
            (operands.length > 2)    )                return null;
		if (operands[0] == null)                      return null;
		if (!(operands[0] instanceof DoubleNumberToken))    return null;


		// get data from arguments
		double[][] A =      ((DoubleNumberToken)operands[0]).getReValues();

		// create LUDecomposition object
		LUDecomposition luDecomp = new LUDecomposition( A );

		if (operands.length == 2)
		{
			//  solve A*x = B (e.g. x=lu(A,B) )

			// get right side of A*x = B from arguments
			double[][] B = ((DoubleNumberToken)operands[1]).getReValues();

			// solve equation
			double[][] x = luDecomp.solve(B);

			return new DoubleNumberToken(x);
		}

		// Check number of Arguments
		if (getNoOfLeftHandArguments()==3)
		{
			// 3 arguments on the left side (e.g. [L, U, P] = lu(A) )

			// get L matrix
			double[][] L = luDecomp.getL();

			// get U matrix
			double[][] U = luDecomp.getU();

			// get P vector
			double[][] P = luDecomp.getDoublePivotAsArray();

			OperandToken values[][] = new OperandToken[1][3];
			values[0][0] = new DoubleNumberToken(L);
			values[0][1] = new DoubleNumberToken(U);
			values[0][2] = new DoubleNumberToken(P);
			return new MatrixToken( values );

		}
		else if (getNoOfLeftHandArguments()==2)
		{
			// 2 arguments on the left side (e.g. [L, U] = lu(A) )

			// get L matrix
			double[][] L = luDecomp.getL();

			// get U matrix
			double[][] U = luDecomp.getU();

			OperandToken values[][] = new OperandToken[1][2];
			values[0][0] = new DoubleNumberToken(L);
			values[0][1] = new DoubleNumberToken(U);
			return new MatrixToken( values );

		}
		else
		{
			// 0 or 1 arguments on the left side 

			// get L matrix
			double[][] L = luDecomp.getL();

			return new DoubleNumberToken( L );
		}

	} // end eval
}

/*
@GROUP
matrix
@SYNTAX
   [L,U]   = lu (A) 
   [L,U,P] = lu (A) 
   [L]     = lu (A) 
   x       = lu (A,B) as a solution to A*X = B;
@DOC
Returns the LU decomposition of a matrix
@NOTES
   For an m-by-n matrix A with m &gt;= n, the LU decomposition is an m-by-n
   unit lower triangular matrix L, an n-by-n upper triangular matrix U,
   and a permutation vector piv of length m so that A(piv,:) = L*U.
   If m &lt; n, then L is m-by-m and U is m-by-n.
   
   The LU decompostion with pivoting always exists, even if the matrix is
   singular, so the constructor will never fail.  The primary use of the
   LU decomposition is in the solution of square systems of simultaneous
   linear equations.  This will fail if isNonsingular() returns false.
@EXAMPLES
@SEE
qr
*/

