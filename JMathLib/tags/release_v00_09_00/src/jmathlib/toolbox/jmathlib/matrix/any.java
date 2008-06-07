package jmathlib.toolbox.jmathlib.matrix;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.LogicalToken;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;

/**An external function that return 1.0 is any element of the argument is nonzero  */
public class any extends ExternalFunction
{
	/**return a  matrix 
	* @param operands[0] = matrix 
	* @return 1.0 if any element of the argument is nonzero  */
	public OperandToken evaluate(Token[] operands)
	{

		// two operands (e.g. not(A) )
        if (getNArgIn(operands) != 1)
			throwMathLibException("any: number of arguments != 1");

        // if boolean: convert LogicalToken to DoubleNumberToken
        if (operands[0] instanceof LogicalToken)
            operands[0]=((LogicalToken)operands[0]).getDoubleNumberToken(); 
        
        
        if (operands[0] instanceof DoubleNumberToken)
        {
            DoubleNumberToken num = (DoubleNumberToken)operands[0];
        
        	for (int i=0; i<num.getNumberOfElements(); i++)
        	{
    			if (num.getValueRe(i) != 0.0) 
                    return new LogicalToken(true);
        	}	
        	return new LogicalToken(false);		
        }

        throwMathLibException("any: works on numbers and booleans only");
        return null;
        
	} // end eval
}

/*
@GROUP
matrix
@SYNTAX
answer = any(matrix)
@DOC
Returns 1 if any of the elements of the supplied matrix are not zero.
@NOTES
@EXAMPLES
<programlisting>
any([0,0;0,0]) = 0
any([1,0;0,0]) = 1
</programlisting>
@SEE
and, eye, all
*/

