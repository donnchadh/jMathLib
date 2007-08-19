package jmathlib.toolbox.jmathlib.matrix;

import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.MatrixToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.Errors;

/**An external function to return the element at a certain point of a matrix*/
public class elementat extends ExternalFunction
{
	/**get  the element at a certain point of a matrix
	@param operands[0] = the matrix to sum
	@param operands[1] = the row no
	@param operands[2] = toe column no*/
	public OperandToken evaluate(Token[] operands)
	{
		OperandToken result = null;
		
		if (getNArgIn(operands) != 3)
			throwMathLibException("ElementAt: number of arguments != 3");
		
		int rowNo = ((int)((DoubleNumberToken)operands[1]).getValueRe());
		int colNo = ((int)((DoubleNumberToken)operands[2]).getValueRe());
		
		if(operands[0] instanceof DoubleNumberToken)
		{
			double[][] values = ((DoubleNumberToken)operands[0]).getReValues();
			result = new DoubleNumberToken(values[rowNo][colNo]);
		}
		else if(operands[0] instanceof MatrixToken)
		{
			OperandToken[][] values = ((MatrixToken)operands[0]).getValue();
			result = values[rowNo][colNo];
		}
		else
			Errors.throwMathLibException(ERR_INVALID_PARAMETER, new Object[] {"DoubleNumberToken or MatrixToken", operands[0].getClass().getName()});
		
		return result;
	}
}

/*
@GROUP
matrix
@SYNTAX
element = ELEMENTAT(matrix, rowno, colno)
@DOC
Returns the values in the specified position of the matrix.
@NOTES
@EXAMPLES
ELEMENTAT([1,2;3,4],0,0) = 1
ELEMENTAT([1,2;3,4],1,1) = 4
@SEE

*/

