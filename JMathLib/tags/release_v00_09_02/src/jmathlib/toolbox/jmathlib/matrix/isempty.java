package jmathlib.toolbox.jmathlib.matrix;

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;

/**An external function for checking if a matrix is empty (no number or string)  */
public class isempty extends ExternalFunction
{
	/**return a  matrix 
	* @param  operands[0] = matrix 
	* @return position of nonzero elements  */
	public OperandToken evaluate(Token[] operands)
	{

		if (getNArgIn(operands) != 1)
			throwMathLibException("isEmpty: number of arguments != 1");

		if ((operands[0] instanceof DoubleNumberToken) || 
            (operands[0] instanceof CharToken)   )   
		{
			// check for something like a=[]
            if ( (((DataToken)operands[0]).getSizeY()==0) &&
                 (((DataToken)operands[0]).getSizeX()==0)  )
                return new DoubleNumberToken(1.0);
            
            // token is not empty
			return new DoubleNumberToken(0.0);		
		}
		else
		{
			// token is empty
            // empty means: the token has no number or is no string
			return new DoubleNumberToken(1.0);		
		}

	} // end eval
}

/*
@GROUP
matrix
@SYNTAX
answer=ISEMPTY(value)
@DOC
Checks if a matrix is empty (no number or string)
@NOTES
@EXAMPLES
<programlisting>
isempty([]) -> 1

a=[]
isempty(a) -> 1
</programlisting>
@SEE
isfinite, isimaginary, isreal, isnan, isinf
*/

