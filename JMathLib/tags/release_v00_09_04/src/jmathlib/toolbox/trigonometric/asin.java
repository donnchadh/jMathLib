package jmathlib.toolbox.trigonometric;

import jmathlib.core.functions.ExternalElementWiseFunction;
import jmathlib.toolbox.jmathlib.matrix.log;
import jmathlib.toolbox.jmathlib.matrix.sqrt;

public class asin extends ExternalElementWiseFunction
{
    
    public asin()
    {
        name = "asin";
    }
    
    /**Calculates the arcsine of a complex number
     *  @param arg = the value as an array of double
     *  @return the result as an array of double
     */ 
    public double[] evaluateValue(double[] arg)
    {
        double result[] = new double[2];
        //  asin(z)  =  -i * log(i*z + Sqrt(1 - z*z))
        double re =  arg[REAL];
        double im =  arg[IMAG];
        
        // _1:      one.Minus(z.Times(z)) ...
        result[REAL]       =  1.0 - ( (re*re) - (im*im) );
        result[IMAG]  =  0.0 - ( (re*im) + (im*re) );

        // result:  _1.Sqrt() ...
        sqrt s= new sqrt();
        result = s.evaluateValue(result);

        // _1:      z.Times(i) ...
        // result:  _1.Plus(result) ...
        result[REAL]       =   result[REAL] - im;
        result[IMAG]  = result[IMAG] +  re;

        // _1:      result.log() ...
        log logFunc = new log();
        result = logFunc.evaluateValue(result);

        double temp     = result[IMAG];
        result[IMAG]  =  -result[REAL];
        result[REAL]       =  temp;

        return result;
    }
}

/*
@GROUP
trigonometric
@SYNTAX
angle=asin(value)
@DOC
Returns the arc sine of the first operand.
@EXAMPLES
<programlisting>
asin(1) = 1.5707963267948966
asin(0) = 0
</programlisting>
@SEE
sin, asinh, cos, acos, acosh
*/

