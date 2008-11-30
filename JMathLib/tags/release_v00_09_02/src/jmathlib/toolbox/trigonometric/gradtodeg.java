package jmathlib.toolbox.trigonometric;

import jmathlib.core.functions.ExternalElementWiseFunction;

public class gradtodeg extends ExternalElementWiseFunction
{
    
    public gradtodeg()
    {
        name = "GradToDeg";
    }
    
    /**converts value from gradiants to degrees
    @param operads[0] = value to convert
    @return the converted value*/
    public double[] evaluateValue(double[] arg)
    {
        double[] result = new double[2];
        
        result[REAL] = arg[REAL] * 90 / 100;
        
        return result;
    }

}

/*
@GROUP
trigonometric
@SYNTAX
degrees = gradtodeg(gradients)
@DOC
converts the angle from gradients to degrees.
@NOTES
@EXAMPLES
gradtodeg(200) = 180
gradtodeg(100) = 100
@SEE
gradtodeg, degtograd, radtograd, degtorad, radtodeg
*/

