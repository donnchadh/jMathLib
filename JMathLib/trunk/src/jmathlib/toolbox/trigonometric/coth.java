package jmathlib.toolbox.trigonometric;

import jmathlib.core.functions.ExternalElementWiseFunction;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;

public class coth extends ExternalElementWiseFunction
{
    
    public coth()
    {
        name = "coth";
    }
    
    /**trigonometric functions - calculate the hyperbolic cotangent of this token
     * @param  double value
     * @return the result as a double array
     */
    public double[] evaluateValue(double[] arg)
    {
        DoubleNumberToken num   = new DoubleNumberToken();
        tanh        tanhF = new tanh();
        
        double[] temp = tanhF.evaluateValue(arg);
        
        double[] result = num.divide(new double[]{1,0}, temp);
        
        return result;
    }

}

/*
@GROUP
trigonometric
@SYNTAX
coth(value)
@DOC
.
@NOTES
@EXAMPLES
.
@SEE
sec, csc, csch, sech, cot
*/

