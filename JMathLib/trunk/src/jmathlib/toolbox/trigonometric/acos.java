package jmathlib.toolbox.trigonometric;

import jmathlib.core.functions.ExternalElementWiseFunction;
import jmathlib.toolbox.jmathlib.matrix.log;
import jmathlib.toolbox.jmathlib.matrix.sqrt;

public class acos extends ExternalElementWiseFunction
{
    
    public acos()
    {
        name = "acos";
    }
    
    /**Calculates the arccosine of a complex number
     * @param arg = the value as an array of double
     * @return the result as an array of double
     */ 
    public double[] evaluateValue(double[] arg)
    {
        double result[] = new double[2];
        double _re1, _im1;

        double re =  arg[REAL];
        double im =  arg[IMAG];
               
        // _1:      one - z^2 ...
        result[REAL]  =  1.0 - ( (re*re) - (im*im) );
        result[IMAG]  =  0.0 - ( (re*im) + (im*re) );

        // result:  _1.Sqrt() ...
        sqrt sqrtFunc = new sqrt();
        result = sqrtFunc.evaluateValue(result);

        // _1:      i * result ...
        _re1 =  - result[IMAG];
        _im1 =  + result[REAL];

        // result:  z +_1  ...
        result[REAL]  =  re + _re1;
        result[IMAG]  =  im + _im1;

        // _1:      result.log()
        log logFunc = new log();
        result = logFunc.evaluateValue(result);

        // result:  -i * _1 ...
        double temp  = result[IMAG];
        result[IMAG] = -result[REAL];
        result[REAL] = temp;
        
        return result;
    }

}

/*
@GROUP
trigonometric
@SYNTAX
angle = ACOS(value)
@DOC
Returns the arc cosine of value.
@EXAMPLES
ACOS(1) = 0
ACOS(0) = 1.5707963267948966
@SEE
cos, acosh, sin, asin, asinh
*/

