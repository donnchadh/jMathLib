package jmathlib.toolbox.general;

/* This file is part or JMathLib 
 * author:  2005/xx/xx   
 * */

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;


public class ndims extends ExternalFunction
{
	/**returns the dimensions of an array 1,2,3,4-dimensional 

	*/
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

		if (getNArgIn(operands)!=1)
			throwMathLibException("ndims: number of input arguments != 1");

		if ( !(operands[0] instanceof DoubleNumberToken))
			throwMathLibException("ndims: works only on numbers");

        // get size vector -> length of size vector is number of dimensions
        int n = ((DoubleNumberToken)operands[0]).getSize().length;
        
        return new DoubleNumberToken(n);
        
	} // end eval
}


/*
@GROUP
General
@SYNTAX
answer = ndims(value)
@DOC
Returns the sign of value.
@EXAMPLES
<programlisting>
a=rand(5,5);
ndims(a) -> 2

a=rand(5,5,5)
ndims(a) -> 3
</programlisting>
@NOTES
.
@SEE
template
*/
