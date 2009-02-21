package jmathlib.toolbox.trigonometric;

import jmathlib.core.functions.ExternalElementWiseFunction;

public class sinh extends ExternalElementWiseFunction
{
    
    public sinh()
    {
        name = "sinh";
    }
    
    /**Calculates the hyperbolic sine of a complex number
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

        // _1:      _1.Minus(_2) ...
        _re1 = _re1 - _re2;                        // !!!
        _im1 = _im1 - _im2;                        // !!!

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
sinh(angle)
@DOC
Returns the hyperbolic sine of angle.
@EXAMPLES
<programlisting>
sinh(0) = 0
sinh(1) = 1.175201
</programlisting>
@SEE
sin, asinh, cos, cosh, acosh
*/

