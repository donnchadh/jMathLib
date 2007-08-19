package jmathlib.toolbox.trigonometric;

import jmathlib.core.functions.ExternalElementWiseFunction;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;

public class acoth extends ExternalElementWiseFunction
{
    
    public acoth()
    {
        name = "acoth";
    }
    
    /**trigonometric functions -calculates the inverse hperbolic cotangent
     * @param  double value array
     * @return the result as a double array
     */
    public double[] evaluateValue(double[] arg)
    {
        DoubleNumberToken num       = new DoubleNumberToken();
        atanh       atanhFunc = new atanh();
        
        double[] temp   = num.divide(new double[]{1,0}, arg);
        
        double[] result = atanhFunc.evaluateValue(temp);
        
        return result;
    }

}

/*
@GROUP
trigonometric
@SYNTAX
acoth(value)
@DOC
calculates the inverse hperbolic cotangent
@NOTES
@EXAMPLES
<programlisting>
y = acoth(x)
</programlisting>
@SEE
sec, cot, csch, sech, coth
*/

