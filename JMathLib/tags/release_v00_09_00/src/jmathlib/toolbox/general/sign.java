package jmathlib.toolbox.general;

import jmathlib.core.functions.ExternalElementWiseFunction;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.toolbox.jmathlib.matrix.abs;

/**External function to calculate sign of a number
for a matrix it returns a matrix of equal size
for a complex number x sign = x/abs x
*/
public class sign extends ExternalElementWiseFunction
{
	/**Calculate the sign
	@param operands[0] = value to calculate sign of
	@return a matrix of the same size as the operands*/
    public double[] evaluateValue(double[] arg)
	{
        double[] result = new double[2];

        if (arg[IMAG]==0)
        {
            result[REAL] = Math.abs(arg[REAL]);
            result[IMAG] = 0;
        }
        else
        {
            // for complex number sign is determined 
            // like sign(X) = X./abs(X)
            abs         absFunc = new abs();
            DoubleNumberToken num     = new DoubleNumberToken();

            result = absFunc.evaluateValue(arg);
            result = num.divide(arg, result);
        }

        return  result;                      
	}	
}

/*
@GROUP
general
@SYNTAX
sign(value)
@DOC
Returns the sign of value.
@EXAMPLES
<programlisting>
sign(-10)  = -1
sign(10)   = 1
sign(3+2i) = 0.832 + 0.555i 
</programlisting>
@NOTES
The sign of a complex number is calculated as sign(X) = X./abs(X)
@SEE
abs
*/

