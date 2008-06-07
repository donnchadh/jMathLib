package jmathlib.toolbox.jmathlib.matrix;

import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.functions.ExternalFunction;

/**An external function that return 1.0 is all elements of the argument are non imaginary  */
public class isreal extends ExternalFunction
{
	/**return a  matrix 
	* @param operands[0] = matrix 
	* @return 1.0 if all elements of the argument are nonzero  */
	public OperandToken evaluate(Token[] operands)
	{

		// two operands (e.g. not(A) )
        if (getNArgIn(operands) != 1)
			throwMathLibException("isreal: number of arguments != 1");
        
        if (!(operands[0] instanceof DoubleNumberToken))
            throwMathLibException("isreal: works on numbers only");

        DoubleNumberToken num = (DoubleNumberToken)operands[0];
        boolean realB   = true;
	
        // check all elements
        // if at least one element is imaginary than return FALSE
        for (int n=0; n<num.getNumberOfElements() ; n++)
		{
				if ( num.getValueIm(n) != 0.0)
                    realB = false;
		}	
        
		if (realB)
		    return new DoubleNumberToken(1.0);		
		else
		    return new DoubleNumberToken(0.0);        

        
	} // end eval
}

/*
@GROUP
matrix
@SYNTAX
answer = isreal(matrix)
@DOC
Returns 1 if any of the elements of the supplied matrix are not zero.
@NOTES
@EXAMPLES
isreal([0,2i]) = 0
isreal([11,0]) = 1
isreal([])     = 1
@SEE
isnan, isimaginary
*/

