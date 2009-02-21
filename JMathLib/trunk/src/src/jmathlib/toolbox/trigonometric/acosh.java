package jmathlib.toolbox.trigonometric;

import jmathlib.core.functions.ExternalElementWiseFunction;
import jmathlib.toolbox.jmathlib.matrix.log;
import jmathlib.toolbox.jmathlib.matrix.sqrt;

public class acosh extends ExternalElementWiseFunction
{
    
    public acosh()
    {
        name = "acosh";
    }
    
    /**Calculates the inverse hyperbolic cosine of a complex number
    @param arg = the angle as an array of double
    @return the result as an array of double*/ 
    public double[] evaluateValue(double[] arg)
    {
         double result[] = new double[2];
         //  acosh(z)  =  log(z + Sqrt(z*z - 1))
         double re = arg[REAL];
         double im = arg[IMAG];

         // _1:  z.Times(z).Minus(one) ...
         result[REAL]       =  ( (re*re) - (im*im) ) - 1.0;
         result[IMAG]  =  ( (re*im) + (im*re) ) - 0.0;

         // result:  _1.Sqrt() ...
         sqrt sqrtF = new sqrt();
         result = sqrtF.evaluateValue(result);

         // result:  z.Plus(result) ...
         result[REAL] =  re + result[REAL];         // !
         result[IMAG] =  im + result[IMAG];         // !

         // _1:  result.log() ...
         log logF = new log();
         result = logF.evaluateValue(result);

         // result:  _1 ...
         return  result;
     }

}

/*
@@GROUP
trigonometric
@SYNTAX
angle = ACOS(value)
@DOC
Returns the arc cosine of value.
@EXAMPLES
ACOS(1) = 0
ACOS(0) = 1.5707963267948966
@SEE
cos, acosh
*/

