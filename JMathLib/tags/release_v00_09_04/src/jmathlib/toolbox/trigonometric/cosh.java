package jmathlib.toolbox.trigonometric;

import jmathlib.core.functions.ExternalElementWiseFunction;;

public class cosh extends ExternalElementWiseFunction
{
    
    public cosh()
    {
        name = "cosh";
    }
    
    /**Calculates the hyperbolic cosine of a complex number
     * @param arg = the angle as an array of double
     * @return the result as an array of double
     */ 
    public double[] evaluateValue(double[] arg)
    {
        double result[] = new double[2];
        double scalar;
        double _re1, _im1;
        double _re2, _im2;

        // _1:      z.exp() ...
        scalar =  Math.exp(arg[REAL]);
        _re1 =  scalar * Math.cos(arg[IMAG]);
        _im1 =  scalar * Math.sin(arg[IMAG]);

        // _2:      z.neg().exp() ...
        scalar =  Math.exp(-arg[REAL]);
        _re2 =  scalar * Math.cos(-arg[IMAG]);
        _im2 =  scalar * Math.sin(-arg[IMAG]);

        // _1:  _1.Plus(_2) ...
        _re1 = _re1 + _re2;                    // !!!
        _im1 = _im1 + _im2;                    // !!!

        // result:  _1.scale(0.5) ...
        result[REAL] = 0.5 * _re1;
        result[IMAG] = 0.5 * _im1;
         
        return result;
    }

}

/*
@GROUP
trigonometric
@SYNTAX
cosh(angle)
@DOC
Returns the hyperbolic cosine of angle.
@EXAMPLES
<programlisting>
cosh(0) = 1
cosh(1.5707963267948966)  = 2.5091784786580567
</programlisting>
@SEE
cos, acosh, acos, sin, asin, asinh
*/

