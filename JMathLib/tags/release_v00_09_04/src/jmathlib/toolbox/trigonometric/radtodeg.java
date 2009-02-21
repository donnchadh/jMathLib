package jmathlib.toolbox.trigonometric;

import jmathlib.core.functions.ExternalElementWiseFunction;

public class radtodeg extends ExternalElementWiseFunction
{
    
    public radtodeg()
    {
        name = "radtodeg";
    }
    
    /**converts value from radians to degrees
    @param operands[0] = value to convert
    @return the converted value*/
    public double[] evaluateValue(double[] arg)
    {
        double[] result = new double[2];
        
        result[REAL] = arg[REAL]  * 180 / Math.PI;
        
        return result;
    }

}

/*
@GROUP
trigonometric
@SYNTAX
degrees = radtodeg(radians)
@DOC
converts the angle from radians to degrees.
@NOTES
@EXAMPLES
radtodeg(3.141592653589793)  = 180
radtodeg(1.5707963267948966) = 90
@SEE
gradtodeg, degtograd, radtograd, degtorad, radtodeg
*/

