package jmathlib.toolbox.general;

import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

public class conj extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

        if (getNArgIn(operands) != 1 )
			throwMathLibException("conj: number of arguments !=1");
        
        if (!(operands[0] instanceof DoubleNumberToken))
            throwMathLibException("conj: only works on numbers");

		// create conjugate
		DoubleNumberToken conj     = (DoubleNumberToken)((DoubleNumberToken)operands[0]).conjugate();
        
        return conj;
        
	} // end eval
}

/*
@GROUP
general
@SYNTAX
conj(complex)
@DOC
Returns the conjugate of a complex number.
@EXAMPLES
<programlisting>
conj(5) = 5
conj(2 + i)  = 2 - i
</programlisting>
@SEE
imag, real, angle, abs
*/

