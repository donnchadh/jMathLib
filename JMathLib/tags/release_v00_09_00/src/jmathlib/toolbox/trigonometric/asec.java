package jmathlib.toolbox.trigonometric;

import jmathlib.core.functions.ExternalElementWiseFunction;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;

public class asec extends ExternalElementWiseFunction
{
    
    public asec()
    {
        name = "asec";
    }
    
    /**trigonometric functions -calculates the inverse secant
     * @param  double value array
     * @return the result as a double array
     */
    public double[] evaluateValue(double[] arg)
    {
        DoubleNumberToken num      = new DoubleNumberToken();
        acos        acosFunc = new acos();
        
        double[] temp   = num.divide(new double[]{1,0}, arg);
        
        double[] result = acosFunc.evaluateValue(temp);
        
        return result;
    }

}

/*
@GROUP
trigonometric
@SYNTAX
asec(value)
@DOC
calculates the inverse secant
@NOTES
@EXAMPLES
<programlisting>
y = asec(x)
</programlisting>
@SEE
sec, cot, csch, sech, coth
*/

