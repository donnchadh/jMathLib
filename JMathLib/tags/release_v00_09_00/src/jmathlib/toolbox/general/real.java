package jmathlib.toolbox.general;

import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.functions.ExternalFunction;

public class real extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{

        if (getNArgIn(operands) != 1 )
			throwMathLibException("real: number of arguments !=1");
        
        if (!(operands[0] instanceof DoubleNumberToken))
            throwMathLibException("real: only works on numbers");


		// get data from arguments
		double[][] a_r     = ((DoubleNumberToken)operands[0]).getReValues();
        
        return new DoubleNumberToken(a_r);
        
	} // end eval
}

/*
@GROUP
general
@SYNTAX
real(complex)
@DOC
Returns the real part of a complex number
@EXAMPLES
<programlisting>
real(2) = 2
real(5 + 3i) = 5
</programlisting>
@SEE
imag, conj, angle, abs
*/


