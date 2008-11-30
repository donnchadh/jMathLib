package jmathlib.toolbox.trigonometric;

import jmathlib.core.functions.ExternalElementWiseFunction;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;

public class csch extends ExternalElementWiseFunction
{
    
    public csch()
    {
        name = "csch";
    }
    
    /**trigonometric functions - calculate the cosecant of this token
     * @param  double value
     * @return the result as a double array
     */
    public double[] evaluateValue(double[] arg)
    {
        DoubleNumberToken num   = new DoubleNumberToken();
        sinh        sinhF = new sinh();
        
        double[] temp = sinhF.evaluateValue(arg);
        
        double[] result = num.divide(new double[]{1,0}, temp);
        
        return result;
    }


}

/*
@GROUP
trigonometric
@SYNTAX
cscd(value)
@DOC
.
@NOTES
@EXAMPLES
.
@SEE
sec, cot, csch, sech, coth
*/

