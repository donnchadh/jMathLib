package jmathlib.toolbox.trigonometric;

import jmathlib.core.functions.ExternalElementWiseFunction;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;

public class sech extends ExternalElementWiseFunction
{
    
    public sech()
    {
        name = "sech";
    }
    
    /**trigonometric functions - calculate the hyperbolic secant of this token
    @param  double value
    @return the result as an OperandToken
    */
    public double[] evaluateValue(double[] arg)
    {

        DoubleNumberToken num   = new DoubleNumberToken();
        cosh        coshF = new cosh();
        
        double[] temp = coshF.evaluateValue(arg);

        double[] result = num.divide(new double[]{1,0}, temp);
        
        return result;
    }
}

/*
@GROUP
trigonometric
@SYNTAX
sech(value)
@DOC
.
@NOTES
@EXAMPLES
.
@SEE
csc, cot, csch, sec, coth
*/

