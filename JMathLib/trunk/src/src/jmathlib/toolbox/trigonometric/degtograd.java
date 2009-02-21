package jmathlib.toolbox.trigonometric;

import jmathlib.core.functions.ExternalElementWiseFunction;

public class degtograd extends ExternalElementWiseFunction
{
    
    public degtograd()
    {
        name = "degtograd";
    }
    
	/**converts value from degrees to gradients
	if the parameter is a matrix then all elements get converted
	@param operads[0] = value to convert
	@return the converted value*/
    public double[] evaluateValue(double[] arg)
    {
        double[] result = new double[2];
        
        result[REAL] = arg[REAL] * 100 / 90;
        
		return result;
	}

}

/*
@GROUP
trigonometric
@SYNTAX
gradients = degtograd(degrees)
@DOC
converts the angle from degrees to gradients.
@NOTES
@EXAMPLES
degtograd(180) = 200
degtograd(90) = 100
@SEE
gradtodeg, degtograd, radtograd, degtorad, radtodeg
*/

