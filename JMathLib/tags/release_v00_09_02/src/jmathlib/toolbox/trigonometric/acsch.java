package jmathlib.toolbox.trigonometric;

import jmathlib.core.functions.ExternalElementWiseFunction;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;

public class acsch extends ExternalElementWiseFunction
{
    
    public acsch()
    {
        name = "acsch";
    }
    
    /**trigonometric functions -calculates the inverse hperbolic cosecant
     * @param  double value array
     * @return the result as a double array
     */
    public double[] evaluateValue(double[] arg)
    {
        DoubleNumberToken num       = new DoubleNumberToken();
        asinh       asinhFunc = new asinh();
        
        double[] temp   = num.divide(new double[]{1,0}, arg);
        
        double[] result = asinhFunc.evaluateValue(temp);
        
        return result;
    }

}

/*
@GROUP
trigonometric
@SYNTAX
acsch(value)
@DOC
calculates the inverse hperbolic cosecant
@NOTES
@EXAMPLES
<programlisting>
y = acsch(x)
</programlisting>
@SEE
sec, cot, csch, sech, coth
*/

