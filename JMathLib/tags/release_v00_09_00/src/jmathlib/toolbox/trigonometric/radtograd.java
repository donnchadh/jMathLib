package jmathlib.toolbox.trigonometric;

import jmathlib.core.functions.ExternalElementWiseFunction;

public class radtograd extends ExternalElementWiseFunction
{
    
    public radtograd()
    {
        name = "radtograd";
    }
    
    /**converts value from radians to gradients
    @param operads[0] = value to convert
    @return the converted value*/
    public double[] evaluateValue(double[] arg)
    {
        double[] result = new double[2];
        
        result[REAL] = arg[REAL]  * 200 / Math.PI;
        
        return result;
    }

}

/*
@GROUP
trigonometric
@SYNTAX
gradients = radtograd(radians)
@DOC
converts the angle from radians to gradients.
@NOTES
@EXAMPLES
radtograd(3.141592653589793) = 200
radtograd(1.570796326794896) = 100
@SEE
degtograd, radtograd, degtorad, radtodeg
*/

