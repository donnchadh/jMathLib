package jmathlib.toolbox.trigonometric;

import jmathlib.core.functions.ExternalElementWiseFunction;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;

public class tanh extends ExternalElementWiseFunction
{
    
    public tanh()
    {
        name = "tanh";
    }
    
    /**Calculates the hyperbolic tangent of a complex number
     * @param arg = the angle as an array of double
     * @return the result as an array of double
     */ 
    public double[] evaluateValue(double[] arg)
    {
        sinh sinhF = new sinh();
        cosh coshF = new cosh();
        
        double[] temp1 = sinhF.evaluateValue(arg);
        double[] temp2 = coshF.evaluateValue(arg);
        
        DoubleNumberToken  num= null;
        
        return num.divide(temp1, temp2);
    }
    
}

/*
@GROUP
trigonometric
@SYNTAX
tanh(angle)
@DOC
Returns the hyperbolic tangent of angle.
@EXAMPLES
<programlisting>
tanh(0) = 0
tanh(1) = 0.76159
</programlisting>
@SEE
tan, atanh, cos, sin
*/

