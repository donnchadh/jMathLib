package jmathlib.toolbox.jmathlib.matrix;

import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.Errors;
import jmathlib.core.interpreter.GlobalValues;

/**An external function for determining number of non zero elements in a matrix*/
public class nnz extends ExternalFunction
{
	/**Calculate number of non zero elements
	@param operands[0] = the matrix
	@return the number of non zero elements*/
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{
		int count = 0;
        
        if (getNArgIn(operands) !=1)
            throwMathLibException("nnz: number of arguments !=1");
		
		if(operands[0] instanceof DoubleNumberToken)
		{
			DoubleNumberToken matrix = ((DoubleNumberToken)operands[0]);
			int sizeX = matrix.getSizeX();
			int sizeY = matrix.getSizeY();
			double[][] values = matrix.getReValues();
			
			for(int yy = 0; yy < sizeY; yy++)
			{
				for(int xx = 0; xx < sizeX; xx++)
				{
					if(values[yy][xx] != 0)
						count++;
				}
			}
		}
		else
			Errors.throwMathLibException(ERR_INVALID_PARAMETER, new Object[] {"DoubleNumberToken", operands[0].getClass().getName()});

		return new DoubleNumberToken(count);
	}
}

/*
@GROUP
matrix
@SYNTAX
answer=NNZ(matrix)
@DOC
Calculates the number of non zero elements within a matrix.
@NOTES
@EXAMPLES
<programlisting>
NNZ([1,0,2;0,0,5]) = 3
</programlisting>
@SEE
*/

