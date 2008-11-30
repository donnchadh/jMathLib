package jmathlib.toolbox.general;

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;

public class imag extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{

        if (getNArgIn(operands) != 1 )
			throwMathLibException("imag: number of arguments !=1");
        
        if (!(operands[0] instanceof DoubleNumberToken))
            throwMathLibException("imag: only works on numbers");

		// get data from arguments
		double[][] a_i     = ((DoubleNumberToken)operands[0]).getValuesIm();
        
        return new DoubleNumberToken(a_i);
        
	} // end eval
}

/*
@GROUP
general
@SYNTAX
imag(complex)
@DOC
Returns the imaginary part of a complex number
@EXAMPLES
<programlisting>
imag(2i) = 2
imag(5 + 3i) = 3
</programlisting>
@SEE
real, conj, angle, abs
*/

