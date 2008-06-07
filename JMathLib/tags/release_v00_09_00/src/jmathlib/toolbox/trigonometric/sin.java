package jmathlib.toolbox.trigonometric;

import jmathlib.core.functions.ExternalElementWiseFunction;

public class sin extends ExternalElementWiseFunction
{
    
    public sin()
    {
        name = "sin";
    }
    
    /**Calculates the sine of a complex number
     * @param arg = the angle as an array of double
     * @return the result as an array of double
     * */ 
    public double[] evaluateValue(double[] arg)
    {
        double result[] = new double[2];
        double scalar;
        double iz_re, iz_im;
        double _re1, _im1;
        double _re2, _im2;

        // iz:      i.Times(z) ...
        iz_re =  -arg[IMAG];
        iz_im =   arg[REAL];

        // _1:      iz.exp() ...
        scalar =  Math.exp(iz_re);
        _re1 =  scalar * Math.cos(iz_im);
        _im1 =  scalar * Math.sin(iz_im);

        // _2:      iz.neg().exp() ...
        scalar =  Math.exp(-iz_re);
        _re2 =  scalar * Math.cos(-iz_im);
        _im2 =  scalar * Math.sin(-iz_im);

        // _1:      _1.Minus(_2) ...
        _re1 = _re1 - _re2;                          // !!!
        _im1 = _im1 - _im2;                          // !!!

        // result:  _1.Div(2*i) ...
        result[REAL] = 0.5*_im1;
        result[IMAG] = -0.5*_re1;
        
        return result;
    }

}

/*
@@GROUP
trigonometric
@SYNTAX
answer = sin(angle)
@DOC
Returns the sine of angle.
@EXAMPLES
sin(0) = 0
sin(1.5708) = 1
@SEE
asin, sinh
*/

