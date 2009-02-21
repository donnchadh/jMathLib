package jmathlib.toolbox.trigonometric;

import jmathlib.core.functions.ExternalElementWiseFunction;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;

public class acsc extends ExternalElementWiseFunction
{
    
    public acsc()
    {
        name = "acsc";
    }
    
    /**trigonometric functions - calculates the inverse cosecans
    @param  double value array
    @return the result as a double array
    */
    public double[] evaluateValue(double[] arg)
    {
        DoubleNumberToken num      = new DoubleNumberToken();
        asin        asinFunc = new asin();
        
        double[] temp = num.divide(new double[]{1,0}, arg);
        
        double[] result = asinFunc.evaluateValue(temp);
        
        return result;
    }

}

/*
@GROUP
trigonometric
@SYNTAX
acsc(value)
@DOC
calculates the inverse cosecans
@NOTES
@EXAMPLES
.
@SEE
sec, cot, csch, sech, coth
*/

