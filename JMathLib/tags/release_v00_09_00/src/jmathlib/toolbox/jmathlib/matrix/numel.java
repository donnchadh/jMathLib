package jmathlib.toolbox.jmathlib.matrix;

import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.DataToken;
import jmathlib.core.functions.ExternalFunction;

/**number of elements  */
public class numel extends ExternalFunction
{
	/**  */
	public OperandToken evaluate(Token[] operands)
	{

		// two operands (e.g. not(A) )
        if (getNArgIn(operands) != 1)
			throwMathLibException("numel: number of arguments != 1");
        
        if (!(operands[0] instanceof DataToken))
            throwMathLibException("numel: no data token");

        int x = ((DataToken)operands[0]).getSizeX();
        int y = ((DataToken)operands[0]).getSizeY();
        
		return new DoubleNumberToken(x*y);		

	} // end eval
}

/*
@GROUP
matrix
@SYNTAX
numel(array)
@DOC
returns the number of elements in array
@NOTES
@EXAMPLES
numel([2,5,3]) -> 3
@SEE
*/

