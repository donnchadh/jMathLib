package jmathlib.toolbox.trigonometric;

import jmathlib.core.functions.ExternalElementWiseFunction;

public class gradtorad extends ExternalElementWiseFunction
{
    
    public gradtorad()
    {
        name = "gradtorad";
    }
    
    /**converts value from gradients to radians
    @param operands[0] = value to convert
    @return the converted value*/
    public double[] evaluateValue(double[] arg)
    {
        double[] result = new double[2];
        
        result[REAL] = arg[REAL] * Math.PI / 200;
        
        return result;
    }

}

/*
@GROUP
trigonometric
@SYNTAX
radians = gradtorad(gradients)
@DOC
converts the angle from gradients to radians.
@NOTES
@EXAMPLES
gradtorad(200) = 3.141592653589793
gradtorad(100) = 1.5707963267948966
@SEE
gradtodeg, degtograd, radtograd, degtorad, radtodeg
*/

