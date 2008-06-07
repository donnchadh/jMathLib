package jmathlib.toolbox.trigonometric;

import jmathlib.core.functions.ExternalElementWiseFunction;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;

public class sec extends ExternalElementWiseFunction
{
    
    public sec()
    {
        name = "sec";
    }
    
    /**trigonometric functions - calculate the secans of this token
    @param  double value
    @return the result as an OperandToken
    */
    public double[] evaluateValue(double[] arg)
    {

        DoubleNumberToken num  = new DoubleNumberToken();
        cos         cosF = new cos();
        
        double[] temp = cosF.evaluateValue(arg);

        double[] result = num.divide(new double[]{1,0}, temp);
        
        return result;
    }


}

/*
@GROUP
trigonometric
@SYNTAX
sec(value)
@DOC
.
@NOTES
@EXAMPLES
.
@SEE
csc, cot, csch, sech, coth
*/

