package jmathlib.toolbox.trigonometric;

import jmathlib.core.functions.ExternalElementWiseFunction;

public class degtorad extends ExternalElementWiseFunction
{
    
    public degtorad()
    {
        name = "degtorad";
    }
    
    /**converts value from degrees to radians
    @param operads[0] = value to convert
    @return the converted value*/
    public double[] evaluateValue(double[] arg)
    {
        double[] result = new double[2];
        
        result[REAL] = arg[REAL] * Math.PI / 180;
        
        return result;
    }

}

/*
@GROUP
trigonometric
@SYNTAX
radians = DEGTORAD(degrees)
@DOC
converts the angle from degrees to radians.
@NOTES
@EXAMPLES
DEGTORAD(180) = 3.141592653589793
DEGTORAD(90) = 1.5707963267948966
@SEE
gradtodeg, degtograd, radtograd, degtorad, radtodeg
*/

