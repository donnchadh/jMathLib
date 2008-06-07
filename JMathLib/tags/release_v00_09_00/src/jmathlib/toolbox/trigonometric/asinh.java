package jmathlib.toolbox.trigonometric;

import jmathlib.core.functions.ExternalElementWiseFunction;
import jmathlib.toolbox.jmathlib.matrix.log;
import jmathlib.toolbox.jmathlib.matrix.sqrt;

public class asinh extends ExternalElementWiseFunction
{
    
    public asinh()
    {
        name = "asinh";
    }
    
    /**Calculates the inverse hyperbolic sine of a complex number
    @param arg = the value as an array of double
    @return the result as an array of double*/ 
    public double[] evaluateValue(double[] arg)
    {

         double result[] = new double[2];
         //  asinh(z)  =  log(z + Sqrt(z*z + 1))
         double re = arg[REAL];
         double im = arg[IMAG];
         // _1:      z.Times(z).Plus(one) ...
         result[REAL] =  ( (re*re) - (im*im) ) + 1.0;
         result[IMAG] =  ( (re*im) + (im*re) );

         // result:  _1.Sqrt() ...
         sqrt sqrtFunc = new sqrt();
         result = sqrtFunc.evaluateValue(result);

         // result:  z.Plus(result) ...
         result[REAL] =  re + result[REAL];                                       // !
         result[IMAG] =  im + result[IMAG];                                       // !

         // _1:      result.log() ...
         log logFunc = new log();
         result = logFunc.evaluateValue(result);
                 
         return  result;
     }

}

/*
@GROUP
trigonometric
@SYNTAX
angle = asinh(value);
@DOC
Returns the arc hyperbolic sine
@EXAMPLES
<programlisting>
asinh(1) = 0.8813735870195429
asinh(0) = 0
</programlisting>
@SEE
sinh, asin, cosh, acos
*/

